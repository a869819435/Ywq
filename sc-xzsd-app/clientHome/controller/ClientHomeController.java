package com.xzsd.app.clientHome.controller;

import com.neusoft.core.restful.AppResponse;
import com.xzsd.app.clientHome.service.ClientHomeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @ClassName ClientHomeController
 * @Deripition app客户首页管理
 * @Author ywq
 * @Date 2020-04-17
 */
@RestController
@RequestMapping("/clientHome")
public class ClientHomeController {

    private static final Logger logger = LoggerFactory.getLogger(ClientHomeController.class);

    @Resource
    private ClientHomeService clientHomeService;

    /**
     * 获取首页轮播图信息
     * @return
     * @Author ywq
     * @Date 2020-04-17
     */
    @PostMapping("listRotationCharHome")
    public AppResponse listRotationCharHome(){
        try{
            return clientHomeService.listRotationCharHome();
        }catch (Exception e){
            logger.error("获取轮播图信息失败",e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 查询热门商品信息
     * @return
     * @Author ywq
     * @Date 2020-04-17
     */
    @PostMapping("listHotGoods")
    public AppResponse listHotGoods(){
        try{
            return clientHomeService.listHotGoods();
        }catch (Exception e){
            logger.error("获取轮播图信息失败",e);
            System.out.println(e.toString());
            throw e;
        }
    }
}
