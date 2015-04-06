package com.mongolia.website.model;

import java.sql.Timestamp;
/**
 * 
 * @author baowzh
 *
 */
public class Wechatmsgconfig implements java.io.Serializable {

	// Fields

	private Integer id;
	private String platform;
	private String uid;
	private String receivemsgtype;
	private String sendmsgtype;
	private String receivemsg;
	private String sendmsg;
	private Timestamp createtime;
	private String creator;
	private String status;
	private Timestamp effectivetime;
	private Timestamp failuretime;
	private String infochannel;

	// Constructors

	public String getInfochannel() {
		return infochannel;
	}

	public void setInfochannel(String infochannel) {
		this.infochannel = infochannel;
	}

	/** default constructor */
	public Wechatmsgconfig() {
	}

	/** minimal constructor */
	public Wechatmsgconfig(String platform, String uid, String receivemsgtype,
			String sendmsgtype, Timestamp createtime, String status ,String infochannel) {
		this.platform = platform;
		this.uid = uid;
		this.receivemsgtype = receivemsgtype;
		this.sendmsgtype = sendmsgtype;
		this.createtime = createtime;
		this.status = status;
		this.infochannel=infochannel;
	}

	/** full constructor */
	public Wechatmsgconfig(String platform, String uid, String receivemsgtype,
			String sendmsgtype, String receivemsg, String sendmsg,
			Timestamp createtime, String creator, String status,
			Timestamp effectivetime, Timestamp failuretime) {
		this.platform = platform;
		this.uid = uid;
		this.receivemsgtype = receivemsgtype;
		this.sendmsgtype = sendmsgtype;
		this.receivemsg = receivemsg;
		this.sendmsg = sendmsg;
		this.createtime = createtime;
		this.creator = creator;
		this.status = status;
		this.effectivetime = effectivetime;
		this.failuretime = failuretime;
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


	public Timestamp getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}

	public String getCreator() {
		return this.creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}


	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}


	public Timestamp getEffectivetime() {
		return this.effectivetime;
	}

	public void setEffectivetime(Timestamp effectivetime) {
		this.effectivetime = effectivetime;
	}

	public Timestamp getFailuretime() {
		return this.failuretime;
	}

	public void setFailuretime(Timestamp failuretime) {
		this.failuretime = failuretime;
	}

}