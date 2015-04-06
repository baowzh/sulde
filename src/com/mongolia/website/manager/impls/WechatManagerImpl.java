package com.mongolia.website.manager.impls;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mongolia.website.dao.interfaces.WeixinAccountDao;
import com.mongolia.website.manager.interfaces.WechatManager;
import com.mongolia.website.model.WechatAccountEntity;

@Service("wechatManagerImpl")
public class WechatManagerImpl implements WechatManager {
	@Resource(name = "weixinAccountDaoImpl")
	private WeixinAccountDao weixinAccountDao;

	@Override
	public WechatAccountEntity getWechatAccountEntity(String appid)
			throws Exception {
		// TODO Auto-generated method stub
		List<WechatAccountEntity> accounts = this.weixinAccountDao
				.getWechatAccounts(new HashMap<String, Object>());
		return accounts.get(0);
	}

	@Override
	public void addnewitem(String mediaid, String author, String title,
			String contentsourceurl, String content, String digest,
			Integer showcoverpic) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void uploadMapnews(List<String> newsitemids) throws Exception {
		// TODO Auto-generated method stub

	}

}
