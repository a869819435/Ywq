package com.xzsd.pc.goodsClassify.entity;

import java.util.List;

/**
 * @ClassName GoodsClassifyList
 * @Description 商品分类下拉框返回信息实体类
 * @Author ywq
 * @Date 2020-03-29
 */
public class GoodsClassifyList {
    /**
     * 商品分类信息集合
     */
    private List<GoodsClassify> goodsClassifyList;
    /**
     * 商品分类信息集合
     */
    private List<GoodsClassifyTree> oneClassifyList;

    public List<GoodsClassifyTree> getOneClassifyList() {
        return oneClassifyList;
    }

    public void setOneClassifyList(List<GoodsClassifyTree> oneClassifyList) {
        this.oneClassifyList = oneClassifyList;
    }

    public List<GoodsClassify> getGoodsClassifyList() {
        return goodsClassifyList;
    }

    public void setGoodsClassifyList(List<GoodsClassify> goodsClassifyList) {
        this.goodsClassifyList = goodsClassifyList;
    }
}
