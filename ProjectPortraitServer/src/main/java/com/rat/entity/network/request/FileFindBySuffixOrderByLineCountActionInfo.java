package com.rat.entity.network.request;


import com.rat.entity.network.request.base.ActionInfoWithPageData;

/**
 * author : L.jinzhu
 * date : 2015/8/12
 * introduce : 请求实体
 */
public class FileFindBySuffixOrderByLineCountActionInfo extends ActionInfoWithPageData {
    private String suffix;
    private int maxLineCount;

    public FileFindBySuffixOrderByLineCountActionInfo(int actionId, int pageNumber, int dataGetType, String suffix, int maxLineCount) {
        super(actionId, pageNumber, dataGetType);
        this.suffix = suffix;
        this.maxLineCount = maxLineCount;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public int getMaxLineCount() {
        return maxLineCount;
    }

    public void setMaxLineCount(int maxLineCount) {
        this.maxLineCount = maxLineCount;
    }
}