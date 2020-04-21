package com.xzsd.app.clientInformation.controller;

import com.neusoft.core.restful.AppResponse;
import com.xzsd.app.clientInformation.service.ClientInformationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @ClassName ClientInformationController
 * @Deripition app客户端客户信息实现类
 * @Author ywq
 * @Date 2020-04-19
 */
@RestController
@RequestMapping("clientInformation")
public class ClientInformationController {

    private static final Logger logger = LoggerFactory.getLogger(ClientInformationController.class);

    @Resource
    private ClientInformationService clientInformationService;

    /**
     * 修改客户邀请码
     * @param inviteCode
     * @return
     * @Author ywq
     * @Date 2020-04-19
     */
    @PostMapping("updateClientInvite")
    public AppResponse updateClientInvite(String inviteCode){
        try{
            return clientInformationService.updateInviteCode(inviteCode);
        }catch (Exception e){
            logger.error("修改门店邀请码失败",e);
            System.out.println(e.toString());
            throw e;
        }
    }

}
