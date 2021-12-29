package com.hongpro.demo.common.validate.exception;

import com.hongpro.demo.common.validate.model.constant.GlobalReturnStatus;
import com.hongpro.demo.common.validate.model.result.ResultStatus;

/**
 * @author zhangzihong
 * @description 数据不存在异常
 * @date 2021/12/16 16:47
 */
public class DataNotExistsException extends BaseException {
    private static final long serialVersionUID = -2016959026653079415L;

    private ResultStatus resultStatus;


    public DataNotExistsException() {
        super(GlobalReturnStatus.NOT_FOUND_DATA);
    }

    public DataNotExistsException(ResultStatus resultStatus) {
        super(resultStatus);
        this.resultStatus = resultStatus;
    }

    public DataNotExistsException(String message) {
        super(message);
        this.resultStatus = new ResultStatus(GlobalReturnStatus.NOT_FOUND_DATA.getCode(), message);
    }

    public DataNotExistsException(String message, Throwable cause) {
        super(message, cause);
        this.resultStatus = new ResultStatus(GlobalReturnStatus.NOT_FOUND_DATA.getCode(), message);
    }

    public ResultStatus getResultStatus() {
        return resultStatus;
    }
}
