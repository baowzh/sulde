package com.mongolia.website.dao.interfaces;

import java.util.List;

import com.mongolia.website.model.DocumentValue;
import com.mongolia.website.model.ImgValue;
import com.mongolia.website.model.PaingModel;
import com.mongolia.website.model.RaceDocumentValue;
import com.mongolia.website.model.RaceModelValue;
import com.mongolia.website.model.RaceRound;
import com.mongolia.website.model.RaceScoreLogValue;
import com.mongolia.website.model.RaceUserModel;
import com.mongolia.website.model.UserValue;

public interface RaceDao {
	/**
	 * 
	 * @param raceid
	 * @param inactive
	 * @return
	 * @throws Exception
	 */
	public List<RaceModelValue> getRaceModels(String raceid, Integer inactive)
			throws Exception;

	/**
	 * 
	 * @param raceid
	 * @param docid
	 * @param userid
	 * @return
	 * @throws Exception
	 */
	public List<RaceDocumentValue> getRaceDocuments(String raceid,
			String docid, String userid, Integer round,Integer jointype) throws Exception;

	/**
	 * 
	 * @param raceid
	 * @param docid
	 * @param userid
	 * @return
	 * @throws Exception
	 */
	public List<RaceScoreLogValue> getRaceScoreLog(String raceid, String docid,
			String userid, Integer round) throws Exception;

	/**
	 * 
	 * @param raceDocumentValue
	 * @throws Exception
	 */
	public void addRaceDocument(RaceDocumentValue raceDocumentValue)
			throws Exception;

	/**
	 * 
	 * @param raceid
	 * @param docid
	 * @throws Exception
	 */
	public void delRaceDocument(String raceid, String docid, String userid)
			throws Exception;

	/**
	 * 
	 * @param raceScoreLogValue
	 * @throws Exception
	 */
	public void addRaceScoreLogValue(RaceScoreLogValue raceScoreLogValue)
			throws Exception;

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<UserValue> getRaceUserList(String raceid, Integer round,Integer jointype)
			throws Exception;
	
	public List<UserValue> getRaceChildList(String raceid, Integer round,Integer jointype)
			throws Exception;
	
	/**
	 * 
	 * @param raceid
	 * @param docid
	 * @return
	 * @throws Exception
	 */
	public List<RaceDocumentValue> getRaceSumValue(String raceid, String docid,
			Integer round) throws Exception;

	/**
	 * 
	 * @param raceid
	 * @param round
	 * @return
	 * @throws Exception
	 */
	public List<UserValue> getUserMaxScores(String raceid, Integer round,Integer jointype)
			throws Exception;

	/**
	 * 
	 * @param raceid
	 * @param round
	 * @return
	 * @throws Exception
	 */
	public List<RaceRound> getRaceRounds(String raceid, Integer round)
			throws Exception;

	/**
	 * 
	 * @param raceid
	 * @param round
	 * @param userid
	 * @return
	 * @throws Exception
	 */
	public List<RaceUserModel> getRaceUserModels(String raceid, Integer round,
			String userid) throws Exception;

	/**
	 * 
	 * @param raceid
	 * @param round
	 * @param userid
	 * @throws Exception
	 */
	public void addRaceUser(String raceid, String userid, Integer round)
			throws Exception;

	/**
	 * 
	 * @param raceid
	 * @param docid
	 * @param index
	 * @return
	 * @throws Exception
	 */
	public List<RaceScoreLogValue> pagingqueryscorelog(String raceid,
			String docid, Integer round, Integer index) throws Exception;

	/**
	 * 
	 * @param raceid
	 * @param docid
	 * @param index
	 * @return
	 * @throws Exception
	 */
	public Integer racescorecount(String raceid, String docid, Integer round)
			throws Exception;

	/**
	 * 
	 * @param raceid
	 * @param count
	 * @return
	 * @throws Exception
	 */
	public List<ImgValue> getRaceImgList(String raceid, Integer count)
			throws Exception;

	public List<DocumentValue> pagingquerydoc(
			PaingModel<DocumentValue> paingModel) throws Exception;

	public Integer getRowCount(PaingModel<DocumentValue> paingModel)
			throws Exception;
	/**
	 * 
	 * @param raceid
	 * @param round
	 * @param jointype
	 * @return
	 * @throws Exception
	 */
	public List<UserValue> getRaceJoinUserList(String raceid, Integer round,Integer jointype)
			throws Exception;
	

}
