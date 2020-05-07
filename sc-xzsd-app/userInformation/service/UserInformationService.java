package com.xzsd.app.userInformation.service;

import com.neusoft.core.restful.AppResponse;
import com.neusoft.security.client.utils.SecurityUtils;
import com.xzsd.app.register.enums.RoleEnums;
import com.xzsd.app.userInformation.dao.UserInformationDao;
import com.xzsd.app.userInformation.entity.UserInfo;
import com.xzsd.app.utils.PasswordUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @ClassName UserInformationService
 * @Decripition 用户信息管理实现类
 * @Author ywq
 * @Date 2020-04-16
 */
@Service
public class UserInformationService {

    @Resource
    private UserInformationDao userInformationDao;

    /**
     * 获取用户详细信息
     * @return
     * @Author ywq
     * @Date 2020-04-16
     */
    public AppResponse getUser(){
        //获取当前账号id
        String userId = SecurityUtils.getCurrentUserId();
        //获取当前角色编号
        String role = userInformationDao.getUserRole(userId);
        if(RoleEnums.ADMIN.getType().compareTo(role) >= 0){
            role = null;
        }
        //顺便判断此用户是否被删除
        if(role == null || "".equals(role)){
            return AppResponse.versionError("用户不存在或已被删除！");
        }
        UserInfo userInfo = userInformationDao.getUser(userId,role);
        if(role.equals(RoleEnums.DRIVER.getType())){
            userInfo.setDriverName(userInfo.getUserName());
        }
        userInfo.setRole(role);
        userInfo.setUserPassword(null);
        return AppResponse.success("查询用户详细信息成功",userInfo);
    }

    /**
     * 修改用户密码
     * @param userInfo
     * @return
     * @Author ywq
     * @Date 2020-04-16
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse updateUserPassword(UserInfo userInfo) {
        AppResponse appResponse = AppResponse.success("修改密码成功！");
        userInfo.setUserId(SecurityUtils.getCurrentUserId());
        //需要校验原密码是否正确
        String oldPassword = userInfo.getUserPassword();
        if ( null != oldPassword && !"".equals(oldPassword) ) {
            //获取用户原信息
            String userDetail = userInformationDao.getUserPassword(userInfo.getUserId());
            if (null == userDetail || "".equals(userDetail)) {
                return AppResponse.versionError("用户不存在或已被删除！");
            } else {
                if ( !PasswordUtils.equalsPassword(oldPassword,userDetail) ){
                    return AppResponse.versionError("原密码不匹配，请重新输入！");
                }
            }
        }
        //获取新的密码
        userInfo.setUserNewPassword(PasswordUtils.generatePassword(userInfo.getUserNewPassword()));
        //获取修改者
        userInfo.setUpdateUser(SecurityUtils.getCurrentUserId());
        int count = userInformationDao.updateUserPassword(userInfo);
        if(0 == count) {
            appResponse = AppResponse.versionError("修改密码失败，请重试！");
        }
        return appResponse;
    }
}
