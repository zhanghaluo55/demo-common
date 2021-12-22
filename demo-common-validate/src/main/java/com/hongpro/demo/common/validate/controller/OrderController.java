package com.hongpro.demo.common.validate.controller;

import com.hongpro.demo.common.validate.exception.BusinessException;
import com.hongpro.demo.common.validate.model.dto.OrderCreateDto;
import com.hongpro.demo.common.validate.model.result.BaseResult;
import com.hongpro.demo.common.validate.model.vo.OrderCreateVo;
import com.hongpro.demo.common.validate.service.OrderService;
import com.hongpro.demo.common.validate.utils.ValidatorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author zhangzihong
 * @description 实例控制器
 * @date 2021/12/16 10:26
 */
@RestController
@Validated
public class OrderController {
    @Autowired
    private OrderService orderService;
    private static final String EXIST_PARAM = "A";

    @PostMapping("/order1")
    public BaseResult<OrderCreateVo> validationTest(@Validated OrderCreateDto orderCreateDto, BindingResult result) {
        if(result.hasErrors()){
            return  BaseResult.error(result.getAllErrors().get(0).getDefaultMessage());
        }
        return BaseResult.success(orderService.createOrder(orderCreateDto));
    }

    @PostMapping("/order2")
    public BaseResult<OrderCreateVo> validationTest2(@RequestBody @Valid OrderCreateDto orderCreateDto) {
        if (EXIST_PARAM.equals(orderCreateDto.getOrderId())) {
            throw new BusinessException("订单号已存在!");
        }
        return BaseResult.success(orderService.createOrder(orderCreateDto));
    }

    @PostMapping("/order3")
    public BaseResult<OrderCreateVo> validationTest3(@RequestBody OrderCreateDto orderCreateDto) {
        ValidatorUtils.validate(orderCreateDto);
        return BaseResult.success(orderService.createOrder(orderCreateDto));
    }
}
