package com.mongolia.website.model;

public class VoteResultDetailValue {
	private String questionid;
	private String joinerid;
	private String answerid;
	private String answerdesc;
	private Double percentage;
	private Long votenum;
	private String charindex;

	public String getQuestionid() {
		return questionid;
	}

	public void setQuestionid(String questionid) {
		this.questionid = questionid;
	}

	public String getJoinerid() {
		return joinerid;
	}

	public void setJoinerid(String joinerid) {
		this.joinerid = joinerid;
	}

	public String getAnswerid() {
		return answerid;
	}

	public void setAnswerid(String answerid) {
		this.answerid = answerid;
	}

	public String getAnswerdesc() {
		return answerdesc;
	}

	public void setAnswerdesc(String answerdesc) {
		this.answerdesc = answerdesc;
	}

	public Double getPercentage() {
		return percentage;
	}

	public void setPercentage(Double percentage) {
		this.percentage = percentage;
	}

	public Long getVotenum() {
		return votenum;
	}

	public void setVotenum(Long votenum) {
		this.votenum = votenum;
	}

	public String getCharindex() {
		return charindex;
	}

	public void setCharindex(String charindex) {
		this.charindex = charindex;
	}
	

}
