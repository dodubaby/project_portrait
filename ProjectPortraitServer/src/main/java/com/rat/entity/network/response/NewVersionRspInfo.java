package com.rat.entity.network.response;

import com.rat.entity.network.response.base.ResponseInfo;


/**
 * author : L.jinzhu
 * date : 2018/12/11
 * introduce : 响应实体
 */
public class NewVersionRspInfo extends ResponseInfo {
    private String versionCode;
    private String versionName;
    private String isForced;// 是否强制 1：是 0：否
    private String downloadUrl;// 下载地址
    private String des;// 描述

    public String getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(String versionCode) {
        this.versionCode = versionCode;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public String getIsForced() {
        return isForced;
    }

    public void setIsForced(String isForced) {
        this.isForced = isForced;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }
}