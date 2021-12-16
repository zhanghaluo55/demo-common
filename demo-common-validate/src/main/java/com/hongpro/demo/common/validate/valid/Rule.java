package com.hongpro.demo.common.validate.valid;

/**
 * @author zhangzihong
 * @version 1.0.0.0
 * @description 校验规则，扩展校验规则
 * @date 2021/11/29 16:48
 */
public interface Rule {
    /**
     * 提示信息
     * @return 信息内容
     */
    String getMessage();

    /**
     * 是否通过检验
     * @return true:通过； false:不通过
     */
    boolean isValid();
}
