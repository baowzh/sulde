package com.mongolia.website.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class VoteResultValue {
	private String voteid;
	private String joinerid;
	private String questionid;
	private Date votedate;
	private Integer questiontype;
	private String questiondesc;
	private String answerid;
	private Long votenum;
	private String artname;
	List<VoteResultDetailValue> detail = new ArrayList<VoteResultDetailValue>();

	public String getVoteid() {
		return voteid;
	}

	public void setVoteid(String voteid) {
		this.voteid = voteid;
	}

	public String getJoinerid() {
		return joinerid;
	}

	public void setJoinerid(String joinerid) {
		this.joinerid = joinerid;
	}

	public String getQuestionid() {
		return questionid;
	}

	public void setQuestionid(String questionid) {
		this.questionid = questionid;
	}

	public Date getVotedate() {
		return votedate;
	}

	public void setVotedate(Date votedate) {
		this.votedate = votedate;
	}

	public Integer getQuestiontype() {
		return questiontype;
	}

	public void setQuestiontype(Integer questiontype) {
		this.questiontype = questiontype;
	}

	public String getQuestiondesc() {
		return questiondesc;
	}

	public void setQuestiondesc(String questiondesc) {
		this.questiondesc = questiondesc;
	}

	public List<VoteResultDetailValue> getDetail() {
		return detail;
	}

	public void setDetail(List<VoteResultDetailValue> detail) {
		this.detail = detail;
	}

	public String getAnswerid() {
		return answerid;
	}

	public void setAnswerid(String answerid) {
		this.answerid = answerid;
	}

	public Long getVotenum() {
		return votenum;
	}

	public void setVotenum(Long votenum) {
		this.votenum = votenum;
	}

	public String getArtname() {
		return artname;
	}

	public void setArtname(String artname) {
		this.artname = artname;
	}
	

}
