package com.jingling.orderservice.service;

import com.jingling.orderservice.dto.Location;
import com.jingling.orderservice.dto.RecyclerInfo;
import com.jingling.orderservice.dto.OrderDistributionInfo;
import com.jingling.orderservice.po.OrderInfo;
import com.jingling.orderservice.po.OrderItem;
import com.jingling.orderservice.strategy.OrderFactor;

import java.math.BigInteger;
import java.util.List;

/**
 * @Auther: finalcola-zyy
 * @Date: 2018/3/28 09:56
 * @Description: 订单服务接口
 */
public interface OrderService {
    /**
     * 添加订单
     * @param orderInfo: 包含订单的信息：回收员id，用户id，用户地址id
     * @param itemList： 订单包含的子项信息：
     *                垃圾Id，垃圾名称，垃圾图片，垃圾价格，回收数量，垃圾重量，说明
     * @param location: 包含添加订单的用户的经纬度信息
     * @return
     */
    OrderInfo addOrder(OrderInfo orderInfo, List<OrderItem> itemList, Location location);


    /**
     * 添加订单
     * @param userId:用户ID
     * @param location：用户所在地址
     * @return
     */
    OrderInfo addOrder(BigInteger userId, Location location);

    /**
     * 重新分配订单
     * @param orderId
     */
    void redistribute(BigInteger orderId);

    /**
     * 完成订单并支付
     * @param orderId
     */
    void finishOrder(BigInteger orderId);

    /**
     * 根据用户信息以及所在位置，自动分配合适的回收员
     * @param orderInfo 订单信息
     * @param userLocation 用户地址信息（经纬度）
     * @return
     */
    BigInteger orderDistribute(OrderInfo orderInfo, Location userLocation);

    /**
     * 更新订单分配的计算因子
     *
     * @param orderFactor
     */
    void updateOrderFactor(OrderFactor orderFactor);

    /**
     * 查询当前的订单分配因子
     *
     * @return
     */
    OrderFactor getOrderFactor();

    /**
     * 取消订单
     * @param orderId：订单Id
     * @param userId：当前操作用户的Id
     */
    void cancelOrder(BigInteger orderId, BigInteger userId);

    /**
     * 更改回收人员Id
     *
     * @param orderId：订单Id
     * @param recyclerId：回收员id
     */
    void changeRecycler(BigInteger orderId, BigInteger recyclerId);

    /**
     * 获取用户的历史订单
     * @param userId：用户Id
     * @param page
     * @param size
     * @return
     */
    List<OrderInfo> listByUserId(BigInteger userId, Integer page, Integer size);

    /**
     * 获取回收员还已经完成的订单列表
     * @param recyclerId
     * @param page
     * @param size
     * @return
     */
    List<OrderInfo> listCompletedOrderByRecyclerId(BigInteger recyclerId, Integer page, Integer size);

    /**
     * 获取回收员还未完成的订单列表
     * @param recyclerId
     * @param page
     * @param size
     * @return
     */
    List<OrderInfo> listUncompletedOrderByRecyclerId(BigInteger recyclerId, Integer page, Integer size);

    /**
     * 修改订单状态
     *
     * @param orderId：订单Id
     * @param orderStatus：更新后的订单状态
     * @param userId:回收员Id
     */
    void changeOrderStatus(BigInteger orderId, String orderStatus, BigInteger userId);

    /**
     * 获取当前未完成订单的分配情况
     * @param page
     * @param size
     * @return
     */
    List<OrderDistributionInfo> listRecyclerId(Integer page, Integer size);

    /**
     * 手动重新分配订单的配送员
     * @param orderId
     * @param newReyclerId
     */
    void updateRecycler(BigInteger orderId, BigInteger newReyclerId);

    /**
     * 查询订单列表
     *
     * @param status:订单状态
     * @param page
     * @param size
     * @return
     */
    List<OrderInfo> listOrder(String status, Integer page, Integer size);

}
