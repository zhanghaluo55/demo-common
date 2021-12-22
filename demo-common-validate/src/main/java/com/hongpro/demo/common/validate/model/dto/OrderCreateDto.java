package com.hongpro.demo.common.validate.model.dto;

import com.hongpro.demo.common.validate.annotation.EnumValue;
import com.hongpro.demo.common.validate.model.constant.Status;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author zhangzihong
 * @description 请求实体
 * @date 2021/12/16 10:41
 */
@Data
@ToString
public class OrderCreateDto implements Serializable {
    private static final long serialVersionUID = 225549025561921900L;
    @ApiModelProperty(value = "订单号", dataType = "String")
    @NotBlank(message = "订单号不能为空")
    private String orderId;

    @ApiModelProperty(value = "订单名称", dataType = "String")
    @EnumValue(strValues = {"pay"}, message = "订单信息错误!")
    @NotBlank(message = "名称不能为空")
    private String name;

    @ApiModelProperty(value = "用户id", dataType = "String")
    @NotNull(message = "用户名称不能为空")
    private Long userId;

    @ApiModelProperty(value = "订单状态", dataType = "String")
    @EnumValue(enumValues = Status.class, message = "订单状态错误")
    @NotBlank(message = "状态不能为空")
    private String status;

    @ApiModelProperty(value = "订单记录")
    private RecordDto recordDto;
}
