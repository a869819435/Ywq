package com.xzsd.app.clientOrder.entity;

/**
 * @ClassName ClientOrderGoods
 * @Deripition app客户端订单商品实体类
 * @Author ywq
 * @Date 2020-04-18
 */
public class ClientOrderGoods {
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
     * 新增用的商品数量(对应字段goods_count)
     */
    private String clientGoodsNum;
    /**
     * 查询显示的商品数量(对应字段goods_count)
     */
    private String cartGoodsCount;
    /**
     * 商品编号
     */
    private String goodsId;
    /**
     * 门店编号
     */
    private String storeId;

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

    public String getClientGoodsNum() {
        return clientGoodsNum;
    }

    public void setClientGoodsNum(String clientGoodsNum) {
        this.clientGoodsNum = clientGoodsNum;
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

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }
}
