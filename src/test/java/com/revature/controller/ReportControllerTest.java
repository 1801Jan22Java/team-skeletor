package com.revature.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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

import com.revature.beans.Message;
import com.revature.beans.Report;
import com.revature.beans.User;
import com.revature.service.ReportService;
import com.revature.util.TestContext;
import com.revature.util.TestUtil;
import com.revature.util.WebAppContext;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestContext.class, WebAppContext.class})
@WebAppConfiguration
public class ReportControllerTest {
	
	private MockMvc mockMvc;
	
	@Autowired
	ReportService rsMock;
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@Before
	public void setUp() {
		Mockito.reset(rsMock);
		
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	
	@Test
	public void testGetReportById() throws Exception{
		
		int id=1;
		User user = new User();
		Message msg = new Message();
		Report rep = new Report(id,user,msg);
		Mockito.when(rsMock.getReportById(id)).thenReturn(rep);
		
		mockMvc.perform(get("/report/{id}", id))
		.andExpect(status().isOk())
		.andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
		.andExpect(jsonPath("$.id", is(1)));
		
		Mockito.verify(rsMock, Mockito.times(1)).getReportById(id);
		Mockito.verifyNoMoreInteractions(rsMock);
	}
	
	@Test
	public void testGetReportByMessageId() throws Exception{
		
		int id=1;
		Report rep = new Report();
		List<Report> reps = new ArrayList<>();
		reps.add(rep);
		Mockito.when(rsMock.viewReportsByMessageId(id)).thenReturn(reps);
		
		mockMvc.perform(get("/report/message/{id}", id))
		.andExpect(status().isOk())
		.andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
		.andExpect(jsonPath("$[0].id", is(0)));
		
		Mockito.verify(rsMock, Mockito.times(1)).viewReportsByMessageId(id);
		Mockito.verifyNoMoreInteractions(rsMock);
	}
	
	@Test
	public void testDeleteChatroom() throws Exception{
		int id = 1;
		mockMvc.perform(delete("/report/delete/{id}", id))
		.andExpect(status().isOk())
		.andExpect(content().string("Report deleted"));
		
		Mockito.verify(rsMock, Mockito.times(1)).deleteReport(id);
		Mockito.verifyNoMoreInteractions(rsMock);
	}
}