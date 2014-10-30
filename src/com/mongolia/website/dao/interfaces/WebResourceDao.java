package com.mongolia.website.dao.interfaces;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.mongolia.website.manager.ManagerException;
import com.mongolia.website.model.DocumentValue;
import com.mongolia.website.model.FriendNews;
import com.mongolia.website.model.FriendValue;
import com.mongolia.website.model.ImgGrpupValue;
import com.mongolia.website.model.ImgValue;
import com.mongolia.website.model.MarkedResourceValue;
import com.mongolia.website.model.MessageValue;
import com.mongolia.website.model.ShareResourceValue;
import com.mongolia.website.model.UserValue;
import com.mongolia.website.model.VisitorValue;
import com.mongolia.website.model.VoteDetailValue;
import com.mongolia.website.model.VoteResultDetailValue;
import com.mongolia.website.model.VoteResultValue;
import com.mongolia.website.model.VoteValue;

public interface WebResourceDao extends BaseDao {

	public void adddoc(DocumentValue docValue) throws Exception;

	/**
	 * 获取用户文档
	 * 
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public List<DocumentValue> getDocList(Map<String, Object> params)
			throws Exception;

	/**
	 * 删除用户所属文档
	 * 
	 * @param userId
	 * @param docid
	 * @throws Exception
	 */
	public void deleteDoc(String userId, String docid) throws Exception;

	/**
	 * 通过用户id删除文档资源
	 * 
	 * @param userid
	 * @throws Exception
	 */
	public void deleteDocByUserid(String userid) throws Exception;

	/**
	 * 获取相册列表
	 * 
	 * @param params
	 * @return
	 * @throws ManagerException
	 */
	public List<ImgGrpupValue> getImgGroupList(Map<String, Object> params)
			throws Exception;

	/**
	 * 获取图片列表
	 * 
	 * @param params
	 * @return
	 * @throws ManagerException
	 */
	public List<ImgValue> getImgList(Map<String, Object> params)
			throws Exception;

	/**
	 * 添加相片
	 * 
	 * @param imgGrpupValue
	 * @throws ManagerException
	 */
	public void addIImgGroup(ImgGrpupValue imgGrpupValue) throws Exception;

	/**
	 * 修改张片信息
	 * 
	 * @param imgGrpupValue
	 * @throws Exception
	 */
	public void updIImgGroup(ImgGrpupValue imgGrpupValue) throws Exception;

	/**
	 * 添加照片
	 * 
	 * @param imgValue
	 * @throws ManagerException
	 */
	public void addImg(ImgValue imgValue) throws Exception;

	/**
	 * 删除相册
	 * 
	 * @param groupid
	 * @throws Exception
	 */
	public void deleteImgGroup(String groupid, String userid) throws Exception;

	/**
	 * 根据用户id删除相册
	 * 
	 * @param userid
	 * @throws Exception
	 */
	public void deleteImgGroupByUserid(String userid) throws Exception;

	/**
	 * 删除图片
	 * 
	 * @param imgId
	 * @throws Exception
	 */
	public void deleteImg(String imgId) throws Exception;

	/**
	 * 根据用户索引删除图片
	 * 
	 * @param userid
	 * @throws Exception
	 */
	public void deleteImgByUserid(String userid) throws Exception;

	/**
	 * 根据用户编号删除朋友列表
	 * 
	 * @param userid
	 * @throws Exception
	 */
	public void deleteFriendByUserid(String userid) throws Exception;

	/**
	 * 根据用户id删除浏览者列表
	 * 
	 * @param userid
	 * @throws Exception
	 */
	public void deleteVisitLogByUserid(String userid) throws Exception;

	/**
	 * 根据用户id删除分享资源
	 * 
	 * @param userid
	 * @throws Exception
	 */
	public void deleteShareResourceByUserid(String userid) throws Exception;

	/**
	 * 根据用户id删除搜藏资源
	 * 
	 * @param userid
	 * @throws Exception
	 */
	public void deleteMarkeResourceByUserid(String userid) throws Exception;

	/**
	 * 根据用户id删除消息
	 * 
	 * @param userid
	 * @throws Exception
	 */
	public void deleteMessageByUserid(String userid) throws Exception;

	/**
	 * 获取工具栏
	 * 
	 * @param pageurl
	 * @return
	 * @throws Exception
	 */
	public List getToolBar(String pageurl) throws Exception;

	/**
	 * 获取朋友列表
	 * 
	 * @param friendid
	 * @param userid
	 * @param friendname
	 * @return
	 * @throws Exception
	 */
	public List<FriendValue> getFriendValues(String friendid, String userid,
			String friendname) throws Exception;

	/**
	 * 获取浏览者列表
	 * 
	 * @param visitorid
	 * @param userid
	 * @param visitorname
	 * @return
	 * @throws Exception
	 */
	public List<VisitorValue> getVisitorValues(String visitorid, String userid,
			String visitorname) throws Exception;

	/**
	 * 获取浏览者总数
	 * 
	 * @param visitorid
	 * @param userid
	 * @param visitorname
	 * @return
	 * @throws Exception
	 */
	public Integer getTotalVisitorCount(String visitorid, String userid,
			String visitorname) throws Exception;

	/**
	 * 获取当日浏览者总数
	 * 
	 * @param visitorid
	 * @param userid
	 * @param visitorname
	 * @return
	 * @throws Exception
	 */
	public Integer getCurrentDateVisitCount(String visitorid, String userid,
			String visitorname) throws Exception;

	/**
	 * 添加网站浏览记录
	 * 
	 * @param visitorValue
	 * @throws Exception
	 */
	public void addVisitLog(VisitorValue visitorValue) throws Exception;

	public void addDocReader(VisitorValue visitorValue) throws Exception;

	/**
	 * 添加消息
	 * 
	 * @param messageValue
	 * @throws Exception
	 */
	public void addCommentOnResource(MessageValue messageValue)
			throws Exception;

	/**
	 * 获取视频评论信息
	 * 
	 * @param docid
	 * @return
	 * @throws Exception
	 */
	public List<MessageValue> getCommentList(String resourceid,
			Integer resourceKind, String userid, String messid,
			String senderid, Integer status) throws Exception;

	/**
	 * 
	 * @param shareResourceValue
	 * @throws Exception
	 */
	public void addSharedResource(ShareResourceValue shareResourceValue)
			throws Exception;

	public boolean resourceisshared(String userid, String resourceid)
			throws Exception;

	public boolean resourceismarked(String userid, String resourceid)
			throws Exception;

	/**
	 * 删除分享资源
	 * 
	 * @param resourceid
	 * @param resourcekind
	 * @throws Exception
	 */
	public void delSharedResource(String resourceid, Integer resourcekind)
			throws Exception;

	/**
	 * 文章被分享
	 * 
	 * @param docid
	 * @throws Exception
	 */
	public void docisshared(String docid) throws Exception;

	/**
	 * 图片被分享
	 * 
	 * @param imgid
	 * @throws Exception
	 */
	public void imgisshared(String imgid) throws Exception;

	/**
	 * 视频被分享
	 * 
	 * @param videoid
	 * @throws Exception
	 */
	public void videoisshared(String videoid) throws Exception;

	/**
	 * 增加收藏文章
	 * 
	 * @param shareResourceValue
	 * @throws Exception
	 */
	public void addMarkedResource(MarkedResourceValue markedResourceValue)
			throws Exception;

	/**
	 * 删除收藏的资源
	 * 
	 * @param resourceid
	 * @param resourcekind
	 * @throws Exception
	 */
	public void delMarkedResource(String resourceid, Integer resourcekind)
			throws Exception;

	/**
	 * 文章被收藏
	 * 
	 * @param docid
	 * @throws Exception
	 */
	public void docIsMarked(String docid) throws Exception;

	/**
	 * 获取文章评论数
	 * 
	 * @param resourceid
	 * @param resourceKind
	 * @return
	 * @throws Exception
	 */
	public Integer getCommentCount(String resourceid, Integer resourceKind)
			throws Exception;

	/**
	 * 文章被浏览
	 * 
	 * @param docid
	 * @throws Exception
	 */
	public void doIsRead(String docid) throws Exception;

	/**
	 * 获取文章列表
	 * 
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public List<DocumentValue> getFriendsDocList(String userId)
			throws Exception;

	/**
	 * 修改文章
	 * 
	 * @param docValue
	 * @throws Exception
	 */
	public void upddoc(DocumentValue docValue) throws Exception;

	/**
	 * 删除资源上的评论信息
	 * 
	 * @param resourceid
	 * @param resourceKind
	 * @param messageid
	 * @throws Exception
	 */
	public void delResourceComment(String resourceid, Integer resourceKind,
			String messageid) throws Exception;

	/**
	 * 
	 * @param userid
	 * @param queryDate
	 * @return
	 * @throws Exception
	 */
	public List<FriendNews> getFriendNews(String userid, Date queryDate)
			throws Exception;

	/**
	 * 
	 * @param userid
	 * @param queryDate
	 * @return
	 * @throws Exception
	 */
	public List<FriendNews> getBlogNews(String userid, Date queryDate)
			throws Exception;

	public List<MessageValue> getMessList(String userid, Integer recorsend,
			String messid, String desid, Integer pageIndex)
			throws ManagerException;

	public MessageValue getMessageValueContent(String messid) throws Exception;

	/**
	 * 确认信息
	 * 
	 * @param messid
	 * @throws ManagerException
	 */
	public void confiremMess(String messid) throws Exception;

	/**
	 * 
	 * @param userid
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	public List<FriendValue> pagingQueryFriends(String userid,
			Integer pageIndex, Integer pageSize) throws Exception;

	/**
	 * 
	 * @param userid
	 * @return
	 * @throws Exception
	 */
	public Integer getFriendCount(String userid) throws Exception;

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
	 * 
	 * @param voteDetailValue
	 * @throws Exception
	 */
	public void addVoteQuestion(VoteDetailValue voteDetailValue)
			throws Exception;

	/**
	 * 获取问卷调查
	 * 
	 * @param voteid
	 * @return
	 * @throws Exception
	 */
	public List<VoteValue> getVoteValue(String voteid) throws Exception;

	/**
	 * 添加问卷调查
	 * 
	 * @param voteValue
	 * @throws Exception
	 */
	public void addVoteValue(VoteValue voteValue) throws Exception;

	/**
	 * 修改问卷调查
	 * 
	 * @param voteValue
	 * @throws Exception
	 */
	public void updVoteValue(VoteValue voteValue) throws Exception;

	/**
	 * 
	 * @param voteid
	 * @throws Exception
	 */
	public void deleteVoteValue(String voteid) throws Exception;

	/**
	 * 
	 * @param voteid
	 * @throws Exception
	 */
	public void deleteVOteDetail(String voteid, String questionid)
			throws Exception;

	/**
	 * 
	 * @param questionid
	 * @throws Exception
	 */
	public void deleteVoteQuestion(String questionid, String answerid)
			throws Exception;

	/**
	 * 获取问卷问题
	 * 
	 * @param voteid
	 * @param questionid
	 * @return
	 * @throws Exception
	 */
	public List<VoteDetailValue> getVoteQuestions(String voteid,
			String questionid) throws Exception;

	/**
	 * 
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public List<VoteValue> getVoteList(Map<String, Object> params)
			throws Exception;

	/**
	 * 
	 * @param voteResultValue
	 * @throws Exception
	 */
	public void saveVoteResult(VoteResultValue voteResultValue)
			throws Exception;

	/**
	 * 
	 * @param questionid
	 * @throws Exception
	 */
	public List<VoteResultDetailValue> calVoteResult(String questionid)
			throws Exception;

	/**
	 * 
	 * @param voteid
	 * @return
	 * @throws Exception
	 */
	public Integer getVoteJoinerCount(String voteid) throws Exception;

	/**
	 * 
	 * @param questionid
	 * @return
	 * @throws Exception
	 */
	public List<VoteResultValue> getMultiQuestionResult(String questionid)
			throws Exception;

	/**
	 * 
	 * @param voteid
	 * @param questionid
	 * @return
	 * @throws Exception
	 */
	public Integer voteResultvcount(String voteid, String questionid)
			throws Exception;

	/**
	 * 
	 * @param voteid
	 * @param questionid
	 * @param startindex
	 * @param fechcount
	 * @return
	 */
	public List<VoteResultValue> getVoteResult(String voteid,
			String questionid, Integer startindex, Integer fechcount);

	/**
	 * 
	 * @param imggroupid
	 * @throws Exception
	 */
	public void deleteImgByGroupId(String imggroupid, String userid)
			throws Exception;

	/**
	 * 获取用户收件箱里面信息个数
	 * 
	 * @param userid
	 * @return
	 * @throws Exception
	 */
	public Integer getMessageCount(String userid) throws Exception;

	/**
	 * 获取分享资源个数
	 * 
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public Integer getSharedDocCount(Map<String, Object> params, int doctype)
			throws Exception;

	/**
	 * 获取分享作文列表
	 * 
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public List<DocumentValue> getSharedDocs(Map<String, Object> params)
			throws Exception;

	/**
	 * 
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public List<ImgValue> getSharedImgs(Map<String, Object> params)
			throws Exception;

	public void setDocCommentCount(String docid, String add, String sub)
			throws Exception;

	/**
	 * 
	 * @param imgValue
	 * @throws Exception
	 */
	public void updimg(ImgValue imgValue) throws Exception;

	/**
	 * 获取最新的评论信息
	 * 
	 * @param messcount
	 * @return
	 * @throws Exception
	 */
	public List<MessageValue> getRecentDocComm(int messcount,
			Integer resourcekind, Integer messtype) throws Exception;

	/**
	 * 获取热门文章
	 * 
	 * @param doccount
	 * @return
	 * @throws Exception
	 */
	public List<DocumentValue> getTopDocuments(Integer doccount)
			throws Exception;

	/**
	 * 获取评论列表
	 * 
	 * @param queryParams
	 * @return
	 * @throws Exception
	 */
	public List<MessageValue> getCommentList(Map<String, Object> queryParams)
			throws Exception;

	/**
	 * 获取评论总行数
	 * 
	 * @param queryParams
	 * @return
	 * @throws Exception
	 */
	public Integer getCommentCount(Map<String, Object> queryParams)
			throws Exception;

	public List<UserValue> getOldUsers() throws Exception;

	public List<DocumentValue> getOldDocs() throws Exception;

	public List<ImgValue> getOldImgs() throws Exception;

	public List<MessageValue> getOldMess() throws Exception;

	public List<ImgGrpupValue> getOldImgGroup() throws Exception;

	public List<DocumentValue> getRecentDocs(Integer count) throws Exception;

	public List<UserValue> getRecentActiveUsers(Integer count)
			throws Exception;

}
