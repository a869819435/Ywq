package com.xzsd.app.register.controller;

import com.neusoft.core.restful.AppResponse;
import com.xzsd.app.register.entity.RegisterInfo;
import com.xzsd.app.register.service.RegisterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @ClassName RegisterController
 * @description 注册管理
 * @author ywq
 * @date 2020-04-15
 */
@RestController
@RequestMapping("/register")
public class RegisterController {

    private static final Logger logger = LoggerFactory.getLogger(RegisterController.class);

    @Resource
    private RegisterService registerService;

    /**
     * 注册客户
     * @param userInfo
     * @return
     */
    @PostMapping("clientRegister")
    public AppResponse clientRegister(RegisterInfo userInfo){
        try {
            //如果用户上传头像,获取用户头像
            userInfo.setUserImage(userInfo.getImagePath());
            AppResponse appResponse = registerService.register(userInfo);
            return appResponse;
        }catch (Exception e){
            logger.error("用户新增失败",e);
            System.out.println(e.toString());
            throw e;
        }
    }
}
