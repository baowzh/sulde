package com.mongolia.website.model;

public class PageChannelRelationValue {
	/**
	 * 页面id
	 */
	private String pageid;
	/**
	 * 页面名称
	 */
	private String pagename;
	/**
	 * 栏目id
	 */
	private String channelid;
	/**
	 * 栏目名称
	 */
	private String chnlname;
	/**
	 * 栏目展现方式
	 */
	private Integer channeldisplaytype;
	/**
	 * 栏目明细个数
	 */
	private Integer channeldoccount;
	/**
	 * 列表名称（在网页模板中使用）
	 */
	private String variablename;
	/**
	 * 排序列
	 */
	private String channellistordercol;

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

	public String getChannelid() {
		return channelid;
	}

	public void setChannelid(String channelid) {
		this.channelid = channelid;
	}

	

	public String getChnlname() {
		return chnlname;
	}

	public void setChnlname(String chnlname) {
		this.chnlname = chnlname;
	}

	public Integer getChanneldisplaytype() {
		return channeldisplaytype;
	}

	public void setChanneldisplaytype(Integer channeldisplaytype) {
		this.channeldisplaytype = channeldisplaytype;
	}

	

	public Integer getChanneldoccount() {
		return channeldoccount;
	}

	public void setChanneldoccount(Integer channeldoccount) {
		this.channeldoccount = channeldoccount;
	}

	public String getVariablename() {
		return variablename;
	}

	public void setVariablename(String variablename) {
		this.variablename = variablename;
	}

	public String getChannellistordercol() {
		return channellistordercol;
	}

	public void setChannellistordercol(String channellistordercol) {
		this.channellistordercol = channellistordercol;
	}

}
