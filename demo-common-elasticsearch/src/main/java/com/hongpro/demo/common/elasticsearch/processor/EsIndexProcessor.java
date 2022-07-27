package com.hongpro.demo.common.elasticsearch.processor;

import com.hongpro.demo.common.elasticsearch.annotation.EnableESTools;
import com.hongpro.demo.common.elasticsearch.tool.EsEntityScanner;
import com.hongpro.demo.common.elasticsearch.tool.GetBasePackage;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.ApplicationContext;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @description: spring初始化完成后通过读取启动类EnableESTools注解上entity的路径（或者不配置，取启动类所在包），
 * 得到路径后委托ESEntityScanner扫描相关路径ESCRegistrar进行调用
 * @author: tracy
 * @createTime: 2022/7/25
 */
public class EsIndexProcessor {

    /**
     * 扫描ESMetaData注解的类entity path或根路径的entity托管给spring
     *
     * @param beanFactory bean工厂
     * @throws BeansException 异常
     */
    public void scan(AnnotationMetadata annotationMetadata, BeanFactory beanFactory, ApplicationContext applicationContext) {
        GetBasePackage getBasePackage = new GetBasePackage(EnableESTools.class);
        EsEntityScanner scanner = new EsEntityScanner((BeanDefinitionRegistry) beanFactory);
        scanner.setResourceLoader(applicationContext);
        scanner.scan(getBasePackage.getEntityPackage(annotationMetadata).toArray(String[]::new));
    }
}
