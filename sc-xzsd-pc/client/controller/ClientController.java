package com.xzsd.pc.client.controller;

import com.neusoft.core.restful.AppResponse;
import com.neusoft.security.client.utils.SecurityUtils;
import com.xzsd.pc.client.service.ClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @ClassName ClientController
 * @description 客户管理
 * @author ywq
 * @date 2020-04-09
 */
@RestController
@RequestMapping("/client")
public class ClientController {

    private static final Logger logger = LoggerFactory.getLogger(ClientController.class);

    @Resource
    private ClientService clientService;

    /**
     * 查询客户列表
     * @param userName
     * @param userAcct
     * @param role
     * @return
     * @author ywq
     * @date 2020-04-09
     */
    @PostMapping("listClients")
    public AppResponse listClients(String userName, String userAcct, String role){
        try{
            return clientService.listClients(userName,userAcct,role);
        }catch (Exception e){
            logger.error("查询客户信息失败",e);
            System.out.println(e.toString());
            throw e;
        }
    }
}
