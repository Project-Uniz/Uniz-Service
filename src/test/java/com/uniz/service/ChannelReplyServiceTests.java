package com.uniz.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.uniz.domain.ChannelReplyVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class ChannelReplyServiceTests {
	
	@Setter(onMethod_ = {@Autowired})
	private ChannelReplyService service;
	
//	@Test
//	public void testRegister() {
//		
//		ChannelReplyVO vo = new ChannelReplyVO();
//		
//		vo.setPostSN(78L);
//		vo.setReplyContent("잘 할 수 있다");
//		vo.setUserSN(9L);
//		
//		service.register(vo);
//		
//	}
//	@Test
//	public void testGet() {
//		
//		log.info(service.get(9L));
//		
//	}
	
//	@Test
//	public void testUpdate() {
//		
//		ChannelReplyVO vo = new ChannelReplyVO();
//		
//		vo.setReplySN(9L);
//		vo.setReplyContent("화이팅!");
//		
//		service.modify(vo);
//		
//	}
	
//	@Test
//	public void testDelete() {
//		
//		service.delete(9L);
//		
//	}
	
}
