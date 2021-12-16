package com.hongpro.demo.common.validate.domain;

/**
 * @author zhangzihong
 * @version 1.0.0.0
 * @description 校验结果类
 * @date 2021/11/29 17:08
 */
public class ValidateResult {
    /**
     * 提示信息
     */
    private String message;

    /**
     * 是否通过检验
     */
    private boolean isValid = true;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.isValid = false;
        this.message = message;
    }

    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }
}
