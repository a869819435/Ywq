package com.xzsd.app.managerInformation.controller;

import com.neusoft.core.restful.AppResponse;
import com.xzsd.app.managerInformation.service.ManagerInformationService;
import com.xzsd.app.managerOrder.service.ManagerOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @ClassName ManagerInformationController
 * @Deripition app店长端店长信息
 * @Author ywq
 * @Date 2020-04-19
 */
@RestController
@RequestMapping("/managerInformation")
public class ManagerInformationController {

    private static final Logger logger = LoggerFactory.getLogger(ManagerInformationController.class);

    @Resource
    private ManagerInformationService managerInformationService;

    /**
     * 查询负责店长门店的司机信息
     * @return
     * @Author ywq
     * @Date 2020-04-19
     */
    @PostMapping("listManagerDrivers")
    public AppResponse listManagerDrivers(){
        try{
            return managerInformationService.listManagerDrivers();
        }catch (Exception e){
            logger.error("查询负责店长门店的司机信息失败",e);
            System.out.println(e.toString());
            throw e;
        }
    }
}
