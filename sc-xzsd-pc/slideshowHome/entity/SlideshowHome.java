package com.xzsd.pc.slideshowHome.entity;

/**
 * @ClassName SlideshowHome
 * @Deprecation 首页轮播图实体类
 * @Author ywq
 * @Date 2020-04-05
 */
public class SlideshowHome {
    /**
     * 页码
     */
    private int pageSize;
    /**
     * 页数
     */
    private int pageNum;
    /**
     * 首页轮播图编号
     */
    private String slideshowId;
    /**
     * 首页轮播图序号
     */
    private int slideshowNum;
    /**
     * 首页轮播图图片url
     */
    private String slideshowPath;
    /**
     * 有效期开始时间
     */
    private String startTime;
    /**
     * 有效期结束时间
     */
    private String endTime;
    /**
     * 对应的商品编号
     */
    private String goodsId;
    /**
     * 首页轮播图状态
     */
    private String slideshowStateId;
    /**
     * 删除标记
     */
    private int isDelete;
    /**
     * 创作时间
     */
    private String createTime;
    /**
     * 创作者
     */
    private String createUser;
    /**
     * 更新时间
     */
    private String updateTime;
    /**
     * 更新者
     */
    private String updateUser;
    /**
     * 版本号
     */
    private String version;

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public String getSlideshowId() {
        return slideshowId;
    }

    public void setSlideshowId(String slideshowId) {
        this.slideshowId = slideshowId;
    }

    public int getSlideshowNum() {
        return slideshowNum;
    }

    public void setSlideshowNum(int slideshowNum) {
        this.slideshowNum = slideshowNum;
    }

    public String getSlideshowPath() {
        return slideshowPath;
    }

    public void setSlideshowPath(String slideshowPath) {
        this.slideshowPath = slideshowPath;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        if(startTime == null || "".equals(startTime)){
            this.startTime = startTime;
        }else{
            this.startTime = startTime.replace(".0","");
        }
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        if(endTime == null || "".equals(endTime)){
            this.endTime = endTime;
        }else{
            this.endTime = endTime.replace(".0","");
        }
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getSlideshowStateId() {
        return slideshowStateId;
    }

    public void setSlideshowStateId(String slideshowStateId) {
        this.slideshowStateId = slideshowStateId;
    }

    public int getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(int isDelete) {
        this.isDelete = isDelete;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
