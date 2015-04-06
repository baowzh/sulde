package com.mongolia.website.model;

import java.util.Date;

public class RaceScoreLogValue {
	private String raceid;
	private String docid;
	private Date scoredate;
	private String scoreuserid;
	private Integer usertype;
	private Double score;
	private Integer round;
	private String headurl;
	private String artname;
	public String getRaceid() {
		return raceid;
	}

	public void setRaceid(String raceid) {
		this.raceid = raceid;
	}

	public String getDocid() {
		return docid;
	}

	public void setDocid(String docid) {
		this.docid = docid;
	}

	public Date getScoredate() {
		return scoredate;
	}

	public void setScoredate(Date scoredate) {
		this.scoredate = scoredate;
	}

	public String getScoreuserid() {
		return scoreuserid;
	}

	public void setScoreuserid(String scoreuserid) {
		this.scoreuserid = scoreuserid;
	}

	public Integer getUsertype() {
		return usertype;
	}

	public void setUsertype(Integer usertype) {
		this.usertype = usertype;
	}

	public Double getScore() {
		return score;
	}

	public void setScore(Double score) {
		this.score = score;
	}

	public Integer getRound() {
		return round;
	}

	public void setRound(Integer round) {
		this.round = round;
	}

	public String getHeadurl() {
		return headurl;
	}

	public void setHeadurl(String headurl) {
		this.headurl = headurl;
	}

	public String getArtname() {
		return artname;
	}

	public void setArtname(String artname) {
		this.artname = artname;
	}

}
