package com.xzsd.app.driverHome.entity;

import java.util.List;

/**
 * @ClassName DriverHomeData
 * @Deripition app司机端负责的门店页信息实现类
 * @Author ywq
 * @Date 2020-04-19
 */
public class DriverHomeData {
    /**
     * 门店信息数据
     */
    List<StroeInfo> list;

    public List<StroeInfo> getList() {
        return list;
    }

    public void setList(List<StroeInfo> list) {
        this.list = list;
    }
}
