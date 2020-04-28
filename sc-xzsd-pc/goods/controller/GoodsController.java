package com.xzsd.pc.goods.controller;


import com.neusoft.core.restful.AppResponse;
import com.neusoft.security.client.utils.SecurityUtils;
import com.xzsd.pc.goods.entity.Goods;
import com.xzsd.pc.goods.service.GoodsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 商品管理
 * @ClassName GoodsController
 * @Description GoodsManger
 * @Author ywq
 * @Date 2020-03-26
 */
@RestController
@RequestMapping("/goods")
public class GoodsController {

    private static final Logger logger = LoggerFactory.getLogger(GoodsController.class);

    @Resource
    private GoodsService goodsService;

    /**
     * goods 商品分类查询
     * @param classifyId
     * @return
     * @Author ywq
     * @Date 2020-03-26
     */
    @PostMapping("listGoodsClassify")
    public AppResponse listGoodsClassify(String classifyId){
        try{
            return goodsService.listGoodsClassify(classifyId);
        }catch (Exception e){
            logger.error("查询商品分类错误",e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * goods 新增商品
     * @param goods
     * @return AppResponse
     * @Author ywq
     * @Date 2020-03-26
     */
    @PostMapping("addGoods")
    public AppResponse addGoods(Goods goods, String imagePath){
        try{
            //获取商品图片路径
            goods.setGoodsImagePath(imagePath);
            AppResponse appResponse  = goodsService.addGoods(goods);
            return appResponse;
        }catch (Exception e){
            logger.error("商品新增失败",e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 获取商品详情
     * @param goodsId
     * @return
     * @Author ywq
     * @Date 2020-03-26
     */
    @PostMapping(value = "getGoods")
    public AppResponse getGoods(String goodsId){
        try{
            return goodsService.getGoods(goodsId);
        }catch (Exception e){
            logger.error("查询商品详情失败",e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 分页查询商品信息
     * @param goods
     * @return
     * @Author ywq
     * @Date 2020-03-26
     */
    @PostMapping(value = "listGoods")
    public AppResponse listGoods(Goods goods){
        try{
            return goodsService.listGoods(goods);
        }catch (Exception e){
            logger.error("分页查询商品失败",e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 修改商品信息
     * @param goods
     * @return
     * @Author ywq
     * @Date 2020-03-26
     */
    @PostMapping("updateGoods")
    public AppResponse updateGoods(Goods goods,String imagePath){
        try{
            //获取商品图片
            goods.setGoodsImagePath(imagePath);
            return goodsService.updateGoods(goods);
        }catch (Exception e){
            logger.error("修改商品信息失败",e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 修改商品状态
     * @param goods
     * @return
     * @Author ywq
     * @Date 2020-03-26
     */
    @PostMapping("updateGoodsShelfState")
    public AppResponse updateGoodsState(Goods goods,String goodsInventory){
        try{
            return goodsService.updateGoodsState(goods,goodsInventory);
        }catch (Exception e){
            logger.error("修改商品状态失败",e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 删除商品
     * @param goodsId
     * @return
     * @Author ywq
     * @Date 2020-03-26
     */
    @PostMapping("deleteGoods")
    public AppResponse deleteGoods(String goodsId){
        try{
            return goodsService.deleteGoods(goodsId);
        }catch (Exception e){
            logger.error("删除商品失败",e);
            System.out.println(e.toString());
            throw e;
        }
    }
}
