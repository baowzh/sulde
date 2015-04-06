package com.mongolia.website.dao.interfaces;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
public interface IBatisBaseDao {

	/**
	 * 获取数据表所有的记录
	 * @param clazz
	 * @return
	 */
	@SuppressWarnings("unchecked")
	List getAll(Class clazz);

	/**
	 * 根据主键从数据表中取回一个对象
	 * @param clazz
	 * @param id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	Object get(Class clazz, Serializable id);

	Object save(Object object);

	/**
	 * 增加记录到数据表中
	 */
	void insert(Object object) ;

	/**
	 * 根据主键删除一条数据库记录
	 * @param clazz
	 * @param id
	 */
	@SuppressWarnings("unchecked")
	void remove(Class clazz, Serializable primaryKey);

	/**
	 * 依据条件进行删除
	 * @param clazz
	 * @param paramMap
	 */
	@SuppressWarnings("unchecked")
	void remove(Class clazz, Map<String, Object> paramMap);

	/**
	 * 验证对象是否已经被使用
	 */
	boolean exists(Object object);
	
	void update(Object object);

}