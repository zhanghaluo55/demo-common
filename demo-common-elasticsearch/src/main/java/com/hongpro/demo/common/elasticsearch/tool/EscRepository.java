package com.hongpro.demo.common.elasticsearch.tool;

import java.util.List;

import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Request;
import org.elasticsearch.client.Response;
import org.elasticsearch.index.query.QueryBuilder;

/**
 * @description: Es Client Repository
 * @author: tracy
 * @createTime: 2022/7/25
 */
public interface EscRepository<T, M> {

    /**
     * 通过Low Level REST Client
     * 查询 https://www.elastic.co/guide/en/elasticsearch/client/java-rest/6.6/java-rest-low-usage-requests.html
     *
     * @param request 请求
     * @return 结果
     */
    Response request(Request request) throws Exception;


    /**
     * 新增索引
     * @param t 索引对象
     * @return 结果
     */
    boolean save(T t) throws Exception;

    /**
     * 新增索引集合
     * @param list 索引对象
     * @return 结果
     */
    BulkResponse save(List<T> list) throws Exception;

    /**
     * 按照有值字段更新索引
     * @param t 索引对象
     * @return 结果
     */
    boolean update(T t) throws Exception;


    /**
     * 覆盖更新索引
     * @param t 索引对象
     * @return 结果
     */
    boolean updateCover(T t) throws Exception;


    /**
     * 删除索引
     * @param t 索引对象
     * @return 结果
     */
    boolean delete(T t) throws Exception;

    /**
     * 删除索引
     * @param id 索引对象
     * @return 结果
     */
    boolean deleteById(M id) throws Exception;

    /**
     * 根据ID查询
     * @param id ID
     * @return 结果
     */
    T getById(M id) throws Exception;

    /**
     * 【最原始】查询
     * @param searchRequest 查询对象
     * @return 结果
     * @throws Exception
     */
    SearchResponse search(SearchRequest searchRequest) throws Exception;


    /**
     * 非分页查询 目前暂时传入类类型
     *
     * @param queryBuilder 查询条 件
     * @return 结果
     * @throws Exception
     */
    List<T> search(QueryBuilder queryBuilder) throws Exception;


    /**
     * 查询数量
     *
     * @param queryBuilder
     * @return 统计值
     * @throws Exception
     */
    long count(QueryBuilder queryBuilder) throws Exception;


    /**
     * 支持分页、高亮、排序的查询
     *
     * @param queryBuilder
     * @param pageSortHighLight
     * @return
     * @throws Exception
     */
    //PageList<T> search(QueryBuilder queryBuilder, PageSortHighLight pageSortHighLight) throws Exception;

    /**
     * 非分页查询，指定最大返回条数 目前暂时传入类类型
     *
     * @param queryBuilder
     * @param limitSize    最大返回条数
     * @return
     * @throws Exception
     */
    List<T> searchMore(QueryBuilder queryBuilder, int limitSize) throws Exception;

    /**
     * 搜索建议
     *
     * @param fieldName
     * @param fieldValue
     * @return
     * @throws Exception
     */
    List<String> completionSuggest(String fieldName, String fieldValue) throws Exception;


    /**
     * 普通聚合查询 以bucket分组以aggstypes的方式metric度量
     * @param bucketName 桶名
     * @param queryBuilder 查询条件
     * @param aggsType 聚合类型
     * @return 结果
     */
    //Map aggs(String metricName, AggsType aggsType, QueryBuilder queryBuilder, String bucketName) throws Exception;

}
