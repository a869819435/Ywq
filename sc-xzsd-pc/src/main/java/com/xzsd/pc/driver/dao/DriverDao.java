package com.xzsd.pc.driver.dao;

import com.xzsd.pc.driver.entity.Driver;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName DriverDao
 * @Deprecated 司机管理接口
 * @Author ywq
 * @Date 2020-04-13
 */
@Mapper
public interface DriverDao {

    /**
     * 新增司机
     * @param driver
     * @return
     */
    int addDriver(Driver driver);

    /**
     * 获取司机详情
     * @param driverId
     * @return
     */
    Driver getDriver(@Param("driverId") String driverId);

    /**
     * 获取所有司机信息
     * @param Driver
     * @param nowLogin
     * @return
     */
    List<Driver> listDriverByPage(@Param("Dr") Driver Driver, @Param("nowLogin") String nowLogin);

    /**
     * 修改司机信息
     * @param driver
     * @return
     */
    int updateDriver(Driver driver);

    /**
     * 删除司机
     * @param listDriverId
     * @param updateUser
     * @return
     */
    int deleteDriver(@Param("listDriverId") List<String> listDriverId, @Param("updateUser") String updateUser);
}
