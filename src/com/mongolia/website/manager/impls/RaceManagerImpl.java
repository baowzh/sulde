package com.mongolia.website.manager.impls;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mongolia.website.dao.interfaces.RaceDao;
import com.mongolia.website.dao.interfaces.UserManagerDao;
import com.mongolia.website.dao.interfaces.WebPageManagerDao;
import com.mongolia.website.dao.interfaces.WebSiteVisitorDao;
import com.mongolia.website.manager.interfaces.RaceManager;
import com.mongolia.website.model.DocumentValue;
import com.mongolia.website.model.ImgValue;
import com.mongolia.website.model.PageChannelRelationValue;
import com.mongolia.website.model.PaingModel;
import com.mongolia.website.model.RaceDocumentValue;
import com.mongolia.website.model.RaceModelValue;
import com.mongolia.website.model.RaceRound;
import com.mongolia.website.model.RaceScoreLogValue;
import com.mongolia.website.model.RaceUser;
import com.mongolia.website.model.RaceUserModel;
import com.mongolia.website.model.UserValue;
import com.mongolia.website.util.StaticConstants;

@Service("raceManager")
public class RaceManagerImpl implements RaceManager {
	@Autowired
	private RaceDao raceDao;
	@Autowired
	private UserManagerDao userManagerDao;
	@Autowired
	private WebPageManagerDao webPageManagerDao;
	@Autowired
	private WebSiteVisitorDao webSiteVisitorDao;
	public static Integer GER_USER_TYPE_CHILD = 2;

	@Override
	public List<RaceModelValue> getRaceModels(String raceid, Integer inactive)
			throws Exception {
		// TODO Auto-generated method stub
		List<RaceModelValue> models = raceDao.getRaceModels(raceid, inactive);
		for (RaceModelValue raceModelValue : models) {
			List<RaceRound> raceRounds = this.raceDao.getRaceRounds(
					raceModelValue.getRaceid(), raceModelValue.getRound());
			if (raceRounds != null && !raceRounds.isEmpty()) {
				raceModelValue.setRaceRound(raceRounds.get(0));
			}
		}
		return models;
	}

	@Override
	public List<RaceDocumentValue> getRaceDocuments(String raceid,
			String docid, String userid, Integer round) throws Exception {
		// TODO Auto-generated method stub
		return raceDao.getRaceDocuments(raceid, docid, userid, round, null);
	}

	@Override
	public List<RaceScoreLogValue> getRaceScoreLog(String raceid, String docid,
			String userid, Integer round) throws Exception {
		// TODO Auto-generated method stub
		return raceDao.getRaceScoreLog(raceid, docid, userid, round);
	}

	@Override
	public void addRaceDocument(RaceDocumentValue raceDocumentValue)
			throws Exception {
		// TODO Auto-generated method stub
		// 1.校验活动是否有效
		List<RaceModelValue> racemodels = this.raceDao.getRaceModels(
				raceDocumentValue.getRaceid(), 1);
		if (racemodels == null || racemodels.isEmpty()) {
			throw new Exception("1");
		}
		List<RaceRound> rounds = this.raceDao.getRaceRounds(racemodels.get(0)
				.getRaceid(), racemodels.get(0).getRound());
		// 是否处于第一轮
		List<RaceUserModel> joinusers = this.raceDao.getRaceUserModels(
				racemodels.get(0).getRaceid(), racemodels.get(0).getRound(),
				raceDocumentValue.getJoinuserid());
		if (rounds.get(0).getRaceround().intValue() != 1 && joinusers.isEmpty()) {
			throw new Exception("5");// 不是第一轮切少资格用户则
		}
		// 2.校验是否已经够了限制数
		List<RaceDocumentValue> documents = this.raceDao.getRaceDocuments(
				raceDocumentValue.getRaceid(), null,
				raceDocumentValue.getJoinuserid(),
				racemodels.get(0).getRound(), null);
		if (documents.size() >= 1) {
			throw new Exception("2");
		}
		// 参与方式是否一致
		// if (documents != null && !documents.isEmpty()) {// 校验参与方式是否一致
		// for (RaceDocumentValue raceDocumentValuei : documents) {
		// if (raceDocumentValuei.getJointype().intValue() != raceDocumentValue
		// .getJointype().intValue()) {
		// throw new Exception("6");// 参与方式不一致
		// }
		// }
		// }
		// 3.是否已经参与了
		documents = this.raceDao.getRaceDocuments(
				raceDocumentValue.getRaceid(), raceDocumentValue.getDocid(),
				raceDocumentValue.getJoinuserid(),
				racemodels.get(0).getRound(), null);
		if (!documents.isEmpty()) {
			throw new Exception("3");
		}
		// 3.添加
		raceDocumentValue.setJoindate(new Date());
		raceDocumentValue.setFinalscore(new Double(0));
		raceDocumentValue.setNetaveragescore(new Double(0));
		raceDocumentValue.setNettotalscore(new Double(0));
		raceDocumentValue.setSpeaveragescore(new Double(0));
		raceDocumentValue.setSpetotalscore(new Double(0));
		raceDocumentValue.setRaceround(racemodels.get(0).getRound());
		raceDao.addRaceDocument(raceDocumentValue);
	}

	@Override
	public void delRaceDocument(String raceid, String docid, String userid)
			throws Exception {
		// TODO Auto-generated method stub
		List<RaceDocumentValue> racedocs = this.getRaceDocuments(raceid, docid,
				userid, 1);
		if (racedocs == null || racedocs.isEmpty()) {
			throw new Exception("1");
		}
		raceDao.delRaceDocument(raceid, docid, userid);
	}

	@Override
	public void addRaceScoreLogValue(RaceScoreLogValue raceScoreLogValue)
			throws Exception {
		// TODO Auto-generated method stub
		List<RaceModelValue> raceModelValues = this.raceDao.getRaceModels(
				raceScoreLogValue.getRaceid(), 1);
		RaceModelValue raceModelValue = raceModelValues.get(0);
		// 获取当前nvgvlga
		Date currentDate = new Date();
		List<RaceRound> raceRounds = new ArrayList<RaceRound>();
		raceRounds = this.raceDao.getRaceRounds(raceModelValue.getRaceid(),
				raceModelValue.getRound());
		// currentDate.setTime(System.currentTimeMillis());
		if (currentDate.compareTo(raceRounds.get(0).getBegindate()) < 0) {
			throw new Exception("5");
		} else if (currentDate.compareTo(raceRounds.get(0).getEnddate()) > 0) {
			throw new Exception("6");
		}
		raceScoreLogValue.setRound(raceRounds.get(0).getRaceround());
		Map<String, Object> queryUserParams = new HashMap<String, Object>();
		queryUserParams.put("userid", raceScoreLogValue.getScoreuserid());
		List<UserValue> userValues = this.userManagerDao
				.getUser(queryUserParams);
		if (userValues == null || userValues.isEmpty()) {
			throw new Exception("2");// 没有找到用户
		}
		UserValue userValue = userValues.get(0);
		if (userValue.getExpert() != null
				&& userValue.getExpert().intValue() == StaticConstants.SCORE_USER_TYPE2) {// 专家可以平三次
			raceScoreLogValue.setUsertype(StaticConstants.SCORE_USER_TYPE2);
		} else {
			raceScoreLogValue.setUsertype(StaticConstants.SCORE_USER_TYPE1);
		}
		if (raceScoreLogValue.getUsertype().intValue() == StaticConstants.SCORE_USER_TYPE1
				&& currentDate.compareTo(raceRounds.get(0).getNetuserendtime()) > 0) {
			throw new Exception("7");
		}

		// 获取参与比赛类型
		List<RaceDocumentValue> racedos = this.raceDao.getRaceDocuments(
				raceScoreLogValue.getRaceid(), raceScoreLogValue.getDocid(),
				null, raceModelValue.getRound(), null);
		RaceDocumentValue raceDocumentValue = racedos.get(0);
		// 1. 校验是否已经评过分
		List<RaceScoreLogValue> scorelogs = this.raceDao.getRaceScoreLog(
				raceScoreLogValue.getRaceid(), raceScoreLogValue.getDocid(),
				raceScoreLogValue.getScoreuserid(), raceModelValue.getRound());
		if (scorelogs != null && !scorelogs.isEmpty()) {
			throw new Exception("1");// 普通用户只能评一次分数
		} 
//		else if (raceModelValue.getRound().intValue() > 2
//				&& raceScoreLogValue.getUsertype().intValue() == StaticConstants.SCORE_USER_TYPE2
//				&& raceDocumentValue.getJointype().intValue() == StaticConstants.JOINRACE_TYPE2) {
//			throw new Exception("4");// 儿童作品专家用户只能平2次
//		}
		
		if (raceModelValue.getRound().intValue() == 3) {// 决赛专家现场评分
			if (raceScoreLogValue.getUsertype().intValue() == StaticConstants.SCORE_USER_TYPE2
					&& currentDate.compareTo(raceRounds.get(0)
							.getNetuserendtime()) < 0) {
				throw new Exception("8");
			}
		}
		if (raceModelValue.getRound().intValue() == 3
				&& raceDocumentValue.getJointype().intValue() == StaticConstants.JOINRACE_TYPE2
				&& currentDate.compareTo(raceRounds.get(0).getNetuserendtime()) < 0) {// 决赛专家现场评分
			throw new Exception("9");
		}
		raceScoreLogValue.setScoredate(new Date());
		raceDao.addRaceScoreLogValue(raceScoreLogValue);
	}

	@Override
	public List<RaceUser> getRaceIndexContent(String raceid, Integer jointype) throws Exception {
		// TODO Auto-generated method stub
		// 先获取所有参与比赛用户列表，在根据用户列表获取每个用户列表下面的参赛作品列表
		List<RaceModelValue> raceModelValues = this.raceDao.getRaceModels(
				raceid, 1);
		List<UserValue> userlist = new ArrayList<UserValue>();
		userlist = this.raceDao.getRaceUserList(raceModelValues.get(0)
				.getRaceid(), raceModelValues.get(0).getRound(), jointype);
		List<UserValue> maxScorelist = new ArrayList<UserValue>();
		maxScorelist = this.raceDao.getUserMaxScores(raceModelValues.get(0)
				.getRaceid(), raceModelValues.get(0).getRound(), jointype);
		List<RaceUser> raceUsers = new ArrayList<RaceUser>();
		List<RaceDocumentValue> documents = new ArrayList<RaceDocumentValue>();

		documents = this.raceDao.getRaceDocuments(raceModelValues.get(0)
				.getRaceid(), null, null, raceModelValues.get(0).getRound(),
				jointype);
		for (UserValue uservalue : userlist) {
			RaceUser raceUser = new RaceUser();
			raceUser.setUservalue(uservalue);
			UserValue maxScore = getMaxScore(uservalue, maxScorelist);
			if (maxScore != null) {
				raceUser.setMaxscore(maxScore.getMaxscore());
			} else {
				raceUser.setMaxscore(new Double(0));
			}
			RaceDocumentValue raceDocumentValue = getRaceDocument(
					uservalue.getUserid(), documents);
			/*
			 * List<RaceDocumentValue> documents =
			 * this.raceDao.getRaceDocuments(
			 * raceModelValues.get(0).getRaceid(), null, uservalue.getUserid(),
			 * raceModelValues.get(0).getRound(), jointype);
			 */
			List<RaceDocumentValue> racedoces = new ArrayList<RaceDocumentValue>();
			racedoces.add(raceDocumentValue);
			raceUser.setRaceDocumentValues(racedoces);
			raceUsers.add(raceUser);
		}
		RaceUser raceusers[] = new RaceUser[raceUsers.size()];
		raceUsers.toArray(raceusers);
		Arrays.sort(raceusers, new Comparator<RaceUser>() {
			@Override
			public int compare(RaceUser o1, RaceUser o2) {
				// TODO Auto-generated method stub
				return o2.getMaxscore().compareTo(o1.getMaxscore());
			}
		});
		return Arrays.asList(raceusers);
	}

	private RaceDocumentValue getRaceDocument(String userid,
			List<RaceDocumentValue> docs) {
		for (RaceDocumentValue raceDocumentValue : docs) {
			if (raceDocumentValue.getJoinuserid().equalsIgnoreCase(userid)) {
				return raceDocumentValue;
			}
		}
		return null;
	}

	private UserValue getMaxScore(UserValue uservalue,
			List<UserValue> maxScorelist) {
		UserValue returnUserValue = new UserValue();
		returnUserValue.setMaxscore(new Double(0));
		for (UserValue userValuei : maxScorelist) {
			if (userValuei.getUserid().equalsIgnoreCase(uservalue.getUserid())) {
				returnUserValue = userValuei;
			}
		}
		return returnUserValue;
	}

	@Override
	public List<RaceDocumentValue> getRaceSumValue(String raceid, String docid,
			Integer round) throws Exception {
		// TODO Auto-generated method stub
		return this.raceDao.getRaceSumValue(raceid, docid, round);
	}

	@Override
	public Map<String, Object> getRaceIndexCon(String raceid, String pageid,
			Integer jointype) throws Exception {
		// TODO Auto-generated method stub
		List<PageChannelRelationValue> relations = webPageManagerDao
				.getRelatedChannes("raceindex");
		Map<String, Object> indexPageContent = new HashMap<String, Object>();
		for (int i = 0; i < relations.size(); i++) {
			PageChannelRelationValue channel = relations.get(i);
			PaingModel<DocumentValue> paingModel = new PaingModel<DocumentValue>();
			paingModel.setDocchannel(channel.getChannelid());
			paingModel.setPageindex(1);
			paingModel.setPageindex(StaticConstants.INDEX_DOC_ROWCOUNT);
			paingModel.setStartrow(0);
			paingModel.setEndrow(channel.getChanneldoccount());// fetchcount
			paingModel.setDocstatus(2);
			paingModel.setInindex(1);
			List<DocumentValue> documents = this.webSiteVisitorDao
					.pagingquerydoc(paingModel);
			indexPageContent.put(channel.getVariablename(), documents);
		}
		return indexPageContent;
	}

	@Override
	public void switchUserToNextRound(String raceid, String userid,
			Integer jointype) throws Exception {
		// TODO Auto-generated method stub
		List<RaceModelValue> racemodels = this.raceDao.getRaceModels(null, 1);
		if (racemodels != null && !racemodels.isEmpty()) {
			RaceModelValue raceModelValue = racemodels.get(0);
			Integer nextround = raceModelValue.getRound() + 1;
			// 1.校验是否存在
			List<RaceDocumentValue> racedocs = this.raceDao.getRaceDocuments(
					raceModelValue.getRaceid(), null, userid,
					raceModelValue.getRound(), null);
			if (racedocs == null || racedocs.isEmpty()) {
				throw new Exception("4");// vrvldagan d vrvlqahv budugel baihu
											// ugei
			}
			// 2.校验是否已经转换
			List<RaceUserModel> raceusers = this.raceDao.getRaceUserModels(
					raceModelValue.getRaceid(), nextround, userid);
			if (raceusers != null && !raceusers.isEmpty()) {
				throw new Exception("5");// vrvldagan d vrvlqahv budugel baihu
			}
			// 3.是否在活动期间
			Date currentDate = new Date();
			currentDate.setTime(System.currentTimeMillis());
			if (currentDate.compareTo(raceModelValue.getBegindate()) < 0) {
				throw new Exception("6");
			} else if (currentDate.compareTo(raceModelValue.getEnddate()) > 0) {
				throw new Exception("7");
			}
			List<RaceRound> rounds = this.raceDao.getRaceRounds(
					raceModelValue.getRaceid(), raceModelValue.getRound());
			if (currentDate.compareTo(rounds.get(0).getEnddate()) < 0) {
				throw new Exception("9");
			}
			// 校验是否有nextround
			rounds = this.raceDao.getRaceRounds(raceModelValue.getRaceid(),
					nextround);
			if (rounds == null || rounds.isEmpty()) {
				throw new Exception("8"); //  
			}
			this.raceDao.addRaceUser(raceModelValue.getRaceid(), userid,
					nextround);

		} else {
			throw new Exception("3");
		}
	}

	@Override
	public PaingModel<RaceScoreLogValue> pagingqueryscorelog(String raceid,
			String docid, String index, Integer round) throws Exception {
		// TODO Auto-generated method stub
		List<RaceScoreLogValue> racescores = this.raceDao.pagingqueryscorelog(
				raceid, docid, round, Integer.parseInt(index));
		Integer rount = this.raceDao.racescorecount(raceid, docid, round);
		PaingModel<RaceScoreLogValue> paingModel = new PaingModel<RaceScoreLogValue>();
		paingModel.setModelList(racescores);
		paingModel.setRowcount("" + rount);
		//
		paingModel.setPagesize(30);
		int pageCount = rount / 30;
		if (rount % paingModel.getPagesize() > 0) {
			pageCount = pageCount + 1;
		}
		paingModel.setPageindex(Integer.parseInt(index));
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
		paingModel.setDocstatus(round);
		paingModel.setDocchannel(raceid);
		paingModel.setImggroupid(docid);
		return paingModel;
	}

	public List<ImgValue> getRaceImgList(String raceid, Integer count)
			throws Exception {
		return this.raceDao.getRaceImgList(raceid, count);
	}

	/**
	 * 
	 * @param channelid
	 * @return
	 * @throws Exception
	 */
	public List<DocumentValue> getvides(String channelid) throws Exception {
		//
		PaingModel<DocumentValue> paingModelVide0 = new PaingModel<DocumentValue>();
		paingModelVide0.setDocchannel(StaticConstants.bubaichanelid);
		paingModelVide0.setPageindex(1);
		paingModelVide0.setPageindex(StaticConstants.INDEX_DOC_ROWCOUNT);
		paingModelVide0.setStartrow(0);
		paingModelVide0.setEndrow(9);
		paingModelVide0.setDocstatus(2);
		paingModelVide0.setFlash(1);
		paingModelVide0.setInindex(1);
		List<DocumentValue> documents = this.webSiteVisitorDao
				.pagingquerydoc(paingModelVide0);
		return documents;
	}

	@Override
	public PaingModel<DocumentValue> pagingqueryracedoc(
			PaingModel<DocumentValue> paingModel) throws Exception {
		// TODO Auto-generated method stub
		paingModel.setStartrow((paingModel.getPageindex() - 1)
				* paingModel.getPagesize());
		paingModel.setEndrow(paingModel.getPagesize());
		List<RaceModelValue> raceModels = this.raceDao.getRaceModels(null, 1);
		paingModel.setRaceid(raceModels.get(0).getRaceid());
		paingModel.setRound(raceModels.get(0).getRound());
		List<DocumentValue> documents = this.raceDao.pagingquerydoc(paingModel);
		paingModel.setModelList(documents);
		Integer rowCount = this.raceDao.getRowCount(paingModel);
		paingModel.setRowcount("" + rowCount);
		int pageCount = rowCount / paingModel.getPagesize();
		if (rowCount % paingModel.getPagesize() > 0) {
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
	public List<RaceUser> getRaceStatus(String raceid, Integer jointype,
			Integer round) throws Exception {
		// TODO Auto-generated method stub
		List<RaceModelValue> raceModelValues = this.raceDao.getRaceModels(
				raceid, 1);
		List<UserValue> userlist = new ArrayList<UserValue>();
		userlist = this.raceDao.getRaceJoinUserList(raceModelValues.get(0)
				.getRaceid(), round, jointype);
		List<UserValue> maxScorelist = new ArrayList<UserValue>();
		maxScorelist = this.raceDao.getUserMaxScores(raceModelValues.get(0)
				.getRaceid(), round, jointype);
		List<RaceUser> raceUsers = new ArrayList<RaceUser>();
		List<RaceDocumentValue> documents = new ArrayList<RaceDocumentValue>();
		documents = this.raceDao.getRaceDocuments(raceModelValues.get(0)
				.getRaceid(), null, null, round,
				jointype);
		for (UserValue uservalue : userlist) {
			RaceUser raceUser = new RaceUser();
			raceUser.setUservalue(uservalue);
			UserValue maxScore = getMaxScore(uservalue, maxScorelist);
			if (maxScore != null) {
				raceUser.setMaxscore(maxScore.getMaxscore());
			} else {
				raceUser.setMaxscore(new Double(0));
			}
			RaceDocumentValue raceDocumentValue = getRaceDocument(
					uservalue.getUserid(), documents);
			/*
			 * List<RaceDocumentValue> documents =
			 * this.raceDao.getRaceDocuments(
			 * raceModelValues.get(0).getRaceid(), null, uservalue.getUserid(),
			 * raceModelValues.get(0).getRound(), jointype);
			 */
			List<RaceDocumentValue> racedoces = new ArrayList<RaceDocumentValue>();
			racedoces.add(raceDocumentValue);
			raceUser.setRaceDocumentValues(racedoces);
			raceUsers.add(raceUser);
		}
		RaceUser raceusers[] = new RaceUser[raceUsers.size()];
		raceUsers.toArray(raceusers);
		Arrays.sort(raceusers, new Comparator<RaceUser>() {
			@Override
			public int compare(RaceUser o1, RaceUser o2) {
				// TODO Auto-generated method stub
				return o2.getMaxscore().compareTo(o1.getMaxscore());
			}
		});
		return Arrays.asList(raceusers);
	}

}
