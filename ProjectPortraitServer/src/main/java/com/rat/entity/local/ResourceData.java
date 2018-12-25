package com.rat.entity.local;

/**
 * 短视频实体类
 *
 * @author shisheng.zhao
 * @date 2017-03-31 17:56
 */
public class ResourceData {
    private long resourceId; // 短视频id
    private long userId; // 用户id
    private String resourceTitle; // 短视频标题
    private String resourceTime; // 短视频创建时间
    private String isDefault; // 是否为默认展示短视频 1是;0否
    private String resourceImg; // 短视频封皮
    private String resourceUrl; // 短视频播放地址
    private String resourcePlayTime;// 视频播放次数
    private String remark; // 保留域

    public void setResourceId(long resourceId) {
        this.resourceId = resourceId;
    }

    public long getResourceId() {
        return resourceId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getUserId() {
        return userId;
    }

    public String getResourceTitle() {
        return resourceTitle;
    }

    public void setResourceTitle(String resourceTitle) {
        this.resourceTitle = resourceTitle;
    }

    public String getResourceTime() {
        return resourceTime;
    }

    public void setResourceTime(String resourceTime) {
        this.resourceTime = resourceTime;
    }

    public String getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(String isDefault) {
        this.isDefault = isDefault;
    }

    public String getResourceImg() {
        return resourceImg;
    }

    public void setResourceImg(String resourceImg) {
        this.resourceImg = resourceImg;
    }

    public String getResourceUrl() {
        return resourceUrl;
    }

    public void setResourceUrl(String resourceUrl) {
        this.resourceUrl = resourceUrl;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRemark() {
        return remark;
    }

    public String getResourcePlayTime() {
        return resourcePlayTime;
    }

    public void setResourcePlayTime(String resourcePlayTime) {
        this.resourcePlayTime = resourcePlayTime;
    }

    @Override
    public String toString() {
        return "ResourceData{" +
                "resourceId=" + resourceId +
                ", userId=" + userId +
                ", resourceTitle='" + resourceTitle + '\'' +
                ", resourceTime='" + resourceTime + '\'' +
                ", isDefault=" + isDefault +
                ", resourceImg='" + resourceImg + '\'' +
                ", resourceUrl='" + resourceUrl + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
