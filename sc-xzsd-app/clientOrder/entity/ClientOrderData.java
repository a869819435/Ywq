package com.xzsd.app.clientOrder.entity;

import java.util.List;

/**
 * @ClassName ClientOrderData
 * @Deripition app客户端订单页信息实体类
 * @Author ywq
 * @Date 2020-04-18
 */
public class ClientOrderData {
    /**
     * 订单详情数据
     */
    ClientOrderDeepenVO orderDeepen;
    /**
     * 商品信息集合
     */
    List<ClientOrderGoods> goodsList;

    public ClientOrderDeepenVO getOrderDeepen() {
        return orderDeepen;
    }

    public void setOrderDeepen(ClientOrderDeepenVO orderDeepen) {
        this.orderDeepen = orderDeepen;
    }

    public List<ClientOrderGoods> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<ClientOrderGoods> goodsList) {
        this.goodsList = goodsList;
    }
}
