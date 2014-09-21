package com.mongolia.website.controler;

import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import com.mongolia.website.model.PaingModel;
/**
 * 分页查询接口(需要进行分页查询的程序实现此类)
 * @author baowzh
 *
 */
public interface PaingActionIA {
	
	public ModelAndView pagingData(PaingModel model, ModelMap map);

	public ModelAndView refreshData(PaingModel model, ModelMap map);

}
