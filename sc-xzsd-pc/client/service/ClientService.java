package com.xzsd.pc.client.service;

import com.neusoft.core.restful.AppResponse;
import com.neusoft.security.client.utils.SecurityUtils;
import com.xzsd.pc.client.dao.ClientDao;
import com.xzsd.pc.user.entity.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

import static com.neusoft.core.page.PageUtils.getPageInfo;

/**
 * @Deprecated UserService
 * @Description 客户管理实现类
 * @Author ywq
 * @Date 2020-03-24
 */
@Service
public class ClientService {

    @Resource
    private ClientDao clientDao;

    /**
     * 查询客户列表
     * @param userName
     * @param userAcct
     * @param role
     * @return
     * @Author ywq
     * @Date 2020-03-24
     */
    public AppResponse listClients(String userName, String userAcct, String role){
        String nowLogin = SecurityUtils.getCurrentUserId();
        List<User> userList = clientDao.listClientsByPage(userName,userAcct,role,nowLogin);
        return AppResponse.success("查询信息成功",getPageInfo(userList));
    }

}
