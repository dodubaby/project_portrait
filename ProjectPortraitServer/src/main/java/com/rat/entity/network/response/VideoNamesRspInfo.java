package com.rat.entity.network.response;

import com.rat.entity.network.response.base.ResponseInfo;


/**
 * author : L.jinzhu
 * date : 2015/8/12
 * introduce : 响应实体
 */
public class VideoNamesRspInfo extends ResponseInfo {
    private String videoNames;

    public String getVideoNames() {
        return videoNames;
    }

    public void setVideoNames(String videoNames) {
        this.videoNames = videoNames;
    }
}