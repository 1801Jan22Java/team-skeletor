package com.revature.dao;

import java.util.List;

import com.revature.beans.Message;

public interface MessageDao {
	public void createMessage(Message msg);

	public Message getMessageById(int msgId);

	public List<Message> getMessages();

	public void deleteMessage(int msgId);

	public void updateMessage(Message msg);
	
	public List<Message> getMessagesByChatroomId(int roomId);

}
