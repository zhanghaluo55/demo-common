package com.hongpro.demo.common.validate.aop;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.ReUtil;
import cn.hutool.core.util.StrUtil;
import com.hongpro.demo.common.validate.annotation.*;
import com.hongpro.demo.common.validate.base.IValidParam;
import com.hongpro.demo.common.validate.exception.ParamValidException;
import com.hongpro.demo.common.validate.model.result.BaseReturn;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * 参数校验切面<br>
 *
 * 规则：类方法上加有：ValidArguments 注解（接口上可有可无）
 *
 * 参数验证：
 *  1. 如果对象实现了 {@link IValidParam} 接口，则调用接口方式进行校验
 *  2. 如果没有实现 IValidParam 接口，但有ApiModel注解，那么根据ApiModel的ApiModelProperty注解验证
 *  3. 如果添加 {@link ValidIgnore} 注解则不验证该参数。
 *  4. 如果添加 {@link NotNull} 注解，任何类型  属性不能为null
 *  5. 如果添加 {@link Size} 注解，则限定字符串长度，或Integer的最大值和最小值
 *  6. 如果添加 {@link NotBlank} 注解，字符类不能为null，且去掉空格之后长度大于0
 *  7. 如果添加 {@link NotEmpty} 注解，集合不能为null，且size大于0
 *  8. 如果添加 {@link Pattern} 注解，正则匹配字符串
 * @author zhangzihong
 * @description 参数校验切面
 * @date 2021/12/29 15:05
 */
@Order(1)
@Aspect
@Component
public class ArgumentValidAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(ArgumentValidAspect.class);

    /**
     * 定义切面
     */
    @Pointcut("@annotation(com.hongpro.demo.common.validate.annotation.ValidArguments)")
    public void execute() {
    }

    /**
     * 执行验证
     * @param joinPoint  切入点
     * @return 返回校验结果
     * @throws Throwable 异常
     */
    @Around("execute()")
    public Object valid(ProceedingJoinPoint joinPoint) throws Throwable {
        StringBuilder strBuilder = validArguments(joinPoint);
        Object result;
        if (strBuilder.length() == 0) {
            result = joinPoint.proceed();
        } else {
            result = handleValidFailed(joinPoint, strBuilder);
        }
        LOGGER.debug("执行验证通过，类：{},方法：{}", joinPoint.getTarget().getClass().getSimpleName(),
                joinPoint.getSignature().getName());
        return result;
    }

    /**
     * 检查和校验参数
     *
     * @param joinPoint  切入点
     * @return 问题串
     */
    private StringBuilder validArguments(ProceedingJoinPoint joinPoint) throws IllegalAccessException {
        Object[] args = joinPoint.getArgs();
        StringBuilder strBuilder = new StringBuilder();
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Annotation[][] annotations = methodSignature.getMethod().getParameterAnnotations();
        String msg = validParamNull(methodSignature, annotations, args);
        if (null != msg) {
            strBuilder.append(msg);
        } else {
            for (int i = 0; i < args.length; i++) {
                Object arg = args[i];
                if (arg != null) {
                    validArg(annotations[i], strBuilder, arg);
                }
            }
        }
        return strBuilder;
    }

    /**
     * 校验一个参数
     *
     * @param annotation  注解
     * @param strBuilder  错误提示信息
     * @param arg         参数
     */
    private void validArg(Annotation[] annotation, StringBuilder strBuilder, Object arg) throws IllegalAccessException {
        if (needValid(annotation, arg)) {
            if (arg instanceof IValidParam) {
                IValidParam iValidParam = (IValidParam) arg;
                strBuilder.append(iValidParam.valid());
            } else {
                Field[] fields = arg.getClass().getDeclaredFields();
                for (Field field : fields) {
                    validModelField(strBuilder, arg, field);
                }
            }
        }
    }

    /**
     *  校验模型成员
     *
     * @param strBuilder  错误信息
     * @param arg         参数
     * @param field       成员属性
     */
    private void validModelField(StringBuilder strBuilder, Object arg, Field field) throws IllegalAccessException {
        if (field.isAnnotationPresent(ApiModelProperty.class)) {
            ReflectionUtils.makeAccessible(field);
            Object value = field.get(arg);
            if (ObjectUtil.isEmpty(value)) {
                ApiModelProperty apiModelProperty = field.getAnnotation(ApiModelProperty.class);
                if (apiModelProperty.required()) {
                    if (strBuilder.length() > 0) {
                        strBuilder.append(";");
                    }
                    strBuilder.append(apiModelProperty.value()).append("不能为空");
                }
            }
            // 检查自定义的校验注解
            if (strBuilder.length() == 0) {
                validFieldAnnotation(strBuilder, arg, field);
            }
        }
    }

    /**
     * 检查自定义的校验注解
     *
     * @param strBuilder  错误提示信息
     * @param arg         参数
     * @param field       成员属性
     */
    private void validFieldAnnotation(StringBuilder strBuilder, Object arg, Field field)
            throws IllegalArgumentException, IllegalAccessException {
        Annotation[] annotations = field.getAnnotations();
        for (Annotation annotation : annotations) {
            if (strBuilder.length() > 0) {
                break;
            }
            if (annotation instanceof Size) {
                validSize(strBuilder, arg, field);
            } else if (annotation instanceof NotBlank) {
                validNotBlank(strBuilder, arg, field);
            } else if (annotation instanceof NotEmpty) {
                validNotEmpty(strBuilder, arg, field);
            } else if (annotation instanceof NotNull) {
                validNotNull(strBuilder, arg, field);
            } else if (annotation instanceof Pattern) {
                validPattern(strBuilder, arg, field);
            }
        }
    }

    /**
     * 判断是否需要校验
     *
     * @param annotations  注解
     * @param arg         参数
     * @return 是否需要校验
     */
    private boolean needValid(Annotation[] annotations, Object arg) {
        boolean needValid = arg.getClass().isAnnotationPresent(ApiModel.class) || arg instanceof IValidParam;
        if (needValid) {
            for (Annotation annotation : annotations) {
                if (annotation instanceof ValidIgnore) {
                    needValid = false;
                    break;
                }
            }
        }
        return needValid;
    }

    /**
     * 判断参数是否为null
     *
     * @param methodSignature  方法定义
     * @param annotations      注解
     * @param args             参数列表
     * @return  错误信息
     */
    private String validParamNull(MethodSignature methodSignature, Annotation[][] annotations, Object[] args) {
        String result = null;
        for (int i = 0; i < annotations.length; i++) {
            Annotation[] paramAnnotation = annotations[i];
            Object param = args[i];
            // 判断参数是否为空
            if (param == null) {
                result = getNullDescription(paramAnnotation);
                result = StrUtil.isBlank(result) ? getNullDescription(methodSignature, i) : result;
                break;
            }
        }
        return result;
    }

    /**
     * 校验不通过信息处理
     *
     * @param joinPoint  切入点
     * @param strBuilder 错误提示信息
     * @return 返回结果
     */
    private Object handleValidFailed(ProceedingJoinPoint joinPoint, StringBuilder strBuilder)
            throws IllegalAccessException, InstantiationException {
        String rst = strBuilder.toString();
        LOGGER.warn("验证失败,类：{},方法：{},信息:{}", joinPoint.getTarget().getClass().getSimpleName(),
                joinPoint.getSignature().getName(), rst);
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        if (BaseReturn.class.isAssignableFrom(methodSignature.getReturnType())) {
            BaseReturn baseReturn = (BaseReturn) methodSignature.getReturnType().newInstance();
            return baseReturn.setReturnStatus("A900", rst);
        } else {
            throw new ParamValidException(rst);
        }
    }

    /**
     * 获取参数注解的提示信息
     *
     * @param paramAnnotation  注解列表
     * @return 错误信息
     */
    private String getNullDescription(Annotation[] paramAnnotation) {
        String description = null;
        for (Annotation annotation : paramAnnotation) {
            if (annotation instanceof NotNull) {
                NotNull notNull = (NotNull) annotation;
                description = notNull.message();
                break;
            }
        }
        return description;
    }

    /**
     * 获取参数错误信息
     * @param methodSignature 方法定义
     * @param pos            参数位置
     * @return 错误信息
     */
    private String getNullDescription(MethodSignature methodSignature, int pos) {
        return "参数" + getParameterName(methodSignature, pos) + "不能为空";
    }

    /**
     * 取得参数名
     * @param methodSignature 方法定义
     * @param pos            参数位置
     * @return 参数名
     */
    private String getParameterName(MethodSignature methodSignature, int pos) {
        String[] params = methodSignature.getParameterNames();
        return params == null ? "第" + pos + "个" : params[pos];
    }

    /**
     * 校验Size自定义注解，校验字符串长度，或Integer的最大值和最小值
     *
     * @param strBuilder 错误信息
     * @param arg        参数
     * @param field      成员属性
     */
    private void validSize(StringBuilder strBuilder, Object arg, Field field)
            throws IllegalArgumentException, IllegalAccessException {
        Size size = field.getAnnotation(Size.class);
        Object value = field.get(arg);
        int currentSize = 0;
        if (value instanceof String) {
            currentSize = String.valueOf(value).length();
        } else if (value instanceof Integer) {
            currentSize = (int) value;
        }
        if (currentSize < size.min() || currentSize > size.max()) {
            strBuilder.append(size.message());
        }
    }

    /**
     * 校验NotBlank自定义注解，字符类不能为null，且去掉空格之后长度大于0
     *
     * @param strBuilder 错误信息
     * @param arg        参数
     * @param field      成员属性
     */
    private void validNotBlank(StringBuilder strBuilder, Object arg, Field field)
            throws IllegalArgumentException, IllegalAccessException {
        NotBlank notBlank = field.getAnnotation(NotBlank.class);
        Object value = field.get(arg);
        if (value instanceof String) {
            if (StrUtil.isBlank(String.valueOf(value))) {
                strBuilder.append(notBlank.message());
            }
        }
    }

    /**
     * 校验NotEmpty自定义注解，集合不能为null，且size大于0
     *
     * @param strBuilder 错误信息
     * @param arg        参数
     * @param field      成员属性
     */
    private void validNotEmpty(StringBuilder strBuilder, Object arg, Field field)
            throws IllegalArgumentException, IllegalAccessException {
        NotEmpty notEmpty = field.getAnnotation(NotEmpty.class);
        Object value = field.get(arg);
        if (ObjectUtil.isEmpty(value)) {
            strBuilder.append(notEmpty.message());
        }
    }

    /**
     * 校验NotNull自定义注解，任何类型  属性不能为null
     *
     * @param strBuilder 错误信息
     * @param arg        参数
     * @param field      成员属性
     */
    private void validNotNull(StringBuilder strBuilder, Object arg, Field field)
            throws IllegalArgumentException, IllegalAccessException {
        NotNull notNull = field.getAnnotation(NotNull.class);
        Object value = field.get(arg);
        if (ObjectUtil.isEmpty(value)) {
            strBuilder.append(notNull.message());
        }
    }

    /**
     * 校验Pattern自定义注解，正则匹配字符串
     *
     * @param strBuilder 错误信息
     * @param arg        参数
     * @param field      成员属性
     */
    private void validPattern(StringBuilder strBuilder, Object arg, Field field)
            throws IllegalArgumentException, IllegalAccessException {
        Pattern pattern = field.getAnnotation(Pattern.class);
        Object value = field.get(arg);
        if (!ReUtil.isMatch(pattern.regexp(), String.valueOf(value))) {
            strBuilder.append(pattern.message());
        }
    }
}
