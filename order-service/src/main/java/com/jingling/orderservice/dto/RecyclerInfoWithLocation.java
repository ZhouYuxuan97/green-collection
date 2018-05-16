package com.jingling.orderservice.dto;

import java.util.List;

/**
 * 包含用户地址以及回收员信息
 *
 * @author: finalcola-zyy
 * @date: 2018/4/4 18:14
 */
public class RecyclerInfoWithLocation {
    private List<RecyclerInfo> recyclerInfoList;
    private Location userLocation;

    public RecyclerInfoWithLocation(List<RecyclerInfo> recyclerInfoList, Location userLocation) {
        this.recyclerInfoList = recyclerInfoList;
        this.userLocation = userLocation;
    }

    public RecyclerInfoWithLocation() {
    }

    public List<RecyclerInfo> getRecyclerInfoList() {
        return recyclerInfoList;
    }

    public void setRecyclerInfoList(List<RecyclerInfo> recyclerInfoList) {
        this.recyclerInfoList = recyclerInfoList;
    }

    public Location getUserLocation() {
        return userLocation;
    }

    public void setUserLocation(Location userLocation) {
        this.userLocation = userLocation;
    }
}
