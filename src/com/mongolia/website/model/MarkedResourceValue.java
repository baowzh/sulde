package com.mongolia.website.model;

import java.util.Date;

public class MarkedResourceValue {
	private String originalauthorid;
	private String userid;
	private String resourceid;
	private Integer resourcekind;
	private Date markeddate;

	public String getOriginalauthorid() {
		return originalauthorid;
	}

	public void setOriginalauthorid(String originalauthorid) {
		this.originalauthorid = originalauthorid;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getResourceid() {
		return resourceid;
	}

	public void setResourceid(String resourceid) {
		this.resourceid = resourceid;
	}

	public Integer getResourcekind() {
		return resourcekind;
	}

	public void setResourcekind(Integer resourcekind) {
		this.resourcekind = resourcekind;
	}

	public Date getMarkeddate() {
		return markeddate;
	}

	public void setMarkeddate(Date markeddate) {
		this.markeddate = markeddate;
	}

}
