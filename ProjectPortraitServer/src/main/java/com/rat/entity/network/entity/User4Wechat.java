package com.rat.entity.network.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * 用户实体类
 *
 * @author L.jinzhu
 * @date 2017-03-31 18:07
 */
public class User4Wechat implements Serializable {
    //  TODO by L.jinzhu  for 待解析

    // dataFromOtherPlatform   {"ret":0,"msg":"","is_lost":0,"nickname":"IT鼠部落","gender":"男","province":"北京","city":"平谷","figureurl":"http:\/\/qzapp.qlogo.cn\/qzapp\/1105974837\/17EFDCF4DE2101561D27119C0D1CE2E7\/30","figureurl_1":"http:\/\/qzapp.qlogo.cn\/qzapp\/1105974837\/17EFDCF4DE2101561D27119C0D1CE2E7\/50","figureurl_2":"http:\/\/qzapp.qlogo.cn\/qzapp\/1105974837\/17EFDCF4DE2101561D27119C0D1CE2E7\/100","figureurl_qq_1":"http:\/\/q.qlogo.cn\/qqapp\/1105974837\/17EFDCF4DE2101561D27119C0D1CE2E7\/40","figureurl_qq_2":"http:\/\/q.qlogo.cn\/qqapp\/1105974837\/17EFDCF4DE2101561D27119C0D1CE2E7\/100","is_yellow_vip":"0","vip":"0","yellow_vip_level":"0","level":"0","is_yellow_year_vip":"0"}

    @SerializedName("figureurl")
    private String headUrl; // 用户头像
    @SerializedName("nickname")
    private String nickName; // 用户昵称
    private int age = 0; // 用户年龄
    @SerializedName("gender")
    private String sex;//性别 0:未知;1男;2女
    private String bigImg; // 用户信息默认大图
    private String cityCode; // 城市编号
    @SerializedName("province")
    private String cityName; // 城市名称

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
        if ("男".equals(this.sex)) {
            this.sex = "1";
        } else if ("女".equals(this.sex)) {
            this.sex = "2";
        } else {
            this.sex = "0";
        }
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
}
