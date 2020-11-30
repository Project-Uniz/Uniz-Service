package com.uniz.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.uniz.domain.Criteria;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration()
@Log4j
public class BoardServiceTests {
	
	@Setter(onMethod_ = {@Autowired})
	private BoardService service;
	
//	@Test
//	public void testExist() {
//		
//		log.info(service);
//		assertNotNull(service);
//	}
	
//	@Test
//	public void testGetList() {
//		
//		service.getList(new Criteria(2,10)).forEach(board -> log.info(board));
//	}
	
	
//	@Test
//	public void testGet() {
//		log.info(service.get(7L));
//	}
	
//	@Test
//	public void testUpdate() {
//		
//		BoardVO board = new BoardVO();
//		
//		board.setPostSN(6L);                     
//		board.setTitle("배가고파aaa");
//		board.setPostContent("밥먹자aa");
//		
//		log.info("결과= " + service.update(board ));
//		
//	}
	
	@Test
	public void testDelete() {
		
		
		
	}
	
}
