package com.xzsd.pc.user.service;

import com.neusoft.core.restful.AppResponse;
import com.neusoft.security.client.utils.SecurityUtils;
import com.neusoft.util.StringUtil;
import com.xzsd.pc.user.dao.UserDao;
import com.xzsd.pc.user.entity.User;
import com.xzsd.pc.user.enums.RoleEnums;
import com.xzsd.pc.utils.PasswordUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

import static com.neusoft.core.page.PageUtils.getPageInfo;

/**
 * @Deprecated UserService
 * @Description 用户管理
 * @Author ywq
 * @Date 2020-03-24
 */
@Service
public class UserService {

    @Resource
    private UserDao userDao;

    /**
     * 新增用户
     * @param user
     * @return
     */
    @Transactional(rollbackFor = Exception.class )
    public AppResponse addUser(User user){
        //校验账号、手机号是否存在
        int countInfo = userDao.countInfo(user);
        String errorInfo = "";
        if( (countInfo & 1) == 1){
            errorInfo = errorInfo + "1.用户账号已被注册！" + "\n";
        }
        if( (countInfo & 2) ==  2){
            errorInfo = errorInfo + "2.电话已被使用！";
        }
        if(0 != countInfo){
            return AppResponse.versionError(errorInfo);
        }
        //获取用户id
        String createrUser = SecurityUtils.getCurrentUserId();
        user.setCreateUser(createrUser);
        //获取密码的加密形式
        String password = PasswordUtils.generatePassword(user.getUserPassword());
        user.setUserPassword(password);
        //时间戳生成用户id
        user.setUserId(StringUtil.getCommonCode(2));
        user.setIsDelete(0);
        //新增用户
        int count = userDao.addUser(user);
        if(0 == count){
            return AppResponse.versionError("新增失败，请重试！");
        }
        return AppResponse.success("新增成功！");
    }

    /**
     * 查看用户详情
     * @param userId
     * @return
     */
    public AppResponse getUser(String userId){
        //获取用户详情信息
        User user = userDao.getUser(userId);
        return AppResponse.success("获取用户"+ userId +"详情",user);
    }
    /**
     * 查询用户列表（分页）
     * @param user
     * @return
     */
    public AppResponse listUsers(User user){
        List<User> userList = userDao.listUsersByPage(user);
        return AppResponse.success("查询成功!",getPageInfo(userList));
    }

    /**
     * 修改用户信息
     * @param user
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse updateUser(User user){
        AppResponse appResponse = AppResponse.success("修改成功！");
        //校验账号、手机号是否存在
        int countInfo = userDao.countInfo(user);
        String errorInfo = "";
        if( (countInfo & 1) == 1){
            errorInfo = errorInfo + "1.用户账号已被注册！" + "\n";
        }
        if( (countInfo & 2) ==  2){
            errorInfo = errorInfo + "2.电话已被使用！";
        }
        if(0 != countInfo){
            return AppResponse.versionError(errorInfo);
        }
        //获取用户id
        String updateUser = SecurityUtils.getCurrentUserId();
        user.setUpdateUser(updateUser);
        //获取密码的加密形式
        String password = PasswordUtils.generatePassword(user.getUserPassword());
        user.setUserPassword(password);
        //修改用户信息
        int count = userDao.updateUser(user);
        if(0 == count){
            appResponse = AppResponse.versionError("修改失败,数据有变化，请刷新！");
            return appResponse;
        }
        return appResponse;
    }

    /**
     * 删除用户
     * @param userId
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse deleteUser(String userId){
        List<String> listUserId = Arrays.asList(userId.split(","));
        AppResponse appResponse = AppResponse.success("删除成功！");
        //获取用户id
        String updateUser = SecurityUtils.getCurrentUserId();
        //删除用户
        int count = userDao.deleteUser(listUserId,updateUser);
        if(0 == count){
            appResponse = AppResponse.versionError("删除失败，请重试！");
        }
        return appResponse;
    }

    /**
     * 修改密码
     * @param user
     * @return
     */
    public AppResponse updatePassword(User user) {
        AppResponse appResponse = AppResponse.success("修改密码成功！");
        //需要校验原密码是否正确
        String oldPassword = user.getUserPassword();
        if( null != oldPassword && !"".equals(oldPassword) ) {
            String oldDbPassword = PasswordUtils.generatePassword(oldPassword);
            //获取用户原信息
            User userDetail = userDao.getUser(user.getUserId());
            if(null == userDetail) {
                return AppResponse.versionError("用户不存在或已被删除！");
            } else {
                if(!oldDbPassword.equals(userDetail.getUserPassword())) {
                    return AppResponse.versionError("原密码不匹配，请重新输入！");
                }
            }
        }
        //获取新的密码
        user.setUserNewPassword(PasswordUtils.generatePassword(user.getUserNewPassword()));
        //获取修改者
        user.setUpdateUser(SecurityUtils.getCurrentUserId());
        int count = userDao.updateUserPassword(user);
        if(0 == count) {
            appResponse = AppResponse.versionError("修改密码失败，请重试！");
        }
        return appResponse;
    }
}
