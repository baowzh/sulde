package com.mongolia.website.controler.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.mongolia.website.model.UserValue;
import com.mongolia.website.util.StaticConstants;

/**
 * 用户授权过滤器
 * 
 * @author Administrator
 * 
 */
public class AuthenticationFilter implements Filter {
	/**
	 * 需要权限控制的url
	 */
	private List<String> authurls = new ArrayList<String>();
	/**
	 * 管理员权限的
	 */
	private List<String> adminurls = new ArrayList<String>();

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) arg0;
		boolean tologin = false;
		for (String url : authurls) {
			if (request.getRequestURI().endsWith(url)) {
				Object obj = request.getSession().getAttribute("user");
				if (obj == null) {// 是否只有登录才能访问
					tologin = true;
					request.getSession().setAttribute("directurl", request.getRequestURI());
					break;
				} else {
					// 校验是否只有管理员才能访问
					int userkind = ((UserValue) obj).getUserkind();
					if (userkind == StaticConstants.USER_KIND1) {
						for (String uri : adminurls) {
							if (request.getRequestURI().endsWith(uri)) {// 权限不足
								tologin = true;
								// 把目标url保存住用于登录以后跳转
								
								break;
							}
						}
					}
				}
			}
		}
		if (!tologin) {
			arg2.doFilter(arg0, arg1);
		} else {
			request.getRequestDispatcher("tologin.do").forward(arg0, arg1);
		}

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		String urls = arg0.getInitParameter("authurls");
		String url[] = urls.split(",");
		for (int i = 0; i < url.length; i++) {
			authurls.add(url[i].trim());
		}
		// 管理员权限才可以访问的目录
		String admurls = arg0.getInitParameter("adminurls");
		String admurl[] = admurls.split(",");
		for (int i = 0; i < admurl.length; i++) {
			adminurls.add(admurl[i].trim());
		}
	}
}
