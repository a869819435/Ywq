package com.xzsd.pc.order.entity;

/**
 * @ClassName OrderGoods
 * @Derecation 订单商品管理实体类
 * @Author ywq
 * @Date 2020-04-24
 */
public class OrderGoods {
    /**
     * 订单编号
     */
    private String orderId;
    /**
     * 订单状态编号
     */
    private String orderStateId;
    /**
     * 商品编号
     */
    private String goodsId;
    /**
     * 客户选择的商品数量
     */
    private int goodsCount;
    /**
     * 库存
     */
    private int goodsInventory;
    /**
     * 更新者
     */
    private String updateUser;
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

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public int getGoodsCount() {
        return goodsCount;
    }

    public void setGoodsCount(int goodsCount) {
        this.goodsCount = goodsCount;
    }

    public int getGoodsInventory() {
        return goodsInventory;
    }

    public void setGoodsInventory(int goodsInventory) {
        this.goodsInventory = goodsInventory;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
