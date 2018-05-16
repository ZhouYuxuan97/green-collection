package com.jingling.orderservice.service.remote;

import com.jingling.orderservice.service.remote.fallback.UserInfoServiceFallback;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigInteger;

/**
 * 获取用户信息的远程服务调用接口
 *
 * @author finalcola-zyy
 * @date 2018/4/4 17:22
 */
@FeignClient(value = "user-service",fallback = UserInfoServiceFallback.class)
public interface UserInfoService {

    /**
     * 获取回收员的位置信息
     *
     * @param recyclersId
     * @return
     */
    @RequestMapping("/recycler/getLocation")
    String getRecyclerLocatin(@RequestParam("recyclersId") BigInteger[] recyclersId);

    /**
     * 获取用户的addressId
     *
     * @param userId
     * @return
     */
    @RequestMapping("/user/getAddressId")
    String getUserAddressId(@RequestParam("userId") BigInteger userId);

}
