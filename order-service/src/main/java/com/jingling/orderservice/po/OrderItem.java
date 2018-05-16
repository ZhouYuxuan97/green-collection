package com.jingling.orderservice.po;

import com.jingling.orderservice.constant.OrderInfoContant;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * @Auther: finalcola-zyy
 * @Date: 2018/3/27 21:09
 * @Description: 订单详情项
 */
public class OrderItem {
    private BigInteger orderItemId;
    private BigInteger orderId;
    private Integer garbageId;
    private String garbageName;
    private String garbagePhotoPath;
    private BigDecimal garbagePrice;
    private Integer garbageNumber;
    private BigDecimal garbageWeight;
    private String description;

    public OrderItem() {
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "orderItemId=" + orderItemId +
                ", orderId=" + orderId +
                ", garbageId=" + garbageId +
                ", garbageName='" + garbageName + '\'' +
                ", garbagePhotoPath='" + garbagePhotoPath + '\'' +
                ", garbagePrice=" + garbagePrice +
                ", garbageNumber=" + garbageNumber +
                ", garbageWeight=" + garbageWeight +
                ", description='" + description + '\'' +
                '}';
    }

    public BigInteger getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(BigInteger orderItemId) {
        this.orderItemId = orderItemId;
    }

    public BigInteger getOrderId() {
        return orderId;
    }

    public void setOrderId(BigInteger orderId) {
        this.orderId = orderId;
    }

    public Integer getGarbageId() {
        return garbageId;
    }

    public void setGarbageId(Integer garbageId) {
        this.garbageId = garbageId;
    }

    public String getGarbageName() {
        return garbageName;
    }

    public void setGarbageName(String garbageName) {
        this.garbageName = garbageName;
    }

    public String getGarbagePhotoPath() {
        return garbagePhotoPath;
    }

    public void setGarbagePhotoPath(String garbagePhotoPath) {
        this.garbagePhotoPath = garbagePhotoPath;
    }

    public BigDecimal getGarbagePrice() {
        return garbagePrice;
    }

    public void setGarbagePrice(BigDecimal garbagePrice) {
        this.garbagePrice = garbagePrice;
    }

    public Integer getGarbageNumber() {
        return garbageNumber;
    }

    public void setGarbageNumber(Integer garbageNumber) {
        this.garbageNumber = garbageNumber;
    }

    public BigDecimal getGarbageWeight() {
        return garbageWeight;
    }

    public void setGarbageWeight(BigDecimal garbageWeight) {
        this.garbageWeight = garbageWeight;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
