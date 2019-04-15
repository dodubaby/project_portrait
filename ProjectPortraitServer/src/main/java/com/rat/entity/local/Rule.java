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
    private String regular;
    private String scanFileSuffix;
    private String keyLeft;
    private String keyRight;
    private String ruleGroup;
    private String remark;
    private String creater;
    private String createTime;
    private List<RuleData> ruleDataList;
    private int ruleDataListSize;

    public Rule() {
    }

    public Rule(String scanFileSuffix, String keyLeft, String keyRight, String ruleGroup, String remark, String creater) {
        this.scanFileSuffix = scanFileSuffix;
        this.keyLeft = keyLeft;
        this.keyRight = keyRight;
        this.ruleGroup = ruleGroup;
        this.remark = remark;
        this.creater = creater;
        this.createTime = createTime;
    }

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

    public String getRegular() {
        return regular;
    }

    public void setRegular(String regular) {
        this.regular = regular;
    }

    public String getScanFileSuffix() {
        return scanFileSuffix;
    }

    public void setScanFileSuffix(String scanFileSuffix) {
        this.scanFileSuffix = scanFileSuffix;
    }

    public String getRuleGroup() {
        return ruleGroup;
    }

    public void setRuleGroup(String ruleGroup) {
        this.ruleGroup = ruleGroup;
    }
}