package com.mongolia.website.manager.interfaces;

import java.util.List;
import java.util.Map;

import com.mongolia.website.manager.ManagerException;
import com.mongolia.website.model.TemplateValue;

public interface WebTemplateManager {
	/**
	 * 获取模板列表
	 * 
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public List<TemplateValue> getTemplateValues(Map<String, Object> params)
			throws Exception;

	/**
	 * 增加模板
	 * 
	 * @param templateValue
	 * @throws Exception
	 */
	public void doAddTemplate(TemplateValue templateValue) throws Exception;

	/**
	 * 删除模板
	 * 
	 * @param params
	 * @throws Exception
	 */
	public void doDeleteTemplateValue(Map<String, String> params)
			throws Exception;

	/**
	 * 修改模板
	 * 
	 * @param templateValue
	 * @throws Exception
	 */
	public void doUpdateTemplateValue(TemplateValue templateValue)
			throws Exception;
}
