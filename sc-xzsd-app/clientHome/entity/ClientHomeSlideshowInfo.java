package com.xzsd.app.clientHome.entity;

/**
 * @ClassName ClientHomeInfo
 * @Deripition app客户首页轮播图实体类
 * @Author ywq
 * @Date 2020-04-17
 */
public class ClientHomeSlideshowInfo {
    /**
     * 商品编号
     */
    private String goodsId;
    /**
     * 轮播图图片路径
     */
    private String slideshowPath;

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getSlideshowPath() {
        return slideshowPath;
    }

    public void setSlideshowPath(String slideshowPath) {
        this.slideshowPath = slideshowPath;
    }

}
