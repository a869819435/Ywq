package com.xzsd.pc.topOfColumn.controller;

import com.neusoft.core.restful.AppResponse;
import com.xzsd.pc.menu.controller.MenuController;
import com.xzsd.pc.topOfColumn.service.TopOfColumnService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @ClassName TopOfColumnController
 * @Deprecated 顶部栏管理
 * @Author ywq
 * @Date 2020-04-14
 */
@RestController
@RequestMapping("/topOfColumn")
public class TopOfColumnController {

    private static final Logger logger = LoggerFactory.getLogger(MenuController.class);

    @Resource
    private TopOfColumnService topOfColumnService;

    /**
     * 获取顶部栏信息
     * @return
     * @Author ywq
     * @Date 2020-04-14
     */
    @PostMapping("getTopOfColumn")
    public AppResponse getTopOfColumn(){
        try{
            return topOfColumnService.getTopOfColumn();
        }catch (Exception e){
            logger.error("查询菜单名列表失败",e);
            System.out.println(e.toString());
            throw e;
        }
    }

}
