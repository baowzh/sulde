package com.mongolia.website.model;

import java.util.Date;

public class RssItem {
	private String title;
	private String description;
	private String link;
	private String author;
	private Date datepublished;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Date getDatepublished() {
		return datepublished;
	}

	public void setDatepublished(Date datepublished) {
		this.datepublished = datepublished;
	}

}
