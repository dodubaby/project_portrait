package com.rat.entity.network.response;

import com.rat.entity.local.TargetData;
import com.rat.entity.network.response.base.ResponseInfo;
import com.rat.entity.network.response.base.ResponseInfoWithPageData;

import java.util.List;

/**
 * author : L.jinzhu
 * date : 2018/12/11
 * introduce : 响应实体
 */
public class TargetDataFindAllRspInfo extends ResponseInfoWithPageData {
    private List<TargetData> targetDataList;

    public List<TargetData> getTargetDataList() {
        return targetDataList;
    }

    public void setTargetDataList(List<TargetData> targetDataList) {
        this.targetDataList = targetDataList;
    }
}