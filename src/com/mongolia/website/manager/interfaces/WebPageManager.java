package com.mongolia.website.manager.interfaces;

import java.util.List;
import java.util.Map;

import com.mongolia.website.model.PageChannelRelationValue;
import com.mongolia.website.model.WebPageValue;

public interface WebPageManager {
	/**
	 * 获取页面列表
	 * 
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public List<WebPageValue> getPages(Map<String, Object> params)
			throws Exception;

	/**
	 * 获取页面信息
	 * 
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public WebPageValue getpageInfo(Map<String, Object> params)
			throws Exception;

	/**
	 * 增加页面
	 * 
	 * @param webPageValue
	 * @throws Exception
	 */
	public void doAddWebPage(WebPageValue webPageValue) throws Exception;

	/**
	 * 
	 * @param pageid
	 * @throws Exception
	 */
	public void doDeletePage(String pageid) throws Exception;

	/**
	 * 
	 * @param webPageValue
	 * @throws Exception
	 */
	public void doModifyWebPage(WebPageValue webPageValue) throws Exception;

	/**
	 * 获取已关联的栏目列表
	 * 
	 * @param pageid
	 * @return
	 * @throws Exception
	 */
	public List<PageChannelRelationValue> getRelatedChannes(String pageid) throws Exception;

	/**
	 * 添加栏目到页面
	 * 
	 * @param pageid
	 * @param channel
	 * @throws Exception
	 */
	public void doAddChannelToPage(
			PageChannelRelationValue pageChannelRelationValue) throws Exception;
    /**
     * 
     * @param pageId
     * @param channelId
     * @param displayType
     * @throws Exception
     */
	public void doDeleteChannelFromPage(String pageId, String channelId,
			String displayType) throws Exception;

}
