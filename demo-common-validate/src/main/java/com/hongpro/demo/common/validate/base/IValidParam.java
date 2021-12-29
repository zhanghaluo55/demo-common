package com.hongpro.demo.common.validate.base;

/**
 * @author zhangzihong
 * @description 参数校验接口
 * @date 2021/12/29 15:27
 */
public interface IValidParam {
    /**
     * 参数校验
     *
     * @return 错误提示信息
     */
    StringBuilder valid();
}
