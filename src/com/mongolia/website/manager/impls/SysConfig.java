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

}
