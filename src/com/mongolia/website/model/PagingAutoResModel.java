package com.mongolia.website.model;

import java.util.Date;

public class PagingAutoResModel extends PaingModel<AutoResponse> {
	private Date begindate;
	private Date endate;
	private String accountid;
	private String id;
	private Integer defaultmess;

	public Date getBegindate() {
		return begindate;
	}

	public void setBegindate(Date begindate) {
		this.begindate = begindate;
	}

	public Date getEndate() {
		return endate;
	}

	public void setEndate(Date endate) {
		this.endate = endate;
	}

	public String getAccountid() {
		return accountid;
	}

	public void setAccountid(String accountid) {
		this.accountid = accountid;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getDefaultmess() {
		return defaultmess;
	}

	public void setDefaultmess(Integer defaultmess) {
		this.defaultmess = defaultmess;
	}

}
