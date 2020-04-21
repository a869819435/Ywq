package com.xzsd.app.clientGoods.controller;

import com.neusoft.core.restful.AppResponse;
import com.xzsd.app.clientGoods.entity.ClientGoodsEvaluates;
import com.xzsd.app.clientGoods.service.ClientGoodsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @ClassName ClientGoodsController
 * @Deripition app客户客户端商品信息
 * @Author ywq
 * @Date 2020-04-17
 */
@RestController
@RequestMapping("clientGoods")
public class ClientGoodsController {

    private static final Logger logger = LoggerFactory.getLogger(ClientGoodsController.class);

    @Resource
    private ClientGoodsService clientGoodsService;

    /**
     * 查询商品信息详情
     * @param goodsId
     * @return
     * @Author ywq
     * @Date 2020-04-17
     */
    @PostMapping("getGoods")
    public AppResponse getGoods(String goodsId){
        try{
            return clientGoodsService.getGoods(goodsId);
        }catch (Exception e){
            logger.error("查询商品详情失败",e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 查询商品评价列表
     * @param clientGoodsEvaluates
     * @return
     * @Author ywq
     * @Date 2020-04-17
     */
    @PostMapping("listGoodsEvaluates")
    public AppResponse listGoodsEvaluates(ClientGoodsEvaluates clientGoodsEvaluates){
        try{
            return clientGoodsService.listGoodsEvaluates(clientGoodsEvaluates);
        }catch (Exception e){
            logger.error("查询商品评价列表失败",e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 查询商品一级分类信息
     * @return
     * @Author ywq
     * @Date 2020-04-17
     */
    @PostMapping("listOneGoodsClassify")
    public AppResponse listOneGoodsClassify(){
        try{
            return clientGoodsService.listOneGoodsClassify();
        }catch (Exception e){
            logger.error("查询商品评价列表失败",e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 查询二级商品分类以及商品接口
     * @return
     * @Author ywq
     * @Date 2020-04-17
     */
    @PostMapping("listGetClassGoods")
    public AppResponse listGetClassGoods(String classifyId){
        try{
            return clientGoodsService.listGetClassGoods(classifyId);
        }catch (Exception e){
            logger.error("查询商品评价列表失败",e);
            System.out.println(e.toString());
            throw e;
        }
    }
}
