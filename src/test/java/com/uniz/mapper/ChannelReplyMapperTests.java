package com.uniz.mapper;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.uniz.domain.ChannelReplyVO;
import com.uniz.domain.Criteria;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class ChannelReplyMapperTests {
	
	@Setter(onMethod_ = @Autowired)
	private ChannelReplyMapper mapper;

//	@Test
//	public void testMapper() {
//		log.info(mapper);
//	}
	
//	@Test
//	public void testInsert() {
//		
//		ChannelReplyVO vo = new ChannelReplyVO();
//		
//		vo.setUserSN(5L);
//		vo.setReplyContent("아아 매퍼 테스틑");
//		vo.setPostSN(33L);
//		
//		mapper.insert(vo);
//		
//	}
	
//	@Test
//	public void testRead() {
//		log.info("read : " + mapper.read(8L));
//	}
	
//	@Test
//	public void testUpdate() {
//		
//		ChannelReplyVO vo = new ChannelReplyVO();
//		
//		vo.setReplyContent("얄리얄리 얄랴셩");
//		vo.setReplySN(8L);
//		
//		mapper.update(vo);
//		
//	}
	
//	@Test
//	public void testDelete() {
//		
//		mapper.deleteReply(8L);
//		
//	}
	
	@Test
	public void testList() {
		
		Criteria cri = new Criteria();
		
		List<ChannelReplyVO> reply = mapper.getList(cri, 78L);
		
		log.info("reply===== " + reply);
		
	}
	
	
}
