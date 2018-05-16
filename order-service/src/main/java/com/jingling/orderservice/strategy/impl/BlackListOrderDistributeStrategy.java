package com.jingling.orderservice.strategy.impl;

import com.jingling.orderservice.dto.RecyclerInfo;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 在基础晒功能上，添加黑名单功能
 *
 * @author: finalcola-zyy
 * @date: 2018/4/4 19:27
 */
public class BlackListOrderDistributeStrategy extends SimpleOrderDistributeStrategy {

    private List<BigInteger> balckList=new LinkedList<>();

    /**
     * 将在黑名单中的人员剔除
     * @param recyclerInfos 回收员信息：经纬度、评分、当前已经分配的订单数量
     * @return
     */
    @Override
    protected List<RecyclerInfo> preChoose(List<RecyclerInfo> recyclerInfos) {
        List<RecyclerInfo> list = super.preChoose(recyclerInfos);
        LinkedList<RecyclerInfo> toDelete = new LinkedList<>();
        for (int i = 0; i < list.size(); i++) {
            RecyclerInfo recyclerInfo = list.get(i);
            BigInteger recyclerId = recyclerInfo.getRecyclerId();
            if (balckList.contains(recyclerId)) {
                toDelete.add(recyclerInfo);
            }
        }
        list.removeAll(toDelete);
        return list;
    }

    public void addBlackList(BigInteger recyclerId) {
        balckList.add(recyclerId);
    }

    public List<BigInteger> getBalckList() {
        return balckList;
    }

    public void setBalckList(List<BigInteger> balckList) {
        this.balckList = balckList;
    }
}
