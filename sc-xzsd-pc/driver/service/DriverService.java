package com.xzsd.pc.driver.service;

import com.neusoft.core.restful.AppResponse;
import com.neusoft.security.client.utils.SecurityUtils;
import com.neusoft.util.StringUtil;
import com.xzsd.pc.driver.dao.DriverDao;
import com.xzsd.pc.driver.entity.Driver;
import com.xzsd.pc.user.dao.UserDao;
import com.xzsd.pc.user.entity.User;
import com.xzsd.pc.user.enums.RoleEnums;
import com.xzsd.pc.utils.PasswordUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

import static com.neusoft.core.page.PageUtils.getPageInfo;

/**
 * @ClassName DriverService
 * @Deprecated 司机管理实现类
 * @Author ywq
 * @Date 2020-04-13
 */
@Service
public class DriverService {

    @Resource
    private DriverDao driverDao;

    @Resource
    private UserDao userDao;

    /**
     * 添加司机信息
     * @param driver
     * @return
     */
    @Transactional(rollbackFor = Exception.class )
    public AppResponse addDriver(Driver driver){
        //获取当前登录人
        driver.setCreateUser(SecurityUtils.getCurrentUserId());
        //获取当前登录角色
        String nowRole = userDao.getUserRole(driver.getCreateUser());
        if (  RoleEnums.MANAGE.equals(nowRole) ){
            return AppResponse.versionError("您无权新增司机！");
        }
        User user = new User();
        //将司机部分信息转入user进行校验
        user.setPhone(driver.getPhone());
        user.setUserAcct(driver.getUserAcct());
        //设置固定司机角色编号
        user.setRole(RoleEnums.DRIVER.getType());
        driver.setRole(RoleEnums.DRIVER.getType());
        //校验用户账号、司机电弧是否存在
        int countDriverInfo = userDao.countInfo(user);
        String errorInfo = "";
        if( (countDriverInfo & 1) == 1){
            errorInfo = errorInfo + "1.用户账号已被注册！" + "\n";
        }
        if( (countDriverInfo & 2) ==  2){
            errorInfo = errorInfo + "2.司机电话已被使用！";
        }
        if(0 != countDriverInfo){
            return AppResponse.versionError(errorInfo);
        }
        //生成司机用户编号
        driver.setDriverId("sj" + StringUtil.getCommonCode(2));
        //获取密码的加密形式
        String password = PasswordUtils.generatePassword(driver.getUserPassword());
        driver.setUserPassword(password);
        //新增用户
        int count = driverDao.addDriver(driver);
        if(0 == count){
            return AppResponse.versionError("新增司机用户失败，请重试！");
        }
        return AppResponse.success("新增司机用户成功！");
    }

    /**
     * 查看司机详情
     * @param driverId
     * @return
     */
    public AppResponse getDriver(String driverId){
        Driver driver = driverDao.getDriver(driverId);
        driver.setDriverId(driverId);
        return AppResponse.success("获取司机详情成功",driver);
    }

    /**
     * 查询司机列表（分页）
     * @param driver
     * @return
     */
    public AppResponse listDrivers(Driver driver){
        //获取当前登录人id
        String nowLogin = SecurityUtils.getCurrentUserId();
        List<Driver> driverList = driverDao.listDriverByPage(driver,nowLogin);
        return AppResponse.success("分页查询司机列表成功!",getPageInfo(driverList));
    }

    /**
     * 修改司机信息
     * @param driver
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse updateDriver(Driver driver){
        //获取当前登录人
        driver.setUpdateUser(SecurityUtils.getCurrentUserId());
        //获取当前登录角色
        String nowRole = userDao.getUserRole(driver.getUpdateUser());
        if (  RoleEnums.MANAGE.equals(nowRole) ){
            return AppResponse.versionError("您无权修改司机信息！");
        }
        User user = new User();
        //获取司机的用户信息
        user.setUserAcct(driver.getUserAcct());
        user.setUserId(driver.getDriverId());
        user.setRole(RoleEnums.DRIVER.getType());
        user.setPhone(driver.getPhone());
        //校验用户账号、司机电话是否存在
        int countDriverInfo = userDao.countInfo(user);
        String errorInfo = "";
        if( (countDriverInfo & 1) == 1){
            errorInfo = errorInfo + "1.用户账号已被注册！" + "\n";
        }
        if( (countDriverInfo & 2) ==  2){
            errorInfo = errorInfo + "2.司机电话已被使用！";
        }
        if(0 != countDriverInfo){
            return AppResponse.versionError(errorInfo);
        }
        //获取密码的加密形式
        String password = PasswordUtils.generatePassword(driver.getUserPassword());
        driver.setUserPassword(password);
        //修改用户信息
        int count = driverDao.updateDriver(driver);
        if(0 == count){
            return AppResponse.versionError("修改失败,数据有变化，请刷新！");
        }
        return AppResponse.success("修改成功！");
    }

    /**
     * 删除司机
     * @param driverId
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse deleteDriver(String driverId){
        //获取当前登录人id
        String updateUser = SecurityUtils.getCurrentUserId();
        //获取当前登录角色
        String nowRole = userDao.getUserRole(updateUser);
        if (  RoleEnums.MANAGE.equals(nowRole) ){
            return AppResponse.versionError("您无权删除司机！");
        }
        List<String> listDriverId = Arrays.asList(driverId.split(","));

        //删除用户
        int count = driverDao.deleteDriver(listDriverId,updateUser);
        if(0 == count){
            return AppResponse.versionError("删除司机失败，请重试！");
        }
        return AppResponse.success("删除司机成功！");
    }
}
