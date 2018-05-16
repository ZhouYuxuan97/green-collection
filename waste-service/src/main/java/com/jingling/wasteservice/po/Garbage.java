package com.jingling.wasteservice.po;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * @Author: Hy
 * @Date: 2018/4/7 20:48
 * @Description:功能实现
 */
public class Garbage implements Serializable {
    private BigInteger id;
    private BigInteger secondcategoryid;
    private BigDecimal price;
    private BigInteger createrid;
    private String photopath;
    private String description;
    private String garbagename;
    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }


    public BigInteger getSecondcategoryid() {
        return secondcategoryid;
    }

    public void setSecondcategoryid(BigInteger second_category_id) {
        this.secondcategoryid = second_category_id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigInteger getCreaterid() {
        return createrid;
    }

    public void setCreaterid(BigInteger creater_id) {
        this.createrid = creater_id;
    }

    public String getPhotopath() {
        return photopath;
    }

    public void setPhotopath(String photo_path) {
        this.photopath = photo_path;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGarbagename() {
        return garbagename;
    }

    public void setGarbagename(String garbage_name) {
        this.garbagename = garbage_name;
    }
    //    private Integer first_categoryId;
//    private String first_name;
//    private String second_name;
//    private String first_discription;
//    private String second_discription;
//    private String first_photo_path;
//    private String second_photo_path;




}

