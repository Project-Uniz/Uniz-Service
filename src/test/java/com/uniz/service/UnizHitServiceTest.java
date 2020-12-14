package com.uniz.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.uniz.domain.UnizVO;
import com.uniz.domain.VideoDataVO;
import com.uniz.mapper.UnizHitMapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class UnizHitServiceTest {

	@Setter(onMethod_ = {@Autowired})
	private UnizHitService service;
	private UnizHitMapper mapper;
	
//	@Test
//	public void testGetHitListTest() {
//		
//		service.getHitList().forEach(uniz->log.info(uniz));
//	}
	
//	@Test
//	public void testkeywordHitListTest() {
//		
//		
//		service.keywordHitlist((long) 20).forEach(list -> log.info(list));
//	}
	
//	@Test
//	public void testkeywordHitListTest2() {
//		
//		List<UnizVO> unizSN2 =mapper.keywordUniz((long)20);
//		service.keywordHitlist(unizSN2).forEach(list -> log.info(list));
//	}
//	
	

}
