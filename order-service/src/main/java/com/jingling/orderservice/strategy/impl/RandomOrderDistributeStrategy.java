package com.jingling.orderservice.strategy.impl;

import com.jingling.orderservice.dto.RecyclerInfo;
import com.jingling.orderservice.dto.RecyclerInfoWithLocation;
import com.jingling.orderservice.strategy.OrderDistributeStrategy;

import java.math.BigInteger;
import java.util.List;
import java.util.Random;

/**
 * 随机选择回收员安排订单
 *
 * @author: finalcola-zyy
 * @date: 2018/4/4 19:24
 */
public class RandomOrderDistributeStrategy extends OrderDistributeStrategy {
    private static Random random = new Random();


    @Override
    protected BigInteger choose(RecyclerInfoWithLocation recyclerInfoWithLocation) {
        List<RecyclerInfo> recyclerInfoList = recyclerInfoWithLocation.getRecyclerInfoList();
        int size = recyclerInfoList.size();
        int nextInt = random.nextInt(size);
        return recyclerInfoList.get(nextInt).getRecyclerId();
    }

}
