package com.mongolia.website.controler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mongolia.website.manager.interfaces.ChannelManager;
import com.mongolia.website.manager.interfaces.WebSiteManager;
import com.mongolia.website.manager.interfaces.WebSiteVisitorManager;
import com.mongolia.website.model.Channel;
import com.mongolia.website.model.PagingIndex;
import com.mongolia.website.model.PaingModel;
import com.mongolia.website.model.PaingUser;
import com.mongolia.website.model.QueryDocForm;
import com.mongolia.website.model.QueryUserForm;
import com.mongolia.website.model.TopDocumentValue;
import com.mongolia.website.util.StaticConstants;

@Controller
public class WebSiteVisiterAction {
	@Autowired
	WebSiteVisitorManager webSiteVisitorManager;
	@Autowired
	private ChannelManager channelManager;
	@Autowired
	private WebSiteManager webSiteManager;

	/**
	 * 进入系统主页
	 * 
	 * @param request
	 * @param response
	 * @param map
	 * @return
	 */
	@RequestMapping("/index.do")
	public ModelAndView gotowebsiteIndex(HttpServletRequest request,
			HttpServletResponse response, ModelMap map) {
		Map<String, Object> indexPageContent = new HashMap<String, Object>();
		try {
			String path = request.getSession().getServletContext()
					.getRealPath("/");
			indexPageContent = this.webSiteVisitorManager.getIndexContent(path);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		map.put("indexPageContent", indexPageContent);
		Integer agentkind = 0;
		String user_agent_kind = request.getHeader("user-agent");
		if (user_agent_kind.indexOf("Chrome") > 0) {
			agentkind = 1;
		} else {
			agentkind = 0;
		}
		map.put("agentkind", agentkind);
		return new ModelAndView("website/index", map);
	}

	/**
	 * 分页查询页面数据
	 * 
	 * @param paingModel
	 * @param map
	 * @return
	 */
	@RequestMapping("/pagingquery.do")
	public ModelAndView pagingquerydoc(HttpServletRequest request,
			PaingModel paingModel, ModelMap map) {
		try {
			paingModel.setPagesize(StaticConstants.DEFAULT_PAGESIZE);
			paingModel.setStartrow((paingModel.getPageindex() - 1)
					* paingModel.getPagesize());
			paingModel.setEndrow(paingModel.getPageindex()
					* paingModel.getPagesize());
			PaingModel pamodel = webSiteVisitorManager
					.pagingquerydoc(paingModel);
			map.put("paingModel", pamodel);
			// /

			List<PagingIndex> indexs = new ArrayList<PagingIndex>();
			for (int i = 0; i < pamodel.getPagecount(); i++) {
				PagingIndex pagingIndex = new PagingIndex();// 就显示首页，末页和当前页，当前页前面，后面
				pagingIndex.setPageindex(i + 1);
				if (pamodel.getPageindex() == i + 1) {
					pagingIndex.setCurrent(1);
				}
				if (i == 0) {
					indexs.add(pagingIndex);
					pagingIndex.setDoc(0);
				} else if (i == pamodel.getPagecount() - 1) {
					indexs.add(pagingIndex);
					pagingIndex.setDoc(0);
				} else if (i + 1 == pamodel.getPageindex()) {
					indexs.add(pagingIndex);
					pagingIndex.setCurrent(1);
				} else if (i == pamodel.getPageindex()) {
					indexs.add(pagingIndex);
					if (i + 2 != pamodel.getPagecount() && i != 1) {
						pagingIndex.setDoc(1);
						pagingIndex.setFront(0);
					}
				} else if (i == pamodel.getPageindex() - 2) {
					indexs.add(pagingIndex);
					if (i != 1 && i + 1 != pamodel.getPagecount()
							&& i + 1 != pamodel.getPageindex()) {
						pagingIndex.setDoc(1);
						pagingIndex.setFront(1);
					}
				}

			}
			map.put("pagingindexs", indexs);
			// /
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("channelid", paingModel.getDocchannel());
			List<Channel> channels = channelManager.getChannelList(params);
			if (channels != null && !channels.isEmpty()) {
				Channel channel = channels.get(0);
				map.put("channel", channel);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}// doclist.html
		return new ModelAndView("website/doclist", map);
	}

	/**
	 * 图片新闻
	 */
	@RequestMapping("/getimagenews.do")
	public ModelAndView getimagenews(HttpServletRequest request, ModelMap map) {
		try {
			List<TopDocumentValue> topDdocumentValues = this.webSiteVisitorManager
					.getTopDocuments(StaticConstants.TOP_TYPE1, null, 7);
			map.put("imgnewws", topDdocumentValues);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ModelAndView("jsonView");
	}

	@RequestMapping("/getimgnewsimg.do")
	public ResponseEntity<byte[]> getnewimg(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String docid = request.getParameter("docid");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("docid", docid);
		List<TopDocumentValue> topDdocumentValues = new ArrayList<TopDocumentValue>();
		try {
			topDdocumentValues = this.webSiteVisitorManager.getTopDocuments(
					StaticConstants.TOP_TYPE1, null, 7);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		TopDocumentValue topDocumentValue = topDdocumentValues.get(0);

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.setContentType(MediaType.IMAGE_JPEG);
		responseHeaders.setContentLength(topDocumentValue.getDocimg().length);
		responseHeaders
				.setCacheControl("no-store, no-cache, must-revalidate, post-check=0, pre-check=0");
		responseHeaders.setPragma("no-cache");
		return new ResponseEntity<byte[]>(topDocumentValue.getDocimg(),
				responseHeaders, HttpStatus.OK);
	}

	@RequestMapping("/pagingquerydoc.do")
	public ModelAndView pagingQueryDoc(HttpServletRequest request,
			ModelMap map, PaingModel paingmodel) throws IOException {
		try {
			PaingModel paingresult = this.webSiteVisitorManager
					.pagingquerydoc(paingmodel);
			map.put("paingresult", paingresult);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ModelAndView("jsonView");
	}

	@RequestMapping("/searchblog.do")
	public ModelAndView searchblog(HttpServletRequest request, ModelMap map,
			QueryUserForm queryUserForm) throws IOException {
		try {
			if (queryUserForm.getPageindex() == null) {
				queryUserForm.setPageindex(1);
			}
			queryUserForm.setUsername(queryUserForm.getSearchtext());
			queryUserForm.setPagesize(6);
			PaingUser paingUser = this.webSiteManager.getUsers(queryUserForm);
			map.put("users", paingUser.getUsers());
			map.put("usercount", paingUser.getUsercount());
			map.put("queryform", queryUserForm);
			List<PagingIndex> indexs = new ArrayList<PagingIndex>();
			for (int i = 0; i < paingUser.getPageCount(); i++) {
				PagingIndex pagingIndex = new PagingIndex();// 就显示首页，末页和当前页，当前页前面，后面
				pagingIndex.setPageindex(i + 1);
				if (i + 1 == queryUserForm.getPageindex().intValue()) {
					pagingIndex.setCurrent(1);
				}
				if (i == 0) {
					indexs.add(pagingIndex);
					pagingIndex.setDoc(0);
				} else if (i == paingUser.getPageCount() - 1) {
					indexs.add(pagingIndex);
					pagingIndex.setDoc(0);
				} else if (i + 1 == queryUserForm.getPageindex().intValue()) {
					indexs.add(pagingIndex);

				} else if (i == queryUserForm.getPageindex()) {
					indexs.add(pagingIndex);
					if (i + 2 != paingUser.getPageCount() && i != 1) {
						pagingIndex.setDoc(1);
						pagingIndex.setFront(0);
					}
				} else if (i == queryUserForm.getPageindex() - 2) {
					indexs.add(pagingIndex);
					if (i != 1 && i + 1 != paingUser.getPageCount()
							&& i + 1 != queryUserForm.getPageindex()) {
						pagingIndex.setDoc(1);
						pagingIndex.setFront(1);
					}
				}

			}
			map.put("pagingindexs", indexs);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ModelAndView("website/bloglist");
	}

	@RequestMapping("/searchdoc.do")
	public ModelAndView searchdoc(HttpServletRequest request, ModelMap map,
			QueryDocForm queryDocForm) throws IOException {
		// 从后台把所有需要编辑和审核的文章列表
		try {
			Map<String, Object> queryDocParams = new HashMap<String, Object>();
			queryDocForm.setDoctitle(queryDocForm.getSearchtext());
			if (queryDocForm.getStatus() != null
					&& queryDocForm.getStatus().intValue() == 0) {
				queryDocParams.put("status", null);
			} else {
				queryDocParams.put("status", queryDocForm.getStatus());
			}
			if (queryDocForm.getChannel() != null
					&& queryDocForm.getChannel().equalsIgnoreCase("#")) {
				queryDocParams.put("channelid", null);
			} else {
				queryDocParams.put("channelid", queryDocForm.getChannel());
			}
			if (queryDocForm.getAuthorname() != null
					&& queryDocForm.getAuthorname().equalsIgnoreCase("")) {
				queryDocParams.put("authorname", null);
			} else {
				queryDocParams.put("authorname", queryDocForm.getAuthorname());
			}
			if (queryDocForm.getStrcrtime() != null
					&& queryDocForm.getStrcrtime().equalsIgnoreCase("")) {
				queryDocParams.put("strcrtime", null);
			} else {
				queryDocParams.put("strcrtime", queryDocForm.getStrcrtime());
			}
			if (queryDocForm.getEndcrtime() != null
					&& queryDocForm.getEndcrtime().equalsIgnoreCase("")) {
				queryDocParams.put("endcrtime", null);
			} else {
				queryDocParams.put("endcrtime", queryDocForm.getEndcrtime());
			}
			if (queryDocForm.getDoctitle() != null
					&& queryDocForm.getDoctitle().equalsIgnoreCase("")) {
				queryDocParams.put("doctitle", null);
			} else {
				queryDocParams.put("doctitle", queryDocForm.getDoctitle());
			}
			if (queryDocForm.getTop() != null
					&& queryDocForm.getTop().intValue() != 0) {
				queryDocParams.put("top", queryDocForm.getTop());
				queryDocParams.put("currentday", new Date());
			}
			if (queryDocForm.getPageindex() == null
					|| queryDocForm.getPageindex() == 0) {
				queryDocForm.setPageindex(1);
			}
			Integer startindex = ((queryDocForm.getPageindex() - 1) * 30);
			queryDocParams.put("displaydoccount", 30);
			queryDocParams.put("startindex", startindex);
			Map<String, Object> result = this.webSiteManager
					.getDocuments(queryDocParams);
			map.put("docs", result.get("docs"));
			map.put("doccount", result.get("doccount"));
			Integer pageCount = (Integer) result.get("pageCount");
			List<PagingIndex> indexs = new ArrayList<PagingIndex>();
			for (int i = 0; i < pageCount; i++) {
				PagingIndex pagingIndex = new PagingIndex();// 就显示首页，末页和当前页，当前页前面，后面
				pagingIndex.setPageindex(i + 1);
				if (i + 1 == queryDocForm.getPageindex().intValue()) {
					pagingIndex.setCurrent(1);
				}
				if (i == 0) {
					indexs.add(pagingIndex);
					pagingIndex.setDoc(0);
				} else if (i == pageCount - 1) {
					indexs.add(pagingIndex);
					pagingIndex.setDoc(0);
				} else if (i + 1 == queryDocForm.getPageindex()) {
					indexs.add(pagingIndex);
					pagingIndex.setCurrent(1);
				} else if (i == queryDocForm.getPageindex()) {
					indexs.add(pagingIndex);
					if (i + 2 != pageCount && i != 1) {
						pagingIndex.setDoc(1);
						pagingIndex.setFront(0);
					}
				} else if (i == queryDocForm.getPageindex() - 2) {
					indexs.add(pagingIndex);
					if (i != 1 && i + 1 != pageCount
							&& i + 1 != queryDocForm.getPageindex()) {
						pagingIndex.setDoc(1);
						pagingIndex.setFront(1);
					}
				}

			}
			map.put("pagingindexs", indexs);
			map.put("queryDocForm", queryDocForm);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ModelAndView("website/artlist");
	}

}
