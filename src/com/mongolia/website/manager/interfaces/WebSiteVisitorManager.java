package com.mongolia.website.manager.interfaces;

import java.util.List;
import java.util.Map;

import com.mongolia.website.model.DocumentValue;
import com.mongolia.website.model.ImgGrpupValue;
import com.mongolia.website.model.PaingModel;
import com.mongolia.website.model.ProgramItem;
import com.mongolia.website.model.ProgramValue;
import com.mongolia.website.model.TopDocumentValue;

/**
 * 内容浏览
 * 
 * @author Administrator
 *
 */
public interface WebSiteVisitorManager extends BaseManager {

	public List<ProgramValue> getProgramList() throws Exception;

	public List<ProgramItem> getProgramItemList(Map<String, Object> params)
			throws Exception;

	public ProgramItem getItemContent(Map<String, Object> params)
			throws Exception;

	/**
	 * 分页显示文档列表
	 * 
	 * @param paingModel
	 * @return
	 * @throws Exception
	 */
	public PaingModel<DocumentValue> pagingquerydoc(
			PaingModel<DocumentValue> paingModel) throws Exception;

	/**
	 * index
	 * 
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> getIndexContent(String htmlpath)
			throws Exception;

	/**
	 * 获取topdocument
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<TopDocumentValue> getTopDocuments(Integer type, String docid,
			Integer limit) throws Exception;

	/**
	 * 
	 * @param count
	 * @return
	 * @throws Exception
	 */
	public List<DocumentValue> getRecentDocs(Integer count) throws Exception;
	/**
	 * 
	 * @param paingModel
	 * @return
	 * @throws Exception
	 */
	public PaingModel<ImgGrpupValue> pagingqueryAlbum(
			PaingModel<ImgGrpupValue> paingModel) throws Exception;


}
