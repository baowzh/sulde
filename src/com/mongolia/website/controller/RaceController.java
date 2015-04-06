package com.mongolia.website.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mongolia.website.manager.interfaces.RaceManager;
import com.mongolia.website.model.DocumentValue;
import com.mongolia.website.model.ImgValue;
import com.mongolia.website.model.PaingModel;
import com.mongolia.website.model.RaceDocumentValue;
import com.mongolia.website.model.RaceModelValue;
import com.mongolia.website.model.RaceScoreLogValue;
import com.mongolia.website.model.RaceUser;
import com.mongolia.website.model.UserValue;
import com.mongolia.website.util.PageUtil;

@Controller
public class RaceController {
	@Autowired
	private RaceManager raceManager;

	/**
	 * 
	 * @param request
	 * @param map
	 * @return
	 */
	@RequestMapping("/getRaceModels.do")
	public ModelAndView getRaceModels(HttpServletRequest request, ModelMap map) {
		try {

			List<RaceModelValue> raceModelValues = this.raceManager
					.getRaceModels(request.getParameter("raceid"), 1);
			map.put("raceModelValues", raceModelValues);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ModelAndView("jsonView", map);
	}

	/**
	 * 
	 * @param request
	 * @param raceDocumentValue
	 * @param map
	 * @return
	 */
	@RequestMapping("/addRaceDocument.do")
	public ModelAndView addRaceDocument(HttpServletRequest request,
			RaceDocumentValue raceDocumentValue, ModelMap map) {
		try {
			String sysvalidcode = (String) request.getSession().getAttribute(
					"validateCode");
			if (!sysvalidcode.equalsIgnoreCase(raceDocumentValue
					.getRaicevalidcode())) {
				throw new Exception("4");
			}
			UserValue sessionUser = (UserValue) request.getSession()
					.getAttribute("user");
			raceDocumentValue.setJoinuserid(sessionUser.getUserid());
			this.raceManager.addRaceDocument(raceDocumentValue);
			map.put("mess", 0);
		} catch (Exception ex) {
			ex.printStackTrace();
			map.put("mess", ex.getMessage());
		}
		return new ModelAndView("jsonView", map);
	}

	@RequestMapping("/delRaceDocument.do")
	public ModelAndView delRaceDocument(HttpServletRequest request, ModelMap map) {
		try {
			UserValue sessionUser = (UserValue) request.getSession()
					.getAttribute("user");
			this.raceManager.delRaceDocument(request.getParameter("raceid"),
					request.getParameter("docid"), sessionUser.getUserid());
			map.put("mess", 0);
		} catch (Exception ex) {
			ex.printStackTrace();
			map.put("mess", ex.getMessage());
		}
		return new ModelAndView("jsonView", map);
	}

	/**
	 * 
	 * @param request
	 * @param raceScoreLogValue
	 * @param map
	 * @return
	 */
	@RequestMapping("/addRaceScoreLogValue.do")
	public ModelAndView addRaceScoreLogValue(HttpServletRequest request,
			RaceScoreLogValue raceScoreLogValue, ModelMap map) {
		try {
			UserValue sessionUser = (UserValue) request.getSession()
					.getAttribute("user");
			raceScoreLogValue.setScoreuserid(sessionUser.getUserid());
			raceScoreLogValue.setScoredate(new Date());
			this.raceManager.addRaceScoreLogValue(raceScoreLogValue);
			map.put("mess", 0);
		} catch (Exception ex) {
			ex.printStackTrace();
			map.put("mess", ex.getMessage());
		}
		return new ModelAndView("jsonView", map);
	}

	/**
	 * 
	 * @param request
	 * @param map
	 * @return
	 */
	@RequestMapping("/raceindex.do")
	public ModelAndView raceindex(HttpServletRequest request, ModelMap map) {
		// 获取所有参赛人员和相关的作品
		try {
			List<RaceUser> raceUsers = this.raceManager.getRaceIndexContent(
					request.getParameter("raceid"), 1);
			Map<String, Object> indexcontent = this.raceManager
					.getRaceIndexCon(request.getParameter("raceid"),
							"raceindex", 1);
			List<RaceModelValue> raceModelValues = this.raceManager
					.getRaceModels(null, 1);
			Object userValueobj = request.getSession().getAttribute("user");
			if (userValueobj != null) {
				map.put("userValue", (UserValue) userValueobj);
			}
			map.put("raceUsers", raceUsers);
			map.put("indexPageContent", indexcontent);
			map.put("racemodel", raceModelValues.get(0));
			List<ImgValue> imgs = this.raceManager.getRaceImgList(
					raceModelValues.get(0).getRaceid(), 10);
			map.put("imgs", imgs);
			List<DocumentValue> videos = this.raceManager.getvides(null);
			map.put("videos", videos);
			JSONObject json = new JSONObject();
			json.put("raceModel", raceModelValues.get(0));
			map.put("raceModelJson", json.toString());
			map.put("jointype", 1);
			List<RaceUser> raceUsers1 = this.raceManager.getRaceIndexContent(
					request.getParameter("raceid"), 2);// 儿童组的特殊处理
			map.put("raceUsers1", raceUsers1);
			// 跟专题相关的页面栏目
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ModelAndView("baitelhei/raceindex", map);
	}

	/**
	 * 
	 * @param request
	 * @param map
	 * @return
	 */
	@RequestMapping("/childraceindex.do")
	public ModelAndView childraceindex(HttpServletRequest request, ModelMap map) {
		// 获取所有参赛人员和相关的作品
		try {
			List<RaceUser> raceUsers = this.raceManager.getRaceIndexContent(
					request.getParameter("raceid"), 2);
			Map<String, Object> indexcontent = this.raceManager
					.getRaceIndexCon(request.getParameter("raceid"),
							"raceindex", 2);
			List<RaceModelValue> raceModelValues = this.raceManager
					.getRaceModels(null, 1);
			Object userValueobj = request.getSession().getAttribute("user");
			if (userValueobj != null) {
				map.put("userValue", (UserValue) userValueobj);
			}
			map.put("raceUsers", raceUsers);
			map.put("indexPageContent", indexcontent);
			map.put("racemodel", raceModelValues.get(0));
			List<ImgValue> imgs = this.raceManager.getRaceImgList(
					raceModelValues.get(0).getRaceid(), 10);
			map.put("imgs", imgs);
			List<DocumentValue> videos = this.raceManager.getvides(null);
			map.put("videos", videos);
			JSONObject json = new JSONObject();
			json.put("raceModel", raceModelValues.get(0));
			map.put("raceModelJson", json.toString());
			map.put("jointype", 2);
			// 跟专题相关的页面栏目
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ModelAndView("baitelhei/raceindex", map);
	}

	@RequestMapping("/switchDocToNextRound.do")
	public ModelAndView switchDocToNextRound(HttpServletRequest request,
			RaceDocumentValue raceDocumentValue, ModelMap map) {
		// String sysvalidcode = (String) request.getSession().getAttribute(
		// "validateCode");
		try {
			// if (!sysvalidcode.equalsIgnoreCase(raceDocumentValue
			// .getRaicevalidcode())) {
			// throw new Exception("1");// 校验码不对
			// }
			UserValue sessionUser = (UserValue) request.getSession()
					.getAttribute("user");
			if (sessionUser.getManagerflag() == null
					|| sessionUser.getManagerflag().intValue() == 0) {
				throw new Exception("2");// 非管理人员不能操作
			}
			this.raceManager.switchUserToNextRound(
					raceDocumentValue.getRaceid(),
					raceDocumentValue.getJoinuserid(),
					raceDocumentValue.getJointype());
			map.put("mess", 0);
		} catch (Exception ex) {
			map.put("mess", ex.getMessage());
		}
		return new ModelAndView("jsonView", map);
	}

	@RequestMapping("/raceScoreDetail.do")
	public ModelAndView raceScoreDetail(HttpServletRequest request, ModelMap map) {
		String raceid = request.getParameter("raceid");
		String docid = request.getParameter("docid");
		String index = request.getParameter("index");
		String round = request.getParameter("round");
		try {
			if (index == null || index.equalsIgnoreCase("")) {
				index = "1";
			}
			round = request.getParameter("round");
			PaingModel<RaceScoreLogValue> retPaingModel = raceManager
					.pagingqueryscorelog(raceid, docid, index,
							Integer.parseInt(round));
			map.put("paingModel", retPaingModel);
			String pagingstr = PageUtil.getPagingLinkForRaceScore(
					retPaingModel, 1);
			map.put("pagingstr", pagingstr);
			//
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ModelAndView("baitelhei/racedetail", map);
	}

	@RequestMapping("/racestatus.do")
	public ModelAndView racestatus(HttpServletRequest request, ModelMap map) {
		String raceid = request.getParameter("raceid");
		String round = request.getParameter("round");
		try {
			List<RaceUser> raceusers = this.raceManager.getRaceStatus(raceid,
					1, Integer.parseInt(round));
			List<RaceUser> raceUsers1 = this.raceManager.getRaceStatus(raceid,
					2, Integer.parseInt(round));
			map.put("raceUsers", raceusers);
			map.put("raceUsers1", raceUsers1);
			Map<String, Object> indexcontent = this.raceManager
					.getRaceIndexCon(request.getParameter("raceid"),
							"raceindex", 1);
			List<RaceModelValue> raceModelValues = this.raceManager
					.getRaceModels(null, 1);
			Object userValueobj = request.getSession().getAttribute("user");
			if (userValueobj != null) {
				map.put("userValue", (UserValue) userValueobj);
			}
			map.put("indexPageContent", indexcontent);
			map.put("racemodel", raceModelValues.get(0));
			List<ImgValue> imgs = this.raceManager.getRaceImgList(
					raceModelValues.get(0).getRaceid(), 10);
			map.put("imgs", imgs);
			List<DocumentValue> videos = this.raceManager.getvides(null);
			map.put("videos", videos);
			JSONObject json = new JSONObject();
			json.put("raceModel", raceModelValues.get(0));
			map.put("raceModelJson", json.toString());
			map.put("jointype", 1);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ModelAndView("baitelhei/raceindex", map);
	}

	@RequestMapping("/refreshRaceList.do")
	public ModelAndView refreshRaceList(HttpServletRequest request,
			HttpServletResponse response, ModelMap map) throws Exception {
		List<RaceUser> raceUsers = this.raceManager.getRaceIndexContent(
				request.getParameter("raceid"), 1);
		List<RaceUser> raceUsers2 = this.raceManager.getRaceIndexContent(
				request.getParameter("raceid"), 2);
		map.put("raceUsers", raceUsers);
		map.put("raceUsers2", raceUsers2);
		return new ModelAndView("jsonView", map);
	}

}
