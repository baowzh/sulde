package com.mongolia.website.dao.interfaces;

import java.util.List;
import java.util.Map;

import com.mongolia.website.model.PageChannelRelationValue;
import com.mongolia.website.model.WebPageValue;

public interface WebPageManagerDao extends IBatisBaseDao {
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
	 */
	public WebPageValue getpageInfo(Map<String, Object> params)
			throws Exception;

	/**
	 * 
	 * @param webPageValue
	 * @throws Exception
	 */
	public void addPage(WebPageValue webPageValue) throws Exception;

	/**
	 * 
	 * @param params
	 * @throws Exception
	 */
	public void deletePage(Map<String, Object> params) throws Exception;

	/**
	 * 
	 * @param webPageValue
	 * @throws Exception
	 */
	public void modifyPage(WebPageValue webPageValue) throws Exception;

	/**
	 * 获取跟某个页面关联的栏目列表
	 * 
	 * @param pageid
	 * @return
	 * @throws Exception
	 */
	public List<PageChannelRelationValue> getRelatedChannes(String pageid) throws Exception;

	/**
	 * 判断是否用同样方式关联过了？
	 * 
	 * @return
	 * @throws Exception
	 */
	public Integer checkIsRelated(
			PageChannelRelationValue pageChannelRelationValue) throws Exception;

	/**
	 * 添加栏目到页面
	 * 
	 * @param pageid
	 * @param channel
	 * @throws Exception
	 */
	public void addChannelToPage(
			PageChannelRelationValue pageChannelRelationValue) throws Exception;

	/**
	 * 校验是否父级页面
	 * 
	 * @param pageid
	 * @return
	 * @throws Exception
	 */
	public Integer isParentPage(String pageid) throws Exception;
    /**
     * 从页面删除栏目定义
     * @param pageid
     * @param channelid
     * @param displayType
     * @throws Exception
     */
	public void deleteChannelFromPage(String pageid, String channelid,
			String displayType) throws Exception;
}
