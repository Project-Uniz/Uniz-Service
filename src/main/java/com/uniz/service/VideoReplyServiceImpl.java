package com.uniz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniz.domain.Criteria;
import com.uniz.domain.VideoReplyVO;
import com.uniz.mapper.VideoReplyMapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class VideoReplyServiceImpl implements VideoReplyService {
	
	
	@Setter(onMethod_ = @Autowired)
	private VideoReplyMapper mapper;

	@Override
	public int register(VideoReplyVO vo) {
		
		log.info("register......" + vo);
		
		return mapper.insert(vo);
	}

	@Override
	public VideoReplyVO get(Long replySN) {
		
		log.info("get......" + replySN);		
		
		return mapper.read(replySN);
	}

	@Override
	public int modify(VideoReplyVO vo) {
		
		log.info("update......" + vo);
		
		return mapper.update(vo);
	}

	@Override
	public int remove(Long replySN) {
		
		log.info("remove......" + replySN);
		
		return mapper.delete(replySN);
	}

	@Override
	public List<VideoReplyVO> getList(Criteria cri, Long videoSN) {
		
		log.info("get Reply List of a Video" + videoSN);
		
		return mapper.getListWithPaging(cri, videoSN);
	}
	
	
	
	

}
