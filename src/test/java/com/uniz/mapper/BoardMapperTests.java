package com.uniz.mapper;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.uniz.domain.BoardVO;
import com.uniz.domain.Criteria;
import com.uniz.service.BoardService;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class BoardMapperTests {
	
	@Setter(onMethod_ = @Autowired)
	private BoardMapper mapper;
	
	@Setter(onMethod_ = @Autowired)
	private BoardService service;
	
//	@Test
//	public void testGetList() {
//		
//		mapper.getList().forEach(board -> log.info(board));
//	}
	
	
//	@Test
//	public void insert() {
//		
//		BoardVO board = new BoardVO();
//		
//		board.setUserSN(4L);
//		board.setTitle("44444444");
//		
//		board.setPostContent("ddddddd"); 
//		
//		log.info("postSN = "+board.getPostSN());
//		
//		service.register(board);
//		
//	}
	
//	@Test
//	public void testRead() {
//		
//		BoardVO board = mapper.read(50L);
//		
//		log.info(board);
//		
//	}
	
//	@Test
//	public void testDelete() {
//		
//		log.info("결과 : " + mapper.deleteReply(51L)) ;
//		
//	}
//	
//	@Test
//	public void testUpdate() {
//		
//		BoardVO board = new BoardVO();
//		
//		board.setPostSN(6L);                     
//		board.setTitle("제목 수정");
//		board.setPostContent("도우너 어서오고");
//		
//		log.info("결과= " + mapper.updatePost(board));
//		
//	}
	
	@Test
	public void testPaging() {
		
		Criteria cri = new Criteria();
		
		cri.setPageNum(2);
		cri.setAmount(10);
		
		List<BoardVO> list = mapper.getListWithPaging(cri);
		
		list.forEach(board -> log.info(board));
		
	}
	
//	@Test
//	public void testSearch() {
//		
//		Criteria cri = new Criteria();
//		cri.setKeyword("오민뚱");
//		cri.setType("W");
//		
//		List<BoardVO> list = mapper.getListWithPaging(cri);
//		
//		list.forEach(board -> log.info(board));
//		
//	}
//	
}
