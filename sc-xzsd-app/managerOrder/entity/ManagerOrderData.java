package com.xzsd.app.managerOrder.entity;

/**
 * @ClassName ManagerOrderData
 * @Deripition app店长端店长订单页信息实体类
 * @Author ywq
 * @Date 2020-04-19
 */
public class ManagerOrderData {
    /**
     * 订单详情商品列表数据
     */
    ManagerOrderDeepenVO orderDeepen;

    public ManagerOrderDeepenVO getOrderDeepn() {
        return orderDeepen;
    }

    public void setOrderDeepn(ManagerOrderDeepenVO orderDeepen) {
        this.orderDeepen = orderDeepen;
    }
}
