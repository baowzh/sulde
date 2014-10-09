package com.mongolia.website.dao.impls;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.mongolia.website.dao.interfaces.UserManagerDao;
import com.mongolia.website.model.DistrictValue;
import com.mongolia.website.model.FriendValue;
import com.mongolia.website.model.ProfessionValue;
import com.mongolia.website.model.UserValue;

@Repository("userManagerDao")
public class UserManagerDaoImpl extends BaseDaoiBatis implements UserManagerDao {

	@Override
	public void createUser(UserValue userValue) throws Exception {
		// TODO Auto-generated method stub
		this.getSqlMapClientTemplate().insert("createUser", userValue);
	}

	@Override
	public void deleteUser(Map<String, Object> params) throws Exception {
		// TODO Auto-generated method stub
		this.getSqlMapClientTemplate().delete("deleteUser", params);
	}

	@Override
	public List<UserValue> getUser(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return this.getSqlMapClientTemplate().queryForList("getUser", params);
	}

	@Override
	public void updateUser(UserValue userValue) throws Exception {
		// TODO Auto-generated method stub
		this.getSqlMapClientTemplate().update("updateUser", userValue);
	}

	@Override
	public void addFriend(FriendValue friendValue) throws Exception {
		// TODO Auto-generated method stub
		this.getSqlMapClientTemplate().insert("addFriendValue", friendValue);

	}

	@Override
	public void reuseUser(String userid) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> updParams = new HashMap<String, Object>();
		updParams.put("userid", userid);
		updParams.put("stoped", 0);
		updParams.put("stopeddate", null);
		this.getSqlMapClientTemplate().update("stopUserAndReuse", updParams);
	}

	@Override
	public void stopUser(String userid) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> updParams = new HashMap<String, Object>();
		updParams.put("userid", userid);
		updParams.put("stoped", 1);
		updParams.put("stopeddate", new Date());
		this.getSqlMapClientTemplate().update("stopUserAndReuse", updParams);
	}

	@Override
	public void modifyUserPass(String userid, String username, String pass)
			throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> updparams = new HashMap<String, Object>();
		updparams.put("userid", userid);
		updparams.put("pass", pass);
		this.getSqlMapClientTemplate().update("modifyUserPass", updparams);
	}

	@Override
	public void delFriend(String userid, String friendid) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> updparams = new HashMap<String, Object>();
		updparams.put("userid", userid);
		updparams.put("friendid", friendid);
		this.getSqlMapClientTemplate().update("delfriend", updparams);
	}

	@Override
	public List<UserValue> paingQueryUser(Map<String, Object> params)
			throws Exception {
		// TODO Auto-generated method stub
		return this.getSqlMapClientTemplate().queryForList("paingQueryUser",
				params);
	}

	@Override
	public Integer paingUserCount(Map<String, Object> params) throws Exception {
		// TODO Auto-generated method stub
		return (Integer) this.getSqlMapClientTemplate().queryForObject(
				"paingUserCount", params);
	}

	@Override
	public List<DistrictValue> getDistrictValues(String districtcode,
			String parentcode, String top) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> queryparams = new HashMap<String, Object>();
		queryparams.put("districtcode", districtcode);
		queryparams.put("parentcode", parentcode);
		queryparams.put("top", top);
		return this.getSqlMapClientTemplate().queryForList("getDistrictValues",
				queryparams);
	}

	@Override
	public List<ProfessionValue> getProfessionValues(String professioncode,
			String professionname) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> queryparams = new HashMap<String, Object>();
		queryparams.put("professioncode", professioncode);
		queryparams.put("professionname", professionname);
		return this.getSqlMapClientTemplate().queryForList("getProfessions",
				queryparams);
	}

}
