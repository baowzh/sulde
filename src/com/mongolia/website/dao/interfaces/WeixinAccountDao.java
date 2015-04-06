package com.mongolia.website.dao.interfaces;

import java.util.List;
import java.util.Map;

import com.mongolia.website.model.AccessTokenYw;
import com.mongolia.website.model.WechatAccountEntity;

/**
 * 微信账户dao
 * 
 * @author dell
 * 
 */
public interface WeixinAccountDao 
		 {
	public List<WechatAccountEntity> getAccountByUserName(String username);

	public WechatAccountEntity getAccountById(String id);

	public void saveOrUpdate(WechatAccountEntity weixinAccountEntity);

	public void deleteAccount(WechatAccountEntity weixinAccountEntity);

	public List<WechatAccountEntity> getWechatAccounts(
			Map<String, Object> params);

	public AccessTokenYw getAccessToken(String appid) throws Exception;

	public void saveAccessToken(AccessTokenYw accessTokenYw) throws Exception;

}
