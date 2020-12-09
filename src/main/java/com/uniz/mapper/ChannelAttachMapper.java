package com.uniz.mapper;

import java.util.List;

import com.uniz.domain.BoardAttachVO;
import com.uniz.domain.ChannelAttachVO;

public interface ChannelAttachMapper {
	
	public void insert(ChannelAttachVO vo);
	
	public void delete(String uuid);
	
	public List<ChannelAttachVO> findByPostSN(Long postSN);
	
	public void deleteAll(Long postSN);
	
}
