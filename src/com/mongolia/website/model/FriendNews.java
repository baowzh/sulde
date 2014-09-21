package com.mongolia.website.model;

import java.util.Date;

/**
 * 
 * @author Administrator
 *
 */
public class FriendNews extends UserValue {

	private String doctitle;
	private String docid;
	private Integer doctype;
	private Date docreltime;
	private Integer type;
	private Date dydate;
	private String docauthor;
	private String docabstract;

	public String getDoctitle() {
		return doctitle;
	}

	public void setDoctitle(String doctitle) {
		this.doctitle = doctitle;
	}

	public Integer getDoctype() {
		return doctype;
	}

	public void setDoctype(Integer doctype) {
		this.doctype = doctype;
	}

	public Date getDocreltime() {
		return docreltime;
	}

	public void setDocreltime(Date docreltime) {
		this.docreltime = docreltime;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Date getDydate() {
		return dydate;
	}

	public void setDydate(Date dydate) {
		this.dydate = dydate;
	}

	public String getDocauthor() {
		return docauthor;
	}

	public void setDocauthor(String docauthor) {
		this.docauthor = docauthor;
	}

	public String getDocid() {
		return docid;
	}

	public void setDocid(String docid) {
		this.docid = docid;
	}

	public String getDocabstract() {
		return docabstract;
	}

	public void setDocabstract(String docabstract) {
		this.docabstract = docabstract;
	}
	

}
