package com.hongpro.demo.common.validate.exception;

import com.hongpro.demo.common.validate.model.result.ResultStatus;

/**
 * @author zhangzihong
 * @description 基础异常类
 * @date 2021/12/16 16:43
 */
public class BaseException extends RuntimeException {
    private static final long serialVersionUID = 1989571348489976195L;

    public BaseException(ResultStatus resultStatus) {
        super(resultStatus.getMsg());
    }

    public BaseException(String message) {
        super(message);
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }
}
