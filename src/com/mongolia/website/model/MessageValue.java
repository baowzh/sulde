package com.mongolia.website.model;

import java.util.Date;

public class MessageValue {
	private String messageid;
	private String messagesenderid;
	private String messagesendername;
	private byte[] messagecont;
	private Date sendtime;
	private String sendtimestr;
	private String userid;
	private Integer resourcekind;
	private String resourceid;
	private Date messtime;
	private String contenthtml;
	private String artname;
	private Integer received;
	private Integer messtype;
	private Integer hidden;

	public String getMessageid() {
		return messageid;
	}

	public void setMessageid(String messageid) {
		this.messageid = messageid;
	}

	public String getMessagesenderid() {
		return messagesenderid;
	}

	public void setMessagesenderid(String messagesenderid) {
		this.messagesenderid = messagesenderid;
	}

	public String getMessagesendername() {
		return messagesendername;
	}

	public void setMessagesendername(String messagesendername) {
		this.messagesendername = messagesendername;
	}

	public byte[] getMessagecont() {
		return messagecont;
	}

	public void setMessagecont(byte[] messagecont) {
		this.messagecont = messagecont;
	}

	public Date getSendtime() {
		return sendtime;
	}

	public void setSendtime(Date sendtime) {
		this.sendtime = sendtime;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public Integer getResourcekind() {
		return resourcekind;
	}

	public void setResourcekind(Integer resourcekind) {
		this.resourcekind = resourcekind;
	}

	public String getResourceid() {
		return resourceid;
	}

	public void setResourceid(String resourceid) {
		this.resourceid = resourceid;
	}

	public Date getMesstime() {
		return messtime;
	}

	public void setMesstime(Date messtime) {
		this.messtime = messtime;
	}

	public String getContenthtml() {
		return contenthtml;
	}

	public void setContenthtml(String contenthtml) {
		this.contenthtml = contenthtml;
	}

	public String getArtname() {
		return artname;
	}

	public void setArtname(String artname) {
		this.artname = artname;
	}

	public Integer getReceived() {
		return received;
	}

	public void setReceived(Integer received) {
		this.received = received;
	}

	public Integer getMesstype() {
		return messtype;
	}

	public void setMesstype(Integer messtype) {
		this.messtype = messtype;
	}

	public String getSendtimestr() {
		return sendtimestr;
	}

	public void setSendtimestr(String sendtimestr) {
		this.sendtimestr = sendtimestr;
	}

	public Integer getHidden() {
		return hidden;
	}

	public void setHidden(Integer hidden) {
		this.hidden = hidden;
	}

}
