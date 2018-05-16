package com.jingling.orderservice.service.impl;

import com.jingling.basic.enums.ExceptionEnums;
import com.jingling.basic.exceptions.ServiceException;
import com.jingling.orderservice.dto.Garbage;
import com.jingling.orderservice.mapper.OrderInfoMapper;
import com.jingling.orderservice.mapper.OrderItemMapper;
import com.jingling.orderservice.po.OrderInfo;
import com.jingling.orderservice.po.OrderItem;
import com.jingling.orderservice.service.OrderItemService;
import com.jingling.orderservice.service.remote.GarbageInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

/**
 * @auther: finalcola-zyy
 * @date: 2018/4/3 09:54
 * @description: 订单服务实现
 */
@SuppressWarnings("SpringJavaAutowiringInspection")
@Service
public class OrderItemServiceImpl implements OrderItemService {

    @Autowired
    private OrderInfoMapper orderInfoMapper;

    @Autowired
    private OrderItemMapper orderItemMapper;

    @Autowired
    private GarbageInfoService garbageInfoService;

    /**
     * 添加订单子项
     *
     * @param orderItem
     */
    @Override
    public void addOrderItem(OrderItem orderItem) {
        BigInteger orderId = orderItem.getOrderId();
        if (orderId == null) {
            throw new ServiceException(ExceptionEnums.OrderInfoNotExist);
        }

        Integer garbageId = orderItem.getGarbageId();
        Integer[] garbageIdArray = new Integer[1];
        garbageIdArray[0] = garbageId;
        List<Garbage> garbageInfos = garbageInfoService.getGarbageInfos(garbageIdArray);
        if (garbageInfos.size() != 1) {
            throw new ServiceException(ExceptionEnums.Error);
        }
        Garbage garbage = garbageInfos.get(0);
        orderItem.setGarbagePrice(garbage.getPrice());
        orderItem.setGarbageName(garbage.getName());
        orderItem.setGarbagePhotoPath(garbage.getPhotoPath());
        /*
        更新订单总价
         */
        OrderInfo orderInfo = orderInfoMapper.getById(orderId);
        BigDecimal originOrderAmount = orderInfo.getOrderAmount();
        BigDecimal garbagePrice = orderItem.getGarbagePrice();
        BigDecimal garbageWeight = orderItem.getGarbageWeight();
        BigDecimal amount = originOrderAmount.add(garbagePrice.multiply(garbageWeight));
        OrderInfo orderInfoToUpdate = new OrderInfo();
        orderInfoToUpdate.setOrderId(orderId).setOrderAmount(amount);
        orderInfoMapper.update(orderInfoToUpdate);

        /*
        保存订单的垃圾信息
         */
        orderItemMapper.save(Arrays.asList(orderItem));
    }

    /**
     * 获取订单中的所有子项
     *
     * @param orderId
     * @return
     */
    @Override
    public List<OrderItem> listByOrderId(BigInteger orderId) {
        List<OrderItem> orderItems = orderItemMapper.listOrderItemByOrderId(orderId);
        return orderItems;
    }

    /**
     * 修改订单详情项的信息
     * @param orderItem
     * @param changerUserId
     */
    @Override
    public void update(OrderItem orderItem, BigInteger changerUserId) {
        //id不能为空
        if (orderItem.getOrderItemId() == null) {
            throw new ServiceException(ExceptionEnums.WrongId);
        }
        OrderItem orderItemInDB = orderItemMapper.getById(orderItem.getOrderId());
        if (orderItemInDB == null) {
            throw new ServiceException(ExceptionEnums.WrongId);
        }
        boolean isManager = false;
        //TODO  检查更新人员是否是管理员
        if (!isManager) {
            BigInteger orderId = orderItemInDB.getOrderId();
            OrderInfo orderInfo = orderInfoMapper.getById(orderId);
            //检查更新人员是否是该订单的回收员
            if (!orderInfo.getRecyclerId().equals(changerUserId)) {
                throw new ServiceException(ExceptionEnums.LackPermission);
            }
        }

        orderItemMapper.update(orderItem);
    }

}
