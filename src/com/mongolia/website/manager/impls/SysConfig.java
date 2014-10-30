package com.mongolia.website.manager.impls;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("configInfo")
public class SysConfig {

	@Value("${sitename}")
	private String sitename;

	@Value("${recentlogusercount}")
	private Integer recentlogusercount;
	@Value("${recentcommcount}")
	private Integer recentcommcount;
	@Value("${topdocumentcount}")
	private Integer topdocumentcount;
	@Value("${selecteddoccount}")
	private Integer selecteddoccount;
	@Value("${newusercount}")
	private Integer newusercount;
	@Value("${topusercount}")
	private Integer topusercount;
	@Value("${hotdoccount}")
	private Integer hotdoccount;
	@Value("${recentdoccount}")
	private Integer recentdoccount;
	@Value("${activeusercount}")
	private Integer activeusercount;
	@Value("${online}")
	private Integer online;

	public String getSitename() {
		return sitename;
	}

	public void setSitename(String sitename) {
		this.sitename = sitename;
	}

	public Integer getRecentlogusercount() {
		return recentlogusercount;
	}

	public void setRecentlogusercount(Integer recentlogusercount) {
		this.recentlogusercount = recentlogusercount;
	}

	public Integer getRecentcommcount() {
		return recentcommcount;
	}

	public void setRecentcommcount(Integer recentcommcount) {
		this.recentcommcount = recentcommcount;
	}

	public Integer getTopdocumentcount() {
		return topdocumentcount;
	}

	public void setTopdocumentcount(Integer topdocumentcount) {
		this.topdocumentcount = topdocumentcount;
	}

	public Integer getSelecteddoccount() {
		return selecteddoccount;
	}

	public void setSelecteddoccount(Integer selecteddoccount) {
		this.selecteddoccount = selecteddoccount;
	}

	public Integer getNewusercount() {
		return newusercount;
	}

	public void setNewusercount(Integer newusercount) {
		this.newusercount = newusercount;
	}

	public Integer getTopusercount() {
		return topusercount;
	}

	public void setTopusercount(Integer topusercount) {
		this.topusercount = topusercount;
	}

	public Integer getHotdoccount() {
		return hotdoccount;
	}

	public void setHotdoccount(Integer hotdoccount) {
		this.hotdoccount = hotdoccount;
	}

	public Integer getRecentdoccount() {
		return recentdoccount;
	}

	public void setRecentdoccount(Integer recentdoccount) {
		this.recentdoccount = recentdoccount;
	}

	public Integer getActiveusercount() {
		return activeusercount;
	}

	public void setActiveusercount(Integer activeusercount) {
		this.activeusercount = activeusercount;
	}

	public Integer getOnline() {
		return online;
	}

	public void setOnline(Integer online) {
		this.online = online;
	}

}
