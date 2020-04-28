package com.xzsd.pc.selectCombox.entity;

/**
 * @ClassName AreaInfo
 * @Deprecated 区域实体类
 * @Author ywq
 * @Date 2020-04-12
 */
public class AreaInfo {
    /**
     * 地域编号
     */
    private String areaId;
    /**
     * 地域名称
     */
    private String areaName;
    /**
     * 地域父级编号
     */
    private String parentArea;

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getParentArea() {
        return parentArea;
    }

    public void setParentArea(String parentArea) {
        this.parentArea = parentArea;
    }
}
