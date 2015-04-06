package com.mongolia.website.dao.impls;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
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

import com.mongolia.website.dao.interfaces.WeixinAccountDao;
import com.mongolia.website.model.AccessTokenYw;
import com.mongolia.website.model.WechatAccountEntity;

@Repository("weixinAccountDaoImpl")
public class WeixinAccountDaoImpl  implements
		WeixinAccountDao {
	@Resource(name = "jdbcTemplate")
	private JdbcTemplate jdbcTemplate;
	

	@Override
	public List<WechatAccountEntity> getAccountByUserName(String username) {

		RowMapper<WechatAccountEntity> rm = ParameterizedBeanPropertyRowMapper
				.newInstance(WechatAccountEntity.class);
		
		List<WechatAccountEntity> wechataccount = jdbcTemplate
				.query("select * from WECHATACCOUNT where USERNAME='"
						+ username + "'", rm);
		return wechataccount;
	}

	@Override
	public WechatAccountEntity getAccountById(String id) {

		RowMapper<WechatAccountEntity> rm = ParameterizedBeanPropertyRowMapper
				.newInstance(WechatAccountEntity.class);
		List<WechatAccountEntity> entityes = jdbcTemplate.query(
				"select * from WECHATACCOUNT where id='" + id + "'", rm);
		if (entityes != null && !entityes.isEmpty()) {
			return entityes.get(0);
		} else {
			return null; // 应该抛出异常
		}
	}

	@Override
	public void saveOrUpdate(final WechatAccountEntity weixinAccountEntity) {
		// TODO Auto-generated method stub
		if (checkExist(weixinAccountEntity)) {
			List<Object> prams = new ArrayList<Object>();
			String updSql = "update wechataccount set  id=?,";
			prams.add(weixinAccountEntity.getId());
			if (weixinAccountEntity.getAccountaccesstoken() != null) {
				updSql = updSql + " accountaccesstoken=?,";
				prams.add(weixinAccountEntity.getAccountaccesstoken());
			}
			if (weixinAccountEntity.getAccountname() != null) {
				updSql = updSql + " accountname=?,";
				prams.add(weixinAccountEntity.getAccountname());
			}
			if (weixinAccountEntity.getAccounttoken() != null) {
				updSql = updSql + " accounttoken=?,";
				prams.add(weixinAccountEntity.getAccounttoken());
			}
			if (weixinAccountEntity.getAccountnumber() != null) {
				updSql = updSql + " accountnumber=?,";
				prams.add(weixinAccountEntity.getAccountnumber());
			}
			if (weixinAccountEntity.getAccounttype() != null) {
				updSql = updSql + " accounttype=?,";
				prams.add(weixinAccountEntity.getAccounttype());
			}
			if (weixinAccountEntity.getAccountemail() != null) {
				updSql = updSql + " accountemail=?,";
				prams.add(weixinAccountEntity.getAccountemail());
			}
			if (weixinAccountEntity.getAccountdesc() != null) {
				updSql = updSql + " accountdesc=?,";
				prams.add(weixinAccountEntity.getAccountdesc());
			}
			if (weixinAccountEntity.getAccountappid() != null) {
				updSql = updSql + " accountappid=?,";
				prams.add(weixinAccountEntity.getAccountappid());
			}
			if (weixinAccountEntity.getAccountappsecret() != null) {
				updSql = updSql + " accountappsecret=?,";
				prams.add(weixinAccountEntity.getAccountappsecret());
			}
			if (weixinAccountEntity.getAddtoekntime() != null) {
				updSql = updSql + " ADDTOEKNTIME=?,";
				prams.add(weixinAccountEntity.getAddtoekntime());
			}
			if (weixinAccountEntity.getUsername() != null) {
				updSql = updSql + " USERNAME=?,";
				prams.add(weixinAccountEntity.getUsername());
			}
			if (weixinAccountEntity.getJsaccounttoken() != null) {
				updSql = updSql + " jsaccounttoken=?,";
				prams.add(weixinAccountEntity.getJsaccounttoken());
			}
			updSql = updSql.substring(0, updSql.length() - 1);
			updSql = updSql + " where id=?";
			prams.add(weixinAccountEntity.getId());
		
			this.jdbcTemplate.update(updSql, prams.toArray());
			/*
			 * this.jdbcTemplate.execute(new PreparedStatementCreator() {
			 * 
			 * @Override public PreparedStatement
			 * createPreparedStatement(Connection conn) throws SQLException {
			 * return conn .prepareStatement(
			 * "  update  wechataccount  set accountname =?, accounttoken=? , accountnumber=? , accounttype=? , accountemail=? , accountdesc=? , accountaccesstoken=?, accountappid=? , accountappsecret=? , ADDTOEKNTIME=? ,  USERNAME=?  where id=? "
			 * ); } }, new PreparedStatementCallback<Integer>() {
			 * 
			 * @Override public Integer doInPreparedStatement(PreparedStatement
			 * ps) throws SQLException, DataAccessException { // TODO
			 * Auto-generated method stub
			 * 
			 * ps.setString(1, weixinAccountEntity.getAccountname());
			 * ps.setString(2, weixinAccountEntity.getAccounttoken());
			 * ps.setString(3, weixinAccountEntity.getAccountnumber());
			 * ps.setString(4, weixinAccountEntity.getAccounttype());
			 * ps.setString(5, weixinAccountEntity.getAccountemail());
			 * ps.setString(6, weixinAccountEntity.getAccountdesc());
			 * ps.setString(7, weixinAccountEntity.getAccountaccesstoken());
			 * ps.setString(8, weixinAccountEntity.getAccountappid());
			 * ps.setString(9, weixinAccountEntity.getAccountappsecret());
			 * ps.setDate(10, new java.sql.Date(weixinAccountEntity
			 * .getAddtoekntime().getTime())); ps.setString(11,
			 * weixinAccountEntity.getUsername()); ps.setString(12,
			 * weixinAccountEntity.getId()); return ps.executeUpdate();
			 * 
			 * } });
			 */
		} else {
			this.jdbcTemplate.execute(new PreparedStatementCreator() {
				@Override
				public PreparedStatement createPreparedStatement(Connection conn)
						throws SQLException {
					return conn
							.prepareStatement("insert into wechataccount(id ,accountname , accounttoken ,accountnumber , accounttype , accountemail , accountdesc ,accountaccesstoken,accountappid ,accountappsecret , ADDTOEKNTIME,  USERNAME , ACCOUNTID) values (?,?,?,?,?,?,?,?,?,?,?,?,?)");
				}
			}, new PreparedStatementCallback<Integer>() {
				@Override
				public Integer doInPreparedStatement(PreparedStatement ps)
						throws SQLException, DataAccessException {
					// TODO Auto-generated method stub
					ps.setString(1, weixinAccountEntity.getId());
					ps.setString(2, weixinAccountEntity.getAccountname());
					ps.setString(3, weixinAccountEntity.getAccounttoken());
					ps.setString(4, weixinAccountEntity.getAccountnumber());
					ps.setString(5, weixinAccountEntity.getAccounttype());
					ps.setString(6, weixinAccountEntity.getAccountemail());
					ps.setString(7, weixinAccountEntity.getAccountdesc());
					ps.setString(8, weixinAccountEntity.getAccountaccesstoken());
					ps.setString(9, weixinAccountEntity.getAccountappid());
					ps.setString(10, weixinAccountEntity.getAccountappsecret());
					ps.setDate(11, new java.sql.Date(weixinAccountEntity
							.getAddtoekntime().getTime()));
					ps.setString(12, weixinAccountEntity.getUsername());
					ps.setString(13, weixinAccountEntity.getAccountid());
					ps.execute();
					return 0;
				}
			});
		}

	}

	@Override
	public void deleteAccount(WechatAccountEntity weixinAccountEntity) {
		// TODO Auto-generated method stub
		String delSql = "delete from wechataccount where wechataccount.id='"
				+ weixinAccountEntity.getId() + "'";
		this.jdbcTemplate.execute(delSql);
	}

	private boolean checkExist(WechatAccountEntity weixinAccountEntity) {
		String querySql = "select count(1) from wechataccount where id=? ";
		List<Object> params = new ArrayList<Object>();
		params.add(weixinAccountEntity.getId());
		int argTypes[] = { Types.VARCHAR };
		Integer result = this.jdbcTemplate.queryForObject(querySql,
				params.toArray(), argTypes, Integer.class);
		if (result.intValue() > 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public List<WechatAccountEntity> getWechatAccounts(
			Map<String, Object> params) {
		// TODO Auto-generated method stub
		RowMapper<WechatAccountEntity> rm = ParameterizedBeanPropertyRowMapper
				.newInstance(WechatAccountEntity.class);
		String querySql = "select * from wechataccount where 1=1";
		List<Object> queryparams = new ArrayList<Object>();
		if (params.get("accountid") != null) {
			queryparams.add(params.get("accountid"));
			querySql = querySql + " and accountid=?";
		}
		if (params.get("username") != null) {
			queryparams.add(params.get("username"));
			querySql = querySql + " and username=?";
		}
		if (params.get("id") != null) {
			queryparams.add(params.get("id"));
			querySql = querySql + " and id=?";
		}
		if (params.get("accountappid") != null) {
			queryparams.add(params.get("accountappid"));
			querySql = querySql + " and accountappid=?";
		}
		return this.jdbcTemplate.query(querySql, queryparams.toArray(), rm);
	}

	@Override
	public AccessTokenYw getAccessToken(String appid) throws Exception {
		// TODO Auto-generated method stub
		RowMapper<AccessTokenYw> rm = ParameterizedBeanPropertyRowMapper
				.newInstance(AccessTokenYw.class);
		List<Object> params = new ArrayList<Object>();
		params.add(appid);
		String querySql = "select a.id, a.accountappid as appid,a.accountappsecret as appsecret,a.accountaccesstoken as access_token,a.addtoekntime as addtime,a.expiresin as expires_in,jsaccounttoken as jsaccess_token from wechataccount a where a.accountappid=? ";
	
		List<AccessTokenYw> accessTokens = this.jdbcTemplate.query(querySql,
				params.toArray(), rm);
		if (accessTokens == null || accessTokens.isEmpty()) {
			return null;
		} else {
			return accessTokens.get(0);
		}
	}

	@Override
	public void saveAccessToken(AccessTokenYw accessTokenYw) throws Exception {
		// TODO Auto-generated method stub
		String updAccessTokenSql = "update wechataccount a set a.accountaccesstoken=?,a.addtoekntime=?,a.expiresin=?,jsaccounttoken=? where a.accountappid=? ";
		List<Object> params = new ArrayList<Object>();
		params.add(accessTokenYw.getAccess_token());
		params.add(accessTokenYw.getAddtime());
		params.add(accessTokenYw.getExpires_in());
		params.add(accessTokenYw.getJsaccess_token());
		params.add(accessTokenYw.getAppid());
		
		this.jdbcTemplate.update(updAccessTokenSql, params.toArray());
	}

}
