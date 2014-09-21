package com.mongolia.website.dao.interfaces;

import java.util.List;
import java.util.Map;

import com.mongolia.website.model.Channel;

public interface ChannelManagerDao extends BaseDao {
	/**
	 * 
	 * @param params
	 * @return
	 */
	public List<Channel> getChannel(Map<String, Object> params);

	public void addChannel(Channel channel) throws Exception;

	public void deleteChannel(Map<String, String> params) throws Exception;

	public void updateChannel(Channel channel) throws Exception;

}