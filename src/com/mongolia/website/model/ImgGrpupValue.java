package com.mongolia.website.model;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 用户相册实体bean
 * 
 * @author baowzh
 * 
 */
public class ImgGrpupValue {
	private String imggroupid;
	private String imggroupname;
	private String imggroupkind;
	private String userid;
	private byte[] faceimg;
	private String comm;
	private Date createdtime;
	private String createdtimestr;
	private String oldid;
	private String faceurl;
	private byte[] imgurl;

	public String getImggroupid() {
		return imggroupid;
	}

	public void setImggroupid(String imggroupid) {
		this.imggroupid = imggroupid;
	}

	public String getImggroupname() {
		return imggroupname;
	}

	public void setImggroupname(String imggroupname) {
		this.imggroupname = imggroupname;
	}

	public String getImggroupkind() {
		return imggroupkind;
	}

	public void setImggroupkind(String imggroupkind) {
		this.imggroupkind = imggroupkind;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getComm() {
		return comm;
	}

	public void setComm(String comm) {
		this.comm = comm;
	}

	public byte[] getFaceimg() {
		return faceimg;
	}

	public void setFaceimg(byte[] faceimg) {
		this.faceimg = faceimg;
	}

	public Date getCreatedtime() {
		return createdtime;
	}

	public void setCreatedtime(Date createdtime) {
		this.createdtime = createdtime;
	}

	public String getCreatedtimestr() {
		java.text.SimpleDateFormat dataformater = new SimpleDateFormat(
				"yyyy-MM-dd");
		return dataformater.format(this.getCreatedtime());
		// return createdtimestr;
	}

	public void setCreatedtimestr(String createdtimestr) {
		this.createdtimestr = createdtimestr;
	}

	public String getOldid() {
		return oldid;
	}

	public void setOldid(String oldid) {
		this.oldid = oldid;
	}

	public String getFaceurl() {
		return faceurl;
	}

	public void setFaceurl(String faceurl) {
		this.faceurl = faceurl;
	}

	public byte[] getImgurl() {
		return imgurl;
	}

	public void setImgurl(byte[] imgurl) {
		this.imgurl = imgurl;
	}

}
