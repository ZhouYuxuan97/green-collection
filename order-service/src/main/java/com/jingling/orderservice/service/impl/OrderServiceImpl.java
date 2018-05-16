package com.jingling.orderservice.service.impl;

import com.jingling.basic.enums.ExceptionEnums;
import com.jingling.basic.exceptions.ServiceException;
import com.jingling.basic.utils.FieldTransfer;
import com.jingling.basic.utils.StringUtils;
import com.jingling.orderservice.constant.OrderInfoContant;
import com.jingling.orderservice.dto.*;
import com.jingling.orderservice.mapper.OrderInfoMapper;
import com.jingling.orderservice.mapper.OrderItemMapper;
import com.jingling.orderservice.po.OrderInfo;
import com.jingling.orderservice.po.OrderItem;
import com.jingling.orderservice.service.OrderService;
import com.jingling.orderservice.service.remote.GarbageInfoService;
import com.jingling.orderservice.service.remote.UserInfoService;
import com.jingling.orderservice.strategy.OrderDistributeStrategy;
import com.jingling.orderservice.strategy.OrderFactor;
import com.jingling.orderservice.strategy.impl.SimpleOrderDistributeStrategy;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

/**
 * @Auther: finalcola-zyy
 * @Date: 2018/3/28 09:57
 * @Description: 订单服务的实现类
 */
@SuppressWarnings({"SpringJavaAutowiringInspection", "unused"})
@Service
public class OrderServiceImpl implements OrderService {
    private static final Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    private OrderInfoMapper orderInfoMapper;

    @Autowired
    private OrderItemMapper orderItemMapper;

    @Autowired
    private OrderDistributeStrategy orderDistributeStrategy;

    @Autowired
    private OrderFactor orderFactor;

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private GarbageInfoService garbageInfoService;

//    @Value("${order.config.simpleMode}")
    private String simpleMode;

    private Random random = new Random();

    /**
     * 订单分配时，进行自动分配时的默认人数
     */
    public static final int DEFAULT_CHOOSE_RECYCLER_NUM = 20;


    /**
     * 添加订单
     *
     * @param orderInfo : 包含订单的信息：回收员id，用户id，用户地址id
     * @param itemList  ： 订单包含的子项信息：
     *                  垃圾Id，垃圾名称，垃圾图片，垃圾价格，回收数量，垃圾重量，说明
     * @param location: 包含添加订单的用户的经纬度信息
     * @return
     */
    @Override
    public OrderInfo addOrder(OrderInfo orderInfo, List<OrderItem> itemList, Location location) {
        /*
        填充订单信息，如创建时间，订单状态等
         */
        String userAddressIdStr = userInfoService.getUserAddressId(orderInfo.getUserId());
        BigInteger addressId = new BigInteger(userAddressIdStr);//用户地址Id
        orderInfo.setAddressId(addressId);

        BigInteger recyclerId = orderDistribute(orderInfo, location);
        orderInfo.setRecyclerId(recyclerId);

        //设置创建时间、更新时间为当前时间
        long currentTimeMillis = System.currentTimeMillis();
        Timestamp now = new Timestamp(currentTimeMillis);
        orderInfo.setCreateTime(now);
        orderInfo.setUpdateTime(now);
        //设置为当前未支付状态
        orderInfo.setPaid(false);
        //设置订单初始状态--处理中
        orderInfo.setOrderStatus(OrderInfoContant.ORDER_STATUS_PROCESSING);
        //设置默认的订单说明
        if (StringUtils.isEmpty(orderInfo.getDescription())) {
            orderInfo.setDescription(OrderInfoContant.ORDER_DESC_DEFAULT);
        }

        /*
        填充回收的垃圾信息
        计算订单总价
         */
        //获取垃圾信息
        Integer[] ids = new Integer[itemList.size()];
        for (int i = 0; i < ids.length; i++) {
            ids[i] = itemList.get(i).getGarbageId();
        }
        List<Garbage> garbageInfos = garbageInfoService.getGarbageInfos(ids);
        if (garbageInfos.size() != itemList.size()) {
            log.error("核对垃圾列表和获取的垃圾信息列表长度失败");
            throw new ServiceException(ExceptionEnums.Error);
        }

        BigDecimal amountPrice = BigDecimal.ZERO;
        //填充属性
        for (int i = 0; i < garbageInfos.size(); i++) {
            Garbage garbage = garbageInfos.get(i);
            OrderItem orderItem = itemList.get(i);
            orderItem.setGarbageName(garbage.getName());
            //单价
            BigDecimal singlePrice = garbage.getPrice();
            orderItem.setGarbagePrice(singlePrice);
            orderItem.setGarbagePhotoPath(garbage.getPhotoPath());
            //暂时不使用number字段
            orderItem.setGarbageNumber(-1);

            //计算价格总和
            BigDecimal garbageWeight = orderItem.getGarbageWeight();
            BigDecimal simgleAmount = garbageWeight.multiply(singlePrice);
            amountPrice.add(simgleAmount);
        }
        //设置总价
        orderInfo.setOrderAmount(amountPrice);

        /*
        保存总订单信息
         */
        orderInfoMapper.save(orderInfo);

//        log.info("orderInfo:" + orderInfo);

        /*
        保存订单子项的信息
         */
        //获取新加入订单的自增Id并设置到item中
        BigInteger orderId = orderInfo.getOrderId();
        itemList.forEach(x->{
            x.setOrderId(orderId);
            x.setDescription(OrderInfoContant.ORDER_ITEM_DESC_DEFAULT);
        });
        //保存详情子项信息
        orderItemMapper.save(itemList);

        return orderInfo;
    }

    /**
     * 添加订单
     *
     * @param userId   :用户ID
     * @param location ：用户所在地址
     * @return
     */
    @Override
    public OrderInfo addOrder(BigInteger userId, Location location) {
        OrderInfo orderInfo = new OrderInfo();
        /*
        填充订单信息，如创建时间，订单状态等
         */
        orderInfo.setUserId(userId);
        //用户地址Id
        String userAddressIdStr = null;
        try {
            userAddressIdStr = userInfoService.getUserAddressId(userId);
        } catch (FeignException e) {
            log.error("调用远程服务失败", e);
        }
        if (StringUtils.isEmpty(userAddressIdStr)) {
            orderInfo.setAddressId(null);
        } else {
            BigInteger addressId = new BigInteger(userAddressIdStr);
            orderInfo.setAddressId(addressId);
        }

        BigInteger recyclerId = orderDistribute(null, location);
        orderInfo.setRecyclerId(recyclerId);

        //设置创建时间、更新时间为当前时间
        long currentTimeMillis = System.currentTimeMillis();
        Timestamp now = new Timestamp(currentTimeMillis);
        orderInfo.setCreateTime(now);
        orderInfo.setUpdateTime(now);
        //设置为当前未支付状态
        orderInfo.setPaid(false);
        //设置订单初始状态--处理中
        orderInfo.setOrderStatus(OrderInfoContant.ORDER_STATUS_PROCESSING);
        //设置默认的订单说明
        if (StringUtils.isEmpty(orderInfo.getDescription())) {
            orderInfo.setDescription(OrderInfoContant.ORDER_DESC_DEFAULT);
        }

        orderInfoMapper.save(orderInfo);
        return orderInfo;
    }

    /**
     * 重新分配订单
     *
     * @param orderId
     */
    @Override
    public void redistribute(BigInteger orderId) {
        BigInteger recyclerID = orderDistribute(null, null);
        log.info(recyclerID + "");
        if (recyclerID == null) {
            throw new ServiceException(ExceptionEnums.Error);
        }
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setOrderId(orderId);
        orderInfo.setRecyclerId(recyclerID);
        orderInfoMapper.update(orderInfo);
    }

    /**
     * 完成订单并支付
     *
     * @param orderId
     */
    @Override
    public void finishOrder(BigInteger orderId) {
        OrderInfo orderInfo = orderInfoMapper.getById(orderId);
        //TODO 进行支付

        OrderInfo paid = orderInfo.setPaid(true);
        orderInfo.setOrderStatus(OrderInfoContant.ORDER_STATUS_SUCCESSED);
        orderInfoMapper.update(orderInfo);
    }

    /**
     * 根据用户信息以及所在位置，自动分配合适的回收员
     *
     * @param orderInfo 订单信息
     * @param userLocation 用户的地址信息
     * @return
     */
    @Override
    public BigInteger orderDistribute(OrderInfo orderInfo, Location userLocation) {
        //获取当前时间，接收订单数最少的DEFAULT_CHOOSE_RECYCLER_NUM个回收员信息（Id，已接收的订单数）
        List<OrderDistributionInfo> orderDistributionInfos =
                orderInfoMapper.listRecyclerFree(OrderInfoContant.ORDER_STATUS_PROCESSING, DEFAULT_CHOOSE_RECYCLER_NUM);
        if (StringUtils.isEmpty(simpleMode)) {
            simpleMode = "true";
        }

        orderDistributionInfos.forEach(x->{
            log.debug("打印待分配回收员ID:"+x.getRecyclerId().toString());
        });

        boolean isSimple = Boolean.valueOf(simpleMode);

        if (isSimple) {
            int nextInt = random.nextInt(orderDistributionInfos.size());
            BigInteger res = orderDistributionInfos.get(nextInt).getRecyclerId();
            return res;
        }
        BigInteger[] recyclerIdArray = new BigInteger[orderDistributionInfos.size()];
        for (int i = 0; i < recyclerIdArray.length; i++) {
            recyclerIdArray[i] = orderDistributionInfos.get(i).getRecyclerId();
        }
        //获取回收员的位置信息（经纬度）
        String recyclerLocatinArrayJson = userInfoService.getRecyclerLocatin(recyclerIdArray);
        List<RecyclerInfo> recyclerInfos = RecyclerInfo.fromJson(recyclerLocatinArrayJson);
//        recyclerInfos.sort((x,y)->{
//            return x.getRecyclerId().compareTo(y.getRecyclerId());
//        });
        if (recyclerInfos.size() != orderDistributionInfos.size()) {
            log.error("订单分配时，用户模块返回的回收员信息数量与请求数量不符");
            throw new ServiceException(ExceptionEnums.Error);
        }
        //将获取的两个列表已回收员Id进行排序
        recyclerInfos.sort(Comparator.comparing(RecyclerInfo::getRecyclerId));
        orderDistributionInfos.sort(Comparator.comparing(OrderDistributionInfo::getRecyclerId));
        //将订单数设置到recyclerInfos列表中
        for (int i = 0; i < recyclerInfos.size(); i++) {
            RecyclerInfo recyclerInfo = recyclerInfos.get(i);
            recyclerInfo.setOrderCount(orderDistributionInfos.get(i).getOrderCount());
        }

        //进行选择
        BigInteger choosedRecycler = doOrderDistribute(recyclerInfos, userLocation);
        return choosedRecycler;
    }

    @Override
    public void updateOrderFactor(OrderFactor orderFactor) {
        try {
            log.debug(orderFactor.toString()+"\n"+orderFactor.getClass().getName()+"\n"+this.orderFactor.getClass().getName());
            FieldTransfer.fieldTrans(orderFactor, this.orderFactor);
        } catch (IllegalAccessException e) {
            log.error("复制字段错误");
            throw new ServiceException(ExceptionEnums.Error);
        }
    }


    /**
     * 查询当前的订单分配因子，如果当前分配策略不支持，则抛出服务异常
     *
     * @return
     */
    @Override
    public OrderFactor getOrderFactor() {
        if (orderDistributeStrategy instanceof SimpleOrderDistributeStrategy) {
            return this.orderFactor;
        }
        throw new ServiceException(ExceptionEnums.Error);
    }


    /**
     * 根据回收员的信息对订单进行分配
     *
     * @param recyclerInfos 回收员信息：经纬度、评分、当前已经分配的订单数量
     * @param userLocation 包含用户所在位置的经纬度
     * @return 选择出来的回收员Id
     */
    protected BigInteger doOrderDistribute(List<RecyclerInfo> recyclerInfos, Location userLocation) {
        RecyclerInfoWithLocation recyclerInfoWithLocation = new RecyclerInfoWithLocation(recyclerInfos, userLocation);
        BigInteger result = orderDistributeStrategy.work(recyclerInfoWithLocation);
        return result;
    }

    /**
     * 取消订单
     *
     * @param orderId ：订单Id
     * @param userId  ：当前操作用户的Id
     */
    @Override
    public void cancelOrder(BigInteger orderId, BigInteger userId) {
        /*
        1、获取对应的订单信息
        2、比对用户Id
        3、
            a：比对正确：取消订单
            b: 比对错误：记录当前用户信息，抛出异常
         */

        OrderInfo order = orderInfoMapper.getById(orderId);
//        BigInteger userIdInOrder = order.getUserId();
//        if (!userId.equals(userIdInOrder)) {
//            throw new ServiceException(ExceptionEnums.WrongId);
//        }

        /*
        删除订单详情子项
        将订单状态设置为 已取消
        通知回收员
         */
        orderItemMapper.deleteByOrderId(orderId);
        orderInfoMapper.updateOrderStatus(orderId
                ,OrderInfoContant.ORDER_STATUS_CANCELED,new Timestamp(System.currentTimeMillis()));
//        orderInfoMapper
        //TODO 通过邮件或短信通知回收员该订单已经取消
    }

    /**
     * 更改回收人员Id
     *
     * @param orderId    ：订单Id
     * @param recyclerId ：回收员id
     */
    @Override
    public void changeRecycler(BigInteger orderId, BigInteger recyclerId) {
        /**
         * 检查订单信息是否存在
         * 若存在：修改订单信息，并通知新的回收员
         * 若不存在：抛出异常
         */

        OrderInfo orderInfo = orderInfoMapper.getById(orderId);
        if (orderInfo == null) {
            throw new ServiceException(ExceptionEnums.OrderInfoNotExist);
        }

        OrderInfo newOrderInfo = new OrderInfo();
        orderInfo.setOrderId(orderId)
                .setRecyclerId(recyclerId)
                .setUpdateTime(new Timestamp(System.currentTimeMillis()));
        orderInfoMapper.update(orderInfo);
        //TODO  通知新回收员
    }

    /**
     * 获取用户的历史订单
     *
     * @param userId ：用户Id
     * @param page
     * @param size
     * @return
     */
    @Override
    public List<OrderInfo> listByUserId(BigInteger userId, Integer page, Integer size) {
        Integer start = (Math.max(page, 1) - 1) * size;
        size = Math.min(size, OrderInfoContant.MAX_SELECT_ORDER_SIZE);
        size = (size < 1) ? OrderInfoContant.MIN_SELECT_ORDER_SIZE : size;
        List<OrderInfo> orderInfoList = orderInfoMapper.listByUserId(userId, start, size);
        return orderInfoList;
    }

    /**
     * 获取回收员还已经完成的订单列表
     *
     * @param recyclerId
     * @param page
     * @param size
     * @return
     */
    @Override
    public List<OrderInfo> listCompletedOrderByRecyclerId(BigInteger recyclerId, Integer page, Integer size) {
        Integer start = (Math.max(page, 1) - 1) * size;
        size = Math.min(size, OrderInfoContant.MAX_SELECT_ORDER_SIZE);
        size = (size < 1) ? OrderInfoContant.MIN_SELECT_ORDER_SIZE : size;

        String orderStatusProcessing = OrderInfoContant.ORDER_STATUS_PROCESSING;
        List<OrderInfo> orderInfoList = orderInfoMapper.listOrderByRecyclerIdWithStatus(recyclerId, orderStatusProcessing, start, size);
        return orderInfoList;
    }

    /**
     * 获取回收员还未完成的订单列表
     *
     * @param recyclerId
     * @param page
     * @param size
     * @return
     */
    @Override
    public List<OrderInfo> listUncompletedOrderByRecyclerId(BigInteger recyclerId, Integer page, Integer size) {
        Integer start = (Math.max(page, 1) - 1) * size;
        size = Math.min(size, OrderInfoContant.MAX_SELECT_ORDER_SIZE);
        size = (size < 1) ? OrderInfoContant.MIN_SELECT_ORDER_SIZE : size;

        String orderStatusProcessing = OrderInfoContant.ORDER_STATUS_SUCCESSED;
        List<OrderInfo> orderInfoList = orderInfoMapper.listOrderByRecyclerIdWithStatus(recyclerId, orderStatusProcessing, start, size);
        return orderInfoList;
    }

    /**
     * 修改订单状态
     *
     * @param orderId：订单Id
     * @param orderStatus：更新后的订单状态
     * @param userId:回收员Id
     */
    @Override
    public void changeOrderStatus(BigInteger orderId, String orderStatus, BigInteger userId) {
        OrderInfo orderInfo = orderInfoMapper.getById(orderId);
        if (!orderInfo.getRecyclerId().equals(userId)) {
            //如果请求修改的回收员与订单中记录的回收员不一致，就抛出异常，并通知管理员
            //TODO 通知管理员
            throw new ServiceException(ExceptionEnums.Error);
        }
        //修改订单状态
        Timestamp now = new Timestamp(System.currentTimeMillis());
        orderInfoMapper.updateOrderStatus(orderId, orderStatus, now);
    }

    /**
     * 获取当前未完成订单的分配情况
     *
     * @param page
     * @param size
     * @return
     */
    @Override
    public List<OrderDistributionInfo> listRecyclerId(Integer page, Integer size) {
        Integer start = (Math.max(page, 1) - 1) * size;
        size = (size > OrderInfoContant.MAX_SELECT_ORDER_SIZE) ? OrderInfoContant.MAX_SELECT_ORDER_SIZE : size;
        List<OrderDistributionInfo> orderDistributionInfos =
                orderInfoMapper.listRecyclerId(OrderInfoContant.ORDER_STATUS_PROCESSING, start, size);
        return orderDistributionInfos;
    }

    /**
     * 手动重新分配订单的配送员
     *
     * @param orderId
     * @param newReyclerId
     */
    @Override
    public void updateRecycler(BigInteger orderId, BigInteger newReyclerId) {
        OrderInfo orderInfo = new OrderInfo().setOrderId(orderId).setRecyclerId(newReyclerId);
        orderInfoMapper.update(orderInfo);
    }

    /**
     * 查询订单列表
     *
     * @param status :订单状态
     * @param page
     * @param size
     * @return
     */
    @Override
    public List<OrderInfo> listOrder(String status, Integer page, Integer size) {
        Integer start = (Math.max(page, 1) - 1) * size;
        size = (size > OrderInfoContant.MAX_SELECT_ORDER_SIZE) ? OrderInfoContant.MAX_SELECT_ORDER_SIZE : size;
        List<OrderInfo> orderInfos = orderInfoMapper.listOrder(status, start, size);
        return orderInfos;
    }


}
