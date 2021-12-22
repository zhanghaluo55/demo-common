package com.hongpro.demo.common.validate.model.result;

import com.hongpro.demo.common.validate.model.constant.GlobalReturnStatus;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhangzihong
 * @description 返回分页数据
 * @date 2021/12/16 16:24
 */
public class ReturnPage<T extends Serializable> extends BaseReturn {
    private static final long serialVersionUID = -3476700676225975814L;

    private static final String KEY_ROWS = "rows";
    private static final String KEY_PAGE = "page";
    private static final String KEY_TOTAL = "total";

    private Map<String, Object> data = new HashMap<>();

    public ReturnPage() {
        super(GlobalReturnStatus.SUCCESS);
    }

    /*public ReturnPage(IPage<T> page) {
        super(GlobalReturnStatus.SUCCESS);
        if (page != null) {
            data.put(KEY_ROWS, page.getRecords());
            data.put(KEY_PAGE, page.getCurrent());
            data.put(KEY_TOTAL, page.getTotal());
        } else {
            data.put(KEY_ROWS, Collections.emptyList());
            data.put(KEY_PAGE, 1);
            data.put(KEY_TOTAL, 0);
        }
    }

    public Map<String, Object> getData() {
        return data;
    }

    public ReturnPage<T> data(IPage<T> page) {
        if (page != null) {
            data.put(KEY_ROWS, page.getRecords());
            data.put(KEY_PAGE, page.getCurrent());
            data.put(KEY_TOTAL, page.getTotal());
        } else {
            data.put(KEY_ROWS, Collections.emptyList());
            data.put(KEY_PAGE, 1);
            data.put(KEY_TOTAL, 0);
        }
        return this;
    }*/
}
