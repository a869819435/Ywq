package com.xzsd.pc.user.service;

import com.neusoft.core.restful.AppResponse;
import com.neusoft.security.client.utils.SecurityUtils;
import com.neusoft.util.StringUtil;
import com.xzsd.pc.user.dao.UserDao;
import com.xzsd.pc.user.entity.User;
import com.xzsd.pc.user.enums.RoleEnums;
import com.xzsd.pc.utils.PasswordUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;
import java.util.logging.Handler;

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
        //获取用户id
        String createUser = SecurityUtils.getCurrentUserId();
        //获取当前登录角色
        String nowRole = user.getNowRole();
        if (nowRole.equals(user.getRole()) ){
            return AppResponse.versionError("您的权限不足");
        }
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
        user.setCreateUser(createUser);
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
        //获取用户id
        String updateUser = SecurityUtils.getCurrentUserId();
        String nowRole = user.getNowRole();;
        if (nowRole.equals(user.getRole()) ){
            return AppResponse.versionError("您的权限不足");
        }
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
    public AppResponse deleteUser(String userId,String role,String nowRole){
        List<String> listUserId = Arrays.asList(userId.split(","));
        //获取用户id
        String updateUser = SecurityUtils.getCurrentUserId();
        AppResponse appResponse = AppResponse.success("删除成功！");
        //需要获取角色的用户id
        List<String> getRoleUserId = new ArrayList<>();
        getRoleUserId.add(updateUser);
        getRoleUserId.addAll(listUserId);
        //获取所有角色
        List<String> roles = Arrays.asList(role.split(","));
        //记录角色编号为2的用户
        List<String> userIdOfManager = new ArrayList<>();
        for (int i = 0 ; i < roles.size() ; i++ ){
            //管理员不能删管理员
            if (roles.get(i).equals(nowRole)){
                return AppResponse.versionError("删除账号为" + listUserId.get(i) + "的用户权限不足");
            }
            //是店长的用户
            if (roles.get(i).equals(RoleEnums.MANAGE.getType())){
                userIdOfManager.add(listUserId.get(i));
            }
        }
        if (userIdOfManager != null && userIdOfManager.size() != 0 ){
            //获取有门店的店长编号
            List<String> havingStore = userDao.getHavingStore(userIdOfManager);
            if( havingStore != null || havingStore.size() != 0 ){
                String errorInformation = StringUtils.join(havingStore.toString(),",");
                return AppResponse.versionError("店长编号" + errorInformation + "有绑定门店");
            }
        }
        //删除用户
        int count = userDao.deleteUser(listUserId,updateUser);
        if(0 == count){
            appResponse = AppResponse.versionError("删除失败，请重试！");
        }
        return appResponse;
    }
}
