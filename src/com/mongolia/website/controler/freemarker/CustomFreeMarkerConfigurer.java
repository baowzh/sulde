package com.mongolia.website.controler.freemarker;

import java.util.List;

import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import freemarker.cache.FileTemplateLoader;
import freemarker.cache.TemplateLoader;

public class CustomFreeMarkerConfigurer extends FreeMarkerConfigurer {

	@Override
	protected void postProcessTemplateLoaders(
			List<TemplateLoader> templateLoaders) {
		// TODO Auto-generated method stub
		for(int i=0;i<templateLoaders.size();i++){
			if (templateLoaders.get(i) instanceof FileTemplateLoader){
				templateLoaders.remove(i);
			}
		}
		super.postProcessTemplateLoaders(templateLoaders);
	}	

}
