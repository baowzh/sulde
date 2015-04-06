package com.mongolia.website.dao.interfaces;

import java.util.List;

import com.mongolia.website.model.AttentionValue;
import com.mongolia.website.model.NewsInfo;
import com.mongolia.website.model.Wechatmsg;
import com.mongolia.website.model.Wechatmsgconfig;

public interface WechatDao {
	/*
	 * 保存微信信息
	 * 
	 * @param wechatmsg
	 * 
	 * @return
	 * 
	 * @throws Exception
	 */
	public void saveWechatMsg(Wechatmsg wechatmsg) throws Exception;

	/**
	 * 获取微信消息配置
	 * 
	 * @param recMsg
	 * @param recMsgType
	 * @param uid
	 * @param platform
	 * @return
	 */
	public Wechatmsgconfig queryWechatMsgConfig(String recMsg,
			String recMsgType, String uid, String platform);

	/**
	 * 获取新闻配置
	 * 
	 * @param groupid
	 * @return
	 */
	public List<NewsInfo> queryMsgConfigNews(String groupid);

	/**
	 * 保存关注用户信息
	 * 
	 * @param attentionValue
	 */
	public void addAttentionUserInfo(AttentionValue attentionValue)
			throws Exception;

	/**
	 * 
	 * @param oepnid
	 * @throws Exception
	 */
	public void deleteAttentionUserInfo(String oepnid) throws Exception;

	/**
	 * 获取关注用信息
	 * 
	 * @param openid
	 * @return
	 * @throws Exception
	 */
	public AttentionValue getAttentionUserInfo(String openid);
}
