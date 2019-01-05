package com.rat.entity.network.response;

import com.rat.entity.local.Tag;
import com.rat.entity.network.response.base.ResponseInfoWithPageData;

import java.util.List;

/**
 * author : L.jinzhu
 * date : 2015/8/12
 * introduce : 响应实体
 */
public class TagFindByTypeRspInfo extends ResponseInfoWithPageData {
    private List<Tag> tagList;

    public List<Tag> getTagList() {
        return tagList;
    }

    public void setTagList(List<Tag> tagList) {
        this.tagList = tagList;
    }
}