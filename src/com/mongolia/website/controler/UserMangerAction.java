package com.mongolia.website.controler;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.mongolia.website.manager.interfaces.UserManager;
import com.mongolia.website.model.FriendValue;
import com.mongolia.website.model.UserValue;
import com.mongolia.website.util.ImgeUtil;
import com.mongolia.website.util.StaticConstants;
import com.mongolia.website.util.UUIDMaker;

@Controller
public class UserMangerAction {
	@Autowired
	private UserManager userManager;

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
						return new ModelAndView("redirect:index.do", map);
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
			map.put("mess", "3");
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
			UserValue userValue, ModelMap map) {
		try {
			userManager.doCreateUser(userValue);
			List<UserValue> users = this.userManager.getUsers(null,
					userValue.getUsername());
			if (users != null && !users.isEmpty()) {
				map.put("userinfo", users.get(0));
				request.getSession().setAttribute("user", users.get(0));
			}
			map.put("success", "1");

		} catch (Exception ex) {
			ex.printStackTrace();
			map.put("error", ex.getMessage());
			map.put("success", "0");
			return new ModelAndView("jsonView", map);
		}
		return new ModelAndView("forward:doedituserinifo.do", map);
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
		if (users != null && !users.isEmpty()) {
			map.put("userinfo", users.get(0));
			request.getSession().setAttribute("user", users.get(0));// 在线session
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
			// 获取头部图像
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			MultiValueMap file = multipartRequest.getMultiFileMap();
			String path = request.getSession().getServletContext()
					.getRealPath("/html/img");
			Set<String> set = file.keySet();
			Iterator iterator = set.iterator();
			while (iterator.hasNext()) {
				String name = (String) iterator.next();
				List files = (List) file.get(name);
				String imgname = "";
				String imgnamesm = "";

				for (int i = 0; i < files.size(); i++) {
					CommonsMultipartFile commonsMultipartFile = (CommonsMultipartFile) files
							.get(i);
					String OriginalFilename = commonsMultipartFile
							.getOriginalFilename();
					OriginalFilename = OriginalFilename.split("\\.")[1];
					imgname = UUIDMaker.getUUID() + "." + OriginalFilename;
					imgnamesm = UUIDMaker.getUUID() + "." + OriginalFilename;
					ImgeUtil.CompressPic(commonsMultipartFile.getBytes(), path,
							imgname);
					ImgeUtil.CompressPic(commonsMultipartFile.getBytes(), path,
							imgnamesm, StaticConstants.IMGWIDTHSM,
							StaticConstants.IMGHEIGHTSM);
					//
					File headimgFile = new File(path, imgname);
					FileInputStream stream = new FileInputStream(headimgFile);
					int length = stream.available();
					byte reader1[] = new byte[length];
					stream.read(reader1);
					stream.close();
					File headimgFilesm = new File(path, imgname);
					FileInputStream streamsm = new FileInputStream(
							headimgFilesm);
					length = streamsm.available();
					byte reader2[] = new byte[length];
					streamsm.read(reader2);
					streamsm.close();
					userValue.setHeadimg(reader1);
					userValue.setHeadimgsm(reader2);
				}
			}
			//
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

	@RequestMapping("/userheadimgedit.do")
	public ModelAndView editUserHeadImg(HttpServletRequest request, ModelMap map) {
		//
		try {
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			MultiValueMap file = multipartRequest.getMultiFileMap();
			String path = request.getSession().getServletContext()
					.getRealPath("/html/img");
			Set<String> set = file.keySet();
			Iterator iterator = set.iterator();
			while (iterator.hasNext()) {
				String name = (String) iterator.next();
				List files = (List) file.get(name);
				String imgname = "";
				String imgnamesm = "";
				UserValue sessionUser = (UserValue) request.getSession()
						.getAttribute("user");// 在线session
				try {
					for (int i = 0; i < files.size(); i++) {
						CommonsMultipartFile commonsMultipartFile = (CommonsMultipartFile) files
								.get(i);
						String OriginalFilename = commonsMultipartFile
								.getOriginalFilename();
						OriginalFilename = OriginalFilename.split("\\.")[1];
						imgname = UUIDMaker.getUUID() + "." + OriginalFilename;
						imgnamesm = UUIDMaker.getUUID() + "."
								+ OriginalFilename;
						ImgeUtil.CompressPic(commonsMultipartFile.getBytes(),
								path, imgname);
						ImgeUtil.CompressPic(commonsMultipartFile.getBytes(),
								path, imgnamesm, StaticConstants.IMGWIDTHSM,
								StaticConstants.IMGHEIGHTSM);
						//
						File headimgFile = new File(path, imgname);
						FileInputStream stream = new FileInputStream(
								headimgFile);
						int length = stream.available();
						byte reader1[] = new byte[length];
						stream.read(reader1);
						stream.close();
						File headimgFilesm = new File(path, imgname);
						FileInputStream streamsm = new FileInputStream(
								headimgFilesm);
						length = streamsm.available();
						byte reader2[] = new byte[length];
						streamsm.read(reader2);
						streamsm.close();
						sessionUser.setHeadimg(reader1);
						sessionUser.setHeadimgsm(reader2);
					}
					// 修改用户头像
					userManager.doUpdateUser(sessionUser);
					// imgValue.setImgurl("img/" + imgname);
				} catch (Exception ex) {
					ex.printStackTrace();
					return new ModelAndView("sitemanager/error", map);
				}
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ModelAndView("userspace/userheadimgedit", map);
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
	public ModelAndView checkUserIsExists(UserValue UserValue, ModelMap map) {
		List<UserValue> userValues = userManager.getUsers(
				UserValue.getUserid(), UserValue.getUsername());
		if (userValues == null || userValues.isEmpty()) {
			map.put("exists", "0");
		} else {
			map.put("exists", "1");
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
			ModelMap map) {
		List<UserValue> userValues = userManager.getUsers(
				UserValue.getUserid(), UserValue.getUsername());
		if (userValues == null || userValues.isEmpty()) {
			map.put("result", "0");// 用户不存在
		} else {
			// 校验密码是否正确
			UserValue userValuei = userValues.get(0);
			if (!userValuei.getPassword().equalsIgnoreCase(
					UserValue.getPassword())) {
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
		// HttpServletRequest request = ((ServletRequestAttributes)
		// RequestContextHolder
		// .getRequestAttributes()).getRequest();
		// UserValue sessionUser = (UserValue)
		// request.getSession().getAttribute(
		// "user");// 在线session
		// if (sessionUser == null) {
		return new ModelAndView("userspace/login", map);
		// } else {
		// return blogManagerAction.gointoroom(request, map);
		// }
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
			String userid = sessionUser.getUserid();
			String username = request.getParameter("username");
			String pass = request.getParameter("pass");
			String oldpass = request.getParameter("oldpass");
			if (!sessionUser.getUsername().equalsIgnoreCase(username)) {
				map.put("mess", "3");
				return new ModelAndView("jsonView", map);
			} else {// 修改用户密码
				this.userManager.doModifyPass(userid, username, pass, oldpass);
			}
			map.put("mess", "1");
		} catch (Exception ex) {
			map.put("mess", ex.getMessage());
		}
		return new ModelAndView("jsonView", map);
	}
}
