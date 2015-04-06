package com.mongolia.website.dao.interfaces;
import java.io.Serializable;
import java.util.List;

import com.mongolia.website.model.RoleValue;

/**
 * 角色接口
 * 
 * @author shadow
 * 
 */
public interface RoleDao  {

	public List<RoleValue> queryByResourceId(Serializable id);

	public List<RoleValue> queryByUserId(Serializable id);

	/**
	 * 查询分配角色
	 * 
	 * @param id
	 * @param mold
	 *            1已分配,2未分配
	 * @return List<Role>
	 */
	public List<RoleValue> queryAllotRole(Serializable id, int mold);

	/**
	 * 保存用户角色的关系
	 * 
	 * @param userid
	 * @param roleid
	 * @return int
	 */
	public int insertRelation(Serializable userid, Serializable roleid);

	/**
	 * 删除用户角色的关系
	 * 
	 * @param userid
	 * @param roleid
	 * @return int
	 */
	public int deleteRelation(Serializable userid, Serializable roleid);
	
}