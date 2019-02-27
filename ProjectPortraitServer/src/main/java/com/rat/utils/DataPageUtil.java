package com.rat.utils;

import com.rat.common.Constant;
import com.rat.entity.enums.DataGetType;
import com.rat.entity.network.entity.DataPage;

/**
 * author : L.jinzhu
 * date : 2018/8/1
 * introduce : json解析工具
 */
public class DataPageUtil {
    private DataPageUtil() {
    }

    public static DataPage getPage(int pageNumber, int dataGetType) {
        int dataIndexStart;
        int dataIndexEnd;
        int currentPage = 0;
        if (0 == pageNumber) {
            currentPage = 1;
        } else {
            // 上一页
            if (DataGetType.UP.getCode() == dataGetType) {
                currentPage = pageNumber - 1;
            }
            // 下一页
            else if (DataGetType.DOWN.getCode() == dataGetType) {
                currentPage = pageNumber + 1;
            }
        }
        currentPage = currentPage <= 1 ? 1 : currentPage;
        dataIndexStart = Constant.DATA_COUNT_OF_PAGE * (currentPage - 1);
        dataIndexEnd = Constant.DATA_COUNT_OF_PAGE * currentPage + 1;//  多取一个，用于区分是否是EndPage

        DataPage dataPage = new DataPage();
        dataPage.setCurrentPage(currentPage);
        dataPage.setDataIndexStart(dataIndexStart);
        dataPage.setDataIndexEnd(dataIndexEnd);
        return dataPage;
    }

    /**
     * 是否是最后一页
     *
     * @param realDataSize
     * @return
     */
    public static boolean isEndPage(int realDataSize) {
        return Constant.DATA_COUNT_OF_PAGE >= realDataSize ? true : false;
    }
}
