package com.mongolia.website.manager.interfaces;

import java.util.List;

import com.mongolia.website.model.WechatAccountEntity;

public interface WechatManager {
	/*
	 * 获取公共账号信息
	 */
	public WechatAccountEntity getWechatAccountEntity(String appid)
			throws Exception;

	/**
	 * 添加图文消息
	 * 
	 * @param mediaid
	 * @param author
	 * @param title
	 * @param contentsourceurl
	 * @param content
	 * @param digest
	 * @param showcoverpic
	 * @throws Exception
	 */
	public void addnewitem(String mediaid, String author, String title,
			String contentsourceurl, String content, String digest,
			Integer showcoverpic) throws Exception;

	/**
	 * 
	 * @param newsitemids
	 * @throws Exception
	 */
	public void uploadMapnews(List<String> newsitemids) throws Exception;
}
