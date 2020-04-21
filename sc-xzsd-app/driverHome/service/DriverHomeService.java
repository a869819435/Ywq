package com.xzsd.app.driverHome.service;

import com.neusoft.core.restful.AppResponse;
import com.neusoft.security.client.utils.SecurityUtils;
import com.xzsd.app.driverHome.dao.DriverHomeDao;
import com.xzsd.app.managerInformation.entity.DriverInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName DriverHomeService
 * @Deripition app司机端负责的门店信息实现类
 * @Author ywq
 * @Date 2020-04-19
 */
@Service
public class DriverHomeService {

    @Resource
    private DriverHomeDao driverHomeDao;

    /**
     * 查询司机负责的门店信息实现
     * @return
     */
    public AppResponse listDriverStores(){
        String userId = SecurityUtils.getCurrentUserId();
        List<DriverInfo> driverInfoList = driverHomeDao.listDriverStores(userId);
        if (driverInfoList == null || driverInfoList.size() == 0){
            AppResponse.notFound("您负责的地区尚未有分布门店！");
        }
        return AppResponse.success("查询司机负责的门店信息成功！",driverInfoList);
    }

}
