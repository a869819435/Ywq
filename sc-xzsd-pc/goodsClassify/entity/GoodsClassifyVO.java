package com.xzsd.pc.goodsClassify.entity;

import java.util.List;

/**
 * 一二级商品分类实体类
 * @ClassName GoodsClassify
 * @Description goods of classify
 * @Author ywq
 * @Date 2020-03-29
 */
public class GoodsClassifyVO {
    /**
     * 分类的编号
     */
    private String classifyId;
    /**
     * 分类的名称
     */
    private String classifyName;
    /**
     * 父级编号
     */
    private String classifyParent;
    /**
     * 版本号
     */
    private String version;
    /**
     * 二级分类信息
     */
    private List<GoodsClassifyVO> twoGoodsClassify;

    public String getClassifyId() {
        return classifyId;
    }

    public void setClassifyId(String classifyId) {
        this.classifyId = classifyId;
    }

    public String getClassifyName() {
        return classifyName;
    }

    public void setClassifyName(String classifyName) {
        this.classifyName = classifyName;
    }

    public String getClassifyParent() {
        return classifyParent;
    }

    public void setClassifyParent(String classifyParent) {
        this.classifyParent = classifyParent;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public List<GoodsClassifyVO> getTwoGoodsClassify() {
        return twoGoodsClassify;
    }

    public void setTwoGoodsClassify(List<GoodsClassifyVO> twoGoodsClassify) {
        this.twoGoodsClassify = twoGoodsClassify;
    }
}
