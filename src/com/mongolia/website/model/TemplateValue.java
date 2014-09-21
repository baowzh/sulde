package com.mongolia.website.model;

public class TemplateValue {
	private String tempid;
	private String tempname;
	private String tempdesc;
	private String tempext;
	private byte[] tempfile;
	private Integer temptype;
	private String temptypestr;

	public String getTempid() {
		return tempid;
	}

	public void setTempid(String tempid) {
		this.tempid = tempid;
	}

	public String getTempname() {
		return tempname;
	}

	public void setTempname(String tempname) {
		this.tempname = tempname;
	}

	public String getTempdesc() {
		return tempdesc;
	}

	public void setTempdesc(String tempdesc) {
		this.tempdesc = tempdesc;
	}

	public String getTempext() {
		return tempext;
	}

	public void setTempext(String tempext) {
		this.tempext = tempext;
	}

	public byte[] getTempfile() {
		return tempfile;
	}

	public void setTempfile(byte[] tempfile) {
		this.tempfile = tempfile;
	}

	public Integer getTemptype() {
		return temptype;
	}

	public void setTemptype(Integer temptype) {
		this.temptype = temptype;
	}

	public String getTemptypestr() {
		return temptypestr;
	}

	public void setTemptypestr(String temptypestr) {
		this.temptypestr = temptypestr;
	}
	

}
