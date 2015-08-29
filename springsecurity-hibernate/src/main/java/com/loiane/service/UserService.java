package com.loiane.service;

import com.loiane.model.User;

public interface UserService {

	User findById(int id);
	
	User findBySso(String sso);
	
}