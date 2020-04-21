package com.xzsd.app.clientOrder.entity;

import java.util.List;
/**
 * @ClassName ClientOrderVO
 * @Deripition app客户端订单返回信息实体类
 * @Author ywq
 * @Date 2020-04-18
 */
public class ClientOrderVO {
    /**
     * 订单编号
     */
    private String orderId;
    /**
     * 订单状态编号
     */
    private String orderStateId;
    /**
     * 商品列表
     */
    private List<ClientOrderGoodsVO> goodsList;
    /**
     * 订单总价
     */
    private String orderAllCost;
    /**
     * 订单商品总数
     */
    private String orderAllGoodsCount;
    /**
     * 版本号
     */
    private String version;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderStateId() {
        return orderStateId;
    }

    public void setOrderStateId(String orderStateId) {
        this.orderStateId = orderStateId;
    }

    public List<ClientOrderGoodsVO> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<ClientOrderGoodsVO> goodsList) {
        this.goodsList = goodsList;
    }

    public String getOrderAllCost() {
        return orderAllCost;
    }

    public void setOrderAllCost(String orderAllCost) {
        this.orderAllCost = orderAllCost;
    }

    public String getOrderAllGoodsCount() {
        return orderAllGoodsCount;
    }

    public void setOrderAllGoodsCount(String orderAllGoodsCount) {
        this.orderAllGoodsCount = orderAllGoodsCount;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
