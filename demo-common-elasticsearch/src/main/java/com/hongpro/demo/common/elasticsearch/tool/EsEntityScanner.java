package com.hongpro.demo.common.elasticsearch.tool;

import java.util.Set;

import com.hongpro.demo.common.elasticsearch.annotation.ESMetaData;

import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.type.filter.AnnotationTypeFilter;

/**
 * @description:
 * @author: tracy
 * @createTime: 2022/7/25
 */
@SuppressWarnings("NullableProblems")
public class EsEntityScanner extends ClassPathBeanDefinitionScanner {

    public EsEntityScanner(BeanDefinitionRegistry registry) {
        super(registry);
    }

    @Override
    public void registerDefaultFilters() {
        this.addIncludeFilter(new AnnotationTypeFilter(ESMetaData.class));
    }

    @Override
    public Set<BeanDefinitionHolder> doScan(String... basePackages) {
        return super.doScan(basePackages);
    }

    @Override
    public boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
        return super.isCandidateComponent(beanDefinition) && beanDefinition.getMetadata()
                .hasAnnotation(ESMetaData.class.getName());
    }
}
