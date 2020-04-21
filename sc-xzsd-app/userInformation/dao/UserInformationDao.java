package com.xzsd.app.userInformation.dao;

import com.xzsd.app.userInformation.entity.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @ClassName UserInformationDao
 * @Decripition 用户信息管理接口类
 * @Author ywq
 * @Date 2020-04-16
 */
@Mapper
public interface UserInformationDao {
    /**
     * 获取用户角色编号
     * @param userId
     * @return
     */
    String getUserRole(@Param("userId") String userId);

    /**
     * 获取用户详细信息
     * @param userId
     * @param role
     * @return
     */
    UserInfo getUser(@Param("userId") String userId, @Param("role") String role);

    /**
     * 修改用户密码
     * @param userInfo
     * @return
     */
    int updateUserPassword(UserInfo userInfo);
}
