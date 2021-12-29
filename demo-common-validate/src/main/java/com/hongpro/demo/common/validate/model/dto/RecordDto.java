package com.hongpro.demo.common.validate.model.dto;

import com.hongpro.demo.common.validate.annotation.EnumValue;
import com.hongpro.demo.common.validate.model.constant.Status;
import io.swagger.annotations.ApiModelProperty;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author zhangzihong
 * @description
 * @date 2021/12/17 16:03
 */
@ToString
public class RecordDto implements Serializable {
    private static final long serialVersionUID = -5642219551089219080L;

    @ApiModelProperty(value = "时间戳", dataType = "String")
    @NotNull(message = "时间戳不能为空")
    private Long timestamp;

    @ApiModelProperty(value = "描述", dataType = "String")
    @EnumValue(enumValues = Status.class, message = "描述错误")
    @NotBlank(message = "描述不能为空")
    private String desc;
}
