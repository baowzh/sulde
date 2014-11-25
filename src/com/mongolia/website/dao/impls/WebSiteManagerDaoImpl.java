package com.mongolia.website.dao.impls;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.mongolia.website.dao.interfaces.WebSiteManagerDao;
import com.mongolia.website.model.Channel;
import com.mongolia.website.model.ChannelDocValue;
import com.mongolia.website.model.DocumentValue;
import com.mongolia.website.model.MenuValue;
import com.mongolia.website.model.OpinionValue;
import com.mongolia.website.model.ProgramItem;
import com.mongolia.website.model.ProgramValue;
import com.mongolia.website.model.QueryOpinionFrom;
import com.mongolia.website.model.TopDocumentValue;

@Repository("webSiteManagerDao")
public class WebSiteManagerDaoImpl extends BaseDaoiBatis implements
		WebSiteManagerDao {

	@Override
	public List<ProgramItem> getProgramItemList(Map<String, Object> params)
			throws Exception {
		// TODO Auto-generated method stub
		return this.getSqlMapClientTemplate().queryForList(
				"getProgramItemsValue", params);
	}

	@Override
	public List<ProgramValue> getProgramList(Map<String, Object> params)
			throws Exception {
		// TODO Auto-generated method stub
		return this.getSqlMapClientTemplate().queryForList(
				"getProgramValuesValue", params);
	}

	@Override
	public void addNews(ProgramItem item) throws Exception {
		// TODO Auto-generated method stub
		this.getSqlMapClientTemplate().insert("addnews", item);
	}

	@Override
	public void addProgram(ProgramValue programValue) throws Exception {
		// TODO Auto-generated method stub
		this.getSqlMapClientTemplate().insert("programValue", programValue);
	}

	@Override
	public void deleteProgramItem(Map<String, String> params) throws Exception {
		// TODO Auto-generated method stub
		this.getSqlMapClientTemplate().insert("deteletProgramItem", params);
	}

	@Override
	public List<MenuValue> getMenus(Map<String, Object> params)
			throws Exception {
		// TODO Auto-generated method stub
		return this.getSqlMapClientTemplate().queryForList("getmenus", params);
	}

	@Override
	public List<Channel> getDistinctDocChannels(Map<String, Object> params)
			throws Exception {
		// TODO Auto-generated method stub
		return this.getSqlMapClientTemplate().queryForList(
				"getDistinctDocChannels", params);
	}

	@Override
	public List<DocumentValue> getDocuments(Map<String, Object> params)
			throws Exception {
		// TODO Auto-generated method stub
		return this.getSqlMapClientTemplate().queryForList("paingUserDocList",
				params);
	}

	@Override
	public Integer getDocumentsCount(Map<String, Object> params)
			throws Exception {
		// TODO Auto-generated method stub
		return (Integer) this.getSqlMapClientTemplate().queryForObject(
				"getUserDocCount", params);
	}

	@Override
	public void groupingdoc(String channelid, String docid) throws Exception {
		// TODO Auto-generated method stub
		ChannelDocValue channelDocValue = new ChannelDocValue();
		channelDocValue.setChannelid(channelid);
		channelDocValue.setDocid(docid);
		this.getSqlMapClientTemplate().insert("addChannelDocValue",
				channelDocValue);
	}

	@Override
	public List<DocumentValue> getChannelDocs(String channelid)
			throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> queryparams = new HashMap<String, Object>();
		queryparams.put("channelid", channelid);
		return this.getSqlMapClientTemplate().queryForList("getChannelDoc",
				queryparams);
	}

	@Override
	public void checkDocument(String checkdocid, Integer status)
			throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> checkdocparams = new HashMap<String, Object>();
		checkdocparams.put("docid", checkdocid);
		checkdocparams.put("status", status);
		this.getSqlMapClientTemplate().update("checkdocument", checkdocparams);
	}

	@Override
	public void createTopDocument(TopDocumentValue topDocumentValue)
			throws Exception {
		// TODO Auto-generated method stub
		this.getSqlMapClientTemplate().insert("createtopdocument",
				topDocumentValue);
	}

	@Override
	public void addopinions(OpinionValue opinionValue) throws Exception {
		// TODO Auto-generated method stub
		this.getSqlMapClientTemplate().insert("addOpinion", opinionValue);
	}

	@Override
	public List<OpinionValue> getopinions(QueryOpinionFrom queryOpinionFrom)
			throws Exception {
		// TODO Auto-generated method stub
		return this.getSqlMapClientTemplate().queryForList("getOpinions",
				queryOpinionFrom);
	}

	@Override
	public void deleteTopDocument(String docids) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("docids", docids);
		this.getSqlMapClientTemplate().delete("deletetopDoc", params);

	}

}
