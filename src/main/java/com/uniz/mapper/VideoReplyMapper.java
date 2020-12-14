package com.uniz.mapper;



import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.uniz.domain.Criteria;
import com.uniz.domain.VideoReplyVO;

public interface VideoReplyMapper {
	
	public int insert(VideoReplyVO vo);
	
	public VideoReplyVO read(Long videoSN);
	
	public int delete (Long replySN);
	
	public int update(VideoReplyVO videoReply);
	
	public List<VideoReplyVO> getListWithPaging(
			@Param("cir") Criteria cri,
			@Param("videoSN") Long videoSN);

}
