package com.jingling.orderservice.web;

import com.jingling.basic.enums.ReplyCode;
import com.jingling.basic.vo.ReplyResult;
import com.jingling.orderservice.po.OrderInfo;
import com.jingling.orderservice.po.OrderItem;
import com.jingling.orderservice.service.OrderItemService;
import com.jingling.orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.util.List;

/**
 * @auther: finalcola-zyy
 * @date: 2018/4/3 10:09
 * @description: 为回收员暴露的服务接口
 */
@RestController
@RequestMapping("/recycler")
public class RecyclerController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderItemService orderItemService;

    /**
     * @description: 获取已完成的订单列表
     * @auther: finalcola-zyy
     * @param
     * @return
     */
    @RequestMapping(value = "/{userId}/listCompleteOrders", produces = {"application/json;charset=UTF-8"})
    public String listCompleteOrders(@PathVariable("userId") BigInteger userId
            , @RequestParam(value = "page") Integer page
            , @RequestParam(value = "size") Integer size) {
        List<OrderInfo> orderInfos = orderService.listCompletedOrderByRecyclerId(userId, page, size);
        return ReplyResult.toJsonWithData(orderInfos);
    }

    /**
     * @description: 获取未完成的订单列表
     * @auther: finalcola-zyy
     * @param
     * @return
     */
    @RequestMapping(value = "/{userId}/listUncompleteOrders",produces = {"application/json;charset=UTF-8"})
    public String listUncompleteOrders(@PathVariable("userId") BigInteger userId
            , @RequestParam(value = "page") Integer page
            , @RequestParam(value = "size") Integer size) {
        List<OrderInfo> orderInfos = orderService.listUncompletedOrderByRecyclerId(userId, page, size);
        return ReplyResult.toJsonWithData(orderInfos);
    }

    /**
     * @description: 更新订单状态
     * @auther: finalcola-zyy
     * @param
     * @return
     */
    @RequestMapping(value = "/{userId}/{orderId}/update/Status/{status}",produces = {"application/json;charset=UTF-8"})
    public String updateStatus(@PathVariable("userId") BigInteger userId, @PathVariable("userId") BigInteger orderId
            , @PathVariable("status") String status) {
        orderService.changeOrderStatus(orderId, status, userId);
        return ReplyResult.toJson(ReplyCode.OK);
    }

    /**
     * @description: 完成订单
     * @auther: finalcola-zyy
     * @param
     * @return
     */
    @RequestMapping(value = "/userId/{orderId}/completeOrder",produces = {"application/json;charset=UTF-8"})
    public String completeOrder(@PathVariable("orderId") BigInteger orderId) {
        orderService.finishOrder(orderId);
        return ReplyResult.toJson(ReplyCode.OK);
    }

    /**
     * @param
     * @return
     * @description: 更新订单详情项的信息
     * @auther: finalcola-zyy
     */
    @RequestMapping(value = "/{userId}/update/orderItem", produces = {"application/json;charset=UTF-8"})
    public String updateOrderItem(OrderItem orderItem, @PathVariable("userId") BigInteger userId) {
        orderItemService.update(orderItem, userId);
        return ReplyResult.toJson(ReplyCode.OK);
    }

    /**
     * @description: 为订单添加垃圾信息
     * @auther: finalcola-zyy
     * @param
     * @return
     */
    @RequestMapping(value = "/{userId}/addOrderItem",produces = {"application/json;charset=UTF-8"})
    public String addOrderItem(OrderItem orderItem) {
        orderItemService.addOrderItem(orderItem);
        return ReplyResult.toJsonWithData(null);
    }

}
