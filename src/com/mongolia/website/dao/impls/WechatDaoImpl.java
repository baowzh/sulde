package com.mongolia.website.dao.impls;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.mongolia.website.dao.interfaces.WechatDao;
import com.mongolia.website.model.AttentionValue;
import com.mongolia.website.model.NewsInfo;
import com.mongolia.website.model.Wechatmsg;
import com.mongolia.website.model.Wechatmsgconfig;
@Repository("wechatDaoImpl")
public class WechatDaoImpl implements WechatDao {

	@Resource(name = "jdbcTemplate")
	private JdbcTemplate jdbcTemplate;

	@Override
	public void saveWechatMsg(final Wechatmsg wechatmsg) throws Exception {
		// TODO Auto-generated method stub
		jdbcTemplate.execute(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection conn)
					throws SQLException {
				return conn
						.prepareStatement("inser into wechatmsg(id,create_date,fromusername	,tousername	,msgflag,msgid	,platform,	receivemsg	,			receivemsgtype	,receivetime	,		sendmsg	,			sendmsgtype	,		sendtime	,		status	,			u_id)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)	");
			}
		}, new PreparedStatementCallback<Integer>() {
			@Override
			public Integer doInPreparedStatement(PreparedStatement ps)
					throws SQLException, DataAccessException {
				// TODO Auto-generated method stub
				ps.setInt(1, wechatmsg.getId());
				ps.setTimestamp(2, wechatmsg.getCreatetime());
				ps.setString(3, wechatmsg.getFromusername());
				ps.setString(4, wechatmsg.getTousername());
				ps.setString(5, wechatmsg.getMsgflag());
				ps.setString(6, wechatmsg.getMsgid());
				ps.setString(7, wechatmsg.getPlatform());
				ps.setString(8, wechatmsg.getReceivemsg());
				ps.setString(9, wechatmsg.getReceivemsgtype());
				ps.setTimestamp(10, wechatmsg.getReceivetime());
				ps.setString(11, wechatmsg.getSendmsg());
				ps.setString(12, wechatmsg.getSendmsgtype());
				ps.setTimestamp(13, wechatmsg.getSendtime());
				ps.setString(14, wechatmsg.getStatus());
				ps.setString(15, wechatmsg.getUid());
				ps.execute();
				return 0;
			}
		});
	}

	@Override
	public Wechatmsgconfig queryWechatMsgConfig(String recMsg,
			String recMsgType, String uid, String platform) {
		// TODO Auto-generated method stub
		List<Object> aras = new ArrayList<Object>();
		aras.add(recMsgType);
		aras.add(recMsg);
		aras.add(uid);
		aras.add(platform);
		int argtypes[] = { Types.VARCHAR, Types.VARCHAR, Types.INTEGER,
				Types.VARCHAR };
		return this.jdbcTemplate
				.queryForObject(
						"select * from wechatmsgconfig  a where a.receivemsgtype=? and a.receivemsg=? and a.UId=? and a.platform=?",
						aras.toArray(), argtypes, Wechatmsgconfig.class);
	}

	@Override
	public List<NewsInfo> queryMsgConfigNews(String groupid) {
		// TODO Auto-generated method stub
		List<Object> aras = new ArrayList<Object>();
		aras.add(groupid);
		int argtypes[] = { Types.VARCHAR };
		return this.jdbcTemplate
				.queryForList(
						"select * from from wechatnewsconfig a where a.groupid=? order by order_No",
						aras.toArray(), argtypes, NewsInfo.class);
	}

	@Override
	public void addAttentionUserInfo(final AttentionValue attentionValue)
			throws Exception {
		// TODO Auto-generated method stub
		// 先检验是否存在
		try {
			List<Object> params = new ArrayList<Object>();
			params.add(attentionValue.getOpenid());
			List<Map<String, Object>> result = this.jdbcTemplate.queryForList(
					"select * from wechatattentionuserinfo where openid=? ",
					params.toArray());
			if (result == null || result.isEmpty()) {

				this.jdbcTemplate.execute(new PreparedStatementCreator() {
					@Override
					public PreparedStatement createPreparedStatement(
							Connection conn) throws SQLException {
						return conn
								.prepareStatement("insert into wechatattentionuserinfo(accountid, openid,attentiontime, nickname, sex, language, city,province,country, headimgurl, remark,status, unattentiontime) values(?,?,?,?,?,?,?,?,?,?,?,?,?)");
					}
				}, new PreparedStatementCallback<Integer>() {

					@Override
					public Integer doInPreparedStatement(PreparedStatement ps)
							throws SQLException, DataAccessException {
						// TODO Auto-generated method stub
						ps.setString(1, attentionValue.getAccountid());
						ps.setString(2, attentionValue.getOpenid());
						ps.setTime(3, new java.sql.Time(attentionValue
								.getAttentiontime().getTime()));
						ps.setString(4, attentionValue.getNickname());
						ps.setInt(5, 1);
						ps.setString(6, attentionValue.getLanguage());
						ps.setString(7, attentionValue.getCity());
						ps.setString(8, attentionValue.getProvince());
						ps.setString(9, attentionValue.getCountry());
						ps.setString(10, attentionValue.getHeadimgurl());
						ps.setString(11, attentionValue.getRemark());
						ps.setInt(12, attentionValue.getStatus());
						if (attentionValue.getUnattentiontime() != null) {
							ps.setTime(13, new java.sql.Time(attentionValue
									.getUnattentiontime().getTime()));
						} else {
							ps.setTime(13, null);
						}
						ps.execute();
						return 0;
					}

				});
				// this.jdbcTemplate
				// .execute(
				// "insert into wechatattentionuserinfo(accountid, openid,attentiontime, nickname, sex, language, city,province,country, headimgurl, remark,status, unattentiontime) values(?,?,?,?,?,?,?,?,?,?,?,?,?)",
				// );
			} else {
				List<Object> updParams = new ArrayList<Object>();
				updParams.add(attentionValue.getStatus());
				updParams.add(null);
				updParams.add(new Date());
				updParams.add(attentionValue.getOpenid());

				this.jdbcTemplate
						.update("update wechatattentionuserinfo set status=?,unattentiontime=?,attentiontime=? where openid=?",
								updParams.toArray());
			}
		} catch (Exception ex) {

		}

	}

	@Override
	public void deleteAttentionUserInfo(String oepnid) throws Exception {
		// TODO Auto-generated method stub
		List<Object> updParams = new ArrayList<Object>();
		updParams.add(0);
		updParams.add(new Date());
		updParams.add(oepnid);
		this.jdbcTemplate
				.update("update wechatattentionuserinfo set status=?,unattentiontime=? where openid=?",
						updParams.toArray());

	}

	@Override
	public AttentionValue getAttentionUserInfo(String openid) {
		// TODO Auto-generated method stub
		List<Object> args = new ArrayList<Object>();
		RowMapper<AttentionValue> rm = ParameterizedBeanPropertyRowMapper
				.newInstance(AttentionValue.class);
		List<AttentionValue> attentions = this.jdbcTemplate.query(
				"select * from wechatattentionuserinfo where openid='" + openid
						+ "'", rm);
		if (attentions != null && !attentions.isEmpty()) {
			return attentions.get(0);
		} else {
			return null;
		}
	}

}
