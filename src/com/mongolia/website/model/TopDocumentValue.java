package com.mongolia.website.model;

import java.util.Date;
/**
 * 优秀内容
 * @author Administrator
 *
 */
public class TopDocumentValue {
	/**
	 * 文档id
	 */
	private String docid;
	/**
	 * 选择类型
	 */
	private Integer toptype;
	/**
	 * 展示时间（开始）
	 */
	private Date startdate;
	/**
	 * 展示时间(结尾)
	 */
	private Date enddate;
	/**
	 * 组内索引
	 */
	private Integer playindex;
	/**
	 * 标签
	 */
	private byte[] docimg;
	/**
	 * 审核用户id
	 */
	private String checkuserid;
	private String groupid;
	private String title;

	public String getDocid() {
		return docid;
	}

	public void setDocid(String docid) {
		this.docid = docid;
	}

	public Integer getToptype() {
		return toptype;
	}

	public void setToptype(Integer toptype) {
		this.toptype = toptype;
	}

	public Date getStartdate() {
		return startdate;
	}

	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}

	public Date getEnddate() {
		return enddate;
	}

	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}

	public Integer getPlayindex() {
		return playindex;
	}

	public void setPlayindex(Integer playindex) {
		this.playindex = playindex;
	}

	public byte[] getDocimg() {
		return docimg;
	}

	public void setDocimg(byte[] docimg) {
		this.docimg = docimg;
	}

	public String getCheckuserid() {
		return checkuserid;
	}

	public void setCheckuserid(String checkuserid) {
		this.checkuserid = checkuserid;
	}

	public String getGroupid() {
		return groupid;
	}

	public void setGroupid(String groupid) {
		this.groupid = groupid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	

}
