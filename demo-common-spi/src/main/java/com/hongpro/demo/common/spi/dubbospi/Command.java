package com.hongpro.demo.common.spi.dubbospi;

import org.apache.dubbo.common.extension.SPI;

/**
 * @Description:
 * @Author: zhangzihong
 * @CreateTime: 2021/10/27
 * @Version:
 */
@SPI
public interface Command {
    void execute();
}
