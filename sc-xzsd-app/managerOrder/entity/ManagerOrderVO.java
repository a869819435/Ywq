package com.xzsd.app.managerOrder.entity;

import java.util.List;

/**
 * @ClassName ClieManagerOrderVOntOrderVO
 * @Deripition app店长端订单返回信息实体类
 * @Author ywq
 * @Date 2020-04-18
 */
public class ManagerOrderVO {
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
    private List<ManagerOrderGoodsVO> goodsList;
    /**
     * 客户编号
     */
    private String userId;
    /**
     * 客户姓名
     */
    private String userName;
    /**
     * 客户电话
     */
    private String phone;
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

    public List<ManagerOrderGoodsVO> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<ManagerOrderGoodsVO> goodsList) {
        this.goodsList = goodsList;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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
