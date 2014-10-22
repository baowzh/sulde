package com.mongolia.website.manager.interfaces;

import java.util.List;
import java.util.Map;

import com.mongolia.website.manager.ManagerException;
import com.mongolia.website.model.DocumentValue;
import com.mongolia.website.model.FriendValue;
import com.mongolia.website.model.ImgGrpupValue;
import com.mongolia.website.model.ImgValue;
import com.mongolia.website.model.MessageValue;
import com.mongolia.website.model.PaingModel;
import com.mongolia.website.model.UserValue;
import com.mongolia.website.model.VisitorValue;
import com.mongolia.website.model.VoteDetailForm;
import com.mongolia.website.model.VoteDetailValue;
import com.mongolia.website.model.VoteResultDetailValue;
import com.mongolia.website.model.VoteResultValue;
import com.mongolia.website.model.VoteValue;

public interface WebResourceManager {
	/**
	 * 添加文章
	 * 
	 * @param docValue
	 * @throws ManagerException
	 */
	public void doAdddoc(DocumentValue docValue) throws ManagerException;

	/**
	 * 获取文章列表
	 * 
	 * @param params
	 * @return
	 * @throws ManagerException
	 */
	public List<DocumentValue> getDocList(Map<String, Object> params)
			throws ManagerException;

	/**
	 * 
	 * @param docid
	 * @return
	 * @throws ManagerException
	 */
	public DocumentValue readUserDDocument(String docid, UserValue userValue)
			throws ManagerException;

	/**
	 * 删除文章
	 * 
	 * @param userId
	 * @param docid
	 * @throws ManagerException
	 */
	public void doDeleteDoc(String userId, String docid, String docType)
			throws ManagerException;

	/**
	 * 获取相册列表
	 * 
	 * @param params
	 * @return
	 * @throws ManagerException
	 */
	public List<ImgGrpupValue> getImgGroupList(Map<String, Object> params)
			throws ManagerException;

	/**
	 * 获取图片列表
	 * 
	 * @param params
	 * @return
	 * @throws ManagerException
	 */
	public List<ImgValue> getImgList(Map<String, Object> params)
			throws ManagerException;

	/**
	 * 添加相片
	 * 
	 * @param imgGrpupValue
	 * @throws ManagerException
	 */
	public void doAddIImgGroup(ImgGrpupValue imgGrpupValue)
			throws ManagerException;

	/**
	 * 修改相册
	 * 
	 * @param imgGrpupValue
	 * @throws ManagerException
	 */
	public void doUpdIImgGroup(ImgGrpupValue imgGrpupValue)
			throws ManagerException;

	/**
	 * 删除相册
	 * 
	 * @param groupid
	 * @throws ManagerException
	 */
	public void doDeleteImgGroup(String groupid, String userid)
			throws ManagerException;

	/**
	 * 添加照片
	 * 
	 * @param imgValue
	 * @throws ManagerException
	 */
	public void doAddImg(ImgValue imgValue) throws ManagerException;

	/**
	 * 
	 * @param imgId
	 * @throws ManagerException
	 */
	public void doDeleteImg(String imgId) throws ManagerException;

	/**
	 * 获取工具栏
	 * 
	 * @param pageurl
	 * @return
	 * @throws ManagerException
	 */
	public List getToolBar(String pageurl) throws ManagerException;

	/**
	 * 
	 * @param friendid
	 * @param userid
	 * @param friendname
	 * @param fetchcount
	 * @return
	 * @throws ManagerException
	 */
	public List<FriendValue> getFriendValues(String friendid, String userid,
			String friendname, int fetchcount) throws ManagerException;

	/**
	 * 获取浏览者
	 * 
	 * @param visitorid
	 * @param userid
	 * @param visitorname
	 * @return
	 * @throws ManagerException
	 */
	public List<VisitorValue> getVisitorValues(String visitorid, String userid,
			String visitorname, int fetchcount) throws ManagerException;

	/**
	 * 获取博客页面展示的数据
	 * 
	 * @param sessionUser
	 * @return
	 * @throws ManagerException
	 */
	public Map<String, Object> getBlogInfo(UserValue blogUser,
			UserValue sessionUser, Integer self, String docchannel)
			throws ManagerException;

	/**
	 * 
	 * @param resourceid
	 * @param resourceType
	 * @param userid
	 * @param comment
	 * @param sendderid
	 * @throws ManagerException
	 */
	public void doAddCommentOnResource(String resourceid, Integer resourceType,
			String userid, String comment, String sendderid,
			String messageSenderName, Integer messType, Integer hidden)
			throws ManagerException;

	/**
	 * 获取资源评论
	 * 
	 * @param resourceid
	 * @param resourceType
	 * @return
	 * @throws ManagerException
	 */
	public List<MessageValue> getResourceCommentList(String resourceid,
			Integer resourceType, String userid, String messid,
			String senderid, Integer staus) throws ManagerException;

	public Integer getResourceCommentCount(String resourceid,
			Integer resourceType) throws ManagerException;

	/**
	 * 
	 * @param docid
	 * @param currentuserid
	 * @throws ManagerException
	 */
	public void doShareDdoc(String docid, String currentuserid)
			throws ManagerException;

	/**
	 * 
	 * @param docid
	 * @param currentuserid
	 * @throws ManagerException
	 */
	public void doShareImg(String docid, String currentuserid)
			throws ManagerException;

	/**
	 * 
	 * @param docid
	 * @param currentuserid
	 * @throws ManagerException
	 */
	public void doShareVideo(String docid, String currentuserid)
			throws ManagerException;

	/**
	 * 收藏文章
	 * 
	 * @param docid
	 * @param currentuserid
	 * @throws ManagerException
	 */
	public void doMarkDoc(String docid, String currentuserid)
			throws ManagerException;

	/**
	 * 修改文章
	 * 
	 * @param docValue
	 * @throws ManagerException
	 */
	public void doUpdDoc(DocumentValue docValue) throws ManagerException;

	/**
	 * 删除评论
	 * 
	 * @param resourceid
	 * @param resourceType
	 * @param userid
	 * @param messageid
	 * @throws ManagerException
	 */
	public void doDelResourceComment(String resourceid, Integer resourceType,
			String userid, String messageid) throws ManagerException;

	/**
	 * 获取收件箱列表
	 * 
	 * @param userid
	 * @param pageIndex
	 * @return
	 * @throws ManagerException
	 */
	public List<MessageValue> getReceMessList(String userid, String messid,
			Integer pageIndex) throws ManagerException;

	/**
	 * 
	 * @param userid
	 * @param recorsend
	 * @param pageIndex
	 * @return
	 * @throws ManagerException
	 */
	public List<MessageValue> getSendMessList(String userid, String messid,
			Integer pageIndex) throws ManagerException;

	/**
	 * 
	 * @param messid
	 * @return
	 * @throws ManagerException
	 */
	public MessageValue getMessageValueContent(String messid)
			throws ManagerException;

	/**
	 * 
	 * @param resourceid
	 * @param resourceType
	 * @param userid
	 * @param comment
	 * @param sendderid
	 * @param messageSenderName
	 * @param messType
	 * @throws ManagerException
	 */
	public void doSendMess(String resourceid, Integer resourceType,
			String userid, String comment, String sendderid,
			String messageSenderName, Integer messType) throws ManagerException;

	/**
	 * 
	 * @param userid
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 * @throws ManagerException
	 */
	public Map<String, Object> pagingQueryFriends(String userid,
			Integer pageIndex, Integer pageSize) throws ManagerException;

	/**
	 * 
	 * @param docid
	 * @return
	 * @throws Exception
	 */
	public List<DocumentValue> getNextDoc(String docid, String groupid)
			throws Exception;

	/**
	 * 
	 * @param docid
	 * @return
	 * @throws Exception
	 */
	public List<DocumentValue> getPreviousDoc(String docid, String groupid)
			throws Exception;

	/**
	 * 给某个问卷添加问题
	 */
	public List<VoteDetailValue> doAddVoteQuestion(
			VoteDetailForm voteDetailValue) throws Exception;

	/**
	 * 
	 * @param voteid
	 * @param topic
	 * @throws Exception
	 */
	public void doSaveVote(String voteid, String topic, String userid)
			throws Exception;

	/**
	 * 根据问卷id和问题id 获取问题
	 * 
	 * @param voteid
	 * @param questionid
	 * @throws Exception
	 */
	public List<VoteDetailValue> getVoteQuestion(String voteid,
			String questionid) throws Exception;

	/**
	 * 获取问卷调查列表
	 * 
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public List<VoteValue> getVoteList(Map<String, Object> params)
			throws Exception;

	/**
	 * 
	 * @param singquestionrel
	 * @param multiquestionrel
	 * @param textquestionrel
	 * @throws Exception
	 */
	public void doSaveVoteResult(String voteid, String singquestionrel,
			String multiquestionrel, String textquestionrel, String userid)
			throws Exception;

	/**
	 * 计算某个问题的投票比例
	 * 
	 * @param voteid
	 * @param questionid
	 * @return
	 * @throws Exception
	 */
	public List<VoteResultDetailValue> calVoteResult(String voteid,
			String questionid) throws Exception;

	/**
	 * 
	 * @param voteid
	 * @param userid
	 * @throws Exception
	 */
	public void doDelVote(String voteid, String userid) throws Exception;

	/**
	 * 
	 * @param voteid
	 * @return
	 * @throws Exception
	 */

	public Map<String, Object> checkVoteStatus(String voteid) throws Exception;

	/**
	 * 删除问卷调调查中的一个问题
	 * 
	 * @param voteid
	 * @param questionid
	 * @throws Exception
	 */
	public void doDelVoteQuestion(String voteid, String questionid)
			throws Exception;

	/**
	 * 
	 * @param questionid
	 * @param answerid
	 * @throws Exception
	 */
	public void doDelVoteQuestionSel(String questionid, String answerid)
			throws Exception;

	/**
	 * 
	 * @param voteid
	 * @param questionid
	 * @param pageindex
	 * @return
	 * @throws Exception
	 */
	public PaingModel<VoteResultValue> pagingqueryVoteResult(String voteid,
			String questionid, Integer pageindex) throws Exception;

	/**
	 * 
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public PaingModel<DocumentValue> pagingQuerySharedDocs(Map<String, Object> params,
			int doctype, int pageindex, int pagesize) throws Exception;

	/**
	 * 
	 * @throws Exception
	 */
	public void gzipdoccontent() throws Exception;

	/**
	 * 
	 * @param params
	 * @throws Exception
	 */
	public PaingModel<MessageValue> paingQueryComment(
			Map<String, Object> queryDocForm, Integer rowCount,
			Integer pageindex) throws Exception;

	public void synOldUser() throws Exception;

	public void synOldDoc() throws Exception;

	public void synOldMess() throws Exception;

	public void synOldImg() throws Exception;

}
