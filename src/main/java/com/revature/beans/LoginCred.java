package com.revature.beans;

public class LoginCred {

	public LoginCred() {
		super();
		// TODO Auto-generated constructor stub
	}
	public LoginCred(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	private String username;
	private String password;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
