package com.mongolia.website.model;

public class WechatUserValue {
	/**
	 * 是否已关注
	 */
	private boolean subscribe;
	/**
	 * 用户openid
	 */
	private String openid;
	/**
	 * 用户你名
	 */
	private String nickname;
	/**
	 * 性别
	 */
	private Integer sex;
	/**
	 * 语言
	 */
	private String language;
	/**
	 * 所在城市
	 */
	private String city;
	/**
	 * 所在地区
	 */
	private String province;
	/**
	 * 国家
	 */
	private String country;
	/**
	 * 头像
	 */
	private String headimgurl;
	/**
	 * 描述
	 */
	private String remark;

	public boolean isSubscribe() {
		return subscribe;
	}

	public void setSubscribe(boolean subscribe) {
		this.subscribe = subscribe;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
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

}
