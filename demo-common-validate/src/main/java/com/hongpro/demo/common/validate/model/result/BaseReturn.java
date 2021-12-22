package com.hongpro.demo.common.validate.model.result;

import com.hongpro.demo.common.validate.model.constant.GlobalReturnStatus;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @author zhangzihong
 * @description 全局统一结果类
 * @date 2021/12/16 16:05
 */
@ApiModel(description= "返回的响应数据")
public class BaseReturn implements Serializable {
    private static final long serialVersionUID = 1706319627356175475L;
    /**
     * 响应码， 0000 成功  其他 失败
     */
    @ApiModelProperty(value = "响应编号")
    private String code;
    /**
     * 提示信息
     */
    @ApiModelProperty(value = "提示信息")
    private String msg;

    public BaseReturn() {
        this.code = GlobalReturnStatus.SUCCESS.getCode();
        this.msg = GlobalReturnStatus.SUCCESS.getMsg();
    }

    public BaseReturn(ResultStatus resultStatus) {
        this.code = resultStatus.getCode();
        this.msg = resultStatus.getMsg();
    }

    public BaseReturn(String msg) {
        this.code = GlobalReturnStatus.SUCCESS.getCode();
        this.msg = msg;
    }

    public BaseReturn setReturnStatus(String code, String msg) {
        this.code = code;
        this.msg = msg;
        return this;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
