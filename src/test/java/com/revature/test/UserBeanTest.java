package com.revature.test;
import static org.junit.Assert.*;

import org.junit.Test;

import com.revature.beans.User;



public class UserBeanTest {

	@Test
	public void test() {
	//	fail("Not yet implemented");
	}
	
	@Test
	public void getUserName() {
		User user = new User("username","testpass","testemail",10,false,false);
		String name = user.getUsername();
		assert(name.equals("username"));
	}
	@Test
	public void testUserPassword() {
		User user = new User("username","testpass","testemail",10,false,false);
		String pass = user.getPassword();
		assert(pass.equals("testpass"));
		
	}

}
