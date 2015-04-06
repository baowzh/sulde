package com.mongolia.website.manager.impls;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mongolia.website.dao.interfaces.IBatisBaseDao;
import com.mongolia.website.manager.interfaces.BaseManager;

/**
 * @author zeting
 */
@Service("baseManager")
public class BaseManagerImpl implements BaseManager {

	protected final Log log = LogFactory.getLog(getClass());
	@Autowired
	protected IBatisBaseDao baseDao;

	@SuppressWarnings("unchecked")
	public Object get(Class clazz, Serializable id) {
		return baseDao.get(clazz, id);
	}

	@SuppressWarnings("unchecked")
	public List getAll(Class clazz) {
		return baseDao.getAll(clazz);
	}

	public Object save(Object o) {
		return baseDao.save(o);
	}

	public void insert(Object obj) {
		baseDao.insert(obj);
	}

	@Override
	public void remove(Class clazz, Serializable primaryKey) {
		baseDao.remove(clazz, primaryKey);
	}

	public void update(Object object) {
		this.baseDao.update(object);
	}
}
