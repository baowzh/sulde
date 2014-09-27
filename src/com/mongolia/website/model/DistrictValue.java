package com.mongolia.website.model;

public class DistrictValue {
	/**
	 * 地区编号
	 */
	private String districtcode;
	/**
	 * 地区名称
	 */
	private String districtname;
	/**
	 * 上级地区名称
	 */
	private String parentcode;

	public String getDistrictcode() {
		return districtcode;
	}

	public void setDistrictcode(String districtcode) {
		this.districtcode = districtcode;
	}

	public String getDistrictname() {
		return districtname;
	}

	public void setDistrictname(String districtname) {
		this.districtname = districtname;
	}

	public String getParentcode() {
		return parentcode;
	}

	public void setParentcode(String parentcode) {
		this.parentcode = parentcode;
	}

}
