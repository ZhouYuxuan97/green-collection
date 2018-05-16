package com.jingling.orderservice.web;

import com.jingling.basic.enums.ReplyCode;
import com.jingling.basic.vo.LayuiReplay;
import com.jingling.basic.vo.ReplyResult;
import com.jingling.orderservice.dto.OrderDistributionInfo;
import com.jingling.orderservice.po.OrderInfo;
import com.jingling.orderservice.service.OrderService;
import com.jingling.orderservice.strategy.OrderFactor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

/**
 * @auther: finalcola-zyy
 * @date: 2018/4/3 11:37
 * @description: 管理员服务接口
 */
@CrossOrigin(origins="*", maxAge=3600)
@RestController
@RequestMapping("/manager")
public class ManagerController {

    private static final Logger log = LoggerFactory.getLogger(ManagerController.class);

    @Autowired
    private OrderService orderService;

    /**
     * @description: 显示订单列表
     * @auther: finalcola-zyy
     * @param
     * @return
     */
    @RequestMapping(value = "/listOrders/{status}",produces = {"application/json;charset=UTF-8"})
    public String listOrders(@PathVariable("status") String status,@RequestParam("page") Integer page
            , @RequestParam("size") Integer size) {
        List<OrderInfo> orderInfos = orderService.listOrder(status, page, size);
        LayuiReplay<OrderInfo> layuiReplay = new LayuiReplay<>(ReplyCode.OK.getCode(), "OK", orderInfos.size(), orderInfos);
        return layuiReplay.toJson();
    }

    /**
     * @description: 查看当前未完成订单分配的人员信息
     * @auther: finalcola-zyy
     * @param
     * @return
     */
    @RequestMapping(value = "/listRecyclers",produces = {"application/json;charset=UTF-8"})
    public String listRecyclers(@RequestParam("page") Integer page, @RequestParam("size") Integer size) {
        List<OrderDistributionInfo> orderDistributionInfos = orderService.listRecyclerId(page, size);
        return LayuiReplay.toJson(orderDistributionInfos.size(),orderDistributionInfos);
    }


    /**
     * @description: 手动修改订单分配
     * @auther: finalcola-zyy
     * @param
     * @return
     */
    @RequestMapping(value = "/orderDistribution/updateWithId", produces = {"application/json;charset=UTF-8"})
    public String updateOrderDistribution(BigInteger orderId, BigInteger newRecyclerId) {
        orderService.updateRecycler(orderId, newRecyclerId);
        return ReplyResult.toJsonWithData(null);
    }

    /**
     * @description: 重新分配回收员
     * @auther: finalcola-zyy
     * @param
     * @return
     */
    @RequestMapping(value = "/orderDistribute",produces = {"application/json;charset=UTF-8"})
    public String orderDistribute(BigInteger orderId) {
        log.info("重新分配订单");
        orderService.redistribute(orderId);
        return ReplyResult.toJsonWithData(null);
    }

    /**
     * @description: 取消订单
     * @auther: finalcola-zyy
     * @param
     * @return
     */
    @RequestMapping(value = "/order/cancel",produces = {"application/json;charset=UTF-8"})
    public String orderCancel(BigInteger orderId) {
        orderService.cancelOrder(orderId, null);
        return ReplyResult.toJson(ReplyCode.OK);
    }

    /**
     * @description: 更新订单分配的负载因子
     * @auther: finalcola-zyy
     * @param
     * @return
     */
    @RequestMapping(value = "/orderFactor/update",produces = {"application/json;charset=UTF-8"})
    public String updateOrderFactor(OrderFactor orderFactor) {
        orderService.updateOrderFactor(orderFactor);
        return ReplyResult.toJson(ReplyCode.OK);
    }

    /**
     * @description: 获取当前的分配策略参数,如果当前采用的策略不支持调整，则返回异常状态码
     * @auther: finalcola-zyy
     * @param
     * @return
     */
    @RequestMapping(value = "/orderFactor/get",produces = {"application/json;charset=UTF-8"})
    public String getOrderFactor() {
        OrderFactor orderFactor = orderService.getOrderFactor();
        return ReplyResult.toJsonWithData(orderFactor);
    }
    
}
