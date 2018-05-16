package com.jingling.orderservice.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * @auther: finalcola-zyy
 * @date: 2018/3/28 10:14
 * @description: 垃圾信息
 */
public class Garbage implements Serializable {

    private Integer id;
    private Integer second_categoryId;
    private BigDecimal price;
    private BigInteger createrId;
    private String photoPath;
    private String description;
    private String name;

    //
//    private Integer id;
//    private Integer second_categoryId;
//    private Double price;
//    private Integer createrId;
//    private String photoPath;
//    private String description;
//    private String name;
    //

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSecond_categoryId() {
        return second_categoryId;
    }

    public void setSecond_categoryId(Integer second_categoryId) {
        this.second_categoryId = second_categoryId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigInteger getCreaterId() {
        return createrId;
    }

    public void setCreaterId(BigInteger createrId) {
        this.createrId = createrId;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Garbage{" +
                "id=" + id +
                ", second_categoryId=" + second_categoryId +
                ", price=" + price +
                ", createrId=" + createrId +
                ", photoPath='" + photoPath + '\'' +
                ", description='" + description + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
