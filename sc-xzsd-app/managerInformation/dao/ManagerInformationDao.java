package com.xzsd.app.managerInformation.dao;

import com.xzsd.app.managerInformation.entity.DriverInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName ManagerInformationDao
 * @Deripition app店长端店长信息接口类
 * @Author ywq
 * @Date 2020-04-19
 */
@Mapper
public interface ManagerInformationDao {

    /**
     * 查询负责店长门店的司机信息接口
     * @param userId
     * @return
     * @Author ywq
     * @Date 2020-04-19
     */
    List<DriverInfo> listManagerDrivers(@Param("userId") String userId);

}
