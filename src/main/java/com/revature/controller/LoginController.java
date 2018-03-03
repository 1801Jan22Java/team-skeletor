package com.revature.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.revature.beans.User;
import com.revature.service.LoginService;

@Controller("loginController")
@RequestMapping("/login")
public class LoginController {

	@Autowired
	LoginService loginService;


	// Just checks if someone is still logged in, is an admin, or is logged out.
	@RequestMapping(value = "/testSess")
	@ResponseBody
	public ResponseEntity<String> testSess() {
		ResponseEntity<String> response = null;
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpSession session = attr.getRequest().getSession();
		try {
			String username = session.getAttribute("username").toString();
			User u = loginService.getUserByUsername(username);
			if (!u.isAdmin() && u.isActive()) {
				response = new ResponseEntity<>("You are logged in", HttpStatus.OK);
			} else if (u.isAdmin()) {
				response = new ResponseEntity<>("You are an admin", HttpStatus.OK);
			}
		} catch (Exception e) {
			response = new ResponseEntity<>("You are not allowed in here", HttpStatus.BAD_REQUEST);

		}
		return response;
	}

	// For logging the user out, invalidates the session
	@RequestMapping(value = "/logout")
	@ResponseBody
	public ResponseEntity<String> logout() {
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpSession session = attr.getRequest().getSession();
		ResponseEntity<String> response = null;
		session.invalidate();
		try {
			response = new ResponseEntity<>("You have been logged out", HttpStatus.OK);
		} catch (Exception e) {
			response = new ResponseEntity<>("Something went wrong...", HttpStatus.BAD_REQUEST);
		}
		return response;
	}

	// Takes in HttpSession, String username, String password. Initializes session
	@RequestMapping(value = "/login")
	@ResponseBody
	public ResponseEntity<String> login(String username, String password) {
		User u = null;
		ResponseEntity<String> response = null;
		try {
			u = loginService.getUserByCredentials("skeletor", "Skelet0r");

			
		/* No longer checking if user is banned at login...
		 * 
		 * 	if (!u.isActive()) {
				response = new ResponseEntity<>("User is inactive", HttpStatus.BAD_REQUEST);
			} else {
		 */
				boolean isAdmin = u.isAdmin();
				ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder
						.currentRequestAttributes();
				HttpSession session = attr.getRequest().getSession();
				session.setAttribute("username", u.getUsername());
				session.setAttribute("isAdmin", u.isAdmin());
				String sessUser = session.getAttribute("username").toString();
				response = getSession();
				// System.out.println(sessUser); //DEBUGGING
			//}

		} catch (Exception e) {
			response = new ResponseEntity<>("failed to log in user", HttpStatus.BAD_REQUEST);
			// u.getUsername();
		}
		return response;
	}
	
	
	// Ensures that someone is logged into an HttpSession as an active user
	private ResponseEntity<String> getSession() {
		ResponseEntity<String> response = null;
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpSession session = attr.getRequest().getSession();
		String username = session.getAttribute("username").toString();
		User u = loginService.getUserByUsername(username);
		// System.out.println("In get session"); //DEBUGGING
		if (u.isAdmin()) {

			response = new ResponseEntity<>("Still logged in as " + u.getUsername() + " who is admin", HttpStatus.OK);
			// System.out.println("In if statement"); //DEBUGGING
		}
		return response;
	}
}
