package com.mongolia.website.manager.impls;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mongolia.website.dao.interfaces.AutoResponseDao;
import com.mongolia.website.dao.interfaces.WechatDocDao;
import com.mongolia.website.manager.interfaces.AutoResponseManager;
import com.mongolia.website.model.Article;
import com.mongolia.website.model.AutoResponse;
import com.mongolia.website.model.PagingAutoResModel;
import com.mongolia.website.model.WechatDocValue;
import com.mongolia.website.util.UUIDMaker;
import com.mongolia.website.util.WeixinUtil;

@Service("autoResponseManagerImpl")
public class AutoResponseManagerImpl implements AutoResponseManager {

	@Resource(name = "autoResponseDaoImpl")
	private AutoResponseDao autoResponseDao;
	@Resource(name = "wechatDocDaoImpl")
	private WechatDocDao wechatDocDao;
	@Resource(name = "configInfo")
	private SysConfig sysConfig;

	@Override
	public void delete(AutoResponse entity) throws Exception {
		// TODO Auto-generated method stub
		autoResponseDao.delete(entity);
	}

	@Override
	public List<AutoResponse> getAutoResponses(Map<String, Object> params)
			throws Exception {
		// TODO Auto-generated method stub
		return this.autoResponseDao.getAutoResponses(params);
	}

	@Override
	public void saveOrUpdate(AutoResponse entity) throws Exception {
		// TODO Auto-generated method stub
		if (entity.getDefaultmess().intValue() == 1) {
			// 检验是否存在
			Map<String, Object> getParams = new HashMap<String, Object>();
			getParams.put("defaultmess", 1);
			List<AutoResponse> autoResponses = this.autoResponseDao
					.getAutoResponses(getParams);
			if (autoResponses != null && !autoResponses.isEmpty()) {
				if (!autoResponses.get(0).getId()
						.equalsIgnoreCase(entity.getId())) {
					throw new Exception("已经存在默认回复设置,系统中智能维护一条默认回复设置！");
				}
			}
		}
		if (entity.getId() != null
				&& !entity.getId().equalsIgnoreCase("_empty")) {
			this.autoResponseDao.update(entity);
		} else {
			entity.setId(UUIDMaker.getUUID());
			this.autoResponseDao.save(entity);
		}
	}

	@Override
	public PagingAutoResModel pagingqueryAutoResp(PagingAutoResModel paingModel)
			throws Exception {
		// TODO Auto-generated method stub
		paingModel.setPagesize(10);
		paingModel.setStartrow((paingModel.getPageindex() - 1)
				* paingModel.getPagesize());
		List<AutoResponse> autoResponses = this.autoResponseDao
				.pagingQueryAutoResponse(paingModel);
		paingModel.setModelList(autoResponses);
		Integer rowcount = this.autoResponseDao
				.getAutoResponseCount(paingModel);
		paingModel.setRowcount("" + rowcount);
		int pageCount = rowcount / 10;
		if (rowcount % 10 > 0) {
			pageCount = pageCount + 1;
		}
		paingModel.setPagecount(pageCount);
		return paingModel;
	}

	@Override
	public void addWechatDoc(WechatDocValue wechatDocValue) throws Exception {
		// TODO Auto-generated method stube
		wechatDocValue.setSeldate(new Date());
		wechatDocValue.setId(UUIDMaker.getUUID());
		this.wechatDocDao.addWechatDoc(wechatDocValue);
	}

	@Override
	public void delWechatDoc(String docid, String autoresid) throws Exception {
		// TODO Auto-generated method stub
		WechatDocValue wechatDocValue = new WechatDocValue();
		wechatDocValue.setDocid(docid);
		wechatDocValue.setResponseid(autoresid);
		this.wechatDocDao.delWechatDoc(wechatDocValue);

	}

	@Override
	public List<WechatDocValue> getWechatDocValues(String autoresid)
			throws Exception {
		// TODO Auto-generated method stub
		return this.wechatDocDao.getWechatDocWithAutoResId(autoresid);
	}

	@Override
	public void sendServiceMess() {
		// TODO Auto-generated method stub
		Map<String, Object> prams = new HashMap<String, Object>();
		java.text.SimpleDateFormat format = new java.text.SimpleDateFormat(
				"yyyy-MM-dd");
		prams.put("addtime", format.format(new Date()));
		try {
			List<AutoResponse> autoResponses = this
					.getAutoResponses(prams);
			if (autoResponses == null || autoResponses.isEmpty()) {
				return;
			}
			List<WechatDocValue> WechatDocValues = this.wechatDocDao
					.getWechatDocWithAutoResId(autoResponses.get(0).getId());
			List<Article> articleList = new ArrayList<Article>();
			for (WechatDocValue wechatDocValue : WechatDocValues) {
				Article article = new Article();
				article.setTitle(wechatDocValue.getDoctitle());
				article.setPicUrl(sysConfig.getSiteaddress() + "/html/img/"
						+ wechatDocValue.getDocimg());
				article.setUrl(sysConfig.getSiteaddress()
						+ "/phonedetail.do?docid=" + wechatDocValue.getDocid());
				article.setDescription(wechatDocValue.getDocabc());
				articleList.add(article);
			}
			// 给所有发生过互动的用户群发消息
			List<String> openids = this.wechatDocDao.getRecentReqUserId();
			for (String openid : openids) {
				WeixinUtil.sendServiceMess(sysConfig.getAccountid(), openid,
						articleList);
			}
            System.out.println("给"+openids.size()+"用户发送群发消息！"+new Date());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	

}
