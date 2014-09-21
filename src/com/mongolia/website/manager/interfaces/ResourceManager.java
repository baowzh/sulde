package com.mongolia.website.manager.interfaces;

import java.util.List;

import com.mongolia.website.model.ResourceValue;
import com.mongolia.website.model.RoleValue;

/**
 * 资源模块接口
 * 
 * @author shadow
 * 
 */
public interface ResourceManager  {

	/**
	 * 查询分配资源
	 * 
	 * @param roleValue
	 * @param mold
	 * @return List<Resource>
	 */
	public List<ResourceValue> findAllotResource(RoleValue roleValue, int mold);

	/**
	 * 保存角色资源关系
	 * @param roleValue
	 * @param resourceValue
	 * @return int
	 */
	public int saveRelation(RoleValue roleValue, ResourceValue resourceValue);

	/**
	 * 删除角色资源关系
	 * @param roleValue
	 * @param resourceValue
	 * @return int
	 */
	public int removeRelation(RoleValue roleValue, ResourceValue resourceValue);
	
	public List<ResourceValue> findForAll();
}
