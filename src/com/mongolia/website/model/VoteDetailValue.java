package com.mongolia.website.model;

import java.util.ArrayList;
import java.util.List;

public class VoteDetailValue {
	/**
	 * 问卷id
	 */
	private String voteid;
	/**
	 * 问题id
	 */
	private String questionid;
	/**
	 * 问题类型
	 */
	private Integer questiontype;
	/**
	 * 问题描述
	 */
	private String questiondesc;

	private List<QuestionValue> questionValues = new ArrayList<QuestionValue>();

	public String getVoteid() {
		return voteid;
	}

	public void setVoteid(String voteid) {
		this.voteid = voteid;
	}

	public String getQuestionid() {
		return questionid;
	}

	public void setQuestionid(String questionid) {
		this.questionid = questionid;
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

	public List<QuestionValue> getQuestionValues() {
		return questionValues;
	}

	public void setQuestionValues(List<QuestionValue> questionValues) {
		this.questionValues = questionValues;
	}

}
