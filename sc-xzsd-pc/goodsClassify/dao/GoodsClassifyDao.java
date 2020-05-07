package com.xzsd.pc.goodsClassify.dao;

import com.xzsd.pc.goodsClassify.entity.GoodsClassify;
import com.xzsd.pc.goodsClassify.entity.GoodsClassifyVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 商品分类管理接口类
 * @ClassName GoodsClassifyDao
 * @Description Goods of classify
 * @Author ywq
 * @Date 2020-03-26
 */
@Mapper
public interface GoodsClassifyDao {
    /**
     * 查询商品分类是否存在
     * @param goodsClassify
     * @return
     * @Author ywq
     * @Date 2020-03-26
     */
    int countGoodsClassify(GoodsClassify goodsClassify);

    /**
     * 添加商品分类
     * @param goodsClassify
     * @return
     * @Author ywq
     * @Date 2020-03-26
     */
    int addGoodsClassify(GoodsClassify goodsClassify);

    /**
     * 查询分类详情
     * @param classifyId
     * @return
     * @Author ywq
     * @Date 2020-03-26
     */
    GoodsClassify getGoodsClassify(@Param("classifyId") String classifyId);

    /**
     * 查询一二级商品分类
     * @return
     * @Author ywq
     * @Date 2020-03-26
     */
    List<GoodsClassify> listClassify();

    /**
     * 修改商品分类
     * @param goodsClassify
     * @return
     * @Author ywq
     * @Date 2020-03-26
     */
    int updateGoodsClassify(GoodsClassify goodsClassify);

    /**
     * 查询该商品分类是否有商品，以及分类
     * @param classifyId
     * @param classifyParent
     * @return
     * @Author ywq
     * @Date 2020-03-26
     */
    int countNextInfo(@Param("classifyId") String classifyId,@Param("classifyParent") String classifyParent);

    /**
     * 删除商品分类
     * @param classifyId
     * @param updateUser
     * @return
     * @Author ywq
     * @Date 2020-03-26
     */
    int deleteGoodsClassify(@Param("classifyId") String classifyId, @Param("updateUser") String updateUser);
}
