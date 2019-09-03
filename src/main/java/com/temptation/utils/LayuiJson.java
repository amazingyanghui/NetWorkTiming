package com.temptation.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2019/3/29.
 */
public class LayuiJson {
    // 接收前端发送来的分页参数
    private int page = 1;
    public void setPage(int page) {
        this.page = page;
    }
    public int getPage() {
        return page;
    }
    private int limit = 2;
    public int getLimit() {
        return limit;
    }
    public void setLimit(int limit) {
        this.limit = limit;
    }


}
