package com.mongolia.website.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.ConnectException;
import java.net.URL;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import net.sf.json.JSONObject;

import com.mongolia.website.dao.interfaces.WechatDao;
import com.mongolia.website.manager.impls.SpringUtils;
import com.mongolia.website.manager.interfaces.WeixinAccountServiceI;
import com.mongolia.website.model.AccessToken;
import com.mongolia.website.model.AccessTokenYw;
import com.mongolia.website.model.AttentionValue;
import com.mongolia.website.model.WechatAccountEntity;
import com.mongolia.website.model.WechatUserValue;
import com.mongolia.website.model.WeixinAccountEntity;

/**
 * 公众平台通用接口工具类
 * 
 * @author liuyq
 * @date 2013-08-09
 */
public class WeixinUtil {
	public final static String access_token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
	public static String menu_create_url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
	public static String send_message_url = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=ACCESS_TOKEN";
	public static Map<String, AccessTokenYw> accessTokens = new HashMap<String, AccessTokenYw>();
	private static ResourceBundle bundler = ResourceBundle
			.getBundle("vflyshop");
	private static ResourceBundle bundler1 = ResourceBundle
			.getBundle("sysConfig");
	private static String authurl;
	public static WechatAccountEntity defaultAccountEntity;

	/**
	 * 发起https请求并获取结果
	 * 
	 * @param requestUrl
	 *            请求地址
	 * @param requestMethod
	 *            请求方式（GET、POST）
	 * @param outputStr
	 *            提交的数据
	 * @return JSONObject(通过JSONObject.get(key)的方式获取json对象的属性值)
	 */
	public static JSONObject httpRequest(String requestUrl,
			String requestMethod, String outputStr) {
		JSONObject jsonObject = null;
		StringBuffer buffer = new StringBuffer();
		try {
			TrustManager[] tm = { new MyX509TrustManager() };
			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			sslContext.init(null, tm, new java.security.SecureRandom());
			// 从上述SSLContext对象中得到SSLSocketFactory对象
			SSLSocketFactory ssf = sslContext.getSocketFactory();
			URL url = new URL(requestUrl);
			HttpsURLConnection httpUrlConn = (HttpsURLConnection) url
					.openConnection();
			httpUrlConn.setSSLSocketFactory(ssf);
			httpUrlConn.setDoOutput(true);
			httpUrlConn.setDoInput(true);
			httpUrlConn.setUseCaches(false);
			// 设置请求方式（GET/POST）
			httpUrlConn.setRequestMethod(requestMethod);
			if ("GET".equalsIgnoreCase(requestMethod))
				httpUrlConn.connect();
			if (null != outputStr) {
				OutputStream outputStream = httpUrlConn.getOutputStream();
				// 注意编码格式，防止中文乱码
				outputStream.write(outputStr.getBytes("UTF-8"));
				outputStream.close();
			}
			InputStream inputStream = httpUrlConn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(
					inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(
					inputStreamReader);
			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			bufferedReader.close();
			inputStreamReader.close();
			// 释放资源
			inputStream.close();
			inputStream = null;
			httpUrlConn.disconnect();
			jsonObject = JSONObject.fromObject(buffer.toString());
			// jsonObject = JSONObject.fromObject(buffer.toString());
		} catch (ConnectException ce) {
			ce.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonObject;
	}

	/**
	 * 获取access_token
	 * 
	 * @param appid
	 *            凭证
	 * @param appsecret
	 *            密钥
	 * @return
	 */
	public static AccessToken getAccessToken(String appid, String appsecret) {
		AccessTokenYw accessTocken = accessTokens.get(appid);
		AccessToken returntoken = new AccessToken();
		if (accessTocken != null) {
			// 从数据库汇总获取
			accessTocken = getRealAccessToken(appid);
			java.util.Date end = new java.util.Date();
			if (end.getTime() - accessTocken.getAddtime().getTime() > accessTocken
					.getExpires_in() * 1000) {
				AccessToken accessToken = getToken(appid, appsecret);
				returntoken = accessToken;
			} else {
				AccessToken accessToken = new AccessToken();
				accessToken.setToken(accessTocken.getAccess_token());
				accessToken.setExpiresIn(accessTocken.getExpires_in());
				accessToken.setJstoken(accessTocken.getJsaccess_token());
				returntoken = accessToken;
			}
		} else {
			returntoken = getToken(appid, appsecret);
		}
		// 判断是否存在jstoken 如果没有则回去
		if (returntoken.getJstoken() == null
				|| returntoken.getJstoken().equalsIgnoreCase("")) {
			//
			try {
				String accessjsUrl = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token="
						+ returntoken.getToken() + "&type=jsapi";
				JSONObject jsticketObject = WeixinUtil.httpRequest(accessjsUrl,
						"GET", null);
				if (null != jsticketObject) {
					String jstoken = jsticketObject.getString("ticket");
					returntoken.setJstoken(jstoken);
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

		return returntoken;
	}

	/**
	 * 从微信服务端获取access_token
	 * 
	 * @param appid
	 * @param appsecret
	 * @return
	 */
	private static AccessToken getToken(String appid, String appsecret) {
		String requestUrl = access_token_url.replace("APPID", appid).replace(
				"APPSECRET", appsecret);
		JSONObject jsonObject = httpRequest(requestUrl, "GET", null);
		AccessToken accessToken = null;
		AccessTokenYw accessTocken = accessTokens.get(appid);
		// 如果请求成功
		if (null != jsonObject) {
			try {
				//

				accessToken = new AccessToken();
				accessToken.setToken(jsonObject.getString("access_token"));
				accessToken.setExpiresIn(jsonObject.getInt("expires_in"));
				// 后去js token
				String accessjsUrl = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token="
						+ accessToken.getToken() + "&type=jsapi";
				JSONObject jsticketObject = WeixinUtil.httpRequest(accessjsUrl,
						"GET", null);
				if (null != jsticketObject) {

					String jstoken = jsticketObject.getString("ticket");
					accessToken.setJstoken(jstoken);
				}
				//
				AccessTokenYw atyw = new AccessTokenYw();
				if (accessTocken != null) {
					atyw.setId(accessTocken.getId());
				}
				atyw.setExpires_in(jsonObject.getInt("expires_in"));
				atyw.setAccess_token(jsonObject.getString("access_token"));
				Date currentDate = new Date();
				atyw.setAddtime(new java.sql.Timestamp(currentDate.getTime()));
				atyw.setAppid(appid);
				atyw.setJsaccess_token(accessToken.getJstoken());
				atyw.setAppsecret(appsecret);
				// 修改数据库
				updateAccessToken(atyw);
				accessTokens.put(appid, atyw);
			} catch (Exception e) {
				accessToken = null;

				// 获取token失败
			}
		}
		return accessToken;
	}

	/**
	 * 从数据库中读取凭证
	 * 
	 * @return
	 */
	public static AccessTokenYw getRealAccessToken(String appid) {
		WeixinAccountServiceI weixinAccountServiceI = (WeixinAccountServiceI) SpringUtils
				.getBean("weixinAccountService");
		AccessTokenYw accessTokenYw = weixinAccountServiceI
				.getAccessToken(appid);
		return accessTokenYw;
	}

	/**
	 * 更新凭证
	 * 
	 * @return
	 */
	public static void updateAccessToken(AccessTokenYw accessTocken) {
		WeixinAccountServiceI weixinAccountServiceI = (WeixinAccountServiceI) SpringUtils
				.getBean("weixinAccountService");
		weixinAccountServiceI.updateAccountAccessToken(accessTocken);
	}

	/**
	 * 调用微信api获取用户信息
	 * 
	 * @param authcode
	 * @param appid
	 * @param appsecret
	 * @return
	 */
	public static WechatUserValue getWechatUserValue(String authcode,
			String appid, String appsecret) {
		String requestUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="
				+ appid
				+ "&secret="
				+ appsecret
				+ "&code="
				+ authcode
				+ "&grant_type=authorization_code";
		JSONObject jsonObject = WeixinUtil.httpRequest(requestUrl, "GET", null);
		// AccessToken accessToken = getAccessToken(appid, appsecret);//
		// 获取access_token
		String openid = (String) jsonObject.get("openid");
		WechatUserValue wechatUserValue = getUserValueWithOpenid(openid, appid,
				appsecret);
		return wechatUserValue;
	}

	public static WechatUserValue getUserValueWithOpenid(String openid,
			String appid, String appsecret) {
		// 先同数据库查询，若查询不到从微信服务端查询
		WechatDao wechatDao = (WechatDao) SpringUtils.getBean("wechatDaoImpl");
		AttentionValue attentionValue = wechatDao.getAttentionUserInfo(openid);
		WechatUserValue wechatUserValue = new WechatUserValue();
		if (attentionValue != null
				&& attentionValue.getStatus().intValue() == 1) {
			wechatUserValue.setSubscribe(true);
			wechatUserValue.setOpenid(attentionValue.getOpenid());
			wechatUserValue.setCountry(attentionValue.getCountry());
			wechatUserValue.setHeadimgurl(attentionValue.getHeadimgurl());
			wechatUserValue.setLanguage(attentionValue.getLanguage());
			wechatUserValue.setNickname(attentionValue.getNickname());
			wechatUserValue.setProvince(attentionValue.getProvince());
			wechatUserValue.setRemark(attentionValue.getRemark());
			wechatUserValue.setSex(attentionValue.getSex());
		} else {
			AccessToken accessToken = getAccessToken(appid, appsecret);// 获取access_token
			String getUserInfoUrl = "https://api.weixin.qq.com/cgi-bin/user/info?access_token="
					+ accessToken.getToken()
					+ "&openid="
					+ openid
					+ "&lang=zh_CN";
			JSONObject userInfo = WeixinUtil.httpRequest(getUserInfoUrl, "GET",
					null);// 获取用户信息
			// userinfo:{"subscribe":1,"openid":"ouxkWt0pOIpZZuuXvIqnGCluksH0","nickname":"文章","sex":1,"language":"zh_CN","city":"呼和浩特","province":"内蒙古","country":"中国","headimgurl":"http://wx.qlogo.cn/mmopen/oy6t7Y5iag6QVhzjDYckjwanh9YyHyTXEon0qCXGVjjoVVVlGcLbvbmnL0szNkwJg4ajsQ8mtVBpIcLUoHdwD8RXgDrgQWKicy/0","subscribe_time":1412930744,"remark":""}
			int subscribe = userInfo.getInt("subscribe");
			if (subscribe == 1) {
				wechatUserValue.setSubscribe(true);
				wechatUserValue.setCity(userInfo.getString("city"));
				wechatUserValue.setNickname(userInfo.getString("nickname"));
				wechatUserValue.setSex(userInfo.getInt("sex"));
				wechatUserValue.setLanguage(userInfo.getString("language"));
				wechatUserValue.setProvince(userInfo.getString("province"));
				wechatUserValue.setCountry(userInfo.getString("country"));
				wechatUserValue.setHeadimgurl(userInfo.getString("headimgurl"));
				wechatUserValue.setRemark(userInfo.getString("remark"));
			} else {
				wechatUserValue.setSubscribe(false);
				wechatUserValue.setNickname("游客");
			}
		}
		return wechatUserValue;
	}

	public static String getAuth20Url(String appid, String directurl,
			Map<String, Object> params) throws Exception {
		if (authurl == null) {
			authurl = bundler.getString("authurl");
		}
		String auth2url = authurl + "/auth2dispatcher.jhtml";
		Set<String> keys = params.keySet();
		Iterator<String> iterator = keys.iterator();
		int index = 0;
		while (iterator.hasNext()) {
			String paramname = iterator.next();
			if (index == 0) {
				auth2url = auth2url + "?" + paramname + "="
						+ params.get(paramname);
			} else {
				auth2url = auth2url + "&" + paramname + "="
						+ params.get(paramname);
			}
			index++;
		}
		// 添加跳转地址
		if (index == 0) {
			auth2url = auth2url + "?dispathurl=" + directurl;
		} else {
			auth2url = auth2url + "&dispathurl=" + directurl;
		}
		String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid="
				+ appid
				+ "&redirect_uri="
				+ URLEncoder.encode(auth2url, "utf-8")
				+ "&response_type=code&scope=snsapi_base&state=12#wechat_redirect";
		return url;
	}

	public static JSONObject sendServiceMess(String accountid, String openid,
			String mess) {
		List<WechatAccountEntity> accounts = getAccountEntity(accountid);
		AccessToken accesstokeni = getAccessToken(accounts.get(0)
				.getAccountappid(), accounts.get(0).getAccountappsecret());

		String url = send_message_url.replace("ACCESS_TOKEN",
				accesstokeni.getToken());

		Map<String, Object> jspnMap = new HashMap<String, Object>();
		jspnMap.put("touser", openid);
		jspnMap.put("msgtype", "text");
		Map<String, Object> contentMap = new HashMap<String, Object>();
		contentMap.put("content", mess);
		jspnMap.put("text", contentMap);
		JSONObject jsonMess = JSONObject.fromObject(jspnMap);

		JSONObject jsonObject = WeixinUtil.httpRequest(url, "POST",
				jsonMess.toString());

		return jsonObject;
	}

	public static List<WechatAccountEntity> getAccountEntity(String account_id) {
		Map<String, Object> queryParams = new HashMap<String, Object>();
		queryParams.put("accountid", account_id);
		List<WechatAccountEntity> accountentities = ((WeixinAccountServiceI) SpringUtils
				.getBean("weixinAccountService"))
				.getWechatAccounts(queryParams);
		return accountentities;
	}

	public static List<String> getAttentionUserList(String appid,
			String appsecret) {
		// String getuserlistUrl =
		// "https://api.weixin.qq.com/cgi-bin/user/get?access_token=ACCESS_TOKEN&next_openid=NEXT_OPENID";
		AccessToken token = getAccessToken(appid, appsecret);
		String tokenid = token.getToken();
		String getuserlistUrl = "https://api.weixin.qq.com/cgi-bin/user/get?access_token="
				+ tokenid;
		JSONObject jsonObject = WeixinUtil.httpRequest(getuserlistUrl, "GET",
				null);
		int total = jsonObject.getInt("total");
		List<String> returnIds = new ArrayList<String>();
		// int count = jsonObject.getInt("count");
		// JSONObject jsonData = jsonObject.getJSONObject("data");
		// String openids = jsonData.getString("openid");
		// String openidlist[] = openids.split(",");
		// String next_openid = jsonObject.getString("next_openid");
		String next_openid = "";
		int fechcount = total / 1000;
		if (total % 1000 > 0) {
			fechcount = fechcount + 1;
		}
		for (int i = 0; i < fechcount; i++) {
			if (i == 0) {
				getuserlistUrl = "https://api.weixin.qq.com/cgi-bin/user/get?access_token="
						+ tokenid;
				jsonObject = WeixinUtil
						.httpRequest(getuserlistUrl, "GET", null);
			} else {
				getuserlistUrl = "https://api.weixin.qq.com/cgi-bin/user/get?access_token="
						+ tokenid + "&next_openid=" + next_openid;
				jsonObject = WeixinUtil
						.httpRequest(getuserlistUrl, "GET", null);
			}
			JSONObject jsonData = jsonObject.getJSONObject("data");
			String openids = jsonData.getString("openid");
			String openidlist[] = openids.split(",");
			List<String> idlist = Arrays.asList(openidlist);
			returnIds.addAll(idlist);
			next_openid = jsonObject.getString("next_openid");
		}
		return returnIds;
	}

	public static String getUserOpenIdWithAuthCode(String authcode) {
		JSONObject jsonObject = new JSONObject();
		String openid = "";
		try {
			WeixinAccountServiceI weixinAccountServiceI = (WeixinAccountServiceI) SpringUtils
					.getBean("weixinAccountService");
			String account_id = bundler1.getString("account_id");
			if (defaultAccountEntity == null) {
				List<WechatAccountEntity> accounts = weixinAccountServiceI
						.getWechatAccounts(new HashMap<String, Object>());
				for (WechatAccountEntity weixinAccountEntity : accounts) {
					if (weixinAccountEntity.getAccountid().equalsIgnoreCase(
							account_id)) {
						defaultAccountEntity = weixinAccountEntity;
					}
				}
			}
			String requestUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="
					+ defaultAccountEntity.getAccountappid()
					+ "&secret="
					+ defaultAccountEntity.getAccountappsecret()
					+ "&code="
					+ authcode + "&grant_type=authorization_code";

			jsonObject = WeixinUtil.httpRequest(requestUrl, "GET", null);

			openid = (String) jsonObject.get("openid");
		} catch (Exception e) {

			e.printStackTrace();
		}
		return openid;
	}

	public static Map<String, String> getjsSignStr(String appid,
			String appsecret, String url) {
		AccessToken accessToken = getAccessToken(appid, appsecret);
		Map<String, String> returnMap = new HashMap<String, String>();
		returnMap = sign(accessToken.getJstoken(), url);
		returnMap.put("appId", appid);
		// String noncestr = UUIDGenerator.generate();
		// Long timestamp = System.currentTimeMillis() / 1000;
		// returnMap.put("nonceStr", noncestr);
		// String signStr = "jsapi_ticket=" + accessToken.getJstoken()
		// + "&noncestr=" + noncestr + "&timestamp=" + timestamp + "&url="
		// + url;
		return returnMap;
	}

	private static Map<String, String> sign(String jsapi_ticket, String url) {
		Map<String, String> ret = new HashMap<String, String>();
		String nonce_str = create_nonce_str();
		String timestamp = create_timestamp();
		String string1;
		String signature = "";

		// 注意这里参数名必须全部小写，且必须有序
		string1 = "jsapi_ticket=" + jsapi_ticket + "&noncestr=" + nonce_str
				+ "&timestamp=" + timestamp + "&url=" + url;
		System.out.println(string1);

		try {
			MessageDigest crypt = MessageDigest.getInstance("SHA-1");
			crypt.reset();
			crypt.update(string1.getBytes("UTF-8"));
			signature = byteToHex(crypt.digest());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		ret.put("url", url);
		ret.put("jsApiList", jsapi_ticket);
		ret.put("nonceStr", nonce_str);
		ret.put("timestamp", timestamp);
		ret.put("signature", signature);

		return ret;
	}

	private static String byteToHex(final byte[] hash) {
		Formatter formatter = new Formatter();
		for (byte b : hash) {
			formatter.format("%02x", b);
		}
		String result = formatter.toString();
		formatter.close();
		return result;
	}

	private static String create_nonce_str() {
		return UUIDMaker.getUUID();
	}

	private static String create_timestamp() {
		return Long.toString(System.currentTimeMillis() / 1000);
	}

}