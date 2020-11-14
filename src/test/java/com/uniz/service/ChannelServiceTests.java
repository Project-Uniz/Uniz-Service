package com.uniz.service;

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
public class ChannelServiceTests {
	
	@Setter(onMethod_ = {@Autowired})
	private ChannelService service;
	
//	@Test
//	public void testRegister() {
//		
//		ChannelBoardVO vo = new ChannelBoardVO();
//		
//		vo.setChannelSN(6L);
//		vo.setUserSN(7L);
//		vo.setTitle("테스트");
//		vo.setPostContent("테스트");
//		
//		log.info("add count: " +service.register(vo));
//	}
//	@Test
//	public void testDelete() {
//		
//		service.delete(12L);
//		log.info("삭제 완료");
//	}
	
//	@Test
//	public void testUpdate() {
//		
//		ChannelBoardVO vo = new ChannelBoardVO();
//		
//		vo.setPostSN(14L);
//		vo.setTitle("수정 된 제목");
//		vo.setPostContent("수정된 내용");
//		
//		service.update(vo);
//		
//	}
	
	@Test
	public void testGetList() {
		log.info("채널 리스트 게시글 불러오기");
		service.getPostList(5L);
	}
	
}
