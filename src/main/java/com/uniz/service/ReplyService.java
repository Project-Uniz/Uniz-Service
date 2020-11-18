package com.uniz.service;

import java.util.List;

import com.uniz.domain.Criteria;
import com.uniz.domain.ReplyPageDTO;
import com.uniz.domain.ReplyVO;

public interface ReplyService {
	
	public int register(ReplyVO vo);
	
	public ReplyVO get(Long replySN);
	
	public int modify(ReplyVO vo);
	
	public int remove(Long replySN);
	
	public List<ReplyVO> getReplyListWithPaging(Criteria cri, Long postSN);
	
	public ReplyPageDTO getListPage(Criteria cri, Long postSN);
	
}
