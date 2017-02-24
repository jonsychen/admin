package com.admin.interfaces.facade.dto;

/**
 * 用户个人信息
 *
 * @author Jonsy
 */
public class MyProfile {

    private String username;

    private String email;

    public MyProfile(String username, String email) {
        this.username = username;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
