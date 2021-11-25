package com.hongpro.demo.common.spi.dubbospi;

/**
 * @Description:
 * @Author: zhangzihong
 * @CreateTime: 2021/10/27
 * @Version:
 */
public class StartCommand implements Command {
    @Override
    public void execute() {
        System.out.println("start up!");
    }
}
