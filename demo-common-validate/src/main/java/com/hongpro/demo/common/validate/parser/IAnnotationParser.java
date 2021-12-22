package com.hongpro.demo.common.validate.parser;

import com.hongpro.demo.common.validate.model.result.ValidateResult;

import java.lang.reflect.Field;

/**
 * @author zhangzihong
 * @version 1.0.0.0
 * @description 通用解析器
 * @date 2021/11/29 17:06
 */
public interface IAnnotationParser {
    /**
     * 校验方法
     * @return 校验结果
     * @param f 校验参数
     * @param o 校验内容
     */
    ValidateResult validate(Field f, Object o);
}
