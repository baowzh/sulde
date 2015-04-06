package com.mongolia.website.manager.interfaces;

import java.util.List;
import java.util.Map;

import com.mongolia.website.model.PageValue;
import com.mongolia.website.model.PagingPrams;

/**
 * 
 * @author baowzh
 *
 * @param <T>
 *            业务数据类型
 * @param <P>
 *            参数类型
 */
public interface BaseService<T, P extends PagingPrams> {
	/**
	 * 
	 * @param pageValue
	 * @return
	 */
	public PageValue<T> pagingQuery(P pagingPrams);

	public void update(T t, Map<String, Object> p) throws Exception;

	public void insert(T t) throws Exception;

	public boolean exists(T t) throws Exception;

	public List<T> queryModels(P p) throws Exception;

	public List<T> getData(Map<String, Object> params) throws Exception;

}
