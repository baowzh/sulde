package com.mongolia.website.controller.tag;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class CssTage extends TagSupport {
	/**
	 * 
	 */
	private String type;
	private static final long serialVersionUID = 1L;

	public int doStartTag() throws JspException {
		return EVAL_PAGE;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int doEndTag() throws JspException {
		try {
			JspWriter out = this.pageContext.getOut();
			String user_agent_kind = ((HttpServletRequest) this.pageContext
					.getRequest()).getHeader("user-agent");
			StringBuffer sb = new StringBuffer();
			if (user_agent_kind.toLowerCase().indexOf("Chrome".toLowerCase()) > 0) {
				sb.append("<link href=\"site/css/webkit_fontface.css\" rel=\"stylesheet\" type=\"text/css\" />");
			} else if (user_agent_kind.toLowerCase().indexOf("MSIE".toLowerCase()) > 0) {
				sb.append("<link href=\"site/css/ie_fontface.css\" rel=\"stylesheet\" type=\"text/css\" />");
			}else{
				sb.append("<link href=\"site/css/webkit_fontface.css\" rel=\"stylesheet\" type=\"text/css\" />");
			}
			out.print(sb.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return EVAL_PAGE;
	}
}
