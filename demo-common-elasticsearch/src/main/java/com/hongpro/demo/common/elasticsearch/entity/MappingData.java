package com.hongpro.demo.common.elasticsearch.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * @description:
 * @author: tracy
 * @createTime: 2022/7/22
 */
@Data
public class MappingData {

    @JsonProperty("field_name")
    private String fieldName;
    /**
     * 数据类型（包含 关键字类型）
     */
    private String datatype;
    /**
     * 间接关键字
     */
    private boolean keyword;

    /**
     * 关键字忽略字数
     */
    @JsonProperty("ignore_above")
    private int ignoreAbove;

    /**
     * 是否支持ngram，高效全文搜索提示
     */
    private boolean ngram;

    /**
     * 是否支持suggest，高效前缀搜索提示
     */
    private boolean suggest;

    /**
     * 索引分词器设置
     */
    private String analyzer;

    /**
     * 搜索内容分词器设置
     */
    @JsonProperty("search_analyzer")
    private String searchAnalyzer;

    @JsonProperty("allow_search")
    private boolean allowSearch;

    @JsonProperty("copy_to")
    private String copyTo;

    @JsonProperty("null_value")
    private String nullValue;

    @JsonProperty("nested_class")
    @SuppressWarnings("rawtypes")
    private Class nestedClass;


    /**
     * 时间格式
     */
    private List<String> dateFormat;

    /**
     * normalizer名称指定，需要配合自定义settings使用
     */
    private String normalizer;
}
