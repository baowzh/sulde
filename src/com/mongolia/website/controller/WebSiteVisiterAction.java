package com.mongolia.website.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.tree.DefaultCDATA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mongolia.website.manager.impls.SysConfig;
import com.mongolia.website.manager.interfaces.ChannelManager;
import com.mongolia.website.manager.interfaces.UserManager;
import com.mongolia.website.manager.interfaces.WebSiteManager;
import com.mongolia.website.manager.interfaces.WebSiteVisitorManager;
import com.mongolia.website.model.Channel;
import com.mongolia.website.model.DistrictValue;
import com.mongolia.website.model.DocumentValue;
import com.mongolia.website.model.PagingIndex;
import com.mongolia.website.model.PaingModel;
import com.mongolia.website.model.QueryDocForm;
import com.mongolia.website.model.QueryUserForm;
import com.mongolia.website.model.RssItem;
import com.mongolia.website.model.TopDocumentValue;
import com.mongolia.website.model.UserValue;
import com.mongolia.website.util.StaticConstants;

@Controller
public class WebSiteVisiterAction {
	@Autowired
	WebSiteVisitorManager webSiteVisitorManager;
	@Autowired
	private ChannelManager channelManager;
	@Autowired
	private WebSiteManager webSiteManager;
	@Autowired
	UserManager userManager;
	@Resource(name = "configInfo")
	private SysConfig sysConfig;

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
			PaingModel<DocumentValue> paingModel, ModelMap map) {
		try {
			paingModel.setPagesize(StaticConstants.DEFAULT_PAGESIZE);
			paingModel.setStartrow((paingModel.getPageindex() - 1)
					* paingModel.getPagesize());
			paingModel.setEndrow(paingModel.getPageindex()
					* paingModel.getPagesize());
			PaingModel<DocumentValue> pamodel = webSiteVisitorManager
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
			ModelMap map, PaingModel<DocumentValue> paingmodel)
			throws IOException {
		try {
			PaingModel<DocumentValue> paingresult = this.webSiteVisitorManager
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
			PaingModel<UserValue> paingUser = this.webSiteManager
					.getUsers(queryUserForm);
			map.put("users", paingUser.getModelList());
			map.put("usercount", paingUser.getRowcount());
			map.put("queryform", queryUserForm);
			//
			List<DistrictValue> districts = this.userManager.getDistrictValues(
					null, null, "top");
			JSONObject json = new JSONObject();
			map.put("districts", districts);
			List<DistrictValue> districts1 = this.userManager
					.getDistrictValues(null, null, null);
			json.put("districts", districts1);
			map.put("districtsdata", json.toString());
			//
			List<PagingIndex> indexs = new ArrayList<PagingIndex>();
			for (int i = 0; i < paingUser.getPagecount(); i++) {
				PagingIndex pagingIndex = new PagingIndex();// 就显示首页，末页和当前页，当前页前面，后面
				pagingIndex.setPageindex(i + 1);
				if (i + 1 == queryUserForm.getPageindex().intValue()) {
					pagingIndex.setCurrent(1);
				}
				if (i == 0) {
					indexs.add(pagingIndex);
					pagingIndex.setDoc(0);
				} else if (i == paingUser.getPagecount() - 1) {
					indexs.add(pagingIndex);
					pagingIndex.setDoc(0);
				} else if (i + 1 == queryUserForm.getPageindex().intValue()) {
					indexs.add(pagingIndex);

				} else if (i == queryUserForm.getPageindex()) {
					indexs.add(pagingIndex);
					if (i + 2 != paingUser.getPagecount() && i != 1) {
						pagingIndex.setDoc(1);
						pagingIndex.setFront(0);
					}
				} else if (i == queryUserForm.getPageindex() - 2) {
					indexs.add(pagingIndex);
					if (i != 1 && i + 1 != paingUser.getPagecount()
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
//			if (queryDocForm.getStatus() != null
//					&& queryDocForm.getStatus().intValue() == 0) {
//				queryDocParams.put("status", null);
//			} else {
				queryDocParams.put("status", StaticConstants.DOCSTATUS2);
			//}
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
			queryDocParams.put("doctype", 1);
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

	@RequestMapping("/siterss.do")
	ResponseEntity<String> siterss(ModelMap model) throws IOException {
		//
		String returnstr = "";
		try {
			List<TopDocumentValue> tops = this.webSiteVisitorManager
					.getTopDocuments(StaticConstants.TOP_TYPE1, null, 7);
			List<RssItem> rssItems = new ArrayList<RssItem>();
			for (TopDocumentValue topDocumentValue : tops) {
				RssItem n = new RssItem();
				n.setTitle(topDocumentValue.getTitle());
				n.setDescription(topDocumentValue.getTitle());
				n.setLink(sysConfig.getSiteaddress()
						+ "getuserdocdetail.do?docid="
						+ topDocumentValue.getDocid());
				n.setAuthor(topDocumentValue.getDocauthor());
				n.setDatepublished(topDocumentValue.getStartdate());
				rssItems.add(n);
			}
			Document document = DocumentHelper.createDocument();
			document.setXMLEncoding("UTF-8");
			Element root = document.addElement("rss");
			root.addAttribute("version", "2.0");
			Element channelele = root.addElement("channel");
			Element titleEle = channelele.addElement("title");
			titleEle.setText("blog");
			Element linkEle = channelele.addElement("link");
			DefaultCDATA conTblOprCdata = new DefaultCDATA(
					sysConfig.getSiteaddress());
			linkEle.add(conTblOprCdata);
			Element descriptionEle = channelele.addElement("description");
			descriptionEle.addCDATA("blog");
			Element lastBuildDateEle = channelele.addElement("lastBuildDate");
			lastBuildDateEle.addCDATA((new Date()).toString());
			for (RssItem rssItem : rssItems) {
				Element itemele = channelele.addElement("item");
				Element tileelei = itemele.addElement("title");
				tileelei.addCDATA(rssItem.getTitle());
				Element linki = itemele.addElement("link");
				linki.addCDATA(rssItem.getLink());
				Element descriptioni = itemele.addElement("description");
				descriptioni.addCDATA(rssItem.getDescription());
				Element authori = itemele.addElement("author");
				authori.addCDATA(rssItem.getAuthor());
				Element pubDatei = itemele.addElement("pubDate");
				pubDatei.addCDATA(rssItem.getDatepublished().toString());
				Element guidi = itemele.addElement("guid");
				guidi.addCDATA(rssItem.getLink());
			}
			returnstr = document.asXML();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.setContentType(MediaType.TEXT_XML);
		responseHeaders.setContentLength(returnstr.getBytes().length);
		responseHeaders
				.setCacheControl("no-store, no-cache, must-revalidate, post-check=0, pre-check=0");
		responseHeaders.setPragma("no-cache");
		return new ResponseEntity<String>(returnstr, responseHeaders,
				HttpStatus.OK);
		// return new ModelAndView("rssView");
	}
}
