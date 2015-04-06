package com.mongolia.website.manager.impls;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mongolia.website.dao.interfaces.TextTemplateDao;
import com.mongolia.website.manager.interfaces.TextTemplateManager;
import com.mongolia.website.model.TextTemplate;
import com.mongolia.website.util.UUIDMaker;
@Service("textTemplateManagerImpl")
public class TextTemplateManagerImpl implements TextTemplateManager {

	@Resource(name = "textTemplateDaoImpl")
	TextTemplateDao textTemplateDao;

	@Override
	public void delete(TextTemplate entity) {
		// TODO Auto-generated method stub
		this.textTemplateDao.delete(entity);

	}

	@Override
	public void save(TextTemplate entity) {
		// TODO Auto-generated method stub
		entity.setId(UUIDMaker.getUUID());
//		entity.setAccountid(ResourceUtil.getWeiXinAccount()
//				.getAccountid());
		 this.textTemplateDao.save(entity);
	}

	@Override
	public void saveOrUpdate(TextTemplate entity)throws Exception  {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<String, Object>();
		try{
			params.put("id", entity.getId());
		List<TextTemplate> textTemplates = this
				.queryTextTemplate(params);
		if(textTemplates==null||textTemplates.isEmpty()){
			 this.save(entity);
		}else{
			this.textTemplateDao.Update(entity);
		}
		}catch(Exception ex){
			throw ex;
		}

	}

	@Override
	public List<TextTemplate> queryTextTemplate(Map<String, Object> params)
			throws Exception {
		// TODO Auto-generated method stub
		return textTemplateDao.queryTextTemplate(params);
	}

}
