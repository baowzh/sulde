package com.mongolia.website.model;

public class PagingIndex {
	private Integer pageindex;
	private String link;
	private Integer current = 0;
	private Integer doc = 0;
	private Integer front = 0;
	private String showstr;
	private String id;

	public Integer getPageindex() {
		return pageindex;
	}

	public void setPageindex(Integer pageindex) {
		this.pageindex = pageindex;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public Integer getCurrent() {
		return current;
	}

	public void setCurrent(Integer current) {
		this.current = current;
	}

	public Integer getDoc() {
		return doc;
	}

	public void setDoc(Integer doc) {
		this.doc = doc;
	}

	public Integer getFront() {
		return front;
	}

	public void setFront(Integer front) {
		this.front = front;
	}

	public String getShowstr() {
		return showstr;
	}

	public void setShowstr(String showstr) {
		this.showstr = showstr;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	

}
