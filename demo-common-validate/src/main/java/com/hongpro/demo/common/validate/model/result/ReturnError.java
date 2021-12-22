package com.hongpro.demo.common.validate.model.result;

import com.hongpro.demo.common.validate.model.constant.GlobalReturnStatus;

/**
 * @author zhangzihong
 * @description 返回错误的结果信息
 * @date 2021/12/16 16:10
 */
public class ReturnError extends BaseReturn {
    private static final long serialVersionUID = 4285226912005693592L;

    public ReturnError() {
        super(GlobalReturnStatus.ERROR);
    }

    public ReturnError(String msg) {
        super(GlobalReturnStatus.ERROR.msg(msg));
    }

    public ReturnError(ResultStatus resultStatus) {
        super(resultStatus);
    }
}
