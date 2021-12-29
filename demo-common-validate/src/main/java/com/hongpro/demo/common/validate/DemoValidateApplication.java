package com.hongpro.demo.common.validate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author zhangzihong
 */
@SpringBootApplication
@ComponentScan(basePackages = {
        "com.hongpro.demo.common.validate.handler",
        "com.hongpro.demo.common.validate.config",
        "com.hongpro.demo.common.validate.controller",
        "com.hongpro.demo.common.validate.service"})
@EnableTransactionManagement
public class DemoValidateApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoValidateApplication.class, args);
    }
}
