package com.xzsd.pc.goodsClassify.controller;


import com.neusoft.core.restful.AppResponse;
import com.neusoft.security.client.utils.SecurityUtils;
import com.xzsd.pc.goodsClassify.entity.GoodsClassify;
import com.xzsd.pc.goodsClassify.service.GoodsClassifyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;


/**
 * 商品分类管理
 * @ClassName GoodsClassifyController
 * @Description goods of classify
 * @Author ywq
 * @Date 2020-03-29
 */
@RestController
@RequestMapping("/goodsClassify")
public class GoodsClassifyController {

    private static final Logger logger = LoggerFactory.getLogger(GoodsClassifyController.class);

    @Resource
    private GoodsClassifyService goodsClassifyService;

    /**
     * 添加商品分类
     * @param goodsClassify
     * @return
     * @Author ywq
     * @Date 2020-03-29
     */
    @PostMapping("addGoodsClassify")
    public AppResponse addGoodsClassify(GoodsClassify goodsClassify){
        try{
            return goodsClassifyService.addGoodsClassify(goodsClassify);
        }catch (Exception e){
            logger.error("添加分类失败",e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 获取分类详情
     * @param classifyId
     * @return
     * @Author ywq
     * @Date 2020-03-29
     */
    @PostMapping("getGoodsClassify")
    public AppResponse getGoodsClassify(String classifyId){
        try{
            return goodsClassifyService.getGoodsClassify(classifyId);
        }catch (Exception e){
            logger.error("获取详情失败",e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 获取全部分类信息(未检查)
     * @return
     * @Author ywq
     * @Date 2020-03-29
     */
    @PostMapping("listAllGoodsClassify")
    public AppResponse listAllGoodsClassify(){
        try{
            return AppResponse.success("获取全部分类信息成功！",goodsClassifyService.listAllGoodsClassify());
        }catch (Exception e){
            logger.error("获取全部分类失败",e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 修改商品分类信息
     * @param goodsClassify
     * @return
     * @Author ywq
     * @Date 2020-03-29
     */
    @PostMapping("updateGoodsClassify")
    public AppResponse updateGoodsClassify(GoodsClassify goodsClassify){
        try{
            return goodsClassifyService.updateGoodsClassify(goodsClassify);
        }catch (Exception e){
            logger.error("修改商品分类信息失败",e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 删除商品分类信息
     * @param classifyId
     * @return
     * @Author ywq
     * @Date 2020-03-29
     */
    @PostMapping("deleteGoodsClassify")
    public AppResponse deleteGoodsClassify(String classifyId){
        try{
            return goodsClassifyService.deleteGoodsClassify(classifyId);
        }catch (Exception e){
            logger.error("删除商品分类信息失败",e);
            System.out.println(e.toString());
            throw e;
        }
    }
}
