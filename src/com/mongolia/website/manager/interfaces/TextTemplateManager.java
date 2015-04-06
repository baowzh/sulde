package com.mongolia.website.manager.interfaces;

import java.util.List;
import java.util.Map;

import com.mongolia.website.model.TextTemplate;

public interface TextTemplateManager {
	/**
	 * 
	 * @param entity
	 */
	public void delete(TextTemplate entity);

	/**
	 * 
	 * @param entity
	 * @return
	 */
	public void save(TextTemplate entity);

	/**
	 * 
	 * @param entity
	 */
	public void saveOrUpdate(TextTemplate entity) throws Exception;

	/**
	 * 
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public List<TextTemplate> queryTextTemplate(Map<String, Object> params)
			throws Exception;
}
