package com.mongolia.website.dao.impls;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.support.DaoSupport;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.ClassUtils;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.mongolia.website.dao.interfaces.IBatisBaseDao;
import com.mongolia.website.util.UUIDMaker;
import com.mongolia.website.util.iBatisDaoUtils;

@Repository("baseDao")
public class BaseDaoiBatis extends DaoSupport  implements IBatisBaseDao {

	protected final Log log = LogFactory.getLog(getClass());
	private SqlMapClientTemplate sqlMapClientTemplate = new SqlMapClientTemplate();

	@SuppressWarnings("unchecked")
	public List getAll(Class clazz) {
		return getSqlMapClientTemplate().queryForList(
				iBatisDaoUtils.getSelectQuery(ClassUtils.getShortName(clazz)),
				null);
	}

	@SuppressWarnings("unchecked")
	public Object get(Class clazz, Serializable primaryKey) {
		return getSqlMapClientTemplate().queryForObject(
				iBatisDaoUtils.getFindQuery(ClassUtils.getShortName(clazz)),
				primaryKey);
	}

	public Object save(Object object) {
		String className = ClassUtils.getShortName(object.getClass());
		Object primaryKey = iBatisDaoUtils.getPrimaryKeyValue(object);
		if (primaryKey == null
				|| StringUtils.isBlank(String.valueOf(primaryKey))) {
			// check for new record
			iBatisDaoUtils.setPrimaryKey(object, String.class, UUIDMaker
					.getUUID());
			return getSqlMapClientTemplate().insert(
					iBatisDaoUtils.getInsertQuery(className), object);

		} else {
			return getSqlMapClientTemplate().update(
					iBatisDaoUtils.getUpdateQuery(className), object);

		}
	}

	public void insert(Object object) {
		String className = ClassUtils.getShortName(object.getClass());
		getSqlMapClientTemplate().insert(
				iBatisDaoUtils.getInsertQuery(className), object);

	}

	@SuppressWarnings("unchecked")
	public void remove(Class clazz, Serializable primaryKey) {
		getSqlMapClientTemplate().delete(
				iBatisDaoUtils.getDeleteQuery(ClassUtils.getShortName(clazz)),
				primaryKey);
	}

	@SuppressWarnings("unchecked")
	public void remove(Class clazz, Map<String, Object> paramMap) {
		getSqlMapClientTemplate().delete(
				iBatisDaoUtils.getDeleteQuery(ClassUtils.getShortName(clazz)),
				paramMap);
	}

	public boolean exists(Object object) {
		boolean app = false;
		String className = ClassUtils.getShortName(object.getClass());
		Integer record = (Integer) getSqlMapClientTemplate().queryForObject(
				iBatisDaoUtils.getValueQuery(className), object);
		if (record > 0) {
			app = true;
		}
		return app;
	}

	@Override
	public void update(Object object) {
		String className = ClassUtils.getShortName(object.getClass());
		this.getSqlMapClientTemplate().update(
				iBatisDaoUtils.getUpdateQuery(className), object);
	}

	//
	private boolean externalTemplate = false;

	@Resource(name = "dataSource")
	public final void setDataSource(DataSource dataSource) {
		this.sqlMapClientTemplate.setDataSource(dataSource);
	}

	@Resource(name = "sqlMapClient")
	public final void setSqlMapClient(SqlMapClient sqlMapClient) {
		this.sqlMapClientTemplate.setSqlMapClient(sqlMapClient);
	}

	public final void setSqlMapClientTemplate(
			SqlMapClientTemplate sqlMapClientTemplate) {
		if (sqlMapClientTemplate == null) {
			throw new IllegalArgumentException(
					"Cannot set sqlMapClientTemplate to null");
		}
		this.sqlMapClientTemplate = sqlMapClientTemplate;
		this.externalTemplate = true;
	}

	public final DataSource getDataSource() {
		return (this.sqlMapClientTemplate != null ? this.sqlMapClientTemplate
				.getDataSource() : null);
	}

	public final SqlMapClient getSqlMapClient() {
		return this.sqlMapClientTemplate.getSqlMapClient();
	}

	public final SqlMapClientTemplate getSqlMapClientTemplate() {
		return sqlMapClientTemplate;
	}

	protected final void checkDaoConfig() {
		if (!this.externalTemplate) {
			this.sqlMapClientTemplate.afterPropertiesSet();
		}
	}

}
