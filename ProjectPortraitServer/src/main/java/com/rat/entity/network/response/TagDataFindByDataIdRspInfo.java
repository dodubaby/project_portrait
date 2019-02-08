package com.rat.entity.network.response;

import com.rat.entity.local.Tag;
import com.rat.entity.network.response.base.ResponseInfo;

import java.util.List;

/**
 * author : L.jinzhu
 * date : 2018/12/11
 * introduce : 响应实体
 */
public class TagDataFindByDataIdRspInfo extends ResponseInfo {
    private List<Tag> tagList4Owner;
    private List<Tag> tagList4Function;
    private List<Tag> tagList4Other;

    private List<String> tagValueList4Owner;// 对应tagValue列表
    private List<String> tagValueList4Function;// 对应tagValue列表
    private List<String> tagValueList4Other;// 对应tagValue列表

    public List<Tag> getTagList4Owner() {
        return tagList4Owner;
    }

    public void setTagList4Owner(List<Tag> tagList4Owner) {
        this.tagList4Owner = tagList4Owner;
    }

    public List<Tag> getTagList4Function() {
        return tagList4Function;
    }

    public void setTagList4Function(List<Tag> tagList4Function) {
        this.tagList4Function = tagList4Function;
    }

    public List<Tag> getTagList4Other() {
        return tagList4Other;
    }

    public void setTagList4Other(List<Tag> tagList4Other) {
        this.tagList4Other = tagList4Other;
    }

    public List<String> getTagValueList4Owner() {
        return tagValueList4Owner;
    }

    public void setTagValueList4Owner(List<String> tagValueList4Owner) {
        this.tagValueList4Owner = tagValueList4Owner;
    }

    public List<String> getTagValueList4Function() {
        return tagValueList4Function;
    }

    public void setTagValueList4Function(List<String> tagValueList4Function) {
        this.tagValueList4Function = tagValueList4Function;
    }

    public List<String> getTagValueList4Other() {
        return tagValueList4Other;
    }

    public void setTagValueList4Other(List<String> tagValueList4Other) {
        this.tagValueList4Other = tagValueList4Other;
    }
}