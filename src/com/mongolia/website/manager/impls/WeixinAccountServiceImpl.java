package com.mongolia.website.manager.impls;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mongolia.website.dao.interfaces.WechatDao;
import com.mongolia.website.dao.interfaces.WeixinAccountDao;
import com.mongolia.website.manager.interfaces.WeixinAccountServiceI;
import com.mongolia.website.model.AccessTokenYw;
import com.mongolia.website.model.AttentionValue;
import com.mongolia.website.model.WechatAccountEntity;
import com.mongolia.website.util.WeixinUtil;

@Service("weixinAccountService")
@Transactional
public class WeixinAccountServiceImpl implements WeixinAccountServiceI {
	@Resource(name = "weixinAccountDaoImpl")
	private WeixinAccountDao weixinAccountDao;
	@Resource(name = "wechatDaoImpl")
	private WechatDao wechatDao;
	@Resource(name = "configInfo")
	private SysConfig sysConfig;

	@Override
	public String getAccessToken() {
		// TODO Auto-generated method stub
		String token = "";
		WechatAccountEntity account = findLoginWeixinAccount();

		token = account.getAccountaccesstoken();
		if (token != null && !"".equals(token)) {
			// 判断有效时间 是否超过2小时
			try {
				java.util.Date end = new java.util.Date();
				java.util.Date start = new java.util.Date(account
						.getAddtoekntime().getTime());
				if ((end.getTime() - start.getTime()) / 1000 / 3600 >= 2) {
					// 失效 重新获取
					String requestUrl = WeixinUtil.access_token_url.replace(
							"APPID", account.getAccountappid()).replace(
							"APPSECRET", account.getAccountappsecret());
					JSONObject jsonObject = WeixinUtil.httpRequest(requestUrl,
							"GET", null);
					if (null != jsonObject) {

						// try {
						token = jsonObject.getString("access_token");
						// 重置token
						account.setAccountaccesstoken(token);
						// 重置事件
						account.setAddtoekntime(new Date());
						// 获取jsaccesstoken
						// "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi";
						String accessjsUrl = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token="
								+ token + "&type=jsapi";
						JSONObject jsticketObject = WeixinUtil.httpRequest(
								accessjsUrl, "GET", null);
						if (null != jsticketObject) {

							String jstoken = jsticketObject.getString("ticket");
							account.setJsaccounttoken(jstoken);
						}
						//this.saveOrUpdate(account);
						// }
						// catch (Exception e) {
					}
				} else {
					return account.getAccountaccesstoken();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
				token = null;

				// 获取token失败
				// String wrongMessage = "获取token失败 errcode:{} errmsg:{}"
				// + jsonObject.getInt("errcode")
				// + jsonObject.getString("errmsg");
				// }
			}
		} else {

			String requestUrl = WeixinUtil.access_token_url.replace("APPID",
					account.getAccountappid()).replace("APPSECRET",
					account.getAccountappsecret());
			JSONObject jsonObject = WeixinUtil.httpRequest(requestUrl, "GET",
					null);

			if (null != jsonObject) {
				try {
					token = jsonObject.getString("access_token");

					// 重置token
					account.setAccountaccesstoken(token);
					// 重置事件
					account.setAddtoekntime(new Date());
					// 获取jstoken
					String accessjsUrl = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token="
							+ token + "&type=jsapi";
					JSONObject jsticketObject = WeixinUtil.httpRequest(
							accessjsUrl, "GET", null);
					if (null != jsticketObject) {

						String jstoken = jsticketObject.getString("ticket");
						account.setJsaccounttoken(jstoken);
					}
					//
					//this.saveOrUpdate(account);
				} catch (Exception e) {
					token = null;

					// 获取token失败
					String wrongMessage = "获取token失败 errcode:{} errmsg:{}"
							+ jsonObject.getInt("errcode")
							+ jsonObject.getString("errmsg");
				}
			}
		}
		return token;
	}

	@Override
	public WechatAccountEntity findLoginWeixinAccount() {
		/*
		 * UserValue user = ResourceUtil.getSessionUserName();
		 * List<WeixinAccountEntity> acclst =
		 * findByUsername(user.getUsername()); WeixinAccountEntity
		 * weixinAccountEntity = acclst.size() != 0 ? acclst .get(0) : null; if
		 * (weixinAccountEntity != null) { return weixinAccountEntity; } else {
		 * weixinAccountEntity = new WeixinAccountEntity(); // 返回个临时对象，防止空指针
		 * weixinAccountEntity.setAccountid("-1"); return weixinAccountEntity; }
		 */
		return null;
	}

	@Override
	public List<WechatAccountEntity> findByUsername(String username) {
		// TODO Auto-generated method stub
		List<WechatAccountEntity> acclst = weixinAccountDao
				.getAccountByUserName(username);
		return acclst;
	}

	@Override
	public WechatAccountEntity getAccountEntity(String id) {
		// TODO Auto-generated method stub
		return this.weixinAccountDao.getAccountById(id);
	}

	@Override
	public void saveOrUpdate(WechatAccountEntity weixinAccountEntity) {
		// TODO Auto-generated method stub
		this.weixinAccountDao.saveOrUpdate(weixinAccountEntity);

	}

	@Override
	public void deleteAccount(WechatAccountEntity weixinAccountEntity) {
		// TODO Auto-generated method stub
		this.weixinAccountDao.deleteAccount(weixinAccountEntity);
	}

	@Override
	public List<WechatAccountEntity> getWechatAccounts(
			Map<String, Object> params) {
		// TODO Auto-generated method stub
		return this.weixinAccountDao.getWechatAccounts(params);
	}

	@Override
	public void updateAccountAccessToken(AccessTokenYw accessTokenYw) {
		// TODO Auto-generated method stub
		// 检测有没有 对应的accesstoken 没有则新增，有则修改
		try {
			this.weixinAccountDao.saveAccessToken(accessTokenYw);
		} catch (Exception ex) {
			ex.printStackTrace();

		}
	}

	@Override
	public AccessTokenYw getAccessToken(String appid) {
		// TODO Auto-generated method stub
		try {
			return this.weixinAccountDao.getAccessToken(appid);
		} catch (Exception ex) {
			ex.printStackTrace();

			return null;
		}
	}

	@Override
	public void synAttentionUsers() throws Exception {
		// TODO Auto-generated method stub
		List<WechatAccountEntity> account = WeixinUtil
				.getAccountEntity(this.sysConfig.getAccountid());
		List<String> ids = WeixinUtil.getAttentionUserList(account.get(0)
				.getAccountappid(), account.get(0).getAccountappsecret());
		try {
			for (String id : ids) {
				AttentionValue attentionValue = new AttentionValue();
				attentionValue.setAccountid(this.sysConfig.getAccountid());
				attentionValue.setAttentiontime(new Date());
				attentionValue.setOpenid(id);
				attentionValue.setStatus(1);
				// wechatDao.addAttentionUserInfo(attentionValue);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}
}