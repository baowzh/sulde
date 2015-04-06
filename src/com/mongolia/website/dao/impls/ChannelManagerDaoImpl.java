package com.mongolia.website.dao.impls;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.mongolia.website.dao.interfaces.ChannelManagerDao;
import com.mongolia.website.model.Channel;

@Repository("channelDao")
public class ChannelManagerDaoImpl extends BaseDaoiBatis implements
		ChannelManagerDao {

	@Override
	public List<Channel> getChannel(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return this.getSqlMapClientTemplate()
				.queryForList("getChannel", params);
	}

	@Override
	public void addChannel(Channel channel) throws Exception {
		// TODO Auto-generated method stub
		this.getSqlMapClientTemplate().insert("addChannel", channel);
	}

	@Override
	public void deleteChannel(Map<String, String> params) throws Exception {
		// TODO Auto-generated method stub
		this.getSqlMapClientTemplate().delete("deleteChannel", params);
	}

	@Override
	public void updateChannel(Channel channel) throws Exception {
		// TODO Auto-generated method stub
		this.getSqlMapClientTemplate().update("updateChannel", channel);
	}

	@Override
	public List<Channel> getRaceChannelList(Map<String, Object> params)
			throws Exception {
		// TODO Auto-generated method stub
		return this.getSqlMapClientTemplate().queryForList("getRaceChannes",
				params);
	}

}
