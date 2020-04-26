package com.xzsd.app.clientOrder.enums;

public enum OrderStateEnums {

    /**
     * 已下单
     */
    CREATED("0", "已下单"),

    /**
     * 已取消
     */
    DELETED("1", "已取消"),

    /**
     * 已到货
     */
    ARRIVAL("2", "已到货"),

    /**
     * 已取货
     */
    PICK_UP("3", "已取货"),

    /**
     * 已完成未评价
     */
    FINISHED_NO("4", "已完成未评价"),

    /**
     * 已完成已评价
     */
    FINISHED_YES("5", "已完成已评价");

    /**
     * The Type.
     */
    String type;
    /**
     * The Name.
     */
    String name;

    OrderStateEnums(String type, String name) {
        this.type = type;
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }


}
