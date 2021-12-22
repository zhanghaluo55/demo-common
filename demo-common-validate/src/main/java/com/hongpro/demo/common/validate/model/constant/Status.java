package com.hongpro.demo.common.validate.model.constant;

/**
 * @author zhangzihong
 * @description 订单状态枚举类
 * @date 2021/12/17 14:11
 */
public enum Status {
    /**
     * 订单状态
     */
    PROCESSING(1, "处理中"),
    SUCCESS(2, "订单已完成");
    Integer value;
    String desc;

    Status(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public Integer getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }
}
