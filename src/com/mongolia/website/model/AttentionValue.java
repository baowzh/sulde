package com.mongolia.website.model;

import java.util.Date;

public class AttentionValue {
	private String accountid;
	private String openid;
	private Date attentiontime;
	private String nickname;
	private Integer sex;
	private String language;
	private String city;
	private String province;
	private String country;
	private String headimgurl;
	private String remark;
	private Integer status;
	private Date unattentiontime;

	public String getAccountid() {
		return accountid;
	}

	public void setAccountid(String accountid) {
		this.accountid = accountid;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public Date getAttentiontime() {
		return attentiontime;
	}

	public void setAttentiontime(Date attentiontime) {
		this.attentiontime = attentiontime;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getHeadimgurl() {
		return headimgurl;
	}

	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getUnattentiontime() {
		return unattentiontime;
	}

	public void setUnattentiontime(Date unattentiontime) {
		this.unattentiontime = unattentiontime;
	}

}
