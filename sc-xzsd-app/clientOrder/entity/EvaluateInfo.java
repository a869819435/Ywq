package com.xzsd.app.clientOrder.entity;

import java.util.List;

/**
 * @ClassName EvaluateInfo
 * @Deripition app客户端添加商品评价信息实体类
 * @Author ywq
 * @Date 2020-04-18
 */
public class EvaluateInfo {
    /**
     * 商品评价编号
     */
    private String evaluateId;
    /**
     * 商品编号
     */
    private String goodsId;
    /**
     * 用户编号
     */
    private String userId;
    /**
     * 评价内容
     */
    private String evaluateContent;
    /**
     * 商品评价等级
     */
    private int evaluateScore;
    /**
     * 评价图片列表
     */
    private List<ImageInfo> imageList;

    public String getEvaluateId() {
        return evaluateId;
    }

    public void setEvaluateId(String evaluateId) {
        this.evaluateId = evaluateId;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEvaluateContent() {
        return evaluateContent;
    }

    public void setEvaluateContent(String evaluateContent) {
        this.evaluateContent = evaluateContent;
    }

    public int getEvaluateScore() {
        return evaluateScore;
    }

    public void setEvaluateScore(int evaluateScore) {
        this.evaluateScore = evaluateScore;
    }

    public List<ImageInfo> getImageList() {
        return imageList;
    }

    public void setImageList(List<ImageInfo> imageList) {
        this.imageList = imageList;
    }


}
