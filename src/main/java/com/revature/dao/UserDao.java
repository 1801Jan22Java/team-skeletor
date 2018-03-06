package com.revature.dao;

import java.util.ArrayList;
import java.util.List;

import com.revature.beans.User;

public interface UserDao {
	
	public List<User> getUsers();
	public User getUserById(int id);
	public int getUserId(User user);
	public User getUserByUsername(String username);
	public int addUser(User user);
	public void deleteUser(int userID);
	public void updateUser(int userID);
	public void banUser(Integer userID);
	public void reactivateUser(Integer userID);
	public User getUserByCredentials(String username, String password);
	public void updateUserPhoto(int userID, int photoID);

}
