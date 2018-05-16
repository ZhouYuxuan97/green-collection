package com.jingling.orderservice.service.remote;

import com.jingling.orderservice.dto.Garbage;
import com.jingling.orderservice.service.remote.fallback.GarbageInfoServiceFallback;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 垃圾信息的远程服务
 *
 * @author: finalcola-zyy
 * @date: 2018/4/6 16:16
 */
@FeignClient(value = "waste-service",fallback = GarbageInfoServiceFallback.class)
public interface GarbageInfoService {

    @RequestMapping("/garbage/listGarbageByIds")
    List<Garbage> getGarbageInfos(@RequestParam("ids") Integer[] garbageIds);

}
