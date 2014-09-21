package com.mongolia.website.dao.impls;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.mongolia.website.dao.interfaces.TemplateManagerDao;
import com.mongolia.website.model.TemplateValue;
import com.mongolia.website.model.Webtemplate;
@Repository("templateManagerDao")
public class TemplateManagerDaoImpl extends BaseDaoiBatis implements TemplateManagerDao {

	@Override
	public byte[] getWebTemplate(String tempname) {
		// TODO Auto-generated method stub
		Map<String,Object> queryMap=new HashMap<String,Object>();
		queryMap.put("name", tempname);
		Webtemplate webtemplate=(Webtemplate) this.getSqlMapClientTemplate().queryForObject("getwebtemplate",queryMap);
		return webtemplate.getTemplatefile();
	}
	@Override
	public List<TemplateValue> getTemplateValues(Map<String, Object> params)
			throws Exception {
		// TODO Auto-generated method stub
		return this.getSqlMapClientTemplate().queryForList("gettemplatevalues",
				params);
	}

	@Override
	public void addTemplate(TemplateValue templateValue) throws Exception {
		// TODO Auto-generated method stub
		this.getSqlMapClientTemplate().insert("addTemplate", templateValue);

	}

	@Override
	public void deleteTemplateValue(Map<String, String> params)
			throws Exception {
		// TODO Auto-generated method stub
		this.getSqlMapClientTemplate().delete("deleteTemplateValue", params);

	}

	@Override
	public void updateTemplateValue(TemplateValue templateValue)
			throws Exception {
		// TODO Auto-generated method stub
		this.getSqlMapClientTemplate().update("deleteTemplateValue",
				templateValue);

	}

	@Override
	public Integer checkTemplateIsUsed(Map<String, String> params)
			throws Exception {
		// TODO Auto-generated method stub
		return (Integer) this.getSqlMapClientTemplate().queryForObject(
				"checktemplateisused", params);
	}


}
