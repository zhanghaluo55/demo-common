package com.hongpro.demo.common.validate.model.result;

import com.hongpro.demo.common.validate.model.constant.GlobalReturnStatus;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author zhangzihong
 * @description 单个数据 返回結果
 * @date 2021/12/16 16:34
 */
public class ReturnSingle<T> extends BaseReturn {
    private static final long serialVersionUID = 7404193465945357324L;

    /**
     * 其他结果信息
     */
    @ApiModelProperty(value = "返回对象")
    private T data;


    public ReturnSingle() {
        super(GlobalReturnStatus.SUCCESS);
    }

    public ReturnSingle(ResultStatus resultStatus) {
        super(resultStatus);
    }

    public ReturnSingle(ResultStatus resultStatus, T data) {
        super(resultStatus);
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public ReturnSingle<T> data(T data) {
        this.data = data;
        return this;
    }
}
