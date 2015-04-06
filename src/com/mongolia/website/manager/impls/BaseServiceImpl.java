package com.mongolia.website.manager.impls;

import java.util.List;
import java.util.Map;

import com.mongolia.website.dao.interfaces.BaseDao;
import com.mongolia.website.manager.interfaces.BaseService;
import com.mongolia.website.model.PageValue;
import com.mongolia.website.model.PagingPrams;

public abstract class BaseServiceImpl<T, P extends PagingPrams> implements
		BaseService<T, P> {
	@Override
	public PageValue<T> pagingQuery(P pageParams) {
		// TODO Auto-generated method stub
		PageValue<T> pageValue = new PageValue<T>();
		List<T> models = this.getBaseDao().pagingqueryData(pageParams);
		pageValue.setModels(models);
		Integer rowcount = this.getBaseDao().queryRowCount(pageParams);
		pageValue.setTotalrowcount(rowcount);
		return pageValue;
	}

	/**
	 * 通过这个方法把分页查询dao实例化
	 */
	protected abstract BaseDao<T, P> getBaseDao();

	@Override
	public void update(T t, Map<String, Object> p) throws Exception {
		// TODO Auto-generated method stub
		try {
			this.getBaseDao().update(t, p);
		} catch (Exception ex) {
			throw new Exception(ex.getMessage());
		}

	}

	@Override
	public void insert(T t) throws Exception {
		// TODO Auto-generated method stub
		try {
			this.getBaseDao().insert(t);
		} catch (Exception ex) {

		}
	}

	@Override
	public boolean exists(T t) throws Exception {
		// TODO Auto-generated method stub
		try {
			return this.getBaseDao().exists(t);
		} catch (Exception ex) {

		}
		return false;
	}

	@Override
	public List<T> queryModels(P p) throws Exception {
		// TODO Auto-generated method stub
		return this.getBaseDao().pagingqueryData(p);
	}

	@Override
	public List<T> getData(Map<String, Object> params) throws Exception {
		// TODO Auto-generated method stub
		return this.getBaseDao().getData(params);
	}

}
