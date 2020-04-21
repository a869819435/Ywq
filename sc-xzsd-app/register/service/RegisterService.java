package com.xzsd.app.register.service;

import com.neusoft.core.restful.AppResponse;
import com.neusoft.util.StringUtil;
import com.xzsd.app.register.dao.RegisterDao;
import com.xzsd.app.register.entity.RegisterInfo;
import com.xzsd.app.register.enums.RoleEnums;
import com.xzsd.app.utils.PasswordUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @ClassName RegisterService
 * @Deprecation 注册功能
 * @Author ywq
 * @Date 2020-04-15
 */
@Service
public class RegisterService {

    @Resource
    private RegisterDao registerDao;

    /**
     * 获取用户信息注册
     * @param registerInfo
     * @return
     * @Author ywq
     * @Date 2020-04-15
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse register(RegisterInfo registerInfo){
        //校验用户账号以及电话是否被使用过
        int countInfo = registerDao.countInfo(registerInfo);
        String errorInfo = "";
        if( (countInfo & 1) == 1){
            errorInfo = errorInfo + "用户账号已被注册！" + "\n";
        }
        if( (countInfo & 2) ==  2){
            errorInfo = errorInfo + "电话已被使用！" + "\n";
        }
        if( (countInfo & 4) ==  4){
            errorInfo = errorInfo + "门店邀请码不存在！";
        }
        if(0 != countInfo){
            return AppResponse.versionError(errorInfo);
        }
        //时间戳生成用户id
        registerInfo.setUserId("kh" + StringUtil.getCommonCode(2));
        //获取客户角色编号
        registerInfo.setRole(RoleEnums.CLIENT.getType());
        //创建者为自己
        registerInfo.setCreateUser(registerInfo.getUserId());
        //密码加密
        String password = PasswordUtils.generatePassword(registerInfo.getUserPassword());
        registerInfo.setUserPassword(password);
        //新增用户
        int count = registerDao.addUser(registerInfo);
        if(0 == count){
            return AppResponse.versionError("注册失败，请重试！");
        }
        return AppResponse.success("注册成功！");
    }
}
