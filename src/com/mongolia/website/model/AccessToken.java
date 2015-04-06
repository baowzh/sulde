package com.mongolia.website.model;

/**
 * 微信通用接口凭证
 * 
 * @author 孙海峰
 * @date 2013-08-08
 */
public class AccessToken {
	// 获取到的凭证
	private String token;
	// 凭证有效时间，单位：秒
	private int expiresIn;
	private String jstoken;
	private int jsexpiresIn;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public int getExpiresIn() {
		return expiresIn;
	}

	public void setExpiresIn(int expiresIn) {
		this.expiresIn = expiresIn;
	}

	public String getJstoken() {
		return jstoken;
	}

	public void setJstoken(String jstoken) {
		this.jstoken = jstoken;
	}

	public int getJsexpiresIn() {
		return jsexpiresIn;
	}

	public void setJsexpiresIn(int jsexpiresIn) {
		this.jsexpiresIn = jsexpiresIn;
	}
	

}