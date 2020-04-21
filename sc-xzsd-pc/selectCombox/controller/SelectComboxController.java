package com.xzsd.pc.selectCombox.controller;

import com.neusoft.core.restful.AppResponse;
import com.xzsd.pc.selectCombox.service.SelectComboxService;
import com.xzsd.pc.store.controller.StoreController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @ClassName SelectComboxController
 * @Deprecated 下拉框管理
 * @Author ywq
 * @Date 2020-04-13
 */
@RestController
@RequestMapping("/selectCombox")
public class SelectComboxController {

    private static final Logger logger = LoggerFactory.getLogger(StoreController.class);

    @Resource
    private SelectComboxService selectComboxService;

    /**
     * 地域下拉框查询
     * @param areaId
     * @return
     * @Author ywq
     * @Date 2020-04-13
     */
    @PostMapping("listArea")
    public AppResponse listArea(String areaId){
        try{
            return selectComboxService.listArea(areaId);
        }catch (Exception e ){
            logger.error("下拉查询地区失败",e);
            System.out.println(e.toString());
            throw e;
        }
    }
}
