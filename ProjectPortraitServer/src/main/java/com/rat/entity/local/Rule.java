package com.rat.entity.local;

import java.io.Serializable;
import java.util.List;

/**
 * 实体类
 *
 * @author L.jinzhu
 * @date 2018-03-31 18:07
 */
public class Rule implements Serializable {
    private long id;
    private String keyLeft;
    private String keyRight;
    private String type;
    private String remark;
    private String creater;
    private String createTime;
    private List<RuleData> ruleDataList;
    private int ruleDataListSize;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getKeyLeft() {
        return keyLeft;
    }

    public void setKeyLeft(String keyLeft) {
        this.keyLeft = keyLeft;
    }

    public String getKeyRight() {
        return keyRight;
    }

    public void setKeyRight(String keyRight) {
        this.keyRight = keyRight;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public List<RuleData> getRuleDataList() {
        return ruleDataList;
    }

    public void setRuleDataList(List<RuleData> ruleDataList) {
        this.ruleDataList = ruleDataList;
    }

    public int getRuleDataListSize() {
        return ruleDataListSize;
    }

    public void setRuleDataListSize(int ruleDataListSize) {
        this.ruleDataListSize = ruleDataListSize;
    }
}