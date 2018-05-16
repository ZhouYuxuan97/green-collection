package com.jingling.orderservice.message;

import com.jingling.orderservice.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;

import java.math.BigInteger;

/**
 * 作为订单消息的接收对象
 *
 * @author: finalcola-zyy
 * @date: 2018/4/14 14:28
 */
@EnableBinding(value = {Receiver.class})
public class OrderReceiver {

    private static final Logger log = LoggerFactory.getLogger(OrderReceiver.class);

    @Autowired
    private OrderService orderService;

    @StreamListener(Receiver.ORDER_INPUT)
    public void receive(Message<String> msg) {
        log.info("接收订单消息" + msg);
        orderService.addOrder(new BigInteger(msg.getPayload()), null);
    }

}
