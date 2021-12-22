package com.hongpro.demo.common.validate.service.impl;

import cn.hutool.db.sql.Order;
import com.hongpro.demo.common.validate.model.dto.OrderCreateDto;
import com.hongpro.demo.common.validate.model.vo.OrderCreateVo;
import com.hongpro.demo.common.validate.service.OrderService;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.springframework.stereotype.Service;

/**
 * @author zhangzihong
 * @description 实例业务逻辑实现
 * @date 2021/12/16 10:28
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Override
    public OrderCreateVo createOrder(OrderCreateDto orderCreateDto) {
        return new OrderCreateVo();
    }
}
