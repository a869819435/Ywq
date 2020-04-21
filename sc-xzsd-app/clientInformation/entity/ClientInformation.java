package com.xzsd.app.clientInformation.entity;

/**
 * @ClassName ClientInformation
 * @Deripition app客户端客户信息实体类
 * @Author ywq
 * @Date 2020-04-19
 */
public class ClientInformation {
    /**
     * 邀请码
     */
    private String inviteCode;
    /**
     * 用户编号
     */
    private String userId;

    public String getInviteCode() {
        return inviteCode;
    }

    public void setInviteCode(String inviteCode) {
        this.inviteCode = inviteCode;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
