package com.demo.common.jdk8newfeatures;

import java.util.Random;
import java.util.function.Supplier;

/**
 * @description:
 * @author: tracy
 * @createTime: 2022/7/27
 */
public class SuppllierTest {

    public static void main(String[] args) {
        Supplier<Integer> supplier = new Supplier<Integer>() {
            @Override
            public Integer get() {
                //返回一个随机值
                return new Random().nextInt();
            }
        };
        System.out.println(supplier.get());

        //② 使用lambda表达式，
        supplier = () -> new Random().nextInt();
        System.out.println(supplier.get());

        //③ 使用方法引用
        Supplier<Double> supplier2 = Math::random;
        System.out.println(supplier2.get());
    }
}
