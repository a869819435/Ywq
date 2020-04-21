package com.xzsd.app.clientOrder.entity;

import java.util.List;

/**
 * @ClassName ClientOrderDeepenVO
 * @Deripition app客户端订单详情返回信息实体类
 * @Author ywq
 * @Date 2020-04-18
 */
public class ClientOrderDeepenVO {
    /**
     * 门店名称
     */
    private String storeName;
    /**
     * 门店地址
     */
    private String address;
    /**
     * 商品信息集合
     */
    private List<ClientOrderGoods> goodsList;
    /**
     * 订单编号
     */
    private String orderId;
    /**
     * 订单状态编号
     */
    private String orderStateId;
    /**
     * 订单总价
     */
    private String orderAllCost;
    /**
     * 订单商品总数
     */
    private String orderAllGoodsCount;
    /**
     * 创建时间
     */
    private String crateTime;
    /**
     * 商品名称
     */
    private String goodsName;
    /**
     * 商品图片路径
     */
    private String goodsImagePath;
    /**
     * 商品描述
     */
    private String goodsDescribe;
    /**
     * 商品价格
     */
    private String goodsPrice;
    /**
     * 商品数量(对应字段goods_count)
     */
    private String cartGoodsCount;
    /**
     * 商品编号
     */
    private String goodsId;

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<ClientOrderGoods> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<ClientOrderGoods> goodsList) {
        this.goodsList = goodsList;
    }

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

    public String getCrateTime() {
        return crateTime;
    }

    public void setCrateTime(String crateTime) {
        this.crateTime = crateTime;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsImagePath() {
        return goodsImagePath;
    }

    public void setGoodsImagePath(String goodsImagePath) {
        this.goodsImagePath = goodsImagePath;
    }

    public String getGoodsDescribe() {
        return goodsDescribe;
    }

    public void setGoodsDescribe(String goodsDescribe) {
        this.goodsDescribe = goodsDescribe;
    }

    public String getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(String goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public String getCartGoodsCount() {
        return cartGoodsCount;
    }

    public void setCartGoodsCount(String cartGoodsCount) {
        this.cartGoodsCount = cartGoodsCount;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }
}
