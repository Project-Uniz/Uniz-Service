package com.uniz.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({ "file:src/main/webapp/WEB-INF/spring/root-context.xml",
"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml" })
@Log4j
public class ChannelControllerTests {
	
	@Setter(onMethod_ = {@Autowired})
	private WebApplicationContext ctx;
	
	private MockMvc mockMvc;
	
	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
	}
	
//	@Test
//	public void testRegister() throws Exception{
//		
//		String resultPage = mockMvc.perform(MockMvcRequestBuilders.post("/channel/register")
//				.param("title", "컨트롤러 테스트")
//				.param("userSN", "7")
//				.param("postContent" , "제발")
//				.param("channelSN", "6")).andReturn().getModelAndView().getViewName();
//		log.info("resultPage=======" + resultPage);
//	}
	
//	@Test
//	public void testUpdate() throws Exception{
//		
//		String resultPage = mockMvc.perform(MockMvcRequestBuilders.post("/channel/modify")
//				.param("postSN", "40")
//				.param("title", "수정")
//				.param("channelSN", "6")
//				.param("postContent", "수정")).andReturn().getModelAndView().getViewName();
//		
//		log.info("update: " +resultPage);
//				
//	}
	
//	@Test
//	public void testRemove() throws Exception{
//		String resultPage = mockMvc.perform(MockMvcRequestBuilders.post("/channel/remove")
//				.param("postSN", "39")
//				.param("channelSN", "6")).andReturn().getModelAndView().getViewName();
//		
//		log.info(resultPage);
//	}
	

	
}
