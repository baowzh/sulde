package com.mongolia.website.manager.impls;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mongolia.website.dao.interfaces.ChannelManagerDao;
import com.mongolia.website.manager.interfaces.ChannelManager;
import com.mongolia.website.model.Channel;
import com.mongolia.website.util.UUIDMaker;

@Service("channelManager")
@Transactional(rollbackFor = Exception.class)
public class ChannelManagerImpl implements ChannelManager {

	@Autowired
	private ChannelManagerDao channelDao;

	@Override
	public void doUpdateChannel(Channel channel) throws Exception {
		// TODO Auto-generated method stub
		channelDao.updateChannel(channel);
	}

	@Override
	public List<Channel> getChannelList(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return channelDao.getChannel(params);
	}

	@Override
	public void doAddChannel(Channel channel) throws Exception {
		// TODO Auto-generated method stub
		channel.setChannelid(UUIDMaker.getUUID());
		channel.setParentid("0");
		channelDao.addChannel(channel);
	}

	@Override
	public void doDeleteChannel(Map<String, String> params) throws Exception {
		// TODO Auto-generated method stub
		channelDao.deleteChannel(params);
	}

}
