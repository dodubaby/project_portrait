package com.rat.entity.network.request;


import com.rat.entity.network.request.base.ActionInfo;

/**
 * author : L.jinzhu
 * date : 2018/12/11
 * introduce : 请求实体
 */
public class RuleDataFindAllActionInfo extends ActionInfo {
    private String ruleType;// 规则类型：bad、custom
    private String ruleDataStatus;// 规则对应数据状态：normal、warn、error

    public RuleDataFindAllActionInfo(int actionId, String ruleType, String ruleDataStatus) {
        super(actionId);
        this.ruleType = ruleType;
        this.ruleDataStatus = ruleDataStatus;
    }

    public String getRuleType() {
        return ruleType;
    }

    public void setRuleType(String ruleType) {
        this.ruleType = ruleType;
    }

    public String getRuleDataStatus() {
        return ruleDataStatus;
    }

    public void setRuleDataStatus(String ruleDataStatus) {
        this.ruleDataStatus = ruleDataStatus;
    }
}