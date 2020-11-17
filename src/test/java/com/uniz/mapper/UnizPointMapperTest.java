package com.uniz.mapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.uniz.domain.MyUnizPoint;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class UnizPointMapperTest {

	@Setter(onMethod_ = @Autowired)
	private UnizPointMapper mapper;

	@Test
	public void testInsert() {
		MyUnizPoint unizPoint = new MyUnizPoint();
		unizPoint.setUserSN(5000L);
		unizPoint.setUnizSN(10002L);
		unizPoint.setPoint(50);

		mapper.insert(MyUnizPoint.builder()
				.userSN(5000L)
				.unizSN(10002L)
				.point(50)
				.build()
				);
	}

	@Test
	public void testRead() {
		MyUnizPoint uniz = mapper.read(MyUnizPoint.builder()
				.userSN(5000L)
				.unizSN(10002L)
				.build());
		log.info("uniz.............. : " + uniz);
	}

	@Test
	public void testRead2() {
		MyUnizPoint uniz = mapper.read(5000L, 10002L);
		log.info("uniz.............2 : " + uniz);
	}

	@Test
	public void testUpdate() {
		List<Long> list = new ArrayList<Long>(Arrays.asList(new Long[] {10002L})); 

		mapper.update(5000L, list, 40);

		mapper.incMyUnizPoint(5000L, list, 5);

		mapper.decMyUnizPoint(5000L, list, 3);
	}

	@Test
	public void testUpdate2() {
		List<Long> list = new ArrayList<Long>(Arrays.asList(new Long[] {10002L, 10004L})); 
		
		log.info("incCount............... : " + mapper.incMyUnizPoint(5000L, list, 5) );
		
		log.info("decCount............... : " + mapper.decMyUnizPoint(5000L, list, 3) );
	}

	@Test
	public void testGetList() {
		List<MyUnizPoint> upList = mapper.getMyUnizPointList(5000L);

		log.info(upList);
	}

}