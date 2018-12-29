package com.rat.entity.network.request;


import com.rat.entity.network.request.base.ActionInfoWithPageData;

/**
 * author : L.jinzhu
 * date : 2015/8/12
 * introduce : 请求实体
 */
public class FileFindBySuffixOrderByLineCountActionInfo extends ActionInfoWithPageData {
    private String suffix;

    public FileFindBySuffixOrderByLineCountActionInfo(int actionId, int pageNumber, int dataGetType, String suffix) {
        super(actionId, pageNumber, dataGetType);
        this.suffix = suffix;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }
}