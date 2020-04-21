package com.xzsd.app.clientInformation.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @ClassName ClientInformationDao
 * @Deripition app客户端客户信息接口类
 * @Author ywq
 * @Date 2020-04-19
 */
@Mapper
public interface ClientInformationDao {

    /**
     * 检验邀请码是否存在
     * @param inviteCode
     * @return
     */
    int countInviteCode(@Param("inviteCode") String inviteCode);

    /**
     * 修改客户邀请码
     * @param userId
     * @param inviteCode
     * @return
     */
    int updateClientInvite(@Param("userId")String userId,@Param("inviteCode") String inviteCode);
}
