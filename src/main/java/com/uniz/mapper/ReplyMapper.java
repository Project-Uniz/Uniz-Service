package com.uniz.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.uniz.domain.Criteria;
import com.uniz.domain.ReplyVO;

public interface ReplyMapper {
	
	public int insert(ReplyVO vo);
	
	public ReplyVO read(Long replySN);
	
	public int deleteReply(Long replySN);
	
	public int update(ReplyVO vo);
	
	public List<ReplyVO> getReplyListWithPaging(@Param("cri") Criteria cri, @Param("postSN") Long postSN);
	
	public int getCountByPostSN(Long postSN);
}
