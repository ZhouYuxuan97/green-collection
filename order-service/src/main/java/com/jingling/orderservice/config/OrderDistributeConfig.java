package com.jingling.orderservice.config;

import com.jingling.orderservice.strategy.OrderDistributeStrategy;
import com.jingling.orderservice.strategy.OrderFactor;
import com.jingling.orderservice.strategy.impl.BlackListOrderDistributeStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 设置订单的分配策略
 *
 * @author: finalcola-zyy
 * @date: 2018/4/4 20:04
 */
@Configuration
public class OrderDistributeConfig {

    @Bean
    @ConfigurationProperties(prefix = "orderDistribute")
    public OrderFactor getOrderFactor() {
        return OrderFactor.defaultInstance();
    }

    /**
     * 默认使用具有黑名单功能的策略
     *
     * @param orderFactor
     * @return
     */
    @Bean
    public OrderDistributeStrategy getOrderDistributeStrategy(@Autowired OrderFactor orderFactor) {
        BlackListOrderDistributeStrategy orderDistributeStrategy = new BlackListOrderDistributeStrategy();
        orderDistributeStrategy.setOrderFactor(orderFactor);
        return orderDistributeStrategy;
    }
}
