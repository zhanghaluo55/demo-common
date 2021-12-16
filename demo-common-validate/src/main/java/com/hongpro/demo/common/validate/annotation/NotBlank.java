package com.hongpro.demo.common.validate.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author zhangzihong
 * @version 1.0.0.0
 * @description 字符串非空校验注解
 * @date 2021/11/29 17:02
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface NotBlank {
    String fieldName();

    String message() default "";
}
