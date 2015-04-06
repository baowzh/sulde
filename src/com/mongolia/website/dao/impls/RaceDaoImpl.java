package com.mongolia.website.dao.impls;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.mongolia.website.dao.interfaces.RaceDao;
import com.mongolia.website.model.DocumentValue;
import com.mongolia.website.model.ImgValue;
import com.mongolia.website.model.PaingModel;
import com.mongolia.website.model.RaceDocumentValue;
import com.mongolia.website.model.RaceModelValue;
import com.mongolia.website.model.RaceRound;
import com.mongolia.website.model.RaceScoreLogValue;
import com.mongolia.website.model.RaceUserModel;
import com.mongolia.website.model.UserValue;

@Repository("raceDao")
public class RaceDaoImpl extends BaseDaoiBatis implements RaceDao {

	@Override
	public List<RaceModelValue> getRaceModels(String raceid, Integer inactive)
			throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> queryParams = new HashMap<String, Object>();
		queryParams.put("raceid", raceid);
		queryParams.put("inactive", inactive);
		return this.getSqlMapClientTemplate().queryForList("getRaceModel",
				queryParams);

	}

	@Override
	public List<RaceDocumentValue> getRaceDocuments(String raceid,
			String docid, String userid, Integer round,Integer jointype) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> queryParams = new HashMap<String, Object>();
		queryParams.put("raceid", raceid);
		queryParams.put("docid", docid);
		queryParams.put("joinuserid", userid);
		queryParams.put("round", round);
		queryParams.put("jointype", jointype);
		return this.getSqlMapClientTemplate().queryForList(
				"getRaceDocumentValue", queryParams);

	}

	@Override
	public List<RaceScoreLogValue> getRaceScoreLog(String raceid, String docid,
			String userid, Integer round) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> queryParams = new HashMap<String, Object>();
		queryParams.put("raceid", raceid);
		queryParams.put("docid", docid);
		queryParams.put("scoreuserid", userid);
		queryParams.put("round", round);
		return this.getSqlMapClientTemplate().queryForList(
				"getRaceScoreLogValue", queryParams);
	}

	@Override
	public void addRaceDocument(RaceDocumentValue raceDocumentValue)
			throws Exception {
		this.getSqlMapClientTemplate().insert("addRaceDocumentValue",
				raceDocumentValue);
		// TODO Auto-generated method stub

	}

	@Override
	public void delRaceDocument(String raceid, String docid, String userid)
			throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> delparams = new HashMap<String, Object>();
		delparams.put("raceid", raceid);
		delparams.put("docid", docid);
		delparams.put("joinuserid", userid);
		this.getSqlMapClientTemplate()
				.insert("delRaceDocumentValue", delparams);
	}

	@Override
	public void addRaceScoreLogValue(RaceScoreLogValue raceScoreLogValue)
			throws Exception {
		// TODO Auto-generated method stub
		this.getSqlMapClientTemplate().insert("addRaceScoreLogValue",
				raceScoreLogValue);
	}

	@Override
	public List<UserValue> getRaceUserList(String raceid, Integer round,Integer jointype)
			throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("raceid", raceid);
		queryMap.put("round", round);
		queryMap.put("jointype", jointype);
		return this.getSqlMapClientTemplate().queryForList("getRaceUserList",
				queryMap);
	}

	@Override
	public List<RaceDocumentValue> getRaceSumValue(String raceid, String docid,
			Integer round) throws Exception {
		// TODO Auto-generated method stub,
		Map<String, Object> getSumRaceValueParams = new HashMap<String, Object>();
		getSumRaceValueParams.put("raceid", raceid);
		getSumRaceValueParams.put("docid", docid);
		getSumRaceValueParams.put("round", round);
		return this.getSqlMapClientTemplate().queryForList("getRaceSumValue",
				getSumRaceValueParams);
	}

	/**
	 * 
	 * @param raceid
	 * @param round
	 * @return
	 * @throws Exception
	 */
	public List<UserValue> getUserMaxScores(String raceid, Integer round,Integer jointype)
			throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("raceid", raceid);
		queryMap.put("round", round);
		queryMap.put("jointype", jointype);
		return this.getSqlMapClientTemplate().queryForList("getUserMaxScores",
				queryMap);
	}

	@Override
	public List<RaceRound> getRaceRounds(String raceid, Integer round)
			throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> getRaceRoundParams = new HashMap<String, Object>();
		getRaceRoundParams.put("raceid", raceid);
		getRaceRoundParams.put("raceround", round);
		return this.getSqlMapClientTemplate().queryForList("getRaceRounds",
				getRaceRoundParams);
	}

	@Override
	public List<RaceUserModel> getRaceUserModels(String raceid, Integer round,
			String userid) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> getRaceRoundParams = new HashMap<String, Object>();
		getRaceRoundParams.put("raceid", raceid);
		getRaceRoundParams.put("round", round);
		getRaceRoundParams.put("userid", userid);
		return this.getSqlMapClientTemplate().queryForList("getRaceUserModels",
				getRaceRoundParams);
	}

	@Override
	public void addRaceUser(String raceid, String userid, Integer round)
			throws Exception {
		// TODO Auto-generated method stub
		RaceUserModel raceUserModel = new RaceUserModel();
		raceUserModel.setRaceid(raceid);
		raceUserModel.setRound(round);
		raceUserModel.setUserid(userid);
		this.getSqlMapClientTemplate()
				.insert("addRaceUserModel", raceUserModel);
	}

	@Override
	public List<RaceScoreLogValue> pagingqueryscorelog(String raceid,
			String docid, Integer round, Integer index) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("raceid", raceid);
		params.put("docid", docid);
		params.put("round", round);
		Integer startindex = (index - 1) * 30;
		params.put("start", startindex);
		params.put("count", 30);
		return this.getSqlMapClientTemplate().queryForList(
				"pagingqueryscorelog", params);
	}

	@Override
	public List<ImgValue> getRaceImgList(String raceid, Integer count)
			throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> queryImgParams = new HashMap<String, Object>();
		queryImgParams.put("raceid", raceid);
		queryImgParams.put("count", count);
		return this.getSqlMapClientTemplate().queryForList("getRaceImgList",
				queryImgParams);
	}

	@Override
	public Integer racescorecount(String raceid, String docid, Integer round)
			throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("raceid", raceid);
		params.put("docid", docid);
		params.put("round", round);
		return (Integer) this.getSqlMapClientTemplate().queryForObject(
				"racescorecount", params);
	}

	@Override
	public List<DocumentValue> pagingquerydoc(
			PaingModel<DocumentValue> paingModel) throws Exception {
		// TODO Auto-generated method stub
		return this.getSqlMapClientTemplate().queryForList("pagingqueryracedoc", paingModel);
	}

	@Override
	public Integer getRowCount(PaingModel<DocumentValue> paingModel)
			throws Exception {
		// TODO Auto-generated method stub
		return (Integer)this.getSqlMapClientTemplate().queryForObject("getRaceDocRowCount", paingModel);
	}

	@Override
	public List<UserValue> getRaceChildList(String raceid, Integer round,
			Integer jointype) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("raceid", raceid);
		queryMap.put("round", round);
		queryMap.put("jointype", jointype);
		return this.getSqlMapClientTemplate().queryForList("getRaceChildList", queryMap);
	}

	@Override
	public List<UserValue> getRaceJoinUserList(String raceid, Integer round,
			Integer jointype) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("raceid", raceid);
		queryMap.put("round", round);
		queryMap.put("jointype", jointype);
		return this.getSqlMapClientTemplate().queryForList("getRaceJoinUserList",
				queryMap);
	}
	
	

}
