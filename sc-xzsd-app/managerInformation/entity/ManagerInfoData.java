package com.xzsd.app.managerInformation.entity;

import java.util.List;

/**
 * @ClassName ManagerInformation
 * @Deripition app店长端店长门下的司机页信息实体类
 * @Author ywq
 * @Date 2020-04-19
 */
public class ManagerInfoData {
    /**
     * 司机信息数据
     */
    List<DriverInfo> list;

    public List<DriverInfo> getList() {
        return list;
    }

    public void setList(List<DriverInfo> list) {
        this.list = list;
    }
}
