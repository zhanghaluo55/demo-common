package com.hongpro.demo.common.validate.annotation;

import java.lang.annotation.*;

/**
 * @author zhangzihong
 * @description 忽略校验
 * @date 2021/12/29 15:23
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface ValidIgnore {
}
