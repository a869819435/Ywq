package com.xzsd.pc.order.dao;

import com.xzsd.pc.order.entity.Order;
import com.xzsd.pc.order.entity.OrderDeepen;
import com.xzsd.pc.order.entity.OrderGoods;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName OrderDao
 * @Derecation 订单管理接口类
 * @Author ywq
 * @Date 2020-04-10
 */
@Mapper
public interface OrderDao {
    /**
     * 查询订单详情接口
     * @param orderId
     * @return
     */
    List<OrderDeepen> getListOrder(@Param("orderId") String orderId);

    /**
     * 分页查询订单接口
     * @param order
     * @param role
     * @param nowLogin
     * @param payTimeStart
     * @param payTimeEnd
     * @return
     */
    List<Order> listOrdersByPage(@Param("o") Order order, @Param("role") String role, @Param("nowLogin") String nowLogin,
                                 @Param("payTimeStart") String payTimeStart, @Param("payTimeEnd") String payTimeEnd);

//    /**
//     * 获取所选订单中当前状态
//     * @param orderIds
//     * @return
//     */
//    List<Order> getChosenOrder(@Param("orderIds") List<String> orderIds);

    /**
     * 获取所选订单中的商品信息
     * @param orderIds
     * @return
     */
    List<OrderGoods> getOrderGoods(@Param("orderIds") List<String> orderIds);

    /**
     * 更新所选订单中的需要更新的商品库存
     * @param goods
     * @return
     */
    int updateOrderGoodsInventory(@Param("goods") List<OrderGoods> goods);

    /**
     * 更新所选订单中的需要更新的商品销售量
     * @param goodsSales
     * @return
     */
    int updateOrderGoodsSales(@Param("goodsSales") List<OrderGoods> goodsSales);

    /**
     * 修改订单状态接口
     * @param updateOrderList
     * @return
     */
    int updateOrderState(@Param(value = "updateOrderList") List<Order> updateOrderList);
}
