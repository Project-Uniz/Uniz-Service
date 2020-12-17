package com.uniz.mapper;

import java.util.List;

import com.uniz.domain.BoardAttachVO;

public interface BoardAttachMapper {
	
	public void insert(BoardAttachVO vo);
	
	public void delete(String uuid);
	
	public List<BoardAttachVO> findByPostSN(Long postSN);
	
	public void deleteAll(Long postSN);
	
}
