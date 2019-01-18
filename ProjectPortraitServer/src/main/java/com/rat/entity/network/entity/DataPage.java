package com.rat.entity.network.entity;

import java.io.Serializable;

/**
 * 数据分页
 *
 * @author L.jinzhu
 * @date 2018-03-31 18:07
 */
public class DataPage implements Serializable {
    private int dataIndexStart = 0;
    private int dataIndexEnd = 0;
    private int currentPage = 0;
    private int isEndPage; // 是否是最后一页 1：是 0：否

    public int getDataIndexStart() {
        return dataIndexStart;
    }

    public void setDataIndexStart(int dataIndexStart) {
        this.dataIndexStart = dataIndexStart;
    }

    public int getDataIndexEnd() {
        return dataIndexEnd;
    }

    public void setDataIndexEnd(int dataIndexEnd) {
        this.dataIndexEnd = dataIndexEnd;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getIsEndPage() {
        return isEndPage;
    }

    public void setIsEndPage(int isEndPage) {
        this.isEndPage = isEndPage;
    }
}
