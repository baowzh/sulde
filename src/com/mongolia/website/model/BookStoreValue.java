package com.mongolia.website.model;

import java.util.Date;

public class BookStoreValue {
	/**
	 * 书店id
	 */
	private String bookid;
	/**
	 * 书封面
	 */
	private String bookimg;
	/**
	 * 书名字
	 */
	private String bookname;
	/**
	 * 书价钱
	 */
	private Double bookprice;
	/**
	 * 书作者
	 */
	private String bookauthor;
	/**
	 * 书连接地址
	 */
	private String booklink;
	/**
	 * 入库时间
	 */
	private Date storedate;

	private byte bookimgcon[];
	private byte imgurl[];

	public String getBookid() {
		return bookid;
	}

	public void setBookid(String bookid) {
		this.bookid = bookid;
	}

	public String getBookimg() {
		return bookimg;
	}

	public void setBookimg(String bookimg) {
		this.bookimg = bookimg;
	}

	public String getBookname() {
		return bookname;
	}

	public void setBookname(String bookname) {
		this.bookname = bookname;
	}

	public Double getBookprice() {
		return bookprice;
	}

	public void setBookprice(Double bookprice) {
		this.bookprice = bookprice;
	}

	public String getBookauthor() {
		return bookauthor;
	}

	public void setBookauthor(String bookauthor) {
		this.bookauthor = bookauthor;
	}

	public String getBooklink() {
		return booklink;
	}

	public void setBooklink(String booklink) {
		this.booklink = booklink;
	}

	public Date getStoredate() {
		return storedate;
	}

	public void setStoredate(Date storedate) {
		this.storedate = storedate;
	}

	public byte[] getBookimgcon() {
		return bookimgcon;
	}

	public void setBookimgcon(byte[] bookimgcon) {
		this.bookimgcon = bookimgcon;
	}

	public byte[] getImgurl() {
		return imgurl;
	}

	public void setImgurl(byte[] imgurl) {
		this.imgurl = imgurl;
	}

}
