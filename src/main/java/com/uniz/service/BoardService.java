package com.uniz.service;

import java.util.List;

import com.uniz.domain.BoardVO;
import com.uniz.domain.Criteria;

public interface BoardService {
	
	public List<BoardVO> getListWithPaging(Criteria cri);
	
	public List<BoardVO> getList(Criteria cri);
	
	public void register(BoardVO board);
	
	public boolean delete(Long postSN);
	
	public boolean update(BoardVO board);
	
	public BoardVO get(Long postSN);
	
	public int getTotal(Criteria cri);
}
