package com.hongpro.demo.common.validate.annotation;

import java.lang.annotation.*;

/**
 * @author zhangzihong
 * @description 正则校验
 * @date 2021/12/29 15:20
 */
@Target({ElementType.PARAMETER, ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface Pattern {
    /**
     * 正则表达式
     */
    String regexp();

    /**
     * 提示信息
     */
    String message() default "";
}
