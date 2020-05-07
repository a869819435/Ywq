package com.xzsd.pc.hotGoods.dao;

import com.xzsd.pc.hotGoods.entity.HotGoods;
import com.xzsd.pc.hotGoods.entity.HotGoodsShowNumVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.access.method.P;

import java.util.List;

/**
 * @ClassName HotGoodsDao
 * @Deprecation 热门商品接口类
 * @Author ywq
 * @Date 2020-04-11
 */
@Mapper
public interface HotGoodsDao {

    /**
     * 校验热门商品信息是否存在重复
     * @param hotGoods
     * @return
     * @Author ywq
     * @Date 2020-04-11
     */
    int countHotGoodsInfo(HotGoods hotGoods);

    /**
     * 处理添加的热门商品编号
     * @param hotGoodsNum
     * @param hotGoodsId
     * @return
     * @Author ywq
     * @Date 2020-04-11
     */
    int solveAddHotGoodsNum(@Param("hotGoodsNum") String hotGoodsNum, @Param("hotGoodsId") String hotGoodsId);

    /**
     * 添加热门商品
     * @param hotGoods
     * @return
     * @Author ywq
     * @Date 2020-04-11
     */
    int addHotGoods(HotGoods hotGoods);

    /**
     * 获取热门商品详情
     * @param hotGoodsId
     * @return
     * @Author ywq
     * @Date 2020-04-11
     */
    HotGoods getHotGoods(@Param("hotGoodsId") String hotGoodsId);

    /**
     * 分页查询热门商品信息
     * @param goodsId
     * @param goodsName
     * @return
     * @Author ywq
     * @Date 2020-04-11
     */
    List<HotGoods> listHotGoodsByPage(@Param("goodsId") String goodsId, @Param("goodsName") String goodsName);

    /**
     * 处理修改的热门商品编号
     * @param hotGoodsNum
     * @param oldHotGoodsNum
     * @param hotGoodsId
     * @return
     * @Author ywq
     * @Date 2020-04-11
     */
    int solveUpdateHotGoodsNum(@Param("hotGoodsNum") int hotGoodsNum, @Param("oldHotGoodsNum") int oldHotGoodsNum,@Param("hotGoodsId") String hotGoodsId);

    /**
     * 获取当前热门商品的序号
     * @param hotGoodsId
     * @return
     * @Author ywq
     * @Date 2020-04-11
     */
    int getHotGoodsNum(@Param("hotGoodsId") String hotGoodsId);

    /**
     * 修改热门商品信息
     * @param hotGoods
     * @return
     * @Author ywq
     * @Date 2020-04-11
     */
    int updateHotGoods(HotGoods hotGoods);

    /**
     * 查询热门商品展示数量
     * @return
     * @Author ywq
     * @Date 2020-04-11
     */
    HotGoodsShowNumVO getHotGoodsShowNum();

    /**
     * 更改热门商品展示数量
     * @param hotGoodsShowNum
     * @param version
     * @param updateUser
     * @return
     * @Author ywq
     * @Date 2020-04-11
     */
    int updateHotGoodsShowNum(@Param("hotGoodsShowNum") String hotGoodsShowNum,@Param("version") String version, @Param("updateUser") String updateUser);

    /**
     * 删除热门商品
     * @param hotGoodsIdList
     * @param updateUser
     * @return
     * @Author ywq
     * @Date 2020-04-11
     */
    int deleteHotGoods(@Param("hotGoodsIdList") List<String> hotGoodsIdList, @Param("updateUser") String updateUser);
}
