package com.revature.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.beans.Message;
import com.revature.dao.MessageDaoImpl;

@Service(value="messageService")
public class MessageService {
	@Autowired
	MessageDaoImpl messageDaoImpl;
	public void createMessage(Message msg) {
		messageDaoImpl.createMessage(msg);

	}
	public Message getMessageById(int msgId) {
		
		return messageDaoImpl.getMessageById(msgId);
	}

	public List<Message> getMessages() {
		
		return messageDaoImpl.getMessages();
	}

	public void deleteMessage(int msgId) {
		messageDaoImpl.deleteMessage(msgId);

	}

	public void updateMessage(Message msg) {
		messageDaoImpl.updateMessage(msg);

	}

	public List<Message> getMessagesByChatroomId(int roomId) {
		
		return messageDaoImpl.getMessagesByChatroomId(roomId);
	}

}
