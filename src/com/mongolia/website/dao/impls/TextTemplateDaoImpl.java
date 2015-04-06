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

import com.mongolia.website.dao.interfaces.TextTemplateDao;
import com.mongolia.website.model.TextTemplate;
@Repository("textTemplateDaoImpl")
public class TextTemplateDaoImpl implements TextTemplateDao {

	@Resource(name = "jdbcTemplate")
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public void delete(final TextTemplate entity) {
		// TODO Auto-generated method stub
		this.jdbcTemplate.execute("delete from WECHATTEXTTEMPLATE where id='"
				+ entity.getId() + "'");

	}

	@Override
	public void save(final TextTemplate entity) {
		// TODO Auto-generated method stub
		this.jdbcTemplate.execute(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection conn)
					throws SQLException {
				return conn
						.prepareStatement(" insert into  WECHATTEXTTEMPLATE( ID,ADDTIME  , CONTENT , TEMPLATENAME ,ACCOUNTID )values(?,?,?,?,?) ");
			}
		}, new PreparedStatementCallback<Integer>() {
			@Override
			public Integer doInPreparedStatement(PreparedStatement ps)
					throws SQLException, DataAccessException {
				// TODO Auto-generated method stub
				ps.setString(1, entity.getId());
				ps.setString(2, entity.getAddtime());
				ps.setString(3, entity.getContent());
				ps.setString(4, entity.getTemplatename());
				ps.setString(5, entity.getAccountid());
				ps.execute();
				return 0;
			}
		});
	}

	@Override
	public void Update(TextTemplate entity) {
		// TODO Auto-generated method stub
		List<Object> args = new ArrayList<Object>();
		String updSql = "update  wechattexttemplate set id=? ,";
		args.add(entity.getId());
		if (entity.getTemplatename() != null
				&& !entity.getTemplatename().equalsIgnoreCase("")) {
			updSql = updSql + " TEMPLATENAME =?,";
			args.add(entity.getTemplatename());
		}
		if (entity.getContent() != null
				&& !entity.getContent().equalsIgnoreCase("")) {
			updSql = updSql + " CONTENT =?,";
			args.add(entity.getContent());
		}
		updSql = updSql.substring(0, updSql.length() - 1);
		updSql = updSql + " where id=?";
		args.add(entity.getId());
		
		this.jdbcTemplate.update(updSql, args.toArray());
	}

	@Override
	public List<TextTemplate> queryTextTemplate(Map<String, Object> params)
			throws Exception {
		// TODO Auto-generated method stub
		RowMapper<TextTemplate> rm = ParameterizedBeanPropertyRowMapper
				.newInstance(TextTemplate.class);
		String querySql = "select * from wechattexttemplate where 1=1 ";
		if (params.get("id") != null) {
			querySql = querySql + " and id='" + params.get("id") + "'";
		}
		if (params.get("templatename") != null) {
			querySql = querySql + " and templatename='"
					+ params.get("templatename") + "'";
		}
		return jdbcTemplate.query(querySql, rm);
	}

}
