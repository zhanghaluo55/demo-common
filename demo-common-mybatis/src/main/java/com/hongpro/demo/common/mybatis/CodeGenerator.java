package com.hongpro.demo.common.mybatis;

import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;
import com.hongpro.demo.common.mybatis.config.GeneratorConfig;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

/**
 * @author zhangzihong
 * @description mybatis-plus代码生成器
 * @date 2021/12/23 13:57
 */
public class CodeGenerator {

    private static final GeneratorConfig CONFIG = new GeneratorConfig();

    private static final Logger logger = LoggerFactory.getLogger(CodeGenerator.class);

    static {
        InputStream inStream = CodeGenerator.class.getClassLoader().getResourceAsStream("mybatis-generator.properties");
        Properties properties = new Properties();
        try {
            properties.load(inStream);
            CONFIG.setAuthor(properties.getProperty("author"));
            CONFIG.setDatabaseDrive(properties.getProperty("database_drive"));
            CONFIG.setDatabaseUrl(properties.getProperty("database_url"));
            CONFIG.setDatabaseUsername(properties.getProperty("database_username"));
            CONFIG.setDatabasePassword(properties.getProperty("database_password"));
            CONFIG.setTemplePath(properties.getProperty("temple_path"));
            CONFIG.setPackageName(properties.getProperty("package_name"));
            CONFIG.setTablePrefix(properties.getProperty("table_prefix"));
            CONFIG.setProjectRelativePath(properties.getProperty("project_relative_path"));
        } catch (IOException e) {
            logger.error("IOException：", e);
        }

    }

    public static String scanner(String tip) {
        Scanner sc = new Scanner(System.in);
        logger.info("请输入" + tip + ":");
        if (sc.hasNext()) {
            String next = sc.next();
            if (StringUtils.isNoneBlank(next)) {
                return next;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "!");
    }

    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();
        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir") + File.separator + "demo-common-mybatis" + CONFIG.getProjectRelativePath();
        gc.setOutputDir(projectPath);
        gc.setSwagger2(true);
        gc.setAuthor(CONFIG.getAuthor());
        gc.setOpen(false);
        gc.setBaseResultMap(true);
        gc.setBaseColumnList(true);
        mpg.setGlobalConfig(gc);
        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl(CONFIG.getDatabaseUrl());
        //dsc.setSchemaName("public");
        dsc.setDriverName(CONFIG.getDatabaseDrive());
        dsc.setUsername(CONFIG.getDatabaseUsername());
        dsc.setPassword(CONFIG.getDatabasePassword());
        mpg.setDataSource(dsc);
        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setModuleName(scanner("模块名"));
        pc.setParent(CONFIG.getPackageName());
        mpg.setPackageInfo(pc);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };

        // 如果模板引擎是 freemarker
        // String templatePath = "/templates/mapper.xml.ftl";
        // 如果模板引擎是 velocity
        //String templatePath = "/templates/mapper.xml.vm";

        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(CONFIG.getTemplePath()) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return projectPath + "/src/main/resources" + pc.getModuleName()
                        + ".mapper/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        /*
        cfg.setFileCreate(new IFileCreate() {
            @Override
            public boolean isCreate(ConfigBuilder configBuilder, FileType fileType, String filePath) {
                // 判断自定义文件夹是否需要创建
                checkDir("调用默认方法创建的目录");
                return false;
            }
        });
        */
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();

        // 配置自定义输出模板
        //指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
        // templateConfig.setEntity("templates/entity2.java");
        // templateConfig.setService();
        // templateConfig.setController();

        templateConfig.setXml(null);
        mpg.setTemplate(templateConfig);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityColumnConstant(true);
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        strategy.setSuperControllerClass("com.baomidou.ant.common.BaseController");
        strategy.setInclude(scanner("表名，多个英文逗号分割").split(","));
//        strategy.setSuperEntityColumns("id");
        strategy.setControllerMappingHyphenStyle(true);
//        strategy.setTablePrefix(pc.getModuleName() + "opt_");
//        strategy.setTablePrefix(CONFIG.getTablePrefix());
        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new VelocityTemplateEngine());
        mpg.execute();
    }
}
