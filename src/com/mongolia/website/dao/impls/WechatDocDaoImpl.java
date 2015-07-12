package com.mongolia.website.dao.impls;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		this.getSqlMapClientTemplate().insert("addWechatReceiveMessValue",
				receiveMessValue);

	}

	@Override
	public List<String> getRecentReqUserId() throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<String, Object>();
		Calendar calendar = java.util.Calendar.getInstance();
		calendar.add(Calendar.HOUR_OF_DAY, -23);
		Date begindate = calendar.getTime();
		params.put("begindate", begindate);
		return this.getSqlMapClientTemplate().queryForList(
				"getRecentReqUserId", params);
	}

}
