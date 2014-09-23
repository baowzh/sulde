package com.mongolia.website.dao.impls;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.mongolia.website.dao.interfaces.WebResourceDao;
import com.mongolia.website.manager.ManagerException;
import com.mongolia.website.model.DocumentValue;
import com.mongolia.website.model.FriendNews;
import com.mongolia.website.model.FriendValue;
import com.mongolia.website.model.ImgGrpupValue;
import com.mongolia.website.model.ImgValue;
import com.mongolia.website.model.MarkedResourceValue;
import com.mongolia.website.model.MessageValue;
import com.mongolia.website.model.QuestionValue;
import com.mongolia.website.model.ShareResourceValue;
import com.mongolia.website.model.VisitorValue;
import com.mongolia.website.model.VoteDetailValue;
import com.mongolia.website.model.VoteResultDetailValue;
import com.mongolia.website.model.VoteResultValue;
import com.mongolia.website.model.VoteValue;
import com.mongolia.website.util.StaticConstants;

@Repository("webResourceDao")
public class WebResourceDaoImpl extends BaseDaoiBatis implements WebResourceDao {

	@Override
	public void adddoc(DocumentValue docValue) throws Exception {
		// TODO Auto-generated method stub
		this.getSqlMapClientTemplate().insert("addDocumentValue", docValue);

	}

	@Override
	public void deleteDoc(String userId, String docid) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> delParams = new HashMap<String, Object>();
		delParams.put("docid", docid);
		delParams.put("userid", userId);
		this.getSqlMapClientTemplate().delete("delDocument", delParams);
	}

	@Override
	public List<DocumentValue> getDocList(Map<String, Object> params)
			throws Exception {
		// TODO Auto-generated method stub
		return this.getSqlMapClientTemplate().queryForList("getUserDocList",
				params);
	}

	@Override
	public void addIImgGroup(ImgGrpupValue imgGrpupValue) throws Exception {
		// TODO Auto-generated method stub
		this.getSqlMapClientTemplate()
				.insert("addImgGrpupValue", imgGrpupValue);

	}

	@Override
	public void addImg(ImgValue imgValue) throws Exception {
		// TODO Auto-generated method stub
		this.getSqlMapClientTemplate().insert("addImgValue", imgValue);

	}

	@Override
	public List<ImgGrpupValue> getImgGroupList(Map<String, Object> params)
			throws Exception {
		// TODO Auto-generated method stub
		return this.getSqlMapClientTemplate().queryForList("getImgGrpupList",
				params);
	}

	@Override
	public List<ImgValue> getImgList(Map<String, Object> params)
			throws Exception {
		// TODO Auto-generated method stub
		return this.getSqlMapClientTemplate()
				.queryForList("getImgList", params);
	}

	@Override
	public void deleteImg(String imgId) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("imgid", imgId);
		this.getSqlMapClientTemplate().delete("deleteimg", params);

	}

	@Override
	public void deleteImgGroup(String groupid, String userid) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("imggroupid", groupid);
		params.put("userid", userid);
		this.getSqlMapClientTemplate().delete("deleteimggroup", params);
	}

	@Override
	public List getToolBar(String pageurl) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("url", pageurl);
		return this.getSqlMapClientTemplate().queryForList("getPageToolsBar",
				params);
	}

	@Override
	public void updIImgGroup(ImgGrpupValue imgGrpupValue) throws Exception {
		// TODO Auto-generated method stub
		this.getSqlMapClientTemplate().update("updateimggroup", imgGrpupValue);

	}

	@Override
	public List<FriendValue> getFriendValues(String friendid, String userid,
			String friendname) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("friendid", friendid);
		params.put("userid", userid);
		params.put("friendname", friendname);
		return this.getSqlMapClientTemplate().queryForList("getFriendList",
				params);
	}

	@Override
	public List<VisitorValue> getVisitorValues(String visitorid, String userid,
			String visitorname) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("visitorid", visitorid);
		params.put("userid", userid);
		params.put("visitorname", visitorname);
		params.put("visitortype", StaticConstants.VISITOR_TYPE_REG);
		return this.getSqlMapClientTemplate().queryForList("getVisitorList",
				params);
	}

	@Override
	public Integer getCurrentDateVisitCount(String visitorid, String userid,
			String visitorname) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> queryParams = new HashMap<String, Object>();
		queryParams.put("visitorid", visitorid);
		queryParams.put("userid", userid);
		queryParams.put("visitorname", visitorname);
		queryParams.put("visitdate", new Date());
		return (Integer) (this.getSqlMapClientTemplate().queryForObject(
				"getDateVisitCount", queryParams));
	}

	@Override
	public Integer getTotalVisitorCount(String visitorid, String userid,
			String visitorname) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> queryParams = new HashMap<String, Object>();
		queryParams.put("visitorid", visitorid);
		queryParams.put("userid", userid);
		queryParams.put("visitorname", visitorname);
		return (Integer) (this.getSqlMapClientTemplate().queryForObject(
				"getTotalVisitorCount", queryParams));
	}

	@Override
	public void addVisitLog(VisitorValue visitorValue) throws Exception {
		// TODO Auto-generated method stub
		this.getSqlMapClientTemplate().insert("addVisitorValue", visitorValue);

	}

	@Override
	public void addDocReader(VisitorValue visitorValue) throws Exception {
		// TODO Auto-generated method stub
		this.addVisitLog(visitorValue);
		Map<String, Object> updateparams = new HashMap<String, Object>();
		updateparams.put("docid", visitorValue.getVisitobjid());
		this.getSqlMapClientTemplate().update("addreadertodoc", updateparams);
	}

	@Override
	public void addCommentOnResource(MessageValue messageValue)
			throws Exception {
		// TODO Auto-generated method stub
		this.getSqlMapClientTemplate().insert("addMessageValue", messageValue);
	}

	@Override
	public List<MessageValue> getCommentList(String resourceid,
			Integer resourceKind, String userid, String messageid,
			String senderid) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> queryparams = new HashMap<String, Object>();
		queryparams.put("resourceid", resourceid);
		queryparams.put("resourcekind", resourceKind);
		queryparams.put("userid", userid);
		queryparams.put("messagesenderid", senderid);
		queryparams.put("messageid", messageid);
		return this.getSqlMapClientTemplate().queryForList(
				"getResourceCommentList", queryparams);
	}

	@Override
	public void addSharedResource(ShareResourceValue shareResourceValue)
			throws Exception {
		// TODO Auto-generated method stub
		this.getSqlMapClientTemplate().insert("addShareResourceValue",
				shareResourceValue);

	}

	@Override
	public void docisshared(String docid) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> updparams = new HashMap<String, Object>();
		updparams.put("docid", docid);
		this.getSqlMapClientTemplate().update("docisshared", updparams);

	}

	@Override
	public void imgisshared(String imgid) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void videoisshared(String videoid) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void addMarkedResource(MarkedResourceValue markedResourceValue)
			throws Exception {
		// TODO Auto-generated method stub
		this.getSqlMapClientTemplate().insert("addMarkedResourceValue",
				markedResourceValue);

	}

	@Override
	public void docIsMarked(String docid) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> updparams = new HashMap<String, Object>();
		updparams.put("docid", docid);
		this.getSqlMapClientTemplate().update("docismarked", updparams);
	}

	@Override
	public Integer getCommentCount(String resourceid, Integer resourceKind)
			throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> queryparams = new HashMap<String, Object>();
		queryparams.put("resourceid", resourceid);
		queryparams.put("resourcekind", resourceKind);
		return (Integer) (this.getSqlMapClientTemplate().queryForObject(
				"getResourceCommentCount", queryparams));
	}

	@Override
	public void doIsRead(String docid) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> updparams = new HashMap<String, Object>();
		updparams.put("docid", docid);
		this.getSqlMapClientTemplate().update("docisVisited", updparams);
	}

	@Override
	public List<DocumentValue> getFriendsDocList(String userId)
			throws Exception {
		// TODO Auto-generated method stub
		// 获取当前日期
		// 获取前10天
		// 前10条
		Date currentDate = new Date();
		// Date lastTenDate=new Date();
		Long lastTenDateLong = System.currentTimeMillis()
				- new Long(10 * 24 * 60 * 60 * 1000);
		Date lastTenDay = new Date(lastTenDateLong);
		Map<String, Object> queryDocParams = new HashMap<String, Object>();
		queryDocParams.put("start", 0);
		queryDocParams.put("end", 10);
		queryDocParams.put("currentDate", currentDate);
		queryDocParams.put("lastTenDay", lastTenDay);
		queryDocParams.put("userid", userId);
		return this.getSqlMapClientTemplate().queryForList("getFriendsDocList",
				queryDocParams);
	}

	@Override
	public void upddoc(DocumentValue docValue) throws Exception {
		// TODO Auto-generated method stub
		this.getSqlMapClientTemplate().update("updDocumentValue", docValue);

	}

	@Override
	public void delResourceComment(String resourceid, Integer resourceKind,
			String messageid) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> deleteParams = new HashMap<String, Object>();
		deleteParams.put("resourceid", resourceid);
		deleteParams.put("resourcekind", resourceKind);
		deleteParams.put("messageid", messageid);
		this.getSqlMapClientTemplate().delete("delResourceComment",
				deleteParams);

	}

	@Override
	public void delMarkedResource(String resourceid, Integer resourcekind)
			throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> delparams = new HashMap<String, Object>();
		delparams.put("resourceid", resourceid);
		delparams.put("resourcekind", resourcekind);
		this.getSqlMapClientTemplate().delete("delMarkedresource", delparams);

	}

	@Override
	public void delSharedResource(String resourceid, Integer resourcekind)
			throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> delparams = new HashMap<String, Object>();
		delparams.put("resourceid", resourceid);
		delparams.put("resourcekind", resourcekind);
		this.getSqlMapClientTemplate().delete("delSharedResource", delparams);

	}

	@Override
	public List<FriendNews> getBlogNews(String userid, Date queryDate)
			throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> queryparams = new HashMap<String, Object>();
		queryparams.put("userid", userid);
		queryparams.put("queryDate", queryDate);
		return this.getSqlMapClientTemplate().queryForList("getBlogUserNews",
				queryparams);
	}

	@Override
	public List<FriendNews> getFriendNews(String userid, Date queryDate)
			throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> queryparams = new HashMap<String, Object>();
		queryparams.put("userid", userid);
		queryparams.put("queryDate", queryDate);
		return this.getSqlMapClientTemplate().queryForList("getFriendNews",
				queryparams);
	}

	@Override
	public void deleteDocByUserid(String userid) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> queryparams = new HashMap<String, Object>();
		queryparams.put("userid", userid);
		this.getSqlMapClientTemplate().delete("deleteDocByUserid", queryparams);

	}

	@Override
	public void deleteFriendByUserid(String userid) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> queryparams = new HashMap<String, Object>();
		queryparams.put("userid", userid);
		this.getSqlMapClientTemplate().delete("deleteFriendByUserid",
				queryparams);

	}

	@Override
	public void deleteImgByUserid(String userid) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> queryparams = new HashMap<String, Object>();
		queryparams.put("userid", userid);
		this.getSqlMapClientTemplate().delete("deleteImgByUserid", queryparams);
	}

	@Override
	public void deleteImgGroupByUserid(String userid) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> queryparams = new HashMap<String, Object>();
		queryparams.put("userid", userid);
		this.getSqlMapClientTemplate().delete("deleteImgGroupByUserid",
				queryparams);
	}

	@Override
	public void deleteMarkeResourceByUserid(String userid) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> queryparams = new HashMap<String, Object>();
		queryparams.put("userid", userid);
		this.getSqlMapClientTemplate().delete("deleteMarkeResourceByUserid",
				queryparams);
	}

	@Override
	public void deleteMessageByUserid(String userid) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> queryparams = new HashMap<String, Object>();
		queryparams.put("userid", userid);
		this.getSqlMapClientTemplate().delete("deleteMessageByUserid",
				queryparams);
	}

	@Override
	public void deleteShareResourceByUserid(String userid) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> queryparams = new HashMap<String, Object>();
		queryparams.put("userid", userid);
		this.getSqlMapClientTemplate().delete("deleteShareResourceByUserid",
				queryparams);
	}

	@Override
	public void deleteVisitLogByUserid(String userid) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> queryparams = new HashMap<String, Object>();
		queryparams.put("userid", userid);
		this.getSqlMapClientTemplate().delete("deleteVisitLogByUserid",
				queryparams);
	}

	@Override
	public List<MessageValue> getMessList(String userid, Integer recorsend,
			String messid, String desid, Integer pageIndex)
			throws ManagerException {
		// TODO Auto-generated method stub
		Map<String, Object> queryParams = new HashMap<String, Object>();
		queryParams.put("userid", userid);
		queryParams.put("recorsend", recorsend);
		if (recorsend.intValue() == 1) {
			queryParams.put("receiveid", desid);
			queryParams.put("messtype", StaticConstants.MESS_TYPE_RECEIVE);
		} else {
			queryParams.put("messagesenderid", desid);
			queryParams.put("messtype", StaticConstants.MESS_TYPE_SEND);
		}
		queryParams.put("messid", messid);
		queryParams.put("pageIndex", pageIndex);
		// 获取信息时候连添加朋友请求一起获取
		List<MessageValue> mess = this.getSqlMapClientTemplate().queryForList(
				"getMessageList", queryParams);
		if (recorsend.intValue() == 1) {
			queryParams.put("messtype", StaticConstants.MESS_TYPE_QUSTION);
			queryParams.put("received", 0);
			List<MessageValue> qustions = this.getSqlMapClientTemplate()
					.queryForList("getMessageList", queryParams);
			mess.addAll(qustions);
		}
		return mess;
	}

	@Override
	public MessageValue getMessageValueContent(String messid)
			throws ManagerException {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("messid", messid);
		return (MessageValue) this.getSqlMapClientTemplate().queryForObject(
				"messageValueContent", params);
	}

	@Override
	public void confiremMess(String messid) throws ManagerException {
		// TODO Auto-generated method stub
		Map<String, Object> updParams = new HashMap<String, Object>();
		updParams.put("messageid", messid);
		this.getSqlMapClientTemplate().update("confirmmess", updParams);
	}

	@Override
	public Integer getFriendCount(String userid) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> queryparams = new HashMap<String, Object>();
		queryparams.put("userid", userid);
		return (Integer) this.getSqlMapClientTemplate().queryForObject(
				"QueryFriendCount", queryparams);
	}

	@Override
	public List<FriendValue> pagingQueryFriends(String userid,
			Integer startindex, Integer fetchcount) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> queryparams = new HashMap<String, Object>();
		queryparams.put("userid", userid);
		queryparams.put("startindex", startindex);
		queryparams.put("fetchcount", fetchcount);
		return this.getSqlMapClientTemplate().queryForList(
				"pagingQueryFriends", queryparams);
	}

	@Override
	public List<DocumentValue> getNextDoc(String docid, String groupid)
			throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> queryParams = new HashMap<String, Object>();
		queryParams.put("docid", docid);
		queryParams.put("imggroupid", groupid);

		return this.getSqlMapClientTemplate().queryForList("nextdoc",
				queryParams);
	}

	@Override
	public List<DocumentValue> getPreviousDoc(String docid, String groupid)
			throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> queryParams = new HashMap<String, Object>();
		queryParams.put("docid", docid);
		queryParams.put("imggroupid", groupid);
		return this.getSqlMapClientTemplate().queryForList("previosdoc",
				queryParams);
	}

	@Override
	public void addVoteQuestion(VoteDetailValue voteDetailValue)
			throws Exception {
		// TODO Auto-generated method stub
		// 1. 添加问题
		this.getSqlMapClientTemplate().insert("addVoteDetailValue",
				voteDetailValue);
		// 2.添加问题选项
		for (QuestionValue questionValue : voteDetailValue.getQuestionValues()) {
			this.getSqlMapClientTemplate().insert("addQuestionValue",
					questionValue);
		}
	}

	@Override
	public List<VoteValue> getVoteValue(String voteid) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("voteid", voteid);
		return this.getSqlMapClientTemplate().queryForList("getVoteValue",
				params);
	}

	@Override
	public void addVoteValue(VoteValue voteValue) throws Exception {
		// TODO Auto-generated method stub
		this.getSqlMapClientTemplate().insert("addVote", voteValue);

	}

	@Override
	public void updVoteValue(VoteValue voteValue) throws Exception {
		// TODO Auto-generated method stub
		this.getSqlMapClientTemplate().update("updateVoteValue", voteValue);
	}

	@Override
	public void deleteVoteValue(String voteid) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> delpatams = new HashMap<String, Object>();
		delpatams.put("voteid", voteid);
		this.getSqlMapClientTemplate().delete("deleteVote", delpatams);
	}

	@Override
	public List<VoteDetailValue> getVoteQuestions(String voteid,
			String questionid) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> queryParams = new HashMap<String, Object>();
		queryParams.put("voteid", voteid);
		queryParams.put("questionid", questionid);
		List<VoteDetailValue> details = this.getSqlMapClientTemplate()
				.queryForList("getVoteDetailValue", queryParams);
		for (VoteDetailValue voteDetailValuei : details) {
			Map<String, Object> queryQuestionParams = new HashMap<String, Object>();
			queryQuestionParams.put("questionid",
					voteDetailValuei.getQuestionid());
			List<QuestionValue> questionanswers = this
					.getSqlMapClientTemplate().queryForList(
							"getVoteQuestionDetail", queryQuestionParams);
			voteDetailValuei.setQuestionValues(questionanswers);
		}
		return details;
	}

	@Override
	public List<VoteValue> getVoteList(Map<String, Object> params)
			throws Exception {
		// TODO Auto-generated method stub
		return this.getSqlMapClientTemplate().queryForList("getVoteValue",
				params);
	}

	@Override
	public void saveVoteResult(VoteResultValue voteResultValue)
			throws Exception {
		// TODO Auto-generated method stub
		this.getSqlMapClientTemplate().insert("addVoteResultValue",
				voteResultValue);
		for (VoteResultDetailValue voteResultDetailValue : voteResultValue
				.getDetail()) {
			this.getSqlMapClientTemplate().insert("addVoteResultDetailValue",
					voteResultDetailValue);
		}
	}

	@Override
	public List<VoteResultDetailValue> calVoteResult(String questionid)
			throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> queryParams = new HashMap<String, Object>();
		queryParams.put("questionid", questionid);
		List<VoteResultDetailValue> result = this.getSqlMapClientTemplate()
				.queryForList("getVoteResultEvery", queryParams);
		// Integer votecount = (Integer) this.getSqlMapClientTemplate()
		// .queryForObject("getVoteResult", queryParams);
		// for (VoteResultDetailValue voteResultDetailValue : result) {
		// Double percentage = new Double(voteResultDetailValue.getVotenum()
		// / votecount);
		// voteResultDetailValue.setPercentage(percentage);
		// }
		return result;
	}

	@Override
	public void deleteVOteDetail(String voteid, String questionid)
			throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("voteid", voteid);
		params.put("questionid", questionid);
		this.getSqlMapClientTemplate().delete("deleteVoteDetail", params);

	}

	@Override
	public void deleteVoteQuestion(String questionid, String answerid)
			throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("questionid", questionid);
		params.put("answerid", answerid);
		this.getSqlMapClientTemplate().delete("deleteVoteQuestion", params);
	}

	@Override
	public Integer getVoteJoinerCount(String voteid) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("voteid", voteid);
		return (Integer) this.getSqlMapClientTemplate().queryForObject(
				"getVoteJoinerCount", params);
	}

	@Override
	public List<VoteResultValue> getMultiQuestionResult(String questionid)
			throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("questionid", questionid);
		return this.getSqlMapClientTemplate().queryForList(
				"getMultiQuestionResult", params);
	}

	@Override
	public boolean resourceisshared(String userid, String resourceid)
			throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> queryParams = new HashMap<String, Object>();
		queryParams.put("userid", userid);
		queryParams.put("resourceid", resourceid);
		Integer result = (Integer) this.getSqlMapClientTemplate()
				.queryForObject("isshared", queryParams);
		if (result == null || result.intValue() == 0) {
			return false;
		} else {
			return true;
		}

	}

	@Override
	public boolean resourceismarked(String userid, String resourceid)
			throws Exception {
		Map<String, Object> queryParams = new HashMap<String, Object>();
		queryParams.put("userid", userid);
		queryParams.put("resourceid", resourceid);
		Integer result = (Integer) this.getSqlMapClientTemplate()
				.queryForObject("ismarked", queryParams);
		if (result == null || result.intValue() == 0) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public Integer voteResultvcount(String voteid, String questionid)
			throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> queryParams = new HashMap<String, Object>();
		queryParams.put("voteid", voteid);
		queryParams.put("questionid", questionid);
		return (Integer) this.getSqlMapClientTemplate().queryForObject(
				"getVoteResultCount", queryParams);
	}

	@Override
	public List<VoteResultValue> getVoteResult(String voteid,
			String questionid, Integer startindex, Integer fechcount) {
		// TODO Auto-generated method stub
		Map<String, Object> queryParams = new HashMap<String, Object>();
		queryParams.put("voteid", voteid);
		queryParams.put("questionid", questionid);
		queryParams.put("startindex", startindex);
		queryParams.put("fechcount", fechcount);
		return this.getSqlMapClientTemplate().queryForList("pagingVoteResult",
				queryParams);
	}

	@Override
	public void deleteImgByGroupId(String imggroupid, String userid)
			throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> deletparams = new HashMap<String, Object>();
		deletparams.put("imggroupid", imggroupid);
		deletparams.put("userid", userid);
		this.getSqlMapClientTemplate().delete("deleteimg", deletparams);
	}

	@Override
	public Integer getMessageCount(String userid) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> queryparams = new HashMap<String, Object>();
		queryparams.put("userid", userid);
		return (Integer) this.getSqlMapClientTemplate().queryForObject(
				"getMessageCount", queryparams);
	}

	@Override
	public Integer getSharedDocCount(Map<String, Object> params, int doctype)
			throws Exception {
		// TODO Auto-generated method stub
		params.put("doctype", doctype);
		return (Integer) this.getSqlMapClientTemplate().queryForObject(
				"getSharedDocCount", params);
	}

	@Override
	public List<DocumentValue> getSharedDocs(Map<String, Object> params)
			throws Exception {
		// TODO Auto-generated method stub
		return this.getSqlMapClientTemplate().queryForList("getSharedDocs",
				params);
	}

	@Override
	public List<ImgValue> getSharedImgs(Map<String, Object> params)
			throws Exception {
		// TODO Auto-generated method stub
		return this.getSqlMapClientTemplate().queryForList("getSharedImgs",
				params);
	}

	@Override
	public void setDocCommentCount(String docid, String add, String sub)
			throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("docid", docid);
		params.put("add", add);
		params.put("sub", sub);
		this.getSqlMapClientTemplate().update("setcommentcount", params);

	}

	@Override
	public void updimg(ImgValue imgValue) throws Exception {
		// TODO Auto-generated method stub
		this.getSqlMapClientTemplate().update("updateimgcontent", imgValue);
	}

}
