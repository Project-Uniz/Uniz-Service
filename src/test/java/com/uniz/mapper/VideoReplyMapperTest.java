package com.uniz.mapper;

import java.util.List;
import java.util.stream.IntStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.uniz.domain.Criteria;
import com.uniz.domain.VideoReplyVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;


@RunWith(SpringRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j

public class VideoReplyMapperTest {
	
	private Long[] videoArr = {300L, 301L, 302L, 303L, 304L, 305L, 306L, 307L, 308L, 309L, 310L};
		

	@Setter(onMethod_ = @Autowired)
	private VideoReplyMapper mapper;
//
//	@Test
//	public void testMapper() {
//		log.info(mapper);
//	}
	
//	@Test
//	public void testCreate() {
		
//			IntStream.rangeClosed(1, 10).forEach(i -> {
			
//			VideoReplyVO vo = new VideoReplyVO();
//			
//			vo.setVideoSN(300L);
//			vo.setReplyContent("댓글테스트 2" );
//			vo.setUserSN(1L);
//			
//			mapper.insert(vo);
//			});
//	}
	
//	@Test
//	public void testRead() {
//		
//		Long targetreplySN = 1L;
//		
//		VideoReplyVO vo = mapper.read(targetreplySN);
//		
//		log.info(vo);
//	}
	
	
//	@Test
//	public void testDelete() {
//		
//		
//		Long targetreplySN = 1L;
//		
//		mapper.delete(targetreplySN);
//		
//	}
	
	
//	@Test
//	public void testUpdate() {
//		
//		Long targetreplySN = 10L;
//		
//		VideoReplyVO vo = mapper.read(targetreplySN);
//		
//		vo.setReplyContent("수정후 테스트");
//		
//		int count = mapper.update(vo);
//		
//		log.info("업데이트 카운트" + count);
//	}
	
	@Test
	public void testList() {
		
		Criteria cri = new Criteria();
		
		List<VideoReplyVO> reply = mapper.getListWithPaging(cri, videoArr[0]);
		
		reply.forEach(video -> log.info(video));
	}
}
