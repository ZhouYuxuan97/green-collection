package com.jingling.orderservice.service;

import com.jingling.orderservice.constant.OrderInfoContant;
import com.jingling.orderservice.dto.Location;
import com.jingling.orderservice.po.OrderInfo;
import com.jingling.orderservice.po.OrderItem;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.*;

/**
 * @auther: finalcola-zyy
 * @Date: 2018/4/2 20:47
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceTest {

    @Autowired
    private OrderService orderService;

    public static OrderInfo createOrderInfo() {
        Random random = new Random();
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setRecyclerId(new BigInteger(String.valueOf(random.nextInt(123123111))))
                .setUserId(new BigInteger("12345"))
                .setAddressId(new BigInteger("11111"))
                .setOrderAmount(new BigDecimal("120.5"))
                .setPayType(OrderInfoContant.PAY_TYPE_ONLINE);
        return orderInfo;
    }

    public static List<OrderItem> createOrderItemList() {
        LinkedList<OrderItem> orderItems = new LinkedList<>();
        OrderItem orderItem = new OrderItem();
        orderItem.setDescription("");
        orderItem.setGarbageId(12345);
        orderItem.setGarbageName("temp for test");
        orderItem.setGarbageNumber(10);
        orderItem.setGarbagePhotoPath("");
        orderItem.setGarbagePrice(new BigDecimal("100.2"));
        orderItem.setGarbageWeight(new BigDecimal("50"));
        orderItems.add(orderItem);
        return orderItems;
    }

    @Test
    public void addOrder() throws Exception {
//        OrderInfo orderInfo = createOrderInfo();
//        List<OrderItem> orderItemList = createOrderItemList();
        BigDecimal l = new BigDecimal("50.5040");
        BigDecimal r = new BigDecimal("20.5044");
        orderService.addOrder(new BigInteger("123456"), new Location(l, r));
//
//        System.out.println(orderInfo);
    }

    @Test
    public void cancelOrder() throws Exception {
    }

    @Test
    public void changeRecycler() throws Exception {
    }

}