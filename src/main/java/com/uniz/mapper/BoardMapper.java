package com.uniz.mapper;



import java.util.List;

import com.uniz.domain.BoardVO;
import com.uniz.domain.Criteria;



public interface BoardMapper {
	
	//@Select("select * from uniz_post where usersn > 0")
	public List<BoardVO> getList();
	
	public List<BoardVO> getListWithPaging(Criteria cri);
	
	public void insertPost(BoardVO board);
	
	public void insertCont(BoardVO board);
	
	public BoardVO read(Long postSN);
	
	public int deletePost(Long postSN);

	public int deleteCont(Long postSN);
	
	public int deleteReply(Long postSN);
	
	public int updatePost(BoardVO board);
	
	public int updateCont(BoardVO board);
	
	public int getTotalCount(Criteria cri);
	
}
