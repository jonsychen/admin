package com.admin.domain.modle;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

/**
 * Created by Jonsy
 * 用户
 *
 */
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	/** 主键ID */
	private String id = UUID.randomUUID().toString();

	/** 登录名称 */
	private String username;

	/** 密码 */
	private String password;

	/**密码加密的盐*/
	private String salt;

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

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public boolean isRoot(){
		return "root".equals(username);
	}
}
