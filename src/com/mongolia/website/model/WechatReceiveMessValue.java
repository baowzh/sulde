package com.mongolia.website.model;

import java.util.Date;

public class WechatReceiveMessValue {
	/**
	 * 信息id
	 */
	private String messid;
	/**
	 * 信息接收者
	 */
	private String tousername;
	/**
	 * 信息发送者
	 */
	private String fromusername;
	/**
	 * 创建时间
	 */
	private Date createtime;
	/**
	 * 信息类型
	 */
	private String messtype;
	/**
	 * 信息
	 */
	private String content;

	public String getMessid() {
		return messid;
	}

	public void setMessid(String messid) {
		this.messid = messid;
	}

	public String getTousername() {
		return tousername;
	}

	public void setTousername(String tousername) {
		this.tousername = tousername;
	}

	public String getFromusername() {
		return fromusername;
	}

	public void setFromusername(String fromusername) {
		this.fromusername = fromusername;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getMesstype() {
		return messtype;
	}

	public void setMesstype(String messtype) {
		this.messtype = messtype;
	}

}
