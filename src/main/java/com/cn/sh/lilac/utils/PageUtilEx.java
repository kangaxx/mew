package com.cn.sh.lilac.utils;

import java.util.Map;

/**
 * @author gu xinxin
 */
public class PageUtilEx<T> extends PageUtil {
    private T filter;
    public PageUtilEx(Map<String, Object> params) {
        super(params);
        this.filter = (T)params.get("filter");
    }

    public T getFilter() {
        return filter;
    }

    public void setFilter(T t) {
        this.filter = t;
    }
}
