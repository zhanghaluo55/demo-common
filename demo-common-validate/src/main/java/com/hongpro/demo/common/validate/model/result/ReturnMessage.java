package com.hongpro.demo.common.validate.model.result;

import com.hongpro.demo.common.validate.model.constant.GlobalReturnStatus;

/**
 * @author zhangzihong
 * @description 返回结果
 * @date 2021/12/16 16:22
 */
public class ReturnMessage extends BaseReturn {
    private static final long serialVersionUID = -9199724055372356114L;

    public ReturnMessage() {
        super(GlobalReturnStatus.SUCCESS);
    }

    public ReturnMessage(String msg) {
        super(msg);
    }
}
