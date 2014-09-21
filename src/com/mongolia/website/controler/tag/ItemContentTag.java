package com.mongolia.website.controler.tag;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.mongolia.website.manager.interfaces.WebSiteVisitorManager;
import com.mongolia.website.model.ProgramItem;

public class ItemContentTag extends TagSupport {
	private WebSiteVisitorManager webSiteVisitorManager;

	@Override
	public int doEndTag() throws JspException {
		// TODO Auto-generated method stub
		return this.EVAL_PAGE;
	}

	@Override
	public int doStartTag() throws JspException {
		try {
			String itemid = (String) this.pageContext.getRequest()
					.getAttribute("itemid");
			String itemidKind = (String) this.pageContext.getRequest()
					.getAttribute("itemidKind");
			StringBuffer buffer = new StringBuffer();
			buffer.append(this.getContent(itemid, itemidKind));
			pageContext.getOut().write(buffer.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return this.EVAL_BODY_INCLUDE;
	}

	private String getContent(String itemId, String itemKind) {
		WebApplicationContext webApplicationContext = WebApplicationContextUtils
				.getWebApplicationContext(this.pageContext.getServletContext());
		this.webSiteVisitorManager = (WebSiteVisitorManager) webApplicationContext
				.getBean("webSiteVisitorManager");
		Map<String, Object> params = new HashMap<String, Object>();
		try {
			params.put("itemid", itemId);
			params.put("programkind", itemKind);
			ProgramItem programItem = this.webSiteVisitorManager
					.getItemContent(params);
			String conten = new String(programItem.getContent());
			return conten;
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return "";
	}
}
