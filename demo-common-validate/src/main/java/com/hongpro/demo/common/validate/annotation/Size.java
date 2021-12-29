package com.hongpro.demo.common.validate.annotation;

import java.lang.annotation.*;

/**
 * @author zhangzihong
 * @description 长度校验
 * @date 2021/12/29 15:21
 */
@Target({ElementType.PARAMETER, ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface Size {
    /**
     * 字符串最小长度
     */
    int min() default 0;
    /**
     * 字符串最大长度
     */
    int max();
    /**
     * 提示信息
     */
    String message() default "";
}
