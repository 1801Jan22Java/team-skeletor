package com.revature.controller;


import static org.hamcrest.CoreMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.Mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.revature.beans.Chatroom;
import com.revature.beans.Message;
import com.revature.beans.User;
import com.revature.service.MessageService;
import com.revature.util.TestContext;
import com.revature.util.TestUtil;
import com.revature.util.WebAppContext;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestContext.class, WebAppContext.class})
@WebAppConfiguration
public class MessageControllerTest {
	
	private MockMvc mockMvc;
	
	@Autowired
	MessageService msMock;
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@Before
	public void setUp() {
		Mockito.reset(msMock);
		
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	
	@Test
	public void testGetMessageById() throws Exception {
		int id = 1;
		Message msg = new Message();
		User usr = new User();
		Chatroom room = new Chatroom();
		LocalDateTime d = LocalDateTime.now();
		String m = "test";
		String i = "image";
		msg.setId(id);
		msg.setUser(usr);
		msg.setRoom(room);
		msg.setDate(d);
		msg.setMessage(m);
		msg.setImageURL(i);
		
		Mockito.when(msMock.getMessageById(id)).thenReturn(msg);
		
		mockMvc.perform(get("/message/{id}", 1))
			.andExpect(status().isOk())
			.andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
			.andExpect(jsonPath("$.id", is(1)))
			.andExpect(jsonPath("$.user", is(usr)))
			.andExpect(jsonPath("$.room", is(room)))
			.andExpect(jsonPath("$.date", is(d)))
			.andExpect(jsonPath("$.message", is(m)))
			.andExpect(jsonPath("$.imageURL", is(i)));
		
		Mockito.verify(msMock, Mockito.times(1)).getMessageById(1);
		Mockito.verifyNoMoreInteractions(msMock);
		
	}
}
