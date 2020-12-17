package com.uniz.mapper;



import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.uniz.domain.BoardVO;
import com.uniz.domain.Criteria;



public interface BoardMapper {
	
	public List<BoardVO> getBoardList(); // 게시판 목록 보여줌
	
	public List<BoardVO> getPostList(@Param("cri") Criteria cri, @Param("boardSN") Long boardSN); // 해당 게시판의 게시글 목록 보여줌
	
	public int getTotalCountByBoard(Long boardSN); // 해당 게시판의 게시글 총 수
	
	public List<BoardVO> getList(Long boardSN);  // 게시글 목록 보여주는 mapper
	
	public List<BoardVO> getAllPost(@Param("cri") Criteria cri); // 게시글 목록 페이징 처리한거
	
	public int getTotalCount(); // 게시글 총 수
	
	public int checkBoard(Long boardSN);
	
	public void insertPost(BoardVO board); // 게시글 쓰는거
	
	public void insertCont(BoardVO board); // 게시글 쓰는거
	
	public BoardVO read(Long postSN); // 게시글 읽는거
	
	public int deletePost(Long postSN); // 게시글 삭제

	public int deleteCont(Long postSN); // 게시글 삭제
	
	public int deleteReply(Long postSN); // 댓글 삭제
	
	public int updatePost(BoardVO board); // 게시글 수정
	
	public int updateCont(BoardVO board); // 게시글 수정
	
	public void updateReplyCnt(@Param("postSN") Long postSN, @Param("amount") int amount);
	
}
