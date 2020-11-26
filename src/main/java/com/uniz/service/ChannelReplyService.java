package com.uniz.service;

import java.util.List;

import com.uniz.domain.ChannelReplyVO;
import com.uniz.domain.Criteria;

public interface ChannelReplyService {
	
	public int register(ChannelReplyVO vo);
	
	public ChannelReplyVO get(Long replySN);
	
	public boolean modify(ChannelReplyVO vo);
	
	public boolean delete(Long replySN);
	
	public List<ChannelReplyVO> getList(Criteria cri , Long postSN);
	
}
