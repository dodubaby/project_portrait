package com.rat.entity.network.request.base;


/**
 * author : L.jinzhu
 * date : 2015/8/12
 * introduce : 请求实体
 */
public class SystemInfo extends AbstractRequestInfo {
    private String uidi;//OpenUDID（当前版本可以为空）
    private String imei;//设备唯一标示
    private String imsi;//sim卡串号
    private String idfa;//广告标识符
    private String device;//设备型号
    private String brand;//厂商
    private String osType;//系统类型
    private String osVer;//系统版本
    private String language;//系统语言
    private String apn; // 网络类型
    private String timezone;// 时区

    public String getUidi() {
        return uidi;
    }

    public void setUidi(String uidi) {
        this.uidi = uidi;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getImsi() {
        return imsi;
    }

    public void setImsi(String imsi) {
        this.imsi = imsi;
    }

    public String getIdfa() {
        return idfa;
    }

    public void setIdfa(String idfa) {
        this.idfa = idfa;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getOsType() {
        return osType;
    }

    public void setOsType(String osType) {
        this.osType = osType;
    }

    public String getOsVer() {
        return osVer;
    }

    public void setOsVer(String osVer) {
        this.osVer = osVer;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getApn() {
        return apn;
    }

    public void setApn(String apn) {
        this.apn = apn;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }
}