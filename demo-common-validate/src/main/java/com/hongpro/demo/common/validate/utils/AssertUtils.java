package com.hongpro.demo.common.validate.utils;

import com.hongpro.demo.common.validate.exception.BusinessException;
import com.hongpro.demo.common.validate.exception.DataNotExistsException;
import com.hongpro.demo.common.validate.exception.ParamValidException;
import com.hongpro.demo.common.validate.model.result.ResultStatus;

import java.text.MessageFormat;
import java.util.function.Supplier;

/**
 * @author zhangzihong
 * @description 断言工具类
 * @date 2021/12/29 14:07
 */
public class AssertUtils {
    /**
     * 断言是否为真，如果为 false 抛出给定的异常<br>
     * <pre class="code">
     *     Assert.isTrue(i == 0, BaseException::new);
     * </pre>
     * @param expression 布尔值
     * @param supplier   指定断言不通过时抛出的异常
     * @throws E         如果expression为假，则抛出指定的异常
     */
    public static <E extends Throwable> void isTrue(boolean expression, Supplier<E> supplier) throws E {
        if (!expression) {
            throw supplier.get();
        }
    }

    /**
     * 断言是否为真，如果为 false 抛出 {@link BusinessException} 异常<br>
     * <pre class="code">
     *      Assert.isTrue(i == 0, GlobalReturnStatus.VALID_ERROR);
     * </pre>
     * @param expression          布尔值
     * @param resultStatus        错误抛出异常附带的提示
     * @throws BusinessException  如果expression为false，则抛出
     */
    public static void isTrue(boolean expression, ResultStatus resultStatus) throws BusinessException {
        isTrue(expression, () -> new BusinessException(resultStatus));
    }

    /**
     * 断言是否为真，如果为 false 抛出 {@link IllegalArgumentException} 异常<br>
     *
     * <pre class="code">
     *      Assert.isTrue(i == 0, "The value must be greater than zero");
     * </pre>
     *
     * @param expression       布尔值
     * @param messageTemplate   错误抛出异常附带的消息模板，变量用{0}代替
     * @param params             参数列表
     * @throws IllegalArgumentException 如果expression为false，则抛出
     */
    public static void isTrue(boolean expression, String messageTemplate, Object... params) throws IllegalArgumentException {
        isTrue(expression, () -> new IllegalArgumentException(MessageFormat.format(messageTemplate, params)));
    }


    /**
     * 断言是否为假，如果为 true 抛出指定类型异常<br>
     * 并使用指定的函数获取错误信息返回
     * <pre class="code">
     *  Assert.isFalse(i > 0, ()->{
     *      return new BusinessException(GlobalReturnStatus.VALID_ERROR);
     *  });
     * </pre>
     *
     * @param <E>           异常类型
     * @param expression    布尔值
     * @param supplier 指定断言不通过时抛出的异常
     * @throws E 如果expression为false，则抛出
     */
    public static <E extends Throwable> void isFalse(boolean expression, Supplier<E> supplier) throws E {
        if (expression) {
            throw supplier.get();
        }
    }

    /**
     * 断言是否为真，如果为 false 抛出 {@link BusinessException} 异常<br>
     * <pre class="code">
     *      Assert.isFalse(i == 0, GlobalReturnStatus.VALID_ERROR);
     * </pre>
     * @param expression          布尔值
     * @param resultStatus        错误抛出异常附带的提示
     * @throws BusinessException  如果expression为false，则抛出
     */
    public static void isFalse(boolean expression, ResultStatus resultStatus) throws BusinessException {
        isFalse(expression, () -> new BusinessException(resultStatus));
    }

    /**
     * 断言是否为真，如果为 false 抛出 {@link BusinessException} 异常<br>
     * <pre class="code">
     *      Assert.isFalse(i == 0, GlobalReturnStatus.VALID_ERROR);
     * </pre>
     * @param expression          布尔值
     * @param errorMessage        错误抛出异常附带的提示
     * @throws BusinessException  如果expression为false，则抛出
     */
    public static void isFalse(boolean expression, String errorMessage) throws ParamValidException {
        isFalse(expression, () -> new ParamValidException(errorMessage));
    }

    /**
     * 断言对象是否为null, 如果不为null 则抛出指定类型的异常
     * <pre class="code">
     *     Assert.isNull(obj, () -> {
     *         return new BaseException("message to return");
     *     }
     * </pre>
     * @param obj       被检查对象
     * @param supplier  错误抛出异常附带的消息生成接口
     * @throws E       如果断言的对象不为空则抛出该异常
     */
    public static <E extends Throwable> void isNull(Object obj, Supplier<E> supplier) throws E {
        if (null != obj) {
            throw supplier.get();
        }
    }

    /**
     * 断言对象是否为null, 如果不为null 则抛出{@link IllegalArgumentException} 异常
     * <pre class="code">
     *    Assert.isNull(obj, "The obj must be null")
     * </pre>
     * @param obj                       被检查对象
     * @param messageTemplate           消息模板，变量使用{0}、{1}标识
     * @param params                    参数列表
     * @throws IllegalArgumentException 如果断言的对象不为空则抛出该异常
     */
    public static void isNull(Object obj, String messageTemplate, Object... params)  throws IllegalArgumentException {
        isNull(obj, () -> new IllegalArgumentException(MessageFormat.format(messageTemplate, params)));
    }

    /**
     * 断言对象是否为null, 如果不为null 则抛出{@link IllegalArgumentException} 异常
     *
     * <pre class="code">
     *     Assert.isNull(obj)
     * </pre>
     *
     * @param obj                        被检查对象
     * @throws IllegalArgumentException  如果断言的对象不为空则抛出该异常
     */
    public static void isNull(Object obj) throws IllegalArgumentException {
        isNull(obj, "[ASSERT FAILED] >>> the object argument must be null");
    }

    /**
     * 断言对象不为null, 如果为null 则抛出指定类型的异常
     * <pre class="code">
     *     Assert.notNull(obj, () -> {
     *         return new BaseException("message to return");
     *     }
     * </pre>
     * @param obj       被检查对象
     * @param supplier  错误抛出异常附带的消息生成接口
     * @throws E       如果断言的对象不为空则抛出该异常
     */
    public static <E extends Throwable> void notNull(Object obj, Supplier<E> supplier) throws E {
        if (null == obj) {
            throw supplier.get();
        }
    }

    /**
     * 断言对象不为null, 如果为null 则抛出 {@code DataNotExistsException} 异常
     *
     * @param obj        被检查对象
     * @throws DataNotExistsException  如果断言的对象为null 则抛出
     */
    public static void notNull(Object obj) throws DataNotExistsException {
        notNull(obj, DataNotExistsException::new);
    }

    /**
     * 断言对象不为null, 如果为null 则抛出 {@code DataNotExistsException} 异常
     *
     * @param obj        被检查对象
     * @throws DataNotExistsException  如果断言的对象为null 则抛出
     */
    public static void notNull(Object obj, ResultStatus resultStatus) throws DataNotExistsException {
        if (resultStatus.getCode().contains("A9")) {
            notNull(obj, () -> new ParamValidException(resultStatus));
        } else {
            notNull(obj, () -> new DataNotExistsException(resultStatus));
        }
    }


    /**
     * 断言对象不为null, 如果为null 则抛出指定类型的异常
     * <pre class="code">
     *     Assert.notBlank(obj, () -> {
     *         return new BaseException("message to return");
     *     }
     * </pre>
     * @param str       被检查字符串
     * @param supplier  错误抛出异常附带的消息生成接口
     * @throws E       如果断言的对象不为空则抛出该异常
     */
    public static <E extends Throwable> void notBlank(String str, Supplier<E> supplier) throws E {
        if (null == str || str.trim().length() == 0) {
            throw supplier.get();
        }
    }

    /**
     * 断言对象不为null, 如果为null 则抛出 {@code ParamValidException} 异常
     *
     * @param str        被检查对象
     * @throws DataNotExistsException  如果断言的对象为null 则抛出
     */
    public static void notBlank(String str, ResultStatus resultStatus) throws ParamValidException {
        notBlank(str, () -> new ParamValidException(resultStatus));
    }

    /**
     * 断言对象不为null, 如果为null 则抛出 {@code ParamValidException} 异常
     *
     * @param str        被检查对象
     * @throws DataNotExistsException  如果断言的对象为null 则抛出
     */
    public static void notBlank(String str, String errorMsg) throws ParamValidException {
        notBlank(str, () -> new ParamValidException(errorMsg));
    }

    /**
     * 断言对象为null 或字符串为空, 如果对象不为为null 或字符串不为空 则抛出指定类型的异常
     * <pre class="code">
     *     Assert.isBlank(obj, () -> {
     *         return new BaseException("message to return");
     *     }
     * </pre>
     * @param str       被检查字符串
     * @param supplier  错误抛出异常附带的消息生成接口
     * @throws E       如果断言的对象不为空则抛出该异常
     */
    public static <E extends Throwable> void isBlank(String str, Supplier<E> supplier) throws E {
        if (null != str && str.trim().length() != 0) {
            throw supplier.get();
        }
    }
}
