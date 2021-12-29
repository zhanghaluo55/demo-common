package com.hongpro.demo.common.validate.exception;

/**
 * @author zhangzihong
 * @description 对象转换异常
 * @date 2021/12/28 13:49
 */
public class TransformException extends RuntimeException {
    private static final long serialVersionUID = -5258690549408823112L;

    public TransformException() {
    }

    public TransformException(String message) {
        super(message);
    }

    public TransformException(String message, Throwable cause) {
        super(message, cause);
    }

    public TransformException(Throwable cause) {
        super(cause);
    }
}
