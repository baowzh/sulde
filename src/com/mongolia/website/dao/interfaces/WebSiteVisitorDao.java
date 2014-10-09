package com.mongolia.website.dao.interfaces;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.mongolia.website.model.DocumentValue;
import com.mongolia.website.model.PaingModel;
import com.mongolia.website.model.ProgramItem;
import com.mongolia.website.model.ProgramValue;
import com.mongolia.website.model.TopDocumentValue;
import com.mongolia.website.model.UserValue;

public interface WebSiteVisitorDao extends BaseDao {
	/**
	 * 获取栏目列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<ProgramValue> getProgramList() throws Exception;

	/**
	 * 获取栏目明细
	 * 
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public List<ProgramItem> getProgramItemList(Map<String, Object> params)
			throws Exception;

	/**
	 * 获取明细内容
	 * 
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public ProgramItem getItemContent(Map<String, Object> params)
			throws Exception;

	/**
	 * 分页查询系统中的文档列表
	 * 
	 * @param paingModel
	 * @return
	 * @throws Exception
	 */
	public List<DocumentValue> pagingquerydoc(PaingModel paingModel)
			throws Exception;

	/**
	 * 获取页数
	 * 
	 * @param paingModel
	 * @return
	 * @throws Exception
	 */
	public Integer getRowCount(PaingModel paingModel) throws Exception;

	/**
	 * 获取当月最前面的几个用户
	 * 
	 * @param month
	 * @return
	 * @throws Exception
	 */
	public List<UserValue> getTopUsers(Date startDate, Date endDate)
			throws Exception;

	/**
	 * 获取最近注册的用户列表
	 * 
	 * @param startDate
	 * @param endDate
	 * @return
	 * @throws Exception
	 */
	public List<UserValue> getRecentRegistUsers(Date startDate, Date endDate,
			int startindex, int fetchcount) throws Exception;

	/**
	 * 获取排前面的
	 * 
	 * @param fetchDate
	 * @param Type
	 * @return
	 * @throws Exception
	 */
	public List<TopDocumentValue> getTopDocuments(Date fetchDate, Integer Type,
			String docid, Integer limit) throws Exception;

	/**
	 * 获取最近登录的用户列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<UserValue> getRecentLoginUsers(Integer dispalycount)
			throws Exception;

}
