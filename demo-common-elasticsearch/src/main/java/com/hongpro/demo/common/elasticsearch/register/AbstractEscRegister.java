package com.hongpro.demo.common.elasticsearch.register;

import java.io.IOException;
import java.util.stream.Stream;

import com.hongpro.demo.common.elasticsearch.tool.EscRepository;
import com.hongpro.demo.common.elasticsearch.tool.EnableEsTools;
import com.hongpro.demo.common.elasticsearch.tool.RepositoryFactorySupport;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.AssignableTypeFilter;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

/**
 * @description:
 * @author: tracy
 * @createTime: 2022/7/25
 */
public abstract class AbstractEscRegister {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String DOT_STRING = ".";

    public void registerBeanDefinitions(BeanFactory factory, Environment environment, ResourceLoader resourceLoader,
            AnnotationMetadata annotationMetadata, BeanDefinitionRegistry registry) {
        getCandidates(annotationMetadata, registry, environment, resourceLoader).forEach(beanDefinition -> {
            BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(RepositoryFactorySupport.class);
            String beanClassName = beanDefinition.getBeanClassName();
            //传入要实例化的接口
            beanDefinitionBuilder.addConstructorArgValue(beanClassName);
            //获取bean的定义
            BeanDefinition bd = beanDefinitionBuilder.getRawBeanDefinition();
            //生成bean name
            if (beanClassName != null && StringUtils.isNoneBlank(beanClassName) && beanClassName.contains(DOT_STRING)) {
                String beanName = beanClassName.substring(beanClassName.lastIndexOf(DOT_STRING) + 1);
                if (EnableEsTools.isPrintRegMsg()) {
                    logger.info(String.format("generate ESCRegistrar beanClassName: %s", beanClassName));
                    logger.info(String.format("generate ESCRegistrar beanName: %s", beanName));
                }
                BeanDefinitionRegistry beanDefinitionRegistry = (BeanDefinitionRegistry) factory;
                //注册bean  beanName是代理bean的名字
                beanDefinitionRegistry.registerBeanDefinition(beanName, bd);
            }
        });
    }

    /**
     * 扫描ESCRepository接口的类型并作为候选人返回
     * @param registry bean注册实体
     * @return 结果
     */
    public Stream<BeanDefinition> getCandidates(AnnotationMetadata annotationMetadata, BeanDefinitionRegistry registry, Environment environment,
            ResourceLoader resourceLoader) {
        EscRepositoryComponentProvider scanner = new EscRepositoryComponentProvider(registry);
        scanner.setEnvironment(environment);
        scanner.setResourceLoader(resourceLoader);
        //输入是base packages，输出是BeanDefinition的Stream
        return getBasePackage(annotationMetadata).flatMap(it -> scanner.findCandidateComponents(it).stream());
    }

    /**
     * 必须子类实现，auto config方式不同
     * @param annotationMetadata 注解元数据信息
     * @return 结果
     */
    public abstract Stream<String> getBasePackage(AnnotationMetadata annotationMetadata);

    /**
     * scanner interface ESCRepository
     */
    private static class EscRepositoryComponentProvider extends ClassPathScanningCandidateComponentProvider {

        public EscRepositoryComponentProvider(BeanDefinitionRegistry registry) {
            super(false);
            Assert.notNull(registry, "BeanDefinitionRegistry must not be null!");
            super.addIncludeFilter(new InterfaceTypeFilter(EscRepository.class));
        }

        @Override
        protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
            boolean isNonRepositoryInterface = !isGenericRepositoryInterface(beanDefinition.getBeanClassName());
            boolean isTopLevelType = !beanDefinition.getMetadata().hasEnclosingClass();
            return isNonRepositoryInterface && isTopLevelType;
        }

        private static boolean isGenericRepositoryInterface(@Nullable String interfaceName) {
            return EscRepository.class.getName().equals(interfaceName);
        }
    }

    @SuppressWarnings("NullableProblems")
    private static class InterfaceTypeFilter extends AssignableTypeFilter {

        public InterfaceTypeFilter(Class<?> targetType) {
            super(targetType);
        }

        @Override
        public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory)
                throws IOException {
            return metadataReader.getClassMetadata().isInterface() && super.match(metadataReader, metadataReaderFactory);
        }
    }
}
