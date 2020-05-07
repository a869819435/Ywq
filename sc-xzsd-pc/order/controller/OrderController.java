package com.xzsd.pc.order.controller;

import com.neusoft.core.restful.AppResponse;
import com.neusoft.security.client.utils.SecurityUtils;
import com.xzsd.pc.order.entity.Order;
import com.xzsd.pc.order.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @ClassName OrderController
 * @Derecation 订单管理控制类
 * @Author ywq
 * @Date 2020-04-10
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Resource
    private OrderService orderService;
    /**
     * 查询订单详情参数控制
     * @param orderId
     * @return
     * @Author ywq
     * @Date 2020-04-10
     */
    @PostMapping("getListOrder")
    public AppResponse getListOrder(String orderId){
        try{
            return orderService.getListOrder(orderId);
        }catch (Exception e){
            logger.error("查询订单详情失败",e);
            System.out.println(e.toString());
            throw e;
        }
    }
    /**
     * 分页查询订单参数控制
     * @param order
     * @return
     * @Author ywq
     * @Date 2020-04-10
     */
    @PostMapping("listOrders")
    public AppResponse listOrders(Order order,String role,String payTimeStart,String payTimeEnd){
        try{
            return orderService.listOrders(order,role,payTimeStart,payTimeEnd);
        }catch (Exception e){
            logger.error("分页查询订单失败",e);
            System.out.println(e.toString());
            throw e;
        }
    }
    /**
     * 修改订单状态参数控制
     * @param order
     * @return
     * @Author ywq
     * @Date 2020-04-10
     */
    @PostMapping("updateOrderState")
    public AppResponse updateOrderState(Order order,String nowRole){
        try{
            return orderService.updateOrderState(order,nowRole);
        }catch (Exception e){
            logger.error("修改订单状态失败",e);
            System.out.println(e.toString());
            throw e;
        }
    }
}
