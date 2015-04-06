package com.mongolia.website.dao.interfaces;

import java.util.List;
import java.util.Map;

import com.mongolia.website.model.BookStoreValue;

/**
 * 
 * @author Administrator
 *
 */
public interface BookStoreDao {
	/**
	 * 添加书籍
	 * 
	 * @param bookStoreValue
	 * @throws Exception
	 */
	public void addBookStore(BookStoreValue bookStoreValue) throws Exception;

	/**
	 * 
	 * @param bookStoreValue
	 * @throws Exception
	 */
	public void updBookStore(BookStoreValue bookStoreValue) throws Exception;

	/**
	 * 
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public List<BookStoreValue> queryBookStoreValues(Map<String, Object> params)
			throws Exception;
    /**
     * 
     * @param bookid
     * @throws Exception
     */
	public void deletebook(String bookid) throws Exception;
}
