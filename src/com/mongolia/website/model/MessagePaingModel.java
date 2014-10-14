package com.mongolia.website.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MessagePaingModel {
	/**
	 * 每页大小
	 */
	private Integer pagesize = 100;
	/**
	 * 其实行
	 */
	private Integer startrow;
	/**
	 * 终止行
	 */
	private Integer endrow;
	/**
	 * 第几页
	 */
	private Integer pageindex = 1;
	/**
	 * 总行数
	 */
	private String rowcount = "0";
	/**
	 * 总页数
	 */
	private Integer pagecount = 0;
	/**
	 * 上一页索引
	 */
	private Integer previousindex;
	/**
	 * 下一页索引
	 */
	private Integer nextindex;

	private String username;
	/**
	 * 资源状态
	 */
	private Integer status;
	/**
	 * 开始时间
	 */
	private Date startdate;
	/**
	 * 结束时间
	 */
	private Date enddate;

	private List<MessageValue> messlist = new ArrayList<MessageValue>();

	public Integer getPagesize() {
		return pagesize;
	}

	public void setPagesize(Integer pagesize) {
		this.pagesize = pagesize;
	}

	public Integer getStartrow() {
		return startrow;
	}

	public void setStartrow(Integer startrow) {
		this.startrow = startrow;
	}

	public Integer getEndrow() {
		return endrow;
	}

	public void setEndrow(Integer endrow) {
		this.endrow = endrow;
	}

	public Integer getPageindex() {
		return pageindex;
	}

	public void setPageindex(Integer pageindex) {
		this.pageindex = pageindex;
	}

	public String getRowcount() {
		return rowcount;
	}

	public void setRowcount(String rowcount) {
		this.rowcount = rowcount;
	}

	public Integer getPagecount() {
		return pagecount;
	}

	public void setPagecount(Integer pagecount) {
		this.pagecount = pagecount;
	}

	public Integer getPreviousindex() {
		return previousindex;
	}

	public void setPreviousindex(Integer previousindex) {
		this.previousindex = previousindex;
	}

	public Integer getNextindex() {
		return nextindex;
	}

	public void setNextindex(Integer nextindex) {
		this.nextindex = nextindex;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getStartdate() {
		return startdate;
	}

	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}

	public Date getEnddate() {
		return enddate;
	}

	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}

	public List<MessageValue> getMesslist() {
		return messlist;
	}

	public void setMesslist(List<MessageValue> messlist) {
		this.messlist = messlist;
	}
	
}
