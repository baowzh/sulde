package com.mongolia.website.model;

import java.util.Date;

public class ProgramItem {
	/**
	 * itemid
	 */
	private String itemid;
	/**
	 * programkind
	 * 
	 */
	private String programkind;
	/**
	 * 标题
	 */
	private String title;
	/**
	 * 发布日期
	 */
	private Date releaseDate;

	private String releaseDateStr;
	/**
	 * 审核日期
	 */
	private Date checkDate;
	/**
	 * 已审核
	 */
	private Integer ischecked;
	/**
	 * 内容
	 */
	private byte[] content;
	/**
	 * 作者
	 */
	private String authorname;
	private String auhtorid;
	/**
	 * 是否在主页上显示
	 */
	private Integer displayInIndex;
	/**
	 * 此item 特有的效果，比如大字体，红色 等
	 */
	private String css;
	/**
	 * 明细列表上的图片
	 */
	private String itemIcon;
	private String imgurl;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public Date getCheckDate() {
		return checkDate;
	}

	public void setCheckDate(Date checkDate) {
		this.checkDate = checkDate;
	}

	public Integer getIschecked() {
		return ischecked;
	}

	public void setIschecked(Integer ischecked) {
		this.ischecked = ischecked;
	}

	public byte[] getContent() {
		return content;
	}

	public void setContent(byte[] content) {
		this.content = content;
	}

	public String getAuthorname() {
		return authorname;
	}

	public void setAuthorname(String authorname) {
		this.authorname = authorname;
	}

	public String getAuhtorid() {
		return auhtorid;
	}

	public void setAuhtorid(String auhtorid) {
		this.auhtorid = auhtorid;
	}

	public Integer getDisplayInIndex() {
		return displayInIndex;
	}

	public void setDisplayInIndex(Integer displayInIndex) {
		this.displayInIndex = displayInIndex;
	}

	public String getCss() {
		return css;
	}

	public void setCss(String css) {
		this.css = css;
	}

	public String getItemIcon() {
		return itemIcon;
	}

	public void setItemIcon(String itemIcon) {
		this.itemIcon = itemIcon;
	}

	public String getProgramkind() {
		return programkind;
	}

	public void setProgramkind(String programkind) {
		this.programkind = programkind;
	}

	public String getItemid() {
		return itemid;
	}

	public void setItemid(String itemid) {
		this.itemid = itemid;
	}

	public String getReleaseDateStr() {
		return releaseDateStr;
	}

	public void setReleaseDateStr(String releaseDateStr) {
		this.releaseDateStr = releaseDateStr;
	}

	public String getImgurl() {
		return imgurl;
	}

	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}

}
