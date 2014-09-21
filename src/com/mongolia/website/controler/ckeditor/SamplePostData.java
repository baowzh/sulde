package com.mongolia.website.controler.ckeditor;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

public class SamplePostData {
	public SamplePostData(HttpServletRequest request) {
		this.request = request;
	}

	public String getAllFormFieldsAndValues() {
		StringBuffer sb = new StringBuffer();
		String fieldValue = "";
		try {
			this.request.setCharacterEncoding("utf-8");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		for (Enumeration e = request.getParameterNames(); e.hasMoreElements(); sb
				.append("</tr>")) {
			String field = (String) e.nextElement();
			if (field.equalsIgnoreCase("htmlcon")) {
				fieldValue = request.getParameter(field);
			}

		}
		return fieldValue;
	}

	private Object parse(String fieldValue) {
		String fv = fieldValue;
		for (int i = 0; i < CHARS_FROM.length; i++)
			fv = fv.replaceAll(CHARS_FROM[i], CHARS_TO[i]);

		return fv;
	}
	
	private static final String CHARS_FROM[] = { "&", "\"", "<", ">" };
	private static final String CHARS_TO[] = { "&amp;", "&quot;", "&lt;",
			"&gt;" };
	private static final long serialVersionUID = 0x8917378a5233beb2L;
	private HttpServletRequest request;
}
