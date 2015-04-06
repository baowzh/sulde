package com.mongolia.website.model;

import java.sql.Timestamp;

public class AccessTokenYw {

	private String access_token;// 凭证
	private int expires_in;// 凭证有效时间
	private Timestamp addtime;// 添加时间
	private String appid;
	private String appsecret;
	private String id;
	private String jsaccess_token;// js凭证
	private int jsexpires_in;// js凭证有效时间

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String accessToken) {
		access_token = accessToken;
	}

	public int getExpires_in() {
		return expires_in;
	}

	public void setExpires_in(int expiresIn) {
		expires_in = expiresIn;
	}

	public Timestamp getAddtime() {
		return addtime;
	}

	public void setAddtime(Timestamp addtime) {
		this.addtime = addtime;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getAppsecret() {
		return appsecret;
	}

	public void setAppsecret(String appsecret) {
		this.appsecret = appsecret;
	}

	public String getJsaccess_token() {
		return jsaccess_token;
	}

	public void setJsaccess_token(String jsaccess_token) {
		this.jsaccess_token = jsaccess_token;
	}

	public int getJsexpires_in() {
		return jsexpires_in;
	}

	public void setJsexpires_in(int jsexpires_in) {
		this.jsexpires_in = jsexpires_in;
	}

}
