package com.jingling.orderservice.service.remote.fallback;

import com.jingling.orderservice.dto.Garbage;
import com.jingling.orderservice.service.remote.GarbageInfoService;

import java.util.Collections;
import java.util.List;

/**
 * 垃圾信息服务的降级处理
 *
 * @author: finalcola-zyy
 * @date: 2018/4/6 18:23
 */
public class GarbageInfoServiceFallback implements GarbageInfoService {

    @Override
    public List<Garbage> getGarbageInfos(Integer[] garbageIds) {
        return Collections.emptyList();
    }
}
