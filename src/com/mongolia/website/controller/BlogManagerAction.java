package com.mongolia.website.controller;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.labels.StandardPieToolTipGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;
import org.springframework.web.servlet.ModelAndView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.mongolia.website.controller.ckeditor.SamplePostData;
import com.mongolia.website.manager.ManagerException;
import com.mongolia.website.manager.impls.SysConfig;
import com.mongolia.website.manager.interfaces.ChannelManager;
import com.mongolia.website.manager.interfaces.RaceManager;
import com.mongolia.website.manager.interfaces.UserManager;
import com.mongolia.website.manager.interfaces.WebResourceManager;
import com.mongolia.website.manager.interfaces.WebSiteManager;
import com.mongolia.website.manager.interfaces.WebSiteVisitorManager;
import com.mongolia.website.model.BookStoreValue;
import com.mongolia.website.model.Channel;
import com.mongolia.website.model.DocumentValue;
import com.mongolia.website.model.FriendValue;
import com.mongolia.website.model.ImgGrpupValue;
import com.mongolia.website.model.ImgValue;
import com.mongolia.website.model.MessageValue;
import com.mongolia.website.model.PagingIndex;
import com.mongolia.website.model.PaingModel;
import com.mongolia.website.model.RaceDocumentValue;
import com.mongolia.website.model.RaceModelValue;
import com.mongolia.website.model.UserValue;
import com.mongolia.website.model.VisitorValue;
import com.mongolia.website.model.VoteDetailForm;
import com.mongolia.website.model.VoteDetailValue;
import com.mongolia.website.model.VoteResultDetailValue;
import com.mongolia.website.model.VoteResultValue;
import com.mongolia.website.model.VoteValue;
import com.mongolia.website.util.ImgeUtil;
import com.mongolia.website.util.PageUtil;
import com.mongolia.website.util.StaticConstants;
import com.mongolia.website.util.UUIDMaker;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/**
 * 个人空间管理
 * 
 * @author baowzh
 * 
 */
@Controller
public class BlogManagerAction {
	@Autowired
	private WebResourceManager webResourceManager;
	@Autowired
	private UserManager userManager;
	@Autowired
	private WebSiteVisitorManager webSiteVisitorManager;
	@Autowired
	private WebSiteManager webSiteManager;
	@Autowired
	private ChannelManager channelManager;
	@Autowired
	private RaceManager raceManager;
	@Autowired
	private SysConfig sysConfig;

	/**
	 * 进入个人主页
	 * 
	 * @param request
	 * @param response
	 * @param map
	 * @return
	 */
	@RequestMapping("/gouserindex.do")
	public ModelAndView gointoroom(HttpServletRequest request, ModelMap map) {
		try {
			if (this.isphoneagent(request)) {
				return new ModelAndView("redirect:phoneuserindex.do");
			}
			map.putAll(getUserBlogInfo(request, 1));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ModelAndView("userspace/userhomeindex", map);
	}

	private Map<String, Object> getUserBlogInfo(HttpServletRequest request,
			Integer clienttype) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		UserValue sessionUser = (UserValue) request.getSession().getAttribute(
				"user");
		String url = request.getParameter("url");
		if (url == null || url.equalsIgnoreCase("")) {
			url = "userdoclist.do";
		}
		map.put("url", url);
		String userid = request.getParameter("userid");
		String docchannel = request.getParameter("docchannel");
		UserValue user = null;
		Integer self = new Integer(0);
		if (userid == null || userid.equalsIgnoreCase("")) {
			user = (UserValue) request.getSession().getAttribute("user");// 在线session
			map.put("maillogin", sessionUser.getMaillogin());
			self = new Integer(1);
		} else {
			List<UserValue> uservalues = this.userManager
					.getUsers(userid, null);// 被浏览用户
			user = uservalues.get(0);
			if (sessionUser != null
					&& sessionUser.getUserid().equalsIgnoreCase(
							user.getUserid())) {
				self = new Integer(1);
				map.put("maillogin", sessionUser.getMaillogin());
			} else {
				self = new Integer(0);
			}
		}
		map.put("self", self);
		if (sessionUser != null) {
			map.put("islogin", 1);
			map.put("loginuserid", sessionUser.getUserid());
		} else {
			map.put("islogin", 0);
		}
		Map<String, Object> bologInfos = this.webResourceManager.getBlogInfo(
				user, sessionUser, self, docchannel, 1, clienttype);
		map.putAll(bologInfos);
		map.put("previousindex", 1);
		map.put("currentindex", 1);
		map.put("nextindex", 1);
		if (sessionUser == null) {
			map.put("notlogin", 1);
		} else {
			map.put("notlogin", 0);
		}
		return map;
	}

	@RequestMapping("/phoneuserindex.do")
	public ModelAndView phoneuserindex(HttpServletRequest request, ModelMap map) {
		try {
			map.putAll(getUserBlogInfo(request, 2));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ModelAndView("wap/userphoneindex", map);
	}

	/**
	 * 进入增加文章页面
	 * 
	 * @param request
	 * @param response
	 * @param map
	 * @return
	 */
	@RequestMapping("/toadddoc.do")
	public ModelAndView addDoc(HttpServletRequest request,
			HttpServletResponse response, ModelMap map) {
		map.put("opertype", "1");
		Integer agentkind = 0;
		// 如果是网站维护人员可以多几个栏目
		UserValue sessionUser = (UserValue) request.getSession().getAttribute(
				"user");
		List<Channel> chanels = new ArrayList<Channel>();
		if (sessionUser.getManagerflag() != null
				&& sessionUser.getManagerflag().intValue() == 1) {
			Map<String, Object> getchannelparams = new HashMap<String, Object>();
			getchannelparams.put("types", "1,2");
			chanels = this.channelManager.getChannelList(getchannelparams);
		} else {
			Map<String, Object> getchannelparams = new HashMap<String, Object>();
			getchannelparams.put("types", "2");
			chanels = this.channelManager.getChannelList(getchannelparams);
		}
		String user_agent_kind = request.getHeader("user-agent");
		if (user_agent_kind.indexOf("Chrome") > 0) {
			agentkind = 1;
		} else {
			agentkind = 0;
		}
		map.put("agentkind", agentkind);
		map.put("chanels", chanels);
		return new ModelAndView("userspace/createDoc", map);
	}

	/**
	 * 文章列表
	 * 
	 * @param request
	 * @param response
	 * @param map
	 * @return
	 */
	@RequestMapping("/userdoclist.do")
	public ModelAndView getuserdoclist(HttpServletRequest request,
			HttpServletResponse response, ModelMap map) {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			List<DocumentValue> docList = this.webResourceManager
					.getDocList(params);
			map.put("docList", docList);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ModelAndView("userspace/userdoclist", map);
	}

	/**
	 * 文章内容
	 * 
	 * @param request
	 * @param response
	 * @param map
	 * @return
	 */
	@RequestMapping("/getuserdocdetail.do")
	public ModelAndView getuserdocdetail(HttpServletRequest request,
			HttpServletResponse response, ModelMap map) {
		try {
			map.putAll(getDocDetail(request, 1));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ModelAndView("userspace/textDetail", map);
	}

	private RaceDocumentValue getRaceScoreStatus(String raceid, String docid,
			Integer round) {
		RaceDocumentValue raceDocumentValue = new RaceDocumentValue();
		raceDocumentValue.setNetaveragescore(new Double(0));
		raceDocumentValue.setSpeaveragescore(new Double(0));
		try {
			List<RaceDocumentValue> raceScoreLogValues = this.raceManager
					.getRaceSumValue(raceid, docid, round);
			for (RaceDocumentValue raceScoreLogValue : raceScoreLogValues) {
				if (raceScoreLogValue.getUsertype().intValue() == StaticConstants.JOINRACE_TYPE1) {
					raceDocumentValue.setNettotalscore(raceScoreLogValue
							.getNettotalscore());
					raceDocumentValue.setNetscorecount(raceScoreLogValue
							.getNetscorecount());
					raceDocumentValue.setNetaveragescore(raceScoreLogValue
							.getNetaveragescore());
				} else {
					raceDocumentValue.setSpetotalscore(raceScoreLogValue
							.getNettotalscore());
					raceDocumentValue.setSpescorecount(raceScoreLogValue
							.getNetscorecount());
					raceDocumentValue.setSpeaveragescore(raceScoreLogValue
							.getNetaveragescore());
				}
			}
			raceDocumentValue.setFinalscore(raceDocumentValue
					.getNetaveragescore()
					* 0.2
					+ raceDocumentValue.getSpeaveragescore() * 0.8);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return raceDocumentValue;
	}

	/**
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	private Map<String, Object> getDocDetail(HttpServletRequest request,
			Integer clienttype) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		UserValue user = null;
		UserValue sessionUser = (UserValue) request.getSession().getAttribute(
				"user");
		Integer self = new Integer(0);
		String docid = request.getParameter("docid");
		if (docid != null && docid.indexOf("?") > 0) {
			docid = docid.split("\\?")[0];
		}
		String pageindex = request.getParameter("pageindex");
		Integer pindex = 1;
		if (pageindex != null && !pageindex.equalsIgnoreCase("")) {
			pindex = Integer.parseInt(pageindex);
		}
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("docid", docid);
		String userid = null;
		DocumentValue documentValue = this.webResourceManager
				.readUserDDocument(docid, sessionUser, clienttype);
		List<MessageValue> comments = new ArrayList<MessageValue>();
		if (documentValue != null) {
			userid = documentValue.getUserid();
			Integer commentCount = this.webResourceManager
					.getResourceCommentCount(docid,
							StaticConstants.RESOURCE_TYPE_DOC);
			documentValue.setCommentCount(commentCount);
			// 格式化
			java.text.SimpleDateFormat simpleDateFormat = new java.text.SimpleDateFormat(
					"yyyy-MM-dd");
			String docRelTime = simpleDateFormat.format(documentValue
					.getDocreltime());
			documentValue.setDocRelTimeStr(docRelTime);
			map.put("documentValue", documentValue);
			userid = documentValue.getUserid();

			comments = this.webResourceManager.getResourceCommentList(docid,
					StaticConstants.DOCTYPE_DOC, null, null, null, null);
			// 计算每个comments 是否要显示
		}
		if (sessionUser != null) {
			map.put("login", 1);
		} else {
			map.put("login", 0);
		}
		if (sessionUser != null
				&& userid.equalsIgnoreCase(sessionUser.getUserid())) {
			user = (UserValue) request.getSession().getAttribute("user");// 在线session
			self = new Integer(1);
		} else {
			List<UserValue> uservalues = this.userManager
					.getUsers(userid, null);// 被浏览用户
			user = uservalues.get(0);
			self = new Integer(0);
		}
		map.put("self", self);
		Map<String, Object> bologInfos = this.webResourceManager.getBlogInfo(
				user, sessionUser, self, null, pindex, 1);
		map.putAll(bologInfos);
		List<VisitorValue> visitors = this.webResourceManager.getVisitorList(
				docid, 24);
		setHiddenFlg(user, sessionUser, comments);
		showemotion(comments);
		map.put("comments", comments);
		map.put("visitors", visitors);
		Integer agentkind = 0;
		String user_agent_kind = request.getHeader("user-agent");
		if (user_agent_kind.indexOf("Chrome") > 0) {
			agentkind = 1;
		} else {
			agentkind = 0;
		}
		map.put("agentkind", agentkind);
		// 一个时间段只能有一个有效的活动
		List<RaceModelValue> raceModelValues = this.raceManager.getRaceModels(
				null, 1);
		// 校验是否已经送比赛则显示取消按钮
		// 获取参赛活动，如果有则在页面后面加参赛按钮
		if (raceModelValues != null && !raceModelValues.isEmpty()) {
			map.put("raceModelValue", raceModelValues.get(0));
			List<RaceDocumentValue> racedocs = this.raceManager
					.getRaceDocuments(raceModelValues.get(0).getRaceid(),
							request.getParameter("docid"), null,
							raceModelValues.get(0).getRound());
			if (racedocs != null
					&& !racedocs.isEmpty()
					&& racedocs.get(0).getRaceround().intValue() == raceModelValues
							.get(0).getRound().intValue()) {
				map.put("isjoin", 1);
			} else {
				map.put("isjoin", 0);
			}
			map.put("raceModelValue", raceModelValues.get(0));
			RaceDocumentValue raceDocumentValue = getRaceScoreStatus(
					raceModelValues.get(0).getRaceid(),
					request.getParameter("docid"), raceModelValues.get(0)
							.getRound());
			map.put("raceDocumentValue", raceDocumentValue);
			JSONObject json = new JSONObject();
			json.put("raceModel", raceModelValues.get(0));
			map.put("raceModelJson", json.toString());
		}
		return map;
	}

	@RequestMapping("/phonedetail.do")
	public ModelAndView phonedetail(HttpServletRequest request,
			PaingModel<DocumentValue> paingModel, ModelMap map) {
		try {
			map.putAll(getDocDetail(request, 2));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("wap/detail", map);
	}

	/**
	 * 获取某篇文章
	 * 
	 * @param request
	 * @param response
	 * @param map
	 * @return
	 */
	@RequestMapping("/getdoc.do")
	public ModelAndView getdoc(HttpServletRequest request,
			HttpServletResponse response, ModelMap map) {
		try {
			String docid = request.getParameter("docid");
			String opertype = request.getParameter("opertype");
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("docid", docid);
			List<DocumentValue> docList = this.webResourceManager
					.getDocList(params);
			if (docList != null && !docList.isEmpty()) {
				DocumentValue documentValue = (DocumentValue) docList.get(0);
				if (documentValue.getDoctype().intValue() == StaticConstants.RESOURCE_TYPE_DOC) {
					String docContent = new String(
							documentValue.getDoccontent());
					if (opertype != null && opertype.equalsIgnoreCase("2")) {
						documentValue.setHtmlstr(docContent);
					} else {
						// 替换flash 视频地址
						String matchStr = "\\[\\[http[s]?:\\/\\/([\\w-]+\\.)+[\\w-]+([\\w-./?%&=]*)?\\]\\]";
						Pattern destStri = Pattern.compile(matchStr);// ^
						Matcher mati = destStri.matcher(docContent);
						StringBuffer bufferi = new StringBuffer();
						while (mati.find()) {
							String groupi = mati.group(0);
							groupi = groupi.substring(2, groupi.length() - 2);
							String embed = "<embed pluginspage=\"http://www.macromedia.com/go/getflashplayer\"  src=\""
									+ groupi
									+ "\" allowFullScreen=\"true\" quality=\"high\" width=\"480\" height=\"400\" align=\"middle\" allowScriptAccess=\"always\" type=\"application/x-shockwave-flash\"></embed>";
							mati.appendReplacement(bufferi, embed);
						}
						mati.appendTail(bufferi);
						docContent = bufferi.toString();
						documentValue.setHtmlstr(docContent);
						//
					}

				}
				map.put("documentValue", documentValue);
				map.put("success", "1");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ModelAndView("jsonView", map);
	}

	/**
	 * 上传图片
	 * 
	 * @param request
	 * @param response
	 * @param map
	 * @return
	 */
	@RequestMapping("/imgupload.do")
	public ModelAndView fileupload(HttpServletRequest request,
			ImgValue imgValue, ModelMap map) {
		String imgname = "";
		try {
			request.setCharacterEncoding("utf-8"); // 设置编码
			// 获取文件需要上传到的路径
			String path = request.getSession().getServletContext()
					.getRealPath("html/img");
			imgname = UUIDMaker.getUUID() + ".jpg";
			if (imgValue.getImg() != null && imgValue.getImg().length != 0) {
				ImgeUtil.CompressPic(imgValue.getImg(), path, imgname);
			}
			//
		} catch (Exception ex) {
			return new ModelAndView("sitemanager/error", map);
		}
		map.put("success", "1");
		map.put("imgurl", "html/img/" + imgname);
		return new ModelAndView("userspace/imageupload", map);
	}

	@RequestMapping("/locate.do")
	public ModelAndView locate(HttpServletRequest request,
			HttpServletResponse response, ModelMap map) {
		String page = request.getParameter("path");
		String infotypeid = request.getParameter("infotypeid");
		String txtUrlId = request.getParameter("txtUrlId");
		map.put("infotypeid", infotypeid);
		map.put("txtUrlId", txtUrlId);
		return new ModelAndView(page, map);
	}

	/**
	 * 增加文章
	 * 
	 * @param request
	 * @param response
	 * @param map
	 * @param docValue
	 * @return
	 */
	@RequestMapping("/adddoc.do")
	public ModelAndView adddoc(HttpServletRequest request,
			HttpServletResponse response, ModelMap map, DocumentValue docValue) {
		UserValue sessionUser = (UserValue) request.getSession().getAttribute(
				"user");// 在线session
		List<UserValue> users = this.userManager.getUsers(
				sessionUser.getUserid(), sessionUser.getUsername());
		UserValue userValue = users.get(0);
		SamplePostData samplePostData = new SamplePostData(request);
		String content = samplePostData.getAllFormFieldsAndValues();
		docValue.setDoccontent(content.getBytes());
		docValue.setDocauthor(userValue.getArtname());
		docValue.setUserid(userValue.getUserid());
		docValue.setDocstatus(1);
		docValue.setDocsource(new Double(1));
		docValue.setDoctype(1);
		docValue.setDocid(UUIDMaker.getUUID());
		docValue.setDocreltime(new Date());
		docValue.setCrtime(new Date());
		try {
			webResourceManager.doAdddoc(docValue);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ModelAndView("redirect:gouserindex.do");
	}

	/**
	 * 修改文章
	 * 
	 * @param request
	 * @param response
	 * @param map
	 * @return
	 */
	@RequestMapping("/toupddoc.do")
	public ModelAndView updateDoc(HttpServletRequest request,
			HttpServletResponse response, ModelMap map) {
		String docid = request.getParameter("docid");
		docid = docid.split(",")[0];
		String opertype = request.getParameter("opertype");
		if (opertype == null || opertype.equalsIgnoreCase("")) {
			map.put("opertype", "2");
		} else {
			map.put("opertype", opertype);
		}
		map.put("docid", docid);
		Integer agentkind = 0;
		String user_agent_kind = request.getHeader("user-agent");
		if (user_agent_kind.indexOf("Chrome") > 0) {
			agentkind = 1;
		} else {
			agentkind = 0;
		}
		UserValue sessionUser = (UserValue) request.getSession().getAttribute(
				"user");
		List<Channel> chanels = new ArrayList<Channel>();
		if (sessionUser.getManagerflag() != null
				&& sessionUser.getManagerflag().intValue() == 1) {
			Map<String, Object> getchannelparams = new HashMap<String, Object>();
			getchannelparams.put("type", "1");
			chanels = this.channelManager.getChannelList(getchannelparams);
		} else {
			chanels = this.channelManager
					.getChannelList(new HashMap<String, Object>());
		}
		map.put("agentkind", agentkind);
		map.put("chanels", chanels);
		//
		return new ModelAndView("userspace/createDoc", map);
	}

	/**
	 * 删除文章
	 * 
	 * @param request
	 * @param response
	 * @param map
	 * @return
	 */
	@RequestMapping("/deletedoc.do")
	public ModelAndView deletedoc(HttpServletRequest request,
			HttpServletResponse response, ModelMap map) {
		try {
			String docid = request.getParameter("docid");
			String doctype = request.getParameter("doctype");
			map.put("opertype", "2");
			map.put("docid", docid);
			map.put("doctype", doctype);
			// 删除文章并把相关的评论删除
			UserValue sessionUser = (UserValue) request.getSession()
					.getAttribute("user");// 在线session
			this.webResourceManager.doDeleteDoc(sessionUser.getUserid(), docid,
					doctype);
			map.put("success", "0");
		} catch (Exception ex) {
			ex.printStackTrace();
			map.put("returncode", ex.getMessage());
		}
		// return this.gointoroom(request, map);
		return new ModelAndView("jsonView", map);
	}

	/**
	 * 修改文章
	 * 
	 * @param request
	 * @param response
	 * @param map
	 * @param docValue
	 * @return
	 */
	@RequestMapping("/updatedoc.do")
	public ModelAndView updatedoc(HttpServletRequest request,
			HttpServletResponse response, ModelMap map, DocumentValue docValue) {
		UserValue sessionUser = (UserValue) request.getSession().getAttribute(
				"user");// 在线session
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("docid", docValue.getDocid());
			if (sessionUser.getUserkind().intValue() == StaticConstants.USER_KIND2
					.intValue()) {
				params.put("userid", null);
			} else {
				params.put("userid", sessionUser.getUserid());
			}
			List<DocumentValue> docList = this.webResourceManager
					.getDocList(params);
			DocumentValue documentValue = docList.get(0);
			SamplePostData samplePostData = new SamplePostData(request);
			String content = samplePostData.getAllFormFieldsAndValues();
			documentValue.setDoccontent(content.getBytes());
			documentValue.setDocstatus(1);
			documentValue.setDocsource(new Double(1));
			documentValue.setDoctype(1);
			documentValue.setDocabstract(docValue.getDocabstract());
			documentValue.setDoctitle(docValue.getDoctitle());
			if (sessionUser.getUserkind().intValue() == StaticConstants.USER_KIND2
					.intValue()) {
				documentValue.setCompiler(sessionUser.getArtname());
			}
			webResourceManager.doUpdDoc(documentValue);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		if (sessionUser.getUserkind().intValue() == StaticConstants.USER_KIND2
				.intValue()) {
			return new ModelAndView("redirect:articlelist.do");
		} else {
			return new ModelAndView("redirect:gouserindex.do");
		}

	}

	/**
	 * 增加相册
	 * 
	 * @param request
	 * @param response
	 * @param map
	 * @param imgGrpupValue
	 * @return
	 */
	@RequestMapping("/addimggroup.do")
	public ModelAndView addImgGroup(HttpServletRequest request, ModelMap map,
			ImgGrpupValue imgGrpupValue) {
		try {
			UserValue sessionUser = (UserValue) request.getSession()
					.getAttribute("user");// 在线session
			String path = request.getSession().getServletContext()
					.getRealPath("/html/img");
			String path1 = request.getSession().getServletContext()
					.getRealPath("/html/photoalbum");
			ImgValue tempImgValue = new ImgValue();
			ImgValue imgValue = new ImgValue();
			String imgid = UUIDMaker.getUUID();
			String imgname = imgid + ".jpg";
			if (imgGrpupValue.getImgurl() != null
					&& imgGrpupValue.getImgurl().length != 0) {
				tempImgValue = ImgeUtil.CompressPic(imgGrpupValue.getImgurl(),
						path, imgname);
				ImgeUtil.CompressPic(imgGrpupValue.getImgurl(), path1, imgname);
				imgValue.setImgurl(imgname);
				// ImgeUtil.CompressPic(imgGrpupValue.getImgurl(), path,
				// imgname);
			}
			imgGrpupValue.setImggroupid(UUIDMaker.getUUID());
			imgGrpupValue.setFaceurl(imgname);
			imgGrpupValue.setUserid(sessionUser.getUserid());
			imgGrpupValue.setImggroupkind("1");
			imgGrpupValue.setCreatedtime(new Date());
			this.webResourceManager.doAddIImgGroup(imgGrpupValue);
			// 同时新增一个图片
			imgValue.setImggroupid(imgGrpupValue.getImggroupid());
			imgValue.setImgid(imgid);
			imgValue.setUserid(imgGrpupValue.getUserid());
			imgValue.setImgname(imgGrpupValue.getImggroupname());
			imgValue.setImgdesc(imgGrpupValue.getComm());
			imgValue.setWidth(tempImgValue.getWidth());
			imgValue.setHeight(tempImgValue.getHeight());
			this.webResourceManager.doAddImg(imgValue, path1);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ModelAndView("redirect:photoAlbumList.do", map);
	}

	/**
	 * 修改相册
	 * 
	 * @param request
	 * @param response
	 * @param map
	 * @param imgGrpupValue
	 * @return
	 */
	@RequestMapping("/updimggroup.do")
	public ModelAndView updImgGroup(HttpServletRequest request, ModelMap map,
			ImgGrpupValue imgGrpupValue) {
		try {
			String path = request.getSession().getServletContext()
					.getRealPath("/html/photoalbum");
			String imgname = "";
			imgname = UUIDMaker.getUUID() + ".jpg";
			if (imgGrpupValue.getImgurl() != null
					&& imgGrpupValue.getImgurl() != null) {
				ImgeUtil.CompressPic(imgGrpupValue.getImgurl(), path, imgname);
			}
			this.webResourceManager.doUpdIImgGroup(imgGrpupValue);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return this.getImgGroupList(request, map);
	}

	/**
	 * 增加图片
	 * 
	 * @param request
	 * @param response
	 * @param map
	 * @param imgValue
	 * @return
	 */
	@RequestMapping("/addimg.do")
	public ModelAndView addImg(HttpServletRequest request, ImgValue imgValue,
			ModelMap map) {
		try {
			String path = request.getSession().getServletContext()
					.getRealPath("/html/img");
			String path1 = request.getSession().getServletContext()
					.getRealPath("/html/photoalbum");
			String imgid = UUIDMaker.getUUID();
			String imgname = imgid + ".jpg";
			if (imgValue.getImg() != null && imgValue.getImg().length != 0) {
				ImgValue tempImgValue = ImgeUtil.CompressPic(imgValue.getImg(),
						path, imgname);
				imgValue.setImgurl(imgname);
				imgValue.setImgid(imgid);
				imgValue.setImgname(imgValue.getImgid());
				imgValue.setImgdesc(imgValue.getImgcomm());
				imgValue.setWidth(tempImgValue.getWidth());
				imgValue.setHeight(tempImgValue.getHeight());
				this.webResourceManager.doAddImg(imgValue, path1);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ModelAndView("forward:getimglist.do");
	}

	@RequestMapping("/deleteimgs.do")
	public ModelAndView deleteimgs(HttpServletRequest request, ModelMap map) {
		String imgids = request.getParameter("imgids");
		try {
			UserValue sessionUser = (UserValue) request.getSession()
					.getAttribute("user");// 在线session
			this.webResourceManager
					.doDeleteImg(imgids, sessionUser.getUserid());
			map.put("success", 1);
		} catch (ManagerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("success", 0);
		}
		return new ModelAndView("jsonView", map);
	}

	/**
	 * 获取图片列表
	 * 
	 * @param request
	 * @param response
	 * @param map
	 * @param imgValue
	 * @return
	 */
	@RequestMapping("/getimglist.do")
	public ModelAndView getImgList(HttpServletRequest request, ModelMap map) {
		try {
			String opergroupid = request.getParameter("imggroupid");
			String pageindex = request.getParameter("pageindex");
			String userid = request.getParameter("userid");
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("imggroupid", opergroupid);
			List<ImgGrpupValue> imgGroups = this.webResourceManager
					.getImgGroupList(params);
			map.put("albumValue", imgGroups.get(0));
			map.putAll(this.getBlogInfo(request));
			PaingModel<DocumentValue> paingModel = new PaingModel<DocumentValue>();
			paingModel.setUserid(userid);
			paingModel.setDoctype(StaticConstants.DOCTYPE_IMG);
			if (pageindex == null) {
				paingModel.setPageindex(1);
				pageindex = "1";
			} else {
				paingModel.setPageindex(Integer.parseInt(pageindex));
			}
			paingModel.setStartrow((paingModel.getPageindex() - 1) * 24);
			paingModel.setEndrow(paingModel.getPagesize());
			paingModel.setPagesize(24);
			paingModel.setImggroupid(opergroupid);
			PaingModel<DocumentValue> pageModel = webSiteVisitorManager
					.pagingquerydoc(paingModel);
			map.put("imgList", pageModel.getModelList());
			String idAndIndexrel = "";
			List<DocumentValue> docs = pageModel.getModelList();
			for (int i = 0; i < docs.size(); i++) {
				idAndIndexrel = idAndIndexrel + (i + 1) + ","
						+ docs.get(i).getDocid() + "#";
			}
			String pagingimgStr = PageUtil.getPagingImgLink(paingModel, 1);
			/*
			 * List<PagingIndex> indexs = new ArrayList<PagingIndex>(); for (int
			 * i = 0; i < paingModel.getPagecount(); i++) { PagingIndex
			 * pagingIndex = new PagingIndex();// 就显示首页，末页和当前页，当前页前面，后面
			 * pagingIndex.setPageindex(i + 1); if (Integer.parseInt(pageindex)
			 * == i + 1) { pagingIndex.setCurrent(1); } if (i == 0) {
			 * indexs.add(pagingIndex); pagingIndex.setDoc(0); } else if (i ==
			 * paingModel.getPagecount() - 1) { indexs.add(pagingIndex);
			 * pagingIndex.setDoc(0); } else if (i + 1 ==
			 * paingModel.getPageindex()) { indexs.add(pagingIndex);
			 * pagingIndex.setCurrent(1); } else if (i ==
			 * paingModel.getPageindex()) { indexs.add(pagingIndex); if (i + 2
			 * != paingModel.getPagecount() && i != 1) { pagingIndex.setDoc(1);
			 * pagingIndex.setFront(0); } } else if (i ==
			 * paingModel.getPageindex() - 2) { indexs.add(pagingIndex); if (i
			 * != 1 && i + 1 != paingModel.getPagecount() && i + 1 !=
			 * paingModel.getPageindex()) { pagingIndex.setDoc(1);
			 * pagingIndex.setFront(1); } }
			 * 
			 * }
			 * 
			 * map.put("pagingindexs", indexs);
			 */
			map.put("pagingimgStr", pagingimgStr);
			map.put("imgcount", pageModel.getRowcount());
			map.put("imggroupid", opergroupid);
			map.put("idAndIndexrel", idAndIndexrel);
			// 检索是否有专题活动
			List<RaceModelValue> racemodels = this.raceManager.getRaceModels(
					null, 1);
			if (racemodels != null && !racemodels.isEmpty()) {
				map.put("racemodel", racemodels.get(0));
			}
			Object sessionobj = request.getSession().getAttribute("user");
			if (sessionobj != null) {
				map.put("sessionuser", sessionobj);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ModelAndView("userspace/photoList", map);
	}

	/**
	 * 获取图片列表
	 * 
	 * @param request
	 * @param response
	 * @param map
	 * @param imgValue
	 * @return
	 */
	@RequestMapping("/pagingqueryimg.do")
	public ModelAndView pagingqueryimg(HttpServletRequest request,
			ModelMap map, PaingModel<DocumentValue> paingModel) {
		try {
			String opergroupid = request.getParameter("imggroupid");
			Map<String, Object> params = new HashMap<String, Object>();
			String pageindex = request.getParameter("pageindex");
			params.put("imggroupid", opergroupid);
			List<ImgGrpupValue> imgGroups = this.webResourceManager
					.getImgGroupList(params);
			map.put("albumValue", imgGroups.get(0));
			paingModel.setDoctype(StaticConstants.DOCTYPE_IMG);
			paingModel.setPageindex(Integer.parseInt(pageindex));
			paingModel.setStartrow((Integer.parseInt(pageindex) - 1) * 12 + 1);
			paingModel.setEndrow(12);
			paingModel.setImggroupid(opergroupid);
			PaingModel<DocumentValue> pageModel = webSiteVisitorManager
					.pagingquerydoc(paingModel);
			map.put("imgList", pageModel);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ModelAndView("jsonView", map);
	}

	@RequestMapping("/getimginfo.do")
	public ModelAndView getImgInfo(HttpServletRequest request, ModelMap map) {
		try {
			String imgid = request.getParameter("imgid");
			String imggroupid = request.getParameter("imggroupid");
			String idAndIndexrel = request.getParameter("idAndIndexrel");
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("docid", imgid);
			UserValue user = null;
			UserValue sessionUser = (UserValue) request.getSession()
					.getAttribute("user");
			if (sessionUser != null) {
				map.put("islogin", 1);
				map.put("loginuserid", sessionUser.getUserid());
			} else {
				map.put("islogin", 0);
			}
			Integer self = new Integer(0);
			String userid = request.getParameter("userid");
			if (userid == null || userid.equalsIgnoreCase("")) {
				user = (UserValue) request.getSession().getAttribute("user");// 在线session
				map.put("maillogin", sessionUser.getMaillogin());
				self = new Integer(1);
			} else {
				List<UserValue> uservalues = this.userManager.getUsers(userid,
						null);// 被浏览用户
				user = uservalues.get(0);
				if (sessionUser != null
						&& sessionUser.getUserid().equalsIgnoreCase(
								user.getUserid())) {
					self = new Integer(1);
					map.put("maillogin", sessionUser.getMaillogin());
				} else {
					self = new Integer(0);
				}
			}
			map.put("self", self);
			DocumentValue documentValue = this.webResourceManager
					.readUserDDocument(imgid, sessionUser, 1);
			if (documentValue.getDoctype() == StaticConstants.RESOURCE_TYPE_DOC) {
				documentValue.setHtmlstr(new String(documentValue
						.getDoccontent()));
			}
			map.put("documentValue", documentValue);
			map.put("success", "1");
			map.put("idAndIndexrel", idAndIndexrel);
			List<UserValue> uservalues = this.userManager.getUsers(
					documentValue.getUserid(), null);// 被浏览用户
			user = uservalues.get(0);
			// }
			params.put("imgid", imgid);
			List<ImgValue> imgList = this.webResourceManager.getImgList(params);
			if (imgList != null && !imgList.isEmpty()) {
				// 获取本相册所有照片
				Map<String, Object> getimgParmas = new HashMap<String, Object>();
				if (imggroupid == null) {
					imggroupid = imgList.get(0).getImggroupid();
				}
				getimgParmas.put("imggroupid", imggroupid);
				List<ImgValue> imgs = this.webResourceManager
						.getImgList(getimgParmas);
				map.put("imgValue", imgList.get(0));
				imgs.add(0, imgList.get(0));
				for (ImgValue imgValuei : imgs) {
					SimpleDateFormat SimpleDateFormat = new SimpleDateFormat(
							"yyyy-MM-dd");
					String datest = SimpleDateFormat.format(imgValuei
							.getCrtime());
					imgValuei.setCrtimestr(datest);
				}
				map.put("imggroupid", imggroupid);
				map.putAll(this.getBlogInfo(request));
				List<VisitorValue> visitors = this.webResourceManager
						.getVisitorList(imgid, 24);
				map.put("visitors", visitors);
				map.put("imgs", imgs);
				List<MessageValue> comments = this.webResourceManager
						.getResourceCommentList(imgid,
								StaticConstants.DOCTYPE_IMG, null, null, null,
								null);
				if (documentValue != null) {
					documentValue.setCommentCount(comments.size());
				}
				// 修改格式
				this.showemotion(comments);
				this.setHiddenFlg(user, sessionUser, comments);
				map.put("comments", comments);
				map.put("documentValue", documentValue);

				Integer agentkind = 0;
				String user_agent_kind = request.getHeader("user-agent");
				if (user_agent_kind.indexOf("Chrome") > 0) {
					agentkind = 1;
				} else {
					agentkind = 0;
				}
				map.put("agentkind", agentkind);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ModelAndView("userspace/photoDetail", map);
	}

	/**
	 * @param request
	 * @param response
	 * @param map
	 * @param imgValue
	 * @return
	 */
	@RequestMapping("/userimags.do")
	public ModelAndView getImgGroupList(HttpServletRequest request, ModelMap map) {
		try {
			List imgGroupList = this.webResourceManager
					.getImgGroupList(new HashMap<String, Object>());
			List rows = new ArrayList();
			List row = new ArrayList();
			for (int i = 0; i < imgGroupList.size(); i++) {
				row.add(imgGroupList.get(i));
				if ((i + 1) % 2 == 0) {
					rows.add(row);
					row = new ArrayList();
				}
			}
			rows.add(row);
			map.put("rows", rows);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ModelAndView("userspace/imggrouplist", map);
	}

	/**
	 * 删除相册
	 * 
	 * @param request
	 * @param response
	 * @param map
	 * @return
	 */
	@RequestMapping("/dodeleteimggroup.do")
	public ModelAndView deleteImgGroup(HttpServletRequest request,
			HttpServletResponse response, ModelMap map) {
		String opergroupid = request.getParameter("imggroupid");
		try {
			UserValue sessionUser = (UserValue) request.getSession()
					.getAttribute("user");
			this.webResourceManager.doDeleteImgGroup(opergroupid,
					sessionUser.getUserid());
			map.put("success", 1);
			map.put("userid", sessionUser.getUserid());
		} catch (Exception ex) {
			ex.printStackTrace();
			map.put("success", 0);
		}
		return new ModelAndView("jsonView", map);
	}

	/**
	 * 相册信息
	 * 
	 * @param request
	 * @param response
	 * @param map
	 * @return
	 */
	@RequestMapping("/photoAlbumList.do")
	public ModelAndView getImgGroupInfo(HttpServletRequest request,
			HttpServletResponse response, ModelMap map) {
		try {
			//
			UserValue sessionUser = (UserValue) request.getSession()
					.getAttribute("user");
			String userid = request.getParameter("userid");
			Map<String, Object> params = new HashMap<String, Object>();
			if (userid == null) {
				params.put("userid", sessionUser.getUserid());
				userid = sessionUser.getUserid();
			} else {
				params.put("userid", userid);
			}
			map.putAll(this.getBlogInfo(request));
			List<ImgGrpupValue> result = this.webResourceManager
					.getImgGroupList(params);
			map.put("photoAlbumList", result);
			//
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ModelAndView("userspace/photoAlbumList", map);
	}

	/**
	 * 给图片或者文章留言
	 * 
	 * @param request
	 * @param response
	 * @param map
	 * @return
	 */
	@RequestMapping("/addCommentOnResource.do")
	public ModelAndView addCommentOnResource(HttpServletRequest request,
			HttpServletResponse response, ModelMap map) {
		try {
			String docid = request.getParameter("docid");
			String userid = request.getParameter("userid");
			String dtype = request.getParameter("doctype");
			String hidden = request.getParameter("ishidden");
			String validcode = request.getParameter("validcode");
			Map<String, Object> queryParams = new HashMap<String, Object>();
			queryParams.put("docid", docid);
			Object sysValidcode = request.getSession().getAttribute(
					"validateCode");
			if (sysValidcode == null) {
				map.put("success", "0");
				map.put("errorcode", "0");
				return new ModelAndView("jsonView", map);
			}
			if (!((String) sysValidcode).equalsIgnoreCase(validcode.trim())) {
				map.put("success", "0");
				map.put("errorcode", "1");
				return new ModelAndView("jsonView", map);
			}
			request.getSession().removeAttribute("validateCode");
			List<DocumentValue> docList = this.webResourceManager
					.getDocList(queryParams);
			if (docList != null && !docList.isEmpty()) {
				String comment = request.getParameter("comment");
				// 使用正则表达式替换表情部分
				DocumentValue documentValue = docList.get(0);
				// 作者id
				if (userid == null || userid.equalsIgnoreCase("")) {
					userid = documentValue.getUserid();
				}
				UserValue userValue = (UserValue) request.getSession()
						.getAttribute("user");// 在线session
				String messageSenderid = userValue.getUserid();
				String messageSenderName = userValue.getArtname();
				// 给作者的作文留言
				Integer ishidden = new Integer(0);
				if (hidden != null) {
					ishidden = Integer.parseInt(hidden);
				}
				this.webResourceManager.doAddCommentOnResource(docid,
						Integer.parseInt(dtype), userid, comment,
						messageSenderid, messageSenderName,
						StaticConstants.MESS_TYPE_COMM, ishidden);
			}
			request.getSession().removeAttribute("validateCode");
			map.put("success", "1");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ModelAndView("jsonView", map);
	}

	/**
	 * 获取文章留言信息
	 * 
	 * @param request
	 * @param response
	 * @param map
	 * @return
	 */
	@RequestMapping("/getDocComment.do")
	public ModelAndView getDocComment(HttpServletRequest request,
			HttpServletResponse response, ModelMap map) {
		try {
			String docid = request.getParameter("docid");
			String doctype = request.getParameter("doctype");
			UserValue sessionUser = new UserValue();
			Map<String, Object> params = new HashMap<String, Object>();
			List<MessageValue> comments = this.webResourceManager
					.getResourceCommentList(docid, Integer.parseInt(doctype),
							null, null, null, null);
			if (comments != null && !comments.isEmpty()) {
				UserValue userValue = (UserValue) request.getSession()
						.getAttribute("user");// 在线session
				sessionUser = userValue;
				Map<String, Object> queryParams = new HashMap<String, Object>();
				queryParams.put("docid", docid);
				List<DocumentValue> docList = this.webResourceManager
						.getDocList(queryParams);
				if (userValue != null
						&& userValue.getUserid().equalsIgnoreCase(
								docList.get(0).getUserid())) {
					map.put("self", "1");
				} else {
					map.put("self", "0");
				}
				showemotion(comments);
				// this.setHiddenFlg(blogUser, sessionUser, comments);
				map.put("comments", comments);

				map.put("success", "1");
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ModelAndView("jsonView", map);
	}

	/**
	 * 分享资源
	 */
	@RequestMapping("/shareUserresource.do")
	public ModelAndView shareUserresource(HttpServletRequest request,
			HttpServletResponse response, ModelMap map) {
		String resourceid = request.getParameter("resouceid");
		String resoucekind = request.getParameter("resoucekind");
		UserValue userValue = (UserValue) request.getSession().getAttribute(
				"user");// 在线session
		try {
			this.webResourceManager.doShareDdoc(resourceid,
					userValue.getUserid());
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("docid", resourceid);
			List<DocumentValue> docs = this.webResourceManager
					.getDocList(params);
			map.put("documentValue", docs.get(0));
			map.put("success", "1");
			map.put("message", " ");
		} catch (Exception ex) {
			ex.printStackTrace();
			map.put("success", "0");
			map.put("message", ex.getMessage());
		}
		return new ModelAndView("jsonView", map);
	}

	/**
	 * 分享资源
	 */
	@RequestMapping("/markUserresource.do")
	public ModelAndView markUserresource(HttpServletRequest request,
			HttpServletResponse response, ModelMap map) {
		String resourceid = request.getParameter("resouceid");
		// String resoucekind = request.getParameter("resoucekind");
		UserValue userValue = (UserValue) request.getSession().getAttribute(
				"user");// 在线session
		try {
			this.webResourceManager
					.doMarkDoc(resourceid, userValue.getUserid());
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("docid", resourceid);
			List<DocumentValue> docs = this.webResourceManager
					.getDocList(params);
			map.put("documentValue", docs.get(0));

			map.put("success", "1");
			map.put("message", " ");
		} catch (Exception ex) {
			ex.printStackTrace();
			map.put("success", "0");
			map.put("message", ex.getMessage());
		}
		return new ModelAndView("jsonView", map);
	}

	/**
	 * 删除资源上的评论
	 * 
	 * @param request
	 * @param map
	 * @return
	 */
	@RequestMapping("/delresourcecomment.do")
	public ModelAndView delResourceComment(HttpServletRequest request,
			ModelMap map) {
		String messid = request.getParameter("messid");
		String docid = request.getParameter("docid");
		UserValue userValue = (UserValue) request.getSession().getAttribute(
				"user");// 在线session
		// 查找消息是否属于发送给当前用户的消息若是，删除
		String returncode = "00";
		try {
			this.webResourceManager.doDelResourceComment(docid,
					StaticConstants.RESOURCE_TYPE_DOC, userValue.getUserid(),
					messid);
			map.put("returncode", returncode);
		} catch (ManagerException ex) {
			ex.printStackTrace();
			returncode = ex.getMessage();
			map.put("returncode", returncode);
		}
		return new ModelAndView("jsonView", map);
	}

	/**
	 * 给用户发信息
	 * 
	 * @param request
	 * @param map
	 * @return
	 */
	@RequestMapping("/sendMessage.do")
	public ModelAndView sendMessage(HttpServletRequest request, ModelMap map) {
		try {
			UserValue sessionUser = (UserValue) request.getSession()
					.getAttribute("user");// 在线session
			String userid = request.getParameter("userid");
			String mess = request.getParameter("mess");
			String messtype = request.getParameter("messtype");
			String currentuserid = sessionUser.getUserid();
			String messageSenderName = sessionUser.getArtname();
			// 给作者的作文留言
			this.webResourceManager.doSendMess(userid,
					StaticConstants.RESOURCE_TYPE_USER, userid, mess,
					currentuserid, messageSenderName,
					Integer.parseInt(messtype));
			map.put("mess", "success");
		} catch (Exception ex) {
			map.put("mess", ex.getMessage());
		}
		return new ModelAndView("jsonView", map);
	}

	/**
	 * 显示收件箱和发件箱列表
	 * 
	 * @param request
	 * @param map
	 * @return
	 */
	@RequestMapping("/getMessage.do")
	public ModelAndView receiveMessage(HttpServletRequest request, ModelMap map) {
		try {
			UserValue sessionUser = (UserValue) request.getSession()
					.getAttribute("user");// 在线session
			String currentuserid = sessionUser.getUserid();
			String pageIndex = request.getParameter("pageIndex");
			if (pageIndex == null) {
				pageIndex = "1";
			}

			PaingModel<MessageValue> messagepage = this.webResourceManager
					.getReceMessList(currentuserid, null,
							Integer.parseInt(pageIndex), 8);
			List<MessageValue> receiveMessList = messagepage.getModelList();
			showemotion(receiveMessList);
			List<PagingIndex> pageindexs = getMessagePageindexs(
					Integer.parseInt(pageIndex), messagepage.getPagecount());
			PaingModel<MessageValue> messagepage1 = this.webResourceManager
					.getSendMessList(currentuserid, null,
							Integer.parseInt(pageIndex), 8);
			map.put("pageindexs", pageindexs);
			List<MessageValue> sendMessList = messagepage1.getModelList();
			showemotion(sendMessList);
			List<PagingIndex> pageindexs1 = getMessagePageindexs(
					Integer.parseInt(pageIndex), messagepage1.getPagecount());
			map.put("pageindexs1", pageindexs1);
			String type = request.getParameter("type");
			if ("1".equalsIgnoreCase(type)) {
				map.put("messList", receiveMessList);
			} else {
				map.put("messList", sendMessList);
			}
			map.put("type", type);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ModelAndView("userspace/messlist", map);
	}

	private List<PagingIndex> getMessagePageindexs(Integer pageindex,
			Integer pageCount) {
		List<PagingIndex> indexs = new ArrayList<PagingIndex>();
		for (int i = 0; i < pageCount; i++) {
			PagingIndex pagingIndex = new PagingIndex();// 就显示首页，末页和当前页，当前页前面，后面
			pagingIndex.setPageindex(i + 1);
			if (i + 1 == pageindex.intValue()) {
				pagingIndex.setCurrent(1);
			}
			if (i == 0) {
				indexs.add(pagingIndex);
				pagingIndex.setDoc(0);
			} else if (i == pageCount - 1) {
				indexs.add(pagingIndex);
				pagingIndex.setDoc(0);
			} else if (i + 1 == pageindex) {
				indexs.add(pagingIndex);
				pagingIndex.setCurrent(1);
			} else if (i == pageindex) {
				indexs.add(pagingIndex);
				if (i + 2 != pageCount && i != 1) {
					pagingIndex.setDoc(1);
					pagingIndex.setFront(0);
				}
			} else if (i == pageindex - 2) {
				indexs.add(pagingIndex);
				if (i != 1 && i + 1 != pageCount && i + 1 != pageindex) {
					pagingIndex.setDoc(1);
					pagingIndex.setFront(1);
				}
			}

		}
		return indexs;
	}

	@RequestMapping("/getmesscontent.do")
	public ModelAndView getmesscontent(HttpServletRequest request,
			HttpServletResponse response, ModelMap map) {
		try {
			String messid = request.getParameter("messid");
			MessageValue messageValue = this.webResourceManager
					.getMessageValueContent(messid);
			map.put("messageValue", messageValue);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ModelAndView("jsonView", map);
	}

	@RequestMapping("/friendlist.do")
	public ModelAndView getFriendList(HttpServletRequest request,
			PaingModel<FriendValue> pagingModel, HttpServletResponse response,
			ModelMap map) {
		try {
			// String userid = request.getParameter("userid");
			// String pageIndex = pagingModel.getPageindex();
			// Integer index = 0;
			// if (pageIndex == null || pageIndex.equalsIgnoreCase("")) {
			// index = new Integer(1);
			// } else {
			// index = Integer.parseInt(pageIndex);
			// }
			map.putAll(this.getBlogInfo(request));
			pagingModel.setPagesize(40);
			pagingModel = this.webResourceManager
					.pagingQueryFriends(pagingModel);
			map.put("friendList", pagingModel.getModelList());
			// Integer friendCount =
			// Integer.parseInt(pagingModel.getRowcount());
			String pagestr = PageUtil.getPagingFriendLink(pagingModel, 1);
			// List<String> pageindexs = new ArrayList<String>();
			// for (int i = 0; i < friendCount; i++) {
			// pageindexs.add(String.valueOf(i + 1));
			// }
			// map.put("pageindexs", pageindexs);
			map.put("pagestr", pagestr);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ModelAndView("userspace/friendList", map);
	}

	private Map<String, Object> getBlogInfo(HttpServletRequest request) {
		String userid = request.getParameter("userid");
		UserValue user = null;
		UserValue sessionUser = (UserValue) request.getSession().getAttribute(
				"user");
		Integer self = new Integer(0);
		if (sessionUser != null && userid != null
				&& userid.equalsIgnoreCase(sessionUser.getUserid())) {
			user = (UserValue) request.getSession().getAttribute("user");// 在线session
			self = new Integer(1);
			// 获取所有朋友的动态
		} else if (sessionUser != null && userid == null) {
			user = (UserValue) request.getSession().getAttribute("user");// 在线session
			self = new Integer(1);
		} else {
			List<UserValue> uservalues = this.userManager
					.getUsers(userid, null);// 被浏览用户
			user = uservalues.get(0);
			self = new Integer(0);
			// 获取当前用户的动态
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("self", self);
		if (sessionUser != null) {
			map.put("islogin", 1);
			map.put("loginuserid", sessionUser.getUserid());
		} else {
			map.put("islogin", 0);
		}
		try {
			Map<String, Object> bologInfos = this.webResourceManager
					.getBlogInfo(user, sessionUser, self, null, 1, 1);
			map.putAll(bologInfos);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return map;

	}

	@RequestMapping("/getimg.do")
	public ResponseEntity<byte[]> getImge(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String imgid = request.getParameter("imgid");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("imgid", imgid);
		List<ImgValue> imgs = new ArrayList<ImgValue>();
		try {
			imgs = this.webResourceManager.getImgList(params);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		ImgValue imgValue = imgs.get(0);
		HttpHeaders responseHeaders = new HttpHeaders();
		if (imgValue.getImgurl().endsWith(".jpg")) {
			responseHeaders.setContentType(MediaType.IMAGE_JPEG);
		} else if (imgValue.getImgurl().endsWith(".png")) {
			responseHeaders.setContentType(MediaType.IMAGE_PNG);
		} else if (imgValue.getImgurl().endsWith(".gif")) {
			responseHeaders.setContentType(MediaType.IMAGE_GIF);
		}
		responseHeaders.setContentLength(imgValue.getImgcontent().length);
		responseHeaders
				.setCacheControl("no-store, no-cache, must-revalidate, post-check=0, pre-check=0");
		responseHeaders.setPragma("no-cache");
		return new ResponseEntity<byte[]>(imgValue.getImgcontent(),
				responseHeaders, HttpStatus.OK);
	}

	@RequestMapping("/getphotoalbumface.do")
	public ResponseEntity<byte[]> getPhotoAlbumFace(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String albumid = request.getParameter("albumid");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("imggroupid", albumid);
		List<ImgGrpupValue> imgs = new ArrayList<ImgGrpupValue>();
		try {
			imgs = this.webResourceManager.getImgGroupList(params);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		ImgGrpupValue imgValue = imgs.get(0);
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.setContentType(MediaType.IMAGE_JPEG);
		responseHeaders.setContentLength(imgValue.getFaceimg().length);
		responseHeaders
				.setCacheControl("no-store, no-cache, must-revalidate, post-check=0, pre-check=0");
		responseHeaders.setPragma("no-cache");
		return new ResponseEntity<byte[]>(imgValue.getFaceimg(),
				responseHeaders, HttpStatus.OK);
	}

	@RequestMapping("/getphotoList.do")
	public ModelAndView getphotoList(HttpServletRequest request,
			HttpServletResponse response, ModelMap map) {
		try {
			//
			String opergroupid = request.getParameter("opergroupid");
			String userid = request.getParameter("userid");
			UserValue sessionUser = (UserValue) request.getSession()
					.getAttribute("user");
			Map<String, Object> params = new HashMap<String, Object>();
			if (userid == null || userid.equalsIgnoreCase("")) {
				params.put("userid", sessionUser.getUserid());
			} else {
				params.put("userid", userid);
			}
			map.put("imggroupid", opergroupid);
			List<ImgValue> result = this.webResourceManager.getImgList(params);
			map.put("photoList", result);
			//
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ModelAndView("jsonView", map);
	}

	/**
	 * 获取已接收和已发送信息列表
	 * 
	 * @param request
	 * @param response
	 * @param map
	 * @return
	 */
	@RequestMapping("/getmessages.do")
	public ModelAndView getMessageList(HttpServletRequest request,
			HttpServletResponse response, ModelMap map) {
		UserValue sessionUser = (UserValue) request.getSession().getAttribute(
				"user");
		String messid = request.getParameter("messid");
		String pageindex = request.getParameter("pageindex");
		Integer pageIndex = null;
		if (pageindex != null) {
			pageIndex = Integer.parseInt(pageindex);
		}
		try {

			PaingModel<MessageValue> pagemodel = this.webResourceManager
					.getReceMessList(sessionUser.getUserid(), messid,
							pageIndex, 8);
			List<MessageValue> receiveMess = pagemodel.getModelList();
			List<PagingIndex> recepageindex = this.getMessagePageindexs(
					pageIndex, pagemodel.getPagecount());
			PaingModel<MessageValue> pagemodel1 = this.webResourceManager
					.getSendMessList(sessionUser.getUserid(), messid,
							pageIndex, 8);
			List<MessageValue> sendMess = pagemodel1.getModelList();
			List<PagingIndex> sendpageindex = this.getMessagePageindexs(
					pageIndex, pagemodel.getPagecount());
			map.put("receiveMess", receiveMess);
			map.put("recepageindexs", recepageindex);
			map.put("sendpageindexs", sendpageindex);
			map.put("sendMess", sendMess);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ModelAndView("jsonView", map);
	}

	/**
	 * 相册信息
	 * 
	 * @param request
	 * @param response
	 * @param map
	 * @return
	 */
	@RequestMapping("/getalbumlist.do")
	public ModelAndView getalbumlist(HttpServletRequest request,
			HttpServletResponse response, ModelMap map) {
		try {
			//
			UserValue sessionUser = (UserValue) request.getSession()
					.getAttribute("user");
			UserValue user = null;
			String userid = request.getParameter("userid");
			Map<String, Object> params = new HashMap<String, Object>();
			if (userid == null || userid.equalsIgnoreCase("")) {
				params.put("userid", sessionUser.getUserid());
				userid = sessionUser.getUserid();
			} else {
				params.put("userid", userid);
			}
			List<ImgGrpupValue> result = this.webResourceManager
					.getImgGroupList(params);
			map.put("photoAlbumList", result);
			//
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ModelAndView("jsonView", map);
	}

	@RequestMapping("/getnextdoc.do")
	public ModelAndView getnextdoc(HttpServletRequest request, ModelMap map) {
		try {
			//
			String docid = request.getParameter("docid");
			// String groupid = request.getParameter("imggroupid");
			String idAndIndexrel = request.getParameter("idAndIndexrel");
			String[] idAndIndexrels = idAndIndexrel.split("#");
			String index = "";
			for (int i = 0; i < idAndIndexrels.length; i++) {
				String idandIndex = idAndIndexrels[i];
				if (idandIndex.indexOf(docid) > 0) {
					index = idandIndex.split(",")[0];
				}
			}
			Integer intIndex = Integer.parseInt(index);
			intIndex = intIndex + 1;
			for (int i = 0; i < idAndIndexrels.length; i++) {
				String idandIndex = idAndIndexrels[i];
				String sindex = idandIndex.split(",")[0];
				if (Integer.parseInt(sindex) == intIndex) {
					docid = idandIndex.split(",")[1];
					break;
				}
			}
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("docid", docid);
			// List docList = this.webResourceManager.getDocList(params);
			UserValue sessionUser = (UserValue) request.getSession()
					.getAttribute("user");
			DocumentValue documentValue = this.webResourceManager
					.readUserDDocument(docid, sessionUser, 1);
			Integer commentCount = this.webResourceManager
					.getResourceCommentCount(docid,
							StaticConstants.RESOURCE_TYPE_IMG);
			documentValue.setCommentCount(commentCount);
			// if (docList != null && !docList.isEmpty()) {
			// DocumentValue documentValue = (DocumentValue) docList.get(0);
			// if (documentValue.getDoctype() ==
			// StaticConstants.RESOURCE_TYPE_DOC) {
			// documentValue.setHtmlstr(new String(documentValue
			// .getDoccontent()));
			// }
			// map.put("documentValue", documentValue);
			// map.put("success", "1");
			// map.put("idAndIndexrel", idAndIndexrel);
			// }
			if (documentValue != null) {
				map.put("nextDoc", documentValue);
				map.put("exists", "1");
			} else {
				map.put("exists", "0");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ModelAndView("jsonView", map);
	}

	@RequestMapping("/getpreviousdoc.do")
	public ModelAndView getpreviousdoc(HttpServletRequest request, ModelMap map) {
		try {
			//
			String docid = request.getParameter("docid");
			// String groupid = request.getParameter("imggroupid");
			String idAndIndexrel = request.getParameter("idAndIndexrel");
			String[] idAndIndexrels = idAndIndexrel.split("#");
			String index = "";
			for (int i = 0; i < idAndIndexrels.length; i++) {
				String idandIndex = idAndIndexrels[i];
				if (idandIndex.indexOf(docid) > 0) {
					index = idandIndex.split(",")[0];
				}
			}
			Integer intIndex = Integer.parseInt(index);
			intIndex = intIndex - 1;
			for (int i = 0; i < idAndIndexrels.length; i++) {
				String idandIndex = idAndIndexrels[i];
				String sindex = idandIndex.split(",")[0];
				if (Integer.parseInt(sindex) == intIndex) {
					docid = idandIndex.split(",")[1];
					break;
				}
			}
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("docid", docid);
			UserValue sessionUser = (UserValue) request.getSession()
					.getAttribute("user");
			// UserValue user=null;

			DocumentValue documentValue = this.webResourceManager
					.readUserDDocument(docid, sessionUser, 1);
			Integer commentCount = this.webResourceManager
					.getResourceCommentCount(docid,
							StaticConstants.RESOURCE_TYPE_IMG);
			documentValue.setCommentCount(commentCount);
			// List<UserValue> uservalues =
			// this.userManager.getUsers(documentValue.getUserid(),
			// null);// 被浏览用户
			// user = uservalues.get(0);
			// List docList = this.webResourceManager.getDocList(params);
			// !
			// if (docList != null && !docList.isEmpty()) {
			// DocumentValue documentValue = (DocumentValue) docList.get(0);
			// if (documentValue.getDoctype() ==
			// StaticConstants.RESOURCE_TYPE_DOC) {
			// documentValue.setHtmlstr(new String(documentValue
			// .getDoccontent()));
			// }
			// map.put("documentValue", documentValue);
			// map.put("success", "1");
			// map.put("idAndIndexrel", idAndIndexrel);
			// }
			if (documentValue != null) {
				map.put("exists", "1");
				map.put("previosdoc", documentValue);
			} else {
				map.put("exists", "0");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ModelAndView("jsonView", map);
	}

	/**
	 * 分页查询文章
	 * 
	 * @param pagingModel
	 * @return
	 */
	@RequestMapping("/pagingdoc.do")
	public ModelAndView pagingdoc(PaingModel<DocumentValue> pagingModel,
			ModelMap map) {
		try {
			if (pagingModel.getPagesize() == null
					|| pagingModel.getPagesize().intValue() == 0) {
				pagingModel.setPagesize(24);
			}
			pagingModel.setDoctype(StaticConstants.DOCTYPE_DOC);
			pagingModel.setDocstatus(StaticConstants.DOCSTATUS2);
			PaingModel<DocumentValue> paingModel1 = this.webSiteVisitorManager
					.pagingquerydoc(pagingModel);
			List<DocumentValue> doclist = paingModel1.getModelList();
			map.put("doclist", doclist);
			// String pagestr = getPagingLink(pagingModel, 1);
			String pagestr = PageUtil.getPagingLink(pagingModel, 1);
			map.put("pagingstr", pagestr);
			// 设置分页栏
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ModelAndView("jsonView", map);
	}

	@RequestMapping("/pagingsharedoc.do")
	public ModelAndView pagingsharedoc(PaingModel<DocumentValue> pagingModel,
			ModelMap map) {
		try {
			if (pagingModel.getPagesize() == null
					|| pagingModel.getPagesize().intValue() == 0) {
				pagingModel.setPagesize(24);
			}
			pagingModel.setDoctype(StaticConstants.DOCTYPE_DOC);
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("userid", pagingModel.getUserid());
			PaingModel<DocumentValue> paingModel1 = this.webResourceManager
					.pagingQuerySharedDocs(params, StaticConstants.DOCTYPE_DOC,
							pagingModel.getPageindex(), 24);
			List<DocumentValue> doclist = paingModel1.getModelList();
			map.put("doclist", doclist);
			// String pagestr = getPagingLink(pagingModel, 2);
			String pagestr = PageUtil.getPagingLink(pagingModel, 2);
			map.put("pagingstr", pagestr);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ModelAndView("jsonView", map);
	}

	@RequestMapping("/readmessage.do")
	public ModelAndView readmessage(HttpServletRequest request, ModelMap map) {
		String messageid = request.getParameter("messageid");
		try {
			MessageValue messageValue = this.webResourceManager
					.getMessageValueContent(messageid);
			// 修改格式
			List<MessageValue> mess = new ArrayList<MessageValue>();
			mess.add(messageValue);
			this.showemotion(mess);
			map.put("messvalue", mess.get(0));
			map.put("success", "1");
		} catch (Exception ex) {
			ex.printStackTrace();
			map.put("success", "0");
		}
		return new ModelAndView("jsonView", map);
	}

	@RequestMapping("/votemanager.do")
	public ModelAndView voteManager(HttpServletRequest request, ModelMap map) {
		// 把所有问卷调查都列出来工修改和删除等
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			List<VoteValue> votes = this.webResourceManager.getVoteList(params);
			map.put("votes", votes);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ModelAndView("vote/voteindex");
	}

	@RequestMapping("/addvote.do")
	public ModelAndView addvote(HttpServletRequest request, ModelMap map) {
		String uuid = UUIDMaker.getUUID();
		map.put("voteid", uuid);
		return new ModelAndView("vote/votedesign");
	}

	@RequestMapping("/updvote.do")
	public ModelAndView updvote(HttpServletRequest request, ModelMap map) {
		String voteid = request.getParameter("voteid");
		String redirecturl = request.getParameter("redirecturl");
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("voteid", voteid);
			List<VoteValue> votes = this.webResourceManager.getVoteList(params);
			map.put("votes", votes);
			if (votes != null && !votes.isEmpty()) {
				map.put("voteid", votes.get(0).getVoteid());
				map.put("votevalue", votes.get(0));
			}
			map.put("redirecturl", redirecturl);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		// if (redirecturl != null && !redirecturl.equalsIgnoreCase("")) {
		// return new ModelAndView("redirect:" + redirecturl);
		// } else {
		return new ModelAndView("vote/votedesign");
		// }

	}

	@RequestMapping("/savevote.do")
	public ModelAndView savevote(HttpServletRequest request, ModelMap map) {
		String voteid = request.getParameter("voteid");
		String topic = request.getParameter("topic");
		try {
			UserValue sessionUser = (UserValue) request.getSession()
					.getAttribute("user");
			String userid = sessionUser.getUserid();
			this.webResourceManager.doSaveVote(voteid, topic, userid);
			map.put("success", "1");
		} catch (Exception ex) {
			map.put("success", "0");
			ex.printStackTrace();
		}
		return new ModelAndView("jsonView");
	}

	@RequestMapping("/delvote.do")
	public ModelAndView deletevote(HttpServletRequest request, ModelMap map) {
		String voteid = request.getParameter("voteid");
		UserValue sessionUser = (UserValue) request.getSession().getAttribute(
				"user");
		String userid = sessionUser.getUserid();
		try {
			this.webResourceManager.doDelVote(voteid, userid);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ModelAndView("redirect:votemanager.do");
	}

	@RequestMapping("/checkvotestatus.do")
	public ModelAndView checkvotestatus(HttpServletRequest request, ModelMap map) {
		String voteid = request.getParameter("voteid");
		try {
			Map<String, Object> checkresult = this.webResourceManager
					.checkVoteStatus(voteid);
			map.put("checkresult", checkresult);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ModelAndView("jsonView");
	}

	@RequestMapping("/addquestion.do")
	public ModelAndView addquestion(HttpServletRequest request,
			VoteDetailForm voteDetailValue, ModelMap map) {
		try {
			List<VoteDetailValue> details = this.webResourceManager
					.doAddVoteQuestion(voteDetailValue);
			map.put("questions", details);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ModelAndView("jsonView");
	}

	@RequestMapping("/joinvote.do")
	public ModelAndView joinvote(HttpServletRequest request, ModelMap map) {
		String voteid = request.getParameter("voteid");
		try {
			// joinvote.do?redirecturl
			String redirecturl = request.getParameter("redirecturl");
			Integer self = new Integer(0);
			UserValue sessionUser = (UserValue) request.getSession()
					.getAttribute("user");
			UserValue user = null;
			String userid = request.getParameter("userid");
			if (userid == null || userid.equalsIgnoreCase("")) {
				user = (UserValue) request.getSession().getAttribute("user");// 在线session
				self = new Integer(1);
			} else {
				List<UserValue> uservalues = this.userManager.getUsers(userid,
						null);// 被浏览用户
				user = uservalues.get(0);
				if (sessionUser != null
						&& sessionUser.getUserid().equalsIgnoreCase(
								user.getUserid())) {
					self = new Integer(1);
				} else {
					self = new Integer(0);
				}
			}
			map.put("self", self);
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("voteid", voteid);
			List<VoteValue> votes = this.webResourceManager.getVoteList(params);
			map.put("votes", votes);
			if (votes != null && !votes.isEmpty()) {
				map.put("voteid", votes.get(0).getVoteid());
				map.put("votevalue", votes.get(0));
			}
			// 所有问题列表已json方式输出
			JSONObject json = new JSONObject();
			json.put("vote", votes);
			map.put("jsonvote", json.toString());
			map.put("redirecturl", redirecturl);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ModelAndView("vote/vote");
	}

	@RequestMapping("/calandview.do")
	public ModelAndView calandview(HttpServletRequest request, ModelMap map) {
		try {
			UserValue sessionUser = (UserValue) request.getSession()
					.getAttribute("user");
			String userid = sessionUser.getUserid();
			String voteid = request.getParameter("voteid");
			String singglequestionrel = request
					.getParameter("singglequestionrel");
			String multiquestionrel = request.getParameter("multiquestionrel");
			String textquestionrel = request.getParameter("textquestionrel");
			this.webResourceManager.doSaveVoteResult(voteid,
					singglequestionrel, multiquestionrel, textquestionrel,
					userid);
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("voteid", voteid);
			List<VoteValue> votes = this.webResourceManager.getVoteList(params);
			map.put("votes", votes);
			if (votes != null && !votes.isEmpty()) {
				map.put("voteid", votes.get(0).getVoteid());
				map.put("votevalue", votes.get(0));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ModelAndView("vote/voteresult");
	}

	@RequestMapping("/viewvoteresult.do")
	public ModelAndView viewvoteresult(HttpServletRequest request, ModelMap map) {
		try {
			String voteid = request.getParameter("voteid");
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("voteid", voteid);
			List<VoteValue> votes = this.webResourceManager.getVoteList(params);
			map.put("votes", votes);
			if (votes != null && !votes.isEmpty()) {
				map.put("voteid", votes.get(0).getVoteid());
				map.put("votevalue", votes.get(0));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ModelAndView("vote/voteresult");
	}

	/**
	 * 问卷调查输出图片结果
	 * 
	 * @param request
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getchar.do")
	public ResponseEntity<byte[]> getchar(HttpServletRequest request,
			ModelMap map) throws Exception {
		String voteid = request.getParameter("voteid");
		String questionid = request.getParameter("questionid");
		List<VoteResultDetailValue> resultdetails = this.webResourceManager
				.calVoteResult(voteid, questionid);
		DefaultPieDataset dpd = new DefaultPieDataset(); // 建立一个默认的饼图
		for (int i = 0; i < resultdetails.size(); i++) {
			dpd.setValue(resultdetails.get(i).getCharindex(), resultdetails
					.get(i).getVotenum());
		}
		StandardChartTheme standardChartTheme = new StandardChartTheme("CN");
		// 设置标题字体
		standardChartTheme.setExtraLargeFont(new Font("隶书", Font.BOLD, 20));
		// 设置图例的字体
		standardChartTheme.setRegularFont(new Font("宋书", Font.PLAIN, 15));
		// 设置轴向的字体
		standardChartTheme.setLargeFont(new Font("宋书", Font.PLAIN, 15));
		// 应用主题样式
		ChartFactory.setChartTheme(standardChartTheme);
		// JFreeChart chart = ChartFactory.createPieChart(" ", dpd, true, true,
		// false);
		JFreeChart chart = ChartFactory.createPieChart3D("", dpd, true, true,
				true);
		PiePlot pieplot = (PiePlot) chart.getPlot();
		//
		pieplot.setBaseSectionOutlinePaint(Color.RED);
		// plot.setBaseSectionPaint(Color.WHITE);
		// 图形边框粗细
		pieplot.setBaseSectionOutlineStroke(new BasicStroke(1.0f));

		// 指定图片的透明度(0.0-1.0)
		pieplot.setForegroundAlpha(0.65f);
		// 指定显示的饼图上圆形(false)还椭圆形(true)
		pieplot.setCircular(true);

		// 设置第一个 饼块section 的开始位置，默认是12点钟方向
		pieplot.setStartAngle(360);
		// 设置鼠标悬停提示
		pieplot.setToolTipGenerator(new StandardPieToolTipGenerator());

		// 设置突出显示的数据块
		pieplot.setExplodePercent("One", 0.1D);
		// 设置饼图各部分标签字体
		pieplot.setLabelFont(new Font("宋体", Font.ITALIC, 20));
		// 设置分饼颜色
		pieplot.setSectionPaint(0, new Color(244, 194, 144));

		pieplot.setLabelGenerator(new StandardPieSectionLabelGenerator(
				"{0}={1}({2})", NumberFormat.getNumberInstance(),
				new DecimalFormat("0.00%")));
		//
		BufferedImage bufferedImage = chart.createBufferedImage(410, 400);
		String path = request.getSession().getServletContext()
				.getRealPath("html/img");
		FileOutputStream fileout = new FileOutputStream(
				new File(path, "a.jpeg"));
		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(fileout);
		byte readerr[] = null;
		try {
			encoder.encode(bufferedImage);
			fileout.flush();
			FileInputStream inputStream = new FileInputStream(new File(path,
					"a.jpeg"));
			int length = inputStream.available();
			readerr = new byte[length];
			inputStream.read(readerr);
			inputStream.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.setContentType(MediaType.IMAGE_JPEG);
		responseHeaders.setContentLength(readerr.length);
		responseHeaders
				.setCacheControl("no-store, no-cache, must-revalidate, post-check=0, pre-check=0");
		responseHeaders.setPragma("no-cache");
		return new ResponseEntity<byte[]>(readerr, responseHeaders,
				HttpStatus.OK);
	}

	@RequestMapping("/gettextquestioresult.do")
	public ModelAndView getTextQuestionResult(HttpServletRequest request,
			ModelMap map) throws Exception {
		String voteid = request.getParameter("voteid");
		String questionid = request.getParameter("questionid");
		String index = request.getParameter("index");
		Integer pageindex = new Integer(1);
		if (index == null || index.equalsIgnoreCase("")) {
			pageindex = 1;
		} else {
			pageindex = Integer.parseInt(index);
		}
		PaingModel<VoteResultValue> paingModel = this.webResourceManager
				.pagingqueryVoteResult(voteid, questionid, pageindex);
		//
		List<PagingIndex> indexs = new ArrayList<PagingIndex>();
		for (int i = 0; i < paingModel.getPagecount(); i++) {
			PagingIndex pagingIndex = new PagingIndex();// 就显示首页，末页和当前页，当前页前面，后面
			pagingIndex.setPageindex(i + 1);
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
		map.put("paingModel", paingModel);
		map.put("voteid", voteid);
		map.put("questionid", questionid);
		//
		return new ModelAndView("vote/textquestionresult");
	}

	@RequestMapping("/deltevotequestion.do")
	public ModelAndView delteVoteQuestion(HttpServletRequest request,
			ModelMap map) throws Exception {
		String questionid = request.getParameter("questionid");
		String voteid = request.getParameter("voteid");
		try {
			this.webResourceManager.doDelVoteQuestion(voteid, questionid);
			map.put("success", 1);
		} catch (Exception ex) {
			map.put("success", 0);
			map.put("mess", ex.getMessage());
		}
		return new ModelAndView("jsonView", map);

	}

	@RequestMapping("/delvotequestionsel.do")
	public ModelAndView delVoteQuestionSel(HttpServletRequest request,
			ModelMap map) throws Exception {
		String questionid = request.getParameter("questionid");
		String answerid = request.getParameter("answerid");
		try {
			this.webResourceManager.doDelVoteQuestionSel(questionid, answerid);
			map.put("success", 1);
		} catch (Exception ex) {
			map.put("success", 0);
			map.put("mess", ex.getMessage());
		}
		return new ModelAndView("jsonView", map);

	}

	/**
	 * 
	 * @param blogUser
	 * @param sessionUser
	 * @param comments
	 */
	private void setHiddenFlg(UserValue blogUser, UserValue sessionUser,
			List<MessageValue> comments) {
		// 判断是否要显示隐藏的comment
		for (int i = 0; i < comments.size(); i++) {
			MessageValue messageValue = comments.get(i);
			if (messageValue.getHidden() != null
					&& messageValue.getHidden().intValue() == 1) {
				if (sessionUser != null
						&& blogUser.getUserid().equalsIgnoreCase(
								sessionUser.getUserid())) {
					messageValue.setHidden(new Integer(0));
				} else if (sessionUser != null
						&& !blogUser.getUserid().equalsIgnoreCase(
								sessionUser.getUserid())
						&& (messageValue.getMessagesenderid()
								.equalsIgnoreCase(sessionUser.getUserid()))) {
					messageValue.setHidden(new Integer(0));
				} else {
					messageValue.setHidden(new Integer(1));
				}

			}
			// 设置是否显示删除按钮
			if (sessionUser != null
					&& (messageValue.getMessagesenderid().equalsIgnoreCase(
							sessionUser.getUserid()) || messageValue
							.getUserid().equalsIgnoreCase(
									sessionUser.getUserid()))) {
				messageValue.setShowdel(1);
				messageValue.setHidden(0);
			}

		}

	}

	/**
	 * 
	 * @param comments
	 */
	private void showemotion(List<MessageValue> comments) {
		//
		for (MessageValue messageValue : comments) {
			//
			// 使用正则表达式替换表情部分
			String comment = messageValue.getContenthtml();
			String matchStr = "\\[\\w+\\]";
			Pattern destStri = Pattern.compile(matchStr);// ^
			Matcher mati = destStri.matcher(comment);
			StringBuffer bufferi = new StringBuffer();
			while (mati.find()) {
				String groupi = mati.group(0);
				groupi = groupi.substring(1, groupi.length() - 1);
				String imgStr = "<img src=\"img/faces/" + groupi + ".gif\"/>";
				mati.appendReplacement(bufferi, imgStr);

			}
			mati.appendTail(bufferi);
			comment = bufferi.toString();
			messageValue.setContenthtml(comment);
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			messageValue.setSendtimestr(format.format(messageValue
					.getSendtime()));
			//
		}
		//

	}

	@RequestMapping("/gzipdoccontent.do")
	public ModelAndView gzipdoccontent(HttpServletRequest request, ModelMap map)
			throws Exception {
		try {
			this.webResourceManager.gzipdoccontent();
			map.put("success", 1);
		} catch (Exception ex) {
			map.put("success", 0);
			map.put("mess", ex.getMessage());
		}
		return new ModelAndView("jsonView", map);
	}

	/**
	 * 审核内容
	 * 
	 * @param request
	 * @param response
	 * @param map
	 * @return
	 */
	@RequestMapping("/uploaddoc.do")
	public ModelAndView checkDocument(HttpServletRequest request,
			HttpServletResponse response, ModelMap map) {
		String docid = request.getParameter("docid");
		String docids[] = { docid };
		try {
			this.webSiteManager.doCheckDocument(docids, new Integer(3));
			map.put("success", 1);
		} catch (Exception ex) {
			ex.printStackTrace();
			map.put("success", 0);
			return new ModelAndView("error", map);
		}
		return new ModelAndView("jsonView", map);
	}

	/**
	 * 文章内容
	 * 
	 * @param request
	 * @param response
	 * @param map
	 * @return
	 */
	@RequestMapping("/readdoc.do")
	public ModelAndView readdoc(HttpServletRequest request,
			HttpServletResponse response, ModelMap map) {
		try {
			UserValue user = null;
			UserValue sessionUser = (UserValue) request.getSession()
					.getAttribute("user");
			Integer self = new Integer(0);
			String docid = request.getParameter("docid");
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("docid", docid);
			String userid = null;
			DocumentValue documentValue = this.webResourceManager
					.readUserDDocument(docid, sessionUser, 1);
			List<MessageValue> comments = new ArrayList<MessageValue>();
			if (documentValue != null) {
				userid = documentValue.getUserid();
				Integer commentCount = this.webResourceManager
						.getResourceCommentCount(docid,
								StaticConstants.RESOURCE_TYPE_DOC);
				documentValue.setCommentCount(commentCount);
				// 格式化
				java.text.SimpleDateFormat simpleDateFormat = new java.text.SimpleDateFormat(
						"yyyy-MM-dd");
				String docRelTime = simpleDateFormat.format(documentValue
						.getDocreltime());
				documentValue.setDocRelTimeStr(docRelTime);
				map.put("documentValue", documentValue);
				userid = documentValue.getUserid();

				comments = this.webResourceManager.getResourceCommentList(
						docid, StaticConstants.DOCTYPE_DOC, null, null, null,
						null);
				// 计算每个comments 是否要显示
			}
			if (sessionUser != null
					&& userid.equalsIgnoreCase(sessionUser.getUserid())) {
				user = (UserValue) request.getSession().getAttribute("user");// 在线session
				self = new Integer(1);
			} else {
				List<UserValue> uservalues = this.userManager.getUsers(userid,
						null);// 被浏览用户
				user = uservalues.get(0);
				self = new Integer(0);
			}
			map.put("self", self);
			setHiddenFlg(user, sessionUser, comments);
			showemotion(comments);
			map.put("comments", comments);
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
		return new ModelAndView("jsonView", map);
	}

	@RequestMapping("/toinderrtimg.do")
	public ModelAndView toinderrtimg(HttpServletRequest request,
			HttpServletResponse response, ModelMap map) {
		return new ModelAndView("userspace/insertimg");
	}

	@RequestMapping("/inderrtimg.do")
	public ModelAndView inderrtimg(HttpServletRequest request,
			BookStoreValue bookStoreValue, ModelMap map) {
		String imgname = "";
		try {
			request.setCharacterEncoding("utf-8"); // 设置编码
			// 获取文件需要上传到的路径
			String path = request.getSession().getServletContext()
					.getRealPath("/html/img");
			imgname = UUIDMaker.getUUID() + ".jpg";
			if (bookStoreValue.getImgurl() != null
					&& bookStoreValue.getImgurl().length != 0) {
				ImgeUtil.CompressPic(bookStoreValue.getImgurl(), path, imgname);
				map.put("picurl", "html/img/" + imgname);
			}

		} catch (Exception ex) {
			return new ModelAndView("sitemanager/error", map);
		}
		return new ModelAndView("userspace/insertimg", map);
	}

	@InitBinder
	protected void initBinder(WebDataBinder binder) throws ServletException {
		binder.registerCustomEditor(byte[].class,
				new ByteArrayMultipartFileEditor());
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, false));
	}

	private boolean isphoneagent(HttpServletRequest request) {
		// Enumeration<String> headers = request.getHeaderNames();
		String user_agent = request.getHeader("user-agent");
		if (user_agent.indexOf("Mobile") > 0) {
			return true;
		}
		return false;
	}

	@RequestMapping("/delMessage.do")
	public ModelAndView delMessage(HttpServletRequest request,
			HttpServletResponse response, ModelMap map) {
		try {
			this.webResourceManager.delMessage("",
					request.getParameter("messid"));
			map.put("success", "1");
		} catch (Exception ex) {
			ex.printStackTrace();
			map.put("success", "0");
		}
		return new ModelAndView("jsonView", map);
	}

	@RequestMapping("/getQRCode.do")
	public ResponseEntity<byte[]> getQRCode(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String imgid = request.getParameter("docid");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("imgid", imgid);
		int width = 200; // 图像宽度
		int height = 240; // 图像高度
		String format = "png";// 图像类型
		Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();
		hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
		BitMatrix bitMatrix = new MultiFormatWriter().encode(
				sysConfig.getSiteaddress() + "/phonedetail.do?docid=" + imgid,
				BarcodeFormat.QR_CODE, width, height, hints);// 生成矩阵
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		MatrixToImageWriter.writeToStream(bitMatrix, format, stream);// 输出图像
		byte[] imgcontent = stream.toByteArray();
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.setContentType(MediaType.IMAGE_PNG);
		responseHeaders.setContentLength(imgcontent.length);
		responseHeaders
				.setCacheControl("no-store, no-cache, must-revalidate, post-check=0, pre-check=0");
		responseHeaders.setPragma("no-cache");
		return new ResponseEntity<byte[]>(imgcontent, responseHeaders,
				HttpStatus.OK);
	}

}
