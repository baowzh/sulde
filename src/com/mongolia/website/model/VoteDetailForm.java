package com.mongolia.website.model;

public class VoteDetailForm {
	/**
	 * 问卷id
	 */
	String voteid;
	/**
	 * 问题类型
	 */
	Integer questiontype;
	/**
	 * 问题描述
	 */
	String questiondesc;
	/**
	 * 问题选项
	 */
	String selections;

	public String getVoteid() {
		return voteid;
	}

	public void setVoteid(String voteid) {
		this.voteid = voteid;
	}

	public String getQuestiondesc() {
		return questiondesc;
	}

	public void setQuestiondesc(String questiondesc) {
		this.questiondesc = questiondesc;
	}

	public String getSelections() {
		return selections;
	}

	public void setSelections(String selections) {
		this.selections = selections;
	}

	public Integer getQuestiontype() {
		return questiontype;
	}

	public void setQuestiontype(Integer questiontype) {
		this.questiontype = questiontype;
	}
	

}
