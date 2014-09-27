package com.mongolia.website.controler;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.mongolia.website.controler.ckeditor.SamplePostData;
import com.mongolia.website.controler.freemarker.CustomFreeMarkerConfigurer;
import com.mongolia.website.manager.interfaces.UserManager;
import com.mongolia.website.manager.interfaces.WebSiteManager;
import com.mongolia.website.manager.interfaces.WebSiteVisitorManager;
import com.mongolia.website.model.MenuValue;
import com.mongolia.website.model.OpinionValue;
import com.mongolia.website.model.PagingIndex;
import com.mongolia.website.model.PaingModel;
import com.mongolia.website.model.PaingUser;
import com.mongolia.website.model.QueryDocForm;
import com.mongolia.website.model.QueryOpinionFrom;
import com.mongolia.website.model.QueryUserForm;
import com.mongolia.website.model.TopDocumentValue;
import com.mongolia.website.model.UserValue;
import com.mongolia.website.util.StaticConstants;

/**
 * 网站管理
 * 
 * @author baowzh
 */
@Controller
public class WebSiteManagerAction implements PaingActionIA {
	@Autowired
	private WebSiteManager webSiteManager;
	@Autowired
	private UserManager userManager;
	@Autowired
	private WebSiteVisitorManager webSiteVisitorManager;

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

	@RequestMapping("/pagingdata.do")
	@Override
	public ModelAndView pagingData(PaingModel model, ModelMap map) {
		// TODO Auto-generated method stub
		System.out.println(" 分页查询方法1");
		return new ModelAndView("jsonView", map);
	}

	@RequestMapping("/refreshdata.do")
	@Override
	public ModelAndView refreshData(PaingModel model, ModelMap map) {
		// TODO Auto-generated method stub
		System.out.println(" 分页查询方法2");
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
	@RequestMapping("/checkdocument.do")
	public ModelAndView checkDocument(HttpServletRequest request,
			HttpServletResponse response, ModelMap map) {
		String docids = request.getParameter("docids");
		String ids[] = docids.split(",");
		try {
			this.webSiteManager.doCheckDocument(ids, new Integer(1));
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
			//
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			MultiValueMap file = multipartRequest.getMultiFileMap();
			Set<String> set = file.keySet();
			Iterator iterator = set.iterator();
			while (iterator.hasNext()) {
				String name = (String) iterator.next();
				List<CommonsMultipartFile> files = (List<CommonsMultipartFile>) file
						.get(name);
				for (int i = 0; i < files.size(); i++) {
					CommonsMultipartFile commonsMultipartFile = (CommonsMultipartFile) files
							.get(i);
					topDocumentValue.setDocimg(commonsMultipartFile.getBytes());
				}
			}
			this.webSiteManager.doCreateTopDocument(topDocumentValue);
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
				} else if (i + 1 == queryUserForm.getPageindex()) {
					indexs.add(pagingIndex);
					pagingIndex.setCurrent(1);
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
			PaingModel paingModel = new PaingModel();
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
			paingModel.setDocstatus(StaticConstants.DOCSTATUS1);
			PaingModel pageModel = webSiteVisitorManager
					.pagingquerydoc(paingModel);
			map.put("imgList", pageModel.getDocList());
			if (pageModel.getDocList().isEmpty()) {
				map.put("isempty", 1);
			} else {
				map.put("isempty", 0);
			}
			String idAndIndexrel = "";
			for (int i = 0; i < pageModel.getDocList().size(); i++) {
				idAndIndexrel = idAndIndexrel + (i + 1) + ","
						+ pageModel.getDocList().get(i).getDocid() + "#";
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
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ModelAndView("sitemanager/checkphoto", map);
	}

}
