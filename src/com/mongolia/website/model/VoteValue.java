package com.mongolia.website.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class VoteValue {
	private String voteid;
	private String topic;
	private Date createtime;
	private Integer status;
	private String userid;
	private Date enddate;
	private String artname;
	private String createtimestr;
	private Integer joinercount;
	private List<VoteDetailValue> details = new ArrayList<VoteDetailValue>();

	public String getVoteid() {
		return voteid;
	}

	public void setVoteid(String voteid) {
		this.voteid = voteid;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public Date getEnddate() {
		return enddate;
	}

	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}

	public String getCreatetimestr() {
		return createtimestr;
	}

	public void setCreatetimestr(String createtimestr) {
		this.createtimestr = createtimestr;
	}

	public String getArtname() {
		return artname;
	}

	public void setArtname(String artname) {
		this.artname = artname;
	}

	public List<VoteDetailValue> getDetails() {
		return details;
	}

	public void setDetails(List<VoteDetailValue> details) {
		this.details = details;
	}

	public Integer getJoinercount() {
		return joinercount;
	}

	public void setJoinercount(Integer joinercount) {
		this.joinercount = joinercount;
	}

}
