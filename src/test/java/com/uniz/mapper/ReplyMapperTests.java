package com.uniz.mapper;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.uniz.domain.Criteria;
import com.uniz.domain.ReplyVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class ReplyMapperTests {
	
	
	
	@Setter(onMethod_ = @Autowired)
	private ReplyMapper mapper;
	
//	@Test
//	public void testMapper() {
//		
//		log.info(mapper);
//		
//	}
//	
//	@Test
//	public void testCreate() {
//		
//		ReplyVO vo = new ReplyVO();
//		
//		vo.setUserSN(9L);
//		vo.setPostSN(54L);
//		vo.setReplycontent("짜증나");
//		
//		mapper.insert(vo);
//		
//	}
	
//	@Test
//	public void testRead() {
//		
//		ReplyVO vo= mapper.read(12L);
//		
//		log.info(vo);
//		
//	}
	
//	@Test
//	public void testDelete() {
//		
//		mapper.deleteRelply(52L);
//		
//	}
	
//	@Test
//	public void testUpdate() {
//		
//		ReplyVO vo = new ReplyVO();
//		vo.setReplySN(2L);
//		vo.setReplycontent("댓글 수정 테스트");
//		
//		int count = mapper.update(vo);
//		
//		log.info("update count: " + count);
//		
//	}
	
//	@Test
//	public void testList() {
//		
//		Criteria cri = new Criteria();
//		
//		mapper.getReplyListWithPaging(cri, 54L).forEach(replycontent -> log.info(replycontent));
//	}
	@Test
	public void testList2() {
		Criteria cri = new Criteria(1,10);
		List<ReplyVO> replies = mapper.getReplyListWithPaging(cri, 85L);
		replies.forEach(replycontent -> log.info(replycontent));
	}
	
}
