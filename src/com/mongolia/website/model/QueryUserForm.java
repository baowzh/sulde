package com.mongolia.website.model;

public class QueryUserForm {
	private String district;
	private String qx;
	private String username;
	private String strregtime;
	private String endregtime;
	private Integer pageindex;
	private Integer pagesize;
	private String searchtext;
	private String professioncode;

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getQx() {
		return qx;
	}

	public void setQx(String qx) {
		this.qx = qx;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getStrregtime() {
		return strregtime;
	}

	public void setStrregtime(String strregtime) {
		this.strregtime = strregtime;
	}

	public String getEndregtime() {
		return endregtime;
	}

	public void setEndregtime(String endregtime) {
		this.endregtime = endregtime;
	}

	public Integer getPageindex() {
		return pageindex;
	}

	public void setPageindex(Integer pageindex) {
		this.pageindex = pageindex;
	}

	public Integer getPagesize() {
		return pagesize;
	}

	public void setPagesize(Integer pagesize) {
		this.pagesize = pagesize;
	}

	public String getSearchtext() {
		return searchtext;
	}

	public void setSearchtext(String searchtext) {
		this.searchtext = searchtext;
	}

	public String getProfessioncode() {
		return professioncode;
	}

	public void setProfessioncode(String professioncode) {
		this.professioncode = professioncode;
	}

}
