package com.xzsd.pc.slideshowHome.dao;


import com.xzsd.pc.goods.entity.Goods;
import com.xzsd.pc.slideshowHome.entity.SlideshowHome;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName SlideshowHomeDao
 * @Deprecation 首页轮播图接口类
 * @Author ywq
 * @Date 2020-04-05
 */
@Mapper
public interface SlideshowHomeDao {

    /**
     * 查询轮播图对应的商品是否存在
     * @param goodsId
     * @param slideshowId
     * @return
     */
    int countGoodsId(@Param("goodsId") String goodsId, @Param("slideshowId") String slideshowId);

    /**
     * 查询轮播图对应的序号是否存在
     * @param slideshowNum
     * @param slideshowId
     * @return
     */
    //int countSlideshowNum(@Param("slideshowNum") int slideshowNum, @Param("slideshowId") String slideshowId);

    /**
     * 校验轮播图中不可重复信息是否存在重复
     * @param slideshowHome
     * @return
     */
    int countSlideshowInfo(SlideshowHome slideshowHome);

    /**
     * 解决轮播图对应的序号重复
     * @param slideshowHome
     * @return
     */
    int solveSlideshowNum(SlideshowHome slideshowHome);

    /**
     * 添加首页轮播图接口
     * @param slideshowHome
     * @return
     */
    int addSlideshowHome(SlideshowHome slideshowHome);

    /**
     * 分页查询首页轮播图接口
     * @param slideshowStateId
     * @return
     */
    List<SlideshowHome> listSlideshowHomeByPage(@Param("slideshowStateId") String slideshowStateId);

    /**
     * 查询商品信息接口
     * @param goodsName
     * @param goodsId
     * @return
     */
    List<Goods> listGoodsByPage(@Param("goodsName") String goodsName, @Param("goodsId") String goodsId);

    /**
     * 修改首页轮播图状态接口
     * @param updateSlideList
     * @return
     */
    int updateSlideshowHomeState(@Param(value = "updateSlideList") List<SlideshowHome> updateSlideList);

    /**
     * 删除首页轮播图接口
     * @param deleteSlideshowHome
     * @param updateUser
     * @return
     */
    int deleteSlideshowHome(@Param("deleteSlideshowHome") List<String> deleteSlideshowHome, @Param("updateUser") String updateUser);

}
