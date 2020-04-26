package com.xzsd.pc.order.service;

import com.neusoft.core.restful.AppResponse;
import com.neusoft.security.client.utils.SecurityUtils;
import com.xzsd.pc.order.dao.OrderDao;
import com.xzsd.pc.order.entity.Order;
import com.xzsd.pc.order.entity.OrderDeepen;
import com.xzsd.pc.order.entity.OrderDeepenList;
import com.xzsd.pc.order.entity.OrderGoods;
import com.xzsd.pc.order.enums.OrderStateEnums;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.neusoft.core.page.PageUtils.getPageInfo;

/**
 * @ClassName OrderService
 * @Derecation 订单管理实现类
 * @Author ywq
 * @Date 2020-04-10
 */
@Service
public class OrderService {

    @Resource
    private OrderDao orderDao;
    /**
     * 查询订单详情实现
     * @param orderId
     * @return
     */
    public AppResponse getListOrder(String orderId){
        OrderDeepenList orderDeepenList = new OrderDeepenList();
        orderDeepenList.setOrderDeepenList(orderDao.getListOrder(orderId));
        return AppResponse.success("查询详情成功！",orderDeepenList);
    }
    /**
     * 分页查询订单实现
     * @param order
     * @return
     */
    public AppResponse listOrders(Order order, String role, String payTimeStart, String payTimeEnd){
        String nowLogin = SecurityUtils.getCurrentUserId();
        List<Order> orderList = orderDao.listOrdersByPage(order,role,nowLogin,payTimeStart,payTimeEnd);
        return AppResponse.success("分页查询订单成功",getPageInfo(orderList));
    }
    /**
     * 修改订单状态实现
     * @param order
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse updateOrderState(Order order){
        //处理订单编号信息
        List<String> orders = Arrays.asList(order.getOrderId().split(","));
        //处理版本号信息
        List<String> versions = Arrays.asList(order.getVersion().split(","));
        //获取订单状态
        String orderStateId = order.getOrderStateId();
        //获取修改人:当前登录人的id
        String updateUser = SecurityUtils.getCurrentUserId();
        //处理订单状态修改涉及的商品信息修改(商品销量、商品库存)
        String error = solveOrderGoodsChange(orders,orderStateId,updateUser);
        if ( error != null ) {
            return AppResponse.versionError(error);
        }
        //将所有涉及修改的订单状态信息放入一个list里
        List<Order> updateOrderList = new ArrayList<>();
        for (int i = 0 ; i < orders.size() ; i++ ) {
            Order order1 = new Order();
            order1.setOrderId(orders.get(i));
            order1.setVersion(versions.get(i));
            order1.setOrderStateId(orderStateId);
            order1.setUpdateUser(updateUser);
            updateOrderList.add(order1);
        }
        int count = orderDao.updateOrderState(updateOrderList);
        if(count == 0){
            return AppResponse.versionError("修改失败");
        }
        return AppResponse.success("修改成功");
    }

    /**
     * 处理订单状态修改涉及的商品信息修改
     * @param orders
     * @param orderStateId
     * @param updateUser
     * @return
     */
    private String solveOrderGoodsChange(List<String> orders,String orderStateId,String updateUser){
        //所选订单中当前状态
        List<OrderGoods> orderGoods = orderDao.getOrderGoods(orders);
        //存储需要处理库存的订单里的商品信息
        List<OrderGoods> goodsForInventory = new ArrayList<>();
        //存储状态为已完成的订单里的商品信息
        List<OrderGoods> goodsForSales = new ArrayList<>();
        //商品库存处理方式标记
        boolean flag = true;
        //当要改变的状态为已取消时,需要将订单内的商品库存加回去
        if (OrderStateEnums.DELETED.getType().equals(orderStateId)){
            for (int i = 0 ; i < orderGoods.size() ; i++ ){
                OrderGoods temp = orderGoods.get(i);
                //获取订单状态
                String oldOrderState = temp.getOrderStateId();
                //赋值更新者
                temp.setUpdateUser(updateUser);
                //排除重复取消,防止库存重复增加
                if (oldOrderState.equals(orderStateId)){
                    continue;
                }else {
                    goodsForInventory.add(temp);
                }
                //当被改变的订单状态时已完成的(4,5)，需要销量减少
                if (oldOrderState.compareTo(OrderStateEnums.FINISHED_NO.getType()) >= 0 ){
                    goodsForSales.add(temp);
                }
            }
        }else{
            //当要改变的状态不为已取消时,需要将订单为已取消的订单选出来处理库存
            for (int i = 0 ; i < orderGoods.size() ; i++ ){
                OrderGoods temp = orderGoods.get(i);
                //获取订单状态
                String oldOrderState = temp.getOrderStateId();
                //赋值更新者
                temp.setUpdateUser(updateUser);
                //获取被改变的订单状态为取消订单的
                if (oldOrderState.equals(OrderStateEnums.DELETED.getType())){
                    goodsForInventory.add(temp);
                }
                //当被改变的订单状态时已完成的(4,5)，需要销量减少
                if (oldOrderState.compareTo(OrderStateEnums.FINISHED_NO.getType()) >= 0 ){
                    goodsForSales.add(temp);
                }
            }
            flag = false;
        }
        //存在需要处理商品库存的订单编号
        if (goodsForInventory != null && goodsForInventory.size() != 0){
            String error = solveGoodsInventory(goodsForInventory,flag,updateUser);
            if ( error != null ){
                return error;
            }
        }
        //有需要改变销售量的商品
        if (goodsForSales != null && goodsForSales.size() != 0 ){
            //更新商品销量
            int countUpdateGoodsSales = orderDao.updateOrderGoodsSales(goodsForSales);
            if (countUpdateGoodsSales == 0){
                return "修改订单状态失败,请重试。";
            }
        }
        return null;
    }

    /**
     * 处理订单中的商品库存
     * @param goodsForInventory
     * @param flag
     * @return
     */
    private String solveGoodsInventory(List<OrderGoods> goodsForInventory,boolean flag,String updateUser){
        for (OrderGoods i : goodsForInventory ){
            if(flag == false){
                //校验商品库存是否足够
                int ans = i.getGoodsInventory() - i.getGoodsCount();
                if ( ans < 0 ){
                    return "订单编号为" + i.getOrderId() + "订单中库存不足的商品" + i.getGoodsId() +
                            ",导致无法将订单状态从已取消修改成其他状态";
                }
                i.setGoodsInventory(ans);
            }else {
                i.setGoodsInventory(i.getGoodsCount() + i.getGoodsInventory());
            }
        }
        //更改库存
        int countUpdateGoods = orderDao.updateOrderGoodsInventory(goodsForInventory);
        //库存更改失败,返回错误
        if (countUpdateGoods == 0){
            return "修改订单状态失败,请重试。";
        }
        return null;
    }
}
