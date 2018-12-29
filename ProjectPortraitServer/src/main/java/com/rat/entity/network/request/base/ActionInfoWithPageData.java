package com.rat.entity.network.request.base;


/**
 * author : L.jinzhu
 * date : 2015/8/12
 * introduce : 请求实体
 */
public class ActionInfoWithPageData extends ActionInfo {
    private int pageNumber;
    private int dataGetType;

    public ActionInfoWithPageData(int actionId, int pageNumber, int dataGetType) {
        super(actionId);
        this.pageNumber = pageNumber;
        this.dataGetType = dataGetType;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getDataGetType() {
        return dataGetType;
    }

    public void setDataGetType(int dataGetType) {
        this.dataGetType = dataGetType;
    }
}