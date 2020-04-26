package com.xzsd.app.managerInformation.service;

import com.neusoft.core.restful.AppResponse;
import com.neusoft.security.client.utils.SecurityUtils;
import com.xzsd.app.managerInformation.dao.ManagerInformationDao;
import com.xzsd.app.managerInformation.entity.DriverInfo;
import com.xzsd.app.managerInformation.entity.ManagerInfoData;
import com.xzsd.app.managerOrder.entity.ManagerOrderData;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName ManagerInformationService
 * @Deripition app店长端店长信息实现类
 * @Author ywq
 * @Date 2020-04-19
 */
@Service
public class ManagerInformationService {

    @Resource
    private ManagerInformationDao managerInformationDao;

    /**
     * 查询负责店长门店的司机信息实现
     * @return
     */
    public AppResponse listManagerDrivers(){
        String userId = SecurityUtils.getCurrentUserId();
        List<DriverInfo> driverInfos = managerInformationDao.listManagerDrivers(userId);
        if(driverInfos == null || driverInfos.size() == 0){
            return AppResponse.notFound("暂时没有分配司机负责您的门店");
        }
        ManagerInfoData list = new ManagerInfoData();
        list.setList(driverInfos);
        return AppResponse.success("查询负责店长门店的司机信息成功",list);
    }

}
