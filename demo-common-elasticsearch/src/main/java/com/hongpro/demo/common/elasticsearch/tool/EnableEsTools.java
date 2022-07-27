package com.hongpro.demo.common.elasticsearch.tool;

import org.springframework.core.annotation.AnnotationAttributes;

/**
 * @description: Es Client Tool
 * @author: tracy
 * @createTime: 2022/7/25
 */
public class EnableEsTools {
    private EnableEsTools() {

    }

    private static String[] basePackages;
    private static String[] value;
    private static String[] entityPath;
    private static boolean printRegMsg = false;

    public static void gainAnnotationInfo(AnnotationAttributes attributes ){
        basePackages = attributes.getStringArray("basePackages");
        value = attributes.getStringArray("value");
        entityPath = attributes.getStringArray("entityPath");
        printRegMsg = attributes.getBoolean("printregmsg");
    }

    public static String[] getBasePackages() {
        return basePackages;
    }

    public static String[] getValue() {
        return value;
    }

    public static String[] getEntityPath() {
        return entityPath;
    }

    public static boolean isPrintRegMsg() {
        return printRegMsg;
    }
}
