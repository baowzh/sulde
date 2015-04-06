package com.mongolia.website.manager.impls;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mongolia.website.controller.freemarker.CustomFreeMarkerConfigurer;
import com.mongolia.website.dao.interfaces.UserManagerDao;
import com.mongolia.website.dao.interfaces.WebResourceDao;
import com.mongolia.website.dao.interfaces.WebSiteManagerDao;
import com.mongolia.website.manager.interfaces.WebSiteManager;
import com.mongolia.website.manager.interfaces.WebSiteVisitorManager;
import com.mongolia.website.model.Channel;
import com.mongolia.website.model.DocumentValue;
import com.mongolia.website.model.MenuValue;
import com.mongolia.website.model.OpinionValue;
import com.mongolia.website.model.PaingModel;
import com.mongolia.website.model.ProgramItem;
import com.mongolia.website.model.ProgramValue;
import com.mongolia.website.model.QueryOpinionFrom;
import com.mongolia.website.model.QueryUserForm;
import com.mongolia.website.model.TopDocumentValue;
import com.mongolia.website.model.UserValue;
import com.mongolia.website.util.StaticConstants;
import com.mongolia.website.util.UUIDMaker;

import freemarker.template.Template;

@Service("webSiteManager")
public class WebSiteManagerImpl implements WebSiteManager {
	@Autowired
	private WebSiteManagerDao WebSiteManagerDao;
	@Autowired
	// private WebPageManager webPageManager;
	private WebSiteVisitorManager webSiteVisitorManager;
	@Autowired
	private UserManagerDao userManagerDao;
	@Autowired
	private WebResourceDao webResourceDao;

	@Override
	public List<ProgramItem> getProgramItemList(Map<String, Object> params)
			throws Exception {
		// TODO Auto-generated method stub
		return WebSiteManagerDao.getProgramItemList(params);
	}

	@Override
	public List<ProgramValue> getProgramList(Map<String, Object> params)
			throws Exception {
		// TODO Auto-generated method stub
		return WebSiteManagerDao.getProgramList(params);
	}

	@Override
	public void addNews(ProgramItem item) throws Exception {
		// TODO Auto-generated method stub
		this.WebSiteManagerDao.addNews(item);

	}

	@Override
	public void addProgram(ProgramValue programValue) throws Exception {
		this.WebSiteManagerDao.addProgram(programValue);

	}

	@Override
	public void deleteProgramItem(Map<String, String> params) throws Exception {
		this.WebSiteManagerDao.deleteProgramItem(params);

	}

	@Override
	public List<MenuValue> getMenus(Map<String, Object> params)
			throws Exception {
		// TODO Auto-generated method stub
		return this.WebSiteManagerDao.getMenus(params);
	}

	@Override
	public Map<String, Object> getDocuments(Map<String, Object> params)
			throws Exception {
		// TODO Auto-generated method stub
		List<DocumentValue> docs = WebSiteManagerDao.getDocuments(params);
		Integer doccount = WebSiteManagerDao.getDocumentsCount(params);
		int pageCount = doccount / 30;
		if (doccount % 30 > 0) {
			pageCount = pageCount + 1;
		}
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("docs", docs);
		result.put("pageCount", pageCount);
		result.put("doccount", doccount);

		return result;
	}

	@Override
	public List<Channel> getDistinctDocChannels(Map<String, Object> params)
			throws Exception {
		// TODO Auto-generated method stub
		return WebSiteManagerDao.getDistinctDocChannels(params);
	}

	@Override
	public void doGroupingDoc(String channelid, String[] docids)
			throws Exception {
		// TODO Auto-generated method stub
		for (int i = 0; i < docids.length; i++) {
			String docid = docids[i];
			WebSiteManagerDao.groupingdoc(channelid, docid);
		}
	}

	@Override
	public void docreateSitePage(String htmlpath,
			CustomFreeMarkerConfigurer customFreeMarkerConfigurer,
			String contentpath) throws Exception {
		// 获取主页信息并传给模板引擎
		Map<String, Object> indexPageContent = webSiteVisitorManager
				.getIndexContent(htmlpath);
		Template t = customFreeMarkerConfigurer.getConfiguration().getTemplate(
				"zymb1");
		File htmFile = new File(htmlpath, "index.html");
		Writer out = new OutputStreamWriter(new FileOutputStream(htmFile),
				"utf-8");
		// 为了加快首页加载速度生成图片新闻和用户头像列表
		List<UserValue> topUsers = (List<UserValue>) indexPageContent
				.get("topUsers");
		List<UserValue> newUsers = (List<UserValue>) indexPageContent
				.get("newUsers");
		File htmimgFile = new File(htmlpath, "html");
		File imgFile = new File(htmimgFile, "img");
		htmimgFile.mkdir();
		imgFile.mkdir();
		// for (UserValue userValue : topUsers) {
		// byte imgsm[] = userValue.getHeadimgsm();
		// if(imgsm!=null){
		// File imgFilei = new File(imgFile, userValue.getUserid() + ".jpg");
		// FileOutputStream stream = new FileOutputStream(imgFilei);
		// stream.write(imgsm);
		// stream.close();
		// }else{
		// if(userValue.getSex()!=null&&userValue.getSex().intValue()==1){
		//
		//
		// }else if(userValue.getSex()!=null&&userValue.getSex().intValue()==0){
		//
		//
		// }
		//
		// }
		//
		// }
		// for (UserValue userValue : newUsers) {
		// byte imgsm[] = userValue.getHeadimgsm();
		// File imgFilei = new File(imgFile, userValue.getUserid() + ".jpg");
		// FileOutputStream stream = new FileOutputStream(imgFilei);
		// if(imgsm!=null){
		// stream.write(imgsm);
		// }
		//
		// stream.close();
		// }
		Map<String, Object> jstempData = new HashMap<String, Object>();
		jstempData.put("pics1", indexPageContent.get("pics"));
		// 根据topdocuments 生成js脚本文件
		Template t1 = customFreeMarkerConfigurer.getConfiguration()
				.getTemplate("imgnews");
		File imgnews = new File(htmlpath, "imgnews.js");
		Writer out1 = new OutputStreamWriter(new FileOutputStream(imgnews),
				"utf-8");
		t1.process(jstempData, out1);
		out1.flush();
		out1.close();
		Map<String, Object> rootMap = new HashMap<String, Object>();
		rootMap.put("indexPageContent", indexPageContent);
		rootMap.put("contentpath", "http://127.0.0.1:8080/mongoliawebsite");
		t.process(rootMap, out);
		out.flush();
		out.close();

	}

	/**
	 * 生成文章明细
	 * 
	 * @param htmlpath
	 * @param customFreeMarkerConfigurer
	 * @param documentValue
	 */
	// private void createDocDetail(String htmlpath,
	// CustomFreeMarkerConfigurer customFreeMarkerConfigurer,
	// DocumentValue documentValue) throws Exception {
	// Template t = customFreeMarkerConfigurer.getConfiguration().getTemplate(
	// "docdetail.ftl");
	// Map<String, Object> pageData = new HashMap<String, Object>();
	// // htmlstr
	// documentValue.setHtmlstr(new String(documentValue.getDoccontent()));
	// pageData.put("documentValue", documentValue);
	// File htmFile = new File(htmlpath, documentValue.getDocid() + ".html");
	// // 对图片的文职进行修正
	// String htmlstr = documentValue.getHtmlstr().replaceAll("html/img/",
	// "img/");
	// documentValue.setHtmlstr(htmlstr);
	// Writer out = new OutputStreamWriter(new FileOutputStream(htmFile),
	// "utf-8");
	// t.process(pageData, out);
	// out.flush();
	// out.close();
	// }
	@Override
	public void doCheckDocument(String[] docids, Integer status)
			throws Exception {
		// TODO Auto-generated method stub
		for (int i = 0; i < docids.length; i++) {
			Map<String, Object> queryParams = new HashMap<String, Object>();
			queryParams.put("docid", docids[i]);
			queryParams.put("startindex", 0);
			queryParams.put("displaydoccount", 1);
			List<DocumentValue> doces = this.WebSiteManagerDao
					.getDocuments(queryParams);
			if (doces == null || doces.isEmpty()) {
				continue;
			}
			DocumentValue documentValue = doces.get(0);
			// if (documentValue.getDocstatus().intValue() == 1) {
			this.WebSiteManagerDao.checkDocument(documentValue.getDocid(),
					status);
			// }
		}
	}

	@Override
	public void doCreateTopDocument(TopDocumentValue topDocumentValue)
			throws Exception {
		// TODO Auto-generated method stub
		// 选择有优秀作品
		if (topDocumentValue.getToptype().intValue() == StaticConstants.TOP_TYPE1) {// 成为当前星期的作品
			Calendar cal = Calendar.getInstance(java.util.Locale.CHINA);
			int weekeay = cal.get(Calendar.DAY_OF_WEEK);
			long beginDisplaytiime = cal.getTime().getTime() - (weekeay - 1)
					* 24 * 60 * 60 * 1000;
			long endDisplaytiime = cal.getTime().getTime()
					+ (7 - (weekeay - 1)) * 24 * 60 * 60 * 1000;
			Date begindate = new Date(beginDisplaytiime);
			Date endDate = new Date(endDisplaytiime);
			topDocumentValue.setStartdate(begindate);
			topDocumentValue.setEnddate(endDate);
		} else if (topDocumentValue.getToptype().intValue() == StaticConstants.TOP_TYPE2) {// 成为当前月份的作品
			Calendar cal = Calendar.getInstance(java.util.Locale.CHINA);
			int MaxDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
			int monthaay = cal.get(Calendar.DAY_OF_MONTH);
			long beginDisplaytiime = cal.getTime().getTime() - monthaay * 24
					* 60 * 60 * 1000;
			long endDisplaytiime = cal.getTime().getTime()
					+ (MaxDay - monthaay) * 24 * 60 * 60 * 1000;
			Date begindate = new Date(beginDisplaytiime);
			Date endDate = new Date(endDisplaytiime);
			topDocumentValue.setStartdate(begindate);
			topDocumentValue.setEnddate(endDate);

		} else {// 成为当年的优秀作品
			Calendar cal = Calendar.getInstance(java.util.Locale.CHINA);
			int year = cal.get(Calendar.YEAR);
			String beainDateStr = year + "-01-01";
			String endDateStr = year + "-12-31";
			java.text.SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
					"yyyy-MM-dd");
			Date beainDate = simpleDateFormat.parse(beainDateStr);
			Date endDate = simpleDateFormat.parse(endDateStr);
			topDocumentValue.setStartdate(beainDate);
			topDocumentValue.setEnddate(endDate);
		}
		this.WebSiteManagerDao.createTopDocument(topDocumentValue);

	}

	@Override
	public PaingModel<UserValue> getUsers(QueryUserForm queryUserForm)
			throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> queryparams = new HashMap<String, Object>();
		if (queryUserForm.getQx() != null
				&& !queryUserForm.getQx().equalsIgnoreCase("")) {
			queryparams.put("qx", queryUserForm.getQx());
		}
		if (queryUserForm.getQx() != null
				&& queryUserForm.getQx().equalsIgnoreCase("#")) {
			queryparams.put("qx", null);
		}
		if (queryUserForm.getDistrict() != null
				&& !queryUserForm.getDistrict().equalsIgnoreCase("")) {
			queryparams.put("district", queryUserForm.getDistrict());
		}
		if (queryUserForm.getDistrict() != null
				&& queryUserForm.getDistrict().equalsIgnoreCase("#")) {
			queryparams.put("district", null);
		}

		if (queryUserForm.getUsername() != null
				&& !queryUserForm.getUsername().equalsIgnoreCase("")) {
			queryparams.put("likeusername", queryUserForm.getUsername());
		}
		queryparams.put("strregtime", queryUserForm.getStrregtime());
		if (queryUserForm.getStrregtime() != null
				&& queryUserForm.getStrregtime().equalsIgnoreCase("")) {
			queryparams.put("strregtime", null);
		}
		queryparams.put("endregtime", queryUserForm.getEndregtime());
		if (queryUserForm.getEndregtime() != null
				&& queryUserForm.getEndregtime().equalsIgnoreCase("")) {
			queryparams.put("endregtime", null);
		}
		if (queryUserForm.getProfessioncode() != null
				&& !queryUserForm.getProfessioncode().equalsIgnoreCase("")) {
			queryparams
					.put("professioncode", queryUserForm.getProfessioncode());
		}
		queryparams.put("userkind", StaticConstants.USER_KIND1);
		// 计算
		if (queryUserForm.getPageindex() == null
				|| queryUserForm.getPageindex() == 0) {
			queryUserForm.setPageindex(1);
		}
		Integer startindex = (queryUserForm.getPagesize() * (queryUserForm
				.getPageindex() - 1));
		queryparams.put("startindex", startindex);
		queryparams.put("fetchcount", queryUserForm.getPagesize());
		PaingModel<UserValue> paingUser = new PaingModel<UserValue>();
		paingUser.setModelList(this.userManagerDao.paingQueryUser(queryparams));
		Integer userCount = this.userManagerDao.paingUserCount(queryparams);
		int pageCount = userCount / queryUserForm.getPagesize();
		paingUser.setRowcount(userCount.toString());
		if (userCount % queryUserForm.getPagesize() > 0) {
			pageCount = pageCount + 1;
		}
		paingUser.setPagecount(pageCount);
		return paingUser;
	}

	@Override
	public void doAaddopinions(OpinionValue opinionValue) throws Exception {
		// TODO Auto-generated method stub
		// 校验数据的完整性并保存
		opinionValue.setOpinionid(UUIDMaker.getUUID());
		opinionValue.setUploaddate(new Date());
		opinionValue.setReaded(0);
		this.WebSiteManagerDao.addopinions(opinionValue);

	}

	@Override
	public List<OpinionValue> getopinions(QueryOpinionFrom queryOpinionFrom)
			throws Exception {
		// TODO Auto-generated method stub
		return this.WebSiteManagerDao.getopinions(queryOpinionFrom);
	}

	@Override
	public void addSelectedDocs(String[] ids, String type) throws Exception {
		// TODO Auto-generated method stub
		// 1.判断是否已经存在于选择作品列表，存在则修改选送时间
		String docids = "";
		for (String id : ids) {
			docids = docids + "'" + id + "',";
		}
		if (!docids.equalsIgnoreCase("")) {
			docids = docids.substring(0, docids.length() - 1);
		}
		Map<String, Object> checkparams = new HashMap<String, Object>();
		checkparams.put("docids", docids);
		checkparams.put("toptypes", type);
		List<DocumentValue> docs = this.webResourceDao.getDocList(checkparams);
		// 已经存在的直接修改时间
		List<String> filterids = new ArrayList<String>();
		for (DocumentValue documentValue : docs) {
			filterids.add(documentValue.getDocid());
			Map<String, Object> updParams = new HashMap<String, Object>();
			updParams.put("docid", documentValue.getDocid());
			updParams.put("startdate", new Date());
			Calendar calendar = java.util.Calendar.getInstance();
			calendar.add(Calendar.DAY_OF_MONTH, 7);
			updParams.put("enddate", calendar.getTime());
			this.webResourceDao.updTopDocument(updParams);
		}
		// 2.不存在则新建一个选送记录
		for (String docid : ids) {
			if (!filterids.contains(docid)) {
				TopDocumentValue documentValue = new TopDocumentValue();
				documentValue.setDocid(docid);
				documentValue.setToptype(Integer.parseInt(type));
				documentValue.setStartdate(new Date());
				Calendar calendar = java.util.Calendar.getInstance();
				calendar.add(Calendar.DAY_OF_MONTH, 7);
				documentValue.setEnddate(calendar.getTime());
				documentValue.setPlayindex(1);
				this.WebSiteManagerDao.createTopDocument(documentValue);
			}
		}
	}

	@Override
	public void deleteTopDocument(String[] ids) throws Exception {
		// TODO Auto-generated method stub
		String docids = "";
		for (String id : ids) {
			docids = docids + "'" + id + "',";
		}
		if (!docids.equalsIgnoreCase("")) {
			docids = docids.substring(0, docids.length() - 1);
		}
		this.WebSiteManagerDao.deleteTopDocument(docids);
	}

	@Override
	public void setVideoface(String docid, String imgpath) throws Exception {
		// TODO Auto-generated method stub
		this.WebSiteManagerDao.setVideoface(docid, imgpath);

	}

}
