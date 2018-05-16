package com.jingling.wasteservice.po;

import java.io.Serializable;
import java.math.BigInteger;


/**
 * @Author: Hy
 * @Date: 2018/4/7 20:44
 * @Description:功能实现
 */
public class SecondGarbageCategory implements Serializable {

    private BigInteger id;
    private BigInteger firstId;
    private String secondname;
    private String description;
    private String photoPath;

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public BigInteger getFirstId() {
        return firstId;
    }

    public void setFirstId(BigInteger firstId) {
        this.firstId = firstId;
    }

    public String getSecondname() {
        return secondname;
    }

    public void setSecondname(String secondname) {
        this.secondname = secondname;
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
