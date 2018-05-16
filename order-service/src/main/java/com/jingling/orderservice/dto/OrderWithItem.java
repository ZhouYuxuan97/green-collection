package com.jingling.orderservice.dto;

import com.jingling.orderservice.po.OrderInfo;
import com.jingling.orderservice.po.OrderItem;

import java.util.List;

/**
 * @Auther: finalcola-zyy
 * @Date: 2018/4/3 09:24
 * @Description: 订单信息以及订单详情
 */
public class OrderWithItem {
    OrderInfo orderInfo;
    List<OrderItem> orderItemList;

    public OrderWithItem(OrderInfo orderInfo, List<OrderItem> orderItemList) {
        this.orderInfo = orderInfo;
        this.orderItemList = orderItemList;
    }

    public OrderWithItem() {
    }

    public OrderInfo getOrderInfo() {
        return orderInfo;
    }

    public void setOrderInfo(OrderInfo orderInfo) {
        this.orderInfo = orderInfo;
    }

    public List<OrderItem> getOrderItemList() {
        return orderItemList;
    }

    public void setOrderItemList(List<OrderItem> orderItemList) {
        this.orderItemList = orderItemList;
    }
}
