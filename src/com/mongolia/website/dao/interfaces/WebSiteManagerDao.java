package com.mongolia.website.dao.interfaces;

import java.util.List;
import java.util.Map;

import com.mongolia.website.model.Channel;
import com.mongolia.website.model.DocumentValue;
import com.mongolia.website.model.MenuValue;
import com.mongolia.website.model.OpinionValue;
import com.mongolia.website.model.ProgramItem;
import com.mongolia.website.model.ProgramValue;
import com.mongolia.website.model.QueryOpinionFrom;
import com.mongolia.website.model.TopDocumentValue;

public interface WebSiteManagerDao extends BaseDao {
	/**
	 * 网站栏目列表
	 * 
	 * @param params
	 * @return
	 */
	public List<ProgramValue> getProgramList(Map<String, Object> params)
			throws Exception;

	/**
	 * 获取栏目明细
	 * 
	 * @param params
	 * @return
	 */
	public List<ProgramItem> getProgramItemList(Map<String, Object> params)
			throws Exception;

	/**
	 * 增加新闻
	 * 
	 * @param item
	 * @throws Exception
	 */
	public void addNews(ProgramItem item) throws Exception;

	/**
	 * 增加栏目
	 * 
	 * @param programValue
	 * @throws Exception
	 */

	public void addProgram(ProgramValue programValue) throws Exception;

	/**
	 * 删除栏目
	 * 
	 * @param params
	 * @throws Exception
	 */

	public void deleteProgramItem(Map<String, String> params) throws Exception;

	/**
	 * 获取菜单
	 * 
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public List<MenuValue> getMenus(Map<String, Object> params)
			throws Exception;

	/**
	 * 获取文章列表
	 * 
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public List<DocumentValue> getDocuments(Map<String, Object> params)
			throws Exception;

	/**
	 * 
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public Integer getDocumentsCount(Map<String, Object> params)
			throws Exception;

	/**
	 * 获取文章所述栏目列表
	 * 
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public List<Channel> getDistinctDocChannels(Map<String, Object> params)
			throws Exception;

	/**
	 * 获取内容列表
	 * 
	 * @param channelid
	 * @param docid
	 * @throws Exception
	 */
	public void groupingdoc(String channelid, String docid) throws Exception;

	/**
	 * 获取栏目对应的内容
	 * 
	 * @param channelid
	 * @return
	 * @throws Exception
	 */
	public List<DocumentValue> getChannelDocs(String channelid)
			throws Exception;

	/**
	 * 
	 * @param checkdocid
	 * @throws Exception
	 */
	public void checkDocument(String checkdocid, Integer status)
			throws Exception;

	/**
	 * @param topDocumentValue
	 * @throws Exception
	 */
	public void createTopDocument(TopDocumentValue topDocumentValue)
			throws Exception;

	/**
	 * 保存意见建议
	 * 
	 * @param opinionValue
	 * @throws Exception
	 */
	public void addopinions(OpinionValue opinionValue) throws Exception;

	/**
	 * 获取意见建议
	 * 
	 * @param queryOpinionFrom
	 * @return
	 * @throws Exception
	 */
	public List<OpinionValue> getopinions(QueryOpinionFrom queryOpinionFrom)
			throws Exception;

	/**
	 * 
	 * @param docids
	 * @throws Exception
	 */
	public void deleteTopDocument(String docids) throws Exception;

}
