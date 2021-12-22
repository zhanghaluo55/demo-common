package com.hongpro.demo.common.validate.exception;

import com.hongpro.demo.common.validate.model.constant.GlobalReturnStatus;
import com.hongpro.demo.common.validate.model.result.ResultStatus;

/**
 * @author zhangzihong
 * @description 业务异常类
 * @date 2021/12/16 16:45
 */
public class BusinessException extends BaseException {
    private static final long serialVersionUID = -4522446518205720605L;

    private final ResultStatus resultStatus;

    public BusinessException(ResultStatus resultStatus) {
        super(resultStatus);
        this.resultStatus = resultStatus;
    }

    public BusinessException(String message) {
        super(message);
        this.resultStatus = new ResultStatus(GlobalReturnStatus.BUSINESS_VALID_ERROR.getCode(), message);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
        this.resultStatus = new ResultStatus(GlobalReturnStatus.BUSINESS_VALID_ERROR.getCode(), message);
    }

    public ResultStatus getResultStatus() {
        return resultStatus;
    }
}
