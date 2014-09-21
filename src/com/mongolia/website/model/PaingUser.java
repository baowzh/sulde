package com.mongolia.website.model;

import java.util.ArrayList;
import java.util.List;

public class PaingUser {
	List<UserValue> users = new ArrayList<UserValue>();
	private Integer pageCount;
	private Integer pageindex;
	private Integer usercount;

	public List<UserValue> getUsers() {
		return users;
	}

	public void setUsers(List<UserValue> users) {
		this.users = users;
	}

	public Integer getPageCount() {
		return pageCount;
	}

	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}

	public Integer getPageindex() {
		return pageindex;
	}

	public void setPageindex(Integer pageindex) {
		this.pageindex = pageindex;
	}

	public Integer getUsercount() {
		return usercount;
	}

	public void setUsercount(Integer usercount) {
		this.usercount = usercount;
	}

}
