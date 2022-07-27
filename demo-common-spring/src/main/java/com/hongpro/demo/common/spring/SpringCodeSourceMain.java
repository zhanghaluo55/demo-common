package com.hongpro.demo.common.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @description:
 * @author: zhangzihong
 * @createTime: 2022/6/23
 */
public class SpringCodeSourceMain {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        Object springObject = applicationContext.getBean("springObject", Object.class);
    }

}
