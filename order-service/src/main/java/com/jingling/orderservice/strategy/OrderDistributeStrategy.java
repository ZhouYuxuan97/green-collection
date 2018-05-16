package com.jingling.orderservice.strategy;

import com.jingling.basic.enums.ExceptionEnums;
import com.jingling.basic.exceptions.ServiceException;
import com.jingling.orderservice.dto.RecyclerInfo;
import com.jingling.orderservice.dto.RecyclerInfoWithLocation;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

/**
 * 订单的分配策略
 *
 * @author: finalcola-zyy
 * @date: 2018/4/4 18:01
 */
public abstract class OrderDistributeStrategy {

    private OrderFactor orderFactor;

    public Optional<OrderFactor> getOrderFactor(){
        return Optional.of(orderFactor);
    }

    public OrderFactor setOrderFactor(OrderFactor orderFactor){
        this.orderFactor = orderFactor;
        return orderFactor;
    }

    /**
     * 进行选择回收员前执行操作
     *
     * @param recyclerInfos 回收员信息：经纬度、评分、当前已经分配的订单数量
     * @return 处理过后的候选列表
     */
    protected List<RecyclerInfo> preChoose(List<RecyclerInfo> recyclerInfos){
        return recyclerInfos;
    }

    /**
     * 根据回收员的信息对订单进行分配
     *
     * @param recyclerInfoWithLocation 用户地址信息、回收员信息：经纬度、评分、当前已经分配的订单数量
     * @return 选择出来的回收员Id
     */
    protected abstract BigInteger choose(RecyclerInfoWithLocation recyclerInfoWithLocation);

    /**
     * 对回收员完成筛选
     *
     * @param recyclerInfoWithLocation 用户地址信息、回收员信息：经纬度、评分、当前已经分配的订单数量
     * @return
     */
    final public BigInteger work(RecyclerInfoWithLocation recyclerInfoWithLocation) {
        /*
        前置处理
         */
        List<RecyclerInfo> recyclerInfoList = recyclerInfoWithLocation.getRecyclerInfoList();
        if (recyclerInfoList.isEmpty()) {
            //如果筛选时将所有回收员都排除在外，则抛出异常
            throw new ServiceException(ExceptionEnums.LackWorker);
        }
        List<RecyclerInfo> lastRecyclers = preChoose(recyclerInfoList);
        recyclerInfoWithLocation.setRecyclerInfoList(recyclerInfoList);
        /*
        筛选
         */
        BigInteger result = choose(recyclerInfoWithLocation);
        return result;
    }


}
