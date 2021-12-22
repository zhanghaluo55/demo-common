package com.hongpro.demo.common.validate.model.result;

import cn.hutool.core.text.CharSequenceUtil;

import java.io.Serializable;

/**
 * @author zhangzihong
 * @description 返回结果状态实体
 * @date 2021/12/16 14:27
 */
public class ResultStatus implements Serializable {
    private static final long serialVersionUID = 4839432678891616614L;

    /**
     * 响应码， 0000 成功  其他 失败
     */
    private final String code;
    /**
     * 提示信息
     */
    private String msg;

    public ResultStatus(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResultStatus format(Object... objects) {
        msg = CharSequenceUtil.indexedFormat(msg, objects);
        return this;
    }

    public ResultStatus msg(String msg) {
        this.msg = msg;
        return this;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

}
