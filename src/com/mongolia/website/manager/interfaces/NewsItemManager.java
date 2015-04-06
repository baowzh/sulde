package com.mongolia.website.manager.interfaces;

import java.util.List;
import java.util.Map;

import com.mongolia.website.model.NewsItem;

public interface NewsItemManager {
	public void delete(NewsItem entity);

	public void save(NewsItem entity);

	public void saveOrUpdate(NewsItem entity);

	public List<NewsItem> queryNewsItems(Map<String, Object> params)
			throws Exception;
}
