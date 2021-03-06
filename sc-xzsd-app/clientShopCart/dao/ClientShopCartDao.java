package com.xzsd.app.clientShopCart.dao;

import com.xzsd.app.clientShopCart.entity.ClientShopCart;
import com.xzsd.app.clientShopCart.entity.ClientShopCartVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName ClientShopCartDao
 * @Deripition app客户端购物车接口类
 * @Author ywq
 * @Date 2020-04-18
 */
@Mapper
public interface ClientShopCartDao {

    /**
     * 统计该商品是否在购物车中
     * @param goodsId
     * @return
     * @Author ywq
     * @Date 2020-04-18
     */
    ClientShopCart isGoodsInShopCart(@Param("goodsId") String goodsId,@Param("nowLogin")String nowLogin);

    /**
     * 新增购物车接口
     * @param clientShopCart
     * @return
     * @Author ywq
     * @Date 2020-04-18
     */
    int addShoppingCart(ClientShopCart clientShopCart);

    /**
     * 查询购物车接口
     * @param userId
     * @return
     * @Author ywq
     * @Date 2020-04-18
     */
    List<ClientShopCartVO> listShoppingCartsByPage(@Param("userId") String userId);

    /**
     * 修改购物车(商品数量)接口
     * @param clientShopCart
     * @return
     * @Author ywq
     * @Date 2020-04-18
     */
    int updateShoppingCart(ClientShopCart clientShopCart);

    /**
     * 删除购物车接口
     * @param shopCartIdList
     * @param updateUser
     * @return
     * @Author ywq
     * @Date 2020-04-18
     */
    int deleteShoppingCart(@Param("shopCartIdList") List<String> shopCartIdList,@Param("updateUser") String updateUser);
}
