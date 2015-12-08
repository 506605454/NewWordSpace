package com.hqw.huangye.bean;

/**
 * Created by 506605454 on 2015/11/30.
 */
public class Users {
    private String userId;
    private String userAccount;
    private String password;

    @Override
    public String toString() {
        return "Users{" +
                "userId='" + userId + '\'' +
                ", userAccount='" + userAccount + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
