package com.mongolia.website.model;

/**
 * @Title: Entity
 * @Description: 微信公众帐号信息
 * @author onlineGenerator
 * @date 2014-05-21 00:53:47
 * @version V1.0
 * 
 */

public class WeixinAccountEntity implements java.io.Serializable {
	/** 主键 */
	private java.lang.String id;
	/** 公众帐号名称 */
	private java.lang.String accountname;
	/** 公众帐号TOKEN */
	private java.lang.String accounttoken;

	private java.lang.String jsaccounttoken;
	/** 公众微信号 */
	private java.lang.String accountnumber;
	/** 公众原始ID */
	private java.lang.String accountid;
	/** 公众号类型 */
	private java.lang.String accounttype;
	/** 电子邮箱 */
	private java.lang.String accountemail;
	/** 公众帐号描述 */
	private java.lang.String accountdesc;
	/** 公众帐号APPID */
	private java.lang.String accountappid;
	/** 公众帐号APPSECRET */
	private java.lang.String accountappsecret;
	/** ACCESS_TOKEN */
	private java.lang.String accountaccesstoken;
	/** TOKEN获取时间 */
	private java.util.Date addtoekntime;
	/** 所属系统用户 **/
	private java.lang.String username;

	/**
	 * 方法: 取得java.lang.String
	 * 
	 * @return: java.lang.String 主键
	 */

	public java.lang.String getId() {
		return this.id;
	}

	/**
	 * 方法: 设置java.lang.String
	 * 
	 * @param: java.lang.String 主键
	 */
	public void setId(java.lang.String id) {
		this.id = id;
	}

	/**
	 * 方法: 取得java.lang.String
	 * 
	 * @return: java.lang.String 公众帐号名称
	 */

	public java.lang.String getAccountname() {
		return this.accountname;
	}

	/**
	 * 方法: 设置java.lang.String
	 * 
	 * @param: java.lang.String 公众帐号名称
	 */
	public void setAccountname(java.lang.String accountname) {
		this.accountname = accountname;
	}

	/**
	 * 方法: 取得java.lang.String
	 * 
	 * @return: java.lang.String 公众帐号TOKEN
	 */

	public java.lang.String getAccounttoken() {
		return this.accounttoken;
	}

	/**
	 * 方法: 设置java.lang.String
	 * 
	 * @param: java.lang.String 公众帐号TOKEN
	 */
	public void setAccounttoken(java.lang.String accounttoken) {
		this.accounttoken = accounttoken;
	}

	/**
	 * 方法: 取得java.lang.String
	 * 
	 * @return: java.lang.String 公众微信号
	 */

	public java.lang.String getAccountnumber() {
		return this.accountnumber;
	}

	/**
	 * 方法: 设置java.lang.String
	 * 
	 * @param: java.lang.String 公众微信号
	 */
	public void setAccountnumber(java.lang.String accountnumber) {
		this.accountnumber = accountnumber;
	}

	/**
	 * 方法: 取得java.lang.String
	 * 
	 * @return: java.lang.String 公众原始ID
	 */

	public java.lang.String getAccountid() {
		return accountid;
	}

	/**
	 * 方法: 设置java.lang.String
	 * 
	 * @param: java.lang.String 公众原始ID
	 */
	public void setAccountid(java.lang.String accountid) {
		this.accountid = accountid;
	}

	/**
	 * 方法: 取得java.lang.String
	 * 
	 * @return: java.lang.String 公众号类型
	 */

	public java.lang.String getAccounttype() {
		return this.accounttype;
	}

	/**
	 * 方法: 设置java.lang.String
	 * 
	 * @param: java.lang.String 公众号类型
	 */
	public void setAccounttype(java.lang.String accounttype) {
		this.accounttype = accounttype;
	}

	/**
	 * 方法: 取得java.lang.String
	 * 
	 * @return: java.lang.String 电子邮箱
	 */

	public java.lang.String getAccountemail() {
		return this.accountemail;
	}

	/**
	 * 方法: 设置java.lang.String
	 * 
	 * @param: java.lang.String 电子邮箱
	 */
	public void setAccountemail(java.lang.String accountemail) {
		this.accountemail = accountemail;
	}

	/**
	 * 方法: 取得java.lang.String
	 * 
	 * @return: java.lang.String 公众帐号描述
	 */

	public java.lang.String getAccountdesc() {
		return this.accountdesc;
	}

	/**
	 * 方法: 设置java.lang.String
	 * 
	 * @param: java.lang.String 公众帐号描述
	 */
	public void setAccountdesc(java.lang.String accountdesc) {
		this.accountdesc = accountdesc;
	}

	/**
	 * 方法: 取得java.lang.String
	 * 
	 * @return: java.lang.String 公众帐号APPID
	 */

	public java.lang.String getAccountappid() {
		return this.accountappid;
	}

	/**
	 * 方法: 设置java.lang.String
	 * 
	 * @param: java.lang.String 公众帐号APPID
	 */
	public void setAccountappid(java.lang.String accountappid) {
		this.accountappid = accountappid;
	}

	/**
	 * 方法: 取得java.lang.String
	 * 
	 * @return: java.lang.String 公众帐号APPSECRET
	 */

	public java.lang.String getAccountappsecret() {
		return this.accountappsecret;
	}

	/**
	 * 方法: 设置java.lang.String
	 * 
	 * @param: java.lang.String 公众帐号APPSECRET
	 */
	public void setAccountappsecret(java.lang.String accountappsecret) {
		this.accountappsecret = accountappsecret;
	}

	/**
	 * 方法: 取得java.lang.String
	 * 
	 * @return: java.lang.String ACCESS_TOKEN
	 */

	public java.lang.String getAccountaccesstoken() {
		return this.accountaccesstoken;
	}

	/**
	 * 方法: 设置java.lang.String
	 * 
	 * @param: java.lang.String ACCESS_TOKEN
	 */
	public void setAccountaccesstoken(java.lang.String accountaccesstoken) {
		this.accountaccesstoken = accountaccesstoken;
	}

	/**
	 * 方法: 取得java.util.Date
	 * 
	 * @return: java.util.Date TOKEN获取时间
	 */

	public java.util.Date getAddtoekntime() {
		return this.addtoekntime;
	}

	/**
	 * 方法: 设置java.util.Date
	 * 
	 * @param: java.util.Date TOKEN获取时间
	 */
	public void setAddtoekntime(java.util.Date addtoekntime) {
		this.addtoekntime = addtoekntime;
	}

	public java.lang.String getUsername() {
		return username;
	}

	public void setUsername(java.lang.String username) {
		this.username = username;
	}

	public java.lang.String getJsaccounttoken() {
		return jsaccounttoken;
	}

	public void setJsaccounttoken(java.lang.String jsaccounttoken) {
		this.jsaccounttoken = jsaccounttoken;
	}

}
