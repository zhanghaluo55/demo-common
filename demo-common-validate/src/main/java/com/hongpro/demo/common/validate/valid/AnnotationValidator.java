package com.hongpro.demo.common.validate.valid;


import com.hongpro.demo.common.validate.constants.Level;
import com.hongpro.demo.common.validate.domain.ValidateResult;
import com.hongpro.demo.common.validate.parser.IAnnotationParser;
import com.hongpro.demo.common.validate.parser.NotBlankParser;
import com.hongpro.demo.common.validate.parser.NotNullParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangzihong
 * @version 1.0.0.0
 * @description 注解校验器
 * @date 2021/11/29 16:54
 */
public class AnnotationValidator {
    private static final Logger logger = LoggerFactory.getLogger(AnnotationValidator.class);

    private static final List<IAnnotationParser> VALIDATE_LIST = new ArrayList<>();

    static {
        VALIDATE_LIST.add(new NotBlankParser());
        VALIDATE_LIST.add(new NotNullParser());
    }

    public static <T> ValidateResult validate(T t) {
        ValidateResult result = null;
        for (Field f : t.getClass().getDeclaredFields()) {
            ReflectionUtils.makeAccessible(f);
            Object value = null;
            try {
                value = f.get(t);
            } catch (IllegalAccessException e) {
                logger.error(Level.SEVERE + " IllegalAccessException ", e);
            } catch (IllegalArgumentException e) {
                logger.error(Level.SEVERE + " IllegalArgumentException ", e);
            }

            for (IAnnotationParser va : VALIDATE_LIST) {
                result = va.validate(f, value);
                if (!result.isValid()) {
                    return result;
                }
            }
        }
        return result;
    }

    public static void register(IAnnotationParser parser) {
        VALIDATE_LIST.add(parser);
    }

}
