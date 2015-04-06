package com.mongolia.website.model;

import java.util.Date;

public class PagingAutoResModel extends PaingModel<AutoResponse> {
	private Date begindate;
	private Date endate;
	private String accountid;

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

}
