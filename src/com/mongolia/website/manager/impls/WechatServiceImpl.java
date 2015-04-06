package com.mongolia.website.manager.impls;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.mongolia.website.dao.interfaces.WechatDocDao;
import com.mongolia.website.manager.interfaces.AutoResponseManager;
import com.mongolia.website.manager.interfaces.TextTemplateManager;
import com.mongolia.website.manager.interfaces.WechatService;
import com.mongolia.website.model.Article;
import com.mongolia.website.model.AutoResponse;
import com.mongolia.website.model.NewsMessageResp;
import com.mongolia.website.model.TextMessageResp;
import com.mongolia.website.model.WechatDocValue;
import com.mongolia.website.model.WechatReceiveMessValue;
import com.mongolia.website.util.MessageUtil;
import com.mongolia.website.util.UUIDMaker;

@Service("wechatServiceImpl")
public class WechatServiceImpl implements WechatService {
	@Resource(name = "autoResponseManagerImpl")
	private AutoResponseManager autoResponseManager;
	@Resource(name = "configInfo")
	private SysConfig sysConfig;
	@Resource(name = "textTemplateManagerImpl")
	private TextTemplateManager textTemplateManager;
	@Resource(name = "newsItemManagerImpl")
	private NewsItemManagerImpl newsItemManagerImpl;
	@Resource(name = "wechatDocDaoImpl")
	private WechatDocDao WechatDocDao;

	public String coreService(HttpServletRequest request) throws Exception {
		String respMessage = null;
		TextMessageResp textMessage = new TextMessageResp();
		String fromUserName = "";
		String toUserName = "";
		WechatReceiveMessValue receiveMessValue = new WechatReceiveMessValue();
		try {
			// 默认返回的文本消息内容
			String respContent = "请求处理异常，请稍候尝试！";
			// xml请求解析
			Map<String, String> requestMap = MessageUtil.parseXml(request);
			fromUserName = requestMap.get("FromUserName");
			// 公众帐号
			toUserName = requestMap.get("ToUserName");
			// 消息类型
			String msgType = requestMap.get("MsgType");
			// 默认回复此文本消息
			textMessage.setToUserName(fromUserName);
			textMessage.setFromUserName(toUserName);
			textMessage.setCreateTime(new Date().getTime());
			textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
			receiveMessValue.setMessid(UUIDMaker.getUUID());
			receiveMessValue.setTousername(toUserName);
			receiveMessValue.setFromusername(fromUserName);
			receiveMessValue.setMesstype(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
			receiveMessValue.setCreatetime(new Date());
			// 文本消息
			if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {
				// 根据收到的文本消息返回内容
				String content = requestMap.get("Content");
				AutoResponse autoResponse = findKey(content, toUserName);
				if (autoResponse != null) {
					// 根据此自动回复设置组织图文消息比你更返回
					List<WechatDocValue> WechatDocValues = this.WechatDocDao
							.getWechatDocWithAutoResId(autoResponse.getId());
					List<Article> articleList = new ArrayList<Article>();
					for (WechatDocValue wechatDocValue : WechatDocValues) {
						Article article = new Article();
						article.setTitle(wechatDocValue.getDoctitle());
						article.setPicUrl(wechatDocValue.getDocimg());
						article.setUrl(wechatDocValue.getDocurl());
						article.setDescription(wechatDocValue.getDocabc());
						articleList.add(article);
					}
					NewsMessageResp newsResp = new NewsMessageResp();
					newsResp.setCreateTime(new Date().getTime());
					newsResp.setFromUserName(toUserName);
					newsResp.setToUserName(fromUserName);
					newsResp.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
					newsResp.setArticleCount(articleList.size());
					newsResp.setArticles(articleList);
					respMessage = MessageUtil.newsMessageToXml(newsResp);
				}
				receiveMessValue.setContent(requestMap.get("Content"));

			}
			// 图片消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {
				respContent = "您发送的是图片消息！";
				textMessage.setContent(respContent);
				respMessage = MessageUtil.textMessageToXml(textMessage);
				receiveMessValue.setContent(requestMap.get("PicUrl"));
			}
			// 地理位置消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {
				respContent = "您发送的是地理位置消息！";
				textMessage.setContent(respContent);
				respMessage = MessageUtil.textMessageToXml(textMessage);
				receiveMessValue.setContent(requestMap.get("Label"));
			}
			// 链接消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) {
				respContent = "您发送的是链接消息！";
				textMessage.setContent(respContent);
				respMessage = MessageUtil.textMessageToXml(textMessage);
				receiveMessValue.setContent(requestMap.get("Url"));
			}
			// 音频消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {
				respContent = "您发送的是音频消息！";
				textMessage.setContent(respContent);
				respMessage = MessageUtil.textMessageToXml(textMessage);
				receiveMessValue.setContent(requestMap.get("MediaId"));
			}
			// 事件推送
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
				// 事件类型
				String eventType = requestMap.get("Event");
				receiveMessValue.setContent(eventType);
				// 订阅
				if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
					textMessage.setContent("谢谢您的关注");
					respMessage = MessageUtil.textMessageToXml(textMessage);
				}
				// 取消订阅
				else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {
					// 取消订阅后用户再收不到公众号发送的消息，因此不需要回复消息

				}
				// 自定义菜单点击事件
				else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {
					textMessage.setContent("谢谢您的关注");
					respMessage = MessageUtil.textMessageToXml(textMessage);
					// 如果是点击了优秀作品
				}

			}
			// 记录公众号收到的消息
			this.WechatDocDao.addWechatReceiveMessValue(receiveMessValue);

		} catch (Exception e) {
			e.printStackTrace();
			textMessage.setToUserName(fromUserName);
			textMessage.setFromUserName(toUserName);
			textMessage.setCreateTime(new Date().getTime());
			textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
			textMessage.setContent(e.getMessage());
			return MessageUtil.textMessageToXml(textMessage);
		}
		return respMessage;

	}

	private AutoResponse findKey(String content, String accountId) {
		// 获取关键字管理的列表，匹配后返回信息
		Map<String, Object> prams = new HashMap<String, Object>();
		prams.put("accountId", accountId);
		try {
			List<AutoResponse> autoResponses = this.autoResponseManager
					.getAutoResponses(prams);
			// (
			// AutoResponse.class, "accountId", accountId);
			for (AutoResponse r : autoResponses) {
				// 如果包含关键字
				String kw = r.getKeyword();
				String[] allkw = kw.split(",");
				for (String k : allkw) {
					if (k.equalsIgnoreCase(content)) {
						return r;
					}
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

}
