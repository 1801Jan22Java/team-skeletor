package com.revature.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

public class LoginController {
	
	@RequestMapping("login")
	public ModelAndView login(@RequestParam("username") String username, 
			@RequestParam("password") String pass) 
	{
		ModelAndView mv = new ModelAndView();
		if(username.equals("test") && pass.equals("pass")) {
			mv.setViewName("welcome.jsp");
			return mv;
		}
		else {
			mv.setViewName("loginscreen.jsp");
			return mv;
		}
	}

}
