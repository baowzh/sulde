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
	 * 
	 * @param params
	 * @return
	 */
	public List<Channel> getChannelList(Map<String, Object> params);
    /**
     * 
     * @param channel
     * @throws Exception
     */
	public void doAddChannel(Channel channel) throws Exception;
    /**
     * 
     * @param params
     * @throws Exception
     */
	public void doDeleteChannel(Map<String, String> params) throws Exception;

	/**
	 * 
	 * @param channel
	 * @throws Exception
	 */
	public void doUpdateChannel(Channel channel) throws Exception;

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<Channel> getRaceChannelList() throws Exception;
}
