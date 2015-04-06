package com.mongolia.website.dao.interfaces;

import java.util.List;
import java.util.Map;

import com.mongolia.website.model.NewsItem;

public interface NewsItemDao {
	/**
	 * 
	 * @param entity
	 */
	public void delete(NewsItem entity);

	/**
	 * 
	 * @param entity
	 */
	public void save(NewsItem entity);

	/**
	 * 
	 * @param entity
	 */
	public void update(NewsItem entity);

	/**
	 * 
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public List<NewsItem> queryNewsItems(Map<String, Object> params)
			throws Exception;
}
