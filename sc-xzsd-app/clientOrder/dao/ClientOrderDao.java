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
     */
    List<Integer> getGoodsInventory(@Param("goodsIdList") List<String> goodsIdList);

    /**
     * 更新商品库存
     * @param updateGoods
     * @return
     */
    int updateGoodsInventory(@Param(value = "updateGoods") List<ClientGoodsInfo> updateGoods);

    /**
     * 添加订单接口
     * @param clientOrderDeepenList
     * @param clientOrder
     * @return
     */
    int addOrder(@Param(value = "clientOrderDeepenList") List<ClientOrderDeepen> clientOrderDeepenList,
                 @Param("clientOrder")ClientOrder clientOrder);

    /**
     * 查询订单列表接口
     * @param userId
     * @param orderStateId
     * @return
     */
    List<ClientOrderVO> listOrderByPage(@Param("userId")String userId,@Param("orderStateId")String orderStateId);

    /**
     * 查询订单商品列表接口
     * @param orderId
     * @param orderStateId
     * @return
     */
    List<ClientOrderGoodsVO> listOrderGoodsVO(@Param("orderId")List<String> orderId,@Param("orderStateId")String orderStateId);

    /**
     * 更新订单状态接口
     * @param orderId
     * @param orderStateId
     * @param updateUser
     * @param version
     * @return
     */
    int updateOrderState(@Param("orderId") String orderId,@Param("orderStateId") String orderStateId,
                         @Param("updateUser")String updateUser,@Param("version")String version);

    /**
     * 查询订单详情接口
     * @param orderId
     * @return
     */
    List<ClientOrderDeepenVO> listOrderDeepen(@Param("orderId")String orderId);

    /**
     * 查询订单评价商品信息列表接口
     * @param orderId
     * @return
     */
    List<ClientOrderGoods> listGoodsForEvaluate(@Param("orderId")String orderId);

    /**
     * 新增订单商品评价接口
     * @param evaluateList
     * @return
     */
    int addGoodsEvaluate(@Param("evaluateList") List<EvaluateInfo> evaluateList);

    /**
     * 更新评价成功相关信息
     * @param orderId
     * @param orderStateId
     * @param updateUser
     * @param goodsIds
     * @return
     */
    int updateEvaluateState(@Param("orderId") String orderId,@Param("orderStateId") String orderStateId,
                            @Param("updateUser")String updateUser,@Param("goodsIds")List<String> goodsIds);
}
