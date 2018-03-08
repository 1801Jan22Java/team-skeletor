package com.revature.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.revature.beans.Message;
import com.revature.beans.MyResponseMessage;
import com.revature.service.MessageService;

@Controller("messageController")
@RequestMapping("/message")
@CrossOrigin(origins="http://localhost:4200")

public class MessageController {
	
	@Autowired
	MessageService messageService;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Message> getMessageById(@PathVariable int id){
		ResponseEntity<Message> resp = null;
		
		Message msg = messageService.getMessageById(id);
		if (msg == null) {
			resp = new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		} else {
			resp = new ResponseEntity<>(msg, HttpStatus.OK);
		}

		return resp;
		
	}
	
	@PostMapping(value="/addMessage")
	public ResponseEntity<MyResponseMessage> createMessage(@RequestBody Message message) {

		ResponseEntity<MyResponseMessage> resp = null;
		try {
			messageService.createMessage(message);
			resp = new ResponseEntity<>(new MyResponseMessage("Message successfully Created"), HttpStatus.OK);
		} catch (Exception e) {
			resp = new ResponseEntity<>(new MyResponseMessage("failed to add message"),
					HttpStatus.BAD_REQUEST);
		}
		
		return resp;

	}
	
	@RequestMapping(value="/chatroom/{id}", method=RequestMethod.GET)
	public ResponseEntity<List<Message>> getMessageByChatroomId(@PathVariable int id){
		ResponseEntity<List<Message>> resp = null;
		
		List<Message> msg = messageService.getMessagesByChatroomId(id);
		if (msg == null) {
			resp = new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		} else {
			resp = new ResponseEntity<>(msg, HttpStatus.OK);
		}

		return resp;
		
	}
	
	@RequestMapping(value="/delete/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<MyResponseMessage> deleteMessage(@PathVariable int id){
		
		
		messageService.deleteMessage(id);
		

		return new ResponseEntity<>(new MyResponseMessage("Message Deleted"), HttpStatus.OK);
		
	}
	
	@RequestMapping(value="/delete/chatroom/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<MyResponseMessage> deleteMessageByChatroom(@PathVariable int id){
		
		
		messageService.deleteMessageByChatroom(id);
		

		return new ResponseEntity<>(new MyResponseMessage("Messages Deleted From Catroom"), HttpStatus.OK);
		
	}
	
	
	
}
