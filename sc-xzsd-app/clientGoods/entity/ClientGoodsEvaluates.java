package com.xzsd.app.clientGoods.entity;

import java.util.List;

/**
 * @ClassName ClientGoodsEvaluates
 * @Deripition app客户端商品评价实体类
 * @Author ywq
 * @Date 2020-04-17
 */
public class ClientGoodsEvaluates {
    /**
     * 商品编号
     */
    private String goodsId;
    /**
     * 用户账号
     */
    private String userAcct;
    /**
     * 商品评价编号
     */
    private String evaluateId;
    /**
     * 评价内容
     */
    private String evaluateContent;
    /**
     * 商品评价等级
     */
    private int evaluateScore;
    /**
     * 评价时间
     */
    private String createTime;
    /**
     * 评价图片路径
     */
    private String imagePath;

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getUserAcct() {
        return userAcct;
    }

    public void setUserAcct(String userAcct) {
        this.userAcct = userAcct;
    }

    public String getEvaluateId() {
        return evaluateId;
    }

    public void setEvaluateId(String evaluateId) {
        this.evaluateId = evaluateId;
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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
