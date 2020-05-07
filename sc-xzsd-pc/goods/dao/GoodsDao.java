package com.xzsd.pc.goods.dao;


import com.xzsd.pc.goods.entity.Goods;
import com.xzsd.pc.goodsClassify.entity.GoodsClassify;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName GoodsDao
 * @Description 商品管理接口类
 * @Author ywq
 * @Date 2020-03-26
 */
@Mapper
public interface GoodsDao {

    /**
     * 查询商品分类下拉框
     * @param classifyId
     * @return
     * @Author ywq
     * @Date 2020-03-26
     */
    List<GoodsClassify> listGoodsClassify(@Param("classifyId") String classifyId);

    /**
     * 统计书号数量
     * @param goods 商品信息
     * @return
     * @Author ywq
     * @Date 2020-03-26
     */
    int countIsbn(Goods goods);

    /**
     * 新增商品
     * @param goods
     * @return
     * @Author ywq
     * @Date 2020-03-26
     */
    int addGoods(Goods goods);

    /**
     * 查询商品详情
     * @param goodsId
     * @return
     * @Author ywq
     * @Date 2020-03-26
     */
    Goods getGoods(@Param("goodsId") String goodsId);

    /**
     * 分页查询商品
     * @param goods
     * @return
     * @Author ywq
     * @Date 2020-03-26
     */
    List<Goods> listGoodsByPage(Goods goods);

    /**
     * 修改商品信息
     * @param goods
     * @return
     * @Author ywq
     * @Date 2020-03-26
     */
    int updateGoods(Goods goods);

    /**
     * 修改商品信息状态
     * @param listUpdate
     * @return
     * @Author ywq
     * @Date 2020-03-26
     */
    int updateGoodsState(@Param(value = "listUpdate") List<Goods> listUpdate);

    /**
     * 查看选中的商品是否在订单中
     * @param listGoodsId
     * @return
     * @Author ywq
     * @Date 2020-03-26
     */
    List<String> goodsIdInOrder(@Param("listGoodsId") List<String> listGoodsId);

    /**
     * 删除商品
     * @param listGoodsId
     * @param updateUser
     * @return
     * @Author ywq
     * @Date 2020-03-26
     */
    int deleteGoods(@Param("listGoodsId") List<String> listGoodsId, @Param("updateUser") String updateUser);
}
