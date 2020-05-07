package com.xzsd.app.managerOrder.service;

import com.neusoft.core.restful.AppResponse;
import com.neusoft.security.client.utils.SecurityUtils;
import com.xzsd.app.clientOrder.dao.ClientOrderDao;
import com.xzsd.app.clientOrder.entity.OrderGoods;
import com.xzsd.app.clientOrder.enums.OrderStateEnums;
import com.xzsd.app.managerOrder.dao.ManagerOrderDao;
import com.xzsd.app.managerOrder.entity.ManagerOrderData;
import com.xzsd.app.managerOrder.entity.ManagerOrderDeepenVO;
import com.xzsd.app.managerOrder.entity.ManagerOrderGoodsVO;
import com.xzsd.app.managerOrder.entity.ManagerOrderVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.neusoft.core.page.PageUtils.getPageInfo;

/**
 * @ClassName ManagerOrderService
 * @Deripition app店长端店长订单信息实现类
 * @Author ywq
 * @Date 2020-04-19
 */
@Service
public class ManagerOrderService {

    @Resource
    private ManagerOrderDao managerOrderDao;

    @Resource
    private ClientOrderDao clientOrderDao;

    /**
     * 查询店长订单列表
     * @param orderStateId
     * @return
     * @Author ywq
     * @Date 2020-04-19
     */
    public AppResponse listManagerOrders(String orderStateId){
        //获取当前登录人id
        String userId = SecurityUtils.getCurrentUserId();
        List<ManagerOrderVO> managerOrderVOList = managerOrderDao.listManagerOrdersByPage(userId,orderStateId);
        int sum = managerOrderVOList.size();
        if (managerOrderVOList == null || sum == 0) {
            return AppResponse.notFound("还没有客户下订单到您的店铺！");
        }
        //订单列表
        List<String> orderIdList = new ArrayList<>();
        //映射订单id
        Map<String,Integer> map = new HashMap<>();
        for (int i = 0 ; i < sum ; i++ ){
            String key = managerOrderVOList.get(i).getOrderId();
            //获取订单的id对应的value，初始化订单商品列表
            map.put(key,i );
            managerOrderVOList.get(i).setGoodsList(new ArrayList<>());
            orderIdList.add(key);
        }
        List<ManagerOrderGoodsVO> managerOrderGoodsVOList = managerOrderDao.listManagerOrdersGoods(orderIdList,orderStateId);
        //赋值订单列表里的商品信息
        for(ManagerOrderGoodsVO tmp : managerOrderGoodsVOList ){
            String key = tmp.getOrderId();
            int parent = map.get(key);
            managerOrderVOList.get(parent).getGoodsList().add(tmp);
            tmp.setOrderId(null);
        }
        return AppResponse.success("查询订单列表成功",getPageInfo(managerOrderVOList));
    }

    /**
     * 更新店长订单状态
     * @param orderId
     * @param orderStateId
     * @param version
     * @return
     * @Author ywq
     * @Date 2020-04-19
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse updateManagerOrderState(String orderId,String orderStateId,String version){
        //获取当前登录人id为修改人
        String updateUser = SecurityUtils.getCurrentUserId();
        //当订单要修改的状态为取消订单的时候涉及的商品信息更新
        if (OrderStateEnums.DELETED.getType().equals(orderStateId)){
            List<OrderGoods> orderGoodsList = clientOrderDao.getOrderGoods(orderId);
            for (OrderGoods i : orderGoodsList){
                i.setUpdateUser(updateUser);
            }
            int countGoods = 0;
            //当更改的状态为取消订单时,库存增加
            if ( OrderStateEnums.DELETED.getType().equals(orderStateId) ){
                countGoods = clientOrderDao.addGoodsInventory(orderGoodsList);
            }
            if (countGoods == 0){
                return AppResponse.versionError("操作失败，请重试");
            }
        }
        int count = managerOrderDao.updateManagerOrderState(orderId,orderStateId,updateUser,version);
        if (count == 0){
            return AppResponse.versionError("操作失败请重试");
        }
        return AppResponse.success("操作成功！");
    }

    /**
     * 查询店长订单详情
     * @param orderId
     * @return
     * @Author ywq
     * @Date 2020-04-19
     */
    public AppResponse listManagerOrderDeepen(String orderId){
        List<ManagerOrderDeepenVO> managerOrderDeepenVOList = managerOrderDao.listManagerOrderDeepen(orderId);
        if(managerOrderDeepenVOList == null){
            return AppResponse.notFound("获取订单详情失败");
        }
        //初始化订单详情信息列表
        ManagerOrderDeepenVO orderDeepen = getOrderDeepenInfo(managerOrderDeepenVOList);
        return AppResponse.success("获取订单详情成功！",orderDeepen);
    }

    /**
     * 对订单详情进行初始化和赋值
     * @param managerOrderDeepenVOList
     * @return
     * @Author ywq
     * @Date 2020-04-19
     */
    private ManagerOrderDeepenVO getOrderDeepenInfo(List<ManagerOrderDeepenVO> managerOrderDeepenVOList){
        //初始化订单详情信息列表
        ManagerOrderDeepenVO managerOrderDeepenVO = new ManagerOrderDeepenVO();
        ManagerOrderDeepenVO clientOrderDeepenGet = managerOrderDeepenVOList.get(0);
        //赋值订单信息
        managerOrderDeepenVO.setOrderId(clientOrderDeepenGet.getOrderId());
        managerOrderDeepenVO.setCrateTime(clientOrderDeepenGet.getCrateTime());
        managerOrderDeepenVO.setOrderStateId(clientOrderDeepenGet.getOrderStateId());
        managerOrderDeepenVO.setUserName(clientOrderDeepenGet.getUserName());
        managerOrderDeepenVO.setPhone(clientOrderDeepenGet.getPhone());
        managerOrderDeepenVO.setStoreName(clientOrderDeepenGet.getStoreName());
        managerOrderDeepenVO.setAddress(clientOrderDeepenGet.getAddress());
        managerOrderDeepenVO.setGoodsList(new ArrayList<>());
        managerOrderDeepenVO.setOrderAllCost(clientOrderDeepenGet.getOrderAllCost());
        managerOrderDeepenVO.setOrderAllGoodsCount(clientOrderDeepenGet.getOrderAllGoodsCount());
        //获取订单详情里的商品信息列表
        managerOrderDeepenVOList.forEach(i -> {
            ManagerOrderGoodsVO managerOrderGoods = new ManagerOrderGoodsVO();
            managerOrderGoods.setGoodsName(i.getGoodsName());
            managerOrderGoods.setGoodsPrice(i.getGoodsPrice());
            managerOrderGoods.setGoodsImagePath(i.getGoodsImagePath());
            managerOrderGoods.setCartGoodsCount(i.getCartGoodsCount());
            managerOrderGoods.setGoodsId(i.getGoodsId());
            managerOrderDeepenVO.getGoodsList().add(managerOrderGoods);
        });
        return managerOrderDeepenVO;
    }
}
