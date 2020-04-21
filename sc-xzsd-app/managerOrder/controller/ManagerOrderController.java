package com.xzsd.app.managerOrder.controller;

import com.neusoft.core.restful.AppResponse;
import com.xzsd.app.managerOrder.service.ManagerOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @ClassName ManagerOrderController
 * @Deripition app店长端店长订单信息
 * @Author ywq
 * @Date 2020-04-19
 */
@RestController
@RequestMapping("/managerOrder")
public class ManagerOrderController {

    private static final Logger logger = LoggerFactory.getLogger(ManagerOrderController.class);

    @Resource
    private ManagerOrderService managerOrderService;

    /**
     * 查询店长订单列表
     * @param orderStateId
     * @return
     * @Author ywq
     * @Date 2020-04-19
     */
    @PostMapping("listManagerOrders")
    public AppResponse listManagerOrders(String orderStateId){
        try{
            return managerOrderService.listManagerOrders(orderStateId);
        }catch (Exception e){
            logger.error("查询订单列表失败",e);
            System.out.println(e.toString());
            throw e;
        }
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
    @PostMapping("updateManagerOrderState")
    public AppResponse updateManagerOrderState(String orderId,String orderStateId,String version){
        try{
            return managerOrderService.updateManagerOrderState(orderId,orderStateId,version);
        }catch (Exception e){
            logger.error("更新订单状态失败",e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 查询店长订单详情
     * @param orderId
     * @return
     * @Author ywq
     * @Date 2020-04-19
     */
    @PostMapping("listManagerOrderDeepen")
    public AppResponse listManagerOrderDeepen(String orderId){
        try{
            return managerOrderService.listManagerOrderDeepen(orderId);
        }catch (Exception e){
            logger.error("查询订单详情失败",e);
            System.out.println(e.toString());
            throw e;
        }
    }

}
