package com.xzsd.pc.topOfColumn.entity;

/**
 * @ClassName TopOfColumn
 * @Deprecated 顶部栏管理实体类
 * @Author ywq
 * @Date 2020-04-14
 */
public class TopOfColumn {
    /**
     * 用户名称
     */
    private String userName;
    /**
     * 用户头像
     */
    private String userImage;
    /**
     * 用户编号
     */
    private String userId;
    /**
     * 角色
     */
    private String role;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
