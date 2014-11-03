package com.mongolia.website.model;

import java.util.Date;

public class VisitorValue {
	private String visitorid;
	private String visitorname;
	private Date visitdate;
	private byte[] visitorimg;
	private String visitorimgurl;
	private String userid;
	private Integer visittype;
	private String visitobjid;
	private String headimgsm;
	private Integer visitortype;
	private String visitdatestr;

	public String getVisitorid() {
		return visitorid;
	}

	public void setVisitorid(String visitorid) {
		this.visitorid = visitorid;
	}

	public String getVisitorname() {
		return visitorname;
	}

	public void setVisitorname(String visitorname) {
		this.visitorname = visitorname;
	}

	public byte[] getVisitorimg() {
		return visitorimg;
	}

	public void setVisitorimg(byte[] visitorimg) {
		this.visitorimg = visitorimg;
	}

	public String getVisitorimgurl() {
		return visitorimgurl;
	}

	public void setVisitorimgurl(String visitorimgurl) {
		this.visitorimgurl = visitorimgurl;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public Date getVisitdate() {
		return visitdate;
	}

	public void setVisitdate(Date visitdate) {
		this.visitdate = visitdate;
	}

	public Integer getVisittype() {
		return visittype;
	}

	public void setVisittype(Integer visittype) {
		this.visittype = visittype;
	}

	public String getVisitobjid() {
		return visitobjid;
	}

	public void setVisitobjid(String visitobjid) {
		this.visitobjid = visitobjid;
	}

	public String getHeadimgsm() {
		return headimgsm;
	}

	public void setHeadimgsm(String headimgsm) {
		this.headimgsm = headimgsm;
	}

	public Integer getVisitortype() {
		return visitortype;
	}

	public void setVisitortype(Integer visitortype) {
		this.visitortype = visitortype;
	}

	public String getVisitdatestr() {
		return visitdatestr;
	}

	public void setVisitdatestr(String visitdatestr) {
		this.visitdatestr = visitdatestr;
	}

}
