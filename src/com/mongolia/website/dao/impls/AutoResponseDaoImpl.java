package com.mongolia.website.dao.impls;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
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

import com.mongolia.website.dao.interfaces.AutoResponseDao;
import com.mongolia.website.model.AutoResponse;
import com.mongolia.website.model.PagingAutoResModel;

@Repository("autoResponseDaoImpl")
public class AutoResponseDaoImpl implements AutoResponseDao {

	@Resource(name = "jdbcTemplate")
	private JdbcTemplate jdbcTemplate;

	@Override
	public void delete(AutoResponse entity) throws Exception {
		// TODO Auto-generated method stub
		String deleteSql = "delete from wechatautoresponse  where id='"
				+ entity.getId() + "'";
		this.jdbcTemplate.execute(deleteSql);
	}

	@Override
	public List<AutoResponse> getAutoResponses(Map<String, Object> params)
			throws Exception {
		// TODO Auto-generated method stub
		RowMapper<AutoResponse> rm = ParameterizedBeanPropertyRowMapper
				.newInstance(AutoResponse.class);
		String sql = "select * from  wechatautoresponse where 1=1";
		if (params.get("id") != null) {
			// id
			sql = sql + " and id='" + params.get("id") + "'";
		}
		if (params.get("accountId") != null) {
			// id
			sql = sql + " and accountId='" + params.get("accountId") + "'";
		}

		List<AutoResponse> subscribes = this.jdbcTemplate.query(sql, rm);
		return subscribes;
	}

	@Override
	public void save(final AutoResponse entity) throws Exception {
		// TODO Auto-generated method stub
		this.jdbcTemplate.execute(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection conn)
					throws SQLException {
				return conn
						.prepareStatement(" insert into wechatautoresponse (ID  ,ADDTIME, KEYWORD  , MSGTYPE , RESCONTENT,TEMPLATENAME,ACCOUNTID )values (?,?,?,?,?,?,?)");
			}
		}, new PreparedStatementCallback<Integer>() {
			@Override
			public Integer doInPreparedStatement(PreparedStatement ps)
					throws SQLException, DataAccessException {
				// TODO Auto-generated method stub
				ps.setString(1, entity.getId());
				ps.setString(2, entity.getAddtime());
				ps.setString(3, entity.getKeyword());
				ps.setString(4, entity.getMsgtype());
				ps.setString(5, entity.getRescontent());
				ps.setString(6, entity.getTemplatename());
				ps.setString(7, entity.getAccountid());
				ps.execute();
				return 0;
			}
		});
	}

	@Override
	public void update(final AutoResponse entity) throws Exception {
		// TODO Auto-generated method stub
		this.jdbcTemplate.execute(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection conn)
					throws SQLException {
				return conn
						.prepareStatement(" update  wechatautoresponse set ADDTIME=?, KEYWORD=?  , MSGTYPE=? , RESCONTENT=?,TEMPLATENAME=?,ACCOUNTID=?  where id=? ");
			}
		}, new PreparedStatementCallback<Integer>() {
			@Override
			public Integer doInPreparedStatement(PreparedStatement ps)
					throws SQLException, DataAccessException {
				// TODO Auto-generated method stub
				ps.setString(7, entity.getId());
				ps.setString(1, entity.getAddtime());
				ps.setString(2, entity.getKeyword());
				ps.setString(3, entity.getMsgtype());
				ps.setString(4, entity.getRescontent());
				ps.setString(5, entity.getTemplatename());
				ps.setString(6, entity.getAccountid());
				return ps.executeUpdate();
			}
		});

	}

	@Override
	public boolean checkexists(String id) throws Exception {
		// TODO Auto-generated method stub
		RowMapper<AutoResponse> rm = ParameterizedBeanPropertyRowMapper
				.newInstance(AutoResponse.class);
		String sql = "select * from  wechatautoresponse where  id='" + id + "'";
		List<AutoResponse> subscribes = this.jdbcTemplate.query(sql, rm);
		if (subscribes == null || subscribes.isEmpty()) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public List<AutoResponse> pagingQueryAutoResponse(
			PagingAutoResModel paingModel) throws Exception {
		// TODO Auto-generated method stub
		List<Object> args = new ArrayList<Object>();
		String querySql = "select * from wechatautoresponse where accountid=?  ";
		args.add(paingModel.getAccountid());
		if (paingModel.getBegindate() != null) {
			querySql = querySql + " and addtime>=?";
			args.add(paingModel.getBegindate());
		}
		if (paingModel.getEndate() != null) {
			querySql = querySql + " and addtime<=?";
			args.add(paingModel.getEndate());
		}
		querySql = querySql + " order by addtime desc limit ?, ? ";
		args.add((paingModel.getStartrow() - 1) * 10);
		args.add(10);
		RowMapper<AutoResponse> rm = ParameterizedBeanPropertyRowMapper
				.newInstance(AutoResponse.class);
		return this.jdbcTemplate.query(querySql, rm, args.toArray());
	}

	@Override
	public Integer getAutoResponseCount(PagingAutoResModel paingModel)
			throws Exception {
		// TODO Auto-generated method stub
		List<Object> args = new ArrayList<Object>();
		String querySql = "select count(1) from wechatautoresponse where accountid=?  ";
		args.add(paingModel.getAccountid());
		if (paingModel.getBegindate() != null) {
			querySql = querySql + " and addtime>=?";
			args.add(paingModel.getBegindate());
		}
		if (paingModel.getEndate() != null) {
			querySql = querySql + " and addtime<=?";
			args.add(paingModel.getEndate());
		}
		return this.jdbcTemplate.queryForObject(querySql,
				Integer.class, args);
	}

}
