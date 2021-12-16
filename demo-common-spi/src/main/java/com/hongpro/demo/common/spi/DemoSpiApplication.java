package com.hongpro.demo.common.spi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author zhangzihong
 */
@SpringBootApplication
public class DemoSpiApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoSpiApplication.class, args);
    }

    public void a() {
        b();
    }


    public void b() {

    }

    public void c() {

    }
}
