package com.jushu.video.api;

import java.util.Map;

/**
 * @author 大奉
 * @date 2020/1/10 11:48
 * @blame 大奉
 */
public class ParamFilter {
    private Pages page;

    private Map<String,Object> param;

    public Pages getPage() {
        return page;
    }

    public void setPage(Pages page) {
        this.page = page;
    }

    public Map<String, Object> getParam() {
        return param;
    }

    public void setParam(Map<String, Object> param) {
        this.param = param;
    }
}
