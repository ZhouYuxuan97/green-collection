package com.jingling.orderservice.service;

import com.jingling.orderservice.po.OrderItem;

import java.math.BigInteger;
import java.util.List;

/**
 * @Auther: finalcola-zyy
 * @Date: 2018/4/3 09:52
 * @Description: 订单详情服务
 */
public interface OrderItemService {

    /**
     * 添加订单子项
     * @param orderItem
     */
    void addOrderItem(OrderItem orderItem);

    /**
     * 获取订单中的所有子项
     * @param orderId
     * @return
     */
    List<OrderItem> listByOrderId(BigInteger orderId);

    /**
     * 修改订单详情项的信息
     * @param orderItem
     * @param changerUserId
     */
    void update(OrderItem orderItem,BigInteger changerUserId);
}
