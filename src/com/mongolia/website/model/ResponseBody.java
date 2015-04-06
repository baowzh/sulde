package com.mongolia.website.model;

public class ResponseBody {
	/**
	 * 标题
	 */
	private String title;
	/**
	 * 返回文本
	 */
	private String desc;
	/**
	 * 返回消息类型
	 */
	private String messtype;
	/**
	 * 图文消息的url
	 */
	private String url;
	/**
	 * 图片url
	 */
	private String picurl;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getMesstype() {
		return messtype;
	}
	public void setMesstype(String messtype) {
		this.messtype = messtype;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getPicurl() {
		return picurl;
	}
	public void setPicurl(String picurl) {
		this.picurl = picurl;
	}
	

}
