package com.uniz.service;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.uniz.domain.UnizVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class UnizHitServiceTest {

	@Setter(onMethod_ = {@Autowired})
	private UnizHitService service;
	
	@Test
	public void testGetHitListTest() {
		
		service.getHitList().forEach(uniz->log.info(uniz));
	}
	
	

}
