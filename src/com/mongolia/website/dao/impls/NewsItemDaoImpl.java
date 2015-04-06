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

import com.mongolia.website.dao.interfaces.NewsItemDao;
import com.mongolia.website.model.NewsItem;

@Repository("newsItemDaoImpl")
public class NewsItemDaoImpl implements NewsItemDao {
	@Resource(name = "jdbcTemplate")
	private JdbcTemplate jdbcTemplate;

	@Override
	public void delete(NewsItem entity) {
		// TODO Auto-generated method stub
		String delSql = "delete from  WECHATNEWSITEM where id='"
				+ entity.getId() + "'";

		this.jdbcTemplate.execute(delSql);
	}

	@Override
	public void save(final NewsItem entity) {
		// TODO Auto-generated method stub
		this.jdbcTemplate.execute(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection conn)
					throws SQLException {
				return conn
						.prepareStatement("insert into WECHATNEWSITEM(ID ,AUTHOR,CONTENT,DESCRIPTION,IMAGEPATH,ORDERS,TITLE ,TEMPLATEID , ACCOUNTID )values(?,?,?,?,?,?,?,?,?)");
			}
		}, new PreparedStatementCallback<Integer>() {
			@Override
			public Integer doInPreparedStatement(PreparedStatement ps)
					throws SQLException, DataAccessException {
				// TODO Auto-generated method stub
				ps.setString(1, entity.getId());
				ps.setString(2, entity.getAuthor());
				ps.setString(3, entity.getContent());
				ps.setString(4, entity.getDescription());
				ps.setString(5, entity.getImagePath());
				ps.setString(6, entity.getOrders());
				ps.setString(7, entity.getTitle());
				// ps.setString(8, entity.getNewsTemplate().getId());
				ps.setString(9, entity.getAccountId());
				ps.execute();
				return 0;
			}
		});

	}

	@Override
	public void update(NewsItem entity) {
		// TODO Auto-generated method stub
		List<Object> params = new ArrayList<Object>();
		String sql = " update WECHATNEWSITEM set id=?,";
		params.add(entity.getId());
		if (entity.getAuthor() != null) {
			sql = sql + "AUTHOR=?,";
			params.add(entity.getAuthor());
		}
		if (entity.getContent() != null) {
			sql = sql + "CONTENT=?,";
			params.add(entity.getContent());
		}
		if (entity.getDescription() != null) {
			sql = sql + "DESCRIPTION=?,";
			params.add(entity.getDescription());
		}
		if (entity.getImagePath() != null) {
			sql = sql + "IMAGEPATH=?,";
			params.add(entity.getImagePath());
		}
		if (entity.getTitle() != null) {
			sql = sql + "TITLE=?,";
			params.add(entity.getTitle());
		}
		if (entity.getAccountId() != null) {
			sql = sql + "ACCOUNTID=?,";
			params.add(entity.getAccountId());
		}
		if (entity.getOrders() != null) {
			sql = sql + "ORDERS=?,";
			params.add(entity.getOrders());
		}
		sql = sql.substring(0, sql.length() - 1);
		sql = sql + " where id=?";
		params.add(entity.getId());

		this.jdbcTemplate.update(sql, params.toArray());
	}

	@Override
	public List<NewsItem> queryNewsItems(Map<String, Object> params)
			throws Exception {
		RowMapper<NewsItem> rm = ParameterizedBeanPropertyRowMapper
				.newInstance(NewsItem.class);
		String sql = "select * from  WECHATNEWSITEM where 1=1";
		if (params.get("templateid") != null) {
			sql = sql + " and templateid='" + params.get("templateid") + "'";
		}
		if (params.get("id") != null) {
			sql = sql + " and id='" + params.get("id") + "'";
		}
		if (params.get("orders") != null) {
			sql = sql + " and orders='" + params.get("orders") + "'";
		}
		if (params.get("largorder") != null) {
			sql = sql + " and orders>" + params.get("largorder") + "";
		}
		if (params.get("accountId") != null) {
			sql = sql + " and accountId='" + params.get("accountId") + "'";
		}
		if (params.get("templatename") != null) {
			sql = sql
					+ " and exists( select 1 from   wechatnewstemplate where wechatnewstemplate.TEMPLATENAME='"
					+ params.get("templatename")
					+ "'  and  wechatnewstemplate.id=WECHATNEWSITEM.TEMPLATEID  ) ";
		}

		List<NewsItem> results = this.jdbcTemplate.query(sql, rm);
		return results;
	}

}
