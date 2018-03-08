package com.revature.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.revature.beans.Chatroom;
import com.revature.beans.MyResponseMessage;
import com.revature.service.ChatroomService;


@Controller("chatroomController")
@RequestMapping("/chatroom")
@CrossOrigin (origins="*")
public class ChatroomController {

	@Autowired
	ChatroomService chatroomService;
	
	@GetMapping("/{id}")
	public ResponseEntity<Chatroom> getChatroomById(@PathVariable int id){
		ResponseEntity<Chatroom> response = null;
		Chatroom chatroom = chatroomService.getNameById(id);
		
		if(chatroom==null) {
			response = new ResponseEntity(null, HttpStatus.BAD_REQUEST);
		} else {
			response = new ResponseEntity(chatroom, HttpStatus.OK);
		}
		
		return response;
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<Chatroom>> getAllChatrooms(){
		ResponseEntity<List<Chatroom>> response = null;
		List<Chatroom> chatrooms = chatroomService.getChatrooms();
		
		if(chatrooms==null) {
			response = new ResponseEntity(null, HttpStatus.BAD_REQUEST);
		} else {
			response = new ResponseEntity(chatrooms, HttpStatus.OK);
		}
		
		return response;
	}
	
	
	@PostMapping("/addChatroom")
	@ResponseBody
	public ResponseEntity<MyResponseMessage> createChatroom(@RequestBody Chatroom chatroom) {

		ResponseEntity<MyResponseMessage> resp = null;
		
		try {
			chatroomService.addChatroom(chatroom.getName());
			resp = new ResponseEntity<>(new MyResponseMessage(chatroom.getName()), HttpStatus.OK);
		} catch (Exception e) {
			
			resp = new ResponseEntity<>(new MyResponseMessage("failed to add chatroom"),
					HttpStatus.BAD_REQUEST);
		}

		return resp;

	}
	
	@RequestMapping(value="/delete/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<MyResponseMessage> deleteMessage(@PathVariable int id){
		
		
		chatroomService.deleteChatroom(id);
		

		return new ResponseEntity<>(new MyResponseMessage("Chatroom deleted"), HttpStatus.OK);
		
	}
}
