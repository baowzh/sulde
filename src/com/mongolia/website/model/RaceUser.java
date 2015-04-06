package com.mongolia.website.model;

import java.util.List;

public class RaceUser {
	/**
	 * 参与比赛用户
	 */
	private UserValue uservalue;
	private Double maxscore;
	/**
	 * 参与作品集合
	 */
	private List<RaceDocumentValue> raceDocumentValues;

	public UserValue getUservalue() {
		return uservalue;
	}

	public void setUservalue(UserValue uservalue) {
		this.uservalue = uservalue;
	}

	public List<RaceDocumentValue> getRaceDocumentValues() {
		return raceDocumentValues;
	}

	public void setRaceDocumentValues(List<RaceDocumentValue> raceDocumentValues) {
		this.raceDocumentValues = raceDocumentValues;
	}

	public Double getMaxscore() {
		return maxscore;
	}

	public void setMaxscore(Double maxscore) {
		this.maxscore = maxscore;
	}
	

}
