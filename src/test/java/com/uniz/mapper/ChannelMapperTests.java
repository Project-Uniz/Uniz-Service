package com.uniz.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.uniz.domain.ChannelBoardVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class ChannelMapperTests {
	
	@Setter(onMethod_ =@Autowired)
	private ChannelMapper mapper;
	
//	@Test
//	public void testCreateChannle() {
//		
//		ChannelVO vo = new ChannelVO();
//		
//		vo.setChannelTitle("AI Academy");
//		vo.setChannelComment("에이아이 아카데미 채널");
//		
//		mapper.createChannel(vo);
//	}
	
//	@Test
//	public void testCont() {
//		
//		ChannelBoardVO vo = new ChannelBoardVO();
//		
//		vo.setPostSN(12L);
//		vo.setChannelSN(5L);
//		vo.setUserSN(5L);
//		vo.setTitle("test");
//		vo.setPostContent("미워");
//		log.info("Vo: " + vo);
//		mapper.insertCont(vo);
//		
//	}
	
	@Test
	public void testGetList() {
		log.info(mapper.getPostList(5L));
	}
	
}
