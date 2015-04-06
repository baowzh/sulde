package com.mongolia.website.model;

import java.util.Date;

public class RaceDocumentValue {
	private String raceid;
	private String docid;
	private Date joindate;
	private Double nettotalscore;
	private Double netaveragescore;
	private Double spetotalscore;
	private Double speaveragescore;
	private Double finalscore;
	private String joinuserid;
	private Integer netscorecount;
	private Integer spescorecount;
	private Integer raceround;
	/**
	 * 1 成人比赛 2 儿童比赛
	 */
	private Integer jointype;
	private String raicevalidcode;
	private Integer scorecountl;
	private Integer usertype;
	private String doctitle;

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

	public Date getJoindate() {
		return joindate;
	}

	public void setJoindate(Date joindate) {
		this.joindate = joindate;
	}

	public Double getNettotalscore() {
		return nettotalscore;
	}

	public void setNettotalscore(Double nettotalscore) {
		this.nettotalscore = nettotalscore;
	}

	public Double getNetaveragescore() {
		return netaveragescore;
	}

	public void setNetaveragescore(Double netaveragescore) {
		this.netaveragescore = netaveragescore;
	}

	public Double getSpetotalscore() {
		return spetotalscore;
	}

	public void setSpetotalscore(Double spetotalscore) {
		this.spetotalscore = spetotalscore;
	}

	public Double getSpeaveragescore() {
		return speaveragescore;
	}

	public void setSpeaveragescore(Double speaveragescore) {
		this.speaveragescore = speaveragescore;
	}

	public Double getFinalscore() {
		return finalscore;
	}

	public void setFinalscore(Double finalscore) {
		this.finalscore = finalscore;
	}

	public String getJoinuserid() {
		return joinuserid;
	}

	public void setJoinuserid(String joinuserid) {
		this.joinuserid = joinuserid;
	}

	public Integer getJointype() {
		return jointype;
	}

	public void setJointype(Integer jointype) {
		this.jointype = jointype;
	}

	public String getRaicevalidcode() {
		return raicevalidcode;
	}

	public void setRaicevalidcode(String raicevalidcode) {
		this.raicevalidcode = raicevalidcode;
	}

	public Integer getScorecountl() {
		return scorecountl;
	}

	public void setScorecountl(Integer scorecountl) {
		this.scorecountl = scorecountl;
	}

	public Integer getNetscorecount() {
		return netscorecount;
	}

	public void setNetscorecount(Integer netscorecount) {
		this.netscorecount = netscorecount;
	}

	public Integer getSpescorecount() {
		return spescorecount;
	}

	public void setSpescorecount(Integer spescorecount) {
		this.spescorecount = spescorecount;
	}

	public Integer getUsertype() {
		return usertype;
	}

	public void setUsertype(Integer usertype) {
		this.usertype = usertype;
	}

	public Integer getRaceround() {
		return raceround;
	}

	public void setRaceround(Integer raceround) {
		this.raceround = raceround;
	}

	public String getDoctitle() {
		return doctitle;
	}

	public void setDoctitle(String doctitle) {
		this.doctitle = doctitle;
	}

}
