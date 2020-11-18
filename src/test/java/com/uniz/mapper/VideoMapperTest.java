package com.uniz.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.uniz.domain.VideoDataVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@Log4j
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class VideoMapperTest {
	@Setter(onMethod_ = @Autowired)
	private VideoMapper mapper;
	
	@Test
	public void urlTest() {
		long videoSN = 3;
		
		
		VideoDataVO videoVO = mapper.getVideo(videoSN);
		String changeURL = videoVO.getUrlPath();
		int idx = changeURL.indexOf("=");
		
		videoVO.setUrlPath(changeURL.substring(idx+1));
		
		System.out.println(videoVO.getUrlPath());
	}
}
