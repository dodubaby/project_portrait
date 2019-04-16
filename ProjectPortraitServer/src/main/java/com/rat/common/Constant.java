package com.rat.common;

/**
 * Created by liangjinzhu on 17/4/10.
 * 公共数据
 */
public class Constant {
    public static final String LOG_TAG = "[-----pps-----] ";

    public static final String LOG_REQUEST = "[-----request-----] ";
    public static final String LOG_RESPONSE = "[-----response-----] ";

    // 分页数据（配置文件）
    public static int DATA_COUNT_OF_PAGE = 5; // 分页数据

    // 版本更新数据（配置文件）
    public static String versionCode = "";
    public static String versionName = "";
    public static String isForced = "";
    public static String downloadUrl = "";
    public static String des = "";

    public static String DATA_ROOT = "root";
    public static String DATA_ERROR = "DataError";
    public static String DATA_ALL = "DataAll";

    // 文件类型
    public static final String FILE_TYPE = "file";
    public static final String DIR_TYPE = "dir";

    // 数据创建类型
    public static final int DATA_CREATE_TYPE_MANUAL = 1;// 手动
    public static final int DATA_CREATE_TYPE_AUTO = 0;// 自动
}
