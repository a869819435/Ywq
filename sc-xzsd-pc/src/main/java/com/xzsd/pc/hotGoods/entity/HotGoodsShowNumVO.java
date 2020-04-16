package com.xzsd.pc.hotGoods.entity;

/**
 * @ClassName HotGoods
 * @Deprecation 热门商品展示数量实体类
 * @Author ywq
 * @Date 2020-04-11
 */
public class HotGoodsShowNumVO {
    /**
     * 热门商品展示数量
     */
    private int hotGoodsShowNum;
    /**
     * 版本号
     */
    private String version;

    public int getHotGoodsShowNum() {
        return hotGoodsShowNum;
    }

    public void setHotGoodsShowNum(int hotGoodsShowNum) {
        this.hotGoodsShowNum = hotGoodsShowNum;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
