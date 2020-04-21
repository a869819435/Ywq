package com.xzsd.app.clientOrder.entity;

import java.util.List;

/**
 * @ClassName EvaluateOrder
 * @Deripition app客户端新增订单评价实体类
 * @Author ywq
 * @Date 2020-04-18
 */
public class EvaluateOrder {
    /**
     * 订单编号
     */
    private String orderId;
    /**
     * 评价信息
     */
    private List<EvaluateInfo> evaluateList;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public List<EvaluateInfo> getEvaluateInfoList() {
        return evaluateList;
    }

    public void setEvaluateInfoList(List<EvaluateInfo> evaluateList) {
        this.evaluateList = evaluateList;
    }
}
