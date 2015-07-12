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
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
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
		Md5PasswordEncoder md5 = new Md5PasswordEncoder();
		String pass = md5.encodePassword(userValue.getPassword(),
				StaticConstants.encrypekey);
		userValue.setPassword(pass);
		userValue.setHeadurl("nan.jpg");
		// 校验邮箱地址的有效性
		if (userValue.getEmail() == null
				|| userValue.getEmail().equalsIgnoreCase("")) {
			throw new Exception("请填写邮箱地址!");
		}
		Map<String, Object> getparams = new HashMap<String, Object>();
		getparams.put("email", userValue.getEmail());
		List<UserValue> users = this.userManagerDao.getUser(getparams);
		if (users != null && !users.isEmpty()) {
			throw new Exception("此邮箱已注册过账号，不能重复注册!");
		}
		//
		userManagerDao.createUser(userValue);
		/*
		 * MimeMessage mailMessage = this.mailSender.createMimeMessage();
		 * this.mailSender.setUsername("imubwz@126.com");
		 * this.mailSender.setHost("smtp.126.com");
		 * this.mailSender.getPassword(); this.mailSender.getHost(); Properties
		 * prop = new Properties(); prop.put("mail.smtp.auth", "true"); //
		 * 将这个参数设为true，让服务器进行认证,认证用户名和密码是否正确 prop.put("mail.smtp.timeout",
		 * "25000"); mailSender.setJavaMailProperties(prop); MimeMessageHelper
		 * messageHelper = new MimeMessageHelper(mailMessage, true, "utf-8");
		 * messageHelper.setTo(userValue.getEmail());
		 * messageHelper.setSubject(this.sysConfig.getSitename() + "激活账号地址");
		 * String uuid = md5.encodePassword(userValue.getUsername(),
		 * StaticConstants.encrypekey); messageHelper .setFrom(new
		 * InternetAddress(this.mailSender.getUsername())); String mailstr =
		 * "您好！请点击这里<a href=\"" + this.sysConfig.getSiteaddress() +
		 * "/activateUser.do?username=" + userValue.getUsername() + "&id=" +
		 * uuid + "\"</a>激活账号，激活成功以后登陆系统,如果无法点击请把下列地址写浏览器中访问" + ""+
		 * this.sysConfig.getSiteaddress() + "/activateUser.do?username=" +
		 * userValue.getUsername() + "&id=" + uuid;
		 * messageHelper.setText(mailstr, true); mailSender.send(mailMessage);
		 */
	}

	@Override
	public void doUpdateUser(UserValue userValue) throws Exception {
		// TODO Auto-generated method stub
		//
		Map<String, Object> queryUserMap = new HashMap<String, Object>();
		queryUserMap.put("userid", userValue.getUserid());
		List<UserValue> user = this.userManagerDao.getUser(queryUserMap);
		if (userValue.getHeadurl() == null
				&& (user.get(0).getHeadurl() == null || user.get(0)
						.getHeadurl().equalsIgnoreCase(""))) {
			if (userValue.getSex() != null
					|| userValue.getSex().intValue() == 1) {
				userValue.setHeadurl("nan.jpg");
			} else if (userValue.getSex() != null
					|| userValue.getSex().intValue() == 0) {
				userValue.setHeadurl("nv.jpg");
			} else {
				userValue.setHeadurl("nan.jpg");
			}
		}
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
		params.put("username", userValue.getUsername().trim());
		List<UserValue> users = this.userManagerDao.getUser(params);
		if (users == null || users.isEmpty()) {
			throw new Exception("2");
		}
		UserValue sysUserValue = users.get(0);
		/*
		 * if (sysUserValue.getActive() == null ||
		 * sysUserValue.getActive().intValue() == 0) { throw new Exception("5");
		 * }
		 */
		//
		if (sysConfig.getOnline().intValue() == 1
				&& sysUserValue.getOldid() != null) {
			String urlstr = sysConfig.getCheckpassurl() + "/pas.asp?pas="
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
			userValue.setEncripedPass(encripedPass);
			ooutStream.close();
			iniputStream.close();
		} else {
			Md5PasswordEncoder md5 = new Md5PasswordEncoder();
			String pass = md5.encodePassword(userValue.getPassword(),
					StaticConstants.encrypekey);
			userValue.setEncripedPass(pass);
		}
		//
		if (userValue.getUsername()
				.equalsIgnoreCase(sysUserValue.getUsername())) {
			if (userValue.getEncripedPass().equalsIgnoreCase(
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
		Md5PasswordEncoder md5 = new Md5PasswordEncoder();
		List<UserValue> users = this.getUsers(userid, null);
		if (users == null || users.isEmpty()) {
			throw new ManagerException("用户不存在");
		} else {
			UserValue sysUserValue = users.get(0);
			String encripedPass = "";
			if (maillogin.intValue() == 0) {
				//
				if (sysConfig.getOnline().intValue() == 1
						&& sysUserValue.getOldid() != null) {
					String urlstr = sysConfig.getCheckpassurl()
							+ "/pas.asp?pas=" + oldpass;
					URL url = new URL(urlstr);
					URLConnection rulConnection = url.openConnection();
					HttpURLConnection httpUrlConnection = (HttpURLConnection) rulConnection;
					httpUrlConnection.connect();
					InputStream iniputStream = httpUrlConnection
							.getInputStream();
					byte reader[] = new byte[1024];
					int length = 0;
					ByteArrayOutputStream ooutStream = new ByteArrayOutputStream();
					while ((length = iniputStream.read(reader)) != -1) {
						ooutStream.write(reader, 0, length);
					}
					encripedPass = ooutStream.toString();
					ooutStream.close();
					iniputStream.close();
				} else {
					encripedPass = md5.encodePassword(oldpass,
							StaticConstants.encrypekey);
				}
				if (!encripedPass.equalsIgnoreCase(sysUserValue.getPassword())) {
					throw new ManagerException("2");
				}
			}
			pass = md5.encodePassword(pass, StaticConstants.encrypekey);
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
		this.mailSender.setHost("smtp.126.com");
		this.mailSender.getPassword();
		this.mailSender.getHost();
		Properties prop = new Properties();
		prop.put("mail.smtp.auth", "true"); // 将这个参数设为true，让服务器进行认证,认证用户名和密码是否正确
		prop.put("mail.smtp.timeout", "25000");
		mailSender.setJavaMailProperties(prop);
		MimeMessageHelper messageHelper = new MimeMessageHelper(mailMessage,
				true, "utf-8");
		messageHelper.setTo(userValue.getEmail());
		messageHelper.setSubject(this.sysConfig.getSitename() + "找回密码提示");
		String uuid = UUIDMaker.getUUID();
		messageHelper
				.setFrom(new InternetAddress(this.mailSender.getUsername()));
		String mailstr = "<html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" /></head><body><h1>>您好！请在2个小时之内点击这里<a href=\""
				+ this.sysConfig.getSiteaddress()
				+ "/loginmail.do?id="
				+ uuid
				+ "\"</a>登陆系统，并修改密码</h1>如果连接无法点击请把此地址输入浏览器"
				+ this.sysConfig.getSiteaddress()
				+ "/loginmail.do?id="
				+ uuid
				+ "</body></html>";
		messageHelper.setText(mailstr, true);
		mailSender.send(mailMessage);
		userValue.setMailloginid(uuid);
		return userValue;
	}

	@Override
	public void activateUser(String usrname, String activeid) throws Exception {
		// TODO Auto-generated method stub
		Md5PasswordEncoder md5 = new Md5PasswordEncoder();
		String uuid = md5.encodePassword(usrname, StaticConstants.encrypekey);
		if (uuid.equalsIgnoreCase(activeid)) {
			this.userManagerDao.activateUser(usrname);
		} else {
			throw new Exception("激活账号地址不正确！");
		}
	}
}
