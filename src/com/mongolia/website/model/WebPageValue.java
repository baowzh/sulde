package com.mongolia.website.model;

public class WebPageValue {
	private String pageid;
	private String pagename;
	private String showtemplateid;
	private String comment;
	private String parentpageid;

	public String getPageid() {
		return pageid;
	}

	public void setPageid(String pageid) {
		this.pageid = pageid;
	}

	public String getPagename() {
		return pagename;
	}

	public void setPagename(String pagename) {
		this.pagename = pagename;
	}

	public String getShowtemplateid() {
		return showtemplateid;
	}

	public void setShowtemplateid(String showtemplateid) {
		this.showtemplateid = showtemplateid;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getParentpageid() {
		return parentpageid;
	}

	public void setParentpageid(String parentpageid) {
		this.parentpageid = parentpageid;
	}

}
