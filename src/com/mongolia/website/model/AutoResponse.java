package com.mongolia.website.model;

public class AutoResponse {
	private String keyword;
	private String rescontent;
	private String templatename;// 关联模板名称
	private String addtime;
	private String msgtype;
	private String accountid;
	private String id;
	private String servicveid;
	private Integer defaultmess;

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getRescontent() {
		return rescontent;
	}

	public void setRescontent(String rescontent) {
		this.rescontent = rescontent;
	}

	public String getTemplatename() {
		return templatename;
	}

	public void setTemplatename(String templatename) {
		this.templatename = templatename;
	}

	public String getAddtime() {
		return addtime;
	}

	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}

	public String getMsgtype() {
		return msgtype;
	}

	public void setMsgtype(String msgtype) {
		this.msgtype = msgtype;
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

	public String getServicveid() {
		return servicveid;
	}

	public void setServicveid(String servicveid) {
		this.servicveid = servicveid;
	}

	public Integer getDefaultmess() {
		return defaultmess;
	}

	public void setDefaultmess(Integer defaultmess) {
		this.defaultmess = defaultmess;
	}

}
