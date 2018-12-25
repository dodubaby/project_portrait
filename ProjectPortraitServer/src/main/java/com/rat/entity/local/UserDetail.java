package com.rat.entity.local;

import java.util.List;

/**
 * 短视频和用户实体类
 *
 * @author L.jinzhu
 * @date 2017-03-31 17:56
 */
public class UserDetail {
    private File file;
    private ResourceData defultResourceData;// 默认视频
    private List<ResourceData> resourceDataList; // 用户视频列表
    private List<File> referenceFileList; // 用户关注列表
    private int referenceStatus; // 用户关注状态 ReferenceStatus

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public ResourceData getDefultResourceData() {
        return defultResourceData;
    }

    public void setDefultResourceData(ResourceData defultResourceData) {
        this.defultResourceData = defultResourceData;
    }

    public List<ResourceData> getResourceDataList() {
        return resourceDataList;
    }

    public void setResourceDataList(List<ResourceData> resourceDataList) {
        this.resourceDataList = resourceDataList;
    }

    public List<File> getReferenceFileList() {
        return referenceFileList;
    }

    public void setReferenceFileList(List<File> referenceFileList) {
        this.referenceFileList = referenceFileList;
    }

    public int getReferenceStatus() {
        return referenceStatus;
    }

    public void setReferenceStatus(int referenceStatus) {
        this.referenceStatus = referenceStatus;
    }
}
