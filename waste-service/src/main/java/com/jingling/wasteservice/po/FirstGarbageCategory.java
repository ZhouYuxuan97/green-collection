package com.jingling.wasteservice.po;

import java.io.Serializable;
import java.math.BigInteger;

/**
 * @Author: Hy
 * @Date: 2018/4/7 20:47
 * @Description:功能实现
 */
public class FirstGarbageCategory implements Serializable {

    private BigInteger id;
    private String firstname;
    private String description;
    private String photoPath;

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }
}
