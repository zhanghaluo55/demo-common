package com.hongpro.demo.common.validate.annotation;

import java.lang.annotation.*;

/**
 * @author zhangzihong
 * @description 是否要进行参数验证
 * @date 2021/12/29 15:22
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface ValidArguments {
}
