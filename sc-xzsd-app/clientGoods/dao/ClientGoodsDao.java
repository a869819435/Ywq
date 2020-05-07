package com.xzsd.app.clientGoods.dao;

import com.xzsd.app.clientGoods.entity.ClientGoodsClassify;
import com.xzsd.app.clientGoods.entity.ClientGoodsEvaluates;
import com.xzsd.app.clientGoods.entity.ClientGoodsEvaluatesVO;
import com.xzsd.app.clientGoods.entity.ClientGoodsInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName ClientGoodsDao
 * @Deripition app客户客户端商品信息接口类
 * @Author ywq
 * @Date 2020-04-17
 */
@Mapper
public interface ClientGoodsDao {

    /**
     * 查询商品信息详情接口
     * @param goodsId
     * @return
     * @Author ywq
     * @Date 2020-04-17
     */
    ClientGoodsInfo getGoods(@Param("goodsId") String goodsId);

    /**
     * 更新商品浏览量
     * @param goodsId
     * @return
     * @Author ywq
     * @Date 2020-04-17
     */
    int updateGoodsViews(@Param("goodsId") String goodsId);

    /**
     * 查询商品评价列表接口
     * @param clientGoodsEvaluates
     * @return
     * @Author ywq
     * @Date 2020-04-17
     */
    List<ClientGoodsEvaluatesVO> listGoodsEvaluatesByPage(ClientGoodsEvaluates clientGoodsEvaluates);

    /**
     * 查询商品对应的评价图片列表接口
     * @param evaluateIds
     * @param evaluateScore
     * @return
     * @Author ywq
     * @Date 2020-04-17
     */
    List<ClientGoodsEvaluates> listImagePath(@Param("evaluateIds") List<String> evaluateIds,@Param("evaluateScore") int evaluateScore);

    /**
     * 查询一级商品分类列接口
     * @return
     * @Author ywq
     * @Date 2020-04-17
     */
    List<ClientGoodsClassify> listOneGoodsClassify();

    /**
     * 查询二级商品分类以及商品接口
     * @param classifyId
     * @return
     * @Author ywq
     * @Date 2020-04-17
     */
    List<ClientGoodsClassify> listGetClassGoods(@Param("classifyId") String classifyId);
}
