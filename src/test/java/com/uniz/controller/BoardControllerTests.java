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
public class BoardControllerTests {
	
	@Setter(onMethod_ = {@Autowired})
	private WebApplicationContext ctx;
	
	private MockMvc mockMvc;
	
	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
	}
	
//	@Test
//	public void testList() throws Exception{
//		
//		log.info(mockMvc.perform(
//				MockMvcRequestBuilders.get("/board/list")
//				.param("pageNum", "3")
//				.param("amount", "3"))
//				.andReturn().getModelAndView().getModelMap());
//	}
	
//	@Test
//	public void testRegister() throws Exception{
//		
//		String resultPage = mockMvc.perform(MockMvcRequestBuilders.post("/board/register")
//				.param("title", "노란뚱땡이")
//				.param("userSN", "8")
//				.param("postcontent", "오민형은 노란 뚱땡이다"))
//				.andReturn().getModelAndView().getViewName();
//		
//		log.info(resultPage);
//		
//	}
	
//	@Test
//	public void testGet() throws Exception{
//		
//		log.info(mockMvc.perform(MockMvcRequestBuilders
//				.get("/board/get")
//				.param("postsn", "40"))
//				.andReturn()
//				.getModelAndView().getModelMap());
//		
//	}
//	@Test
//	public void testUpdate() throws Exception{
//		
//		String resultPage = mockMvc.perform(MockMvcRequestBuilders.post("/board/modify")
//				.param("postSN", "6")
//				.param("title", "수정된 제목aaaaa ")
//				.param("postContent", "수정된 내용aaaa")).andReturn().getModelAndView().getViewName();
//		
//		log.info("update: " +resultPage);
//				
//		
//	}
	
	@Test
	public void testRemove() throws Exception{
		
		String resultPage = mockMvc.perform(MockMvcRequestBuilders.post("/board/remove")
				.param("postSN", "51")).andReturn().getModelAndView().getViewName();
		
		log.info(resultPage);
		
	}
	
}
