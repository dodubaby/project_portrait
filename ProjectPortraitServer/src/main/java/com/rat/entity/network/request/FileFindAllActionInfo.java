package com.rat.entity.network.request;


import com.rat.entity.network.request.base.ActionInfo;

/**
 * author : L.jinzhu
 * date : 2018/12/11
 * introduce : 请求实体
 */
public class FileFindAllActionInfo extends ActionInfo {
    private String suffix;
    private String rootKey;// 起始根节点
    private String tags;

    public FileFindAllActionInfo(int actionId, String suffix, String rootKey, String tags) {
        super(actionId);
        this.suffix = suffix;
        this.rootKey = rootKey;
        this.tags = tags;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getRootKey() {
        return rootKey;
    }

    public void setRootKey(String rootKey) {
        this.rootKey = rootKey;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }
}