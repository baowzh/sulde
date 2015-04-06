package com.mongolia.website.dao.interfaces;

import java.util.List;
import java.util.Map;

import com.mongolia.website.model.TemplateValue;

public interface TemplateManagerDao extends IBatisBaseDao {
	public byte[] getWebTemplate(String tempname);

	/**
	 * 获取网页模板
	 * 
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public List<TemplateValue> getTemplateValues(Map<String, Object> params)
			throws Exception;

	/**
	 * 增加网页模板
	 * 
	 * @param templateValue
	 * @throws Exception
	 */
	public void addTemplate(TemplateValue templateValue) throws Exception;

	/**
	 * 删除模板
	 * 
	 * @param params
	 * @throws Exception
	 */
	public void deleteTemplateValue(Map<String, String> params)
			throws Exception;

	/**
	 * 修改网页模板
	 * 
	 * @param templateValue
	 * @throws Exception
	 */
	public void updateTemplateValue(TemplateValue templateValue)
			throws Exception;

	/**
	 * 校验模板是否在使用
	 * 
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public Integer checkTemplateIsUsed(Map<String, String> params)
			throws Exception;
}
