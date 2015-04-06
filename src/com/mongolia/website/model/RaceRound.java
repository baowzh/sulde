package com.mongolia.website.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class RaceRound {
	private  String raceid;
	private Integer raceround;
	private Date begindate;
	private Date enddate;
	private Date netuserendtime;
    private String begindatestr;
    private String enddatestr;
    private Date childtime;
	public String getRaceid() {
		return raceid;
	}

	public void setRaceid(String raceid) {
		this.raceid = raceid;
	}

	public Integer getRaceround() {
		return raceround;
	}

	public void setRaceround(Integer raceround) {
		this.raceround = raceround;
	}

	public Date getBegindate() {
		return begindate;
	}

	public void setBegindate(Date begindate) {
		this.begindate = begindate;
	}

	public Date getEnddate() {
		return enddate;
	}

	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}

	public Date getNetuserendtime() {
		return netuserendtime;
	}

	public void setNetuserendtime(Date netuserendtime) {
		this.netuserendtime = netuserendtime;
	}

	public String getBegindatestr() {
		if (this.begindate == null) {
			return null;
		} else {
			java.text.SimpleDateFormat formar = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			return formar.format(begindate);
		}
	}

	public void setBegindatestr(String begindatestr) {
		this.begindatestr = begindatestr;
	}

	public String getEnddatestr() {
		if (this.getEnddate() == null) {
			return null;
		} else {
			java.text.SimpleDateFormat formar = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			return formar.format(this.getEnddate());
		}
	}

	public void setEnddatestr(String enddatestr) {
		this.enddatestr = enddatestr;
	}

	public Date getChildtime() {
		return childtime;
	}

	public void setChildtime(Date childtime) {
		this.childtime = childtime;
	}
	
}
