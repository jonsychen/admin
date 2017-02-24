package com.admin.domain.modle;

import java.io.Serializable;
import java.util.UUID;

/**
 * Created by Jonsy
 * 角色
 *
 */
public class Role implements Serializable {

	private String id = UUID.randomUUID().toString();

	/** 角色名*/
	private String name;

	/** 描述 */
	private String description;

	/** 状态 是否禁用*/
	private boolean disabled;

	public Role(){}

	public Role(String id,String name,String desc){
		this.name=name;
		this.description=desc;
		this.id=id;
	}


	public Role setName(String name) {
		this.name = name;
		return this;
	}

	public String getDescription() {
		return description;
	}

	public Role setDescription(String description) {
		this.description = description;
		return this;
	}

	public String getName() {
		return name;
	}

	public void update(String name,String desc){
		this.setName(name).setDescription(desc);
	}


	public boolean isDisabled() {
		return disabled;
	}

	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}


	public String getId() {
		return id;
	}

	public Role setId(String id) {
		this.id = id;
		return this;
	}


	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Role role = (Role) o;

		return id != null ? id.equals(role.id) : role.id == null;

	}

	@Override
	public int hashCode() {
		return id != null ? id.hashCode() : 0;
	}
}

