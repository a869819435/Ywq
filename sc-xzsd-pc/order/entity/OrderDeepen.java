package com.xzsd.pc.order.entity;

/**
 * @ClassName OrderDeepen
 * @Derecation 订单详情实体类
 * @Author ywq
 * @Date 2020-04-10
 */
public class OrderDeepen {
    /**
     * 用户编号
     */
    private String userId;
    /**
     * 订单编号
     */
    private String orderId;
    /**
     * 商品编号
     */
    private String goodsId;
    /**
     * 商品数量
     */
    private int goodsCount;
    /**
     * 商品名称
     */
    private String goodsName;
    /**
     * 商品总价
     */
    private String theGoodsAllPrice;
    /**
     * 商品售价
     */
    private String goodsPrice;
    /**
     * 商品定价(就是数据库里的goods_original_cost字段)
     */
    private String goodsShelfCost;
    /**
     * 删除标记
     */
    private int isDelete;
    /**
     * 创作时间
     */
    private String createTime;
    /**
     * 创作者
     */
    private String createUser;
    /**
     * 更新时间
     */
    private String updateTime;
    /**
     * 更新者
     */
    private String updateUser;
    /**
     * 版本号
     */
    private String version;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
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

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getTheGoodsAllPrice() {
        return theGoodsAllPrice;
    }

    public void setTheGoodsAllPrice(String theGoodsAllPrice) {
        this.theGoodsAllPrice = theGoodsAllPrice;
    }

    public String getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(String goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public String getGoodsShelfCost() {
        return goodsShelfCost;
    }

    public void setGoodsShelfCost(String goodsShelfCost) {
        this.goodsShelfCost = goodsShelfCost;
    }

    public int getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(int isDelete) {
        this.isDelete = isDelete;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
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
