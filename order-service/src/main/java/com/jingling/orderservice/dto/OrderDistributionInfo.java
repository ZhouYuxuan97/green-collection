package com.jingling.orderservice.dto;

import java.math.BigInteger;

/**
 * @auther: finalcola-zyy
 * @date: 2018/4/3 18:43
 * @description: 订单的分配情况: 回收员Id以及回收员当前收到且未完成的订单数量
 */
public class OrderDistributionInfo {

    private BigInteger recyclerId;
    private Integer orderCount;

    public OrderDistributionInfo(BigInteger recyclerId, Integer orderCount) {
        this.recyclerId = recyclerId;
        this.orderCount = orderCount;
    }

    public OrderDistributionInfo() {
    }

    @Override
    public String toString() {
        return "OrderDistributionInfo{" +
                "recyclerId=" + recyclerId +
                ", orderCount=" + orderCount +
                '}';
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
}
