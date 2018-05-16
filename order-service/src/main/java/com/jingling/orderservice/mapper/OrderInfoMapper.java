package com.jingling.orderservice.mapper;

import com.jingling.orderservice.dto.OrderDistributionInfo;
import com.jingling.orderservice.po.OrderInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.List;

/**
 * @auther: finalcola-zyy
 * @date: 2018/3/27 21:14
 * @description: 订单信息的Dao
 */
@Mapper
public interface OrderInfoMapper {
    void save(OrderInfo orderInfo);

    void deleteById(BigInteger orderId);

    OrderInfo getById(BigInteger orderInfoId);

    List<OrderInfo> listOrder(@Param("status") String status, @Param("start") Integer start
            , @Param("size") Integer size);

    List<OrderInfo> listByUserId(@Param("userId") BigInteger userId, @Param("start") Integer start
            , @Param("size") Integer size);

    /**
     * 更新订单信息
     * <strong>需要指定的是orderId</strong>
     * @param orderInfo
     */
    void update(OrderInfo orderInfo);

    /**
     * 更新订单状态
     * @param orderId：订单id
     * @param orderStatus：订单状态
     * @param updateTime：更新时间
     */
    void updateOrderStatus(@Param("orderId") BigInteger orderId, @Param("orderStatus")String orderStatus
            , @Param("updateTime")Timestamp updateTime);


    /**
     * 获取回收员还已经完成的订单列表
     * @param recyclerId
     * @param status
     * @param start
     * @param size
     * @return
     */
    List<OrderInfo> listOrderByRecyclerIdWithStatus(@Param("recyclerId") BigInteger recyclerId,@Param("status") String status
            , @Param("start") Integer start, @Param("size") Integer size);

    /**
     * 获取完成订单的分配情况
     *
     * @param start
     * @param size
     * @return
     */
    List<OrderDistributionInfo> listRecyclerId(@Param("status") String status, @Param("start") Integer start, @Param("size") Integer size);


    /**
     * 列举出当前回收员中接单数最少的size个回收员Id以及订单数
     * @param status:代表正在处理的订单状态
     * @param size：查询个数
     * @return
     */
    List<OrderDistributionInfo> listRecyclerFree(@Param("status") String status, @Param("size") Integer size);

}
