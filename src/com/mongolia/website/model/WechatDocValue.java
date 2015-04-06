package com.mongolia.website.model;

import java.util.Date;

public class WechatDocValue {
	/**
	 * 自动回复设置id
	 */
	private String responseid;
	/**
	 * 文件id
	 */
	private String docid;
	/**
	 * 附带图片位置
	 */
	private String docimg;
	/**
	 * 文档标题
	 */
	private String doctitle;
	/**
	 * 文档摘要
	 */
	private String docabc;
	/**
	 * 选送时间
	 */
	private Date seldate;
	/**
	 * 
	 */
	private String docurl;

	public String getResponseid() {
		return responseid;
	}

	public void setResponseid(String responseid) {
		this.responseid = responseid;
	}

	public String getDocid() {
		return docid;
	}

	public void setDocid(String docid) {
		this.docid = docid;
	}

	public String getDocimg() {
		return docimg;
	}

	public void setDocimg(String docimg) {
		this.docimg = docimg;
	}

	public String getDoctitle() {
		return doctitle;
	}

	public void setDoctitle(String doctitle) {
		this.doctitle = doctitle;
	}

	public String getDocabc() {
		return docabc;
	}

	public void setDocabc(String docabc) {
		this.docabc = docabc;
	}

	public Date getSeldate() {
		return seldate;
	}

	public void setSeldate(Date seldate) {
		this.seldate = seldate;
	}

	public String getDocurl() {
		return docurl;
	}

	public void setDocurl(String docurl) {
		this.docurl = docurl;
	}

}
