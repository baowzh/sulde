package com.mongolia.website.manager.interfaces;

import java.util.List;

import com.mongolia.website.model.UserValue;

/**
 * 用户信息模块接口
 * 
 * @author shadow
 * 
 */
public interface UserAccountManager {

	/**
	 * 更新登录信息
	 * 
	 * @param userValue
	 */
	public void LoginForUpdate(UserValue userValue);

	/**
	 * 安全退出功能
	 * 
	 * @return String
	 */
	public String logout();

	/**
	 * 检测用户是否存在
	 * 
	 * @param username
	 * @return Boolean
	 */
	public boolean checkUser(String username);

	/**
	 * 通过用户名获取账号
	 * 
	 * @param username
	 * @return List<User>
	 */
	public List<UserValue> findByUserName(String username);

}
