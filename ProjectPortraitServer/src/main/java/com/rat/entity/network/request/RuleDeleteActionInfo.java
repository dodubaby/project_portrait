package com.rat.entity.network.request;

import com.rat.entity.network.request.base.ActionInfo;

/**
 * author : he
 * date : 2019-03-04
 * introduce : 规则请求实体
 */
public class RuleDeleteActionInfo extends ActionInfo {
    private String ruleId;

    public RuleDeleteActionInfo(int actionId, String ruleId) {
        super(actionId);
        this.ruleId = ruleId;
    }

    public String getRuleId() {
        return ruleId;
    }

    public void setRuleId(String ruleId) {
        this.ruleId = ruleId;
    }
}
