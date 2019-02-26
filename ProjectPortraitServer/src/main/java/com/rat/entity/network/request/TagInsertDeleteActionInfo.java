package com.rat.entity.network.request;

import com.rat.entity.network.request.base.ActionInfo;

/**
 * author : he
 * date : 2019-02-26
 * introduce : 标题删除、添加的请求实体
 */
public class TagInsertDeleteActionInfo extends ActionInfo {
    private String type;
    private String value;

    public TagInsertDeleteActionInfo(int actionId, String type, String value) {
        super(actionId);
        this.type = type;
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
