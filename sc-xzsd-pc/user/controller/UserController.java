package com.xzsd.pc.user.controller;

import com.neusoft.core.exception.ScServerException;
import com.neusoft.core.restful.AppResponse;
import com.xzsd.pc.user.entity.User;
import com.xzsd.pc.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @ClassName UserController
 * @description 用户管理
 * @author ywq
 * @date 2020-03-24
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Resource
    private UserService userService;

    /**
     * 新增用户
     * @param user
     * @return
     * @Author ywq
     * @Date 2020-03-24
     */
    @PostMapping("addUser")
    public AppResponse addUser(User user,String imagePath){
        try {
            //如果用户上传头像,获取用户头像
            user.setUserImage(imagePath);
            AppResponse appResponse = userService.addUser(user);
            return appResponse;
        }catch (Exception e){
            logger.error("用户新增失败",e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 查询用户详情
     * @param userId
     * @return
     * @Author ywq
     * @Date 2020-03-25
     */
    @RequestMapping(value = "getUser")
    public AppResponse getUser(String userId){
        try{
            return userService.getUser(userId);
        }catch (Exception e){
            logger.error("查询用户详情失败",e );
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 用户列表（分页）
     * @param user
     * @return
     * @Author ywq
     * @Date 2020-03-24
     */
    @RequestMapping(value = "listUsers")
    public AppResponse listUsers(User user){
        try{
            return userService.listUsers(user);
        }catch (Exception e){
            logger.error("用户新增失败",e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 修改用户
     * @param user
     * @return
     * @Author ywq
     * @Date 2020-03-25
     */
    @RequestMapping("updateUser")
    public AppResponse updateUser(User user,String imagePath){
        try{
            //获取用户头像
            user.setUserImage(imagePath);
            return userService.updateUser(user);
        }catch (Exception e){
            logger.error("修改用户信息失败",e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 删除用户
     * @param userId
     * @return
     * @Author ywq
     * @Date 2020-03-25
     */
    @RequestMapping("deleteUser")
    public AppResponse deleteUser(String userId,String role,String nowRole){
        try {
            return userService.deleteUser(userId,role,nowRole);
        }catch (Exception e){
            logger.error("删除用户失败",e);
            System.out.println(e.toString());
            throw e;
        }
    }
}
