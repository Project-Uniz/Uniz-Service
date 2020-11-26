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
public class ChannelReplyControllertests {
	
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
//	String resultPage = mockMvc.perform(MockMvcRequestBuilders.post("/chreplies/add")
//			.param("userSN", "9")
//			.param("replyContent" , "제발")
//			.param("postSN", "78")).andReturn().getModelAndView().getViewName();
//	log.info("resultPage=======" + resultPage);
//}
	
//	@Test
//	public void testRemove() throws Exception{
//		String resultPage = mockMvc.perform(MockMvcRequestBuilders.post("/chreplies/remove")
//				.param("postSN", "78")
//				.param("replySN", "10")).andReturn().getModelAndView().getViewName();
//		
//		log.info(resultPage);
//	}
	
//	@Test
//	public void testUpdate() throws Exception{
//		
//		String resultPage = mockMvc.perform(MockMvcRequestBuilders.post("/chreplies/modify")
//				.param("postSN", "74")
//				.param("replySN", "11")
//				.param("replyContent", "수정ggggg")).andReturn().getModelAndView().getViewName();
//		
//		log.info("update: " +resultPage);
//				
//	}
	
	
	
}
