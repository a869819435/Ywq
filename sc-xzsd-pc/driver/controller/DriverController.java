package com.xzsd.pc.driver.controller;


import com.neusoft.core.restful.AppResponse;
import com.xzsd.pc.driver.entity.Driver;
import com.xzsd.pc.driver.service.DriverService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @ClassName DriverController
 * @Deprecated 司机管理
 * @Author ywq
 * @Date 2020-04-13
 */
@RestController
@RequestMapping("/driver")
public class DriverController {

    private static final Logger logger = LoggerFactory.getLogger(DriverController.class);

    @Resource
    private DriverService driverService;

    /**
     * 新增司机
     * @param driver
     * @return
     * @Author ywq
     * @Date 2020-04-12
     */
    @PostMapping("addDriver")
    public AppResponse addDriver(Driver driver, String imagePath,String nowRole){
        try {
            driver.setUserImage(imagePath);
            return driverService.addDriver(driver,nowRole);
        }catch (Exception e){
            logger.error("司机新增失败",e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 查询司机详情
     * @param driverId
     * @return
     * @Author ywq
     * @Date 2020-04-12
     */
    @RequestMapping("getDriver")
    public AppResponse getDriver(String driverId){
        try{
            return driverService.getDriver(driverId);
        }catch (Exception e){
            logger.error("查询司机详情失败",e );
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 司机列表（分页）
     * @param driver
     * @return
     * @Author ywq
     * @Date 2020-04-12
     */
    @PostMapping("listDrivers")
    public AppResponse listDrivers(Driver driver){
        try{
            return driverService.listDrivers(driver);
        }catch (Exception e){
            logger.error("分页司机查询失败",e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 修改司机
     * @param driver
     * @return
     * @Author ywq
     * @Date 2020-04-12
     */
    @PostMapping("updateDriver")
    public AppResponse updateDriver(Driver driver,String imagePath,String nowRole){
        try{
            driver.setUserImage(imagePath);
            return driverService.updateDriver(driver,nowRole);
        }catch (Exception e){
            logger.error("修改司机信息失败",e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 删除司机
     * @param driverId
     * @return
     * @Author ywq
     * @Date 2020-04-12
     */
    @RequestMapping("deleteDriver")
    public AppResponse deleteDriver(String driverId,String nowRole){
        try {
            return driverService.deleteDriver(driverId,nowRole);
        }catch (Exception e){
            logger.error("删除司机失败",e);
            System.out.println(e.toString());
            throw e;
        }
    }
}
