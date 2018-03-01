package com.revature.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.revature.beans.Message;
import com.revature.dao.MessageDaoImpl;

@Controller("messageController")
@RequestMapping("/message")
public class MessageController {
	
	@Autowired
	MessageDaoImpl messageDaoImpl;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Message> getMessageById(@PathVariable int id){
		ResponseEntity<Message> resp = null;
		
		Message msg = messageDaoImpl.getMessageById(id);
		if (msg == null) {
			resp = new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		} else {
			resp = new ResponseEntity<>(msg, HttpStatus.OK);
		}

		return resp;
		
	}
	
	@PostMapping("/addMessage")
	@ResponseBody
	public ResponseEntity<String> addTRex(@RequestBody Message message) {

		ResponseEntity<String> resp = null;
		System.out.println(message.toString());
		try {
			messageDaoImpl.createMessage(message);;
			resp = new ResponseEntity<>(message.toString(), HttpStatus.OK);
		} catch (Exception e) {
			resp = new ResponseEntity<>("failed to add message",
					HttpStatus.BAD_REQUEST);
		}

		return resp;

	}
	
	@RequestMapping(value="/chatroom/{id}", method=RequestMethod.GET)
	public ResponseEntity<List<Message>> getMessageByChatroomId(@PathVariable int id){
		ResponseEntity<List<Message>> resp = null;
		
		List<Message> msg = messageDaoImpl.getMessagesByChatroomId(id);
		if (msg == null) {
			resp = new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		} else {
			resp = new ResponseEntity<>(msg, HttpStatus.OK);
		}

		return resp;
		
	}
	
}
