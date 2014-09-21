package com.mongolia.website.model;

public class ChannelDocValue {
	/**
	 * 栏目id
	 */
	private String channelid;
	/**
	 * 文档id
	 */
	private String docid;
	/**
	 * 权值
	 */
	private Integer priority;
	/**
	 * 图片id
	 */
	private String imgid;
	
	public String getChannelid() {
		return channelid;
	}
	public void setChannelid(String channelid) {
		this.channelid = channelid;
	}
	public String getDocid() {
		return docid;
	}
	public void setDocid(String docid) {
		this.docid = docid;
	}
	public Integer getPriority() {
		return priority;
	}
	public void setPriority(Integer priority) {
		this.priority = priority;
	}
	public String getImgid() {
		return imgid;
	}
	public void setImgid(String imgid) {
		this.imgid = imgid;
	}
	
}
