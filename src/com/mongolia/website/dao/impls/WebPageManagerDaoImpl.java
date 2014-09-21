package com.mongolia.website.dao.impls;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.mongolia.website.dao.interfaces.WebPageManagerDao;
import com.mongolia.website.model.PageChannelRelationValue;
import com.mongolia.website.model.WebPageValue;

@Repository("webPageManagerDao")
public class WebPageManagerDaoImpl extends BaseDaoiBatis implements
		WebPageManagerDao {
	@Override
	public List<WebPageValue> getPages(Map<String, Object> params)
			throws Exception {
		// TODO Auto-generated method stub
		return this.getSqlMapClientTemplate().queryForList("getpages", params);
	}

	@Override
	public WebPageValue getpageInfo(Map<String, Object> params)
			throws Exception {
		// TODO Auto-generated method stub
		return (WebPageValue) this.getSqlMapClientTemplate().queryForObject(
				"getpages", params);
	}

	@Override
	public void addPage(WebPageValue webPageValue) throws Exception {
		// TODO Auto-generated method stub
		this.getSqlMapClientTemplate().insert("addwebpage", webPageValue);

	}

	@Override
	public void deletePage(Map<String, Object> params) throws Exception {
		// TODO Auto-generated method stub
		this.getSqlMapClientTemplate().delete("deletepage", params);

	}

	@Override
	public void modifyPage(WebPageValue webPageValue) throws Exception {
		// TODO Auto-generated method stub
		this.getSqlMapClientTemplate().update("updatepage", webPageValue);
	}

	@Override
	public void addChannelToPage(
			PageChannelRelationValue pageChannelRelationValue) throws Exception {
		// TODO Auto-generated method stub
		this.getSqlMapClientTemplate().insert("addChannelToPage",
				pageChannelRelationValue);

	}

	@Override
	public List<PageChannelRelationValue> getRelatedChannes(String pageid) throws Exception {
		// TODO Auto-generated method stub
		Map<String, String> params = new HashMap<String, String>();
		params.put("pageid", pageid);
		return this.getSqlMapClientTemplate().queryForList(
				"getRelatedChannels", params);
	}

	@Override
	public Integer checkIsRelated(
			PageChannelRelationValue pageChannelRelationValue) throws Exception {
		// TODO Auto-generated method stub
		return (Integer) this.getSqlMapClientTemplate().queryForObject(
				"checkChannelIsRelated", pageChannelRelationValue);
	}

	@Override
	public Integer isParentPage(String pageid) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> parmas = new HashMap<String, Object>();
		parmas.put("pageid", pageid);
		return (Integer) this.getSqlMapClientTemplate().queryForObject(
				"pageIsParentPage", parmas);
	}

	@Override
	public void deleteChannelFromPage(String pageid, String channelid,
			String displayType) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("pageid", pageid);
		params.put("channelid", channelid);
		params.put("displayType", displayType);
		this.getSqlMapClientTemplate().delete("deleteChannelFromPage",
				params);

	}

}
