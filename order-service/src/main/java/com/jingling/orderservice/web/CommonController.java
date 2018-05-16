package com.jingling.orderservice.web;

import com.jingling.basic.vo.ReplyResult;
import com.jingling.orderservice.po.OrderItem;
import com.jingling.orderservice.service.OrderItemService;
import com.jingling.orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.util.List;

/**
 * @auther: finalcola-zyy
 * @date: 2018/4/3 10:21
 * @description: 用户、回收员、管理员都可以访问的公共接口
 */
@RestController
@RequestMapping("/common")
public class CommonController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderItemService orderItemService;

    /**
     * @description: 获取订单详情
     * @auther: finalcola-zyy
     * @param
     * @return
     */
    @RequestMapping(value = "/getOrderItems",produces = {"application/json;charset=UTF-8"})
    public String getOrderItems(@RequestParam("orderId") BigInteger orderId) {
        List<OrderItem> orderItems = orderItemService.listByOrderId(orderId);
        return ReplyResult.toJsonWithData(orderItems);
    }
}
