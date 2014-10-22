package com.mongolia.website.model;

import java.util.ArrayList;
import java.util.List;

public class PaingVoteResult {
	//List<VoteResultValue> results = new ArrayList<VoteResultValue>();
	List<?> results = new ArrayList<VoteResultValue>();
	private Integer pageCount;
	private Integer pageindex;
	private Integer resultcount;

	public Integer getPageCount() {
		return pageCount;
	}

	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}

	public Integer getPageindex() {
		return pageindex;
	}

	public void setPageindex(Integer pageindex) {
		this.pageindex = pageindex;
	}

	public Integer getResultcount() {
		return resultcount;
	}

	public void setResultcount(Integer resultcount) {
		this.resultcount = resultcount;
	}

	public List<?> getResults() {
		return results;
	}

	public void setResults(List<?> results) {
		this.results = results;
	}
	

}
