package com.hongpro.demo.common.validate.service;

import com.hongpro.demo.common.validate.model.dto.OrderCreateDto;
import com.hongpro.demo.common.validate.model.vo.OrderCreateVo;

/**
 * @author zhangzihong
 * @description 实例业务逻辑类
 * @date 2021/12/16 10:27
 */
public interface OrderService {
    /**
     * 实例方法
     * @param orderCreateDto 订单对象
     * @return 返回订单实体
     */
    OrderCreateVo createOrder(OrderCreateDto orderCreateDto);
}
