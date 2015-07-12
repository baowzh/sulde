package com.mongolia.website.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;
import org.springframework.web.servlet.ModelAndView;

import com.mongolia.website.controller.ckeditor.SamplePostData;
import com.mongolia.website.controller.freemarker.CustomFreeMarkerConfigurer;
import com.mongolia.website.manager.interfaces.BookStoreManager;
import com.mongolia.website.manager.interfaces.UserManager;
import com.mongolia.website.manager.interfaces.WebResourceManager;
import com.mongolia.website.manager.interfaces.WebSiteManager;
import com.mongolia.website.manager.interfaces.WebSiteVisitorManager;
import com.mongolia.website.model.BookStoreValue;
import com.mongolia.website.model.DistrictValue;
import com.mongolia.website.model.DocumentValue;
import com.mongolia.website.model.MenuValue;
import com.mongolia.website.model.MessageValue;
import com.mongolia.website.model.OpinionValue;
import com.mongolia.website.model.PagingIndex;
import com.mongolia.website.model.PaingModel;
import com.mongolia.website.model.QueryDocForm;
import com.mongolia.website.model.QueryOpinionFrom;
import com.mongolia.website.model.QueryUserForm;
import com.mongolia.website.model.TopDocumentValue;
import com.mongolia.website.model.UserValue;
import com.mongolia.website.util.ImgeUtil;
import com.mongolia.website.util.PageUtil;
import com.mongolia.website.util.StaticConstants;
import com.mongolia.website.util.UUIDMaker;

/**
 * 网站管理
 * 
 * @author baowzh
 */
@Controller
public class WebSiteManagerAction {
	@Autowired
	private WebSiteManager webSiteManager;
	@Autowired
	private UserManager userManager;
	@Autowired
	private WebSiteVisitorManager webSiteVisitorManager;
	@Autowired
	private WebResourceManager webResourceManager;
	@Autowired
	private BookStoreManager bookStoreManagerImpl;

	/**
	 * 打开后台管理功能
	 * 
	 * @param map
	 * @return
	 */
	@RequestMapping("/gotositemanager.do")
	public ModelAndView gotoSiteManager(ModelMap map) {
		try {
			HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
					.getRequestAttributes()).getRequest();
			Object uservalue = request.getSession().getAttribute("user");
			if (uservalue == null) {
				return new ModelAndView("userspace/login", map);
			}
			return new ModelAndView("sitemanager/managerindex", map);
		} catch (Exception ex) {
			return new ModelAndView("error", map);
		}
	}

	/**
	 * 维护网站页面
	 * 
	 * @param map
	 * @return
	 */
	@RequestMapping("/gotopagemanager.do")
	public ModelAndView gotopagemanager(ModelMap map) {
		try {
			return new ModelAndView("sitemanager/pagemanager", map);
		} catch (Exception ex) {
			return new ModelAndView("error", map);
		}
	}

	/**
	 * 获取模板引擎配置
	 * 
	 * @param request
	 * @return
	 */
	private CustomFreeMarkerConfigurer getTemplateLoder(
			HttpServletRequest request) {
		CustomFreeMarkerConfigurer customTemplateLoader = (CustomFreeMarkerConfigurer) WebApplicationContextUtils
				.getWebApplicationContext(
						request.getSession().getServletContext()).getBean(
						"freemarkerConfig");
		return customTemplateLoader;
	}

	/**
	 * 获取后台管理菜单列表
	 * 
	 * @param request
	 * @param map
	 * @return
	 */
	@RequestMapping("/getsitemanagermenus.do")
	public ModelAndView getSiteManagerMenus(HttpServletRequest request,
			ModelMap map) {
		try {
			String menutype = request.getParameter("menutype");
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("menutype", menutype);
			List<MenuValue> menus = this.webSiteManager.getMenus(params);
			map.put("success", "true");
			map.put("treeNodes", menus);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ModelAndView("jsonView", map);
	}

	/**
	 * 到网站更新页面
	 * 
	 * @param map
	 * @return
	 */

	@RequestMapping("/sitemanagerindex.do")
	public ModelAndView managerindex(HttpServletRequest request, ModelMap map) {
		UserValue uservalue = (UserValue) request.getSession().getAttribute(
				"user");
		if (uservalue == null) {// 未登陆则跳专至登陆界面
			return new ModelAndView("userspace/login", map);
		} else if (uservalue.getUserkind().intValue() == 1) {// 如果是普通用户则跳转至博客
			return new ModelAndView("redirect:gouserindex.do");
		} else {// 管理员则到网站管理界面
			map.put("userValue", uservalue);
			return new ModelAndView("sitemanager/sitemanagerindex", map);
		}

	}

	/**
	 * 获取用户上传的文章列表
	 * 
	 * @param map
	 * @return
	 */
	@RequestMapping("/articlelist.do")
	public ModelAndView articleList(HttpServletRequest request, ModelMap map,
			QueryDocForm queryDocForm) {
		// 从后台把所有需要编辑和审核的文章列表
		try {
			Map<String, Object> queryDocParams = new HashMap<String, Object>();
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
				if (queryDocForm.getChannel() != null
						&& queryDocForm.getChannel().equalsIgnoreCase(
								StaticConstants.SEL_TYPE90)) {
					queryDocParams.put("toptype", 4);
				} else if (queryDocForm.getChannel() != null
						&& queryDocForm.getChannel().equalsIgnoreCase(
								StaticConstants.SEL_TYPE91)) {
					queryDocParams.put("toptype", 1);
				} else if (queryDocForm.getChannel() != null
						&& queryDocForm.getChannel().equalsIgnoreCase(
								StaticConstants.SEL_TYPE92)) {
					queryDocParams.put("toptype", 2);
				} else if (queryDocForm.getChannel() != null
						&& queryDocForm.getChannel().equalsIgnoreCase(
								StaticConstants.SEL_TYPE93)) {
					queryDocParams.put("toptype", 3);
				} else {
					queryDocParams.put("channelid", queryDocForm.getChannel());
				}

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
			Integer startindex = ((queryDocForm.getPageindex() - 1) * 27);
			queryDocParams.put("displaydoccount", 27);
			queryDocParams.put("startindex", startindex);
			queryDocParams.put("doctype", StaticConstants.DOCTYPE_DOC);
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
		return new ModelAndView("sitemanager/articlelist", map);
	}

	/**
	 * 对用户提交的文章进行分组
	 * 
	 * @param request
	 * @param map
	 * @return
	 */
	@RequestMapping("/groupinguserdoc.do")
	public ModelAndView groupingUserDoc(HttpServletRequest request, ModelMap map) {
		String docids = request.getParameter("docids");
		String channelid = request.getParameter("channelid");
		String ids[] = docids.split(",");
		try {
			this.webSiteManager.doGroupingDoc(channelid, ids);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		// 刷新已经分组的数据
		return new ModelAndView("jsonView", map);
	}

	/**
	 * 生成网站页面
	 * 
	 * @param request
	 * @param response
	 * @param map
	 * @return
	 */
	@RequestMapping("/createsitepage.do")
	public ModelAndView createSitePage(HttpServletRequest request,
			HttpServletResponse response, ModelMap map) {
		// excellentDocs
		try {
			CustomFreeMarkerConfigurer customFreeMarkerConfigurer = getTemplateLoder(request);
			String path = request.getSession().getServletContext()
					.getRealPath("/");
			String contentpath = request.getContextPath();
			this.webSiteManager.docreateSitePage(path,
					customFreeMarkerConfigurer, contentpath);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("sitemanager/sitemanagerindex", map);
	}

	/**
	 * 审核内容
	 * 
	 * @param request
	 * @param response
	 * @param map
	 * @return
	 */
	@RequestMapping("/checkdocument.do")
	public ModelAndView checkDocument(HttpServletRequest request,
			HttpServletResponse response, ModelMap map) {
		String docids = request.getParameter("docids");
		String ids[] = docids.split(",");
		try {
			this.webSiteManager.doCheckDocument(ids, new Integer(2));
			map.put("success", 1);
		} catch (Exception ex) {
			ex.printStackTrace();
			map.put("success", 0);
			return new ModelAndView("error", map);
		}
		return new ModelAndView("jsonView", map);
	}

	/**
	 * 选择为优秀作品
	 * 
	 * @param request
	 * @param response
	 * @param map
	 * @return
	 */
	@RequestMapping("/astopwriting.do")
	public ModelAndView asTopWriting(HttpServletRequest request,
			TopDocumentValue topDocumentValue, ModelMap map) {
		UserValue uservalue = (UserValue) request.getSession().getAttribute(
				"user");
		try {
			String path = request.getSession().getServletContext()
					.getRealPath("/html/img");
			String imgname = "altan_" + UUIDMaker.getUUID() + ".jpg";
			if (topDocumentValue.getPlayimg() != null
					&& topDocumentValue.getPlayimg().length != 0) {
				ImgeUtil.CompressPic(topDocumentValue.getPlayimg(), path,
						imgname,true);
				topDocumentValue.setDocimg(imgname);
			}
			this.webSiteManager.doCreateTopDocument(topDocumentValue);
		} catch (Exception ex) {
			ex.printStackTrace();
			return new ModelAndView("error", map);
		}
		return new ModelAndView("forward:articlelist.do", map);
	}

	@RequestMapping("/setvideoface.do")
	public ModelAndView setvideoface(HttpServletRequest request,
			TopDocumentValue topDocumentValue, ModelMap map) {
		UserValue uservalue = (UserValue) request.getSession().getAttribute(
				"user");
		String imgname = "";
		try {
			String path = request.getSession().getServletContext()
					.getRealPath("/html/img");
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			MultiValueMap file = multipartRequest.getMultiFileMap();
			Set<String> set = file.keySet();
			Iterator iterator = set.iterator();
			while (iterator.hasNext()) {
				String name = (String) iterator.next();
				List files = (List) file.get(name);
				for (int i = 0; i < files.size(); i++) {
					CommonsMultipartFile commonsMultipartFile = (CommonsMultipartFile) files
							.get(i);
					String OriginalFilename = commonsMultipartFile
							.getOriginalFilename();
					imgname = UUIDMaker.getUUID() + OriginalFilename;
					ImgeUtil.CompressPic(commonsMultipartFile.getBytes(), path,
							imgname,true);
				}
			}
			this.webSiteManager.setVideoface(topDocumentValue.getVideoid(),
					"html/img/" + imgname);
		} catch (Exception ex) {
			ex.printStackTrace();
			return new ModelAndView("error", map);
		}
		return new ModelAndView("forward:articlelist.do", map);
	}

	@RequestMapping("/userlist.do")
	public ModelAndView userList(HttpServletRequest request, ModelMap map,
			QueryUserForm queryUserForm) {
		try {
			if (queryUserForm.getPageindex() == null) {
				queryUserForm.setPageindex(1);
			}
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
				} else if (i + 1 == queryUserForm.getPageindex()) {
					indexs.add(pagingIndex);
					pagingIndex.setCurrent(1);
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
		return new ModelAndView("sitemanager/userlist", map);
	}

	/**
	 * 停用用户
	 * 
	 * @param request
	 * @param map
	 * @return
	 */

	@RequestMapping("/stopuser.do")
	public ModelAndView doStopUser(HttpServletRequest request, ModelMap map) {
		String userids = request.getParameter("userids");
		try {
			String users[] = userids.split(",");
			userManager.doStopUser(users);
			map.put("code", "1");
			map.put("mess", "");
			map.put("success", "true");
		} catch (Exception ex) {
			map.put("code", "2");
			map.put("mess", ex.getMessage());
			ex.printStackTrace();
		}
		return new ModelAndView("jsonView", map);
	}

	/**
	 * 启用用户
	 * 
	 * @param request
	 * @param map
	 * @return
	 */
	@RequestMapping("/reuseuser.do")
	public ModelAndView reUseUser(HttpServletRequest request, ModelMap map) {
		String userids = request.getParameter("userids");
		try {
			String users[] = userids.split(",");
			userManager.doReUseUser(users);
			map.put("code", "1");
			map.put("mess", "");
			map.put("success", "true");
		} catch (Exception ex) {
			map.put("code", "1");
			map.put("mess", ex.getMessage());
			ex.printStackTrace();
		}
		return new ModelAndView("jsonView", map);
	}

	/**
	 * 删除用户
	 * 
	 * @param request
	 * @param map
	 * @return
	 */
	@RequestMapping("/deleteuser.do")
	public ModelAndView deleteUser(HttpServletRequest request, ModelMap map) {
		String userids = request.getParameter("userids");
		try {
			String users[] = userids.split(",");
			userManager.doDeleteUser(users);
			map.put("code", "1");
			map.put("mess", "");
			map.put("success", "true");
		} catch (Exception ex) {
			ex.printStackTrace();
			map.put("code", "1");
			map.put("mess", ex.getMessage());
		}
		return new ModelAndView("jsonView", map);
	}

	/**
	 * 获取用户上传的意见建议
	 * 
	 * @param request
	 * @param map
	 * @return
	 */
	@RequestMapping("/getopinions.do")
	public ModelAndView getopinions(HttpServletRequest request,
			QueryOpinionFrom queryOpinionFrom, ModelMap map) {
		try {
			List<OpinionValue> opinionValues = this.webSiteManager
					.getopinions(queryOpinionFrom);
			map.put("opinionValues", opinionValues);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ModelAndView("sitemanager/opinions", map);
	}

	@RequestMapping("/viewopinion.do")
	public ModelAndView viewopinion(HttpServletRequest request,
			QueryOpinionFrom queryOpinionFrom, ModelMap map) {
		try {
			List<OpinionValue> opinionValues = this.webSiteManager
					.getopinions(queryOpinionFrom);
			OpinionValue opinionValue = opinionValues.get(0);
			map.put("opinion", opinionValue);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ModelAndView("sitemanager/viewoptinion", map);
	}

	@RequestMapping("/getOpinionContent.do")
	public ModelAndView getOpinionContent(HttpServletRequest request,
			QueryOpinionFrom queryOpinionFrom, ModelMap map) {
		try {
			List<OpinionValue> opinionValues = this.webSiteManager
					.getopinions(queryOpinionFrom);
			OpinionValue opinionValue = opinionValues.get(0);
			opinionValue.setHtmlstr(new String(opinionValue.getContent()));
			map.put("opinion", opinionValue);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ModelAndView("jsonView", map);
	}

	/**
	 * 保存意见建议
	 * 
	 * @param request
	 * @param map
	 * @return
	 */
	@RequestMapping("/addopinions.do")
	public ModelAndView addopinions(HttpServletRequest request,
			OpinionValue opinionValue, ModelMap map) {
		try {
			Object uservalue = request.getSession().getAttribute("user");
			if (uservalue != null) {
				String userid = ((UserValue) uservalue).getUserid();
				opinionValue.setUserid(userid);
				opinionValue.setArtname(((UserValue) uservalue).getArtname());
			}
			SamplePostData samplePostData = new SamplePostData(request);
			String content = samplePostData.getAllFormFieldsAndValues();
			opinionValue.setContent(content.getBytes());
			this.webSiteManager.doAaddopinions(opinionValue);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ModelAndView("redirect:getopinions.do", map);
	}

	@RequestMapping("/toaddopinion.do")
	public ModelAndView toAddopinion(HttpServletRequest request, ModelMap map) {
		return new ModelAndView("sitemanager/createopinion", map);
	}

	/**
	 * 获取 通告，广告
	 * 
	 * @param request
	 * @param map
	 * @return
	 */
	@RequestMapping("/advert.do")
	public ModelAndView getadverts(HttpServletRequest request, ModelMap map) {
		return new ModelAndView("sitemanager/adverts", map);
	}

	/**
	 * 添加通告，广告
	 * 
	 * @param request
	 * @param map
	 * @return
	 */
	@RequestMapping("/addadvert.do")
	public ModelAndView addadvert(HttpServletRequest request, ModelMap map) {
		return new ModelAndView("sitemanager/adverts", map);
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
	@RequestMapping("/pagingimglist.do")
	public ModelAndView pagingImgList(HttpServletRequest request, ModelMap map) {
		try {
			String pageindex = request.getParameter("pageindex");
			String imgstatus = request.getParameter("imgstatus");
			PaingModel<DocumentValue> paingModel = new PaingModel<DocumentValue>();
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
			if (imgstatus != null && !imgstatus.equalsIgnoreCase("")) {
				try {
					paingModel.setDocstatus(Integer.parseInt(imgstatus));
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
			// paingModel.setDocstatus(StaticConstants.DOCSTATUS1);
			PaingModel<DocumentValue> pageModel = webSiteVisitorManager
					.pagingquerydoc(paingModel);
			map.put("imgList", pageModel.getModelList());
			if (pageModel.getModelList().isEmpty()) {
				map.put("isempty", 1);
			} else {
				map.put("isempty", 0);
			}
			String idAndIndexrel = "";
			List<DocumentValue> docs = pageModel.getModelList();
			for (int i = 0; i < docs.size(); i++) {
				idAndIndexrel = idAndIndexrel + (i + 1) + ","
						+ docs.get(i).getDocid() + "#";
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
			map.put("imgstatus", imgstatus);
			String linkstr = PageUtil.getPagingImgLink(pageModel, 1);
			map.put("linkstr", linkstr);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ModelAndView("sitemanager/checkphoto", map);
	}

	@RequestMapping("/commentlist.do")
	public ModelAndView commentlist(HttpServletRequest request, ModelMap map,
			QueryDocForm queryDocForm) {
		try {
			Map<String, Object> queryParams = new HashMap<String, Object>();
			queryParams.put("resourcekind", StaticConstants.RESOURCE_TYPE_DOC);
			queryParams.put("artname", queryDocForm.getAuthorname());
			queryParams.put("status", queryDocForm.getStatus());
			queryParams.put("strcrtime", queryDocForm.getStrcrtime());
			queryParams.put("endcrtime", queryDocForm.getEndcrtime());
			PaingModel<MessageValue> messagePaingModel = this.webResourceManager
					.paingQueryComment(queryParams, 30,
							queryDocForm.getPageindex());
			map.put("messagePaingModel", messagePaingModel);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ModelAndView("sitemanager/commentlist", map);
	}

	@RequestMapping("/synuserdata.do")
	public ModelAndView synuserdata(HttpServletRequest request, ModelMap map,
			QueryDocForm queryDocForm) {
		try {
			String path = request.getSession().getServletContext()
					.getRealPath("/html/userhead");
			this.webResourceManager.synOldUser(path);
			this.webResourceManager.synOldDoc();
			// this.webResourceManager.synOldMess();
			path = request.getSession().getServletContext()
					.getRealPath("/html/img");
			this.webResourceManager.synOldImg(path);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ModelAndView("redirect:index.do", map);
	}

	@RequestMapping("/addSelectedDocs.do")
	public ModelAndView addSelectedDocs(HttpServletRequest request,
			HttpServletResponse response, ModelMap map) {
		String docids = request.getParameter("docids");
		String type = request.getParameter("type");
		String ids[] = docids.split(",");
		try {
			this.webSiteManager.addSelectedDocs(ids, type);
			map.put("success", 1);
		} catch (Exception ex) {
			ex.printStackTrace();
			map.put("success", 0);
			return new ModelAndView("error", map);
		}
		return new ModelAndView("jsonView", map);
	}

	@RequestMapping("/delSelectedDocs.do")
	public ModelAndView delSelectedDocs(HttpServletRequest request,
			HttpServletResponse response, ModelMap map) {
		String docids = request.getParameter("docids");
		String ids[] = docids.split(",");
		try {
			this.webSiteManager.deleteTopDocument(ids);
			map.put("success", 1);
		} catch (Exception ex) {
			ex.printStackTrace();
			map.put("success", 0);
			return new ModelAndView("error", map);
		}
		return new ModelAndView("jsonView", map);
	}

	@RequestMapping("/booklist.do")
	public ModelAndView querybooks(HttpServletRequest request, ModelMap map) {
		try {
			List<BookStoreValue> books = this.bookStoreManagerImpl
					.queryBookStoreValues(new HashMap<String, Object>());
			map.put("books", books);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ModelAndView("sitemanager/booklist", map);
	}

	@RequestMapping("/addbook.do")
	public ModelAndView addbook(HttpServletRequest request,
			BookStoreValue bookStoreValue, ModelMap map) {
		try {
			String path = request.getSession().getServletContext()
					.getRealPath("/html/img");
			String imgid = UUIDMaker.getUUID();
			String imgname = imgid + ".jpg";
			File imgfile = new File(path, imgname);
			FileOutputStream outStream = new FileOutputStream(imgfile);
			outStream.write(bookStoreValue.getBookimgcon());
			outStream.close();
			bookStoreValue.setBookid(imgid);
			bookStoreValue.setBookimg(imgname);
			this.bookStoreManagerImpl.addBookStore(bookStoreValue);
		} catch (Exception ex) {
			ex.printStackTrace();
			return new ModelAndView("error", map);
		}
		return new ModelAndView("redirect:booklist.do", map);
	}

	@RequestMapping("/delbooks.do")
	public ModelAndView delbooks(HttpServletRequest request,
			HttpServletResponse response, ModelMap map) {
		String bookids = request.getParameter("bookids");
		// String ids[] = bookids.split(",");
		try {
			this.bookStoreManagerImpl.deletebook(bookids);
			map.put("success", 1);
		} catch (Exception ex) {
			ex.printStackTrace();
			map.put("success", 0);
			return new ModelAndView("error", map);
		}
		return new ModelAndView("jsonView", map);
	}

	@RequestMapping("/selectedBook.do")
	public ModelAndView selectedBook(HttpServletRequest request,
			HttpServletResponse response, ModelMap map) {
		String docids = request.getParameter("bookids");
		String ids[] = docids.split(",");
		try {
			this.webSiteManager.addSelectedDocs(ids, ""
					+ StaticConstants.TOP_TYPE8);
			map.put("success", 1);
		} catch (Exception ex) {
			ex.printStackTrace();
			map.put("success", 0);
			return new ModelAndView("error", map);
		}
		return new ModelAndView("jsonView", map);
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

}
