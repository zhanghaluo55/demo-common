package com.hongpro.demo.common.validate.annotation;

import com.hongpro.demo.common.validate.parser.FieldRepeatValidatorParser;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author zhangzihong
 * @description 校验注解
 * @date 2021/12/22 19:01
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PARAMETER, ElementType.FIELD, ElementType.TYPE})
@Constraint(validatedBy = FieldRepeatValidatorParser.class)
public @interface FieldRepeatValidator {
    /**
     * 实体类id字段 - 默认为id
     */
    String id() default "id";

    /**
     * 注解属性 - 对应校验字段
     */
    String field();

    /**
     * 默认错误提示信息
     */
    String message() default "字段内容重复！";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
