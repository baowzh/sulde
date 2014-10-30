package com.mongolia.website.dao.impls;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.mongolia.website.dao.interfaces.WebSiteVisitorDao;
import com.mongolia.website.model.DocumentValue;
import com.mongolia.website.model.PaingModel;
import com.mongolia.website.model.ProgramItem;
import com.mongolia.website.model.ProgramValue;
import com.mongolia.website.model.TopDocumentValue;
import com.mongolia.website.model.UserValue;

@Repository("webSiteVisitorDao")
public class WebSiteVisitorDaoImpl extends BaseDaoiBatis implements
		WebSiteVisitorDao {

	@Override
	public List<ProgramValue> getProgramList() throws Exception {
		// TODO Auto-generated method stub
		return this.getSqlMapClientTemplate().queryForList(
				"getProgramValuesValue", new HashMap());
	}

	public List<ProgramItem> getProgramItemList(Map<String, Object> params)
			throws Exception {
		return this.getSqlMapClientTemplate().queryForList(
				"getProgramItemsValue", params);
	}

	@Override
	public ProgramItem getItemContent(Map<String, Object> params)
			throws Exception {
		// TODO Auto-generated method stub
		return (ProgramItem) this.getSqlMapClientTemplate().queryForObject(
				"getProgramItemWithContent", params);
	}

	@Override
	public List<DocumentValue> pagingquerydoc(PaingModel paingModel)
			throws Exception {
		List<DocumentValue> docs = this.getSqlMapClientTemplate().queryForList(
				"pagingquerydoc", paingModel);
		DocumentValue docarray[] = new DocumentValue[docs.size()];
		docs.toArray(docarray);
		Arrays.sort(docarray, new Comparator<DocumentValue>() {

			@Override
			public int compare(DocumentValue o1, DocumentValue o2) {
				// TODO Auto-generated method stub
				return o2.getIreadcount() - o1.getIreadcount();
			}

		});

		return Arrays.asList(docarray);
		// return docs;
	}

	@Override
	public Integer getRowCount(PaingModel paingModel) throws Exception {
		// TODO Auto-generated method stub
		return (Integer) this.getSqlMapClientTemplate().queryForObject(
				"getRowCount", paingModel);
	}

	@Override
	public List<UserValue> getTopUsers(Date startDate, Date endDate,Integer fetchcount)
			throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("startDate", startDate);
		params.put("endDate", endDate);
		params.put("fetchcount", fetchcount);
		return this.getSqlMapClientTemplate().queryForList("getTopUsers",
				params);
	}

	@Override
	public List<UserValue> getRecentRegistUsers(Date startDate, Date endDate,
			int startindex, int fetchcount) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("startindex", startindex);
		params.put("fechcount", fetchcount);
		return this.getSqlMapClientTemplate().queryForList(
				"getRecentRegistUsers", params);
	}

	@Override
	public List<TopDocumentValue> getTopDocuments(Date fetchDate, Integer Type,
			String docid, Integer limit) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> queryparams = new HashMap<String, Object>();
		queryparams.put("type", Type);
		queryparams.put("fetchDate", fetchDate);
		queryparams.put("limit", limit);
		return this.getSqlMapClientTemplate().queryForList("getTopDocuments",
				queryparams);
	}

	@Override
	public List<UserValue> getRecentLoginUsers(Integer dispalycount)
			throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("startindex", 0);
		params.put("fechcount", dispalycount);
		List<UserValue> users = this.getSqlMapClientTemplate().queryForList(
				"getRecentLoginUsers", params);
		return users;
	}

}
