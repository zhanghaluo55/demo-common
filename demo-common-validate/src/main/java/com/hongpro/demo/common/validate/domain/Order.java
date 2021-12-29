package com.hongpro.demo.common.validate.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hongpro.demo.common.validate.annotation.FieldRepeatValidator;
import com.hongpro.demo.common.validate.base.SuperEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 *
 * </p>
 *
 * @author zhangzihong
 * @since 2021-12-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Order对象", description="Order对象")
@TableName("tb_order")
@FieldRepeatValidator(id = "order_id", field = "name", message = "订单名称重复!")
public class Order extends SuperEntity<Order> {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "主键id")
    private String orderId;

    @ApiModelProperty(value = "订单名称")
    @TableField("name")
    private String name;

    @ApiModelProperty(value = "用户id")
    private String userId;

    @ApiModelProperty(value = "状态")
    private Boolean status;


    public static final String ORDER_ID = "order_id";

    public static final String NAME = "name";

    public static final String USER_ID = "user_id";

    public static final String STATUS = "status";

}
