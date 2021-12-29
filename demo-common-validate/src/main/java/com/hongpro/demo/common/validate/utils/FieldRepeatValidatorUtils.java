package com.hongpro.demo.common.validate.utils;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hongpro.demo.common.validate.base.SuperEntity;
import io.swagger.annotations.ApiModelProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ReflectionUtils;

import javax.validation.ValidationException;
import java.lang.reflect.Field;
import java.util.List;

/**
 * @author zhangzihong
 * @description 数据库字段内容重复检验处理工具类
 * @date 2021/12/23 9:41
 */
public class FieldRepeatValidatorUtils {
    private static final Logger logger = LoggerFactory.getLogger(FieldRepeatValidatorUtils.class);

    /**
     * 实体类id
     */
    private static String id;

    /**
     * 实体类id值
     */
    private static Integer idValue;

    /**
     * 校验字段
     */
    private static String field;

    /**
     * 校验字段值 - 字符串、数字、对象..
     */
    private static Object fieldValue;

    /**
     * 校验字段 - 数据库字段
     */
    private static String dbField;

    /**
     * 实体类对象值
     */
    private static Object object;

    public static boolean fieldRequest(String id, String field, Object object, String message) {
        FieldRepeatValidatorUtils.id = id;
        FieldRepeatValidatorUtils.field = field;
        FieldRepeatValidatorUtils.object = object;
        getFieldValue();

        SuperEntity baseMapper = (SuperEntity) object;
        List list = baseMapper.selectList(new QueryWrapper().eq(true, dbField, fieldValue));
        //IPage iPage = baseMapper.selectPage(new Page<>(1, 999), new QueryWrapper().eq(true, field, fieldValue));
        if (idValue == null) {
            if (!CollectionUtils.isEmpty(list)) {
                throw new ValidationException(message);
            }
        } else {
            if (!CollectionUtils.isEmpty(list)) {
                Object fieldValueNew = fieldValue;
                FieldRepeatValidatorUtils.object = baseMapper.selectById(idValue);
                getFieldValue();
                if (!fieldValueNew.equals(fieldValue) ||list.size() > 1) {
                    throw new ValidationException(message);
                }
            }
        }
        return true;
    }

    public static void getFieldValue() {
        Field[] fields = object.getClass().getDeclaredFields();
        for (Field f : fields) {
            ReflectionUtils.makeAccessible(f);
            if (f.isAnnotationPresent(ApiModelProperty.class)) {
                if (f.getName().equals(field)) {
                    try {
                        fieldValue = f.get(object);
                        TableField annotation = f.getAnnotation(TableField.class);
                        dbField = annotation.value();
                    } catch (IllegalAccessException e) {
                        logger.error("IllegalAccessException ", e);
                    }
                }
                if (id.equals(f.getName())) {
                    try {
                        idValue = (Integer) f.get(object);
                    } catch (IllegalAccessException e) {
                        logger.error("IllegalAccessException ", e);
                    }
                }
            }
        }
    }


}
