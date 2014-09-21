package com.mongolia.website.manager.impls;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mongolia.website.dao.interfaces.ChannelManagerDao;
import com.mongolia.website.dao.interfaces.WebPageManagerDao;
import com.mongolia.website.dao.interfaces.WebResourceDao;
import com.mongolia.website.dao.interfaces.WebSiteVisitorDao;
import com.mongolia.website.manager.interfaces.WebSiteVisitorManager;
import com.mongolia.website.model.DocumentValue;
import com.mongolia.website.model.ImgNew;
import com.mongolia.website.model.ImgValue;
import com.mongolia.website.model.PageChannelRelationValue;
import com.mongolia.website.model.PaingModel;
import com.mongolia.website.model.ProgramItem;
import com.mongolia.website.model.ProgramValue;
import com.mongolia.website.model.TopDocumentValue;
import com.mongolia.website.model.UserValue;
import com.mongolia.website.util.StaticConstants;

@Service("webSiteVisitorManager")
public class WebSiteVisitorManagerImpl extends BaseManagerImpl implements
		WebSiteVisitorManager {
	@Autowired
	private WebSiteVisitorDao webSiteVisitorDao;
	@Autowired
	private ChannelManagerDao channelDao;
	@Autowired
	private WebResourceDao webResourceDao;
	@Autowired
	private WebPageManagerDao webPageManagerDao;

	@Override
	public List<ProgramValue> getProgramList() throws Exception {
		// TODO Auto-generated method stub
		return webSiteVisitorDao.getProgramList();
	}

	@Override
	public List<ProgramItem> getProgramItemList(Map<String, Object> params)
			throws Exception {
		// TODO Auto-generated method stub
		return this.webSiteVisitorDao.getProgramItemList(params);
	}

	@Override
	public ProgramItem getItemContent(Map<String, Object> params)
			throws Exception {
		// TODO Auto-generated method stub
		return this.webSiteVisitorDao.getItemContent(params);

	}

	@Override
	public PaingModel pagingquerydoc(PaingModel paingModel) throws Exception {
		// TODO Auto-generated method stub
		// 组织查询参数进行查询
		paingModel.setStartrow((paingModel.getPageindex() - 1)
				* paingModel.getPagesize());
		paingModel.setEndrow(paingModel.getPagesize());
		List<DocumentValue> documents = this.webSiteVisitorDao
				.pagingquerydoc(paingModel);
		paingModel.setDocList(documents);
		Integer rowCount = this.webSiteVisitorDao.getRowCount(paingModel);
		paingModel.setRowcount("" + rowCount);
		int pageCount = rowCount / paingModel.getPagesize();
		if (rowCount % paingModel.getPagesize() > 0) {
			pageCount = pageCount + 1;
		}
		paingModel.setPagecount(pageCount);
		if (paingModel.getPageindex() < paingModel.getPagecount()) {
			paingModel.setNextindex(pageCount);
		} else {
			paingModel.setNextindex(paingModel.getPageindex() + 1);
		}
		if (paingModel.getPageindex() > 1) {
			paingModel.setPreviousindex(paingModel.getPageindex() - 1);
		} else {
			paingModel.setPreviousindex(1);
		}

		return paingModel;
	}

	@Override
	public Map<String, Object> getIndexContent(String htmlpath)
			throws Exception {
		// TODO Auto-generated method stub
		List<PageChannelRelationValue> relations = webPageManagerDao
				.getRelatedChannes("index");
		Map<String, Object> indexPageContent = new HashMap<String, Object>();
		for (int i = 0; i < relations.size(); i++) {
			PageChannelRelationValue channel = relations.get(i);
			PaingModel paingModel = new PaingModel();
			paingModel.setDocchannel(channel.getChannelid());
			paingModel.setPageindex(1);
			paingModel.setPageindex(StaticConstants.INDEX_DOC_ROWCOUNT);
			paingModel.setStartrow(0);
			paingModel.setEndrow(channel.getChanneldoccount());// fetchcount
			paingModel.setDocstatus(2);
			List<DocumentValue> documents = this.webSiteVisitorDao
					.pagingquerydoc(paingModel);
			indexPageContent.put(channel.getVariablename(), documents);
		}
		// 获取最近频繁发布内容的前十个用户列表(最多取2月之内的，2月之内的)
		Date endDate = new Date();
		int fechtime = 0;
		List<UserValue> topUsers = new ArrayList<UserValue>();
		while (fechtime < 30 && topUsers.size() <= 12) {
			Calendar calendar = java.util.Calendar.getInstance();
			calendar.setTime(new Date());
			calendar.add(Calendar.WEEK_OF_YEAR, 0 - fechtime);
			Date beginDate = calendar.getTime();
			List<UserValue> users = this.webSiteVisitorDao.getTopUsers(
					beginDate, endDate);
			List<UserValue> toDelUser = new ArrayList<UserValue>();
			for (UserValue user : users) {
				for (UserValue topUser : topUsers) {
					if (user.getUserid().equalsIgnoreCase(topUser.getUserid())) {						
						toDelUser.add(user);
					}
				}
				user.setWeeke(fechtime+1);
			}
			users.removeAll(toDelUser);
			topUsers.addAll(users);
			fechtime++;
		}
		// 通过访问量进行排序
		//if (topUsers != null && !topUsers.isEmpty()) {
			// UserValue sortuser[]=new UserValue[topUsers.size()];
			// topUsers.toArray(sortuser);
			//topUsers=sortUserByVisitCount(topUsers);
		//}

		indexPageContent.put("topUsers", topUsers);
		// 获取最近注册的12个用户列表(最多取4月之内的，4月之内的)
		fechtime = 0;
		Date beginDate = new Date();
		long beginTimee = beginDate.getTime() - (fechtime + 1) * 7 * 24 * 60
				* 60 * 1000;
		beginDate.setTime(beginTimee);
		List<UserValue> newUsers = new ArrayList<UserValue>();
		newUsers = this.webSiteVisitorDao.getRecentRegistUsers(beginDate,
				endDate, 0, 12);
		indexPageContent.put("newUsers", newUsers);
		// 获取最新10条图片文章
		PaingModel paingModel = new PaingModel();
		paingModel.setDoctype(StaticConstants.RESOURCE_TYPE_IMG);
		paingModel.setStartrow(0);
		paingModel.setEndrow(10);
		paingModel.setDocstatus(2);
		List<DocumentValue> images = this.webSiteVisitorDao
				.pagingquerydoc(paingModel);
		// 根据docid 获取img信息
		List<ImgValue> imgs = new ArrayList<ImgValue>();
		for (DocumentValue doc : images) {
			Map<String, Object> getImgParams = new HashMap<String, Object>();
			getImgParams.put("imgid", doc.getDocid());
			List<ImgValue> imgvalue = webResourceDao.getImgList(getImgParams);
			imgs.addAll(imgvalue);
		}
		indexPageContent.put("imgs", imgs);
		//
		List<TopDocumentValue> tops = this.getTopDocuments(
				StaticConstants.TOP_TYPE1, null, 7);
		File htmimgFile = new File(htmlpath, "html");
		File imgFile = new File(htmimgFile, "img");
		htmimgFile.mkdir();
		imgFile.mkdir();
		List<ImgNew> imgNews = new ArrayList<ImgNew>();
		for (TopDocumentValue topDocumentValue : tops) {
			ImgNew imgNew = new ImgNew();
			imgNew.setLink("getuserdocdetail.do?docid="
					+ topDocumentValue.getDocid() + "");
			imgNew.setUrl("html/img/" + topDocumentValue.getDocid() + ".jpg");
			imgNew.setTime(5000);
			imgNew.setTitle(topDocumentValue.getTitle());

			if (topDocumentValue.getDocimg() != null) {
				File imgFilei = new File(imgFile, topDocumentValue.getDocid()
						+ ".jpg");
				FileOutputStream stream = new FileOutputStream(imgFilei);
				stream.write(topDocumentValue.getDocimg());
				stream.close();
			}
			topDocumentValue.setDocimg(null);
			imgNews.add(imgNew);
		}

		net.sf.json.JSONArray jsonArray = net.sf.json.JSONArray
				.fromObject(imgNews);
		indexPageContent.put("pics", jsonArray.toString());
		return indexPageContent;
	}

	@Override
	public List<TopDocumentValue> getTopDocuments(Integer type, String docid,
			Integer limit) throws Exception {
		// TODO Auto-generated method stub
		List<TopDocumentValue> topDocuments = new ArrayList<TopDocumentValue>();
		// 星期
		if (type == StaticConstants.TOP_TYPE1) {
			// 天为周期查找知道找到位置
			Calendar cal = Calendar.getInstance(java.util.Locale.CHINA);
			Date fetchDate = cal.getTime();
			int fechcount = 0;
			while (topDocuments.size() == 0) {
				topDocuments.addAll(this.webSiteVisitorDao.getTopDocuments(
						fetchDate, type, docid, limit));
				// long frontweek = fetchDate.getTime() - 7 * 24 * 60 * 60 *
				// 1000;
				// fetchDate.setTime(frontweek);
				Calendar calendar = java.util.Calendar.getInstance();
				calendar.setTime(new Date());
				calendar.add(Calendar.WEEK_OF_YEAR, 0 - (fechcount + 1));
				fetchDate = calendar.getTime();
				fechcount++;
				if (fechcount >= 1000) {
					break;
				}
			}
		}
		// 月份
		else if (type == StaticConstants.TOP_TYPE2) {
			Calendar cal = Calendar.getInstance(java.util.Locale.CHINA);
			Date fetchDate = cal.getTime();
			while (topDocuments.size() == 0) {
				topDocuments.addAll(this.webSiteVisitorDao.getTopDocuments(
						fetchDate, type, docid, limit));
				Calendar calendar = java.util.Calendar.getInstance();
				calendar.setTime(new Date());
				calendar.add(Calendar.MONTH, -1);
				fetchDate = calendar.getTime();
			}
		} else {
			topDocuments.addAll(this.webSiteVisitorDao.getTopDocuments(
					new Date(), type, docid, limit));
		}
		// 年度
		return topDocuments;
	}

	private List<UserValue> sortUserByVisitCount(List<UserValue> toSortUsers) {
		UserValue sortuser[] = new UserValue[toSortUsers.size()];
		toSortUsers.toArray(sortuser);
		Arrays.sort(sortuser, new Comparator<UserValue>() {
			@Override
			public int compare(UserValue o1, UserValue o2) {
				// TODO Auto-generated method stub
				if (o1.getRetnum() != null && o2.getRetnum() != null) {
					return o1.getRetnum() - o2.getRetnum();
				} else {
					return 0;
				}
				
			}
		});
		toSortUsers = Arrays.asList(sortuser);
		return toSortUsers;
	}
}
