package com.revature.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.beans.User;
import com.revature.dao.UserDaoImpl;

@Service(value="userService")
public class UserService {
	@Autowired
	UserDaoImpl userDaoImpl;
	

	public User getUserById(int userID) {
		return userDaoImpl.getUserById(userID);
	}
	
	public List<User> getUsers(){
		return userDaoImpl.getUsers();
	}
	
	public void deleteUser(int userID) {
		 userDaoImpl.deleteUser(userID);
	}
	
	public User getUserByUsername(String username) {
		User user =userDaoImpl.getUserByUsername(username);
		return user;
	}
	
	public void updateUserPhoto(int userID, int photoID) {
		userDaoImpl.updateUserPhoto(userID,photoID);
	}
	
	public void banUser(int userID) {
		userDaoImpl.banUser(userID);
	}
	
	public void reactivateUser(int userID) {
		userDaoImpl.reactivateUser(userID);
	}
	
	public void addUser(User user) {
		userDaoImpl.addUser(user);
	}
	

}
