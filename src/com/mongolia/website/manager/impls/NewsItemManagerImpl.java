package com.mongolia.website.manager.impls;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mongolia.website.dao.interfaces.NewsItemDao;
import com.mongolia.website.manager.interfaces.NewsItemManager;
import com.mongolia.website.model.NewsItem;
import com.mongolia.website.util.UUIDMaker;

@Service("newsItemManagerImpl")
public class NewsItemManagerImpl implements NewsItemManager {

	@Resource(name = "newsItemDaoImpl")
	private NewsItemDao newsItemDao;
	@Resource(name = "configInfo")
	private SysConfig sysConfig;

	@Override
	public void delete(NewsItem entity) {
		// TODO Auto-generated method stub
		newsItemDao.delete(entity);
	}

	@Override
	public void save(NewsItem entity) {
		// TODO Auto-generated method stub
		entity.setId(UUIDMaker.getUUID());
		// entity.setAccountId();
		newsItemDao.save(entity);
	}

	@Override
	public void saveOrUpdate(NewsItem entity) {
		// TODO Auto-generated method stub
		// entity.setAccountId(ResourceUtil.getWeiXinAccount()
		// .getAccountid());
		newsItemDao.update(entity);
	}

	@Override
	public List<NewsItem> queryNewsItems(Map<String, Object> params)
			throws Exception {
		// TODO Auto-generated method stub
		return newsItemDao.queryNewsItems(params);
	}

}
