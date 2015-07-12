package com.mongolia.website.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;
import org.springframework.web.servlet.ModelAndView;

import com.mongolia.website.manager.interfaces.AutoResponseManager;
import com.mongolia.website.manager.interfaces.WebSiteVisitorManager;
import com.mongolia.website.model.AutoResponse;
import com.mongolia.website.model.DocumentValue;
import com.mongolia.website.model.PagingAutoResModel;
import com.mongolia.website.model.PaingModel;
import com.mongolia.website.model.WechatDocValue;
import com.mongolia.website.util.ImgeUtil;
import com.mongolia.website.util.StaticConstants;
import com.mongolia.website.util.UUIDMaker;

@Controller
public class AutoResponseController {
	@Resource(name = "autoResponseManagerImpl")
	private AutoResponseManager autoResponseManager;
	@Autowired
	private WebSiteVisitorManager webSiteVisitorManager;

	@RequestMapping("/autoresponselist.do")
	public ModelAndView autoresponselist(HttpServletRequest request,
			PagingAutoResModel paingModel, ModelMap map) throws Exception {
		paingModel = this.autoResponseManager.pagingqueryAutoResp(paingModel);
		String resid = request.getParameter("id");
		map.put("paingModel", paingModel);
		map.put("resid", resid);
		return new ModelAndView("wechat/autoresponselist", map);
	}

	@RequestMapping("/getautoresponselist.do")
	public ModelAndView getautoresponselist(HttpServletRequest request,
			PagingAutoResModel paingModel, ModelMap map) throws Exception {
		if (request.getParameter("page") != null) {
			paingModel.setPageindex(Integer.parseInt(request
					.getParameter("page")));
		}
		paingModel = this.autoResponseManager.pagingqueryAutoResp(paingModel);
		map.put("rows", paingModel.getModelList());
		map.put("records", paingModel.getRowcount());
		map.put("page", paingModel.getPageindex());
		map.put("total", paingModel.getPagecount());
		return new ModelAndView("jsonView", map);
	}

	@RequestMapping("/addAutoresponse.do")
	public ModelAndView addAutoresponse(HttpServletRequest request,
			AutoResponse autoResponse, ModelMap map) throws Exception {
		try {
			this.autoResponseManager.saveOrUpdate(autoResponse);
			map.put("status", 1);
		} catch (Exception ex) {
			map.put("status", "404");
			map.put("mess", ex.getMessage());
		}
		return new ModelAndView("jsonView", map);
	}

	@RequestMapping("/updAutoresponse.do")
	public ModelAndView updAutoresponse(HttpServletRequest request,
			AutoResponse autoResponse, ModelMap map) throws Exception {
		try {
			this.autoResponseManager.saveOrUpdate(autoResponse);
			map.put("status", 1);
		} catch (Exception ex) {
			map.put("status", "404");
			map.put("mess", ex.getMessage());
		}
		return new ModelAndView("jsonView", map);
	}

	@RequestMapping("/delAutoresponse.do")
	public ModelAndView delAutoresponse(HttpServletRequest request,
			AutoResponse autoResponse, ModelMap map) throws Exception {
		this.autoResponseManager.delete(autoResponse);
		return new ModelAndView("jsonView", map);
	}

	@RequestMapping("/addWechatDoc.do")
	public ModelAndView addWechatDoc(HttpServletRequest request,
			WechatDocValue wechatDocValue, ModelMap map) throws Exception {
		// 先保存图片并把位置给wechatdoc
		String path = request.getSession().getServletContext()
				.getRealPath("/html/img");
		String imgid = UUIDMaker.getUUID();
		String imgname = imgid + ".jpg";
		if (wechatDocValue.getImg() != null
				&& wechatDocValue.getImg().length != 0) {
			ImgeUtil.CompressPic(wechatDocValue.getImg(), path, imgname,true);
			wechatDocValue.setDocimg(imgname);
		}
		this.autoResponseManager.addWechatDoc(wechatDocValue);
		return new ModelAndView("redirect:autoresponselist.do?id="
				+ wechatDocValue.getResponseid(), map);
	}

	@RequestMapping("/delWechatDoc.do")
	public ModelAndView delWechatDoc(HttpServletRequest request,
			AutoResponse autoResponse, ModelMap map) throws Exception {
		String docid = request.getParameter("docid");
		String autoresid = request.getParameter("autoresid");
		this.autoResponseManager.delWechatDoc(docid, autoresid);
		return new ModelAndView("jsonView", map);
	}

	@RequestMapping("/queryrecentdoc.do")
	public ModelAndView queryrecentdoc(HttpServletRequest request,
			PaingModel<DocumentValue> pagingModel, ModelMap map)
			throws Exception {
		if (request.getParameter("page") != null) {
			pagingModel.setPageindex(Integer.parseInt(request
					.getParameter("page")));
		}
		pagingModel.setDoctype(StaticConstants.DOCTYPE_DOC);
		pagingModel.setDocstatus(StaticConstants.DOCSTATUS2);
		PaingModel<DocumentValue> paingModel1 = this.webSiteVisitorManager
				.pagingquerydoc(pagingModel);
		map.put("rows", paingModel1.getModelList());
		map.put("records", paingModel1.getRowcount());
		map.put("page", paingModel1.getPageindex());
		map.put("total", paingModel1.getPagecount());
		return new ModelAndView("jsonView", map);
	}

	@InitBinder
	protected void initBinder(WebDataBinder binder) throws ServletException {
		binder.registerCustomEditor(byte[].class,
				new ByteArrayMultipartFileEditor());
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, false));
	}

	@RequestMapping("/queryresdoc.do")
	public ModelAndView queryresdoc(HttpServletRequest request, ModelMap map)
			throws Exception {
		String autoresid = request.getParameter("autoresid");
		List<WechatDocValue> wechatDocValues = this.autoResponseManager
				.getWechatDocValues(autoresid);
		map.put("rows", wechatDocValues);
		map.put("records", wechatDocValues.size());
		map.put("page", 1);
		map.put("total", 1);
		return new ModelAndView("jsonView", map);
	}

	@RequestMapping("/testSendMess.do")
	public ModelAndView testSendMess(HttpServletRequest request, ModelMap map)
			throws Exception {
		this.autoResponseManager.sendServiceMess();
		return new ModelAndView("jsonView", map);
	}

}
