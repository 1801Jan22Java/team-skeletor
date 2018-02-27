package com.revature.dao;

public interface ChatroomDao {

	public String getNameById(int id);
	public int getIdByName(String name);
	public void deleteChatroom(int id);
	public void addChatroom(String name);
}
