package com.hongpro.demo.common.validate.parser;

import com.hongpro.demo.common.validate.annotation.NotBlank;
import com.hongpro.demo.common.validate.model.result.ValidateResult;

import java.lang.reflect.Field;

/**
 * @author zhangzihong
 * @version 1.0.0.0
 * @description 字符串非空注解解析器
 * @date 2021/11/29 17:05
 */
public class NotBlankParser implements IAnnotationParser {

    @Override
    public ValidateResult validate(Field f, Object value) {
        ValidateResult result = new ValidateResult();
        if (f.isAnnotationPresent(NotBlank.class)) {
            NotBlank notBlank = f.getAnnotation(NotBlank.class);
            if (value == null || value.toString().length() == 0) {
                result.setMessage(notBlank.message());
            }
        }
        return result;
    }
}
