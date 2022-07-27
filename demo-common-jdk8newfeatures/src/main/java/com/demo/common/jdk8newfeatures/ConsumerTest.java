package com.demo.common.jdk8newfeatures;

import java.util.function.Consumer;
import java.util.stream.Stream;

/**
 * @description:
 * @author: tracy
 * @createTime: 2022/7/27
 */
public class ConsumerTest {
    public static void main(String[] args) {
        Consumer<String> consumer = new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        };
        Stream<String> stream = Stream.of("A", "B", "C", "D");
        stream.forEach(consumer);

        Consumer<String> consumer1 = s -> System.out.println(s);
        stream.forEach(consumer1);

        Consumer<String> consumer2 = System.out::println;
        stream.forEach(consumer2);
    }
}
