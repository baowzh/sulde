package com.mongolia.website.manager.interfaces;

import java.util.List;

import com.mongolia.website.model.DistrictValue;
import com.mongolia.website.model.FriendValue;
import com.mongolia.website.model.ProfessionValue;
import com.mongolia.website.model.UserValue;

public interface UserManager {
	/**
	 * retreive users
	 * 
	 * @param params
	 * @return
	 */
	public List<UserValue> getUsers(String userid, String username);

	/**
	 * create a user
	 * 
	 * @param userValue
	 * @throws Exception
	 */
	public void doCreateUser(UserValue userValue) throws Exception;

	/**
	 * 删除用户
	 * 
	 * @param params
	 * @throws Exception
	 */
	public void doDeleteUser(String userids[]) throws Exception;

	/**
	 * 停用用户
	 * 
	 * @param userids
	 * @throws Exception
	 */
	public void doStopUser(String userids[]) throws Exception;

	/**
	 * 
	 * @param userids
	 * @throws Exception
	 */
	public void doReUseUser(String userids[]) throws Exception;

	/**
	 * update a user
	 * 
	 * @param userValue
	 * @throws Exception
	 */
	public void doUpdateUser(UserValue userValue) throws Exception;

	/**
	 * 用户登录
	 * 
	 * @param UserValue
	 * @throws Exception
	 */
	public boolean doLogin(UserValue UserValue) throws Exception;

	/**
	 * 
	 * @throws Exception
	 */
	public void doAddFriendAndConfirmMess(FriendValue friendValue,
			String messid, Integer agree) throws Exception;

	/**
	 * 修改用户名密码
	 * 
	 * @param userid
	 * @param pass
	 * @throws Exception
	 */
	public void doModifyPass(String userid, String pass,
			String oldpass,Integer maillogin) throws Exception;

	/**
	 * 解除朋友关系
	 * 
	 * @param userid
	 * @param friendid
	 * @throws Exception
	 */
	public void doDelFriend(String userid, String friendid) throws Exception;

	/**
	 * 
	 * @param districtcode
	 * @param parentcode
	 * @return
	 * @throws Exception
	 */
	public List<DistrictValue> getDistrictValues(String districtcode,
			String parentcode, String top) throws Exception;

	/**
	 * 获取职业信息
	 * @param professioncode
	 * @param professionname
	 * @return
	 * @throws Exception
	 */
	public List<ProfessionValue> getProfessionValues(String professioncode,
			String professionname) throws Exception;
	/**
	 * 获取邮箱登陆字符串
	 * @param username
	 * @return
	 * @throws Exception
	 */
	public UserValue getmaillogincode(String username) throws Exception;
}
