package com.admin.domain.modle;

import java.io.Serializable;
import java.util.UUID;

/**
 * Created by Jonsy
 * url资源
 */
public class Resource implements Serializable {

	/** 唯一资源编码 */
	private String id = UUID.randomUUID().toString();

	/** 资源名称 */
	private String title;

	/** 状态 是否禁用*/
	private boolean disabled;

	/** 地址 */
	private String url;

	/** 描述 */
	private String description;


	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}


	public boolean isDisabled() {
		return disabled;
	}

	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Resource resource = (Resource) o;

		return id != null ? id.equals(resource.id) : resource.id == null;

	}

	@Override
	public int hashCode() {
		return id != null ? id.hashCode() : 0;
	}
}
