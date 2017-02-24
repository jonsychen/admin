package com.admin.interfaces.facade.dto;

import java.util.Date;


/**
 * @author Jonsy
 *
 */
public class UserDto {

    /** 主键ID */
    private String id;

    /** 登录名称 */
    private String username;


    /** 邮箱 */
    private String email;

    /** 是否禁用 */
    private boolean disabled;

    /** 创建时间 */
    private Date createTime;

    /** 最后登录时间 */
    private Date lastTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastTime() {
        return lastTime;
    }

    public void setLastTime(Date lastTime) {
        this.lastTime = lastTime;
    }
}
