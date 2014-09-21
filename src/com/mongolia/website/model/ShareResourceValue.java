package com.mongolia.website.model;

import java.util.Date;

public class ShareResourceValue {
	/**
	 * 原用户id
	 */
	private String originalauthorid;
	/**
	 * 用户id
	 */
	private String userid;
	/**
	 * 资源id
	 */
	private String resourceid;
	/**
	 * 资源类型
	 */
	private Integer resourcekind;
	/**
	 * 分享时间
	 */
	private Date sharedate;

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

	public Date getSharedate() {
		return sharedate;
	}

	public void setSharedate(Date sharedate) {
		this.sharedate = sharedate;
	}

}
