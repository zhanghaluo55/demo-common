package com.hongpro.demo.common.validate.model.result;

import com.hongpro.demo.common.validate.model.constant.GlobalReturnStatus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhangzihong
 * @description 返回列表结果
 * @date 2021/12/16 16:13
 */
public class ReturnList<T> extends BaseReturn {
    private static final long serialVersionUID = 5357012498897848895L;

    private static final String ROWS_KEY = "rows";

    private final Map<String, Object> data = new HashMap<>();

    public ReturnList() {
        super(GlobalReturnStatus.SUCCESS);
    }

    public ReturnList(List<T> rows) {
        super(GlobalReturnStatus.SUCCESS);
        if (rows != null) {
            data.put(ROWS_KEY, rows);
        } else {
            data.put(ROWS_KEY, new ArrayList<>());
        }
    }

    public Map<String, Object> getData() {
        return data;
    }

    public ReturnList<T> data(List<T> rows) {
        if (rows != null) {
            data.put(ROWS_KEY, rows);
        } else {
            data.put(ROWS_KEY, new ArrayList<>());
        }
        return this;
    }
}
