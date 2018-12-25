package com.rat.entity.network.response;

import com.rat.entity.local.TargetData;
import com.rat.entity.network.response.base.ResponseInfo;

import java.util.List;

/**
 * author : L.jinzhu
 * date : 2015/8/12
 * introduce : 响应实体
 */
public class TargetDataFindAllRspInfo extends ResponseInfo {
    private int currentPage;
    private int isEndPage; // 是否是最后一页 1：是 0：否
    private List<TargetData> targetDataList;

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getIsEndPage() {
        return isEndPage;
    }

    public void setIsEndPage(boolean isEndPage) {
        this.isEndPage = isEndPage ? 1 : 0;
    }

    public List<TargetData> getTargetDataList() {
        return targetDataList;
    }

    public void setTargetDataList(List<TargetData> targetDataList) {
        this.targetDataList = targetDataList;
    }
}