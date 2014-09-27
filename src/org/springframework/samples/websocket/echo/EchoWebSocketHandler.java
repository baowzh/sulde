package org.springframework.samples.websocket.echo;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

/**
 * Echo messages by implementing a Spring {@link WebSocketHandler} abstraction.
 */
public class EchoWebSocketHandler extends TextWebSocketHandler  {

	private final EchoService echoService;
	private List<String> sessionids = new ArrayList<String>();
	private Map<String, WebSocketSession> sessions = new HashMap<String, WebSocketSession>();
	private Map<String, String> idkeys = new HashMap<String, String>();
	@Autowired
	public EchoWebSocketHandler(EchoService echoService) {
		this.echoService = echoService;
	}

	@Override
	public void handleTextMessage(WebSocketSession session, TextMessage message)
			throws Exception {
		String reply = this.echoService.getMessage(message.getPayload());
		
		reply = replacespaceStr(reply);
		try {
			JSONObject json1 = JSONObject.fromObject(reply);
			JSONObject messageObject = (JSONObject) json1.get("message");
			String sourceid = (String) messageObject.get("sourceid");
			String destid = (String) messageObject.get("destid");
			String content = (String) messageObject.get("content");
			for (String userid : sessionids) {
				WebSocketSession sessioni = sessions.get(userid);
				if (userid.equalsIgnoreCase(destid)) {
					String mess = getJsonMess(destid, sourceid, content, null);
					if (sessioni.isOpen()) {
						sessioni.sendMessage(new TextMessage(mess));
					}
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void afterConnectionEstablished(WebSocketSession session)
			throws Exception {
		// TODO Auto-generated method stub
		URI url = session.getUri();
		String queryString = url.getQuery();
		String sessionuserid = "";
		if (queryString != null) {
			String key = queryString.split("=")[0];
			String value = queryString.split("=")[1];
			if ("managerid".equalsIgnoreCase(key)) {
				sessionids.add(value);
				sessionuserid = value;
				sessions.put(value, session);
				idkeys.put(session.getId(), value);
			}
		}
		super.afterConnectionEstablished(session);
		// 给所有在线用户发送闪现通知---相关联的用户
		for (String userid : sessionids) {
			WebSocketSession sessioni = sessions.get(userid);
			// if (!userid.equalsIgnoreCase(sessionuserid)) {
			String mess = getJsonMess(userid, sessionuserid, " hello 我来了！",
					sessionids);
			if (sessioni.isOpen()) {
				sessioni.sendMessage(new TextMessage(mess));
			}
			// }
		}
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session,
			CloseStatus status) throws Exception {
		// TODO Auto-generated method stub
		super.afterConnectionClosed(session, status);
		String sessionkey = idkeys.get(session.getId());
		sessions.remove(sessionkey);
		this.sessionids.remove(sessionkey);
		session.getHandshakeHeaders();
	}

	

	/**
	 * 组织json 格式的消息
	 * 
	 * @param destid
	 * @param sourceid
	 * @param mess
	 * @return
	 */
	private String getJsonMess(String destid, String sourceid, String mess,
			List<String> sessionids) {
		Map<String, Object> messMap = new HashMap<String, Object>();
		messMap.put("destid", destid);
		messMap.put("sourceid", sourceid);
		messMap.put("content", mess);
		JSONObject json = new JSONObject();
		if (sessionids != null) {
			// messMap.put("userids", sessionids);
			//List<String> ids=new ArrayList<String>();
			//ids.addAll(sessionids);
			//ids.remove(sourceid);
			json.put("userids", sessionids);
		}
		json.put("message", messMap);
		return json.toString();
	}

	/**
	 * 
	 * @param jsonStr
	 * @return
	 */
	private String replacespaceStr(String jsonStr) {
		System.out.println(jsonStr);
	    jsonStr =jsonStr.replaceAll("\n", "<br>");
		return jsonStr;
	}

}
