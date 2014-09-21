package com.mongolia.website.model;

public class FriendValue {
	private String friendid;
	private String friendname;
	private byte[] friendimg;
	private String friendimgurl;
	private String userid;
	private String headimgsm;

	public String getFriendid() {
		return friendid;
	}

	public void setFriendid(String friendid) {
		this.friendid = friendid;
	}

	public String getFriendname() {
		return friendname;
	}

	public void setFriendname(String friendname) {
		this.friendname = friendname;
	}

	public byte[] getFriendimg() {
		return friendimg;
	}

	public void setFriendimg(byte[] friendimg) {
		this.friendimg = friendimg;
	}

	public String getFriendimgurl() {
		return friendimgurl;
	}

	public void setFriendimgurl(String friendimgurl) {
		this.friendimgurl = friendimgurl;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getHeadimgsm() {
		return headimgsm;
	}

	public void setHeadimgsm(String headimgsm) {
		this.headimgsm = headimgsm;
	}
	

}
