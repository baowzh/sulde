package com.mongolia.website.dao.interfaces;

import java.util.List;

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
public interface BaseDao<T, P extends PagingPrams> {
	/**
	 * 查询业务数据
	 * 
	 * @param pageValue
	 * @return
	 */
	public List<T> pagingqueryData(P pageValue);

	/**
	 * 查询业务数据行数
	 * 
	 * @param pageValue
	 * @return
	 */
	public Integer queryRowCount(P pageValue);

	/**
	 * 修改一个实体
	 * 
	 * @param t
	 * @param params
	 * @throws DaoException
	 */
	public void update(T t, Object params) throws Exception;

	/**
	 * 新增实体
	 * 
	 * @param t
	 * @throws DaoException
	 */
	public void insert(T t) throws Exception;

	/**
	 * 校验是否存在
	 * 
	 * @param t
	 * @return
	 * @throws DaoException
	 */
	public boolean exists(T t) throws Exception;

	/**
	 * 按一定条件获取T类型的数据
	 * 
	 * @param params
	 * @return
	 * @throws DaoException
	 */
	public List<T> getData(Object param) throws Exception;

	/**
	 * 删除实体 T
	 * 
	 * @param t
	 * @throws DaoException
	 */
	public void del(T t) throws Exception;

}
