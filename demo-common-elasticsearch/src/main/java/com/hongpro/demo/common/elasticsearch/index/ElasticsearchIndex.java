package com.hongpro.demo.common.elasticsearch.index;

/**
 * @description:
 * @author: tracy
 * @createTime: 2022/7/25
 */
@SuppressWarnings("AlibabaAbstractMethodOrInterfaceMethodMustUseJavadoc")
public interface ElasticsearchIndex<T> {
    /**
     * 创建索引
     */
    //void createIndex(Class<T> clazz) throws ElasticsearchIndexException;


    /**
     * 切换Alias写入index
     */
    //void switchAliasWriteIndex(Class<T> clazz,String writeIndex) throws ElasticsearchIndexException;


    /**
     * 创建Alias
     */
    //void createAlias(Class<T> clazz) throws ElasticsearchIndexException;

    /**
     * 创建索引
     */
    //void createIndex(Map<String,String> settings,Map<String,String[]> settingsList,String mappingJson,String indexName) throws ElasticsearchIndexException;
    /**
     * 删除索引
     */
    //void dropIndex(Class<T> clazz) throws ElasticsearchIndexException;

    /**
     * 索引是否存在
     */
    //boolean exists(Class<T> clazz) throws ElasticsearchIndexException;

    /**
     * 滚动索引
     */
    //void rollover(Class<T> clazz,boolean isAsyn) throws ElasticsearchIndexException;

    /**
     * 获得索引名称
     */
    //String getIndexName(Class<T> clazz);

    /**
     * 获得MetaData配置
     */
    //MetaData getMetaData(Class<T> clazz);
    /**
     * 获得MappingData配置
     */
    //MappingData[] getMappingData(Class<T> clazz);
}
