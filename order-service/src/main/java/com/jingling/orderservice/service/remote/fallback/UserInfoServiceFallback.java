package com.jingling.orderservice.service.remote.fallback;

import java.math.BigInteger;

/**
 * 获取用户信息的降级处理实现
 *
 * @auther: finalcola-zyy
 * @date: 2018/4/4 17:27
 */
public class UserInfoServiceFallback implements com.jingling.orderservice.service.remote.UserInfoService {
    /**
     * 获取回收员的位置信息
     *
     * @param recyclersId
     * @return
     */
    @Override
    public String getRecyclerLocatin(BigInteger[] recyclersId) {
        return "";
    }

    /**
     * 获取用户的addressId
     *
     * @param userId
     * @return
     */
    @Override
    public String getUserAddressId(BigInteger userId) {
        return null;
    }
}
