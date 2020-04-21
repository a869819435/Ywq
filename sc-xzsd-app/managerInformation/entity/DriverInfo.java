package com.xzsd.app.managerInformation.entity;

/**
 * @ClassName ManagerInformation
 * @Deripition app店长端店长门下的司机信息实体类
 * @Author ywq
 * @Date 2020-04-19
 */
public class DriverInfo {
    /**
     * 司机名称
     */
    private String userName;
    /**
     * 司机电话
     */
    private String phone;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
