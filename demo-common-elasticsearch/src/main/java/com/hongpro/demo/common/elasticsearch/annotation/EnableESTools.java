package com.hongpro.demo.common.elasticsearch.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.hongpro.demo.common.elasticsearch.config.ElasticSearchConfiguration;
import com.hongpro.demo.common.elasticsearch.register.EscRegister;

import org.springframework.context.annotation.Import;

/**
 * @description: 开启ES工具
 * @author: tracy
 * @createTime: 2022/7/22
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import({ElasticSearchConfiguration.class, EscRegister.class})
public @interface EnableESTools {

    /**
     * 配置repository包路径,如果不配置，则按照Main方法的路径包进行扫描
     */
    String[] basePackages() default {};

    /**
     * 配置repository包路径,如果不配置，则按照Main方法的路径包进行扫描
     */
    String[] value() default {};

    /**
     * entity路径配置,如果不配置，则按照Main方法的路径包进行扫描
     */
    String[] entityPath() default {};

    /**
     * 是否打印注册信息
     */
    boolean printRegMsg() default false;
}
