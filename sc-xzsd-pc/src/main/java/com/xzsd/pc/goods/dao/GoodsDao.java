package com.xzsd.pc.goods.dao;


import com.xzsd.pc.goods.entity.Goods;
import com.xzsd.pc.goodsClassify.entity.GoodsClassify;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 商品管理接口类
 * @ClassName GoodsDao
 * @Description Goods
 * @Author ywq
 * @Date 2020-03-26
 */
@Mapper
public interface GoodsDao {

    /**
     * 查询商品分类下拉框
     * @param classifyId
     * @return
     */
    List<GoodsClassify> listGoodsClassify(@Param("classifyId") String classifyId);

    /**
     * 统计书号数量
     * @param goods 商品信息
     * @return
     */
    int countIsbn(Goods goods);

    /**
     * 新增商品
     * @param goods
     * @return
     */
    int addGoods(Goods goods);

    /**
     * 查询商品详情
     * @param goodsId
     * @return
     */
    Goods getGoods(@Param("goodsId") String goodsId);

    /**
     * 分页查询商品
     * @param goods
     * @return
     */
    List<Goods> listGoodsByPage(Goods goods);

    /**
     * 修改商品信息
     * @param goods
     * @return
     */
    int updateGoods(Goods goods);

    /**
     * 修改商品信息状态
     * @param listUpdate
     * @return
     */
    int updateGoodsState(@Param(value = "listUpdate") List<Goods> listUpdate);

    /**
     * 删除商品
     * @param listGoodsId
     * @param updateUser
     * @return
     */
    int deleteGoods(@Param("listGoodsId") List<String> listGoodsId, @Param("updateUser") String updateUser);

}
