package com.mongolia.website.controler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 广告管理模块
 * 
 * @author Administrator
 *
 */
@Controller
public class AdvertAction {
	@RequestMapping("/advertindex.do")
	public ModelAndView advertindex(HttpServletRequest request, ModelMap map) {
		return new ModelAndView("advert/advertindex.jsp", map);
	}
	@RequestMapping("/advertList.do")
	public ModelAndView getAdvertList(HttpServletRequest request, ModelMap map) {
		return new ModelAndView("jsonView");
	}
}
