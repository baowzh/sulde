package com.mongolia.website.model;

import java.util.List;

public class PageValue<T> {

	private List<T> models;
	private Integer totalrowcount;
	private Integer pagecount;
	private Integer pagesize;
	

	public List<T> getModels() {
		return models;
	}

	public void setModels(List<T> models) {
		this.models = models;
	}

	

	public Integer getTotalrowcount() {
		return totalrowcount;
	}

	public void setTotalrowcount(Integer totalrowcount) {
		this.totalrowcount = totalrowcount;
	}

	public Integer getPagecount() {
		return pagecount;
	}

	public void setPagecount(Integer pagecount) {
		this.pagecount = pagecount;
	}

	public Integer getPagesize() {
		return pagesize;
	}

	public void setPagesize(Integer pagesize) {
		this.pagesize = pagesize;
	}
	
}
