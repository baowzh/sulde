package com.mongolia.website.model;

public class Webtemplate {
	private String templatename;
	private byte[] templatefile;

	public String getTemplatename() {
		return templatename;
	}

	public void setTemplatename(String templatename) {
		this.templatename = templatename;
	}

	public byte[] getTemplatefile() {
		return templatefile;
	}

	public void setTemplatefile(byte[] templatefile) {
		this.templatefile = templatefile;
	}

}
