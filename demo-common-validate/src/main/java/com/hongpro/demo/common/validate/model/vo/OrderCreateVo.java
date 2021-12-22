package com.hongpro.demo.common.validate.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author zhangzihong
 * @description 请求实体
 * @date 2021/12/16 10:41
 */
@Data
@ToString
public class OrderCreateVo implements Serializable {
    private static final long serialVersionUID = 1628864984155913367L;
    @ApiModelProperty(value = "订单号", dataType = "String")
    private String orderId;
    @ApiModelProperty(value = "订单名称", dataType = "String")
    private String name;
    @ApiModelProperty(value = "用户id", dataType = "String")
    private Long userId;
}
