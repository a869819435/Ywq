package com.xzsd.app.clientOrder.dao;

import com.xzsd.app.clientGoods.entity.ClientGoodsInfo;
import com.xzsd.app.clientOrder.entity.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName ClientOrderDao
 * @Deripition app客户端订单信息接口类
 * @Author ywq
 * @Date 2020-04-18
 */
@Mapper
public interface ClientOrderDao {
    /**
     * 获取选中的商品的库存
     * @param goodsIdList
     * @return
     * @Author ywq
     * @Date 2020-04-19
     */
    List<ClientGoodsInfo> getGoodsInventory(@Param("goodsIdList") List<String> goodsIdList);

    /**
     * 更新商品库存
     * @param updateGoods
     * @return
     * @Author ywq
     * @Date 2020-04-19
     */
    int updateGoodsInventory(@Param(value = "updateGoods") List<ClientGoodsInfo> updateGoods);

    /**
     * 添加订单接口
     * @param clientOrderDeepenList
     * @param clientOrder
     * @return
     * @Author ywq
     * @Date 2020-04-19
     */
    int addOrder(@Param(value = "clientOrderDeepenList") List<ClientOrderDeepen> clientOrderDeepenList,
                 @Param("clientOrder")ClientOrder clientOrder);

    /**
     * 查询订单列表接口
     * @param userId
     * @param orderStateId
     * @return
     * @Author ywq
     * @Date 2020-04-19
     */
    List<ClientOrderVO> listOrderByPage(@Param("userId")String userId,@Param("orderStateId")String orderStateId);

    /**
     * 查询订单商品列表接口
     * @param orderId
     * @param orderStateId
     * @return
     * @Author ywq
     * @Date 2020-04-19
     */
    List<ClientOrderGoodsVO> listOrderGoodsVO(@Param("orderId")List<String> orderId,@Param("orderStateId")String orderStateId);

    /**
     * 获取订单里的商品信息
     * @param orderId
     * @return
     * @Author ywq
     * @Date 2020-04-19
     */
    List<OrderGoods> getOrderGoods(@Param("orderId")String orderId);

    /**
     * 更新商品库存
     * @param orderGoodsList
     * @return
     * @Author ywq
     * @Date 2020-04-19
     */
    int addGoodsInventory(@Param(value = "orderGoodsList") List<OrderGoods> orderGoodsList);

    /**
     * 更新商品库存
     * @param orderGoodsList
     * @return
     * @Author ywq
     * @Date 2020-04-19
     */
    int updateGoodsSales(@Param(value = "orderGoodsList") List<OrderGoods> orderGoodsList);

    /**
     * 更新订单状态接口
     * @param orderId
     * @param orderStateId
     * @param updateUser
     * @param version
     * @return
     * @Author ywq
     * @Date 2020-04-19
     */
    int updateOrderState(@Param("orderId") String orderId,@Param("orderStateId") String orderStateId,
                         @Param("updateUser")String updateUser,@Param("version")String version);

    /**
     * 查询订单详情接口
     * @param orderId
     * @return
     * @Author ywq
     * @Date 2020-04-19
     */
    List<ClientOrderDeepenVO> listOrderDeepen(@Param("orderId")String orderId);

    /**
     * 查询订单评价商品信息列表接口
     * @param orderId
     * @return
     * @Author ywq
     * @Date 2020-04-19
     */
    List<ClientOrderGoods> listGoodsForEvaluate(@Param("orderId")String orderId);

    /**
     * 新增订单商品评价接口
     * @param evaluateList
     * @return
     * @Author ywq
     * @Date 2020-04-19
     */
    int addGoodsEvaluate(@Param("evaluateList") List<EvaluateInfo> evaluateList);

    /**
     * 更新评价成功相关信息
     * @param orderId
     * @param evaluateInfo
     * @param orderStateId
     * @param updateUser
     * @return
     * @Author ywq
     * @Date 2020-04-19
     */
    int updateEvaluateState(@Param("orderId") String orderId,
                            @Param("orderStateId") String orderStateId,
                            @Param("updateUser") String updateUser,
                            @Param("evaluateInfo") List<EvaluateInfo> evaluateInfo);
}
