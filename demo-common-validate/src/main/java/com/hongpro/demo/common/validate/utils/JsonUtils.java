package com.hongpro.demo.common.validate.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;

import java.util.List;

/**
 * @author zhangzihong
 * @description json字符串工具类
 * @date 2021/12/27 15:39
 */
public class JsonUtils {
    private static final Logger logger = LoggerFactory.getLogger(JsonUtils.class);

    /**
     * 定义jackson对象
     */
    private static final ObjectMapper MAPPER = new ObjectMapper();


    /**
     * 将对象转换成json字符串。
     * @param data 对象
     * @return 转换后json字符串
     */
    public static String objectToJson(Object data) {
        try {
            return MAPPER.writeValueAsString(data);
        } catch (JsonProcessingException e) {
            logger.error("ObjectToJson JsonProcessingException: ", e);
        }
        return null;
    }

    /**
     * 将json结果集转化为对象
     *
     * @param jsonData json数据
     * @param beanType 对象中的object类型
     * @param <T> 转换对象泛型
     * @return 转换后json字符串
     */
    public static <T> T jsonToPojo(String jsonData, Class<T> beanType) {
        try {
            return MAPPER.readValue(jsonData, beanType);
        } catch (Exception e) {
            logger.error("JsonToPojo Exception: ", e);
        }
        return null;
    }

    /**
     * 将json数据转换成pojo对象list
     * @param jsonData 原始json字符串
     * @param beanType 转换对象类型
     * @param <T> 转换对象泛型
     * @return 转换后对象
     */
    public static <T> List<T> jsonToList(String jsonData, Class<T> beanType) {
        JavaType javaType = MAPPER.getTypeFactory().constructParametricType(List.class, beanType);
        try {
            return MAPPER.readValue(jsonData, javaType);
        } catch (Exception e) {
            logger.error("JsonToList Exception: ", e);
        }
        return Collections.emptyList();
    }
}
