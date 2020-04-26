package com.xzsd.pc.order.entity;

import java.util.List;

/**
 * @ClassName OrderDeepenList
 * @Description 订单信息集合
 * @Author ywq
 * @Date 2020-04-10
 */
public class OrderDeepenList {
    /**
     * 订单详情信息集合
     */
    List<OrderDeepen> orderDeepenList;

    public List<OrderDeepen> getOrderDeepenList() {
        return orderDeepenList;
    }

    public void setOrderDeepenList(List<OrderDeepen> orderDeepenList) {
        this.orderDeepenList = orderDeepenList;
    }
}
