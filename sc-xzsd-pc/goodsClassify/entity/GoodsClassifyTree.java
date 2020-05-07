package com.xzsd.pc.goodsClassify.entity;

import java.util.List;

/**
 * @ClassName MyTree
 * @Description 树节点实体类
 * @Author ywq
 * @Date 2020-04-14
 */
public class GoodsClassifyTree {
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
     * 备注
     */
    private String classifyComment;
    /**
     * 版本号
     */
    private String version;
    /**
     * 该节点对应的孩子节点
     */
    private List<GoodsClassifyTree> twoClassifyList;

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

    public String getClassifyComment() {
        return classifyComment;
    }

    public void setClassifyComment(String classifyComment) {
        this.classifyComment = classifyComment;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public List<GoodsClassifyTree> getTwoClassifyList() {
        return twoClassifyList;
    }

    public void setTwoClassifyList(List<GoodsClassifyTree> twoClassifyList) {
        this.twoClassifyList = twoClassifyList;
    }
}
