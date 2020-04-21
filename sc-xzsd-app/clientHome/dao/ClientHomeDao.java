package com.xzsd.app.clientHome.dao;

import com.xzsd.app.clientHome.entity.ClientHomeGoodsInfo;
import com.xzsd.app.clientHome.entity.ClientHomeSlideshowInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName ClientHomeDao
 * @Deripition app客户首页接口类
 * @Author ywq
 * @Date 2020-04-17
 */
@Mapper
public interface ClientHomeDao {
    /**
     * 查询首页轮播图信息接口
     * @return
     */
    List<ClientHomeSlideshowInfo> listRotationCharHome();

    /**
     * 获取热门商品展示数量接口
     * @return
     */
    int getHotGoodsShowNum();

    /**
     * 查询热门商品信息接口
     * @param showNum
     * @return
     */
    List<ClientHomeGoodsInfo> listHotGoods(@Param("showNum") int showNum);
}
