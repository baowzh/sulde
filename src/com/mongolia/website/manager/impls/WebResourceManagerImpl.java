package com.mongolia.website.manager.impls;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mongolia.website.dao.interfaces.ChannelManagerDao;
import com.mongolia.website.dao.interfaces.WebResourceDao;
import com.mongolia.website.manager.ManagerException;
import com.mongolia.website.manager.interfaces.UserManager;
import com.mongolia.website.manager.interfaces.WebResourceManager;
import com.mongolia.website.manager.interfaces.WebSiteVisitorManager;
import com.mongolia.website.model.Channel;
import com.mongolia.website.model.DocumentValue;
import com.mongolia.website.model.FriendValue;
import com.mongolia.website.model.ImgGrpupValue;
import com.mongolia.website.model.ImgValue;
import com.mongolia.website.model.MarkedResourceValue;
import com.mongolia.website.model.MessageValue;
import com.mongolia.website.model.PagingIndex;
import com.mongolia.website.model.PaingModel;
import com.mongolia.website.model.PaingVoteResult;
import com.mongolia.website.model.QuestionValue;
import com.mongolia.website.model.ShareResourceValue;
import com.mongolia.website.model.UserValue;
import com.mongolia.website.model.VisitorValue;
import com.mongolia.website.model.VoteDetailForm;
import com.mongolia.website.model.VoteDetailValue;
import com.mongolia.website.model.VoteResultDetailValue;
import com.mongolia.website.model.VoteResultValue;
import com.mongolia.website.model.VoteValue;
import com.mongolia.website.util.StaticConstants;
import com.mongolia.website.util.UUIDMaker;

@Service("webResourceManager")
public class WebResourceManagerImpl implements WebResourceManager {
	@Autowired
	private WebResourceDao webResourceDao;
	@Autowired
	private UserManager userManager;
	@Autowired
	private WebSiteVisitorManager webSiteVisitorManager;
	@Autowired
	private ChannelManagerDao channelDao;

	@Override
	public void doAddIImgGroup(ImgGrpupValue imgGrpupValue)
			throws ManagerException {
		// TODO Auto-generated method stub
		try {
			webResourceDao.addIImgGroup(imgGrpupValue);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ManagerException(ex.getMessage());
		}

	}

	@Override
	public void doAddImg(ImgValue imgValue) throws ManagerException {
		// TODO Auto-generated method stub
		try {
			DocumentValue documentValue = new DocumentValue();
			documentValue.setDocid(imgValue.getImgid());
			documentValue.setCrtime(new Date());
			documentValue.setUserid(imgValue.getUserid());
			documentValue.setDocchannel(StaticConstants.IMGCHANNELID);
			documentValue.setDocstatus(StaticConstants.DOCSTATUS1);
			documentValue.setDoctype(StaticConstants.RESOURCE_TYPE_IMG);
			documentValue.setDocsource(new Double(1));
			documentValue.setDoctitle(imgValue.getImgdesc());
			this.webResourceDao.adddoc(documentValue);
			//
			byte[] imgcontent = imgValue.getImgcontent();
			byte[] newcontent = this.gzipdoccontent(imgcontent);
			imgValue.setImgcontent(newcontent);
			webResourceDao.addImg(imgValue);
			// 如果是封面修改相册封面
			if (imgValue.getCover() == 1) {
				Map<String, Object> queryparams = new HashMap<String, Object>();
				queryparams.put("imggroupid", imgValue.getImggroupid());
				List<ImgGrpupValue> groups = this.webResourceDao
						.getImgGroupList(queryparams);
				if (groups != null && !groups.isEmpty()) {
					ImgGrpupValue imgGrpupValue = groups.get(0);
					imgGrpupValue.setFaceimg(imgcontent);
					this.webResourceDao.updIImgGroup(imgGrpupValue);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ManagerException(ex.getMessage());
		}
	}

	@Override
	public List<ImgGrpupValue> getImgGroupList(Map<String, Object> params)
			throws ManagerException {
		// TODO Auto-generated method stub
		List<ImgGrpupValue> returnList = new ArrayList<ImgGrpupValue>();
		try {
			returnList = webResourceDao.getImgGroupList(params);
			return returnList;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ManagerException(ex.getMessage());
		}
	}

	@Override
	public List<ImgValue> getImgList(Map<String, Object> params)
			throws ManagerException {
		// TODO Auto-generated method stub
		List<ImgValue> returnList = new ArrayList<ImgValue>();
		try {
			returnList = webResourceDao.getImgList(params);
			for (ImgValue imgValue : returnList) {
				byte[] imgcontent = imgValue.getImgcontent();
				byte newcontent[] = this.ungzipdoccontent(imgcontent);
				imgValue.setImgcontent(newcontent);
			}
			return returnList;
		} catch (Exception ex) {
			throw new ManagerException(ex.getMessage());
		}
	}

	@Override
	public void doAdddoc(DocumentValue docValue) throws ManagerException {
		// TODO Auto-generated method stub
		try {
			//
			byte[] content = docValue.getDoccontent();
			if (content != null) {
				byte[] newcontent = gzipdoccontent(content);
				docValue.setDoccontent(newcontent);
			}
			webResourceDao.adddoc(docValue);
		} catch (Exception ex) {
			throw new ManagerException(ex.getMessage());
		}
	}

	@Override
	public void doDeleteDoc(String userId, String docid, String docType)
			throws ManagerException {
		// TODO Auto-generated method stub
		try {
			// 校验是否当前用户的文章，如果是则删除
			Map<String, Object> getDocParams = new HashMap<String, Object>();
			getDocParams.put("docid", docid);
			getDocParams.put("userid", userId);
			List<DocumentValue> docs = this.webResourceDao
					.getDocList(getDocParams);
			if (docs == null || docs.isEmpty()) {
				throw new ManagerException("01");// 不属于当前用户
			}
			webResourceDao.deleteDoc(userId, docid);
			// 删除分享
			this.webResourceDao.delMarkedResource(docid,
					StaticConstants.RESOURCE_TYPE_DOC);
			// 删除收藏，
			this.webResourceDao.delSharedResource(docid,
					StaticConstants.RESOURCE_TYPE_DOC);
			// 删除评论
			this.webResourceDao.delResourceComment(docid,
					StaticConstants.RESOURCE_TYPE_DOC, null);
			// 如果是图片资源删除如片
			if (docType
					.equalsIgnoreCase("" + StaticConstants.RESOURCE_TYPE_IMG)) {
				this.webResourceDao.deleteImg(docid);
			}

		} catch (Exception ex) {
			throw new ManagerException("02");
		}
	}

	@Override
	public List getDocList(Map<String, Object> params) throws ManagerException {
		// TODO Auto-generated method stub
		try {
			return webResourceDao.getDocList(params);
		} catch (Exception ex) {
			throw new ManagerException(ex.getMessage());
		}
	}

	@Override
	public void doDeleteImg(String imgId) throws ManagerException {
		// TODO Auto-generated method stub
		try {
			webResourceDao.deleteImg(imgId);
		} catch (Exception ex) {
			throw new ManagerException(ex.getMessage());
		}
	}

	@Override
	public void doDeleteImgGroup(String groupid, String userid)
			throws ManagerException {
		// TODO Auto-generated method stub
		try {
			webResourceDao.deleteImgGroup(groupid, userid);
			this.webResourceDao.deleteImgByGroupId(groupid, userid);
			// 删除相册里面的照片
		} catch (Exception ex) {
			throw new ManagerException(ex.getMessage());
		}
	}

	@Override
	public List getToolBar(String pageurl) throws ManagerException {
		// TODO Auto-generated method stub
		try {
			return webResourceDao.getToolBar(pageurl);
		} catch (Exception ex) {
			throw new ManagerException(ex.getMessage());
		}
	}

	@Override
	public void doUpdIImgGroup(ImgGrpupValue imgGrpupValue)
			throws ManagerException {
		// TODO Auto-generated method stub
		try {
			byte[] imgcontent = imgGrpupValue.getFaceimg();
			//byte[] newcontent = this.gzipdoccontent(imgcontent);
			imgGrpupValue.setFaceimg(imgcontent);
			this.webResourceDao.updIImgGroup(imgGrpupValue);
		} catch (Exception ex) {
			throw new ManagerException(ex.getMessage());
		}
	}

	@Override
	public List<FriendValue> getFriendValues(String friendid, String userid,
			String friendname, int fetchcount) throws ManagerException {
		// TODO Auto-generated method stub
		try {
			return this.webResourceDao.getFriendValues(friendid, userid,
					friendname);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ManagerException(ex.getMessage());
		}
		// return new ArrayList<FriendValue>();
	}

	@Override
	public List<VisitorValue> getVisitorValues(String visitorid, String userid,
			String visitorname, int fetchcount) throws ManagerException {
		// TODO Auto-generated method stub
		try {
			return this.webResourceDao.getVisitorValues(visitorid, userid,
					visitorname);
		} catch (Exception ex) {
			throw new ManagerException(ex.getMessage());
		}
	}

	@Override
	public Map<String, Object> getBlogInfo(UserValue blogUser,
			UserValue sessionUser, Integer self, String docchannel)
			throws ManagerException {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			// 获取用户信息
			List<UserValue> users = this.userManager.getUsers(
					blogUser.getUserid(), blogUser.getUsername());
			UserValue currentUser = users.get(0);
			map.put("user", currentUser);
			PaingModel pagingModel = new PaingModel();
			pagingModel.setUserid(blogUser.getUserid());
			pagingModel.setDoctype(StaticConstants.RESOURCE_TYPE_DOC);
			if (self.intValue() == 0) {
				pagingModel.setPagesize(24);
				pagingModel.setDocstatus(StaticConstants.DOCSTATUS2);
			} else {
				pagingModel.setPagesize(24);
			}
			pagingModel.setPageindex(1);
			pagingModel.setDocchannel(docchannel);
			PaingModel pagingModel1 = webSiteVisitorManager
					.pagingquerydoc(pagingModel);
			List<DocumentValue> docList = pagingModel1.getDocList();
			for (int i = 0; i < docList.size(); i++) {
				DocumentValue documentValue = docList.get(i);
				documentValue.setSelf(self);
			}
			map.put("docList", docList);
			if (docchannel != null && !docchannel.equalsIgnoreCase("")) {
				Map<String, Object> queryChannelParams = new HashMap<String, Object>();
				queryChannelParams.put("channelid", docchannel);
				List<Channel> channels = this.channelDao
						.getChannel(queryChannelParams);
				if (channels != null && !channels.isEmpty()) {
					map.put("currentchannel", channels.get(0));
				} else {
					map.put("currentchannel", null);
				}

			} else {
				map.put("currentchannel", null);
			}
			int pageCount = pagingModel1.getPagecount();
			map.put("docpagecount", pageCount);
			// 组织首页分页连接
			List<PagingIndex> pageIndexs = new ArrayList<PagingIndex>();
			for (int i = 0; i < pageCount; i++) {
				PagingIndex pagingIndex = new PagingIndex();
				pagingIndex.setPageindex(i + 1);
				pageIndexs.add(pagingIndex);
			}
			if (pageIndexs.size() > 1) {
				map.put("docpageIndexs", pageIndexs);
			}
			// 获取用户朋友列表 只显示8个
			List<FriendValue> fvalues = this.getFriendValues(null,
					blogUser.getUserid(), null,
					StaticConstants.FETCH_FRIEND_COUNT);
			map.put("fvalues", fvalues);
			// 获取用户相册列表 显示全部
			params.put("userid", blogUser.getUserid());
			List<ImgGrpupValue> imgGroupValue = this.getImgGroupList(params);
			map.put("imggroupValues", imgGroupValue);
			Integer totalVisitCount = this.webResourceDao.getTotalVisitorCount(
					null, blogUser.getUserid(), null);
			map.put("totalVisitCount", totalVisitCount);
			Integer currentDateVisitCount = this.webResourceDao
					.getCurrentDateVisitCount(null, blogUser.getUserid(), null);
			map.put("currentDateVisitCount", currentDateVisitCount);
			List<VisitorValue> visitors = this.webResourceDao.getVisitorValues(
					null, blogUser.getUserid(), null);
			map.put("visitors", visitors);
			// 获取当前博客用户好友最近发表的文章（最近10天的前10条）
			List<DocumentValue> friendDocList = this.webResourceDao
					.getFriendsDocList(blogUser.getUserid());
			map.put("friendDocList", friendDocList);
			Integer messageCount = this.webResourceDao.getMessageCount(blogUser
					.getUserid());
			map.put("messageCount", messageCount);
			Date queryDate1 = new Date();
			Calendar calendar = java.util.Calendar.getInstance();
			calendar.setTime(new Date());
			calendar.add(Calendar.DAY_OF_YEAR, 0 - 10);
			queryDate1 = calendar.getTime();
			map.put("blogNews", this.webResourceDao.getBlogNews(
					blogUser.getUserid(), queryDate1));
			// 获取当前博主分享的作品
			PaingModel sharePaingModel = this.pagingQuerySharedDocs(params,
					StaticConstants.DOCTYPE_DOC, 1, 24);
			map.put("sharePaingModel", sharePaingModel);
			List<PagingIndex> sharepageIndexs = new ArrayList<PagingIndex>();
			for (int i = 0; i < sharePaingModel.getPagecount(); i++) {
				PagingIndex pagingIndex = new PagingIndex();
				pagingIndex.setPageindex(i + 1);
				// 先不设置连接
				sharepageIndexs.add(pagingIndex);
			}
			if (pageIndexs.size() > 1) {
				map.put("sharepageIndexs", sharepageIndexs);
			}
			// 添加一个浏览记录
			if (self.intValue() == 0) {
				VisitorValue visitorValue = new VisitorValue();
				visitorValue.setUserid(blogUser.getUserid());
				visitorValue.setVisitdate(new Date());
				if (sessionUser != null
						&& sessionUser.getUserkind().intValue() != StaticConstants.USER_KIND2) {
					visitorValue.setVisitorid(sessionUser.getUserid());
					visitorValue.setVisitorname(sessionUser.getArtname());
					visitorValue
							.setVisitortype(StaticConstants.VISITOR_TYPE_REG);
				} else {
					visitorValue.setVisitorid(StaticConstants.NO_LOGIIN_USERID);
					visitorValue
							.setVisitorname(StaticConstants.NO_LOGIIN_USERID);
					visitorValue
							.setVisitortype(StaticConstants.VISITOR_TYPE_NOREG);
				}

				this.webResourceDao.addVisitLog(visitorValue);
			} else {
				Date queryDate = new Date();
				calendar.setTime(new Date());
				calendar.add(Calendar.DAY_OF_YEAR, 0 - 10);
				queryDate = calendar.getTime();
				map.put("blogNews", this.webResourceDao.getFriendNews(
						blogUser.getUserid(), queryDate));
			}
			// 获取调查数据
			if (self.intValue() == 0) {
				params.put("status", 2);
			} else {
			}
			List<VoteValue> votelist = this.getVoteList(params);
			map.put("votelist", votelist);

		} catch (Exception ex) {
			throw new ManagerException(ex.getMessage());
		}
		return map;
	}

	@Override
	public DocumentValue readUserDDocument(String docid, UserValue userValue)
			throws ManagerException {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("docid", docid);
		try {
			List<DocumentValue> documentvalues = this.webResourceDao
					.getDocList(params);
			if (documentvalues.size() == 0 || documentvalues.isEmpty()) {
				throw new ManagerException("");
			} else {
				DocumentValue documentValue = documentvalues.get(0);
				//
				if (documentValue.getDoccontent() != null) {
					byte newcontent[] = ungzipdoccontent(documentValue
							.getDoccontent());
					documentValue.setDoccontent(newcontent);
				}

				//
				if (documentValue.getDoctype().intValue() == StaticConstants.RESOURCE_TYPE_DOC) {
					String docContent = new String(
							documentValue.getDoccontent());
					// 替换flash 视频地址
					String matchStr = "\\[\\[http[s]?:\\/\\/([\\w-]+\\.)+[\\w-]+([\\w-./?%&=]*)?\\]\\]";
					Pattern destStri = Pattern.compile(matchStr);// ^
					Matcher mati = destStri.matcher(docContent);
					StringBuffer bufferi = new StringBuffer();
					while (mati.find()) {
						String groupi = mati.group(0);
						groupi = groupi.substring(2, groupi.length() - 2);
						String embed = "<embed pluginspage=\"http://www.macromedia.com/go/getflashplayer\"  src=\""
								+ groupi
								+ "\" allowFullScreen=\"true\" quality=\"high\" width=\"480\" height=\"400\" align=\"middle\" allowScriptAccess=\"always\" type=\"application/x-shockwave-flash\"></embed>";
						mati.appendReplacement(bufferi, embed);
					}
					mati.appendTail(bufferi);
					docContent = bufferi.toString();
					documentValue.setHtmlstr(docContent);
				}
				// 添加读者次数
				VisitorValue visitorValue = new VisitorValue();
				visitorValue.setVisitobjid(docid);
				if (userValue != null
						&& userValue.getUserkind() != StaticConstants.USER_KIND2) {
					visitorValue.setVisitorid(userValue.getUserid());
				} else {
					visitorValue.setVisitorid(StaticConstants.NO_LOGIIN_USERID);
				}

				visitorValue.setVisitdate(new Date());
				List<UserValue> docusers = this.userManager.getUsers(
						documentValue.getUserid(), null);
				UserValue docuser = docusers.get(0);
				visitorValue.setVisitorname(docuser.getArtname());
				visitorValue.setUserid(docuser.getUserid());
				this.webResourceDao.addDocReader(visitorValue);
				// 修改文章被读次数
				this.webResourceDao.doIsRead(docid);
				if (documentValue.getReadcount() != null) {
					documentValue
							.setReadcount(documentValue.getReadcount() + 1);
				} else {
					documentValue.setReadcount(1);
				}

				return documentValue;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ManagerException(ex.getMessage());
		}
	}

	@Override
	public void doAddCommentOnResource(String resourceid, Integer resourceType,
			String userid, String comment, String sendderid, String sendername,
			Integer messType, Integer hidden) throws ManagerException {
		// TODO Auto-generated method stub
		try {
			// 写接收新
			MessageValue messageValue = new MessageValue();
			messageValue.setMessageid(UUIDMaker.getUUID());
			messageValue.setMessagesenderid(sendderid);
			messageValue.setUserid(userid);
			messageValue.setResourceid(resourceid);
			messageValue.setResourcekind(resourceType);
			messageValue.setHidden(hidden);
			messageValue.setMesstype(messType);
			messageValue.setMessagecont(comment.getBytes("utf-8"));
			messageValue.setMessagesendername(sendername);
			messageValue.setSendtime(new Date());
			if (messType.intValue() == StaticConstants.MESS_TYPE_SEND) {// 给别人写的信则写2分数据
				// 自己发送的信加上别人接收信
				MessageValue sendmessageValue = new MessageValue();
				sendmessageValue.setMessageid(UUIDMaker.getUUID());
				sendmessageValue.setMessagesenderid(sendderid);
				sendmessageValue.setUserid(sendderid);
				sendmessageValue.setResourceid(resourceid);
				sendmessageValue.setResourcekind(resourceType);
				sendmessageValue.setMesstype(messType);
				sendmessageValue.setMessagecont(comment.getBytes("utf-8"));
				sendmessageValue.setMessagesendername(sendername);
				sendmessageValue.setSendtime(new Date());
				this.webResourceDao.addCommentOnResource(sendmessageValue);
			}
			this.webResourceDao.addCommentOnResource(messageValue);
			this.webResourceDao.setDocCommentCount(resourceid, "add", null);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ManagerException(ex.getMessage());
		}

	}

	@Override
	public List<MessageValue> getResourceCommentList(String resourceid,
			Integer resourceType, String userid, String messid, String senderid)
			throws ManagerException {
		// TODO Auto-generated method stub
		try {
			List<MessageValue> comments = new ArrayList<MessageValue>();
			if (resourceType == StaticConstants.RESOURCE_TYPE_DOC) {
				comments = this.webResourceDao.getCommentList(resourceid,
						StaticConstants.RESOURCE_TYPE_DOC, userid, messid,
						senderid);
			} else if (resourceType == StaticConstants.RESOURCE_TYPE_IMG) {
				comments = this.webResourceDao.getCommentList(resourceid,
						StaticConstants.RESOURCE_TYPE_IMG, userid, messid,
						senderid);
			} else if (resourceType == StaticConstants.RESOURCE_TYPE_VIDEO) {
				comments = this.webResourceDao.getCommentList(resourceid,
						StaticConstants.RESOURCE_TYPE_VIDEO, userid, messid,
						senderid);
			} else {
				comments = this.webResourceDao.getCommentList(resourceid,
						StaticConstants.RESOURCE_TYPE_DOC, userid, messid,
						senderid);
			}
			for (int i = 0; i < comments.size(); i++) {
				MessageValue messageValue = comments.get(i);
				messageValue.setContenthtml(new String(messageValue
						.getMessagecont(), "utf-8"));
				java.text.SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");
				if (messageValue.getSendtime() != null) {

					messageValue.setSendtimestr(simpleDateFormat
							.format(messageValue.getSendtime()));
				}

			}
			return comments;
		} catch (Exception ex) {
			ex.printStackTrace();
			return new ArrayList<MessageValue>();
		}
	}

	@Override
	public void doShareDdoc(String docid, String currentuserid)
			throws ManagerException {
		// TODO Auto-generated method stub
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("docid", docid);
			List<DocumentValue> documentvalues = this.webResourceDao
					.getDocList(params);
			if (documentvalues != null && !documentvalues.isEmpty()) {
				DocumentValue documentValue = documentvalues.get(0);
				String originalauthorid = documentValue.getUserid();
				if (originalauthorid.equalsIgnoreCase(currentuserid)) {
					return;// 自己的作品就不用分享了
				}
				// 校验是否已经分享过
				if (this.webResourceDao.resourceisshared(currentuserid, docid)) {
					throw new Exception("2");
				}
				ShareResourceValue shareResourceValue = new ShareResourceValue();
				shareResourceValue.setOriginalauthorid(originalauthorid);
				shareResourceValue.setResourceid(docid);
				shareResourceValue.setUserid(currentuserid);
				shareResourceValue
						.setResourcekind(StaticConstants.RESOURCE_TYPE_DOC);
				shareResourceValue.setSharedate(new Date());
				this.webResourceDao.addSharedResource(shareResourceValue);
				// 修改文章分享数
				this.webResourceDao.docisshared(docid);

			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	@Override
	public void doShareImg(String imgid, String currentuserid)
			throws ManagerException {
		// TODO Auto-generated method stub
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("imgid", imgid);
			List<ImgValue> imgvalues = this.webResourceDao.getImgList(params);
			if (imgvalues != null && !imgvalues.isEmpty()) {
				ImgValue imgValue = imgvalues.get(0);
				String originalauthorid = imgValue.getUserid();
				if (originalauthorid.equalsIgnoreCase(currentuserid)) {
					return;// 自己的作品就不用分享了
				}
				// 校验是否已经分享过
				if (this.webResourceDao.resourceisshared(currentuserid, imgid)) {
					throw new Exception("2");
				}
				ShareResourceValue shareResourceValue = new ShareResourceValue();
				shareResourceValue.setOriginalauthorid(originalauthorid);
				shareResourceValue.setResourceid(imgid);
				shareResourceValue.setUserid(currentuserid);
				shareResourceValue
						.setResourcekind(StaticConstants.RESOURCE_TYPE_IMG);
				shareResourceValue.setSharedate(new Date());
				this.webResourceDao.addSharedResource(shareResourceValue);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	@Override
	public void doShareVideo(String vedioid, String currentuserid)
			throws ManagerException {
		// TODO Auto-generated method stub
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("imgid", vedioid);
			List<ImgValue> imgvalues = this.webResourceDao.getImgList(params);
			if (imgvalues != null && !imgvalues.isEmpty()) {
				ImgValue imgValue = imgvalues.get(0);
				String originalauthorid = imgValue.getImgid();
				if (originalauthorid.equalsIgnoreCase(currentuserid)) {
					return;// 自己的作品就不用分享了
				}
				// 校验是否已经分享过
				if (this.webResourceDao
						.resourceisshared(currentuserid, vedioid)) {
					throw new Exception("2");
				}
				ShareResourceValue shareResourceValue = new ShareResourceValue();
				shareResourceValue.setOriginalauthorid(originalauthorid);
				shareResourceValue.setUserid(currentuserid);
				shareResourceValue.setResourceid(vedioid);
				shareResourceValue
						.setResourcekind(StaticConstants.RESOURCE_TYPE_IMG);
				shareResourceValue.setSharedate(new Date());
				this.webResourceDao.addSharedResource(shareResourceValue);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	@Override
	public void doMarkDoc(String docid, String currentuserid)
			throws ManagerException {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("docid", docid);
			List<DocumentValue> documentvalues = this.webResourceDao
					.getDocList(params);
			if (documentvalues != null && !documentvalues.isEmpty()) {
				DocumentValue documentValue = documentvalues.get(0);
				String originalauthorid = documentValue.getUserid();
				if (originalauthorid.equalsIgnoreCase(currentuserid)) {
					return;// 自己的作品就不用分享了
				}
				// 校验是否已经分享过
				if (this.webResourceDao.resourceismarked(currentuserid, docid)) {
					throw new Exception("2");
				}
				MarkedResourceValue shareResourceValue = new MarkedResourceValue();
				shareResourceValue.setOriginalauthorid(originalauthorid);
				shareResourceValue.setUserid(currentuserid);
				shareResourceValue.setResourceid(docid);
				shareResourceValue
						.setResourcekind(StaticConstants.RESOURCE_TYPE_DOC);
				shareResourceValue.setMarkeddate(new Date());
				this.webResourceDao.addMarkedResource(shareResourceValue);
				// 修改文章分享数
				this.webResourceDao.docIsMarked(docid);

			}
		} catch (Exception ex) {
			ex.printStackTrace();

		}

	}

	@Override
	public Integer getResourceCommentCount(String resourceid,
			Integer resourceType) throws ManagerException {
		// TODO Auto-generated method stub
		try {
			Integer commentCount = new Integer(0);
			if (resourceType == StaticConstants.RESOURCE_TYPE_DOC) {
				commentCount = this.webResourceDao.getCommentCount(resourceid,
						StaticConstants.RESOURCE_TYPE_DOC);
			} else if (resourceType == StaticConstants.RESOURCE_TYPE_IMG) {
				commentCount = this.webResourceDao.getCommentCount(resourceid,
						StaticConstants.RESOURCE_TYPE_IMG);
			} else if (resourceType == StaticConstants.RESOURCE_TYPE_VIDEO) {
				commentCount = this.webResourceDao.getCommentCount(resourceid,
						StaticConstants.RESOURCE_TYPE_VIDEO);
			} else {
				commentCount = this.webResourceDao.getCommentCount(resourceid,
						StaticConstants.RESOURCE_TYPE_DOC);
			}

			return commentCount;
		} catch (Exception ex) {
			ex.printStackTrace();
			return new Integer(0);
		}
	}

	@Override
	public void doUpdDoc(DocumentValue docValue) throws ManagerException {
		// TODO Auto-generated method stub
		try {
			byte[] content = docValue.getDoccontent();
			if (content != null) {
				byte[] newcontent = gzipdoccontent(content);
				docValue.setDoccontent(newcontent);
			}
			this.webResourceDao.upddoc(docValue);
		} catch (Exception ex) {
			throw new ManagerException(ex.getMessage());
		}
	}

	@Override
	public void doDelResourceComment(String resourceid, Integer resourceType,
			String userid, String messageid) throws ManagerException {
		// TODO Auto-generated method stub
		try {
			List<MessageValue> messageList = this.getResourceCommentList(
					resourceid, resourceType, userid, messageid, null);
			if (messageList == null || messageList.isEmpty()) {
				throw new ManagerException("01");
			}
			//
			for (MessageValue messavalue : messageList) {
				this.webResourceDao.delResourceComment(resourceid,
						resourceType, messavalue.getMessageid());
			}
		} catch (Exception ex) {
			throw new ManagerException("01");
		}
	}

	@Override
	public List<MessageValue> getReceMessList(String userid, String messid,
			Integer pageIndex) throws ManagerException {
		// TODO Auto-generated method stub
		List<MessageValue> mess = this.webResourceDao.getMessList(userid, 1,
				messid, userid, pageIndex);
		for (MessageValue messageValue : mess) {
			if (messageValue.getMessagecont() != null) {
				try {
					messageValue.setContenthtml(new String(messageValue
							.getMessagecont(), "utf-8"));
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}

		}
		return mess;
	}

	@Override
	public List<MessageValue> getSendMessList(String userid, String messid,
			Integer pageIndex) throws ManagerException {
		// TODO Auto-generated method stub
		List<MessageValue> mess = this.webResourceDao.getMessList(userid, 2,
				messid, userid, pageIndex);
		for (MessageValue messageValue : mess) {
			if (messageValue.getMessagecont() != null) {
				try {
					messageValue.setContenthtml(new String(messageValue
							.getMessagecont(), "utf-8"));
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}
		return mess;
	}

	@Override
	public MessageValue getMessageValueContent(String messid)
			throws ManagerException {
		// TODO Auto-generated method stub
		MessageValue messageValue = null;
		try {
			messageValue = this.webResourceDao.getMessageValueContent(messid);
			messageValue.setContenthtml(new String(messageValue
					.getMessagecont(), "utf-8"));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return messageValue;
	}

	@Override
	public void doSendMess(String resourceid, Integer resourceType,
			String userid, String comment, String sendderid,
			String messageSenderName, Integer messType) throws ManagerException {
		// TODO Auto-generated method stub
		try {
			// 写接收新
			MessageValue messageValue = new MessageValue();
			messageValue.setMessageid(UUIDMaker.getUUID());
			messageValue.setMessagesenderid(sendderid);
			messageValue.setUserid(userid);
			messageValue.setResourceid(resourceid);
			messageValue.setResourcekind(resourceType);
			messageValue.setMesstype(messType);
			messageValue.setMessagecont(comment.getBytes("utf-8"));
			messageValue.setMessagesendername(messageSenderName);
			messageValue.setSendtime(new Date());
			if (messType.intValue() == StaticConstants.MESS_TYPE_SEND) {// 给别人写的信则写2分数据
				// 自己发送的信加上别人接收信
				MessageValue sendmessageValue = new MessageValue();
				sendmessageValue.setMessageid(UUIDMaker.getUUID());
				sendmessageValue.setMessagesenderid(sendderid);
				sendmessageValue.setUserid(sendderid);
				sendmessageValue.setResourceid(resourceid);
				sendmessageValue.setResourcekind(resourceType);
				sendmessageValue.setMesstype(StaticConstants.MESS_TYPE_SEND);
				sendmessageValue.setMessagecont(comment.getBytes("utf-8"));
				sendmessageValue.setMessagesendername(messageSenderName);
				sendmessageValue.setSendtime(new Date());
				this.webResourceDao.addCommentOnResource(sendmessageValue);
			} else if (messType.intValue() == StaticConstants.MESS_TYPE_QUSTION) {// 加为
				// 校验是否已经添加为朋友
				List<FriendValue> friends = webResourceDao.getFriendValues(
						userid, sendderid, null);
				if (friends != null && !friends.isEmpty()) {
					throw new ManagerException("02");
				}
			}
			this.webResourceDao.addCommentOnResource(messageValue);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ManagerException(ex.getMessage());
		}
	}

	@Override
	public Map<String, Object> pagingQueryFriends(String userid,
			Integer pageIndex, Integer pageSize) throws ManagerException {
		// TODO Auto-generated method stub
		Map<String, Object> returnMap = new HashMap<String, Object>();
		try {
			Integer startIndex = 0;
			startIndex = (pageIndex - 1) * 40;
			List<FriendValue> friends = this.webResourceDao.pagingQueryFriends(
					userid, startIndex, pageSize);
			Integer count = this.webResourceDao.getFriendCount(userid);
			int pageCount = count / 40;
			if (count % 40 > 0) {
				pageCount = pageCount + 1;
			}
			returnMap.put("friends", friends);
			returnMap.put("friendCount", pageCount);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return returnMap;
	}

	@Override
	public List<DocumentValue> getNextDoc(String docid, String groupid)
			throws Exception {
		// TODO Auto-generated method stub
		return this.webResourceDao.getNextDoc(docid, groupid);
	}

	@Override
	public List<DocumentValue> getPreviousDoc(String docid, String groupid)
			throws Exception {
		// TODO Auto-generated method stub
		return this.webResourceDao.getPreviousDoc(docid, groupid);
	}

	@Override
	public List<VoteDetailValue> doAddVoteQuestion(VoteDetailForm voteDetailfrom)
			throws Exception {
		// TODO Auto-generated method stub
		String chatindex[] = { "A", "B", "C", "D", "E", "F", "G", "H", "I",
				"J", "K", "L", "M", "N" };
		VoteDetailValue voteDetailValue = new VoteDetailValue();
		voteDetailValue.setVoteid(voteDetailfrom.getVoteid());
		String uuid = UUIDMaker.getUUID();
		voteDetailValue.setQuestiontype(voteDetailfrom.getQuestiontype());
		voteDetailValue.setQuestionid(uuid);
		voteDetailValue.setQuestiondesc(voteDetailfrom.getQuestiondesc());
		// 1.产生问题
		List<QuestionValue> questionValues = new ArrayList<QuestionValue>();
		if (voteDetailValue.getQuestiontype().intValue() == StaticConstants.QUESTION_SINGGLE
				|| voteDetailValue.getQuestiontype().intValue() == StaticConstants.QUESTION_MULTSEL) {

			String selecs[] = voteDetailfrom.getSelections().split("##");
			int index = 0;
			for (String selec : selecs) {
				QuestionValue questionValue = new QuestionValue();
				questionValue.setQuestionid(voteDetailValue.getQuestionid());
				questionValue.setAnswername(selec);
				questionValue.setAnswerid(UUIDMaker.getRandomBasedUUID());
				questionValue.setCharindex(chatindex[index]);
				questionValues.add(questionValue);
				voteDetailValue.setQuestionValues(questionValues);
				index++;
			}

		} else { // 文本问题
			QuestionValue questionValue = new QuestionValue();
			questionValue.setQuestionid(voteDetailValue.getQuestionid());
			questionValue.setAnswerid(UUIDMaker.getUUID());
			questionValues.add(questionValue);
			voteDetailValue.setQuestionValues(questionValues);
		}
		// 3.保存数据
		this.webResourceDao.addVoteQuestion(voteDetailValue);
		return this.webResourceDao.getVoteQuestions(
				voteDetailValue.getVoteid(), voteDetailValue.getQuestionid());
	}

	@Override
	public void doSaveVote(String voteid, String topic, String userid)
			throws Exception {
		// TODO Auto-generated method stub
		// 如果已经存在则删除
		List<VoteValue> votes = this.webResourceDao.getVoteValue(voteid);
		VoteValue voteValue = new VoteValue();
		voteValue.setVoteid(voteid);
		voteValue.setTopic(topic);
		voteValue.setCreatetime(new Date());
		voteValue.setEnddate(new Date());
		voteValue.setStatus(0);
		voteValue.setUserid(userid);
		if (votes == null || votes.isEmpty()) {
			this.webResourceDao.addVoteValue(voteValue);
		} else {
			this.webResourceDao.updVoteValue(voteValue);
		}

	}

	@Override
	public List<VoteDetailValue> getVoteQuestion(String voteid,
			String questionid) throws Exception {
		// TODO Auto-generated method stub
		return this.webResourceDao.getVoteQuestions(voteid, questionid);
	}

	@Override
	public List<VoteValue> getVoteList(Map<String, Object> params)
			throws Exception {
		// TODO Auto-generated method stub
		List<VoteValue> votes = this.webResourceDao.getVoteList(params);
		for (VoteValue voteValue : votes) {
			java.text.SimpleDateFormat simpleDateFormat = new java.text.SimpleDateFormat(
					"yyyy-mm-dd");
			String createdatestr = simpleDateFormat.format(voteValue
					.getCreatetime());
			voteValue.setCreatetimestr(createdatestr);
			// 获取每个问题
			List<VoteDetailValue> details = this.webResourceDao
					.getVoteQuestions(voteValue.getVoteid(), null);
			voteValue.setDetails(details);
			Integer joinercount = this.webResourceDao
					.getVoteJoinerCount(voteValue.getVoteid());
			voteValue.setJoinercount(joinercount);
		}
		return votes;
	}

	@Override
	public void doSaveVoteResult(String voteid, String singquestionrel,
			String multiquestionrel, String textquestionrel, String userid)
			throws Exception {
		// TODO Auto-generated method stub
		String questionandanswers[] = null;
		List<VoteResultValue> resuts = new ArrayList<VoteResultValue>();
		if (singquestionrel != null && !singquestionrel.equalsIgnoreCase("")) {
			questionandanswers = singquestionrel.split("##");
			for (int i = 0; i < questionandanswers.length; i++) { // 处理单选
				VoteResultValue voteResultValue = new VoteResultValue();
				String questionandanswe = questionandanswers[i];
				String answers[] = questionandanswe.split(",");
				String questionid = answers[0];
				String answerid = answers[1];
				List<VoteDetailValue> details = this.getVoteQuestion(voteid,
						questionid);
				if (details == null || details.isEmpty()) {
					continue;
				}
				VoteDetailValue voteDetailValue = details.get(0);
				voteResultValue.setQuestiondesc(voteDetailValue
						.getQuestiondesc());
				voteResultValue.setVoteid(voteid);
				voteResultValue.setQuestionid(questionid);
				voteResultValue.setVotedate(new Date());
				voteResultValue
						.setQuestiontype(StaticConstants.QUESTION_SINGGLE);
				voteResultValue.setJoinerid(userid);
				List<VoteResultDetailValue> resultdetail = new ArrayList<VoteResultDetailValue>();
				for (QuestionValue questionValue : voteDetailValue
						.getQuestionValues()) {
					if (questionValue.getAnswerid().equalsIgnoreCase(answerid)) {
						VoteResultDetailValue voteResultDetailValue = new VoteResultDetailValue();
						voteResultDetailValue.setAnswerdesc(questionValue
								.getAnswername());
						voteResultDetailValue.setCharindex(questionValue
								.getCharindex());
						voteResultDetailValue.setAnswerid(answerid);
						voteResultDetailValue.setJoinerid(userid);
						voteResultDetailValue.setQuestionid(questionid);
						resultdetail.add(voteResultDetailValue);
					}
				}
				voteResultValue.setAnswerid(answerid);
				voteResultValue.setDetail(resultdetail);
				resuts.add(voteResultValue);
			}
		}
		if (multiquestionrel != null && !multiquestionrel.equalsIgnoreCase("")) {

			questionandanswers = multiquestionrel.split("##");
			for (int i = 0; i < questionandanswers.length; i++) { // 处理多选
				VoteResultValue voteResultValue = new VoteResultValue();
				String questionandanswe = questionandanswers[i];
				String questionidandanswers[] = questionandanswe.split("@");
				String questionid = questionidandanswers[0];
				String answers = questionidandanswers[1];
				String answerids[] = answers.split(",");
				List<VoteDetailValue> details = this.getVoteQuestion(voteid,
						questionid);
				if (details == null || details.isEmpty()) {
					continue;
				}
				VoteDetailValue voteDetailValue = details.get(0);
				voteResultValue.setQuestiondesc(voteDetailValue
						.getQuestiondesc());
				voteResultValue.setVoteid(voteid);
				voteResultValue.setQuestionid(questionid);
				voteResultValue.setVotedate(new Date());
				voteResultValue
						.setQuestiontype(StaticConstants.QUESTION_MULTSEL);
				voteResultValue.setJoinerid(userid);
				List<VoteResultDetailValue> resultdetail = new ArrayList<VoteResultDetailValue>();
				List<String> sortanswerids = new ArrayList<String>();
				for (QuestionValue questionValue : voteDetailValue
						.getQuestionValues()) {
					for (int j = 0; j < answerids.length; j++) {
						String answerid = answerids[j];
						if (questionValue.getAnswerid().equalsIgnoreCase(
								answerid)) {
							VoteResultDetailValue voteResultDetailValue = new VoteResultDetailValue();
							voteResultDetailValue.setAnswerdesc(questionValue
									.getAnswername());
							voteResultDetailValue.setCharindex(questionValue
									.getCharindex());
							voteResultDetailValue.setAnswerid(answerid);
							voteResultDetailValue.setJoinerid(userid);
							voteResultDetailValue.setQuestionid(questionid);
							resultdetail.add(voteResultDetailValue);
							sortanswerids.add(answerid);
						}
					}
				}
				String sortarry[] = new String[sortanswerids.size()];
				sortanswerids.toArray(sortarry);
				Arrays.sort(sortarry, new Comparator<String>() {
					@Override
					public int compare(String o1, String o2) {
						// TODO Auto-generated method stub
						return o1.compareTo(o2);
					}
				});
				String anserids = "";
				for (int k = 0; k < sortarry.length; k++) {
					anserids = anserids + sortarry[k] + "#";
				}
				voteResultValue.setDetail(resultdetail);
				voteResultValue.setAnswerid(anserids);
				resuts.add(voteResultValue);
			}
		}
		if (textquestionrel != null
				&& !textquestionrel.trim().equalsIgnoreCase("")) {
			questionandanswers = textquestionrel.split("##");
			for (int j = 0; j < questionandanswers.length; j++) {
				String questionandanswer = questionandanswers[j];
				String questionid = questionandanswer.split(",")[0];
				String answer = questionandanswer.split(",")[1];
				List<VoteDetailValue> details = this.getVoteQuestion(voteid,
						questionid);
				if (details == null || details.isEmpty()) {
					continue;
				}
				VoteResultValue voteResultValue = new VoteResultValue();
				// VoteDetailValue voteDetailValue = details.get(0);
				voteResultValue.setQuestiondesc(answer);
				voteResultValue.setVoteid(voteid);
				voteResultValue.setQuestionid(questionid);
				voteResultValue.setVotedate(new Date());
				voteResultValue.setQuestiontype(StaticConstants.QUESTION_TEXT);
				voteResultValue.setJoinerid(userid);
				// 添加明细
				VoteResultDetailValue voteResultDetailValue = new VoteResultDetailValue();
				voteResultDetailValue.setAnswerdesc(answer);
				voteResultDetailValue.setAnswerid("1111");
				voteResultDetailValue.setJoinerid(userid);
				voteResultDetailValue.setQuestionid(questionid);
				List<VoteResultDetailValue> resultdetail = new ArrayList<VoteResultDetailValue>();
				resultdetail.add(voteResultDetailValue);
				voteResultValue.setDetail(resultdetail);
				resuts.add(voteResultValue);
			}
		}
		for (VoteResultValue voteResultValue : resuts) {
			this.webResourceDao.saveVoteResult(voteResultValue);
		}
	}

	@Override
	public List<VoteResultDetailValue> calVoteResult(String voteid,
			String questionid) throws Exception {
		// TODO Auto-generated method stub
		List<VoteDetailValue> details = this
				.getVoteQuestion(voteid, questionid);
		Integer questiontype = details.get(0).getQuestiontype();
		List<VoteResultDetailValue> result = new ArrayList<VoteResultDetailValue>();
		if (questiontype.intValue() == StaticConstants.QUESTION_SINGGLE
				.intValue()) {
			result = this.webResourceDao.calVoteResult(questionid);
		}
		if (questiontype.intValue() == StaticConstants.QUESTION_MULTSEL
				.intValue()) {// 多选题则根据组编号去分组查询
			result = new ArrayList<VoteResultDetailValue>();
			List<VoteResultValue> voteresult = this.webResourceDao
					.getMultiQuestionResult(questionid);
			// 获取每个选项的小标和描述
			for (VoteResultValue voteResultValue : voteresult) {
				VoteResultDetailValue voteResultDetailValue = new VoteResultDetailValue();
				voteResultDetailValue.setVotenum(voteResultValue.getVotenum());
				String answerids[] = voteResultValue.getAnswerid().split("#");
				List<VoteDetailValue> votedetails = this.webResourceDao
						.getVoteQuestions(voteid, questionid);
				List<QuestionValue> questionValues = votedetails.get(0)
						.getQuestionValues();
				String charindex = "";
				String answerdesc = "";
				for (int i = 0; i < questionValues.size(); i++) {
					for (int k = 0; k < answerids.length; k++) {
						if (answerids[k].equalsIgnoreCase(questionValues.get(i)
								.getAnswerid())) {
							charindex = charindex
									+ questionValues.get(i).getCharindex();
							answerdesc = answerdesc
									+ questionValues.get(i).getAnswername()
									+ " ";
						}
					}
				}
				voteResultDetailValue.setAnswerdesc(answerdesc.trim());
				voteResultDetailValue.setCharindex(charindex);
				result.add(voteResultDetailValue);

			}
		}
		return result;
	}

	@Override
	public void doDelVote(String voteid, String userid) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("voteid", voteid);
		List<VoteValue> votes = this.getVoteList(params);
		for (VoteValue voteValue : votes) {
			List<VoteDetailValue> detailValues = voteValue.getDetails();
			for (VoteDetailValue voteDetailValue : detailValues) {
				this.webResourceDao.deleteVOteDetail(
						voteDetailValue.getVoteid(),
						voteDetailValue.getQuestionid());
				this.webResourceDao.deleteVoteQuestion(
						voteDetailValue.getQuestionid(), null);
			}
			this.webResourceDao.deleteVoteValue(voteid);
		}
	}

	@Override
	public Map<String, Object> checkVoteStatus(String voteid) throws Exception {
		// TODO Auto-generated method stub
		// 校验是否已经发布
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("voteid", voteid);
		List<VoteValue> votes = this.getVoteList(params);
		// 及校验是否已经参与
		Integer joinercount = this.webResourceDao.getVoteJoinerCount(voteid);
		Map<String, Object> returnMap = new HashMap<String, Object>();
		returnMap.put("status", votes.get(0).getStatus());
		returnMap.put("joinercount", joinercount);
		return returnMap;
	}

	@Override
	public void doDelVoteQuestion(String voteid, String questionid)
			throws Exception {
		// TODO Auto-generated method stub
		this.webResourceDao.deleteVOteDetail(voteid, questionid);
	}

	@Override
	public void doDelVoteQuestionSel(String questionid, String answerid)
			throws Exception {
		// TODO Auto-generated method stub
		this.webResourceDao.deleteVoteQuestion(questionid, answerid);
	}

	@Override
	public PaingVoteResult pagingqueryVoteResult(String voteid,
			String questionid, Integer pageindex) throws Exception {
		// TODO Auto-generated method stub
		// 获取总行数
		Integer votecount = this.webResourceDao.voteResultvcount(voteid,
				questionid);
		// 获取指定页数据
		Integer startindex = (pageindex - 1) * 5;
		Integer fechcount = 5;
		List<VoteResultValue> result = this.webResourceDao.getVoteResult(
				voteid, questionid, startindex, fechcount);
		PaingVoteResult paingVoteResult = new PaingVoteResult();
		//
		int pageCount = votecount / 5;
		if (pageCount % 5 > 0) {
			pageCount = pageCount + 1;
		}
		//
		paingVoteResult.setPageCount(pageCount);
		paingVoteResult.setPageindex(pageindex);
		paingVoteResult.setResultcount(votecount);
		paingVoteResult.setResults(result);
		return paingVoteResult;
	}

	@Override
	public PaingModel pagingQuerySharedDocs(Map<String, Object> params,
			int doctype, int pageindex, int pagesize) throws Exception {
		// TODO Auto-generated method stub
		PaingModel paingModel = new PaingModel();
		int rowcount = this.webResourceDao.getSharedDocCount(params, doctype);
		int start = (pageindex - 1) * pagesize;
		params.put("start", start);
		params.put("rowcount", pagesize);
		params.put("doctype", doctype);
		List<DocumentValue> docList = this.webResourceDao.getSharedDocs(params);
		paingModel.setDocList(docList);
		paingModel.setRowcount("" + rowcount);
		int pageCount = rowcount / paingModel.getPagesize();
		if (rowcount % paingModel.getPagesize() > 0) {
			pageCount = pageCount + 1;
		}
		paingModel.setPagecount(pageCount);
		if (paingModel.getPageindex() < paingModel.getPagecount()) {
			paingModel.setNextindex(pageCount);
		} else {
			paingModel.setNextindex(paingModel.getPageindex() + 1);
		}
		if (paingModel.getPageindex() > 1) {
			paingModel.setPreviousindex(paingModel.getPageindex() - 1);
		} else {
			paingModel.setPreviousindex(1);
		}
		return paingModel;
	}

	@Override
	public void gzipdoccontent() throws Exception {
		// TODO Auto-generated method stub
		List<ImgValue> docs = this.webResourceDao
				.getImgList(new HashMap<String, Object>());
		for (ImgValue documentValue : docs) {
			byte[] content = documentValue.getImgcontent();
			if (content != null) {
				ByteArrayOutputStream out = new ByteArrayOutputStream();
				GZIPOutputStream gzip = null;
				try {
					gzip = new GZIPOutputStream(out);
					gzip.write(content);
					gzip.finish();
					gzip.close();
					byte[] newcontent = out.toByteArray();
					out.size();
					documentValue.setImgcontent(newcontent);
					out.close();
					this.webResourceDao.updimg(documentValue);
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		}

	}

	/**
	 * 
	 * @param contentn
	 * @return
	 */
	private byte[] gzipdoccontent(byte[] contentn) {
		try {
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			GZIPOutputStream gzip = new GZIPOutputStream(out);
			gzip.write(contentn);
			gzip.finish();
			gzip.close();
			byte[] newcontent = out.toByteArray();
			out.size();
			out.close();
			return newcontent;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	/**
	 * 
	 * @param contentn
	 * @return
	 */
	private byte[] ungzipdoccontent(byte[] contentn) {
		try {
			//
			ByteArrayInputStream in = new ByteArrayInputStream(contentn);
			GZIPInputStream ginzip = new GZIPInputStream(in);
			byte[] buffer = new byte[1024];
			int offset = -1;
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			while ((offset = ginzip.read(buffer)) != -1) {
				out.write(buffer, 0, offset);
			}
			ginzip.close();
			out.flush();
			byte newcontent[] = out.toByteArray();
			out.close();
			in.close();
			return newcontent;
			//
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

}
