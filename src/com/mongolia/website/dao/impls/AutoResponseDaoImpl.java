package com.mongolia.website.dao.impls;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.mongolia.website.dao.interfaces.AutoResponseDao;
import com.mongolia.website.model.AutoResponse;
import com.mongolia.website.model.PagingAutoResModel;

@Repository("autoResponseDaoImpl")
public class AutoResponseDaoImpl extends BaseDaoiBatis implements
		AutoResponseDao {

	@Resource(name = "jdbcTemplate")
	private JdbcTemplate jdbcTemplate;

	@Override
	public void delete(AutoResponse entity) throws Exception {
		// TODO Auto-generated method stub
		this.getSqlMapClient().delete("delAutoResponse", entity);
	}

	@Override
	public List<AutoResponse> getAutoResponses(Map<String, Object> params)
			throws Exception {
		// TODO Auto-generated method stub
		return this.getSqlMapClient().queryForList("getAutoResponses", params);
	}

	@Override
	public void save(final AutoResponse entity) throws Exception {
		// TODO Auto-generated method stub
		this.getSqlMapClient().insert("addWechatautoresponse", entity);
	}

	@Override
	public void update(final AutoResponse entity) throws Exception {
		// TODO Auto-generated method stub
		this.getSqlMapClient().update("updWechatautoresponse", entity);

	}

	@Override
	public boolean checkexists(String id) throws Exception {
		// TODO Auto-generated method stub
		List<AutoResponse> response = this.getSqlMapClient().queryForList(
				"checkexistsAutoResponses", id);
		if (response.size() > 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public List<AutoResponse> pagingQueryAutoResponse(
			PagingAutoResModel paingModel) throws Exception {
		// TODO Auto-generated method stub
		return this.getSqlMapClientTemplate().queryForList(
				"pagingAutoResponses", paingModel);
	}

	@Override
	public Integer getAutoResponseCount(PagingAutoResModel paingModel)
			throws Exception {
		// TODO Auto-generated method stub
		return (Integer) this.getSqlMapClientTemplate().queryForObject(
				"pagingAutoResCount", paingModel);
	}

}
