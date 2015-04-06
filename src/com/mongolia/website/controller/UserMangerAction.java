package com.mongolia.website.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;
import org.springframework.web.servlet.ModelAndView;

import com.mongolia.website.manager.impls.SysConfig;
import com.mongolia.website.manager.interfaces.UserManager;
import com.mongolia.website.model.DistrictValue;
import com.mongolia.website.model.FriendValue;
import com.mongolia.website.model.ProfessionValue;
import com.mongolia.website.model.UserValue;
import com.mongolia.website.util.ImgeUtil;
import com.mongolia.website.util.StaticConstants;

@Controller
public class UserMangerAction {
	@Autowired
	private UserManager userManager;
	@Autowired
	private SysConfig sysConfig;

	/**
	 * 获取用户信息
	 * 
	 * @param request
	 * @param response
	 * @param map
	 * @return
	 */
	@RequestMapping("/getUsers.do")
	public ModelAndView getUsers(HttpServletRequest request,
			HttpServletResponse response, ModelMap map) {
		try {
			String userid = (String) request.getParameter("userid");
			String username = (String) request.getParameter("username");
			List<UserValue> userValues = userManager.getUsers(userid, username);
			map.put("users", userValues);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ModelAndView("jsonView", map);
	}

	/**
	 * 前台用户通过此处进入系统
	 * 
	 * @param userValue
	 * @param map
	 * @return
	 */
	@RequestMapping("/login.do")
	public ModelAndView userLogin(HttpServletRequest request,
			UserValue userValue, ModelMap map) {
		boolean success = false;
		try {
			System.out.println("obj:" + request.getQueryString());
			// 校验验证码吗
			String validcode = request.getParameter("validcode");
			String sessioncheckcode = (String) request.getSession()
					.getAttribute("validateCode");
			if (!validcode.equalsIgnoreCase(sessioncheckcode)) {
				throw new Exception("1");
			}
			// 调用服务密码加密
			success = this.userManager.doLogin(userValue);
			if (success == true) {
				List<UserValue> userValues = this.userManager.getUsers(
						userValue.getUserid(), userValue.getUsername());
				UserValue userinfo = userValues.get(0);
				userinfo.setLogindate(new Date());
				map.put("userinfo", userinfo);
				UserValue sessionUserValue = userValues.get(0);
				if (sessionUserValue.getUserkind().intValue() == StaticConstants.USER_KIND2) {
					map.put("success", "false");
					return new ModelAndView("jsonView", map);// 管理员用户不让从此url登录
				}
				sessionUserValue.setLogindate(new Date());
				request.getSession().removeAttribute("validateCode");
				request.getSession().setAttribute("user", sessionUserValue);// 在线session
				map.put("success", "true");
				map.put("user", sessionUserValue);
			} else {
				map.put("success", "false");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			map.put("success", "false");
			map.put("mess", ex.getMessage());
		}
		return new ModelAndView("jsonView", map);
	}

	/**
	 * 前台用户通过此处进入系统
	 * 
	 * @param userValue
	 * @param map
	 * @return
	 */
	@RequestMapping("/checkandlogin.do")
	public ModelAndView login(HttpServletRequest request, UserValue userValue,
			ModelMap map) {
		boolean success = false;
		try {
			Integer userkind = 2;
			String validateCode = (String) request.getSession().getAttribute(
					"validateCode");
			boolean sivalid = validateCode.equalsIgnoreCase(userValue
					.getVarifycode());
			success = this.userManager.doLogin(userValue);
			if (success == true && sivalid) {
				List<UserValue> userValues = this.userManager.getUsers(
						userValue.getUserid(), userValue.getUsername());
				map.put("userinfo", userValues.get(0));
				UserValue sessionUserValue = userValues.get(0);
				sessionUserValue.setLogindate(new Date());
				request.getSession().setAttribute("user", sessionUserValue);// 在线session
				map.put("success", "true");
				map.put("user", sessionUserValue);
				userkind = sessionUserValue.getUserkind();
				Object directurl = request.getSession().getAttribute(
						"directurl");
				if (userkind.intValue() == StaticConstants.USER_KIND2) {

					if (directurl != null) {
						// request.getRequestDispatcher(((String)directurl));
						request.getSession().removeAttribute("directurl");
						String desurl = (String) directurl;
						desurl = desurl.split("/")[2];
						return new ModelAndView("redirect:" + desurl, map);

					} else {
						return new ModelAndView("redirect:sitemanagerindex.do",
								map);
					}

				} else {

					if (directurl != null) {
						// request.getRequestDispatcher(((String)directurl));
						request.getSession().removeAttribute("directurl");
						String desurl = (String) directurl;
						desurl = desurl.split("/")[2];
						return new ModelAndView("redirect:" + desurl, map);

					} else {
						map.put("loginsuccess", "loginsuccess");
						return new ModelAndView(
								"redirect:index.do?loginsuccess=1", map);
					}

				}
			} else {
				if (success == false) {
					map.put("success", "false");
					map.put("mess", "1");
				}
				if (sivalid == false) {
					map.put("success", "false");
					map.put("mess", "2");
				}
				return new ModelAndView("userspace/login", map);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			map.put("mess", ex.getMessage());
			return new ModelAndView("userspace/login", map);
		}

	}

	@RequestMapping("/userlogout.do")
	public ModelAndView userlogout(UserValue userValue, ModelMap map) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getRequest();
		request.getSession().removeAttribute("user");// 在线session
		return new ModelAndView("jsonView", map);
	}

	/**
	 * 进入用户注册页面
	 * 
	 * @param UserValue
	 * @param map
	 * @return
	 */
	@RequestMapping("/registe.do")
	public ModelAndView registe(HttpServletRequest request,
			UserValue UserValue, ModelMap map) {
		Integer agentkind = 0;
		String user_agent_kind = request.getHeader("user-agent");
		if (user_agent_kind.indexOf("Chrome") > 0) {
			agentkind = 1;
		} else {
			agentkind = 0;
		}
		map.put("agentkind", agentkind);
		return new ModelAndView("userspace/userregiste", map);
	}

	/**
	 * 用户注册
	 * 
	 * @param UserValue
	 * @param map
	 * @return
	 */
	@RequestMapping("/doregiste.do")
	public ModelAndView doregiste(HttpServletRequest request,
			UserValue userValue, ModelMap map, HttpServletResponse response) {
		try {
			userManager.doCreateUser(userValue);
			List<UserValue> users = this.userManager.getUsers(null,
					userValue.getUsername());
			request.getSession().setAttribute("user", users.get(0));
			/*if (users != null && !users.isEmpty()) {
				// map.put("userinfo", users.get(0));
				// request.getSession().setAttribute("user", users.get(0));
				map.put("errorMess", "成功注册系统,请登录邮箱" + userValue.getEmail()
						+ "激活账号再登陆系统。");
				map.put("success", "0");
				return new ModelAndView("website/error", map);
			}*/
			map.put("success", "1");
			Cookie cookie = new Cookie("userName", userValue.getName());
			cookie.setMaxAge(30 * 24 * 60 * 60);
			response.addCookie(cookie);
			// 发送电子邮件
		} catch (Exception ex) {
			ex.printStackTrace();
			map.put("error", ex.getMessage());
			map.put("errorMess", ex.getMessage());
			map.put("success", "0");
			return new ModelAndView("website/error", map);
		}
		if (this.isphoneagent(request)) {
			return new ModelAndView("redirect:phoneuserindex.do");
		} else {
			return new ModelAndView("forward:doedituserinifo.do", map);
		}

	}

	private boolean isphoneagent(HttpServletRequest request) {
		// Enumeration<String> headers = request.getHeaderNames();
		String user_agent = request.getHeader("user-agent");
		if (user_agent.indexOf("Mobile") > 0) {
			return true;
		}
		return false;
	}

	@RequestMapping("/doedituserinifo.do")
	public ModelAndView doEditUserInifo(HttpServletRequest request, ModelMap map) {
		// 获取用户信息
		UserValue userValue = (UserValue) request.getSession().getAttribute(
				"user");
		String userid = userValue.getUserid();
		userValue = (UserValue) request.getSession().getAttribute("user");
		if (userValue != null && userValue.getUserid().equalsIgnoreCase(userid)) {
			userid = userValue.getUserid();
			map.put("self", "1");
		} else {
			map.put("self", "0");
		}
		List<UserValue> users = this.userManager.getUsers(userid, null);
		try {
			List<DistrictValue> districts = this.userManager.getDistrictValues(
					null, null, "top");
			map.put("districts", districts);
			// 获取用户职业列表
			List<ProfessionValue> professions = this.userManager
					.getProfessionValues(null, null);
			map.put("professions", professions);
			// 获取地区信息
			if (users != null && !users.isEmpty()) {
				map.put("userinfo", users.get(0));
				List<DistrictValue> districts1 = this.userManager
						.getDistrictValues(null, null, null);
				setUserDistrictInfo(users.get(0), districts1);
				DistrictValue nullDistrictValue = new DistrictValue();
				nullDistrictValue.setDistrictcode("99");
				nullDistrictValue.setDistrictname("");
				if (users.get(0).getProvince() != null) {
					List<DistrictValue> hsiens = this.userManager
							.getDistrictValues(null, userValue.getProvince(),
									null);
					hsiens.add(nullDistrictValue);
					map.put("hsiens", hsiens);
				} else {
					List<DistrictValue> hsiens = new ArrayList<DistrictValue>();
					hsiens.add(nullDistrictValue);
					map.put("hsiens", hsiens);
				}
				if (users.get(0).getNowprovince() != null) {
					List<DistrictValue> nowhsien = this.userManager
							.getDistrictValues(null,
									userValue.getNowprovince(), null);
					nowhsien.add(nullDistrictValue);
					map.put("nowhsien", nowhsien);
				} else {
					List<DistrictValue> hsiens = new ArrayList<DistrictValue>();
					hsiens.add(nullDistrictValue);
					map.put("nowhsien", hsiens);
				}
				request.getSession().setAttribute("user", users.get(0));// 在线session
				// 所有问题列表已json方式输出
				JSONObject json = new JSONObject();
				json.put("districts", districts1);
				map.put("districtsdata", json.toString());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return new ModelAndView("userspace/edituserinfo", map);
	}

	/**
	 * 修改用户信息
	 * 
	 * @param userValue
	 * @param map
	 * @return
	 */
	@RequestMapping("/edituserinfo.do")
	public ModelAndView edituserinfo(HttpServletRequest request, ModelMap map,
			UserValue userValue) {
		try {
			UserValue sessionUser = (UserValue) request.getSession()
					.getAttribute("user");// 在线session
			userValue.setUserid(sessionUser.getUserid());
			if (userValue.getSexsel() != null
					&& !userValue.getSexsel().equalsIgnoreCase("")) {
				userValue.setSex(Integer.parseInt(userValue.getSexsel()));
			}
			if (userValue.getBlogclasssel() != null
					&& !userValue.getBlogclasssel().equalsIgnoreCase("")) {
				userValue.setBlogclass(Integer.parseInt(userValue
						.getBlogclasssel()));
			}
			// 获取头部图像
			String path = request.getSession().getServletContext()
					.getRealPath("/html/userhead");
			String imgnamesm = "";
			imgnamesm = userValue.getUserid() + ".jpg";
			if (userValue.getImg() != null && userValue.getImg().length != 0) {
				ImgeUtil.CompressPic(userValue.getImg(), path, imgnamesm,
						StaticConstants.IMGWIDTHSM, StaticConstants.IMGHEIGHTSM);
				userValue.setHeadurl(imgnamesm);
			} else {
				userValue.setHeadurl(null);
			}
			userManager.doUpdateUser(userValue);
			List<UserValue> sessions = this.userManager.getUsers(
					userValue.getUserid(), null);
			request.getSession().setAttribute("user", sessions.get(0));
			map.put("success", "1");
		} catch (Exception ex) {
			ex.printStackTrace();
			map.put("error", ex.getMessage());
			map.put("success", "0");
			return new ModelAndView("jsonView", map);
		}
		return new ModelAndView("redirect:gouserindex.do", map);

	}

	/**
	 * 到用户信息修改界面
	 * 
	 * @param userValue
	 * @param map
	 * @return
	 */
	@RequestMapping("/toedituserinfo.do")
	public ModelAndView toedituserinfo(UserValue userValue, ModelMap map) {
		return new ModelAndView("userspace/edituserinfo", map);
	}

	/**
	 * 校验使用是否已登录
	 * 
	 * @param UserValue
	 * @param map
	 * @return
	 */
	@RequestMapping("/checklogin.do")
	public ModelAndView checklogin(ModelMap map) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getRequest();
		Object uservalue = request.getSession().getAttribute("user");
		if (uservalue == null) {
			map.put("login", "false");
		} else if (uservalue != null
				&& (((UserValue) uservalue)).getUserkind().intValue() == StaticConstants.USER_KIND2) {
			map.put("login", "false");
		} else {
			UserValue userValue = (UserValue) uservalue;
			List<UserValue> usrevalues = this.userManager.getUsers(
					userValue.getUserid(), userValue.getUsername());
			UserValue currentUserValue = usrevalues.get(0);
			currentUserValue.setLogindate(new Date());
			map.put("userinfo", currentUserValue);
			map.put("login", "true");
		}
		return new ModelAndView("jsonView", map);
	}

	/**
	 * 获取用户机器产生的验证码
	 * 
	 * @param map
	 * @return
	 */
	@RequestMapping("/getverifycode.do")
	public ModelAndView getVerifyCode(ModelMap map) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getRequest();
		map.put("validateCode",
				request.getSession().getAttribute("validateCode"));
		return new ModelAndView("jsonView", map);
	}

	/**
	 * 校验使用此编码的用户是否存在
	 * 
	 * @param UserValue
	 * @param map
	 * @return
	 */
	@RequestMapping("/checkuserisexists.do")
	public ModelAndView checkUserIsExists(HttpServletRequest request,
			UserValue UserValue, ModelMap map) {
		List<UserValue> userValues = userManager.getUsers(
				UserValue.getUserid(), UserValue.getUsername());
		if (userValues == null || userValues.isEmpty()) {
			map.put("exists", "0");
		} else {
			map.put("exists", "1");
		}
		Cookie cookies[] = request.getCookies();
		for (Cookie cookie : cookies) {
			if (cookie.getName().equalsIgnoreCase("userName")
					&& cookie.getValue() != null) {
				map.put("exists", "2");
				break;
			}
		}
		return new ModelAndView("jsonView", map);
	}

	/**
	 * 
	 * @param UserValue
	 * @param map
	 * @return
	 */
	@RequestMapping("/checkusernameandpassword.do")
	public ModelAndView checkUserNameAndPassword(UserValue UserValue,
			ModelMap map) throws Exception {
		List<UserValue> userValues = userManager.getUsers(
				UserValue.getUserid(), UserValue.getUsername());
		if (userValues == null || userValues.isEmpty()) {
			map.put("result", "0");// 用户不存在
		} else {
			UserValue sysUserValue = userValues.get(0);
			if (sysConfig.getOnline().intValue() == 1
					&& sysUserValue.getOldid() != null) {
				String urlstr = sysConfig.getCheckpassurl() + "/pas.asp?pas="
						+ UserValue.getPassword();
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
				UserValue.setEncripedPass(encripedPass);
				ooutStream.close();
				iniputStream.close();
			} else {
				Md5PasswordEncoder md5 = new Md5PasswordEncoder();
				String pass = md5.encodePassword(UserValue.getPassword(),
						StaticConstants.encrypekey);
				UserValue.setEncripedPass(pass);
			}

			if (!sysUserValue.getPassword().equalsIgnoreCase(
					UserValue.getEncripedPass())) {
				map.put("result", "1");// 用户密码不正确
			} else {
				map.put("result", "2");// 用户名和密码都正确
			}
		}

		return new ModelAndView("jsonView", map);
	}

	@RequestMapping("/tologin.do")
	public ModelAndView toLogin(ModelMap map) {
		// 如果处于登陆状态则直接进入博客主页
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getRequest();
		UserValue sessionUser = (UserValue) request.getSession().getAttribute(
				"user");// 在线session
		if (sessionUser == null) {
			return new ModelAndView("userspace/login", map);
		} else {
			// if (this.isphoneagent(request)) {
			// return new ModelAndView("redirect:phoneuserindex.do");
			// }else{
			return new ModelAndView("redirect:gouserindex.do", map);
			// }

		}
	}

	/**
	 * 
	 * @param UserValue
	 * @param map
	 * @return
	 */
	@RequestMapping("/addfriend.do")
	public ModelAndView addfriend(HttpServletRequest request, ModelMap map) {
		try {
			UserValue sessionUser = (UserValue) request.getSession()
					.getAttribute("user");// 在线session
			String userid = request.getParameter("userid");
			String agree = request.getParameter("agree");
			String messageid = request.getParameter("messageid");
			String currentuserid = sessionUser.getUserid();
			List<UserValue> users = this.userManager.getUsers(userid, null);
			UserValue uservalue = users.get(0);
			FriendValue friendValue = new FriendValue();
			friendValue.setFriendid(uservalue.getUserid());
			friendValue.setFriendname(uservalue.getArtname());
			friendValue.setUserid(currentuserid);
			this.userManager.doAddFriendAndConfirmMess(friendValue, messageid,
					Integer.parseInt(agree));
			if (Integer.parseInt(agree) == 1) {
				map.put("mess", "01");
			} else {
				map.put("mess", "02");
			}

		} catch (Exception ex) {
			map.put("mess", ex.getMessage());
		}
		return new ModelAndView("jsonView", map);
	}

	@RequestMapping("/delfriend.do")
	public ModelAndView delfriend(HttpServletRequest request, ModelMap map) {
		try {
			UserValue sessionUser = (UserValue) request.getSession()
					.getAttribute("user");// 在线session
			String friendid = request.getParameter("friendid");

			this.userManager.doDelFriend(sessionUser.getUserid(), friendid);
			map.put("mess", "01");
		} catch (Exception ex) {
			map.put("mess", ex.getMessage());
		}
		return new ModelAndView("jsonView", map);
	}

	@RequestMapping("/getsmheadimge.do")
	public ResponseEntity<byte[]> getSmHeadImge(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String userid = request.getParameter("userid");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userid", userid);
		List<UserValue> users = new ArrayList<UserValue>();
		try {
			users = this.userManager.getUsers(userid, null);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		UserValue userValue = users.get(0);

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.setContentType(MediaType.IMAGE_JPEG);
		// 如果没有头像则返回默认头像
		if (userValue.getHeadimgsm() != null) {
			responseHeaders.setContentLength(userValue.getHeadimgsm().length);
		} else {
			//
			String path = request.getSession().getServletContext()
					.getRealPath("/img");
			File imgFile = new File(path, "nan.png");
			if (userValue.getSex() != null
					&& userValue.getSex().intValue() == 1) {
				imgFile = new File(path, "nan.png");

			} else if (userValue.getSex() != null
					&& userValue.getSex().intValue() == 0) {
				imgFile = new File(path, "nv.png");
			}
			FileInputStream xxstream = new FileInputStream(imgFile);
			int length = xxstream.available();
			byte[] reader = new byte[length];
			xxstream.read(reader);
			userValue.setHeadimgsm(reader);
			xxstream.close();
		}
		responseHeaders
				.setCacheControl("no-store, no-cache, must-revalidate, post-check=0, pre-check=0");
		responseHeaders.setPragma("no-cache");
		return new ResponseEntity<byte[]>(userValue.getHeadimgsm(),
				responseHeaders, HttpStatus.OK);
	}

	@RequestMapping("/getheadimge.do")
	public ResponseEntity<byte[]> getHeadImge(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String userid = request.getParameter("userid");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userid", userid);
		List<UserValue> users = new ArrayList<UserValue>();
		try {
			users = this.userManager.getUsers(userid, null);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		UserValue userValue = users.get(0);
		HttpHeaders responseHeaders = new HttpHeaders();
		if (userValue.getHeadimg() != null) {
			responseHeaders.setContentLength(userValue.getHeadimg().length);
		} else {
			//
			String path = request.getSession().getServletContext()
					.getRealPath("/img");
			File imgFile = new File(path, "nan.png");
			if (userValue.getSex() != null
					&& userValue.getSex().intValue() == 1) {
				imgFile = new File(path, "nan.png");

			} else if (userValue.getSex() != null
					&& userValue.getSex().intValue() == 0) {
				imgFile = new File(path, "nv.png");
			}
			FileInputStream xxstream = new FileInputStream(imgFile);
			int length = xxstream.available();
			byte[] reader = new byte[length];
			xxstream.read(reader);
			userValue.setHeadimg(reader);
			xxstream.close();
		}
		responseHeaders.setContentType(MediaType.IMAGE_JPEG);
		responseHeaders.setContentLength(userValue.getHeadimg().length);
		responseHeaders
				.setCacheControl("no-store, no-cache, must-revalidate, post-check=0, pre-check=0");
		responseHeaders.setPragma("no-cache");
		return new ResponseEntity<byte[]>(userValue.getHeadimg(),
				responseHeaders, HttpStatus.OK);
	}

	/**
	 * 修改用户密码(修改完重新登录)
	 * 
	 * @param request
	 * @param map
	 * @return
	 */
	@RequestMapping("/modifyuserpass.do")
	public ModelAndView modifyuserpass(HttpServletRequest request, ModelMap map) {
		try {
			UserValue sessionUser = (UserValue) request.getSession()
					.getAttribute("user");// 在线session
			if (sessionUser == null) {
				map.put("mess", "3");
				return new ModelAndView("jsonView", map);
			}
			String userid = sessionUser.getUserid();
			String pass = request.getParameter("pass");
			String oldpass = request.getParameter("oldpass");
			this.userManager.doModifyPass(userid, pass, oldpass,
					sessionUser.getMaillogin());
			map.put("mess", "1");
		} catch (Exception ex) {
			map.put("mess", ex.getMessage());
		}
		return new ModelAndView("jsonView", map);
	}

	/**
	 * 
	 * @param userValue
	 * @param districts
	 */
	private void setUserDistrictInfo(UserValue userValue,
			List<DistrictValue> districts) {
		for (DistrictValue districtValue : districts) {
			if (districtValue.getDistrictcode().equalsIgnoreCase(
					userValue.getProvince())) {
				//
				if (districtValue.getDistrictcode().length() > 2) {
					userValue
							.setParentcode1(districtValue.getDistrictcode()
									.substring(
											0,
											districtValue.getDistrictcode()
													.length() - 2));
				}
			}
			if (districtValue.getDistrictcode().equalsIgnoreCase(
					userValue.getNowprovince())) {
				if (districtValue.getDistrictcode().length() > 2) {
					userValue
							.setParentcode(districtValue.getDistrictcode()
									.substring(
											0,
											districtValue.getDistrictcode()
													.length() - 2));
				}
			}

		}

	}

	@RequestMapping("/getpasswithmail.do")
	public ModelAndView getpasswith(HttpServletRequest request, ModelMap map) {
		try {
			String username = request.getParameter("username");
			String validcode = request.getParameter("validcode");
			if (username == null || username.equalsIgnoreCase("")) {
				map.put("mess", "2");
				return new ModelAndView("jsonView", map);
			}
			if (validcode == null || validcode.equalsIgnoreCase("")) {
				map.put("mess", "3");
				return new ModelAndView("jsonView", map);
			}
			UserValue uservalue = this.userManager.getmaillogincode(username);
			String maillogincode = uservalue.getMailloginid();
			request.getServletContext().setAttribute(maillogincode, username);
			request.getServletContext().setAttribute(username + "time",
					System.currentTimeMillis());
			request.getServletContext().setAttribute(username, username);
			map.put("mailaddress", uservalue.getEmail());
			map.put("mess", "1");
		} catch (Exception ex) {
			ex.printStackTrace();
			map.put("mess", ex.getMessage());
		}
		return new ModelAndView("jsonView", map);
	}

	@RequestMapping("/loginmail.do")
	public ModelAndView loginmail(HttpServletRequest request, ModelMap map) {
		String id = request.getParameter("id");
		Object username = request.getServletContext().getAttribute(id);
		if (username == null) {
			return new ModelAndView("redirect:tologin.do");
		} else {
			Long createtime = (Long) request.getServletContext().getAttribute(
					username + "time");
			if (System.currentTimeMillis() - createtime > 2 * 60 * 60 * 1000) {// 超过2小时则不让登录
				return new ModelAndView("redirect:tologin.do");
			} else {
				String username_name = (String) request.getServletContext()
						.getAttribute("" + username);
				List<UserValue> users = this.userManager.getUsers(null,
						username_name);
				if (users == null || users.isEmpty()) {
					return new ModelAndView("redirect:tologin.do");
				} else {
					UserValue sessionUserValue = users.get(0);
					sessionUserValue.setLogindate(new Date());
					sessionUserValue.setMaillogin(1);
					request.getSession().setAttribute("user", sessionUserValue);// 在线session
					// 清楚mail登录秘钥
					// request.getServletContext().removeAttribute(id);
					return new ModelAndView("redirect:gouserindex.do?userid="
							+ sessionUserValue.getUserid());
				}

			}
		}

	}

	@InitBinder
	protected void initBinder(WebDataBinder binder) throws ServletException {
		binder.registerCustomEditor(byte[].class,
				new ByteArrayMultipartFileEditor());
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, false));
	}

	@RequestMapping("/activateUser.do")
	public ModelAndView activateUser(HttpServletRequest request,
			HttpServletResponse response, ModelMap map) {
		try {
			String userid = (String) request.getParameter("id");
			String username = (String) request.getParameter("username");
			this.userManager.activateUser(username, userid);
			map.put("errorMess", "成功激活账号，请到主页进行登陆系统！");
			map.put("success", "0");
		} catch (Exception ex) {
			ex.printStackTrace();
			map.put("errorMess", ex.getMessage());
		}
		return new ModelAndView("website/error", map);
	}

}
