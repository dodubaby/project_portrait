package com.rat.entity.local;

import com.google.gson.annotations.SerializedName;
import com.rat.utils.StringUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 实体类
 *
 * @author L.jinzhu
 * @date 2017-03-31 18:07
 */
public class ParentChild implements Serializable {
    private String name;
    @SerializedName("children")
    private List<ParentChild> childrenList;

    public ParentChild(String name) {
        if (StringUtil.isNullOrBlank(name)) {
            name = "DataError";
        }
        this.name = name;
    }

    public ParentChild addChild(ParentChild pc) {
        if (null == childrenList) {
            childrenList = new ArrayList<>();
        }
        // 遍历所有子元素
        for (ParentChild _pc : childrenList) {
            // 存在子元素
            if (_pc.getName().equals(pc.getName())) {
                return _pc;
            }
        }
        // 不存在子元素
        childrenList.add(pc);
        return pc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ParentChild> getChildrenList() {
        return childrenList;
    }

    public void setChildrenList(List<ParentChild> childrenList) {
        this.childrenList = childrenList;
    }
}
