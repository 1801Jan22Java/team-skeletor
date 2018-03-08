package com.revature.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.revature.beans.LoginCred;
import com.revature.beans.MyResponseMessage;
import com.revature.beans.User;
import com.revature.service.LoginService;

@Controller("loginController")
@RequestMapping("/login")
@CrossOrigin(origins = "*")
public class LoginController {

	@Autowired
	LoginService loginService;

	// Just checks if someone is still logged in, is an admin, or is logged out.
	@RequestMapping(value = "/testSess")
	@ResponseBody
	public ResponseEntity<MyResponseMessage> testSess() {
		ResponseEntity<MyResponseMessage> response = null;
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder
				.currentRequestAttributes();
		HttpSession session = attr.getRequest().getSession();
		try {
			String username = session.getAttribute("username").toString();
			User u = loginService.getUserByUsername(username);
			if (!u.isAdmin() && u.isActive()) {
				response = new ResponseEntity<>(new MyResponseMessage("You are logged in"),
						HttpStatus.OK);
			} else if (u.isAdmin()) {
				response = new ResponseEntity<>(new MyResponseMessage("You are an admin"),
						HttpStatus.OK);
			}
		} catch (Exception e) {
			response = new ResponseEntity<>(new MyResponseMessage("You are not allowed in here"),
					HttpStatus.BAD_REQUEST);

		}
		return response;
	}

	// For logging the user out, invalidates the session
	@RequestMapping(value = "/logout", method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<MyResponseMessage> logout() {
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder
				.currentRequestAttributes();
		HttpSession session = attr.getRequest().getSession();
		ResponseEntity<MyResponseMessage> response = null;
		session.invalidate();
		try {
			response = new ResponseEntity<>(new MyResponseMessage("You have been logged out"),
					HttpStatus.OK);
		} catch (Exception e) {
			response = new ResponseEntity<>(new MyResponseMessage("Something went wrong..."),
					HttpStatus.BAD_REQUEST);
		}
		return response;
	}

	// Takes in HttpSession, String username, String password. Initializes
	// session
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<User> login(@RequestBody LoginCred login) {
		User u = null;
		ResponseEntity<User> response = null;
		try {
			u = loginService.getUserByCredentials(login.getUsername(), login.getPassword());

				boolean isAdmin = u.isAdmin();
				ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder
						.currentRequestAttributes();
				HttpSession session = attr.getRequest().getSession();
				session.setAttribute("username", u.getUsername());
				session.setAttribute("isAdmin", u.isAdmin());
				String sessUser = session.getAttribute("username").toString();
				response = getSession();
		} catch (Exception e) {

			response = new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);

		}
		return response;
	}

	// Ensures that someone is logged into an HttpSession as an active user
	private ResponseEntity<User> getSession() {
		ResponseEntity<User> response = null;
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder
				.currentRequestAttributes();
		HttpSession session = attr.getRequest().getSession();
		String username = session.getAttribute("username").toString();
		User u = loginService.getUserByUsername(username);
		// System.out.println("In get session"); //DEBUGGING

		response = new ResponseEntity<>(u, HttpStatus.OK);
		// System.out.println("In if statement"); //DEBUGGING
		return response;

	}

	@GetMapping(value = "")
	public String getApp() {
		return "forward:/static/index.html";
	}
}
