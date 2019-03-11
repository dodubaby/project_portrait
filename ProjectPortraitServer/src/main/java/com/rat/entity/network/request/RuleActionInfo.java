package com.rat.entity.network.request;

import com.rat.entity.network.request.base.ActionInfo;

/**
 * author : he
 * date : 2019-03-05
 * introduce :
 */
public class RuleActionInfo extends ActionInfo {
    private String regular;
    private String scanFileSuffix;
    private String keyLeft;
    private String keyRight;
    private String ruleGroup;
    private String remark;
    private String creater;

    public RuleActionInfo(int actionId) {
        super(actionId);
    }

    public RuleActionInfo(int actionId, String regular, String scanFileSuffix, String keyLeft,
            String keyRight, String ruleGroup, String remark, String creater) {
        super(actionId);
        this.regular = regular;
        this.scanFileSuffix = scanFileSuffix;
        this.keyLeft = keyLeft;
        this.keyRight = keyRight;
        this.ruleGroup = ruleGroup;
        this.remark = remark;
        this.creater = creater;
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

    public String getRuleGroup() {
        return ruleGroup;
    }

    public void setRuleGroup(String ruleGroup) {
        this.ruleGroup = ruleGroup;
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

}
