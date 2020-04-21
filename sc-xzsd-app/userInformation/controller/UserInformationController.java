package com.xzsd.app.userInformation.controller;

import com.neusoft.core.restful.AppResponse;
import com.xzsd.app.userInformation.entity.UserInfo;
import com.xzsd.app.userInformation.service.UserInformationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @ClassName UserInformationController
 * @Description 用户信息管理
 * @Author ywq
 * @Date 2020-04-16
 */
@RestController
@RequestMapping("/userInformation")
public class UserInformationController {

    private static final Logger logger = LoggerFactory.getLogger(UserInformationController.class);

    @Resource
    private UserInformationService userInformationService;

    /**
     * 获取用户详细信息
     * @return
     * @Author ywq
     * @Date 2020-04-16
     */
    @PostMapping("getUser")
    public AppResponse getUser(){
        try{
            return userInformationService.getUser();
        }catch (Exception e){
            logger.error("获取用户详细信息失败",e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 修改用户密码
     * @param userInfo
     * @return
     * @Author ywq
     * @Date 2020-04-16
     */
    @PostMapping("updateUserPassword")
    public AppResponse updateUserPassword(UserInfo userInfo){
        try {
            return userInformationService.updateUserPassword(userInfo);
        }catch (Exception e){
            logger.error("修改密码失败",e);
            System.out.println(e.toString());
            throw e;
        }
    }
}
