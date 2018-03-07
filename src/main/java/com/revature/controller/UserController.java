package com.revature.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.revature.beans.MyResponseMessage;
import com.revature.beans.User;
import com.revature.service.UserService;

@Controller("userController")
@RequestMapping("/user")
@CrossOrigin (origins="http://localhost:8084")
public class UserController {

	@Autowired
	UserService userService;
	
	@RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
	public ResponseEntity<User> getUserById(@PathVariable int id) {
		ResponseEntity<User> response = null;
		User user = userService.getUserById(id);
		user.setPassword("****");
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
		user.setPassword("****");
		if (user == null) {
			response = new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		} else {
			response = new ResponseEntity<>(user, HttpStatus.OK);
		}
		return response;
	}

	@PostMapping("/addUser")
	@ResponseBody
	public ResponseEntity<User> addUser(@RequestBody User user) {
		ResponseEntity<User> response = null;
		System.out.println(user.toString());
		try {
			userService.addUser(user);
			
			//Setting password to null before user is returned in response.
			
			user.setPassword(null);
			
			response = new ResponseEntity<>(user,HttpStatus.OK);
		} catch (Exception e) {
			user=null;
			response = new ResponseEntity<>(user, HttpStatus.BAD_REQUEST);
		}
		return response;
	}
	/*
	 * controller method for updating user image
	 * */
	@RequestMapping(value="/updateUserImage",method=RequestMethod.PUT)
	@ResponseBody
	public ResponseEntity<User> updateUserImage(@RequestBody User user, @RequestParam int photoID) {
		ResponseEntity<User> response = null;
		int userID = userService.getUserID(user);
		//System.out.println(user.toString());
		//System.out.println(photoID);
	
		try {
			userService.updateUserPhoto(userID, photoID);
			
			//Setting password to null before user is returned in response.
			
			user.setPassword(null);
			//System.out.println(user.getImageId());
			response = new ResponseEntity<>(user,HttpStatus.OK);
		} catch (Exception e) {
			user=null;
			//e.printStackTrace();
			response = new ResponseEntity<>(user, HttpStatus.BAD_REQUEST);
		}
		return response;
	}
	
	
	/*
	 * controller method for updating user email and password
	 * */
	
	@RequestMapping(value="/updateUser",method=RequestMethod.PUT)
	@ResponseBody
	public ResponseEntity<User> updateUser(@RequestBody User user, @RequestParam String email, String password, String passwordConf) {
		ResponseEntity<User> response = null;
		int userID = userService.getUserID(user);
		if(password.equals("")||password==null) {
			//System.out.println("checking password");
			password=user.getPassword();
		}
		if(email.equals("") || email==null) {
			//System.out.println("checking e-mail");
			email=user.getEmailAddress();
		}
		try {
			
			//System.out.println("In update try");
			userService.updateUser(userID, email,password,passwordConf);
			
			//Setting password to null before user is returned in response.
			
		//	user.setPassword(null);
			//System.out.println(user.getImageId());
			response = new ResponseEntity<>(user,HttpStatus.OK);
		} catch (Exception e) {
			user=null;
			//e.printStackTrace();
			response = new ResponseEntity<>(user, HttpStatus.BAD_REQUEST);
		}
		
		return response;
	}
	

	
	
	@RequestMapping(value="/deleteUser/{userID}",method=RequestMethod.DELETE)
	@ResponseBody
	public ResponseEntity<MyResponseMessage> deleteUser(@PathVariable int userID){
		ResponseEntity<MyResponseMessage> response = null;
		User user = userService.getUserById(userID);
		System.out.println(user.toString());
		try {
			userService.deleteUser(userID);
			user.setPassword(null);
			response= new ResponseEntity<>(new MyResponseMessage(user.getUsername() +  " deleted"),HttpStatus.OK);
		}
		catch(Exception e) {
			//e.printStackTrace();
			response = new ResponseEntity<>(new MyResponseMessage("failed to delete user"), HttpStatus.BAD_REQUEST);
		}
		return response;
	}
	
	@PostMapping("/banUser")
	@ResponseBody
	public ResponseEntity<MyResponseMessage> banUser(@RequestBody int userID){
		ResponseEntity<MyResponseMessage> response = null;
	
		
		try {
		//	Integer userNum= Integer.parseInt(userID);
		//	System.out.println(userID);
			User user = userService.getUserById(userID);
		
			System.out.println(userID);
			userService.banUser(userID);
			System.out.println(user.toString());
			
			response= new ResponseEntity<>(new MyResponseMessage("User " + user.getUsername()+ " banned"),HttpStatus.OK);
		}
		catch(Exception e) {
			//e.printStackTrace();
			response = new ResponseEntity<>(new MyResponseMessage("failed to ban user"), HttpStatus.BAD_REQUEST);
		}
		return response;
	}
	
	
	@PostMapping(value="/reactivateUser")
	@ResponseBody
	public ResponseEntity<MyResponseMessage> reactivateUser(@RequestBody int userID){
		ResponseEntity<MyResponseMessage> response = null;
		User user = userService.getUserById(userID);
	//	System.out.println(user.toString());
		try {
			userService.reactivateUser(userID);
			response= new ResponseEntity<>(new MyResponseMessage("User " + user.getUsername() + " reactivated"),HttpStatus.OK);
		}
		catch(Exception e) {
			response = new ResponseEntity<>(new MyResponseMessage("failed to reactivate user"), HttpStatus.BAD_REQUEST);
		}
		return response;
	}
	
	@GetMapping("/all")
	@ResponseBody
	public ResponseEntity<List<User>> getUsers(){
		return new ResponseEntity<>(userService.getUsers(), HttpStatus.OK);
	}
}