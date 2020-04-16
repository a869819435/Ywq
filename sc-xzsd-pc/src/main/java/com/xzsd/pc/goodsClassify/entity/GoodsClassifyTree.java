package com.xzsd.pc.goodsClassify.entity;

import java.util.List;

/**
 * @ClassName MyTree
 * @Description 树节点实体类（其实不用分one，two，只是为了填坑）
 * @Author ywq
 * @Date 2020-04-14
 */
public class GoodsClassifyTree {
    /**
     * 该节点id
     */
    private String id;
    /**
     * 该节点的父结点id
     */
    private String parentId;
    /**
     * 该节点的名称
     */
    private String name;
    /**
     * 节点等级
     */
    private int level;
    /**
     * 该节点数据
     */
    private Object data;
    /**
     * 该节点对应的孩子节点(一级)
     */
    private List<GoodsClassifyTree> oneClassifyList;
    /**
     * 该节点对应的孩子节点(二级)
     */
    private List<GoodsClassifyTree> twoClassifyList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public List<GoodsClassifyTree> getOneClassifyList() {
        return oneClassifyList;
    }

    public void setOneClassifyList(List<GoodsClassifyTree> oneClassifyList) {
        this.oneClassifyList = oneClassifyList;
    }

    public List<GoodsClassifyTree> getTwoClassifyList() {
        return twoClassifyList;
    }

    public void setTwoClassifyList(List<GoodsClassifyTree> twoClassifyList) {
        this.twoClassifyList = twoClassifyList;
    }
}
