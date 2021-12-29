package com.hongpro.demo.common.validate.parser;

import com.hongpro.demo.common.validate.annotation.FieldRepeatValidator;
import com.hongpro.demo.common.validate.utils.FieldRepeatValidatorUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author zhangzihong
 * @description FieldRepeatValidator注解解析类
 * @date 2021/12/22 19:04
 */
public class FieldRepeatValidatorParser implements ConstraintValidator<FieldRepeatValidator, Object> {
    /**
     * 实体类id
     */
    private String id;

    /**
     * 校验字段
     */
    private String field;

    /**
     * 提示信息
     */
    private String message;

    @Override
    public void initialize(FieldRepeatValidator constraintAnnotation) {
        this.id = constraintAnnotation.id();
        this.field = constraintAnnotation.field();
        this.message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        return FieldRepeatValidatorUtils.fieldRequest(id, field, o, message);
    }
}
