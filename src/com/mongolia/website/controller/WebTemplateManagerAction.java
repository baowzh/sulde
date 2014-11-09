package com.mongolia.website.controller;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.mongolia.website.manager.interfaces.WebTemplateManager;
import com.mongolia.website.model.TemplateValue;
import com.mongolia.website.util.UUIDMaker;

@Controller
public class WebTemplateManagerAction {
	@Autowired
	private WebTemplateManager webTemplateManager;

	/**
	 * 网站模板管理
	 * @param request
	 * @param response
	 * @param map
	 * @return
	 */
	@RequestMapping("/gototmpmanager.do")
	public ModelAndView goToTmplateManager(HttpServletRequest request,
			HttpServletResponse response, ModelMap map) {
		try {
			List<TemplateValue> templates = webTemplateManager
					.getTemplateValues(new HashMap<String, Object>());
			map.put("templates", templates);
		} catch (Exception ex) {
			return new ModelAndView("sitemanager/error", map);
		}
		return new ModelAndView("sitemanager/tempatemanager", map);
	}
    /**
     * 获取网站模板列表
     * @param request
     * @param response
     * @param map
     * @return
     */
	@RequestMapping("/gettemplates.do")
	public ModelAndView gettemplates(HttpServletRequest request,
			HttpServletResponse response, ModelMap map) {
		try {
			List<TemplateValue> templates = webTemplateManager
					.getTemplateValues(new HashMap<String, Object>());
			map.put("success", "true");
			map.put("total", 10);
			map.put("records", 1);
			map.put("page", 1);
			map.put("rows", templates);
			map.put("treeNodes", templates);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ModelAndView("jsonView", map);
	}

	@RequestMapping("/totempadd.do")
	public ModelAndView gotoTempAdd(HttpServletRequest request,
			HttpServletResponse response, ModelMap map) {
		return new ModelAndView("sitemanager/addtemplate", map);
	}
    /**
     * 新增网站模板
     * @param request
     * @param response
     * @param map
     * @param templateValue
     * @return
     */
	@RequestMapping("/addtemplate.do")
	public ModelAndView addTemplate(HttpServletRequest request,
			HttpServletResponse response, ModelMap map,
			TemplateValue templateValue) {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		MultiValueMap file = multipartRequest.getMultiFileMap();
		Set<String> set = file.keySet();
		Iterator iterator = set.iterator();
		while (iterator.hasNext()) {
			String name = (String) iterator.next();
			List files = (List) file.get(name);
			try {
				for (int i = 0; i < files.size(); i++) {
					CommonsMultipartFile commonsMultipartFile = (CommonsMultipartFile) files
							.get(i);
					templateValue.setTempfile(commonsMultipartFile.getBytes());
					String content = new String(
							commonsMultipartFile.getBytes(), "utf-8");
					System.out.println(content);
				}

			} catch (Exception ex) {
				return new ModelAndView("sitemanager/error", map);
			}

		}
		try {
			templateValue.setTempid(UUIDMaker.getUUID());
			this.webTemplateManager.doAddTemplate(templateValue);
		} catch (Exception ex) {
			map.put("errorMess", ex.getLocalizedMessage());
			return new ModelAndView("sitemanager/error", map);
		}

		return this.goToTmplateManager(request, response, map);
	}
    /**
     * 删除网站模板
     * @param request
     * @param response
     * @param map
     * @return
     */
	@RequestMapping("/deletetemplate.do")
	public ModelAndView deleteTemplate(HttpServletRequest request,
			HttpServletResponse response, ModelMap map) {
		try {
			String deleteids = (String) request.getParameter("deleteids");
			String ids[] = deleteids.split("#");
			for (int i = 0; i < ids.length; i++) {
				Map<String, String> deletParams = new HashMap<String, String>();
				deletParams.put("id", ids[i]);
				this.webTemplateManager.doDeleteTemplateValue(deletParams);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			map.put("mess", ex.getLocalizedMessage());
		}
		return this.goToTmplateManager(request, response, map);
	}
}
