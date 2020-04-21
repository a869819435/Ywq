package com.xzsd.app.clientGoods.entity;

import java.util.List;

/**
 * @ClassName ClientGoodsTree
 * @Deripition app客户端二级商品分类以及商品列表实体类
 * @Author ywq
 * @Date 2020-04-17
 */
public class ClientTwoClassify {
    /**
     * 商品分类编号
     */
    private String classifyId;
    /**
     * 商品分类名称
     */
    private String classifyName;
    /**
     * 商品信息列表
     */
    private List<ClientTwoClassifyGoods> goodsList;

    public String getClassifyId() {
        return classifyId;
    }

    public void setClassifyId(String classifyId) {
        this.classifyId = classifyId;
    }

    public String getClassifyName() {
        return classifyName;
    }

    public void setClassifyName(String classifyName) {
        this.classifyName = classifyName;
    }

    public List<ClientTwoClassifyGoods> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<ClientTwoClassifyGoods> goodsList) {
        this.goodsList = goodsList;
    }
}
