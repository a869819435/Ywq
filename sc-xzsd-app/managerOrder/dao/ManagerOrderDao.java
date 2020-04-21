package com.xzsd.app.managerOrder.dao;

import com.xzsd.app.managerOrder.entity.ManagerOrderDeepenVO;
import com.xzsd.app.managerOrder.entity.ManagerOrderGoodsVO;
import com.xzsd.app.managerOrder.entity.ManagerOrderVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName ManagerOrderDao
 * @Deripition app店长端店长订单信息接口类
 * @Author ywq
 * @Date 2020-04-19
 */
public interface ManagerOrderDao {

    /**
     * 查询订单列表接口
     * @param userId
     * @param orderStateId
     * @return
     */
    List<ManagerOrderVO> listManagerOrdersByPage(@Param("userId")String userId, @Param("orderStateId")String orderStateId);

    /**
     * 查询订单商品信息列表接口
     * @param orderIdList
     * @param orderStateId
     * @return
     */
    List<ManagerOrderGoodsVO> listManagerOrdersGoods(@Param("orderIdList")List<String> orderIdList, @Param("orderStateId")String orderStateId);

    /**
     * 更新订单状态接口
     * @param orderId
     * @param orderStateId
     * @param updateUser
     * @param version
     * @return
     */
    int updateManagerOrderState(@Param("orderId") String orderId,@Param("orderStateId") String orderStateId,
                                @Param("updateUser")String updateUser,@Param("version")String version);

    /**
     * 查询订单详情接口
     * @param orderId
     * @return
     */
    List<ManagerOrderDeepenVO> listManagerOrderDeepen(@Param("orderId")String orderId);
}
