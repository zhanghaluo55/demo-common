package com.hongpro.demo.common.validate.annotation;

import java.lang.annotation.*;

/**
 * @author zhangzihong
 * @description 操作日志注解
 * @date 2021/12/29 15:03
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface OperateLog {
    /**
     * 日志类型
     */
    String value() default "";
    /**
     * 业务分类
     */
    String type() default "";
    /**
     * 业务名称
     */
    String name() default "";
}

