package com.jingling.userservice.vo;

import com.jingling.basic.po.Manager;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Description: 管理员视图
 * @Source: JDK 1.8
 * @Author: ZhaoKunsong
 * @Date: 2018-04-04 18:12
 * @Since: version 1.0
 **/
public class ManagerVo extends Manager implements Serializable {

    private Integer roleId;
    private BigDecimal longitude = BigDecimal.valueOf(114.32957768400001);//湖北大学的经纬度
    private BigDecimal latitude = BigDecimal.valueOf(30.58141017095);


    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }
}
