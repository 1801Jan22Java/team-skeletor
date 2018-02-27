package com.revature.dao;

import java.util.ArrayList;

import com.revature.beans.User;

public interface UserDao {
	
	public ArrayList<User> getUsers();
	public User getUserById();
	public int getUserId();
	public User getUserByUsername();
	public int addUser(User user);
	public void deleteUser(User user);
	public void updateUser(User user);
	

}
