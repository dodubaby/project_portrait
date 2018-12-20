package com.rat.entity.network.request.base;


/**
 * author : L.jinzhu
 * date : 2015/8/12
 * introduce : 请求实体
 */
public class RequestInfo extends AbstractRequestInfo {
    private SystemInfo systemInfo;
    private AppInfo appInfo;
    private ActionInfo actionInfo;

    public SystemInfo getSystemInfo() {
        return systemInfo;
    }

    public void setSystemInfo(SystemInfo systemInfo) {
        this.systemInfo = systemInfo;
    }

    public AppInfo getAppInfo() {
        return appInfo;
    }

    public void setAppInfo(AppInfo appInfo) {
        this.appInfo = appInfo;
    }

    public ActionInfo getActionInfo() {
        return actionInfo;
    }

    public void setActionInfo(ActionInfo actionInfo) {
        this.actionInfo = actionInfo;
    }

    @Override
    public String toString() {
        return "RequestInfo{" +
                "actionInfo=" + actionInfo.toString() +
                ", systemInfo=" + systemInfo.toString() +
                ", appInfo=" + appInfo.toString() +
                '}';
    }
}