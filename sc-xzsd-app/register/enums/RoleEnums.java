package com.xzsd.app.register.enums;

/**
 * @ClassName RoleEnums
 * @Deprecation 角色信息枚举
 * @Author ywq
 * @Date 2020-04-15
 */
public enum RoleEnums {

    /**
     * 超级管理员
     */
    SUPPER_ADMIM("0", "超级管理员"),

    /**
     * 管理员
     */
    ADMIN("1", "管理员"),

    /**
     * 店长
     */
    MANAGE("2", "店长"),

    /**
     * 店长
     */
    DRIVER("3", "司机"),

    /**
     * 店长
     */
    CLIENT("4", "客户");

    /**
     * The Type.
     */
    String type;
    /**
     * The Name.
     */
    String name;

    RoleEnums(String type, String name) {
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
