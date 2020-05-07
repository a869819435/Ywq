package com.xzsd.app.driverHome.dao;

import com.xzsd.app.driverHome.entity.StroeInfo;
import com.xzsd.app.managerInformation.entity.DriverInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName DriverHomeDao
 * @Deripition app司机端负责的门店信息接口类
 * @Author ywq
 * @Date 2020-04-19
 */
@Mapper
public interface DriverHomeDao {

    /**
     * 查询司机负责的门店信息接口
     * @param userId
     * @return
     * @Author ywq
     * @Date 2020-04-19
     */
    List<StroeInfo> listDriverStores(@Param("userId") String userId);

}
