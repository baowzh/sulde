package com.mongolia.website.model;

import java.util.Date;

public class ImgValue {
	private String imgid;
	private String imgname;
	private String imgurl;
	private String imgicon;
	private String imgdesc;
	private String imggroupid;
	private String userid;
	private String imgcomm;
	private String groupid;
	private Integer cover = 0;
	private Integer width;
	private Integer height;
	private byte[] imgcontent;
	private Date crtime;
	private String crtimestr;
	private Integer sharecount = new Integer(0);
	private Integer markcount = new Integer(0);
	private Integer readcount = new Integer(0);
	private Integer commcount = new Integer(0);
	private String oldid;

	public String getImgid() {
		return imgid;
	}

	public void setImgid(String imgid) {
		this.imgid = imgid;
	}

	public String getImgname() {
		return imgname;
	}

	public void setImgname(String imgname) {
		this.imgname = imgname;
	}

	public String getImgurl() {
		return imgurl;
	}

	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}

	public String getImgicon() {
		return imgicon;
	}

	public void setImgicon(String imgicon) {
		this.imgicon = imgicon;
	}

	public String getImgdesc() {
		return imgdesc;
	}

	public void setImgdesc(String imgdesc) {
		this.imgdesc = imgdesc;
	}

	public String getImggroupid() {
		return imggroupid;
	}

	public void setImggroupid(String imggroupid) {
		this.imggroupid = imggroupid;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getImgcomm() {
		return imgcomm;
	}

	public void setImgcomm(String imgcomm) {
		this.imgcomm = imgcomm;
	}

	public String getGroupid() {
		return groupid;
	}

	public void setGroupid(String groupid) {
		this.groupid = groupid;
	}

	public Integer getCover() {
		return cover;
	}

	public void setCover(Integer cover) {
		this.cover = cover;
	}

	public byte[] getImgcontent() {
		return imgcontent;
	}

	public void setImgcontent(byte[] imgcontent) {
		this.imgcontent = imgcontent;
	}

	public Integer getWidth() {
		return width;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public Integer getSharecount() {
		return sharecount;
	}

	public void setSharecount(Integer sharecount) {
		this.sharecount = sharecount;
	}

	public Integer getMarkcount() {
		return markcount;
	}

	public void setMarkcount(Integer markcount) {
		this.markcount = markcount;
	}

	public Integer getReadcount() {
		return readcount;
	}

	public void setReadcount(Integer readcount) {
		this.readcount = readcount;
	}

	public Integer getCommcount() {
		return commcount;
	}

	public void setCommcount(Integer commcount) {
		this.commcount = commcount;
	}

	public Date getCrtime() {
		return crtime;
	}

	public void setCrtime(Date crtime) {
		this.crtime = crtime;
	}

	public String getCrtimestr() {
		return crtimestr;
	}

	public void setCrtimestr(String crtimestr) {
		this.crtimestr = crtimestr;
	}

	public String getOldid() {
		return oldid;
	}

	public void setOldid(String oldid) {
		this.oldid = oldid;
	}

}
