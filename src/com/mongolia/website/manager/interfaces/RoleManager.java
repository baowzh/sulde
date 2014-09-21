package com.mongolia.website.manager.interfaces;

import java.io.Serializable;
import java.util.List;

import com.mongolia.website.model.RoleValue;
import com.mongolia.website.model.UserValue;

/**
 * 权限模块接口
 * 
 * @author shadow
 * 
 */
public interface RoleManager  {

	public List<RoleValue> findByResourceId(Serializable id);

	public List<RoleValue> findByUserId(Serializable id);

	/**
	 * 查询分配资源
	 * 
	 * @param userValue
	 * @param mold
	 * @return List<Role>
	 */
	public List<RoleValue> findAllotRole(UserValue userValue, int mold);

	/**
	 * 保存用户角色关系
	 * 
	 * @param userValue
	 * @param roleValue
	 * @return int
	 */
	public int saveRelation(UserValue userValue, RoleValue roleValue);

	/**
	 * 删除用户资源关系
	 * 
	 * @param userValue
	 * @param roleValue
	 * @return int
	 */
	public int removeRelation(UserValue userValue, RoleValue roleValue);
}
