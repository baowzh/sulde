package com.mongolia.website.manager.impls;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.mongolia.website.dao.interfaces.UserManagerDao;
import com.mongolia.website.dao.interfaces.WebResourceDao;
import com.mongolia.website.manager.ManagerException;
import com.mongolia.website.manager.interfaces.UserManager;
import com.mongolia.website.model.DistrictValue;
import com.mongolia.website.model.FriendValue;
import com.mongolia.website.model.ProfessionValue;
import com.mongolia.website.model.UserValue;
import com.mongolia.website.util.StaticConstants;
import com.mongolia.website.util.UUIDMaker;

@Service("userManager")
public class UserManagerImpl implements UserManager {
	@Autowired
	private UserManagerDao userManagerDao;
	@Autowired
	private WebResourceDao webResourceDao;
	@Autowired
	private SysConfig sysConfig;
	@Autowired
	private JavaMailSenderImpl mailSender;

	@Override
	public void doCreateUser(UserValue userValue) throws Exception {
		// TODO Auto-generated method stub
		// 填充用户相关信息
		userValue.setUserid(UUIDMaker.getUUID());
		userValue.setUserkind(StaticConstants.USER_KIND1);
		userValue.setRegdate(new Date());
		userManagerDao.createUser(userValue);
	}

	@Override
	public void doUpdateUser(UserValue userValue) throws Exception {
		// TODO Auto-generated method stub
		userManagerDao.updateUser(userValue);
	}

	@Override
	public List<UserValue> getUsers(String userid, String username) {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userid", userid);
		params.put("username", username);
		return userManagerDao.getUser(params);
	}

	@Override
	public boolean doLogin(UserValue userValue) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("username", userValue.getUsername());
		List<UserValue> users = this.userManagerDao.getUser(params);
		if (users == null || users.isEmpty()) {
			throw new Exception("2");
		}
		UserValue sysUserValue = users.get(0);
		//
		if (sysConfig.getOnline().intValue() == 1
				&& sysUserValue.getOldid() != null) {
			String urlstr = "http://www.altanhurd.com/asp/pas.asp?pas="
					+ userValue.getPassword();
			URL url = new URL(urlstr);
			URLConnection rulConnection = url.openConnection();
			HttpURLConnection httpUrlConnection = (HttpURLConnection) rulConnection;
			httpUrlConnection.connect();
			InputStream iniputStream = httpUrlConnection.getInputStream();
			byte reader[] = new byte[1024];
			int length = 0;
			ByteArrayOutputStream ooutStream = new ByteArrayOutputStream();
			while ((length = iniputStream.read(reader)) != -1) {
				ooutStream.write(reader, 0, length);
			}
			String encripedPass = ooutStream.toString();
			userValue.setPassword(encripedPass);
			ooutStream.close();
			iniputStream.close();
		}
		//
		if (userValue.getUsername()
				.equalsIgnoreCase(sysUserValue.getUsername())) {
			if (userValue.getPassword().equalsIgnoreCase(
					sysUserValue.getPassword())) {
				//
				sysUserValue.setLogindate(new Date());
				this.userManagerDao.updateUser(sysUserValue);
				return true;
			} else {
				throw new Exception("3");
			}
		}
		return false;
	}

	@Override
	public void doAddFriendAndConfirmMess(FriendValue friendValue,
			String messid, Integer agree) throws Exception {
		// TODO Auto-generated method stub
		// 已经存在则不添加
		if (agree.intValue() == 1) {
			List<FriendValue> friends = webResourceDao.getFriendValues(
					friendValue.getFriendid(), friendValue.getUserid(), null);
			if (friends != null && !friends.isEmpty()) {
				return;
			}
			// 把对方价位自己的朋友，同事把自己价位对方的朋友
			FriendValue friendValue1 = new FriendValue();
			friendValue1.setFriendid(friendValue.getUserid());
			friendValue1.setUserid(friendValue.getFriendid());
			Map<String, Object> queryparams = new HashMap<String, Object>();
			queryparams.put("userid", friendValue.getUserid());
			List<UserValue> users = this.userManagerDao.getUser(queryparams);
			users.get(0).getArtname();
			friendValue1.setFriendname(users.get(0).getArtname());
			this.userManagerDao.addFriend(friendValue);
			this.userManagerDao.addFriend(friendValue1);
		}//
			// 信息只为已读
		this.webResourceDao.confiremMess(messid);
	}

	@Override
	public void doDeleteUser(String[] userids) throws Exception {
		// TODO Auto-generated method stub
		for (int i = 0; i < userids.length; i++) {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("useid", userids[i]);
			// 校验用户室友为管理员则不让删除
			List<UserValue> users = this.getUsers(userids[i], null);
			if (users != null && !users.isEmpty()) {
				UserValue userValue = users.get(0);
				if (userValue.getUserkind().intValue() == 2) {
					throw new ManagerException("管理员用户不可删除!");
				}
				String userid = userids[i];
				this.webResourceDao.deleteDocByUserid(userid);
				this.webResourceDao.deleteFriendByUserid(userid);
				this.webResourceDao.deleteImgByUserid(userid);
				this.webResourceDao.deleteImgGroupByUserid(userid);
				this.webResourceDao.deleteMarkeResourceByUserid(userid);
				this.webResourceDao.deleteMessageByUserid(userid);
				this.webResourceDao.deleteShareResourceByUserid(userid);
				this.webResourceDao.deleteVisitLogByUserid(userid);
				this.userManagerDao.deleteUser(params);
			}

		}
	}

	@Override
	public void doReUseUser(String[] userids) throws Exception {
		// TODO Auto-generated method stub
		for (int i = 0; i < userids.length; i++) {

			this.userManagerDao.reuseUser(userids[i]);
		}

	}

	@Override
	public void doStopUser(String[] userids) throws Exception {
		// TODO Auto-generated method stub
		for (int i = 0; i < userids.length; i++) {
			List<UserValue> users = this.getUsers(userids[i], null);
			if (users != null && !users.isEmpty()) {
				UserValue userValue = users.get(0);
				if (userValue.getUserkind().intValue() == 2) {
					throw new ManagerException("管理员用户不可停用!");
				}
			}
			this.userManagerDao.stopUser(userids[i]);
		}
	}

	@Override
	public void doModifyPass(String userid, String pass, String oldpass,
			Integer maillogin) throws Exception {
		// TODO Auto-generated method stub
		List<UserValue> users = this.getUsers(userid, null);
		if (users == null || users.isEmpty()) {
			throw new ManagerException("用户不存在");
		} else {
			if (maillogin.intValue() == 0) {
				UserValue userValue = users.get(0);
				if (!oldpass.equalsIgnoreCase(userValue.getPassword())) {
					throw new ManagerException("2");
				}
			}
			this.userManagerDao.modifyUserPass(userid, pass);
		}
	}

	@Override
	public void doDelFriend(String userid, String friendid) throws Exception {
		// TODO Auto-generated method stub
		this.userManagerDao.delFriend(userid, friendid);
		this.userManagerDao.delFriend(friendid, userid);
	}

	@Override
	public List<DistrictValue> getDistrictValues(String districtcode,
			String parentcode, String top) throws Exception {
		// TODO Auto-generated method stub
		return this.userManagerDao.getDistrictValues(districtcode, parentcode,
				top);
	}

	@Override
	public List<ProfessionValue> getProfessionValues(String professioncode,
			String professionname) throws Exception {
		// TODO Auto-generated method stub
		return this.userManagerDao.getProfessionValues(professioncode,
				professionname);
	}

	@Override
	public UserValue getmaillogincode(String username) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("username", username);
		List<UserValue> users = this.userManagerDao.getUser(params);
		if (users == null || users.isEmpty()) {
			throw new Exception("4");
		}
		UserValue userValue = users.get(0);
		if (userValue.getEmail() == null
				|| userValue.getEmail().equalsIgnoreCase("")) {
			throw new Exception("5");
		}
		MimeMessage mailMessage = this.mailSender.createMimeMessage();
		this.mailSender.setUsername("imubwz@126.com");
		this.mailSender.setPassword("bwZ24%");
		this.mailSender.setHost("smtp.126.com");
		this.mailSender.getPassword();
		this.mailSender.getHost();
		Properties prop = new Properties();
		prop.put("mail.smtp.auth", "true"); // 将这个参数设为true，让服务器进行认证,认证用户名和密码是否正确
		prop.put("mail.smtp.timeout", "25000");
		mailSender.setJavaMailProperties(prop);
		MimeMessageHelper messageHelper = new MimeMessageHelper(mailMessage,true,"utf-8");
		messageHelper.setTo(userValue.getEmail());
		messageHelper.setSubject(this.sysConfig.getSitename() + "找回密码提示");
		String uuid = UUIDMaker.getUUID();
		messageHelper
				.setFrom(new InternetAddress(this.mailSender.getUsername()));
		String mailstr = "<html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" /></head><body><h1>>您好！请在2个小时之内点击<a href=\""
				+ this.sysConfig.getSiteaddress()
				+ "/loginmail.do?id="
				+ uuid
				+ "\"这里</a>登陆系统，并修改密码</h1></body></html>";
		messageHelper.setText(mailstr, true);
		mailSender.send(mailMessage);
		userValue.setMailloginid(uuid);
		return userValue;
	}

}
