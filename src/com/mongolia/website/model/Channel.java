package com.mongolia.website.model;

import java.util.Date;
import java.util.List;

public class Channel {

	private String channelid;
	private String siteid;
	private String chnlname;
	private String chnldesc;
	private String chnltable;
	private String chnlquery;
	private Double chnlprop;
	private String parentid;
	private Integer chnlorder;
	private Double schedule;
	private String chnldatapath;
	private String chnlorderby;
	private String attribute;
	private String cruser;
	private Date crtime;
	private Integer status;
	private Integer chnltype;
	private Double chnloutlinetemp;
	private Double chnldetailtemp;
	private Double publishpro;
	private String operuser;
	private Date opertime;
	private String linkurl;
	private String contentaddeditpage;
	private String contentlistpage;
	private String contentshowpage;
	private String outlinefileds;
	private Double acceptnode;
	private List<DocumentValue> docs;

	public String getChannelid() {
		return channelid;
	}

	public void setChannelid(String channelid) {
		this.channelid = channelid;
	}

	public String getSiteid() {
		return siteid;
	}

	public void setSiteid(String siteid) {
		this.siteid = siteid;
	}

	public void setParentid(String parentid) {
		this.parentid = parentid;
	}

	public void setChnlorder(Integer chnlorder) {
		this.chnlorder = chnlorder;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public void setChnltype(Integer chnltype) {
		this.chnltype = chnltype;
	}

	public String getChnlname() {
		return chnlname;
	}

	public void setChnlname(String chnlname) {
		this.chnlname = chnlname;
	}

	public String getChnldesc() {
		return chnldesc;
	}

	public void setChnldesc(String chnldesc) {
		this.chnldesc = chnldesc;
	}

	public String getChnltable() {
		return chnltable;
	}

	public void setChnltable(String chnltable) {
		this.chnltable = chnltable;
	}

	public String getChnlquery() {
		return chnlquery;
	}

	public void setChnlquery(String chnlquery) {
		this.chnlquery = chnlquery;
	}

	public Double getChnlprop() {
		return chnlprop;
	}

	public void setChnlprop(Double chnlprop) {
		this.chnlprop = chnlprop;
	}

	public String getParentid() {
		return parentid;
	}

	public Integer getChnlorder() {
		return chnlorder;
	}

	public Double getSchedule() {
		return schedule;
	}

	public void setSchedule(Double schedule) {
		this.schedule = schedule;
	}

	public String getChnldatapath() {
		return chnldatapath;
	}

	public void setChnldatapath(String chnldatapath) {
		this.chnldatapath = chnldatapath;
	}

	public String getChnlorderby() {
		return chnlorderby;
	}

	public void setChnlorderby(String chnlorderby) {
		this.chnlorderby = chnlorderby;
	}

	public String getAttribute() {
		return attribute;
	}

	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}

	public String getCruser() {
		return cruser;
	}

	public void setCruser(String cruser) {
		this.cruser = cruser;
	}

	public Date getCrtime() {
		return crtime;
	}

	public void setCrtime(Date crtime) {
		this.crtime = crtime;
	}

	public Integer getStatus() {
		return status;
	}

	public Integer getChnltype() {
		return chnltype;
	}

	public Double getChnloutlinetemp() {
		return chnloutlinetemp;
	}

	public void setChnloutlinetemp(Double chnloutlinetemp) {
		this.chnloutlinetemp = chnloutlinetemp;
	}

	public Double getChnldetailtemp() {
		return chnldetailtemp;
	}

	public void setChnldetailtemp(Double chnldetailtemp) {
		this.chnldetailtemp = chnldetailtemp;
	}

	public Double getPublishpro() {
		return publishpro;
	}

	public void setPublishpro(Double publishpro) {
		this.publishpro = publishpro;
	}

	public String getOperuser() {
		return operuser;
	}

	public void setOperuser(String operuser) {
		this.operuser = operuser;
	}

	public Date getOpertime() {
		return opertime;
	}

	public void setOpertime(Date opertime) {
		this.opertime = opertime;
	}

	public String getLinkurl() {
		return linkurl;
	}

	public void setLinkurl(String linkurl) {
		this.linkurl = linkurl;
	}

	public String getContentaddeditpage() {
		return contentaddeditpage;
	}

	public void setContentaddeditpage(String contentaddeditpage) {
		this.contentaddeditpage = contentaddeditpage;
	}

	public String getContentlistpage() {
		return contentlistpage;
	}

	public void setContentlistpage(String contentlistpage) {
		this.contentlistpage = contentlistpage;
	}

	public String getContentshowpage() {
		return contentshowpage;
	}

	public void setContentshowpage(String contentshowpage) {
		this.contentshowpage = contentshowpage;
	}

	public String getOutlinefileds() {
		return outlinefileds;
	}

	public void setOutlinefileds(String outlinefileds) {
		this.outlinefileds = outlinefileds;
	}

	public Double getAcceptnode() {
		return acceptnode;
	}

	public void setAcceptnode(Double acceptnode) {
		this.acceptnode = acceptnode;
	}

	public List<DocumentValue> getDocs() {
		return docs;
	}

	public void setDocs(List<DocumentValue> docs) {
		this.docs = docs;
	}
	

}
