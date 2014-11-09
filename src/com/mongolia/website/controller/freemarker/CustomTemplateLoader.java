package com.mongolia.website.controller.freemarker;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import org.springframework.beans.factory.annotation.Autowired;
import com.mongolia.website.dao.interfaces.TemplateManagerDao;
import freemarker.cache.TemplateLoader;
/**
 *  
 *  freemarker 模板加载器
 * @author baowzh
 *
 */
public class CustomTemplateLoader implements TemplateLoader {
	public CustomTemplateLoader() {
		System.out.println("");
	}

	@Autowired
	private TemplateManagerDao templateManagerDao;

	@Override
	public void closeTemplateSource(Object templateSource) throws IOException {
		// TODO Auto-generated method stub
	}

	@Override
	public Object findTemplateSource(String name) throws IOException {
		// TODO Auto-generated method stub

		return templateManagerDao.getWebTemplate(name);
	}

	@Override
	public long getLastModified(Object templateSource) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Reader getReader(Object templateSource, String encoding)
			throws IOException {
		// TODO Auto-generated method stubw
		String tempStr = new String((byte[]) templateSource,"utf-8");
		StringReader stringReader = new StringReader(tempStr);
		return stringReader;
	}

}
