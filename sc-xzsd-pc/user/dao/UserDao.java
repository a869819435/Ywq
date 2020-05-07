package com.xzsd.pc.user.dao;

import com.xzsd.pc.user.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName UserDao
 * @Deprecated 用户管理接口类
 * @Author ywq
 * @Date 2020-03-24
 */
@Mapper
public interface UserDao {

    /**
     * 校验用户电话、用户账号数量
     * @param user 用户信息
     * @return
     * @Author ywq
     * @Date 2020-03-24
     */
    int countInfo(User user);

    /**
     * 新增用户
     * @param user 用户信息
     * @return
     * @Author ywq
     * @Date 2020-03-24
     */
    int addUser(User user);

    /**
     * 获取用户详情
     * @param userId 用户编号
     * @return
     * @Author ywq
     * @Date 2020-03-24
     */
    User getUser(@Param("userId") String userId);

    /**
     * 获取所有用户信息
     * @param user 模糊查询的信息
     * @return
     * @Author ywq
     * @Date 2020-03-24
     */
    List<User> listUsersByPage(User user);

    /**
     * 修改用户信息
     * @param user 用户信息
     * @return
     * @Author ywq
     * @Date 2020-03-24
     */
    int updateUser(User user);

    /**
     * 获取有门店的店长编号
     * @param userIdOfManager
     * @return
     * @Author ywq
     * @Date 2020-03-24
     */
    List<String> getHavingStore(@Param("userIdOfManager") List<String> userIdOfManager);

    /**
     * 删除用户
     * @param listUserId 多个用户编号
     * @param updateUser 修改者编号
     * @return
     * @Author ywq
     * @Date 2020-03-24
     */
    int deleteUser(@Param("listUserId") List<String> listUserId, @Param("updateUser") String updateUser);
}
