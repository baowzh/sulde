package com.mongolia.website.model;

import java.util.Date;

public class QueryDocForm {
	private Integer status;
	private String channel;
	private String authorname;
	private String strcrtime;
	private String endcrtime;
	private String doctitle;
	private Integer top;
	private Integer pageindex;
	private String searchtext;

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getAuthorname() {
		return authorname;
	}

	public void setAuthorname(String authorname) {
		this.authorname = authorname;
	}

	public String getDoctitle() {
		return doctitle;
	}

	public void setDoctitle(String doctitle) {
		this.doctitle = doctitle;
	}

	public String getStrcrtime() {
		return strcrtime;
	}

	public void setStrcrtime(String strcrtime) {
		this.strcrtime = strcrtime;
	}

	public String getEndcrtime() {
		return endcrtime;
	}

	public void setEndcrtime(String endcrtime) {
		this.endcrtime = endcrtime;
	}

	public Integer getTop() {
		return top;
	}

	public void setTop(Integer top) {
		this.top = top;
	}

	public Integer getPageindex() {
		return pageindex;
	}

	public void setPageindex(Integer pageindex) {
		this.pageindex = pageindex;
	}

	public String getSearchtext() {
		return searchtext;
	}

	public void setSearchtext(String searchtext) {
		this.searchtext = searchtext;
	}
	

}
