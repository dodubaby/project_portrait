package com.rat.entity.network.request;

import com.rat.entity.local.Rule;
import com.rat.entity.network.request.base.ActionInfo;

/**
 * author : he
 * date : 2019-03-05
 * introduce :
 */
public class RuleInsertActionInfo extends ActionInfo {
    private Rule rule;
    private String scanFileSuffix;
    private String keyLeft;
    private String keyRight;
    private String ruleGroup;
    private String remark;
    private String creater;

    public RuleInsertActionInfo(int actionId, String scanFileSuffix, String keyLeft,
                                String keyRight, String ruleGroup, String remark, String creater) {
        super(actionId);
        this.rule = new Rule(scanFileSuffix, keyLeft, keyRight, ruleGroup, remark, creater);
    }

    public Rule getRule() {
        return rule;
    }

    public void setRule(Rule rule) {
        this.rule = rule;
    }
}
