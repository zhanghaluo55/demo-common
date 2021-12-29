package com.hongpro.demo.common.validate.exception;

/**
 * @author zhangzihong
 * @description 校验异常
 * @date 2021/12/23 11:06
 */
public class ValidatorException extends RuntimeException {
    private static final long serialVersionUID = 500259507725502614L;
    /**
     * 异常状态码
     */
    private final String code;

    private static final String DEFAULT_CODE = "-1";

    public ValidatorException(String message) {
        super(message);
        this.code = DEFAULT_CODE;
    }

    public ValidatorException(Throwable cause) {
        super(cause);
        this.code = DEFAULT_CODE;
    }

    public ValidatorException(String code, String message) {
        super(message);
        this.code = code;
    }

    public ValidatorException(String code, Throwable cause) {
        super(cause);
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
