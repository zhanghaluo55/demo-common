package com.hongpro.demo.common.validate.parser;

import com.hongpro.demo.common.validate.annotation.NotBlank;
import com.hongpro.demo.common.validate.annotation.NotNull;
import com.hongpro.demo.common.validate.domain.ValidateResult;

import java.lang.reflect.Field;

/**
 * @author zhangzihong
 * @version 1.0.0.0
 * @description 非空注解解析器
 * @date 2021/11/29 17:05
 */
public class NotNullParser implements IAnnotationParser {

    @Override
    public ValidateResult validate(Field f, Object value) {
        ValidateResult result = new ValidateResult();
        if (f.isAnnotationPresent(NotNull.class)) {
            NotNull notNull = f.getAnnotation(NotNull.class);
            if (value == null) {
                result.setMessage(notNull.message());
            }
        }
        return result;
    }
}
