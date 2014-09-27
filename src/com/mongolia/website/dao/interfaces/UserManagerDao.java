package com.mongolia.website.dao.interfaces;

import java.util.List;
import java.util.Map;

import com.mongolia.website.model.DistrictValue;
import com.mongolia.website.model.FriendValue;
import com.mongolia.website.model.UserValue;

public interface UserManagerDao extends BaseDao {
	/**
	 * list users
	 * 
	 * @param params
	 * @return
	 */
	public List<UserValue> getUser(Map<String, Object> params);

	/**
	 * create a user
	 * 
	 * @param userValue
	 * @throws Exception
	 */
	public void createUser(UserValue userValue) throws Exception;

	/**
	 * delete a user
	 * 
	 * @param params
	 * @throws Exception
	 */
	public void deleteUser(Map<String, Object> params) throws Exception;

	/**
	 * update a user
	 * 
	 * @param userValue
	 * @throws Exception
	 */
	public void updateUser(UserValue userValue) throws Exception;

	/**
	 * @param friendValue
	 * @throws Exception
	 */
	public void addFriend(FriendValue friendValue) throws Exception;

	/**
	 * 启用用户
	 * 
	 * @param userid
	 * @throws Exception
	 */
	public void reuseUser(String userid) throws Exception;

	/**
	 * 停用用户
	 * 
	 * @param userid
	 * @throws Exception
	 */
	public void stopUser(String userid) throws Exception;

	/**
	 * 修改用户密码
	 * 
	 * @param userid
	 * @param username
	 * @param pass
	 * @throws Exception
	 */
	public void modifyUserPass(String userid, String username, String pass)
			throws Exception;

	/**
	 * 
	 * @param userid
	 * @param friendid
	 * @throws Exception
	 */
	public void delFriend(String userid, String friendid) throws Exception;

	/**
	 * 
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public List<UserValue> paingQueryUser(Map<String, Object> params)
			throws Exception;

	/**
	 * 
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public Integer paingUserCount(Map<String, Object> params) throws Exception;

	/**
	 * 
	 * @param districtcode
	 * @param parentcode
	 * @return
	 * @throws Exception
	 */
	public List<DistrictValue> getDistrictValues(String districtcode,
			String parentcode, String top) throws Exception;
}
