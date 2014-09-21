package com.mongolia.website.dao.interfaces;

import java.util.List;

import com.mongolia.website.model.UserValue;

public interface UserDao {

	public List<UserValue> queryByUserName(String username);

	public int checkUser(String username);


}