package com.jingling.orderservice.web;

import com.jingling.basic.constants.SessionConstants;
import com.jingling.basic.enums.ReplyCode;
import com.jingling.basic.vo.ReplyResult;
import com.jingling.orderservice.constant.OrderInfoContant;
import com.jingling.orderservice.dto.Location;
import com.jingling.orderservice.po.OrderInfo;
import com.jingling.orderservice.po.OrderItem;
import com.jingling.orderservice.service.OrderItemService;
import com.jingling.orderservice.service.OrderService;
import com.jingling.orderservice.service.remote.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * 为社区用户暴露的接口
 * 
 * @author: finalcola-zyy
 * @date: 2018/3/28 10:14
 */
@SuppressWarnings("SpringJavaAutowiringInspection")
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderItemService orderItemService;

    @Autowired
    private MailService mailService;

    /**
     * @Description: 添加订单
     * 
     * @Author: finalcola-zyy
     * @param
     * @return
     */
    @RequestMapping(value = "/{userid}/addOrder/fullinfo", produces = {"application/json;charset=UTF-8"})
    public String addOrder(@Validated OrderInfo orderInfo, BindingResult bindingResult
            ,@PathVariable("userId") BigInteger userId, Location location,String orderItemListStr) {
        if (bindingResult.hasErrors()) {
            return ReplyResult.toJson(ReplyCode.ATTR_WRONG);
        }

        //解析垃圾列表信息
        String[] idAndWeight = orderItemListStr.split("-");
        ArrayList<OrderItem> orderItems = new ArrayList<>(idAndWeight.length);
        for (int i = 0; i < idAndWeight.length; i++) {
            String it = idAndWeight[i];
            String[] pair = it.split("_");
            if (pair.length != 2) {
                return ReplyResult.toJson(ReplyCode.WRONG_PATTERN);
            }
            String idStr = pair[0];
            String weightStr = pair[1];
            try {
                Integer id = Integer.valueOf(idStr);
                BigDecimal weight = new BigDecimal(weightStr);
                OrderItem orderItem = new OrderItem();
                orderItem.setGarbageId(id);
                orderItem.setGarbageWeight(weight);
                orderItems.add(orderItem);
            } catch (Exception e){
                return ReplyResult.toJson(ReplyCode.WRONG_PATTERN);
            }
        }

        orderService.addOrder(orderInfo, orderItems,location);
        return ReplyResult.toJson(ReplyCode.OK);
    }

    /**
     * @description: 添加订单,订单中的回收垃圾由回收员填写
     * @auther: finalcola-zyy
     * @param
     * @return
     */
    @RequestMapping(value = "/{userid}/addOrder",produces = {"application/json;charset=UTF-8"})
    public String addOrder(@PathVariable("userId") BigInteger userId, Location location) {
        OrderInfo orderInfo = orderService.addOrder(userId, location);
        BigInteger orderId = orderInfo.getOrderId();
        return ReplyResult.toJsonWithData(orderId);
    }

    /**
     * 取消订单
     *
     * @author: finalcola-zyy
     * @param
     * @return
     */
    @PostMapping(value = "/{userId}/cancelOrder",produces = {"application/json;charset=UTF-8"})
    public String cancel(@RequestParam("orderId") BigInteger orderId, @PathVariable("userId") BigInteger userId) {
        orderService.cancelOrder(orderId, userId);
        return ReplyResult.toJson(ReplyCode.OK);
    }
    
    /**
     * @Description: 获取用户历史的订单列表
     * @author: finalcola-zyy
     * @param
     * @return
     */
    @RequestMapping(value = "/{userId}/listHistoryOrder",produces = {"application/json;charset=UTF-8"})
    public String listHistoryOrder(@RequestParam("page") Integer page,@RequestParam("size") Integer size
            , @PathVariable("userId") BigInteger userId) {
        List<OrderInfo> orderInfoList = orderService.listByUserId(userId, page, size);
        return ReplyResult.toJsonWithData(orderInfoList);
    }


}
