package com.uniz.service;

import java.util.List;

import com.uniz.domain.Criteria;
import com.uniz.domain.VideoReplyVO;

public interface VideoReplyService {
	
	public int register(VideoReplyVO vo);
	
	public VideoReplyVO get(Long replySN);
	
	public int modify(VideoReplyVO vo);
	
	public int remove(Long replySN);
	
	public List<VideoReplyVO> getList(Criteria cri, Long videoSN);

}
