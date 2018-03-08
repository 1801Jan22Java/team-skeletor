package com.revature.controller;

import static org.hamcrest.CoreMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDateTime;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.Mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.beans.Chatroom;
import com.revature.beans.Message;
import com.revature.beans.User;
import com.revature.service.MessageService;
import com.revature.service.UserService;
import com.revature.util.TestContext;
import com.revature.util.TestUtil;
import com.revature.util.WebAppContext;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestContext.class, WebAppContext.class })
@WebAppConfiguration
public class UserControllerTest {

	private MockMvc mockMvc;

	@Autowired
	UserService usMock;

	@Autowired
	private WebApplicationContext webApplicationContext;

	ObjectMapper mapper = new ObjectMapper();

	@Before
	public void setUp() {
		Mockito.reset(usMock);

		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
				.build();
	}
	
	@Test
	public void testGetUserById() throws Exception {
		int id = 1;
		Message msg = new Message();
		User usr = new User(id, "username", "password", "emailAddress", 1, true,
				true);
		
		

		Mockito.when(usMock.getUserById(id)).thenReturn(usr);

		mockMvc.perform(get("/user/id/{id}", 1)).andExpect(status().isOk())
				.andExpect(
						content().contentType(TestUtil.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$.id", is(1)))
				.andExpect(jsonPath("$.username", is("username")))
				.andExpect(jsonPath("$.password", is("****")))
				.andExpect(jsonPath("$.emailAddress", is("emailAddress")))
				.andExpect(jsonPath("$.admin", is(true)))
				.andExpect(jsonPath("$.active", is(true)));

		Mockito.verify(usMock, Mockito.times(1)).getUserById(1);
		Mockito.verifyNoMoreInteractions(usMock);

	}
	
	@Test
	public void testGetUsers() throws Exception {
		int id = 1;
		Message msg = new Message();
		User usr = new User(id, "username", "password", "emailAddress", 1, true,
				true);
		
		

		Mockito.when(usMock.getUsers()).thenReturn(Arrays.asList(usr, usr));

		mockMvc.perform(get("/user/all", 1)).andExpect(status().isOk())
				.andExpect(
						content().contentType(TestUtil.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$[0].id", is(1)))
				.andExpect(jsonPath("$[0].username", is("username")))
				.andExpect(jsonPath("$[0].password", is("password")))
				.andExpect(jsonPath("$[0].emailAddress", is("emailAddress")))
				.andExpect(jsonPath("$[0].admin", is(true)))
				.andExpect(jsonPath("$[0].active", is(true)))
				.andExpect(jsonPath("$[1].id", is(1)))
				.andExpect(jsonPath("$[1].username", is("username")))
				.andExpect(jsonPath("$[1].password", is("password")))
				.andExpect(jsonPath("$[1].emailAddress", is("emailAddress")))
				.andExpect(jsonPath("$[1].admin", is(true)))
				.andExpect(jsonPath("$[1].active", is(true)));

		Mockito.verify(usMock, Mockito.times(1)).getUsers();
		Mockito.verifyNoMoreInteractions(usMock);

	}
	
	@Test
	public void testGetUserByUsername() throws Exception {
		int id = 1;
		Message msg = new Message();
		User usr = new User(id, "username", "password", "emailAddress", 1, true,
				true);
		
		

		Mockito.when(usMock.getUserByUsername("username")).thenReturn(usr);

		mockMvc.perform(get("/user/username/{username}", "username")).andExpect(status().isOk())
				.andExpect(
						content().contentType(TestUtil.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$.id", is(1)))
				.andExpect(jsonPath("$.username", is("username")))
				.andExpect(jsonPath("$.password", is("****")))
				.andExpect(jsonPath("$.emailAddress", is("emailAddress")))
				.andExpect(jsonPath("$.admin", is(true)))
				.andExpect(jsonPath("$.active", is(true)));

		Mockito.verify(usMock, Mockito.times(1)).getUserByUsername("username");
		Mockito.verifyNoMoreInteractions(usMock);

	}
}
