package com.xzsd.app.clientInformation.service;

import com.neusoft.core.restful.AppResponse;
import com.neusoft.security.client.utils.SecurityUtils;
import com.xzsd.app.clientInformation.dao.ClientInformationDao;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @ClassName ClientInformationService
 * @Deripition app客户端客户信息实现类
 * @Author ywq
 * @Date 2020-04-19
 */
@Service
public class ClientInformationService {

    @Resource
    private ClientInformationDao clientInformationDao;

    /**
     * 修改客户邀请码实现
     * @param inviteCode
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse updateInviteCode(String inviteCode){
        //校验邀请码是否存在
        int countInviteCode = clientInformationDao.countInviteCode(inviteCode);
        if (countInviteCode == 0 ){
            return AppResponse.notFound("该邀请码不存在，请重新输出");
        }
        //获取当前登录人id
        String userId = SecurityUtils.getCurrentUserId();
        int count = clientInformationDao.updateClientInvite(userId,inviteCode);
        if ( count == 0 ){
            AppResponse.versionError("修改邀请码失败！");
        }
        return AppResponse.success("修改邀请码成功！");
    }
}
