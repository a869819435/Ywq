package com.xzsd.pc.order.service;

import com.neusoft.core.restful.AppResponse;
import com.neusoft.security.client.utils.SecurityUtils;
import com.xzsd.pc.order.dao.OrderDao;
import com.xzsd.pc.order.entity.Order;
import com.xzsd.pc.order.entity.OrderDeepen;
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
        List<OrderDeepen> orderDeepenList = orderDao.getListOrder(orderId);
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
        List<Order> updateOrderList = new ArrayList<>();
        //获取订单状态
        String orderStateId = order.getOrderStateId();
        //获取修改人:当前登录人的id
        String updateUser = SecurityUtils.getCurrentUserId();
        //将所有涉及修改的信息放入一个list里
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
}
