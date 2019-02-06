package com.rat.entity.network.response;

import com.rat.entity.local.Rule;
import com.rat.entity.local.RuleData;
import com.rat.entity.network.response.base.ResponseInfo;

import java.util.List;

/**
 * author : L.jinzhu
 * date : 2018/12/11
 * introduce : 响应实体
 */
public class RuleDataFindAllRspInfo extends ResponseInfo {
    private List<Rule> ruleList;
    private List<RuleData> ruleDataList;

    public List<Rule> getRuleList() {
        return ruleList;
    }

    public void setRuleList(List<Rule> ruleList) {
        this.ruleList = ruleList;
    }

    public List<RuleData> getRuleDataList() {
        return ruleDataList;
    }

    public void setRuleDataList(List<RuleData> ruleDataList) {
        this.ruleDataList = ruleDataList;
    }
}