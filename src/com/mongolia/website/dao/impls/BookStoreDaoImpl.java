package com.mongolia.website.dao.impls;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.mongolia.website.dao.interfaces.BookStoreDao;
import com.mongolia.website.model.BookStoreValue;

@Repository("bookStoreDaoImpl")
public class BookStoreDaoImpl extends BaseDaoiBatis implements BookStoreDao {

	@Override
	public void addBookStore(BookStoreValue bookStoreValue) throws Exception {
		// TODO Auto-generated method stub
		this.getSqlMapClientTemplate().insert("addBookStoreValue",
				bookStoreValue);

	}

	@Override
	public void updBookStore(BookStoreValue bookStoreValue) throws Exception {
		// TODO Auto-generated method stub
		this.getSqlMapClientTemplate().insert("updBookStoreValue",
				bookStoreValue);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BookStoreValue> queryBookStoreValues(Map<String, Object> params)
			throws Exception {
		// TODO Auto-generated method stub
		return this.getSqlMapClientTemplate().queryForList(
				"getBookStoreValues", params);
	}

	@Override
	public void deletebook(String bookid) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("bookid", bookid);
		this.getSqlMapClientTemplate().delete("deletebook", params);
	}

}
