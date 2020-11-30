package com.uniz.mapper;

import org.junit.Test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.uniz.domain.UnizVO;
import com.uniz.domain.VideoDataVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class UnizHitMapperTest {

	@Setter(onMethod_ = @Autowired)
	private UnizHitMapper mapper;

//	@Test
//	public void testRead() {
//		VideoDataVO uniz = mapper.read(1L);
//		log.info("result :" + uniz);
//	}
//	

	@Test
	public void testReadList() {
		
		mapper.getHitList().forEach(video -> log.info(video));

		
	}
	
	



}