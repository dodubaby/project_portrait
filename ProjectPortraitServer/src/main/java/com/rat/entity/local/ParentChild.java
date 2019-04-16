package com.rat.entity.local;

import com.google.gson.annotations.SerializedName;
import com.rat.common.Constant;
import com.rat.utils.StringUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 实体类
 *
 * @author L.jinzhu
 * @date 2018-03-31 18:07
 */
public class ParentChild implements Serializable {
    private String dataId;
    private String dataName;
    private String dataType;
    @SerializedName("children")
    private List<ParentChild> childrenList;

    public ParentChild(String dataName, String dataType) {
        if (StringUtil.isNullOrBlank(dataName)) {
            dataName = Constant.DATA_ERROR;
        }
        if (StringUtil.isNullOrBlank(dataType)) {
            dataType = Constant.DATA_ERROR;
        }
        this.dataName = dataName;
        this.dataType = dataType;
    }

    public ParentChild addChild(ParentChild pc) {
        if (null == childrenList) {
            childrenList = new ArrayList<>();
        }
        // 遍历所有子元素
        for (ParentChild _pc : childrenList) {
            // 存在子元素，直接返回
            if (_pc.getDataName().equals(pc.getDataName())) {
                // 新child有id，旧child没有id，则重新赋id值给旧child
                if (StringUtil.isNullOrBlank(_pc.getDataId()) && StringUtil.isNotBlank(pc.getDataId())) {
                    _pc.setDataId(pc.getDataId());
                }
                return _pc;
            }
        }
        // 不存在子元素，添加后返回
        childrenList.add(pc);
        return pc;
    }

    public String getDataId() {
        return dataId;
    }

    public void setDataId(String dataId) {
        this.dataId = dataId;
    }

    public String getDataName() {
        return dataName;
    }

    public void setDataName(String dataName) {
        this.dataName = dataName;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }
}
