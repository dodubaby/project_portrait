package com.rat.entity.network.request;

import com.rat.entity.network.request.base.ActionInfo;

/**
 * author : he
 * date : 2019-03-04
 * introduce : 规则请求实体
 */
public class RuleDeleteActionInfo extends ActionInfo {
    private String regular;

    public RuleDeleteActionInfo(int actionId, String regular) {
        super(actionId);
        this.regular = regular;
    }

    public String getRegular() {
        return regular;
    }

    public void setRegular(String regular) {
        this.regular = regular;
    }
}
