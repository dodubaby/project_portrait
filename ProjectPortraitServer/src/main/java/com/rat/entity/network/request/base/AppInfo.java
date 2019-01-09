package com.rat.entity.network.request.base;


/**
 * author : L.jinzhu
 * date : 2018/12/11
 * introduce : 请求实体
 */
public class AppInfo extends AbstractRequestInfo {
    private String language;
    private int versionCode;
    private String versionName;
    private String bundleId;
    private String channelId;// 渠道号

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(int versionCode) {
        this.versionCode = versionCode;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public String getBundleId() {
        return bundleId;
    }

    public void setBundleId(String bundleId) {
        this.bundleId = bundleId;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }
}