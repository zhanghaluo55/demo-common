package com.hongpro.demo.common.validate.utils;

import com.google.gson.Gson;
import com.hongpro.demo.common.validate.base.BaseEntity;
import com.hongpro.demo.common.validate.exception.TransformException;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangzihong
 * @description 对象转换类
 * @date 2021/12/28 13:40
 */
public class ObjectTransformer {
    private static final Gson GSON = new Gson();

    private ObjectTransformer() {
    }

    public static <T> T transform(Object source, Class<T> t) {
        return source == null ? null : transform(source, t, null);
    }

    public static <T> T transform(Object source, Class<T> t, ObjectTransformAfter<T> after) {
        T target = null;
        if (source != null) {
            try {
                target = t.newInstance();
                BeanUtils.copyProperties(source, target);
                if (after != null) {
                    after.after(source, target);
                }
            } catch (InstantiationException | IllegalAccessException var5) {
                throw new TransformException("对象转换异常", var5);
            }
        }

        return target;
    }

    public static <T> List<T> transform(List<?> list, Class<T> t) {
        return list == null ? null : transform(list, t, null);
    }

    public static <T> List<T> transform(List<?> list, Class<T> t, ObjectTransformAfter<T> after) {
        List<T> resultList = null;
        if (null != list) {
            resultList = new ArrayList(list.size());
            for (Object element : list) {
                resultList.add(transform(element, t, after));
            }
        }

        return resultList;
    }

    public static <T extends BaseEntity> T transform(T t, Object source) {
        if (t != null && source != null) {
            BeanUtils.copyProperties(source, t);
        }

        return t;
    }

    public static <T> T transformByJson(String source, Class<T> t) {
        return GSON.fromJson(GSON.toJson(source), t);
    }

    public static <T> List<T> transformByJson(List<String> list, Class<T> t) {
        List<T> results = new ArrayList(list.size());
        list.forEach((element) -> results.add(transformByJson(element, t)));
        return results;
    }
}
