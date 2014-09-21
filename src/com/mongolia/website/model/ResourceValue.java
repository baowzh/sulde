package com.mongolia.website.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@SuppressWarnings("serial")
public class ResourceValue implements Serializable {

	private Integer id;

	private String mold;

	private String content;

	private String descr;

	private Integer enabled;

	private Date createdate;

	private List<RoleValue> roleValues;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMold() {
		return mold;
	}

	public void setMold(String mold) {
		this.mold = mold;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	public Integer getEnabled() {
		return enabled;
	}

	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}

	public Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	public List<RoleValue> getRoles() {
		return roleValues;
	}

	public void setRoles(List<RoleValue> roleValues) {
		this.roleValues = roleValues;
	}

}