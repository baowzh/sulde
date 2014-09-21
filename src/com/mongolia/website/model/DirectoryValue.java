package com.mongolia.website.model;

class DirectoryValue extends BaseBean {
	/**
	 * 目录url
	 */
	private String directoryURL;
	/**
	 * 目录类型
	 */
	private String directoryKind;

	public String getDirectoryURL() {
		return directoryURL;
	}

	public void setDirectoryURL(String directoryURL) {
		this.directoryURL = directoryURL;
	}

	public String getDirectoryKind() {
		return directoryKind;
	}

	public void setDirectoryKind(String directoryKind) {
		this.directoryKind = directoryKind;
	}

}
