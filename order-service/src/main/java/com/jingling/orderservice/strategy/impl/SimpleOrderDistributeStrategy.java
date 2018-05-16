package com.jingling.orderservice.strategy.impl;

import com.jingling.orderservice.dto.Location;
import com.jingling.orderservice.dto.RecyclerInfo;
import com.jingling.orderservice.dto.RecyclerInfoWithLocation;
import com.jingling.orderservice.strategy.OrderDistributeStrategy;
import com.jingling.orderservice.strategy.OrderFactor;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * 对订单分配策略的实现：
 *      通过回收员的订单数和距离进行综合排序，来完成筛选
 *
 * @author: finalcola-zyy
 * @date: 2018/4/4 18:12
 */
public class SimpleOrderDistributeStrategy extends OrderDistributeStrategy {



    /**
     * 根据回收员的信息对订单进行分配
     *
     * @param recyclerInfoWithLocation 用户地址信息、回收员信息：经纬度、评分、当前已经分配的订单数量
     * @return 选择出来的回收员Id
     */
    @Override
    protected BigInteger choose(RecyclerInfoWithLocation recyclerInfoWithLocation) {
        final List<RecyclerInfo> recyclerInfoList = recyclerInfoWithLocation.getRecyclerInfoList();
        Location userLocation = recyclerInfoWithLocation.getUserLocation();
        final ArrayList<BigDecimal> distanceList = new ArrayList<>(recyclerInfoList.size());//记录回收员到用户的距离
        //获取距离
        recyclerInfoList.forEach(x->{
            if (userLocation == null) {
                distanceList.add(BigDecimal.ONE);
            } else {
                BigDecimal recyLatitude = x.getLatitude();
                BigDecimal recyLongitude = x.getLongitude();
                BigDecimal userLatitude = userLocation.getLatitude();
                BigDecimal userLongitude = userLocation.getLongitude();
                //求出回收员到用户的距离(x1-x2)+(y1-y2)
                BigDecimal distance = recyLatitude.subtract(userLatitude).abs().add(recyLongitude.subtract(userLongitude).abs());
                distanceList.add(distance);
            }
        });

        /**
         * 获取比例因子，如果没有设置，就使用默认的
         */
        Optional<OrderFactor> orderFactorOptional = super.getOrderFactor();
        OrderFactor orderFactor = orderFactorOptional.orElse(OrderFactor.defaultInstance());
        final float distanceFactor = orderFactor.getDistanceFactor();
        final float orderCountFactor = orderFactor.getOrderCountFactor();
        final float scoreFactor = orderFactor.getScoreFactor();

        BigDecimal maxScore = BigDecimal.ZERO;
        BigInteger result = null;
        for (int i = 0; i < recyclerInfoList.size(); i++) {
            RecyclerInfo recyclerInfo = recyclerInfoList.get(i);
            BigDecimal distance = distanceList.get(i);
            Integer score = recyclerInfo.getScore();
            Integer orderCount = recyclerInfo.getOrderCount();
            float factor = (scoreFactor * score) / (10 + (orderCount * orderCountFactor));
            //计算最后得分=（x*回收员评分/(10+y*订单数量)）*距离*z
            BigDecimal finalScore = distance.multiply(new BigDecimal(factor)).multiply(new BigDecimal(distanceFactor));
            if (finalScore.compareTo(maxScore) > 0) {
                maxScore = finalScore;
                result = recyclerInfo.getRecyclerId();
            }
        }
        return result;
    }
}
