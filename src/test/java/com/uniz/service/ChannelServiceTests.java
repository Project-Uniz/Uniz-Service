package com.uniz.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.uniz.domain.ChannelVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class ChannelServiceTests {
	
	@Setter(onMethod_ = {@Autowired})
	private ChannelService service;
	
//	@Test
//	public void testRegister() {
//		
//		ChannelBoardVO vo = new ChannelBoardVO();
//		
//		vo.setChannelSN(6L);
//		vo.setUserSN(5L);
//		vo.setTitle("멘붕");
//		vo.setPostContent("멘붕이다");
//		
//		service.register(vo);
//	}
//	@Test
//	public void testDelete() {
//		
//		service.delete(19L);
//		log.info("삭제 완료");
//	}
	
//	@Test
//	public void testUpdate() {
//		
//		ChannelBoardVO vo = new ChannelBoardVO();
//		
//		vo.setPostSN(22L);
//		vo.setTitle("컨트롤러가 문제인가");
//		vo.setPostContent("아니 내가 문제야");
//		
//		service.update(vo);
//		
//	}
	
//	@Test
//	public void testGetList() {
//		log.info("채널 리스트 게시글 불러오기");
//		service.getPostList(5L);
//	}
	
//	@Test
//	public void testCreateChannel() {
//		
//		ChannelVO vo = new ChannelVO();
//		
//		vo.setChannelTitle("test channel");
//		vo.setChannelComment("test comment");
//		service.createChannel(vo);
//	}
	
}
