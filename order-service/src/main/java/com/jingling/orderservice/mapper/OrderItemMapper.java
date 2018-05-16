package com.jingling.orderservice.mapper;

import com.jingling.orderservice.po.OrderItem;
import org.apache.ibatis.annotations.Mapper;

import java.math.BigInteger;
import java.util.List;

/**
 * @Auther: finalcola-zyy
 * @Date: 2018/4/2 19:22
 * @Description: 订单详情项的mapper
 */
@Mapper
public interface OrderItemMapper {

    /**
     * 添加订单详情项
     * @param orderItem
     */
    void save(List<OrderItem> orderItem);

    /**
     * 获取订单详情项的详细信息
     * @param orderItemId
     * @return
     */
    OrderItem getById(BigInteger orderItemId);

    /**
     * 根据订单对应的所有订单详情项
     * @param orderId
     */
    void deleteByOrderId(BigInteger orderId);

    /**
     * 更新信息，需要指定id
     * @param orderItem
     */
    void update(OrderItem orderItem);

    /**
     * 列出订单的所有子项
     * @param orderId
     * @return
     */
    List<OrderItem> listOrderItemByOrderId(BigInteger orderId);

}
