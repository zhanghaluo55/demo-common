package com.hongpro.demo.common.spi.dubbospi;

import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.extension.SPI;
import org.apache.dubbo.rpc.Invocation;
import org.apache.dubbo.rpc.Invoker;
import org.apache.dubbo.rpc.RpcException;
import org.apache.dubbo.rpc.cluster.loadbalance.RandomLoadBalance;

import java.util.List;

/**
 * @Description:
 * @Author: zhangzihong
 * @CreateTime: 2021/10/27
 * @Version:
 */
@SPI(RandomLoadBalance.NAME)
public interface LoadBalance {

    <T> Invoker<T> select(List<Invoker<T>> invokers, URL url, Invocation invocation) throws RpcException;

}
