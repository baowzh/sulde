/**
 * 
 */
package com.mongolia.website.manager.interfaces;

import java.util.List;
import java.util.Map;

import com.mongolia.website.model.Channel;

/**
 * @author e
 * 
 */
public interface ChannelManager {

	/**
	 * 
	 * ��ȡ��Ŀ���
	 * 
	 * @param params
	 * @return
	 */
	public List<Channel> getChannelList(Map<String, Object> params);

	public void doAddChannel(Channel channel) throws Exception;

	public void doDeleteChannel(Map<String, String> params) throws Exception;

	public void doUpdateChannel(Channel channel) throws Exception;
}
