package com.mongolia.website.model;

import java.util.List;

/**
 * 分页查询
 * 
 * @author baowzh
 * 
 */
public class PaingModel<E> {
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
	/**
	 * 文章分类
	 */

	private String docchannel;
	/**
	 * 
	 */
	private String username;
	/**
	 * 资源状态
	 */
	private Integer docstatus;
	/**
	 * 资源类型
	 */
	private Integer doctype;
	/**
	 * 文章列表
	 */
	private String userid;
	/**
	 * 图片列表
	 */
	private String imggroupid;

	private List<E> modelList;
	private String pagetype;
	private Integer inindex;

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

	public String getDocchannel() {
		return docchannel;
	}

	public void setDocchannel(String docchannel) {
		this.docchannel = docchannel;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	// public List<E> getDocList() {
	// return docList;
	// }
	//
	// public void setDocList(List<E> docList) {
	// this.docList = docList;
	// }

	public Integer getPreviousindex() {
		return previousindex;
	}

	public List<E> getModelList() {
		return modelList;
	}

	public void setModelList(List<E> modelList) {
		this.modelList = modelList;
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

	public Integer getDoctype() {
		return doctype;
	}

	public void setDoctype(Integer doctype) {
		this.doctype = doctype;
	}

	public Integer getDocstatus() {
		return docstatus;
	}

	public void setDocstatus(Integer docstatus) {
		this.docstatus = docstatus;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getImggroupid() {
		return imggroupid;
	}

	public void setImggroupid(String imggroupid) {
		this.imggroupid = imggroupid;
	}

	public String getPagetype() {
		return pagetype;
	}

	public void setPagetype(String pagetype) {
		this.pagetype = pagetype;
	}

	public Integer getInindex() {
		return inindex;
	}

	public void setInindex(Integer inindex) {
		this.inindex = inindex;
	}

}
