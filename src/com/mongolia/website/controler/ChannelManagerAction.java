package com.mongolia.website.controler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mongolia.website.manager.interfaces.ChannelManager;
import com.mongolia.website.model.Channel;

@Controller
public class ChannelManagerAction {
	@Autowired
	private ChannelManager channelManager;

	@RequestMapping("/channel.do")
	public ModelAndView goToChannelManager(HttpServletRequest request,
			HttpServletResponse response, ModelMap map) {

		try {
			List<Channel> chnlMgr = this.channelManager
					.getChannelList(new HashMap<String, Object>());
			map.put("channelList", chnlMgr);

			return new ModelAndView("sitemanager/channelmanager", map);
		} catch (Exception ex) {
			return new ModelAndView("error", map);
		}

	}

	@RequestMapping("/updateChannel.do")
	public ModelAndView updateChannel(HttpServletRequest request,
			HttpServletResponse response, ModelMap map, Channel channel) {

		try {
			this.channelManager.doUpdateChannel(channel);
		} catch (Exception ex) {
			map.put("errorMess", ex.getLocalizedMessage());
			return new ModelAndView("sitemanager/error", map);
		}
		map.put("sucess", "1");

		return goToChannelManager(request, response, map);
	}

	@RequestMapping("/getChannel.do")
	public ModelAndView getChannel(HttpServletRequest request,
			HttpServletResponse response, ModelMap map) {
		try {
			
			String channelid =  request.getParameter("channelid");
			String channelname = request.getParameter("chnlname");
			Map<String, Object> paramas = new HashMap<String, Object>();
			paramas.put("channelid", channelid);
			paramas.put("chnlname", channelname);
			List<Channel> channels = channelManager.getChannelList(paramas);

			// map.put("success", "true");
			map.put("treeNodes", channels);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ModelAndView("jsonView", map);
	}

	@RequestMapping("/addChannel.do")
	public ModelAndView addChannel(HttpServletRequest request,
			HttpServletResponse response, ModelMap map, Channel channel) {

		try {
			channelManager.doAddChannel(channel);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		map.put("addsucess", "1");
		return goToChannelManager(request, response, map);

	}

	@RequestMapping("/toChnlAdd.do")
	public ModelAndView gotoTempAdd(HttpServletRequest request,
			HttpServletResponse response, ModelMap map) {
		return new ModelAndView("sitemanager/channeladd", map);
	}

	@RequestMapping("/deleteChannel.do")
	public ModelAndView deleteChannal(HttpServletRequest request,
			HttpServletResponse response, ModelMap map) {
		try {
			String deleteids = (String) request.getParameter("channelids");
			String ids[] = deleteids.split(",");
			for (int i = 0; i < ids.length; i++) {
				Map<String, String> deletParams = new HashMap<String, String>();
				deletParams.put("channelid", ids[i]);

				this.channelManager.doDeleteChannel(deletParams);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			map.put("mess", ex.getLocalizedMessage());
		}
		return this.goToChannelManager(request, response, map);
	}
}
