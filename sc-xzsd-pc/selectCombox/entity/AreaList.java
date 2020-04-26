package com.xzsd.pc.selectCombox.entity;

import java.util.List;

/**
 * @ClassName Area
 * @Deprecated 区域返回信息实体类
 * @Author ywq
 * @Date 2020-04-12
 */
public class AreaList {
    /**
     * 省市区信息集合
     */
    List<AreaInfo> areaList;

    public List<AreaInfo> getAreaList() {
        return areaList;
    }

    public void setAreaList(List<AreaInfo> areaList) {
        this.areaList = areaList;
    }
}
