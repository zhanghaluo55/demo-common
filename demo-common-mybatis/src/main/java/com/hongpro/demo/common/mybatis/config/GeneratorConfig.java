package com.hongpro.demo.common.mybatis.config;

import lombok.Data;
import org.springframework.context.annotation.PropertySource;

/**
 * @author zhangzihong
 * @description 自动导出配置
 * @date 2021/12/23 15:20
 */
@Data
@PropertySource(value ="classpath:mybatis-generator.properties")
public class GeneratorConfig {
    /**
     * 作者
     */
    private String author;

    /**
     * 项目相对地址
     */
    private String projectRelativePath;

    /**
     * 数据库url
     */
    private String databaseUrl;

    /**
     * 数据库用户名
     */
    private String databaseUsername;

    /**
     * 数据库用户密码
     */
    private String databasePassword;

    /**
     * 数据库驱动
     */
    private String databaseDrive;

    /**
     * 导出包名
     */
    private String packageName;

    /**
     * 模板路径名称
     */
    private String templePath;

    /**
     * 表前缀
     */
    private String tablePrefix = null;
}
