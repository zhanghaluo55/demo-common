package com.hongpro.demo.common.validate.config;

import com.baomidou.mybatisplus.core.parser.ISqlParser;
import com.baomidou.mybatisplus.extension.incrementer.H2KeyGenerator;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangzihong
 * @description mybatis plus配置实体
 * @date 2021/12/28 17:34
 */
//@MapperScan("com.hongpro.demo.common.validate.mapper*")//这个注解，作用相当于下面的@Bean MapperScannerConfigurer，2者配置1份即可
@Configuration
public class MybatisPlusConfig {
    /**
     * mybatis-plus分页插件<br>
     * 文档：http://mp.baomidou.com<br>
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        /*
         * 【测试多租户】 SQL 解析处理拦截器<br>
         * 这里写死 实际情况你可以从cookie读取，因此数据看不到 这条记录（ 注意观察 SQL ）<br>
         */
        List<ISqlParser> sqlParserList = new ArrayList<>();
       /* TenantSqlParser tenantSqlParser = new TenantSqlParser();
        tenantSqlParser.setTenantHandler(new TenantHandler() {
            @Override
            public Expression getTenantId(boolean select) {
                return new LongValue(1L);
            }

            @Override
            public String getTenantIdColumn() {
                return "order_id";
            }

            @Override
            public boolean doTableFilter(String tableName) {
                *//* 这里可以判断是否过滤表
                if ("user".equals(tableName)) {
                    return true;
                }*//*
                return false;
            }
        });*/

        //sqlParserList.add(tenantSqlParser);
        //paginationInterceptor.setSqlParserList(sqlParserList);
/*        paginationInterceptor.setSqlParserFilter(new ISqlParserFilter() {
            @Override
            public boolean doFilter(MetaObject metaObject) {
                MappedStatement ms = PluginUtils.getMappedStatement(metaObject);
                // 过滤自定义查询
                if ("com.baomidou.springboot.mapper.UserMapper.selectListBySQL".equals(ms.getId())) {
                    return true;
                }
                return false;
            }
        });*/
        return paginationInterceptor;
    }

    /**
     * 相当于顶部的：
     * {@code @MapperScan("com.hongpro.demo.common.validate.mapper*")}
     * 这里可以扩展，比如使用配置文件来配置扫描Mapper的路径
     */
    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer scannerConfigure = new MapperScannerConfigurer();
        scannerConfigure.setBasePackage("com.hongpro.demo.common.validate.mapper*");
        return scannerConfigure;
    }

    @Bean
    public H2KeyGenerator getH2KeyGenerator() {
        return new H2KeyGenerator();
    }


    /**
     * 性能分析拦截器，不建议生产使用
     */
    @Bean
    public PaginationInterceptor performanceInterceptor(){
        return new PaginationInterceptor();
    }
}
