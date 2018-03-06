package com.revature.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.beans.User;
import com.revature.dao.UserDao;
import com.revature.dao.UserDaoImpl;

@Service(value="loginService")	
public class LoginService {
	@Autowired
	UserDao userDaoImpl;
	
	public User getUserByCredentials(String username, String password){
		return userDaoImpl.getUserByCredentials(username,password);
	}
	
	public User getUserByUsername(String username) {
		return userDaoImpl.getUserByUsername(username);
	}

}
