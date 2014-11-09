package com.mongolia.website.controller.tag;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.mongolia.website.manager.interfaces.WebSiteVisitorManager;
import com.mongolia.website.model.ProgramItem;
import com.mongolia.website.model.ProgramValue;
import com.mongolia.website.util.StaticConstants;

/**
 * ������վ��ҳ��Ŀ��ǩ
 * 
 * @author baowzh
 * 
 */
public class ProgramTag extends TagSupport {

	private String listname;
	@Autowired
	private WebSiteVisitorManager webSiteVisitorManager;

	@Override
	public int doEndTag() throws JspException {
		// TODO Auto-generated method stub
		return this.EVAL_PAGE;
	}

	@Override
	public int doStartTag() throws JspException {
		try {
			List<ProgramValue> programList = (List<ProgramValue>) this.pageContext
					.getRequest().getAttribute(this.getListname());
			StringBuffer buffer = new StringBuffer();
			for (int i = 0; i < programList.size(); i++) {
				ProgramValue programValue = programList.get(i);
				StringBuffer divbuffer = this.doCreateProgramDiv(programValue);
				buffer.append(divbuffer);
			}
			pageContext.getOut().write(buffer.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return this.EVAL_BODY_INCLUDE;

	}

	public String getListname() {
		return listname;
	}

	public void setListname(String listname) {
		this.listname = listname;
	}

	/**
	 * ������Ŀ
	 * 
	 * @param programValue
	 * @return
	 */
	private StringBuffer doCreateProgramDiv(ProgramValue programValue) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("" + "<div class=\"" + programValue.getStyle()
				+ "\"  style='width:" + programValue.getWidth() + "px; height:"
				+ programValue.getHeight() + "px; top:" + programValue.getTop()
				+ "px; left:" + programValue.getLeft()
				+ "px; border-style: solid; z-index:"
				+ programValue.getZ_index() + "; border:"
				+ programValue.getBorder() + "px;" + "'>");
		if (!programValue.getProgramKind().equalsIgnoreCase(
				StaticConstants.PROGRAM_ADARTICLE)
				&& !programValue.getProgramKind().equalsIgnoreCase(
						StaticConstants.PROGRAM_IMAGE)) {
			StringBuffer itembuffer = this.doCreateItem(programValue);
			buffer.append(itembuffer);
		} else if (programValue.getProgramKind().equalsIgnoreCase(
				StaticConstants.PROGRAM_IMAGE)) {
			buffer
					.append("<div id=\"picplayer\" style=\"position:relative;overflow:hidden;width:560px;"
							+ "height:305px;clear:none;border:solid 1px #fff;\">"
							+ "there is a pic-player" + "</div> ");
		}
		buffer.append("</div>");
		return buffer;
	}

	/**
	 * ��Ŀ��ϸ
	 * 
	 * @param programValue
	 * @return
	 */
	private StringBuffer doCreateItem(ProgramValue programValue) {
		StringBuffer tablestr = new StringBuffer();
		try {
			this.webSiteVisitorManager = (WebSiteVisitorManager) WebApplicationContextUtils
					.getWebApplicationContext(
							this.pageContext.getServletContext()).getBean(
							"webSiteVisitorManager");
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("programkind", programValue.getProgramid());
			params.put("start", 0);
			params.put("end", 10);
			List programList = this.webSiteVisitorManager
					.getProgramItemList(params);
			tablestr
					.append("<div class=\"programName\"><strong><a href=\"www.baidu.com\" class=\"p12lan\">"
							+ programValue.getProgramcomm()
							+ "</a></strong></div><table width=\"100%\"  border=\"0\" cellpadding=\"2\"");
			tablestr.append("cellspacing=\"2\" class=\"buhBg\">");
			if (programList != null && !programList.isEmpty()) {
				for (int i = 0; i < programList.size() && i < 10; i++) {
					ProgramItem programItem = (ProgramItem) programList.get(i);
					tablestr
							.append("<tr><td height=\"24\"><img src=\"img\\indexdot.jpg\"></td>");
					tablestr
							.append("<td height=\"24\"><a href=\"getcontent.do?itemid="
									+ programItem.getItemid()
									+ "&itemkind="
									+ programItem.getProgramkind()
									+ "\" class=\"p12hui\">"
									+ programItem.getTitle() + "</td>");
					SimpleDateFormat formater = new SimpleDateFormat(
							"yyyy-MM-dd");
					String releaseDate = formater.format(programItem
							.getReleaseDate());
					tablestr.append("<td height=\"24\" class=\"p12hui\">"
							+ releaseDate + "</td>");
					tablestr.append("</tr>");
				}
			}
			// if (programList.size() < 10) {
			// for (int i = programList.size() - 1; i < 10; i++) {
			// tablestr
			// .append("<tr><td height=\"24\"><img src=\"img\\dot.png\"></td>");
			// tablestr.append("<td height=\"24\"></td>");
			// tablestr.append("<td height=\"24\"></td>");
			// tablestr.append("</tr>");
			// }
			// }
			tablestr.append("</table>");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tablestr;
	}

}
