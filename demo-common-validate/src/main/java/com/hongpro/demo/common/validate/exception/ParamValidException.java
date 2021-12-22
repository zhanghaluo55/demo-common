package com.hongpro.demo.common.validate.exception;

import com.hongpro.demo.common.validate.model.constant.GlobalReturnStatus;
import com.hongpro.demo.common.validate.model.result.ResultStatus;

/**
 * @author zhangzihong
 * @description 自定义校验异常
 * @date 2021/12/16 16:51
 */
public class ParamValidException extends BaseException {
    private static final long serialVersionUID = 384784335383331845L;

    private final ResultStatus resultStatus;

    public ParamValidException(ResultStatus resultStatus) {
        super(resultStatus);
        this.resultStatus = resultStatus;
    }

    public ParamValidException(String message) {
        super(message);
        this.resultStatus = new ResultStatus(GlobalReturnStatus.PARAM_VALID_ERROR.getCode(),message);
    }

    public ParamValidException(String message, Throwable cause) {
        super(message, cause);
        this.resultStatus = new ResultStatus(GlobalReturnStatus.PARAM_VALID_ERROR.getCode(),message);
    }

    public ResultStatus getResultStatus() {
        return resultStatus;
    }
}
