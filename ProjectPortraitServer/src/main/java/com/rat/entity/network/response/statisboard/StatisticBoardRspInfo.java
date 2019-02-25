package com.rat.entity.network.response.statisboard;

import com.rat.entity.network.response.base.ResponseInfo;

/**
 * author : he
 * date : 2019-02-18
 * introduce : 统计面板实体
 */
public class StatisticBoardRspInfo extends ResponseInfo {
    //疑似bug数
    private BadRuleAnalysis badRuleAnalysis;
    //引用关系
    private String showReferenceRelationship;
    //质量分析
    private QualityAnalysis qualityaAnalysis;
    //功能人员统计
    private FunctionAnalysis functionAnalysis;

    public BadRuleAnalysis getBadRuleAnalysis() {
        return badRuleAnalysis;
    }

    public void setBadRuleAnalysis(BadRuleAnalysis badRuleAnalysis) {
        this.badRuleAnalysis = badRuleAnalysis;
    }

    public QualityAnalysis getQualityaAnalysis() {
        return qualityaAnalysis;
    }

    public void setQualityaAnalysis(QualityAnalysis qualityaAnalysis) {
        this.qualityaAnalysis = qualityaAnalysis;
    }

    public FunctionAnalysis getFunctionAnalysis() {
        return functionAnalysis;
    }

    public void setFunctionAnalysis(FunctionAnalysis functionAnalysis) {
        this.functionAnalysis = functionAnalysis;
    }
}
