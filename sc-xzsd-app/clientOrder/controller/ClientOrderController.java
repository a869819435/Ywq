package com.xzsd.app.clientOrder.controller;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;
import com.neusoft.core.restful.AppResponse;
import com.xzsd.app.clientOrder.entity.ClientOrderGoods;
import com.xzsd.app.clientOrder.service.ClientOrderService;
import com.xzsd.app.clientShopCart.controller.ClientShopCartController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @ClassName ClientOrderController
 * @Deripition app客户端订单信息
 * @Author ywq
 * @Date 2020-04-19
 */
@RestController
@RequestMapping("/clientOrder")
public class ClientOrderController {

    private static final Logger logger = LoggerFactory.getLogger(ClientShopCartController.class);

    @Resource
    private ClientOrderService clientOrderService;

    /**
     * 添加订单
     * @param clientOrderGoods
     * @return
     * @Author ywq
     * @Date 2020-04-19
     */
    @PostMapping("addOrder")
    public AppResponse addOrder(ClientOrderGoods clientOrderGoods,String shopCartId){
        try{
            return clientOrderService.addOrder(clientOrderGoods,shopCartId);
        }catch (Exception e){
            logger.error("添加订单失败",e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 查询订单列表
     * @param orderStateId
     * @return
     * @Author ywq
     * @Date 2020-04-19
     */
    @PostMapping("listOrder")
    public AppResponse listOrder(String orderStateId){
        try{
            return clientOrderService.listOrder(orderStateId);
        }catch (Exception e){
            logger.error("查询订单列表失败",e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 更新订单状态
     * @param orderId
     * @param orderStateId
     * @param version
     * @return
     * @Author ywq
     * @Date 2020-04-19
     */
    @PostMapping("updateOrderState")
    public AppResponse updateOrderState(String orderId,String orderStateId,String version){
        try{
            return clientOrderService.updateOrderState(orderId,orderStateId,version);
        }catch (Exception e){
            logger.error("更新订单状态失败",e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 查询订单详情
     * @param orderId
     * @return
     * @Author ywq
     * @Date 2020-04-19
     */
    @PostMapping("listOrderDeepen")
    public AppResponse listOrderDeepen(String orderId){
        try{
            return clientOrderService.listOrderDeepen(orderId);
        }catch (Exception e){
            logger.error("查询订单详情失败",e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 查询订单评价商品信息列表
     * @param orderId
     * @return
     * @Author ywq
     * @Date 2020-04-19
     */
    @PostMapping("listGoodsForEvaluate")
    public AppResponse listGoodsForEvaluate(String orderId){
        try{
            return clientOrderService.listGoodsForEvaluate(orderId);
        }catch (Exception e){
            logger.error("查询订单评价商品信息列表失败",e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 新增订单商品评价
     * @param evaluateOrder
     * @return
     * @Author ywq
     * @Date 2020-04-19
     */
    @PostMapping("addGoodsEvaluate")
    public AppResponse addGoodsEvaluate(@RequestBody JSONObject evaluateOrder){
        try{
            return clientOrderService.addGoodsEvaluate(evaluateOrder);
        }catch (Exception e){
            logger.error("新增订单商品评价失败",e);
            System.out.println(e.toString());
            throw e;
        }
    }
}
