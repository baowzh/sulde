package com.mongolia.website.model;

import java.util.Date;

/**
 * 意见建议
 * 
 * @author Administrator
 * 
 */
public class OpinionValue {
	private String opinionid;
	/**
	 * 标题
	 */
	private String title;
	/**
	 * 内容
	 */
	private byte[] content;
	/**
	 * 上传时间
	 */
	private Date uploaddate;
	/**
	 * 已阅读
	 */
	private Integer readed;

	private String userid;
	private String artname;
	private String htmlstr;

	public String getOpinionid() {
		return opinionid;
	}

	public void setOpinionid(String opinionid) {
		this.opinionid = opinionid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public byte[] getContent() {
		return content;
	}

	public void setContent(byte[] content) {
		this.content = content;
	}

	public Date getUploaddate() {
		return uploaddate;
	}

	public void setUploaddate(Date uploaddate) {
		this.uploaddate = uploaddate;
	}

	public Integer getReaded() {
		return readed;
	}

	public void setReaded(Integer readed) {
		this.readed = readed;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getArtname() {
		return artname;
	}

	public void setArtname(String artname) {
		this.artname = artname;
	}

	public String getHtmlstr() {
		return htmlstr;
	}

	public void setHtmlstr(String htmlstr) {
		this.htmlstr = htmlstr;
	}

}
