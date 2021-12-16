package com.hongpro.demo.common.validate.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author zhangzihong
 * @version 1.0.0.0
 * @description 扩展校验框架
 * @date 2021/11/29 18:06
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DateFormat {
    String fieldName();

    String format();

    String message() default "";
}
