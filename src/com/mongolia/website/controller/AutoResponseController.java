package com.mongolia.website.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mongolia.website.manager.interfaces.AutoResponseManager;
import com.mongolia.website.manager.interfaces.WebSiteVisitorManager;
import com.mongolia.website.model.AutoResponse;
import com.mongolia.website.model.DocumentValue;
import com.mongolia.website.model.PagingAutoResModel;
import com.mongolia.website.model.PaingModel;
import com.mongolia.website.util.PageUtil;
import com.mongolia.website.util.StaticConstants;

@Controller
public class AutoResponseController {
	@Resource(name = "autoResponseManagerImpl")
	private AutoResponseManager autoResponseManager;
	@Autowired
	private WebSiteVisitorManager webSiteVisitorManager;

	@RequestMapping("/autoresponselist.do")
	public ModelAndView autoresponselist(HttpServletRequest request,
			PagingAutoResModel paingModel, ModelMap map) throws Exception {
		paingModel = this.autoResponseManager.pagingquerydoc(paingModel);
		map.put("paingModel", paingModel);
		return new ModelAndView("wechat/autoresponselist", map);
	}

	@RequestMapping("/addAutoresponse.do")
	public ModelAndView addAutoresponse(HttpServletRequest request,
			AutoResponse autoResponse, ModelMap map) throws Exception {
		this.autoResponseManager.saveOrUpdate(autoResponse);
		return new ModelAndView("redirect:autoresponselist.do", map);
	}

	@RequestMapping("/updAutoresponse.do")
	public ModelAndView updAutoresponse(HttpServletRequest request,
			AutoResponse autoResponse, ModelMap map) throws Exception {
		this.autoResponseManager.saveOrUpdate(autoResponse);
		return new ModelAndView("redirect:autoresponselist.do", map);
	}

	@RequestMapping("/delAutoresponse.do")
	public ModelAndView delAutoresponse(HttpServletRequest request,
			AutoResponse autoResponse, ModelMap map) throws Exception {
		this.autoResponseManager.delete(autoResponse);
		return new ModelAndView("redirect:autoresponselist.do", map);
	}

	@RequestMapping("/addWechatDoc.do")
	public ModelAndView addWechatDoc(HttpServletRequest request, ModelMap map)
			throws Exception {
		String docid = request.getParameter("docid");
		String autoresid = request.getParameter("autoresid");
		this.autoResponseManager.addWechatDoc(docid, autoresid);
		return new ModelAndView("redirect:autoresponselist.do", map);
	}

	@RequestMapping("/delWechatDoc.do")
	public ModelAndView delWechatDoc(HttpServletRequest request,
			AutoResponse autoResponse, ModelMap map) throws Exception {
		String docid = request.getParameter("docid");
		String autoresid = request.getParameter("autoresid");
		this.autoResponseManager.delWechatDoc(docid, autoresid);
		return new ModelAndView("redirect:autoresponselist.do", map);
	}

	@RequestMapping("/queryrecentdoc.do")
	public ModelAndView queryrecentdoc(HttpServletRequest request,
			PaingModel<DocumentValue> pagingModel, ModelMap map)
			throws Exception {
		if (pagingModel.getPagesize() == null
				|| pagingModel.getPagesize().intValue() == 0) {
			pagingModel.setPagesize(24);
		}
		pagingModel.setDoctype(StaticConstants.DOCTYPE_DOC);
		pagingModel.setDocstatus(StaticConstants.DOCSTATUS2);
		PaingModel<DocumentValue> paingModel1 = this.webSiteVisitorManager
				.pagingquerydoc(pagingModel);
		List<DocumentValue> doclist = paingModel1.getModelList();
		map.put("doclist", doclist);
		String pagestr = PageUtil.getPagingLink(pagingModel, 1);
		map.put("pagingstr", pagestr);
		return new ModelAndView("wechat/seldoclist", map);
	}

}
