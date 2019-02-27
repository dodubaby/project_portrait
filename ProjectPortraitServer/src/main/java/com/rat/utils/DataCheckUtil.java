package com.rat.utils;


import java.util.Arrays;
import java.util.List;

/**
 * author : L.jinzhu
 * date : 2018/8/1
 * introduce : 数据检查
 */
public class DataCheckUtil {
    private static final List<String> ignoreDataList = Arrays.asList(
            "lianjia_android_nh_plugin/",
            "../");

    public static String clearIgnoreData(String str) {
        for (String s : ignoreDataList) {
            str = str.replace(s, "");
        }
        return str;
    }
}
