package com.mongolia.website.manager.interfaces;

import java.io.Serializable;
import java.util.List;
public interface BaseManager {

	/**
	 * 获取全部数据
	 * @param clazz
	 * @return
	 */
	@SuppressWarnings("unchecked")
	List getAll(Class clazz);

	/**
	 * 获取单个记录
	 * @param clazz
	 * @param id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	Object get(Class clazz, Serializable id);

	/***
	 * 保存记录
	 * @param object
	 * @return
	 */
	Object save(Object object);
	
	/**
	 * 增加记录到数据表中
	 */
	void insert(Object object);

	/**
	 * 删除记录
	 */
	@SuppressWarnings("unchecked")
	void remove(Class clazz, Serializable id);

	void update(Object object);
}
