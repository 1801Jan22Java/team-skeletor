package com.revature.dao;

import java.util.List;

import com.revature.beans.Chatroom;

public interface ChatroomDao {

	public Chatroom getChatroomById(int id);
	public void deleteChatroom(int id);
	public void addChatroom(String name);
	public List<Chatroom> getChatrooms();
}
