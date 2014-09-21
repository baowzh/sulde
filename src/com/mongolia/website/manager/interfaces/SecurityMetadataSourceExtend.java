package com.mongolia.website.manager.interfaces;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

public interface SecurityMetadataSourceExtend extends
		FilterInvocationSecurityMetadataSource {

	/**
	 * 初始化操作
	 */
	public void init();

	/**
	 * 加载资源
	 */
	public void load();

	/**
	 * 刷新资源
	 */
	public void expireNow();
}
