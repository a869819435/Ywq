package com.xzsd.pc.goods.enums;

/**
 * 商品管理接口类
 * @ClassName GoodsStateEnums
 * @Description 商品状态枚举
 * @Author ywq
 * @Date 2020-03-26
 */
public enum GoodsStateEnums {

    /**
     * 售罄
     */
    SOLD_OUT("0", "售罄"),

    /**
     * 在售
     */
    ON_SALE("1", "在售"),

    /**
     * 已下架
     */
    STOP_SOLD("2", "已下架"),

    /**
     * 未发布
     */
    NO_RELEASE("3", "未发布");

    /**
     * The Type.
     */
    String type;
    /**
     * The Name.
     */
    String name;

    GoodsStateEnums(String type, String name) {
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
