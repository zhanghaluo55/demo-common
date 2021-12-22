package com.hongpro.demo.common.validate.exception;

import com.hongpro.demo.common.validate.model.constant.GlobalReturnStatus;
import com.hongpro.demo.common.validate.model.result.ResultStatus;

/**
 * @author zhangzihong
 * @description 数据重复异常
 * @date 2021/12/16 16:48
 */
public class DuplicateDataException extends BaseException {
    private static final long serialVersionUID = 1294511521065076037L;

    private final ResultStatus resultStatus;

    public DuplicateDataException(ResultStatus resultStatus) {
        super(resultStatus);
        this.resultStatus = resultStatus;
    }

    public DuplicateDataException(String message) {
        super(message);
        this.resultStatus = new ResultStatus(GlobalReturnStatus.DUPLICATE_DATA.getCode(), message);
    }

    public DuplicateDataException(String message, Throwable cause) {
        super(message, cause);
        this.resultStatus = new ResultStatus(GlobalReturnStatus.DUPLICATE_DATA.getCode(), message);
    }

    public ResultStatus getResultStatus() {
        return resultStatus;
    }
}
