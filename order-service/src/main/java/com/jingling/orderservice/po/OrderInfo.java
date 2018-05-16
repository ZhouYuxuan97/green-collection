package com.jingling.orderservice.po;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;

/**
 * @Auther: finalcola-zyy
 * @Date: 2018/3/27 21:02
 * @Description: Order PO
 */
public class OrderInfo {
    private BigInteger orderId;
    private BigInteger recyclerId;
    @NotNull
    private BigInteger userId;
    private BigInteger addressId;
    private Boolean isPaid;
    private String payType;
    private String orderStatus;
    private Timestamp createTime;
    private Timestamp updateTime;
    private BigDecimal orderAmount;
    private String description;

    public OrderInfo() {
    }

    public BigInteger getOrderId() {
        return orderId;
    }

    public OrderInfo setOrderId(BigInteger orderId) {
        this.orderId = orderId;
        return this;
    }

    public BigInteger getRecyclerId() {
        return recyclerId;
    }

    public OrderInfo setRecyclerId(BigInteger recyclerId) {
        this.recyclerId = recyclerId;
        return this;
    }

    public BigInteger getUserId() {
        return userId;
    }

    public OrderInfo setUserId(BigInteger userId) {
        this.userId = userId;
        return this;
    }

    public BigInteger getAddressId() {
        return addressId;
    }

    public OrderInfo setAddressId(BigInteger addressId) {
        this.addressId = addressId;
        return this;
    }

    public Boolean getPaid() {
        return isPaid;
    }

    public OrderInfo setPaid(Boolean paid) {
        isPaid = paid;
        return this;
    }

    public String getPayType() {
        return payType;
    }

    public OrderInfo setPayType(String payType) {
        this.payType = payType;
        return this;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public OrderInfo setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
        return this;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public OrderInfo setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
        return this;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public OrderInfo setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public BigDecimal getOrderAmount() {
        return orderAmount;
    }

    public OrderInfo setOrderAmount(BigDecimal orderAmount) {
        this.orderAmount = orderAmount;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public OrderInfo setDescription(String description) {
        this.description = description;
        return this;
    }

    @Override
    public String toString() {
        return "OrderInfo{" +
                "orderId=" + orderId +
                ", recyclerId=" + recyclerId +
                ", userId=" + userId +
                ", addressId=" + addressId +
                ", isPaid=" + isPaid +
                ", payType='" + payType + '\'' +
                ", orderStatus='" + orderStatus + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", orderAmount=" + orderAmount +
                ", description='" + description + '\'' +
                '}';
    }
}
