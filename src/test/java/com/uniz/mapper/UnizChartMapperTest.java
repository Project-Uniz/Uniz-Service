package com.uniz.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class UnizChartMapperTest {
	
	@Setter(onMethod_ = @Autowired)
	private UnizChartMapper mapper;
	
	
	
	@Test
	public void testchartData() {
		
		mapper.chartData().forEach(video -> log.info(video));

		
	}

}
