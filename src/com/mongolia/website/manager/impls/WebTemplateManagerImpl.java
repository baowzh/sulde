package com.mongolia.website.manager.impls;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mongolia.website.dao.interfaces.TemplateManagerDao;
import com.mongolia.website.manager.ManagerException;
import com.mongolia.website.manager.interfaces.WebTemplateManager;
import com.mongolia.website.model.TemplateValue;

@Service("webTemplateManager")
public class WebTemplateManagerImpl implements WebTemplateManager {
	@Autowired
	private TemplateManagerDao templateManagerDao;

	@Override
	public List<TemplateValue> getTemplateValues(Map<String, Object> params)
			throws Exception {
		// TODO Auto-generated method stub
		return this.templateManagerDao.getTemplateValues(params);
	}

	@Override
	public void doAddTemplate(TemplateValue templateValue) throws Exception {
		this.templateManagerDao.addTemplate(templateValue);

	}

	@Override
	public void doDeleteTemplateValue(Map<String, String> params)
			throws Exception {
		Integer isused = this.templateManagerDao.checkTemplateIsUsed(params);
		if (isused.intValue() > 0) {
			throw new ManagerException("模板正在使用不能删除!");
		} else {
			this.templateManagerDao.deleteTemplateValue(params);
		}
	}

	@Override
	public void doUpdateTemplateValue(TemplateValue templateValue)
			throws Exception {
		// TODO Auto-generated method stub
		this.templateManagerDao.updateTemplateValue(templateValue);

	}

}
