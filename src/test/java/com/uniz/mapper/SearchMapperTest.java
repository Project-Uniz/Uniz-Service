package com.uniz.mapper;

import java.util.List;

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
public class SearchMapperTest {

	@Setter(onMethod_ = @Autowired)
	private SearchMapper mapper;

	@Test
	public void testGetSearchUnizList() {
		
		List<UnizVO> list = mapper.getSearchUnizList(2100L);
		
		log.info("getSearchUnizList..... : " + list);
	}
	
	@Test
	public void testGetUnizTypeFromUnizSN() {
		
		List<Integer> list = mapper.getUnizTypeFromUnizSN(2167L);
		
		log.info("getUnizTypeFromUnizSN..... : " + list);
	}
	
}

