package com.mongolia.website.controller;

import java.util.Date;
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

import com.mongolia.website.controller.ckeditor.SamplePostData;
import com.mongolia.website.manager.interfaces.ChannelManager;
import com.mongolia.website.manager.interfaces.UserManager;
import com.mongolia.website.manager.interfaces.WebResourceManager;
import com.mongolia.website.model.Channel;
import com.mongolia.website.model.DocumentValue;
import com.mongolia.website.model.UserValue;
import com.mongolia.website.util.StaticConstants;
import com.mongolia.website.util.UUIDMaker;

@Controller
public class BubaiTelheiAction {
	@Autowired
	private ChannelManager channelManager;
	@Autowired
	private WebResourceManager webResourceManager;
	@Autowired
	private UserManager userManager;

	@RequestMapping("/bubaitoadddoc.do")
	public ModelAndView addDoc(HttpServletRequest request,
			HttpServletResponse response, ModelMap map) throws Exception {
		map.put("opertype", "1");
		Integer agentkind = 0;
		List<Channel> chanels = this.channelManager.getRaceChannelList();
		String user_agent_kind = request.getHeader("user-agent");
		if (user_agent_kind.indexOf("Chrome") > 0) {
			agentkind = 1;
		} else {
			agentkind = 0;
		}
		map.put("agentkind", agentkind);
		map.put("chanels", chanels);
		return new ModelAndView("baitelhei/createDoc", map);
	}

	@RequestMapping("/bubaiadddoc.do")
	public ModelAndView adddoc(HttpServletRequest request,
			HttpServletResponse response, ModelMap map, DocumentValue docValue) {
		List<UserValue> users = this.userManager.getUsers("99999", null);
		UserValue userValue = users.get(0);
		SamplePostData samplePostData = new SamplePostData(request);
		String content = samplePostData.getAllFormFieldsAndValues();
		docValue.setDoccontent(content.getBytes());
		docValue.setDocauthor(userValue.getArtname());
		docValue.setUserid(userValue.getUserid());
		docValue.setDocstatus(2);
		docValue.setDocsource(new Double(1));
		docValue.setDoctype(1);
		docValue.setDocid(UUIDMaker.getUUID());
		docValue.setDocreltime(new Date());
		docValue.setCrtime(new Date());
		try {
			webResourceManager.doAdddoc(docValue);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ModelAndView("redirect:bubaitoadddoc.do");
	}
	@RequestMapping("/bubaiupdatedoc.do")
	public ModelAndView updatedoc(HttpServletRequest request,
			HttpServletResponse response, ModelMap map, DocumentValue docValue) {
		UserValue sessionUser = (UserValue) request.getSession().getAttribute(
				"user");// 在线session
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("docid", docValue.getDocid());
			if (sessionUser.getUserkind().intValue() == StaticConstants.USER_KIND2
					.intValue()) {
				params.put("userid", null);
			} else {
				params.put("userid", sessionUser.getUserid());
			}
			List<DocumentValue> docList = this.webResourceManager
					.getDocList(params);
			DocumentValue documentValue = docList.get(0);
			SamplePostData samplePostData = new SamplePostData(request);
			String content = samplePostData.getAllFormFieldsAndValues();
			documentValue.setDoccontent(content.getBytes());
			documentValue.setDocstatus(1);
			documentValue.setDocsource(new Double(1));
			documentValue.setDoctype(1);
			documentValue.setDocabstract(docValue.getDocabstract());
			documentValue.setDoctitle(docValue.getDoctitle());
			if (sessionUser.getUserkind().intValue() == StaticConstants.USER_KIND2
					.intValue()) {
				documentValue.setCompiler(sessionUser.getArtname());
			}
			webResourceManager.doUpdDoc(documentValue);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
			return new ModelAndView("redirect:bubaitoadddoc.do");
	}

}
