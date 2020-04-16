package com.xzsd.pc.slideshowHome.controller;

import com.neusoft.core.restful.AppResponse;
import com.neusoft.util.AuthUtils;
import com.xzsd.pc.slideshowHome.entity.SlideshowHome;
import com.xzsd.pc.slideshowHome.service.SlideshowHomeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @ClassName SlideshowHomeController
 * @Deprecation 首页轮播图控制类
 * @Author ywq
 * @Date 2020-04-05
 */
@RestController
@RequestMapping("/slideshowHome")
public class SlideshowHomeController {

    private static final Logger logger = LoggerFactory.getLogger(SlideshowHomeController.class);

    @Resource
    private SlideshowHomeService slideshowHomeService;

    /**
     * 新增首页轮播图
     * @param slideshowHome
     * @return
     * @Author ywq
     * @Date 2020-04-05
     */
    @PostMapping("addSlideshowHome")
    public AppResponse addSlideshowHome(SlideshowHome slideshowHome,String imagePath){
        try{
            //获取图片
            slideshowHome.setSlideshowPath(imagePath);
            AppResponse appResponse  = slideshowHomeService.addSlideshowHome(slideshowHome);
            return appResponse;
        }catch (Exception e){
            logger.error("首页轮播图新增失败",e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 分页首页轮播图信息
     * @param slideshowStateId
     * @return
     * @Author ywq
     * @Date 2020-04-05
     */
    @PostMapping("listSlideshowHome")
    public AppResponse listSlideshowHome(String slideshowStateId){
        try{
            return slideshowHomeService.listSlideshowHome(slideshowStateId);
        }catch (Exception e){
            logger.error("分页查询首页轮播图失败",e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 查询商品分页实现
     * @param goodsName
     * @param goodsId
     * @return
     * @Author ywq
     * @Date 2020-04-05
     */
    @PostMapping("listGoods")
    public AppResponse listGoods(String goodsName,String goodsId){
        try{
            return slideshowHomeService.listGoods(goodsName,goodsId);
        }catch (Exception e){
            logger.error("分页查询首页轮播图失败",e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 修改首页轮播图状态实现
     * @param slideshowId
     * @param slideshowStateId
     * @param version
     * @return
     * @Author ywq
     * @Date 2020-04-05
     */
    @PostMapping("updateSlideshowHomeState")
    public AppResponse updateSlideshowHomeState(String slideshowId,String slideshowStateId,String version){
        try{
            return slideshowHomeService.updateSlideshowHomeState(
                    slideshowId,slideshowStateId,version);
        }catch (Exception e){
            logger.error("修改首页轮播图状态失败",e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 删除首页轮播图
     * @param slideshowId
     * @return
     * @Author ywq
     * @Date 2020-04-05
     */
    @PostMapping("deleteSlideshowHome")
    public AppResponse deleteSlideshowHome(String slideshowId){
        try{
            return slideshowHomeService.deleteSlideshowHome(slideshowId);
        }catch (Exception e){
            logger.error("修改首页轮播图状态失败",e);
            System.out.println(e.toString());
            throw e;
        }
    }
}
