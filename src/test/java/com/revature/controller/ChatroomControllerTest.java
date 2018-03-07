package com.revature.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.revature.beans.Chatroom;
import com.revature.service.ChatroomService;
import com.revature.util.TestContext;
import com.revature.util.TestUtil;
import com.revature.util.WebAppContext;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestContext.class, WebAppContext.class})
@WebAppConfiguration
public class ChatroomControllerTest {
	
	private MockMvc mockMvc;
	
	@Autowired
	ChatroomService csMock;
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@Before
	public void setUp() {
		Mockito.reset(csMock);
		
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	
	@Test
	public void testGetChatroomById() throws Exception{
		
		int id=1;
		String name = "GENERAL";
		Chatroom room = new Chatroom();
		room.setId(id);
		room.setName("GENERAL");
		Mockito.when(csMock.getNameById(id)).thenReturn(room);
		
		mockMvc.perform(get("/chatroom/{id}", 1))
		.andExpect(status().isOk())
		.andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
		.andExpect(jsonPath("$.id", is(1)))
		.andExpect(jsonPath("$.name", is(name)));
		
		Mockito.verify(csMock, Mockito.times(1)).getNameById(1);
		Mockito.verifyNoMoreInteractions(csMock);
	}
	
	@Test
	public void testGetAllChatrooms() throws Exception{
		
		List<Chatroom> chatrooms = new ArrayList<>();
		Chatroom c = new Chatroom(1,"GENERAL");
		chatrooms.add(c);
		
		Mockito.when(csMock.getChatrooms()).thenReturn(chatrooms);
		
		mockMvc.perform(get("/chatroom/all"))
		.andExpect(status().isOk())
		.andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
		.andExpect(jsonPath("$[0].id", is(1)))
		.andExpect(jsonPath("$[0].name", is("GENERAL")));
		
		Mockito.verify(csMock, Mockito.times(1)).getChatrooms();
		Mockito.verifyNoMoreInteractions(csMock);
	}
	
	@Test
	public void testDeleteChatroom() throws Exception{
		int id = 1;
		mockMvc.perform(delete("/chatroom/delete/{id}", id))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.msg", is("Chatroom deleted")));
		
		Mockito.verify(csMock, Mockito.times(1)).deleteChatroom(id);
		Mockito.verifyNoMoreInteractions(csMock);
	}
	
	/*@Test
	public void testAddChatroom() throws Exception{
		String name = "Ape Quadrant";
		
		mockMvc.perform(post("/SkeletorSlums/chatroom/addChatroom"))
		.andExpect(status().isOk())
		.andExpect(content().string(""));
		
		Mockito.verify(csMock, Mockito.times(1)).addChatroom(name);
		Mockito.verifyNoMoreInteractions(csMock);
	}*/
}