package com.xzsd.app.clientGoods.entity;

import java.util.List;

/**
 * @ClassName ClientGoodsInfo
 * @Deripition app客户端商品页信息实体类
 * @Author ywq
 * @Date 2020-04-17
 */
public class ClientGoods {
    /**
     * 一级商品分类信息集合
     */
    List<ClientGoodsClassify>  oneClassifyList;
    /**
     * 二级商品分类信息结合
     */
    List<ClientTwoClassify> twoClassifyList;

    public List<ClientGoodsClassify> getOneClassifyList() {
        return oneClassifyList;
    }

    public void setOneClassifyList(List<ClientGoodsClassify> oneClassifyList) {
        this.oneClassifyList = oneClassifyList;
    }

    public List<ClientTwoClassify> getTwoClassifyList() {
        return twoClassifyList;
    }

    public void setTwoClassifyList(List<ClientTwoClassify> twoClassifyList) {
        this.twoClassifyList = twoClassifyList;
    }
}
