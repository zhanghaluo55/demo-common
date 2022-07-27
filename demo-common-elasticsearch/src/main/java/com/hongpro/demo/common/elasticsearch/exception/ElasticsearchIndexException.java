package com.hongpro.demo.common.elasticsearch.exception;

/**
 * @description:
 * @author: tracy
 * @createTime: 2022/7/26
 */
public class ElasticsearchIndexException extends Exception {
    private final String msg;

    public ElasticsearchIndexException(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
}
