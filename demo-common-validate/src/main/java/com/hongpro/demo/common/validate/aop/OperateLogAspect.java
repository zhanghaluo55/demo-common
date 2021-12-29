package com.hongpro.demo.common.validate.aop;

import com.hongpro.demo.common.validate.annotation.OperateLog;
import com.hongpro.demo.common.validate.domain.OperateLogDomain;
import com.hongpro.demo.common.validate.model.constant.GlobalReturnStatus;
import com.hongpro.demo.common.validate.model.result.BaseReturn;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zhangzihong
 * @description 业务操作日志切面
 * @date 2021/12/29 15:44
 */
public class OperateLogAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(OperateLogAspect.class);
    @Autowired
    private SessionUserApi sessionUserApi;
    /**
     * 定义切面
     */
    @Pointcut("@annotation(com.hongpro.demo.common.validate.annotation.OperateLog)")
    public void businessLogAspect() {
    }


    /**
     * 在被拦截对象所有方法执行无异常，return的时候
     * @param joinPoint  切入点
     * @param result     结果
     */
    @AfterReturning(returning = "result", pointcut = "businessLogAspect()")
    public void doReturning(JoinPoint joinPoint, Object result) {
        try {
            OperateLogDomain operateLogDomain = getBizLogMethodInfo(joinPoint);
            operateLogDomain.setOperationResult("成功");
            if (BaseReturn.class.isAssignableFrom(result.getClass())) {
                String code = ((BaseReturn) result).getCode();
                if (!GlobalReturnStatus.SUCCESS.getCode().equals(code)) {
                    operateLogDomain.setOperationResult("失败");
                }
            }
            operatorLog(operateLogDomain, sessionUserApi.getSessionUser());
        } catch (Exception e) {
            LOGGER.error("记录操作成功的日志出错：" + e.getMessage(), e);
        }
    }

    /**
     * 在被拦截对象所有方法发生异常的时候
     * @param joinPoint 切入点
     */
    @AfterThrowing("businessLogAspect()")
    public void doThrowing(JoinPoint joinPoint) {
        try {
            OperateLogDomain bizOperateLog = getBizLogMethodInfo(joinPoint);
            bizOperateLog.setOperationResult("失败");
            operatorLog(bizOperateLog, sessionUserApi.getSessionUser());
        } catch (Exception e) {
            LOGGER.error("记录操作失败的日志出错：" + e.getMessage(), e);
        }
    }


    /**
     * 得到注解描述信息的方法
     * @param joinPoint  切入点
     * @return  注解中的描述信息
     */
    private OperateLogDomain getBizLogMethodInfo(JoinPoint joinPoint) throws Exception {
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        Class<?> targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        OperateLogDomain operateLogDomain = null;
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Class<?>[] cls = method.getParameterTypes();
                if (cls.length == arguments.length) {
                    OperateLog bizLog = method.getAnnotation(OperateLog.class);
                    operateLogDomain = OperateLogDomain.builder().logType(bizLog.value()).bizType(bizLog.type())
                            .bizName(bizLog.name()).build();
                    break;
                }
            }
        }
        return operateLogDomain;
    }

    private void operatorLog(OperateLogDomain operateLog, ISessionUser sessionUser) {
        Map<String, String> map = new ConcurrentHashMap<>();
        map.put("user_name", sessionUser.getUsername());
//        LogBo logBo = LogBo.builder().operatorId(sessionUser.getId()).operatorName(sessionUser.getName())
//                .clientIp(sessionUser.getIp()).logType(bizOperateLog.getLogType())
//                .businessType(bizOperateLog.getBizType()).otherInfoMap(map).businessId(sessionUser.getId())
//                .businessName(bizOperateLog.getBizName()).operationDomain(sessionUser.getDomain())
//                .organizationId(sessionUser.getOrganizationId()).divisionId(sessionUser.getOrganizationDivision())
//                .operationResult(bizOperateLog.getOperationResult()).build();
//        logApi.insert(logBo);
        LOGGER.info("日志类型：{}, 业务类型：{}, 业务名称：{}, 操作人：{},  操作结果：{}", operateLog.getLogType(),
                operateLog.getBizType(), operateLog.getBizName(), sessionUser.getName(),
                operateLog.getOperationResult());
    }

    /**
     * 内部接口，临时定义。可根据业务系统当前登录用户具体定义
     */
    interface SessionUserApi {
        /**
         * 获取当前登录用户
         * @return 当前登录用户
         */
        ISessionUser getSessionUser();
    }
    class ISessionUser {
        String name;

        String username;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }
    }
}
