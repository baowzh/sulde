package com.mongolia.website.dao.interfaces;

import java.util.List;

import com.mongolia.website.model.WechatDocValue;
import com.mongolia.website.model.WechatReceiveMessValue;

public interface WechatDocDao {
	/**
	 * 根据自动回复设置查询对应的所有文章
	 * 
	 * @param resId
	 * @return
	 * @throws Exception
	 */
	public List<WechatDocValue> getWechatDocWithAutoResId(String resId)
			throws Exception;

	/**
	 * 新增自动回复关联文章
	 * 
	 * @param wechatDocValue
	 * @throws Exception
	 */
	public void addWechatDoc(WechatDocValue wechatDocValue) throws Exception;

	/**
	 * 删除自动回复关联文章
	 * 
	 * @param wechatDocValue
	 * @throws Exception
	 */
	public void delWechatDoc(WechatDocValue wechatDocValue) throws Exception;

	/**
	 * 删除所有跟自动回复相关的文章
	 * 
	 * @param resId
	 * @throws Exception
	 */
	public void delAllAutoResDoc(String resId) throws Exception;
	/**
	 * 
	 * @param receiveMessValue
	 * @throws Exception
	 */
	public void addWechatReceiveMessValue(WechatReceiveMessValue receiveMessValue)throws Exception;
	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<String> getRecentReqUserId()throws Exception;
}
