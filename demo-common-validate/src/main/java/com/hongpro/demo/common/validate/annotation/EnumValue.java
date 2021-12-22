package com.hongpro.demo.common.validate.annotation;

import lombok.SneakyThrows;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.*;
import java.lang.reflect.Method;

/**
 * @author zhangzihong
 * @description 枚举范围校验
 * @date 2021/12/16 17:23
 */
@Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = {EnumValue.EnumValueValidator.class})
public @interface EnumValue {
    /**
     * 错误提示信息
     */
    String message() default "";

    /**
     * 支持字符串数组验证
     */
    String[] strValues() default {};

    /**
     * 支持int数组验证
     */
    int[] intValues() default {};

    /**
     * 支持枚举数组验证
     */
    Class<?>[] enumValues() default {};

    /**
     * 分组
     */
    Class<?>[] groups() default {};

    /**
     * 负载
     */
    Class<? extends Payload>[] payload() default {};

    @Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE, ElementType.FIELD, ElementType.PARAMETER})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @interface List {
        EnumValue[] value();
    }

    /**
     * 校验类逻辑定义
     */
    class EnumValueValidator implements ConstraintValidator<EnumValue, Object> {
        /**
         * 字符串类型数组
         */
        private String[] strValues;

        /**
         * int类型数组
         */
        private int[] intValues;

        /**
         * 枚举类
         */
        private Class<?>[] enumValues;

        /**
         * 初始化方法
         *
         * @param constraintAnnotation 枚举校验注解
         */
        @Override
        public void initialize(EnumValue constraintAnnotation) {
            strValues = constraintAnnotation.strValues();
            intValues = constraintAnnotation.intValues();
            enumValues = constraintAnnotation.enumValues();
        }

        @SneakyThrows
        @Override
        public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
            //字符串数组校验
            if (strValues != null && strValues.length > 0 && value instanceof String) {
                for (String s : strValues) {
                    if (s.equals(value)) {
                        return true;
                    }
                }
            }

            if (intValues != null && intValues.length > 0 && value instanceof Integer) {
                for (Integer i : intValues) {
                    if (i == value) {
                        return true;
                    }
                }
            }

            if (enumValues != null && enumValues.length > 0) {
                for (Class<?> cl : enumValues) {
                    if (cl.isEnum()) {
                        Object[] obs = cl.getEnumConstants();
                        Method method = cl.getMethod("getValue");
                        for (Object obj : obs) {
                            Object methodReturn = method.invoke(obj, null);
                            if (value != null && value.equals(methodReturn.toString())) {
                                return true;
                            }
                        }
                    }
                }
            }
            return false;
        }
    }
}
