package com.admin.domain.modle;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Jonsy
 * 操作日志
 *
 */
public class SysLog implements Serializable {


	/** 用户ID */
	private int uid;

	private String user;

	/** 日志内容 */
	private String content;

	/** 用户操作 */
	private String operation;

	/** 创建时间 */
	private Date createTime;


	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}
}
