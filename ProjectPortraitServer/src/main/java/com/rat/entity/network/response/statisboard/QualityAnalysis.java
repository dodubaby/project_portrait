package com.rat.entity.network.response.statisboard;

/**
 * author : he
 * date : 2019-02-25
 * introduce : 质量统计
 */
public class QualityAnalysis {

    //字符串重复
    private int stringReplicationCount;
    //颜色重复
    private int colorReplicationCount;
    //文件过长
    private int fileLongCount;

    public int getStringReplicationCount() {
        return stringReplicationCount;
    }

    public void setStringReplicationCount(int stringReplicationCount) {
        this.stringReplicationCount = stringReplicationCount;
    }

    public int getColorReplicationCount() {
        return colorReplicationCount;
    }

    public void setColorReplicationCount(int colorReplicationCount) {
        this.colorReplicationCount = colorReplicationCount;
    }

    public int getFileLongCount() {
        return fileLongCount;
    }

    public void setFileLongCount(int fileLongCount) {
        this.fileLongCount = fileLongCount;
    }
}
