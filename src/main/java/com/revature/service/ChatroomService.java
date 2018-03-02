package com.revature.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.beans.Chatroom;
import com.revature.dao.ChatroomDaoImpl;

@Service(value="chatroomService")
public class ChatroomService{

	@Autowired
	ChatroomDaoImpl chatroomDaoImpl;


	public String getNameById(int id) {
		return chatroomDaoImpl.getNameById(id);
	}

	
	public void deleteChatroom(int id) {
		chatroomDaoImpl.deleteChatroom(id);
		
	}

	
	public void addChatroom(String name) {
		chatroomDaoImpl.addChatroom(name);
		
	}

	
	public List<Chatroom> getChatrooms() {
		return chatroomDaoImpl.getChatrooms();
	}
}
