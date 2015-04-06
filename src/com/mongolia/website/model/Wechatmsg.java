package com.mongolia.website.model;

import java.sql.Timestamp;


/**
 * Wechatmsg entity. @author MyEclipse Persistence Tools
 */
public class Wechatmsg implements java.io.Serializable {

	// Fields

	private Integer id;
	private String platform;
	private String uid;
	private String msgflag;
	private String tousername;
	private String fromusername;
	private String receivemsgtype;
	private String sendmsgtype;
	private String receivemsg;
	private String sendmsg;
	private Timestamp receivetime;
	private Timestamp sendtime;
	private Timestamp createtime;
	private String status;
	private String msgid;

	// Constructors

	/** default constructor */
	public Wechatmsg() {
	}

	/** minimal constructor */
	public Wechatmsg(String platform, String uid, String msgflag,
			String tousername, String fromusername, Timestamp createtime,
			String status) {
		this.platform = platform;
		this.uid = uid;
		this.msgflag = msgflag;
		this.tousername = tousername;
		this.fromusername = fromusername;
		this.createtime = createtime;
		this.status = status;
	}

	/** full constructor */
	public Wechatmsg(String platform, String uid, String msgflag,
			String tousername, String fromusername, String receivemsgtype,
			String sendmsgtype, String receivemsg, String sendmsg,
			Timestamp receivetime, Timestamp sendtime, Timestamp createtime,
			String status, String msgid) {
		this.platform = platform;
		this.uid = uid;
		this.msgflag = msgflag;
		this.tousername = tousername;
		this.fromusername = fromusername;
		this.receivemsgtype = receivemsgtype;
		this.sendmsgtype = sendmsgtype;
		this.receivemsg = receivemsg;
		this.sendmsg = sendmsg;
		this.receivetime = receivetime;
		this.sendtime = sendtime;
		this.createtime = createtime;
		this.status = status;
		this.msgid = msgid;
	}

	
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	public String getPlatform() {
		return this.platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}


	public String getUid() {
		return this.uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	
	public String getMsgflag() {
		return this.msgflag;
	}

	public void setMsgflag(String msgflag) {
		this.msgflag = msgflag;
	}

	
	public String getTousername() {
		return this.tousername;
	}

	public void setTousername(String tousername) {
		this.tousername = tousername;
	}

	
	public String getFromusername() {
		return this.fromusername;
	}

	public void setFromusername(String fromusername) {
		this.fromusername = fromusername;
	}

	
	public String getReceivemsgtype() {
		return this.receivemsgtype;
	}

	public void setReceivemsgtype(String receivemsgtype) {
		this.receivemsgtype = receivemsgtype;
	}

	
	public String getSendmsgtype() {
		return this.sendmsgtype;
	}

	public void setSendmsgtype(String sendmsgtype) {
		this.sendmsgtype = sendmsgtype;
	}

	
	public String getReceivemsg() {
		return this.receivemsg;
	}

	public void setReceivemsg(String receivemsg) {
		this.receivemsg = receivemsg;
	}

	
	public String getSendmsg() {
		return this.sendmsg;
	}

	public void setSendmsg(String sendmsg) {
		this.sendmsg = sendmsg;
	}

	
	public Timestamp getReceivetime() {
		return this.receivetime;
	}

	public void setReceivetime(Timestamp receivetime) {
		this.receivetime = receivetime;
	}

	
	public Timestamp getSendtime() {
		return this.sendtime;
	}

	public void setSendtime(Timestamp sendtime) {
		this.sendtime = sendtime;
	}


	public Timestamp getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}

	
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	
	public String getMsgid() {
		return this.msgid;
	}

	public void setMsgid(String msgid) {
		this.msgid = msgid;
	}

}