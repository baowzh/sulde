package com.mongolia.website.model;

public class QuestionValue {
	/**
	 * 问题id
	 */
	private String questionid;
	/**
	 * 回复
	 */
	private String answerid;
	/**
	 * 回复描述
	 */
	private String answername;

	private String charindex;

	public String getQuestionid() {
		return questionid;
	}

	public void setQuestionid(String questionid) {
		this.questionid = questionid;
	}

	public String getAnswerid() {
		return answerid;
	}

	public void setAnswerid(String answerid) {
		this.answerid = answerid;
	}

	public String getAnswername() {
		return answername;
	}

	public void setAnswername(String answername) {
		this.answername = answername;
	}

	public String getCharindex() {
		return charindex;
	}

	public void setCharindex(String charindex) {
		this.charindex = charindex;
	}

}
