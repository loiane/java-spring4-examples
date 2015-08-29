package com.loiane.dao;

import com.loiane.model.User;

public interface UserDao {

	User findById(int id);
	
	User findBySSO(String sso);
	
}

