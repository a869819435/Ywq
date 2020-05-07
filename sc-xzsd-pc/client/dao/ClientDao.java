package com.xzsd.pc.client.dao;


import com.xzsd.pc.user.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Deprecated UserService
 * @Description 客户管理接口
 * @Author ywq
 * @Date 2020-03-24
 */
@Mapper
public interface ClientDao {
    /**
     * 查询客户列表
     * @param userName
     * @param userAcct
     * @param role
     * @param nowLogin
     * @return
     * @Author ywq
     * @Date 2020-03-24
     */
    List<User> listClientsByPage(@Param("userName") String userName, @Param("userAcct") String userAcct,
                                @Param("role") String role, @Param("nowLogin") String nowLogin);

}
