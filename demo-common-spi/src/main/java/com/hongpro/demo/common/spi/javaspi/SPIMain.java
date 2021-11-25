package com.hongpro.demo.common.spi.javaspi;

import java.util.ServiceLoader;

/**
 * @Description:
 * @Author: zhangzihong
 * @CreateTime: 2021/10/27
 * @Version:
 */
public class SPIMain {
    public static void main(String[] args) {
        ServiceLoader<Command> loader = ServiceLoader.load(Command.class);
        System.out.println(loader);
        for (Command command : loader) {
            command.execute();
        }
        Object o = new Object();
        System.out.println(o.hashCode());
    }
}
