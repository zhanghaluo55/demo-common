package com.hongpro.demo.common.validate.annotation;

import java.lang.annotation.*;

/**
 * @author zhangzihong
 * @description
 * @date 2021/12/29 15:25
 */
@Target({ElementType.PARAMETER, ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface NotEmpty {
    /**
     * 提示信息
     */
    String message();
}
