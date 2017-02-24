package com.admin.interfaces.facade.commondobject;


/**
 * @author Jonsy
 *
 */
public class UserCommond {


    /** 登录名称 */
    private String username;

    /** 密码 */
    private String password;

    /** 邮箱 */
    private String email;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
