package com.jingling.orderservice.mapper;

import com.jingling.orderservice.po.OrderInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @auther: finalcola-zyy
 * @Date: 2018/3/27 21:30
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderInfoMapperTest {

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private OrderInfoMapper orderInfoMapper;

    public static OrderInfo createOrderInfo(){
        long currentTimeMillis = System.currentTimeMillis();
        Timestamp now = new Timestamp(currentTimeMillis);
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setAddressId(new BigInteger("123456"));
        orderInfo.setCreateTime(now);
        orderInfo.setUpdateTime(now);
        orderInfo.setDescription("暂无");
        orderInfo.setOrderAmount(new BigDecimal("100.50"));
        orderInfo.setOrderStatus("1");
        orderInfo.setPaid(false);
        orderInfo.setPayType("1");
        orderInfo.setUserId(new BigInteger("11111"));
        orderInfo.setRecyclerId(new BigInteger("123456789"));
        return orderInfo;
    }

    @Test
    public void save() throws Exception {
        OrderInfo orderInfo = createOrderInfo();
        orderInfo.setOrderId(new BigInteger("1111"));
        orderInfoMapper.save(orderInfo);
    }

    @Test
    public void deleteById() throws Exception {
    }

    @Test
    public void getById() throws Exception {
    }

    @Test
    public void listByUserId() throws Exception {
    }

    @Test
    public void listOrder() {
        List<OrderInfo> orderInfos = orderInfoMapper.listOrder("1", 1, 10);
        orderInfos.forEach(System.out::println);
    }

}