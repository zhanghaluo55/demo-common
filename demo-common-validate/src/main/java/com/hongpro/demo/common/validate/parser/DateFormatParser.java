package com.hongpro.demo.common.validate.parser;

import com.hongpro.demo.common.validate.annotation.DateFormat;
import com.hongpro.demo.common.validate.model.constant.Level;
import com.hongpro.demo.common.validate.model.result.ValidateResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * @author zhangzihong
 * @version 1.0.0.0
 * @description 日期格式注解解析器
 * @date 2021/11/29 17:05
 */
public class DateFormatParser implements IAnnotationParser {
    private static final Logger logger = LoggerFactory.getLogger(DateFormatParser.class);

    @Override
    public ValidateResult validate(Field f, Object value) {
        ValidateResult result = new ValidateResult();
        if (f.isAnnotationPresent(DateFormat.class)) {
            DateFormat dateFormat = f.getAnnotation(DateFormat.class);
            if (value != null) {
                SimpleDateFormat format = new SimpleDateFormat(dateFormat.format());
                try {
                    format.parse(value.toString());
                } catch (ParseException e) {
                    logger.error(Level.SEVERE + " ParseException ", e);
                    result.setMessage(dateFormat.fieldName() + "不满足格式：" + dateFormat.format());
                }
            }
        }
        return result;
    }
}
