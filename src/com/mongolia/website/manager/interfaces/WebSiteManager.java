package com.mongolia.website.manager.interfaces;

import java.util.List;
import java.util.Map;

import com.mongolia.website.controller.freemarker.CustomFreeMarkerConfigurer;
import com.mongolia.website.model.Channel;
import com.mongolia.website.model.MenuValue;
import com.mongolia.website.model.OpinionValue;
import com.mongolia.website.model.PaingModel;
import com.mongolia.website.model.ProgramItem;
import com.mongolia.website.model.ProgramValue;
import com.mongolia.website.model.QueryOpinionFrom;
import com.mongolia.website.model.QueryUserForm;
import com.mongolia.website.model.TopDocumentValue;
import com.mongolia.website.model.UserValue;

public interface WebSiteManager {
	/**
	 * 网站后台管理功能
	 * 
	 * @param params
	 * @return
	 */
	public List<ProgramValue> getProgramList(Map<String, Object> params)
			throws Exception;

	/**
	 * 获取栏目明细列表
	 * 
	 * @param params
	 * @return
	 */
	public List<ProgramItem> getProgramItemList(Map<String, Object> params)
			throws Exception;

	/**
	 * 添加新闻
	 * 
	 * @param item
	 * @throws Exception
	 */
	public void addNews(ProgramItem item) throws Exception;

	/**
	 * 添加栏目
	 * 
	 * @param programValue
	 * @throws Exception
	 */
	public void addProgram(ProgramValue programValue) throws Exception;

	/**
	 * 删除栏目明细
	 * 
	 * @param params
	 * @throws Exception
	 */
	public void deleteProgramItem(Map<String, String> params) throws Exception;

	/**
	 * 获取菜单列表
	 * 
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public List<MenuValue> getMenus(Map<String, Object> params)
			throws Exception;

	/**
	 * 获取所有文章列表
	 * 
	 * @param params
	 * @return
	 * @throws Exception
	 */
	Map<String, Object> getDocuments(Map<String, Object> params)
			throws Exception;

	/**
	 * 获取文章列表
	 * 
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public List<Channel> getDistinctDocChannels(Map<String, Object> params)
			throws Exception;

	/**
	 * 对文章进行分组
	 * 
	 * @param channelid
	 * @param docids
	 * @throws Exception
	 */
	public void doGroupingDoc(String channelid, String docids[])
			throws Exception;

	/**
	 * 获取
	 * 
	 * @param channelid
	 * @return
	 * @throws Exception
	 */
	public void docreateSitePage(String htmlpath,
			CustomFreeMarkerConfigurer customFreeMarkerConfigurer,
			String contentpath) throws Exception;

	/**
	 * 审核文档
	 * 
	 * @param docids
	 * @throws Exception
	 */
	public void doCheckDocument(String docids[], Integer status)
			throws Exception;

	/**
	 * 选取优秀作品
	 * 
	 * @param groupid
	 * @param userid
	 * @param docid
	 * @throws Exception
	 */
	public void doCreateTopDocument(TopDocumentValue topDocumentValue)
			throws Exception;

	/**
	 * 根据一定条件博客用户
	 * 
	 * @param queryUserForm
	 * @throws Exception
	 */
	public PaingModel<UserValue> getUsers(QueryUserForm queryUserForm)
			throws Exception;

	/**
	 * 查询意见建议
	 * 
	 * @param queryOpinionFrom
	 * @return
	 * @throws Exception
	 */
	public List<OpinionValue> getopinions(QueryOpinionFrom queryOpinionFrom)
			throws Exception;

	/**
	 * 保存意见建议
	 * 
	 * @param opinionValue
	 * @throws Exception
	 */
	public void doAaddopinions(OpinionValue opinionValue) throws Exception;

	/**
	 * 
	 * @param ids
	 * @throws Exception
	 */
	public void addSelectedDocs(String ids[],String type) throws Exception;

	/**
	 * 从selected 文章中删除
	 * 
	 * @param ids
	 * @throws Exception
	 */
	public void deleteTopDocument(String ids[]) throws Exception;

}
