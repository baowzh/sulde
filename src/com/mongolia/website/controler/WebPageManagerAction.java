package com.mongolia.website.controler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.mongolia.website.manager.interfaces.ChannelManager;
import com.mongolia.website.manager.interfaces.WebPageManager;
import com.mongolia.website.model.Channel;
import com.mongolia.website.model.PageChannelRelationValue;
import com.mongolia.website.model.WebPageValue;
import com.mongolia.website.util.UUIDMaker;

@Controller
public class WebPageManagerAction {
	@Autowired
	private WebPageManager webPageManager;
	@Autowired
	private ChannelManager channelManager;

	@RequestMapping("/gepages.do")
	public ModelAndView gepages(HttpServletRequest request, ModelMap map) {
		try {
			WebPageValue webPageValue = new WebPageValue();
			webPageValue.setPageid("0");
			webPageValue.setComment("全部页面");
			webPageValue.setPagename("全部页面");
			webPageValue.setParentpageid("");
			webPageValue.setShowtemplateid("");
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("pageid", request.getParameter("pageid"));
			List<WebPageValue> pages = this.webPageManager.getPages(params);
			pages.add(webPageValue);
			map.put("success", "true");
			map.put("treeNodes", pages);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ModelAndView("jsonView", map);
	}

	@RequestMapping("/getpageInfo.do")
	public ModelAndView getPageInfo(HttpServletRequest request, ModelMap map) {
		String pageid = request.getParameter("pageid");
		if (pageid == null || pageid.equalsIgnoreCase("")) {
			if (request.getAttribute("pageid") != null) {
				pageid = (String) request.getAttribute("pageid");
			}
		}
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("pageid", pageid);
		try {
			WebPageValue webPageValue = this.webPageManager.getpageInfo(params);
			map.put("pageInfo", webPageValue);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ModelAndView("sitemanager/pageinfo", map);
	}

	@RequestMapping("/addpage.do")
	public ModelAndView addpage(HttpServletRequest request,
			HttpServletResponse response, ModelMap map,
			WebPageValue webPageValue) {
		try {
			webPageValue.setPageid(UUIDMaker.getUUID());
			this.webPageManager.doAddWebPage(webPageValue);
		} catch (Exception ex) {
			map.put("errorMess", ex.getMessage());
			return new ModelAndView("sitemanager/error", map);
		}
		map.put("sucess", "1");
		request.setAttribute("pageid", webPageValue.getPageid());
		return getPageInfo(request, map);
	}

	@RequestMapping("/deletepage.do")
	public ModelAndView deletePage(HttpServletRequest request, ModelMap map) {
		String pageid = request.getParameter("pageid");
		try {
			this.webPageManager.doDeletePage(pageid);
		} catch (Exception ex) {
			map.put("errorMess", ex.getMessage());
			map.put("success", "0");
			return new ModelAndView("jsonView", map);
		}
		map.put("success", "1");
		return new ModelAndView("jsonView", map);
	}

	@RequestMapping("/updatepage.do")
	public ModelAndView updatePage(HttpServletRequest request, ModelMap map,
			WebPageValue webPageValue) {
		try {
			this.webPageManager.doModifyWebPage(webPageValue);
		} catch (Exception ex) {
			map.put("errorMess", ex.getMessage());
			return new ModelAndView("sitemanager/error", map);
		}
		map.put("sucess", "1");
		request.setAttribute("pageid", webPageValue.getPageid());
		return getPageInfo(request, map);
	}

	/**
	 * 获取跟页面关联的栏目列表
	 * 
	 * @param request
	 * @param map
	 * @return
	 */
	@RequestMapping("/getrelatedchannels.do")
	public ModelAndView getRelatedChannels(HttpServletRequest request,
			ModelMap map) {
		List channels = new ArrayList();
		try {
			String pageid = request.getParameter("pageid");
			channels = this.webPageManager.getRelatedChannes(pageid);
		} catch (Exception ex) {
			map.put("errorMess", ex.getMessage());
			return new ModelAndView("sitemanager/error", map);
		}
		map.put("success", "true");
		map.put("total", 10);
		map.put("records", channels);
		map.put("page", 1);
		map.put("rows", channels);
		// map.put("treeNodes", templates);
		return new ModelAndView("jsonView", map);
	}
     
	@RequestMapping("/gechannels.do")
	public ModelAndView geChannels(HttpServletRequest request, ModelMap map) {
		try {
			Channel webPageValue = new Channel();
			webPageValue.setChannelid("0");
			webPageValue.setChnldesc("全部栏目");
			webPageValue.setChnlname("全部栏目");
			webPageValue.setParentid("");
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("pageid", request.getParameter("pageid"));
			List<Channel> channels = this.channelManager.getChannelList(params);
			channels.add(webPageValue);
			map.put("success", "true");
			map.put("treeNodes", channels);
		} catch (Exception ex) {
			map.put("errorMess", ex.getMessage());
			return new ModelAndView("sitemanager/error", map);
		}
		return new ModelAndView("jsonView", map);
	}

	/**
	 * 给页面添加栏目列表
	 * 
	 * @param request
	 * @param map
	 * @return
	 */
	@RequestMapping("/addchanneltopage.do")
	public ModelAndView addChannelToPage(HttpServletRequest request,
			ModelMap map, PageChannelRelationValue pageChannelRelationValue) {
		try {
			this.webPageManager.doAddChannelToPage(pageChannelRelationValue);
		} catch (Exception ex) {
			map.put("success", "0");
			map.put("errorMess", ex.getMessage());
			return new ModelAndView("jsonView", map);
		}
		map.put("success", "1");
		return new ModelAndView("jsonView", map);
	}

	/**
	 * 从页面删除栏目定义
	 * 
	 * @param request
	 * @param map
	 * @return
	 */
	@RequestMapping("/deletechannelfrompage.do")
	public ModelAndView deleteChannelFromPage(HttpServletRequest request,
			ModelMap map) {
		try {
			String pageid = request.getParameter("pageid");
			String channelid = request.getParameter("channelid");
			String displayType = request.getParameter("displayType");
			this.webPageManager.doDeleteChannelFromPage(pageid, channelid,
					displayType);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ModelAndView("jsonView", map);
	}

}
