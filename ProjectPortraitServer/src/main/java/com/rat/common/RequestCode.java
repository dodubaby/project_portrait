package com.rat.common;

/**
 * Created by liangjinzhu on 17/4/10.
 * 请求消息码
 */
public class RequestCode {
    // system
    public static final int SYSTEM_NEW_VERSION = 0001;// 最新版本
    public static final int SYSTEM_USER_LOGIN = 0002;// 登录

    // file
    public static final int FILE_FIND_ALL = 1001;
    public static final int FILE_FIND_BY_SUFFIX_ORDER_BY_LINE_COUNT = 1002;

    // resource
    public static final int RESOURCE_FIND_ALL = 2001;
    public static final int RESOURCE_FIND_STATISTICS_BY_COUNT = 2002;
    public static final int RESOURCE_FIND_BY_VALUE = 2003;

    // reference
    public static final int REFERENCE_FIND_ALL = 3001;

    // rule、ruleData
    public static final int RULE_DATA_FIND_ALL = 4001;
    public static final int RULE_INSERT = 4002;
    public static final int RULE_DELETE_BY_ID = 4003;

    // tag、tagData
    public static final int TAG_FIND_BY_TYPE = 5001;
    public static final int TAG_DATA_FIND_BY_DATA_ID = 5002;
    public static final int TAG_DATA_UPDATE_TAGS = 5003;
    public static final int TAG_FIND_ALL = 5004;
    public static final int TAG_INSERT = 5005;
    public static final int TAG_DELETE_BY_VALUE = 5006;

    // statis
    public static final int STATIS_DATABOARD_INDEX = 6001;
}