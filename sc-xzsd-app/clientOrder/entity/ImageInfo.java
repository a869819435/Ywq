package com.xzsd.app.clientOrder.entity;


/**
 * @ClassName ImageInfo
 * @Deripition app客户端订单详情图片信息实体类
 * @Author ywq
 * @Date 2020-04-18
 */
public class ImageInfo {
    /**
     * 图片编号
     */
    private String imageId;
    /**
     * 商品评价编号
     */
    private String evaluateId;
    /**
     * 图片编号
     */
    private String imageNum;
    /**
     * 图片路径
     */
    private String imagePath;
    /**
     * 创建者
     */
    private String createUser;

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public String getEvaluateId() {
        return evaluateId;
    }

    public void setEvaluateId(String evaluateId) {
        this.evaluateId = evaluateId;
    }

    public String getImageNum() {
        return imageNum;
    }

    public void setImageNum(String imageNum) {
        this.imageNum = imageNum;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }
}
