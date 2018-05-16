package com.jingling.basic.po;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @Description: 地址表对应实体类
 * @Source: JDK 1.8
 * @Author: ZhaoKunsong
 * @Date: 2018-03-29 19:34
 * @Since: version 1.0
 **/
public class Address implements Serializable{

    private Integer addressId;
    private Integer userId;
    private String username;
    private String userPhone;
    private String provinceName;
    private String cityName;
    private String districtName;
    private String zipcodet;
    private Timestamp description;
    private Timestamp createTime;
    private Timestamp updateTime;

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public String getZipcodet() {
        return zipcodet;
    }

    public void setZipcodet(String zipcodet) {
        this.zipcodet = zipcodet;
    }

    public Timestamp getDescription() {
        return description;
    }

    public void setDescription(Timestamp description) {
        this.description = description;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }
}
