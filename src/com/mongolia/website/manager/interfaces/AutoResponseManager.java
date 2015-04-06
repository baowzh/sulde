package com.mongolia.website.manager.interfaces;

import java.util.List;
import java.util.Map;

import com.mongolia.website.model.AutoResponse;
import com.mongolia.website.model.PagingAutoResModel;

public interface AutoResponseManager {
	/**
	 * 删除自动回复
	 * 
	 * @param entity
	 * @throws Exception
	 */
	public void delete(AutoResponse entity) throws Exception;

	/**
	 * 获取自动回复配置
	 * 
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public List<AutoResponse> getAutoResponses(Map<String, Object> params)
			throws Exception;

	/**
	 * 修改或者增加自动回复
	 * 
	 * @param entity
	 * @throws Exception
	 */
	public void saveOrUpdate(AutoResponse entity) throws Exception;

	/**
	 * 分页查询自动回复设置
	 * 
	 * @param paingModel
	 * @return
	 * @throws Exception
	 */
	public PagingAutoResModel pagingquerydoc(PagingAutoResModel paingModel)
			throws Exception;

	/**
	 * 添加自动回复文档
	 * 
	 * @param docid
	 * @param autoresid
	 * @throws Exception
	 */
	public void addWechatDoc(String docid, String autoresid) throws Exception;

	/**
	 * 删除自动回复文档
	 * 
	 * @param docid
	 * @param autoresid
	 * @throws Exception
	 */
	public void delWechatDoc(String docid, String autoresid) throws Exception;
}
