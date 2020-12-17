package com.uniz.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.uniz.domain.ChannelReplyVO;
import com.uniz.domain.Criteria;

public interface ChannelReplyMapper {
	
	public int insert(ChannelReplyVO vo);
	
	public ChannelReplyVO read(Long replySN);
	
	public int deleteReply(Long replySN);
	
	public int update(ChannelReplyVO vo);
	
	public List<ChannelReplyVO> getList(@Param("cri") Criteria cri, @Param("postSN") Long postSN);
	
	public int getCount(Long postSN);
	
}
