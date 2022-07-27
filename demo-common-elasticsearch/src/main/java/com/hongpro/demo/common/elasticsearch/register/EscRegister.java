package com.hongpro.demo.common.elasticsearch.register;

import java.util.stream.Stream;

import com.hongpro.demo.common.elasticsearch.annotation.EnableESTools;
import com.hongpro.demo.common.elasticsearch.processor.EsIndexProcessor;
import com.hongpro.demo.common.elasticsearch.tool.GetBasePackage;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @description:
 * @author: tracy
 * @createTime: 2022/7/25
 */
@SuppressWarnings("NullableProblems")
@Configuration
public class EscRegister extends AbstractEscRegister implements BeanFactoryAware, ApplicationContextAware, ImportBeanDefinitionRegistrar,
        ResourceLoaderAware, EnvironmentAware {

    @SuppressWarnings("null")
    private ResourceLoader resourceLoader;

    @SuppressWarnings("null")
    private Environment environment;

    private ApplicationContext applicationContext;
    private BeanFactory beanFactory;

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }


    /**
     * 模版方法模式
     */
    @Override
    public void registerBeanDefinitions(AnnotationMetadata annotationMetadata, BeanDefinitionRegistry registry) {
        //扫描entity
        new EsIndexProcessor().scan(annotationMetadata, beanFactory, applicationContext);
        //扫描接口
        super.registerBeanDefinitions(beanFactory, environment, resourceLoader, annotationMetadata, registry);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    @Override
    public Stream<String> getBasePackage(AnnotationMetadata annotationMetadata) {
        GetBasePackage getBasePackage = new GetBasePackage(EnableESTools.class);
        return getBasePackage.getBasePackage(annotationMetadata);
    }
}
