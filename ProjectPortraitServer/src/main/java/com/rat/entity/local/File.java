package com.rat.entity.local;

import java.io.Serializable;

/**
 * 实体类
 *
 * @author L.jinzhu
 * @date 2017-03-31 18:07
 */
public class File implements Serializable {
    private long id;
    private String type;
    private String fullName;
    private String path;
    private String name;
    private String suffix;
    private String classFullName;
    private String lineCount;
    private String size;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public String getClassFullName() {
        return classFullName;
    }

    public void setClassFullName(String classFullName) {
        this.classFullName = classFullName;
    }

    public String getLineCount() {
        return lineCount;
    }

    public void setLineCount(String lineCount) {
        this.lineCount = lineCount;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    // 用户基本信息
    private long userId; // 用户id,唯一标示
    private String account;// 用户名
    private int accountType = 0; // 用户类型 1:微信;2:QQ;3:手机号
    private String headUrl; // 用户头像
    private String nickName; // 用户昵称
    private int age = 0; // 用户年龄
    private String sex;//性别 0:未知;1男;2女
    private String bigImg; // 用户信息默认大图
    private String cityCode; // 城市编号
    private String cityName; // 城市名称
    private String workCode; // 职业编号
    private String workName; // 职业名称
    private String educationCode; // 学历编号
    private String educationName; // 学历名称
    private String houseCode; // 住房状况编号
    private String houseName; // 住房状况名称
    private String marriageCode; // 婚姻状况编号
    private String marriageName; // 婚姻状况名称
    private String introduce; // 一句话介绍
    private String remark; // 保留域

    // 其他参数
    private String token4RongCloud;// 融云token

    // 显示额外信息
    private int resourceCount; // 视频数
    private int referenceCount; // 关注数
    private int referenceedCount; // 被关注数

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public int getAccountType() {
        return accountType;
    }

    public void setAccountType(int accountType) {
        this.accountType = accountType;
    }

    public String getHeadUrl() {
        return headUrl;
    }

    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBigImg() {
        return bigImg;
    }

    public void setBigImg(String bigImg) {
        this.bigImg = bigImg;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getWorkCode() {
        return workCode;
    }

    public void setWorkCode(String workCode) {
        this.workCode = workCode;
    }

    public String getWorkName() {
        return workName;
    }

    public void setWorkName(String workName) {
        this.workName = workName;
    }

    public String getEducationCode() {
        return educationCode;
    }

    public void setEducationCode(String educationCode) {
        this.educationCode = educationCode;
    }

    public String getEducationName() {
        return educationName;
    }

    public void setEducationName(String educationName) {
        this.educationName = educationName;
    }

    public String getHouseCode() {
        return houseCode;
    }

    public void setHouseCode(String houseCode) {
        this.houseCode = houseCode;
    }

    public String getHouseName() {
        return houseName;
    }

    public void setHouseName(String houseName) {
        this.houseName = houseName;
    }

    public String getMarriageCode() {
        return marriageCode;
    }

    public void setMarriageCode(String marriageCode) {
        this.marriageCode = marriageCode;
    }

    public String getMarriageName() {
        return marriageName;
    }

    public void setMarriageName(String marriageName) {
        this.marriageName = marriageName;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getToken4RongCloud() {
        return token4RongCloud;
    }

    public void setToken4RongCloud(String token4RongCloud) {
        this.token4RongCloud = token4RongCloud;
    }

    public int getResourceCount() {
        return resourceCount;
    }

    public void setResourceCount(int resourceCount) {
        this.resourceCount = resourceCount;
    }

    public int getReferenceCount() {
        return referenceCount;
    }

    public void setReferenceCount(int referenceCount) {
        this.referenceCount = referenceCount;
    }

    public int getReferenceedCount() {
        return referenceedCount;
    }

    public void setReferenceedCount(int referenceedCount) {
        this.referenceedCount = referenceedCount;
    }

    public static final int ACCOUNT_TYPE_WECHAT = 1;// 微信
    public static final int ACCOUNT_TYPE_QQ = 2;// QQ
    public static final int ACCOUNT_TYPE_PHONE = 3;// 手机号
}
