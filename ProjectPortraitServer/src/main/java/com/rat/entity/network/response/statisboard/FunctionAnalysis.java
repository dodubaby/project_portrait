package com.rat.entity.network.response.statisboard;

/**
 * author : he
 * date : 2019-02-25
 * introduce : 功能统计
 */
public class FunctionAnalysis {
    //功能参与人数
    private int functionNumbers;
    //核心功能数
    private int coreFunctionCounts;
    //通用功能数
    private int commonFunctionCounts;

    public int getFunctionNumbers() {
        return functionNumbers;
    }

    public void setFunctionNumbers(int functionNumbers) {
        this.functionNumbers = functionNumbers;
    }

    public int getCoreFunctionCounts() {
        return coreFunctionCounts;
    }

    public void setCoreFunctionCounts(int coreFunctionCounts) {
        this.coreFunctionCounts = coreFunctionCounts;
    }

    public int getCommonFunctionCounts() {
        return commonFunctionCounts;
    }

    public void setCommonFunctionCounts(int commonFunctionCounts) {
        this.commonFunctionCounts = commonFunctionCounts;
    }
}
