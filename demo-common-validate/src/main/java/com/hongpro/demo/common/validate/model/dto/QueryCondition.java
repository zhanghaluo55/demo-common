package com.hongpro.demo.common.validate.model.dto;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import java.util.List;

/**
 * @author zhangzihong
 * @description 条件查询类
 * @date 2021/12/24 18:04
 */
public class QueryCondition<T> {
    public QueryWrapper<T> queryCondition(List<QueryParam> param) {
        QueryWrapper<T> queryWrapper = new QueryWrapper<>();
        if (param == null) {
            return queryWrapper;
        }
        for (QueryParam q : param) {
            String column = q.getKey();
            String val = q.getValue();
            String operation = q.getOperation();

            String orAnd = q.getOrAnd();
            if ("eq".equals(operation)) {
                if ("and".equals(orAnd)) {
                    // 嵌套and
                    queryWrapper.and(wrapper -> wrapper.eq(column, val));
                } else if ("or".equals(orAnd)) {
                    queryWrapper.or(wrapper -> wrapper.eq(column, val));
                } else {
                    queryWrapper.eq(column, val);
                }
            }
        }
        return queryWrapper;
    }
}
