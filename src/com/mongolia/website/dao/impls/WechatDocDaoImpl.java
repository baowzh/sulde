package com.mongolia.website.dao.impls;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mongolia.website.dao.interfaces.WechatDocDao;
import com.mongolia.website.model.WechatDocValue;
import com.mongolia.website.model.WechatReceiveMessValue;

@Repository("wechatDocDaoImpl")
public class WechatDocDaoImpl extends BaseDaoiBatis implements WechatDocDao {

	@Override
	public List<WechatDocValue> getWechatDocWithAutoResId(String resId)
			throws Exception {
		// TODO Auto-generated method stub
		return this.getSqlMapClientTemplate().queryForList(
				"getWechatDocWithAutoResId", resId);
	}

	@Override
	public void addWechatDoc(WechatDocValue wechatDocValue) throws Exception {
		// TODO Auto-generated method stub
		this.getSqlMapClientTemplate().insert("addWechatDocValue",
				wechatDocValue);

	}

	@Override
	public void delWechatDoc(WechatDocValue wechatDocValue) throws Exception {
		// TODO Auto-generated method stub
		this.getSqlMapClientTemplate().delete("delWechatDoc", wechatDocValue);

	}

	@Override
	public void delAllAutoResDoc(String resId) throws Exception {
		// TODO Auto-generated method stub
		this.getSqlMapClientTemplate().delete("delAllAutoResDoc", resId);

	}

	@Override
	public void addWechatReceiveMessValue(
			WechatReceiveMessValue receiveMessValue) throws Exception {
		// TODO Auto-generated method stub
		this.getSqlMapClientTemplate().insert("addWechatReceiveMessValue", receiveMessValue);
		
	}
	

}
