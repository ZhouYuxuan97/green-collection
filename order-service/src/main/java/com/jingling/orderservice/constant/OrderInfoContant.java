package com.jingling.orderservice.constant;

/**
 * @Auther: finalcola-zyy
 * @Date: 2018/4/2 19:45
 * @Description: 存放订单相关的常量
 */
public class OrderInfoContant {
    public static final int MAX_BUG_NUM = 10000; //单件垃圾单次最大回收数量

    //支付方式 0=线下支付，1=在线支付
    public static final String PAY_TYPE_ONLINE = "1";
    public static final String PAY_TYPE_OFFLINE = "0";

    //回收车中最多能添加多少商品
    public static final int SHOPPING_CART_MAX_NUM = 20;

    public static final int MAX_SELECT_ORDER_SIZE = 50;   //用户查询订单每页的最大请求数量
    public static final int MIN_SELECT_ORDER_SIZE = 10;   //用户查询订单每页的最小请求数量

    public static final String ORDER_STATUS_FAILED = "0"; //失败
    public static final String ORDER_STATUS_SUCCESSED = "1"; //成功
    public static final String ORDER_STATUS_PROCESSING = "2"; //处理中
    public static final String ORDER_STATUS_CANCELED = "3"; //订单取消
    public static final String ORDER_STATUS_PART_CANCELED = "4"; //部分取消
//    public static final String ORDER_STATUS_CLOSED = "0"; //订单关闭

    public static final String ORDER_DESC_DEFAULT = "暂无特殊说明"; //默认订单说明
    public static final String ORDER_ITEM_DESC_DEFAULT = "暂无特殊说明"; //默认订单说明

}
