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
				if (autoResponse != null
						&& autoResponse.getMsgtype().equalsIgnoreCase("1")) {
					// 根据此自动回复设置组织图文消息比你更返回
					List<WechatDocValue> WechatDocValues = this.WechatDocDao
							.getWechatDocWithAutoResId(autoResponse.getId());
					List<Article> articleList = new ArrayList<Article>();
					for (WechatDocValue wechatDocValue : WechatDocValues) {
						Article article = new Article();
						article.setTitle(wechatDocValue.getDoctitle());
						article.setPicUrl(sysConfig.getSiteaddress()
								+ "/html/img/" + wechatDocValue.getDocimg());
						article.setUrl(sysConfig.getSiteaddress()
								+ "/phonedetail.do?docid="
								+ wechatDocValue.getDocid());
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
				} else {
					String mess = getDefaultMess();
					textMessage.setContent(mess);
					respMessage = MessageUtil.textMessageToXml(textMessage);
				}
				receiveMessValue.setContent(requestMap.get("Content"));
			}
			// 图片消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {
				respContent = getDefaultMess();
				textMessage.setContent(respContent);
				respMessage = MessageUtil.textMessageToXml(textMessage);
				receiveMessValue.setContent(requestMap.get("PicUrl"));
			}
			// 地理位置消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {
				respContent = getDefaultMess();
				textMessage.setContent(respContent);
				respMessage = MessageUtil.textMessageToXml(textMessage);
				receiveMessValue.setContent(requestMap.get("Label"));
			}
			// 链接消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) {
				respContent = getDefaultMess();
				textMessage.setContent(respContent);
				respMessage = MessageUtil.textMessageToXml(textMessage);
				receiveMessValue.setContent(requestMap.get("Url"));
			}
			// 音频消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {
				respContent = getDefaultMess();
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
					textMessage.setContent(getDefaultMess());
					respMessage = MessageUtil.textMessageToXml(textMessage);
				}
				// 取消订阅
				else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {
					// 取消订阅后用户再收不到公众号发送的消息，因此不需要回复消息

				}
				// 自定义菜单点击事件
				else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {
					textMessage.setContent(getDefaultMess());
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
		prams.put("keyword", content);
		try {
			List<AutoResponse> autoResponses = this.autoResponseManager
					.getAutoResponses(prams);
			if(autoResponses!=null&&!autoResponses.isEmpty()){
				return autoResponses.get(0);
			}else{
				return null;	
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	private String getDefaultMess() throws Exception {
		Map<String, Object> prams = new HashMap<String, Object>();
		java.text.SimpleDateFormat format = new java.text.SimpleDateFormat(
				"yyyy-MM-dd");
		prams.put("addtime", format.format(new Date()));
		List<AutoResponse> autoResponses = this.autoResponseManager
				.getAutoResponses(prams);
		if (autoResponses != null && !autoResponses.isEmpty()) {
			Date currentdate = new Date();
			return "今天回复\"" + autoResponses.get(0).getKeyword() + "\"即可欣赏金轮文化网"
					+ format.format(currentdate) + "日的精选文章。";
		} else {
			prams.remove("addtime");
			prams.put("defaultmess", 1);
			autoResponses = this.autoResponseManager.getAutoResponses(prams);
			if (autoResponses != null && !autoResponses.isEmpty()) {
				return autoResponses.get(0).getKeyword();
			} else {
				return "欢迎您关注金轮文化网！";
			}
		}

	}

}
