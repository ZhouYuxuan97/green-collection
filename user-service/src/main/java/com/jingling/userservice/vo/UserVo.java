package com.jingling.userservice.vo;

import java.io.Serializable;

/**
 * @Description: 封装用户、角色信息
 * @Source: JDK 1.8
 * @Author: ZhaoKunsong
 * @Date: 2018-03-31 10:27
 * @Since: version 1.0
 **/
public class UserVo implements Serializable {

    private String username;
    private String nickname;
    private String email;
    private String phone;
    private String address;
    private String isLocked;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIsLocked() {
        return isLocked;
    }

    public void setIsLocked(String isLocked) {
        this.isLocked = isLocked;
    }

}
