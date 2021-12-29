package com.hongpro.demo.common.validate.utils;

/**
 * @author zhangzihong
 * @description 函数式接口
 * @date 2021/12/28 13:40
 */
@FunctionalInterface
public interface ObjectTransformAfter<T> {
    /**
     *  接口方法
     * @param var1 转换前对象
     * @param var2 转换后对象
     */
    void after(Object var1, T var2);
}
