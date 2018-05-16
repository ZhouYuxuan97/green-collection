package com.jingling.orderservice.dto;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

/**
 * 记录回收员经纬度和评分信息
 *
 * @auther: finalcola-zyy
 * @date: 2018/4/4 17:05
 */
public class RecyclerInfo {

    /**
     * 回收员Id
     */
    private BigInteger recyclerId;
    /**
     * 回收员评分
     */
    private Integer score;
    /**
     * 纬度
     */
    private BigDecimal latitude;
    /**
     * 经度
     */
    private BigDecimal longitude;
    /**
     * 回收员已经接的订单数
     */
    private Integer orderCount;

    public static List<RecyclerInfo> fromJson(String json) {
        Gson gson = new Gson();
        Object result = gson.fromJson(json, new TypeToken<List<RecyclerInfo>>() {
        }.getType());
        return (List<RecyclerInfo>) result;
    }

    public RecyclerInfo() {
    }

    public RecyclerInfo(Integer score, BigDecimal latitude, BigDecimal longitude) {
        this.score = score;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public BigInteger getRecyclerId() {
        return recyclerId;
    }

    public void setRecyclerId(BigInteger recyclerId) {
        this.recyclerId = recyclerId;
    }

    public Integer getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(Integer orderCount) {
        this.orderCount = orderCount;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "RecyclerInfo{" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
