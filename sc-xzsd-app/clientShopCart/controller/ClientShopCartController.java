package com.xzsd.app.clientShopCart.controller;

import com.neusoft.core.restful.AppResponse;
import com.xzsd.app.clientShopCart.entity.ClientShopCart;
import com.xzsd.app.clientShopCart.service.ClientShopCartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @ClassName ClientShopCartController
 * @Deripition app客户端购物车管理
 * @Author ywq
 * @Date 2020-04-18
 */
@RestController
@RequestMapping("/clientShopCart")
public class ClientShopCartController {

    private static final Logger logger = LoggerFactory.getLogger(ClientShopCartController.class);
    
    @Resource
    private ClientShopCartService clientShopCartService;

    /**
     * 添加购物车
     * @param clientShopCart
     * @return
     * @Author ywq
     * @Date 2020-04-18
     */
    @PostMapping("addShoppingCart")
    public AppResponse addShoppingCart(ClientShopCart clientShopCart){
        try{
            return clientShopCartService.addShoppingCart(clientShopCart);
        }catch (Exception e){
            logger.error("添加购物车失败",e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 分页查询购物车列表
     * @return
     * @Author ywq
     * @Date 2020-04-18
     */
    @PostMapping("listShoppingCarts")
    public AppResponse listShoppingCarts(){
        try{
            return clientShopCartService.listShoppingCarts();
        }catch (Exception e){
            logger.error("查询购物车失败",e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 分页查询购物车列表
     * @param  clientShopCart
     * @return
     * @Author ywq
     * @Date 2020-04-18
     */
    @PostMapping("updateShoppingCart")
    public AppResponse updateShoppingCart(ClientShopCart clientShopCart){
        try{
            return clientShopCartService.updateShoppingCart(clientShopCart);
        }catch (Exception e){
            logger.error("修改购物车失败",e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 删除购物车商品实现
     * @param  shopCartId
     * @return
     * @Author ywq
     * @Date 2020-04-18
     */
    @PostMapping("deleteShoppingCart")
    public AppResponse deleteShoppingCart(String shopCartId){
        try{
            return clientShopCartService.deleteShoppingCart(shopCartId);
        }catch (Exception e){
            logger.error("删除购物车失败",e);
            System.out.println(e.toString());
            throw e;
        }
    }
}
