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
     */
    int countGoodsClassify(GoodsClassify goodsClassify);

    /**
     * 添加商品分类
     * @param goodsClassify
     * @return
     */
    int addGoodsClassify(GoodsClassify goodsClassify);

    /**
     * 查询分类详情
     * @param classifyId
     * @return
     */
    GoodsClassify getGoodsClassify(@Param("classifyId") String classifyId);

    /**
     * 查询一二级商品分类
     * @return
     */
    List<GoodsClassify> listClassify();
//    List<GoodsClassifyVO> listClassify();

    /**
     * 修改商品分类
     * @param goodsClassify
     * @return
     */
    int updateGoodsClassify(GoodsClassify goodsClassify);

    /**
     * 查询该商品分类是否有子分类
     * @param classifyId
     * @return
     */
    int countNextClassify(@Param("classifyId") String classifyId);

    /**
     * 删除商品分类
     * @param classifyId
     * @param updateUser
     * @return
     */
    int deleteGoodsClassify(@Param("classifyId") String classifyId, @Param("updateUser") String updateUser);

}
