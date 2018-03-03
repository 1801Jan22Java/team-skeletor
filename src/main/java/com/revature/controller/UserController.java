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

import com.revature.beans.User;
import com.revature.service.UserService;

@Controller("userController")
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService userService;
	
	@RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
	public ResponseEntity<User> getUserById(@PathVariable int id) {
		ResponseEntity<User> response = null;
		User user = userService.getUserById(id);
		if (user == null) {
			response = new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		} else {
			response = new ResponseEntity<>(user, HttpStatus.OK);
		}
		return response;
	}
	
	@RequestMapping(value = "/username/{username}", method = RequestMethod.GET)
	public ResponseEntity<User> getUserByUsername(@PathVariable String username) {
		ResponseEntity<User> response = null;
		User user = userService.getUserByUsername(username);
		if (user == null) {
			response = new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		} else {
			response = new ResponseEntity<>(user, HttpStatus.OK);
		}
		return response;
	}

	@PostMapping("/addUser")
	@ResponseBody
	public ResponseEntity<String> addUser(@RequestBody User user) {
		ResponseEntity<String> response = null;
		System.out.println(user.toString());
		try {
			userService.addUser(user);
			response = new ResponseEntity<>(user.toString(),HttpStatus.OK);
		} catch (Exception e) {
			response = new ResponseEntity<>("failed to add user", HttpStatus.BAD_REQUEST);
		}
		return response;
	}
	
	@PostMapping("/deleteUser")
	@ResponseBody
	public ResponseEntity<String> deleteUser(@RequestBody int userID){
		ResponseEntity<String> response = null;
		User user = userService.getUserById(userID);
		System.out.println(user.toString());
		try {
			userService.deleteUser(userID);
			response= new ResponseEntity<>(user.toString(),HttpStatus.OK);
		}
		catch(Exception e) {
			response = new ResponseEntity<>("failed to delete user", HttpStatus.BAD_REQUEST);
		}
		return response;
	}
	
	@PostMapping("/banUser")
	@ResponseBody
	public ResponseEntity<String> banUser(@RequestBody int userID){
		ResponseEntity<String> response = null;
		User user = userService.getUserById(userID);
		System.out.println(user.toString());
		try {
			userService.banUser(userID);
			response= new ResponseEntity<>("Response",HttpStatus.OK);
		}
		catch(Exception e) {
			response = new ResponseEntity<>("failed to ban user", HttpStatus.BAD_REQUEST);
		}
		return response;
	}
	
	@PostMapping("/reactivateUser")
	@ResponseBody
	public ResponseEntity<String> reactivateUser(@RequestBody int userID){
		ResponseEntity<String> response = null;
		User user = userService.getUserById(userID);
		System.out.println(user.toString());
		try {
			userService.reactivateUser(userID);
			response= new ResponseEntity<>(user.toString(),HttpStatus.OK);
		}
		catch(Exception e) {
			response = new ResponseEntity<>("failed to reactivate user", HttpStatus.BAD_REQUEST);
		}
		return response;
	}
	
	@GetMapping("/all")
	@ResponseBody
	public ResponseEntity<List<User>> getUsers(){
		return new ResponseEntity<>(userService.getUsers(), HttpStatus.OK);
	}
}
