package com.revature.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.revature.beans.Chatroom;
import com.revature.service.ChatroomService;


@Controller("chatroomController")
@RequestMapping("/chatroom")
public class ChatroomController {

	@Autowired
	ChatroomService chatroomService;
	
	@GetMapping("/{id}")
	public ResponseEntity<String> getChatroomById(@PathVariable int id){
		ResponseEntity<String> response = null;
		String chatroomName = chatroomService.getNameById(id);
		
		if(chatroomName==null) {
			response = new ResponseEntity("that's not a chatroom id", HttpStatus.BAD_REQUEST);
		} else {
			response = new ResponseEntity(chatroomName, HttpStatus.OK);
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
	public ResponseEntity<String> createChatroom(@RequestBody Chatroom chatroom) {

		ResponseEntity<String> resp = null;
		
		try {
			chatroomService.addChatroom(chatroom.getName());
			resp = new ResponseEntity<>(chatroom.getName(), HttpStatus.OK);
		} catch (Exception e) {
			
			resp = new ResponseEntity<>("failed to add chatroom",
					HttpStatus.BAD_REQUEST);
		}

		return resp;

	}
	
	@RequestMapping(value="/delete/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<String> deleteMessage(@PathVariable int id){
		
		
		chatroomService.deleteChatroom(id);
		

		return new ResponseEntity<>("Chatroom Deleted", HttpStatus.OK);
		
	}
}
