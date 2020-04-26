package com.xzsd.app.clientHome.entity;

import java.util.List;

/**
 * @ClassName SlideshowList
 * @Deripition app客户首页信息实体类
 * @Author ywq
 * @Date 2020-04-17
 */
public class ClientHome {
    /**
     * 首页轮播图信息数据
     */
    List<ClientHomeSlideshowInfo> slideshowList;
    /**
     * 热门商品信息集合
     */
    List<ClientHomeGoodsInfo> list;

    public List<ClientHomeSlideshowInfo> getSlideshowList() {
        return slideshowList;
    }

    public void setSlideshowList(List<ClientHomeSlideshowInfo> slideshowList) {
        this.slideshowList = slideshowList;
    }

    public List<ClientHomeGoodsInfo> getList() {
        return list;
    }

    public void setList(List<ClientHomeGoodsInfo> list) {
        this.list = list;
    }
}
