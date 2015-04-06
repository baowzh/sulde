package com.mongolia.website.controller;

import java.io.File;
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
import com.mongolia.website.manager.interfaces.RaceManager;
import com.mongolia.website.manager.interfaces.UserManager;
import com.mongolia.website.manager.interfaces.WebResourceManager;
import com.mongolia.website.manager.interfaces.WebSiteManager;
import com.mongolia.website.manager.interfaces.WebSiteVisitorManager;
import com.mongolia.website.model.Channel;
import com.mongolia.website.model.DistrictValue;
import com.mongolia.website.model.DocumentValue;
import com.mongolia.website.model.ImgGrpupValue;
import com.mongolia.website.model.PagingIndex;
import com.mongolia.website.model.PaingModel;
import com.mongolia.website.model.QueryDocForm;
import com.mongolia.website.model.QueryUserForm;
import com.mongolia.website.model.RssItem;
import com.mongolia.website.model.TopDocumentValue;
import com.mongolia.website.model.UserValue;
import com.mongolia.website.util.PageUtil;
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
	private WebResourceManager webResourceManager;
	@Autowired
	UserManager userManager;
	@Autowired
	private RaceManager raceManager;
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
			if (this.isphoneagent(request)) {
				String loginsuccess = request.getParameter("loginsuccess");
				if (loginsuccess != null) {
					return new ModelAndView(
							"redirect:phoneindex.do?loginsuccess=1");
				} else {
					return new ModelAndView("redirect:phoneindex.do");
				}

			}
			String path = request.getSession().getServletContext()
					.getRealPath("/");
			File file = new File(path, "index.html");
			if (file.exists()) {// 如果pc客户端则且存在静态页面直接返回静态页面
				return new ModelAndView("redirect:index.html");
			}
			indexPageContent = this.webSiteVisitorManager.getIndexContent(path);

			map.put("indexPageContent", indexPageContent);
			Integer agentkind = 0;
			String user_agent_kind = request.getHeader("user-agent");
			if (user_agent_kind.indexOf("Chrome") > 0) {
				agentkind = 1;
			} else {
				agentkind = 0;
			}
			map.put("agentkind", agentkind);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
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
			// if (queryDocForm.getStatus() != null
			// && queryDocForm.getStatus().intValue() == 0) {
			// queryDocParams.put("status", null);
			// } else {
			queryDocParams.put("status", StaticConstants.DOCSTATUS2);
			// }
			if (queryDocForm.getChannel() != null
					&& queryDocForm.getChannel().equalsIgnoreCase("#")) {
				queryDocParams.put("channelid", null);
			} else {

				queryDocParams.put("channelid", queryDocForm.getChannel());
			}
			if (queryDocForm.getAuthorname() == null
					|| (queryDocForm.getAuthorname() != null && queryDocForm
							.getAuthorname().equalsIgnoreCase(""))) {
				queryDocParams.put("authorname", null);
			} else {
				queryDocParams.put("authorname", queryDocForm.getAuthorname()
						.trim());
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
			if (queryDocForm.getDoctitle() == null
					|| (queryDocForm.getDoctitle() != null && queryDocForm
							.getDoctitle().equalsIgnoreCase(""))) {
				queryDocParams.put("doctitle", null);
			} else {
				queryDocParams.put("doctitle", queryDocForm.getDoctitle()
						.trim());
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

	/**
	 * 
	 * @param request
	 * @param map
	 * @param queryDocForm
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/imgs.do")
	public ModelAndView imgs(HttpServletRequest request, ModelMap map)
			throws IOException {
		try {
			String pageindex = request.getParameter("pageindex");
			PaingModel<ImgGrpupValue> paingModel = new PaingModel<ImgGrpupValue>();
			paingModel.setDoctype(StaticConstants.DOCTYPE_IMG);
			if (pageindex == null) {
				paingModel.setPageindex(1);
				pageindex = "1";
			} else {
				paingModel.setPageindex(Integer.parseInt(pageindex));
			}
			paingModel.setStartrow((paingModel.getPageindex() - 1) * 8);
			paingModel.setPagesize(8);
			paingModel.setEndrow(paingModel.getPagesize());

			PaingModel<ImgGrpupValue> pageModel = webSiteVisitorManager
					.pagingqueryAlbum(paingModel);
			map.put("imgList", pageModel.getModelList());
			if (pageModel.getModelList().isEmpty()) {
				map.put("isempty", 1);
			} else {
				map.put("isempty", 0);
			}
			String idAndIndexrel = "";
			List<ImgGrpupValue> docs = pageModel.getModelList();
			for (int i = 0; i < docs.size(); i++) {
				idAndIndexrel = idAndIndexrel + (i + 1) + ","
						+ docs.get(i).getImggroupid() + "#";
			}
			List<PagingIndex> indexs = new ArrayList<PagingIndex>();
			for (int i = 0; i < paingModel.getPagecount(); i++) {
				PagingIndex pagingIndex = new PagingIndex();// 就显示首页，末页和当前页，当前页前面，后面
				pagingIndex.setPageindex(i + 1);
				if (Integer.parseInt(pageindex) == i + 1) {
					pagingIndex.setCurrent(1);
				}
				if (i == 0) {
					indexs.add(pagingIndex);
					pagingIndex.setDoc(0);
				} else if (i == paingModel.getPagecount() - 1) {
					indexs.add(pagingIndex);
					pagingIndex.setDoc(0);
				} else if (i + 1 == paingModel.getPageindex()) {
					indexs.add(pagingIndex);
					pagingIndex.setCurrent(1);
				} else if (i == paingModel.getPageindex()) {
					indexs.add(pagingIndex);
					if (i + 2 != paingModel.getPagecount() && i != 1) {
						pagingIndex.setDoc(1);
						pagingIndex.setFront(0);
					}
				} else if (i == paingModel.getPageindex() - 2) {
					indexs.add(pagingIndex);
					if (i != 1 && i + 1 != paingModel.getPagecount()
							&& i + 1 != paingModel.getPageindex()) {
						pagingIndex.setDoc(1);
						pagingIndex.setFront(1);
					}
				}
			}
			map.put("pagingindexs", indexs);
			map.put("imgcount", pageModel.getRowcount());
			map.put("idAndIndexrel", idAndIndexrel);
			String linkstr = PageUtil.getPagingAlbumLink(pageModel, 1);
			map.put("linkstr", linkstr);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ModelAndView("website/photolist", map);
	}

	@RequestMapping("/videos.do")
	public ModelAndView videos(HttpServletRequest request,
			PaingModel<DocumentValue> paingModel, ModelMap map) {
		try {
			paingModel.setFlash(1);
			paingModel.setPagesize(8);
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
		return new ModelAndView("website/videos", map);
	}

	/**
	 * 
	 * @param request
	 * @return
	 */
	private boolean isphoneagent(HttpServletRequest request) {
		// Enumeration<String> headers = request.getHeaderNames();
		String user_agent = request.getHeader("user-agent");
		if (user_agent.indexOf("Mobile") > 0) {
			return true;
		}
		return false;
	}

	@RequestMapping("/phoneindex.do")
	public ModelAndView phoneindex(HttpServletRequest request,
			PaingModel<DocumentValue> paingModel, ModelMap map) {
		try {
			String loginsuccess = request.getParameter("loginsuccess");
			List<TopDocumentValue> selecteddocs = this.webSiteVisitorManager
					.getTopDocuments(StaticConstants.TOP_TYPE4, null, 14);
			map.put("selecteddocs", selecteddocs);
			List<DocumentValue> recentdocs = this.webSiteVisitorManager
					.getRecentDocs(14);
			map.put("recentdocs", recentdocs);
			List<TopDocumentValue> imgnews = this.webSiteVisitorManager
					.getTopDocuments(StaticConstants.TOP_TYPE1, null, 7);
			map.put("imgnews", imgnews);
			map.put("loginsuccess", loginsuccess);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ModelAndView("wap/index", map);
	}

	@RequestMapping("/phonelist.do")
	public ModelAndView phonelist(HttpServletRequest request,
			PaingModel<DocumentValue> paingModel, ModelMap map) {
		try {
			paingModel.setPagesize(12);
			paingModel.setStartrow(1 * paingModel.getPagesize());
			paingModel.setEndrow(12);
			PaingModel<DocumentValue> pamodel = webSiteVisitorManager
					.pagingquerydoc(paingModel);
			String pagingstr = PageUtil.getPagingLink(pamodel, 1);
			map.put("pagingstr", pagingstr);
			map.put("selecteddocs", pamodel.getModelList());
			map.put("docchannel", paingModel.getDocchannel());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ModelAndView("wap/list", map);
	}

	@RequestMapping("/professionlist.do")
	public ModelAndView professionlist(HttpServletRequest request,
			QueryUserForm queryUserForm, ModelMap map) {
		try {
			if (queryUserForm.getPageindex() == null) {
				queryUserForm.setPageindex(1);
			}
			queryUserForm.setUsername(queryUserForm.getSearchtext());
			queryUserForm.setPagesize(120);
			PaingModel<UserValue> paingUser = this.webSiteManager
					.getUsers(queryUserForm);
			map.put("users", paingUser.getModelList());
			map.put("usercount", paingUser.getRowcount());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ModelAndView("website/professionlist", map);
	}

	@RequestMapping("/phonechannel.do")
	public ModelAndView phonechannel(HttpServletRequest request, ModelMap map) {
		return new ModelAndView("wap/channel");
	}

	@RequestMapping("/phonebloglist.do")
	public ModelAndView phonebloglist(HttpServletRequest request,
			QueryUserForm queryUserForm, ModelMap map) throws Exception {
		if (queryUserForm.getPageindex() == null) {
			queryUserForm.setPageindex(1);
		}
		queryUserForm.setUsername(queryUserForm.getSearchtext());
		queryUserForm.setPagesize(12);
		PaingModel<UserValue> paingUser = this.webSiteManager
				.getUsers(queryUserForm);
		paingUser.setPageindex(queryUserForm.getPageindex());
		map.put("users", paingUser.getModelList());
		map.put("usercount", paingUser.getRowcount());
		map.put("queryform", queryUserForm);
		String pagestr = PageUtil.getPagingUserLink(paingUser, 1);
		map.put("pagestr", pagestr);
		return new ModelAndView("wap/selblogs");
	}

	@RequestMapping("/phoneracelist.do")
	public ModelAndView phoneracelist(HttpServletRequest request,
			PaingModel<DocumentValue> paingModel, ModelMap map) {
		try {
			paingModel.setPagesize(12);
			paingModel.setStartrow(1 * paingModel.getPagesize());
			paingModel.setEndrow(12);
			PaingModel<DocumentValue> pamodel = this.raceManager
					.pagingqueryracedoc(paingModel);
			String pagingstr = PageUtil.getPagingLink(pamodel, 1);
			map.put("pagingstr", pagingstr);
			map.put("selecteddocs", pamodel.getModelList());
			map.put("docchannel", paingModel.getDocchannel());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ModelAndView("wap/racelist", map);
	}

	@RequestMapping("/browserlist.do")
	public ModelAndView browserlist(HttpServletRequest request, ModelMap map) {
		return new ModelAndView("website/browserlist", map);
	}

}
