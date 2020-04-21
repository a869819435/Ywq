package com.xzsd.app.driverHome.controller;

import com.neusoft.core.restful.AppResponse;
import com.xzsd.app.driverHome.service.DriverHomeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @ClassName DriverHomeController
 * @Deripition app司机端负责的门店信息
 * @Author ywq
 * @Date 2020-04-19
 */
@RestController
@RequestMapping("/driverHome")
public class DriverHomeController {

    private static final Logger logger = LoggerFactory.getLogger(DriverHomeController.class);

    @Resource
    private DriverHomeService driverHomeService;

    /**
     * 查询司机负责的门店信息
     * @return
     * @Author ywq
     * @Date 2020-04-20
     */
    @PostMapping("listDriverStores")
    public AppResponse listDriverStores(){
        try{
            return driverHomeService.listDriverStores();
        }catch (Exception e){
            logger.error("查询司机负责的门店信息接口失败",e);
            System.out.println(e.toString());
            throw e;
        }
    }
}
