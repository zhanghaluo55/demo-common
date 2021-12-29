package com.hongpro.demo.common.validate.service.impl;

import com.hongpro.demo.common.validate.base.BaseService;
import com.hongpro.demo.common.validate.domain.Order;
import com.hongpro.demo.common.validate.model.dto.OrderCreateDto;
import com.hongpro.demo.common.validate.model.vo.OrderCreateVo;
import com.hongpro.demo.common.validate.service.OrderService;
import org.springframework.stereotype.Service;

/**
 * @author zhangzihong
 * @description 实例业务逻辑实现
 * @date 2021/12/16 10:28
 */
@Service
public class OrderServiceImpl extends BaseService<Order> implements OrderService {
    @Override
    public OrderCreateVo createOrder(OrderCreateDto orderCreateDto) {
        return new OrderCreateVo();
    }
}
