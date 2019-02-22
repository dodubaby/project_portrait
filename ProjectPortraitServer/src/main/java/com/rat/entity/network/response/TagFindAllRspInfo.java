package com.rat.entity.network.response;

import com.rat.entity.local.Tag;
import com.rat.entity.network.response.base.ResponseInfo;

import java.util.List;

/**
 * author : L.jinzhu
 * date : 2018/12/11
 * introduce : 响应实体
 */
public class TagFindAllRspInfo extends ResponseInfo {
    private List<Tag> tagList4Owner;
    private List<Tag> tagList4Function;
    private List<Tag> tagList4Common;
    private List<Tag> tagList4Other;

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

    public List<Tag> getTagList4Common() {
        return tagList4Common;
    }

    public void setTagList4Common(List<Tag> tagList4Common) {
        this.tagList4Common = tagList4Common;
    }

    public List<Tag> getTagList4Other() {
        return tagList4Other;
    }

    public void setTagList4Other(List<Tag> tagList4Other) {
        this.tagList4Other = tagList4Other;
    }
}