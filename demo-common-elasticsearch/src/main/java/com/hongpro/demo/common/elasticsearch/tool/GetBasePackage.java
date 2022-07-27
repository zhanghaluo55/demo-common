package com.hongpro.demo.common.elasticsearch.tool;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.ClassUtils;

/**
 * @description: 获取base package列表
 * @author: tracy
 * @createTime: 2022/7/25
 */
public class GetBasePackage {

    @SuppressWarnings("rawtypes")
    private static ConcurrentHashMap<Class, List<String>> entityPathsMap = null;

    static {
        entityPathsMap = new ConcurrentHashMap<>();
    }

    private final Class<? extends Annotation> annotation;

    public GetBasePackage(Class<? extends Annotation> annotation) {
        this.annotation = annotation;
    }

    /**
     * 获取repository的路径，如果获取不到就取main启动路径
     * @param annotationMetadata 注解枚举类
     * @return 返回值
     */
    public Stream<String> getBasePackage(AnnotationMetadata annotationMetadata) {
        Map<String, Object> annotationAttributes = annotationMetadata.getAnnotationAttributes(annotation.getName());
        if (annotationAttributes != null) {
            AnnotationAttributes attributes = new AnnotationAttributes(annotationAttributes);
            EnableEsTools.gainAnnotationInfo(attributes);
            //annotation中的注解
            String[] value = EnableEsTools.getValue();
            //annotation中的注解
            String[] basePackages = EnableEsTools.getBasePackages();
            //annotation中的注解
            String[] entityPaths = EnableEsTools.getEntityPath();
            //没配注解参数
            if (value.length == 0 && basePackages.length == 0) {
                String className = annotationMetadata.getClassName();
                return Stream.of(ClassUtils.getPackageName(className));
            }
            //配了注解
            return Stream.of(Arrays.asList(value), Arrays.asList(basePackages), Arrays.asList(entityPaths)).flatMap(list -> list.stream());
        }
        return null;
    }

    /**
     * 获取实体类的路径，如果获取不到就取main启动路径
     * @param annotationMetadata 注解枚举类
     * @return 返回值
     */
    public Stream<String> getEntityPackage(AnnotationMetadata annotationMetadata) {
        //缓存entity paths
        if (entityPathsMap.containsKey(annotation)) {
            return Stream.of(entityPathsMap.get(annotation)).flatMap(Collection::stream);
        }
        Map<String, Object> annotationAttributes = annotationMetadata.getAnnotationAttributes(annotation.getName());
        if (annotationAttributes != null) {
            AnnotationAttributes attributes = new AnnotationAttributes(annotationAttributes);
            //annotation中的注解
            String[] entityPaths = attributes.getStringArray("entityPath");
            //没配注解参数
            if (entityPaths.length == 0) {
                String className = annotationMetadata.getClassName();
                entityPathsMap.put(annotation, Collections.singletonList(ClassUtils.getPackageName(className)));
                return Stream.of(ClassUtils.getPackageName(className));
            }
            entityPathsMap.put(annotation, Arrays.asList(entityPaths));
            //配了注解
            return Stream.of(Arrays.asList(entityPaths)).flatMap(Collection::stream);
        }
        return null;
    }

    @SuppressWarnings("rawtypes")
    public static Map<Class, List<String>> getEntityPathsMap() {
        return entityPathsMap;
    }
}
