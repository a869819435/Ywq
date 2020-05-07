package com.xzsd.app.register.dao;

import com.xzsd.app.register.entity.RegisterInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * @ClassName RegisterDao
 * @Deprecation 注册功能接口
 * @Author ywq
 * @Date 2020-04-15
 */
@Mapper
public interface RegisterDao {

    /**
     * 校验用户电话、用户账号数量
     * @param userInfo 用户信息
     * @return
     * @Author ywq
     * @Date 2020-04-15
     */
    int countInfo(RegisterInfo userInfo);

    /**
     * 新增用户信息
     * @param userInfo 用户信息
     * @return
     * @Author ywq
     * @Date 2020-04-15
     */
    int addUser(RegisterInfo userInfo);
}
