package com.jingling.orderservice.strategy;

/**
 * 调整订单的比例因子
 *
 * @author: finalcola-zyy
 * @date: 2018/4/4 19:57
 */
public class OrderFactor {
    private float distanceFactor;
    private float scoreFactor;
    private float orderCountFactor;

    private static OrderFactor DEFAULT_ORDER_FACTOR = new OrderFactor(1, 1, 1);

    public OrderFactor() {
    }

    public OrderFactor(float distanceFactor, float scoreFactor, float orderCountFactor) {
        this.distanceFactor = distanceFactor;
        this.scoreFactor = scoreFactor;
        this.orderCountFactor = orderCountFactor;
    }

    public static OrderFactor defaultInstance(){
        return DEFAULT_ORDER_FACTOR;
    }

    public float getDistanceFactor() {
        return distanceFactor;
    }

    public void setDistanceFactor(float distanceFactor) {
        this.distanceFactor = distanceFactor;
    }

    public float getScoreFactor() {
        return scoreFactor;
    }

    public void setScoreFactor(float scoreFactor) {
        this.scoreFactor = scoreFactor;
    }

    public float getOrderCountFactor() {
        return orderCountFactor;
    }

    public void setOrderCountFactor(float orderCountFactor) {
        this.orderCountFactor = orderCountFactor;
    }

    @Override
    public String toString() {
        return "OrderFactor{" +
                "distanceFactor=" + distanceFactor +
                ", scoreFactor=" + scoreFactor +
                ", orderCountFactor=" + orderCountFactor +
                '}';
    }
}
