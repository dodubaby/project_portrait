package com.rat.common;

/**
 * Created by liangjinzhu on 17/4/10.
 * 请求消息码
 */
public class RequestCode {

    public static final int TOKEN_SUCCESS = 200;

    public static final int FILE_FIND_ALL = 1001;
    public static final int RESOURCE_FIND_ALL = 1002;
    public static final int REFERENCE_FIND_ALL = 1003;
    public static final int TARGET_DATA_FIND_ALL = 1004;


    public static final int USER_REGISTER_LOGIN = 1009;
    public static final int SEND_SECURITY_CODE = 1002;
    public static final int USER_FIND_ALL = 1003;
    public static final int USER_FIND_DETAIL = 1004;
    public static final int USER_UPDATE = 1005;

    public static final int VIDEO_CREATE = 1006;
    public static final int VIDEO_DELETE = 1007;

    public static final int FOLLOW = 1010;
    public static final int FOLLOW_FIND_BY_USER = 1011;
    public static final int FOLLOW_FIND_BY_FOLLOWED_USER = 1012;


    public static final int SYSTEM_NEW_VERSION = 1020;// 最新版本
    public static final int SYSTEM_VIDEO_NAMES = 1021;
}