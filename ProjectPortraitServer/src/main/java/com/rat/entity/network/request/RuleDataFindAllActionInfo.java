package com.rat.entity.network.request;


import com.rat.entity.network.request.base.ActionInfo;

/**
 * author : L.jinzhu
 * date : 2018/12/11
 * introduce : 请求实体
 */
public class RuleDataFindAllActionInfo extends ActionInfo {
    private String ruleGroup;// 规则分组

    public RuleDataFindAllActionInfo(int actionId, String ruleGroup) {
        super(actionId);
        this.ruleGroup = ruleGroup;
    }

    public String getRuleGroup() {
        return ruleGroup;
    }

    public void setRuleGroup(String ruleGroup) {
        this.ruleGroup = ruleGroup;
    }
}