package com.mongolia.website.manager.impls;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mongolia.website.dao.interfaces.BookStoreDao;
import com.mongolia.website.manager.interfaces.BookStoreManager;
import com.mongolia.website.model.BookStoreValue;

/**
 * 
 * @author Administrator
 *
 */
@Service("bookStoreManagerImpl")
public class BookStoreManagerImpl implements BookStoreManager {
	@Resource(name = "bookStoreDaoImpl")
	private BookStoreDao bookStoreDao;

	/**
	 * 添加书籍
	 * 
	 * @param bookStoreValue
	 * @throws Exception
	 */
	public void addBookStore(BookStoreValue bookStoreValue) throws Exception {
		bookStoreDao.addBookStore(bookStoreValue);
	}

	/**
	 * 
	 * @param bookStoreValue
	 * @throws Exception
	 */
	public void updBookStore(BookStoreValue bookStoreValue) throws Exception {
		bookStoreDao.updBookStore(bookStoreValue);
	}

	/**
	 * 
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public List<BookStoreValue> queryBookStoreValues(Map<String, Object> params)
			throws Exception {
		return bookStoreDao.queryBookStoreValues(params);

	}

	@Override
	public void deletebook(String bookids) throws Exception {
		// TODO Auto-generated method stub
		String[] ids = bookids.split(",");
		for (String id : ids) {
			this.bookStoreDao.deletebook(id);
		}

	}

	
	

}
