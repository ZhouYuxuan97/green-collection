package com.jingling.orderservice.message;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * 订单接收消息通道
 *
 * @author: finalcola-zyy
 * @date: 2018/4/14 14:30
 */
public interface Receiver {

    String ORDER_INPUT = "orderInput";

    @Input(value = ORDER_INPUT)
    SubscribableChannel input();

}
