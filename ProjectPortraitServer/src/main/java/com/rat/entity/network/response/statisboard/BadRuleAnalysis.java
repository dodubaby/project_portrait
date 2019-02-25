package com.rat.entity.network.response.statisboard;

/**
 * author : he
 * date : 2019-02-25
 * introduce : bad rule statis
 */
public class BadRuleAnalysis {

    private int hardcodeCount;
    private int errorCount;
    private int warnCount;

    public BadRuleAnalysis(int hardcodeCount, int errorCount, int warnCount) {
        this.hardcodeCount = hardcodeCount;
        this.errorCount = errorCount;
        this.warnCount = warnCount;
    }

    public int getHardcodeCount() {
        return hardcodeCount;
    }

    public void setHardcodeCount(int hardcodeCount) {
        this.hardcodeCount = hardcodeCount;
    }

    public int getErrorCount() {
        return errorCount;
    }

    public void setErrorCount(int errorCount) {
        this.errorCount = errorCount;
    }

    public int getWarnCount() {
        return warnCount;
    }

    public void setWarnCount(int warnCount) {
        this.warnCount = warnCount;
    }
}
