package com.xzsd.pc.hotGoods.controller;

import com.neusoft.core.restful.AppResponse;
import com.neusoft.security.client.utils.SecurityUtils;
import com.xzsd.pc.goods.entity.Goods;
import com.xzsd.pc.hotGoods.entity.HotGoods;
import com.xzsd.pc.hotGoods.service.HotGoodsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @ClassName HotGoodsController
 * @Deprecation 热门商品管理
 * @Author ywq
 * @Date 2020-04-11
 */
@RestController
@RequestMapping("/hotGoods")
public class HotGoodsController {

    private static final Logger logger = LoggerFactory.getLogger(HotGoodsController.class);

    @Resource
    private HotGoodsService hotGoodsService;

    /**
     * 添加热门商品
     * @param hotGoods
     * @return
     * @Author ywq
     * @Date 2020-04-11
     */
    @PostMapping("addHotGoods")
    public AppResponse addHotGoods(HotGoods hotGoods){
        try{
            return hotGoodsService.addHotGoods(hotGoods);
        }catch (Exception e){
            logger.error("添加热门商品失败",e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 获取热门商品详情
     * @param hotGoodsId
     * @return
     * @Author ywq
     * @Date 2020-04-12
     */
    @PostMapping("getHotGoods")
    public AppResponse getHotGoods(String hotGoodsId){
        try{
            return hotGoodsService.getHotGoods(hotGoodsId);
        }catch (Exception e){
            logger.error("获取用户信息失败",e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 分页查询热门商品信息
     * @param goodsId
     * @param goodsName
     * @return
     * @Author ywq
     * @Date 2020-04-12
     */
    @PostMapping("listHotGoods")
    public AppResponse listHotGoods(String goodsId,String goodsName){
        try{
            return hotGoodsService.listHotGoods(goodsId,goodsName);
        }catch (Exception e){
            logger.error("分页查询热门商品信息失败",e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 更新热门商品信息
     * @param hotGoods
     * @return
     * @Author ywq
     * @Date 2020-04-1
     */
    @PostMapping("updateHotGoods")
    public AppResponse updateHotGoods(HotGoods hotGoods){
        try{
            return hotGoodsService.updateHotGoods(hotGoods);
        }catch (Exception e){
            logger.error("更新热门商品信息失败",e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 查询热门商品展示数量
     * @return
     * @Author ywq
     * @Date 2020-04-1
     */
    @PostMapping("getHotGoodsShowNum")
    public AppResponse getHotGoodsShowNum(){
        try{
            return hotGoodsService.getHotGoodsShowNum();
        }catch (Exception e){
            logger.error("查询热门商品展示数量失败",e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 修改热门商品展示数量
     * @return
     * @Author ywq
     * @Date 2020-04-1
     */
    @PostMapping("updateHotGoodsShowNum")
    public AppResponse updateHotGoodsShowNum(String hotGoodsShowNum,String version){
        try{
            return hotGoodsService.updateHotGoodsShowNum(hotGoodsShowNum,version);
        }catch (Exception e){
            logger.error("修改热门商品展示数量失败",e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 删除热门商品
     * @return
     * @Author ywq
     * @Date 2020-04-1
     */
    @PostMapping("deleteHotGoods")
    public AppResponse deleteHotGoods(String hotGoodsId){
        try{
            return hotGoodsService.deleteHotGoods(hotGoodsId);
        }catch (Exception e){
            logger.error("删除热门商品数量失败",e);
            System.out.println(e.toString());
            throw e;
        }
    }
}
