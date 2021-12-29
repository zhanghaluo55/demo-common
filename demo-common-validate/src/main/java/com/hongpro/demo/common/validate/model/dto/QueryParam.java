package com.hongpro.demo.common.validate.model.dto;

/**
 * @author zhangzihong
 * @description 通用查询条件
 * @date 2021/12/24 17:59
 */
public class QueryParam {
    /**
     * 查询字段
     */
    private String key;
    /**
     * 查询值
     */
    private String value;
    /**
     * 操作
     */
    private String operation;
    /**
     * 且/或
     */
    private String orAnd;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getOrAnd() {
        if (orAnd == null || "".equals(orAnd)) {
            return "Normal";
        }
        return orAnd;
    }

    public void setOrAnd(String orAnd) {
        this.orAnd = orAnd;
    }
}
