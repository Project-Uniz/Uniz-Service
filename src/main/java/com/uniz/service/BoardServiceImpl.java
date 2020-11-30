package com.uniz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uniz.domain.BoardVO;
import com.uniz.domain.Criteria;
import com.uniz.mapper.BoardMapper;

import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
@AllArgsConstructor
public class BoardServiceImpl implements BoardService {
	
	@Setter(onMethod_ = @Autowired)
	private BoardMapper mapper;
	
	@Override
	public List<BoardVO> getListWithPaging(Criteria cri){
		
		return mapper.getListWithPaging(cri);
		
	}
	
	@Override
	public List<BoardVO> getList(Criteria cri){
		
		log.info("get List with criteria: " + cri);
		
		log.info("board매퍼 ======="+ mapper);
		
		return mapper.getList();
		
	}
	
	@Transactional
	@Override
	public void register(BoardVO board) {
		
		mapper.insertPost(board);
		log.info("BoardPost 데이터 추가");
		
		mapper.insertCont(board);
		log.info("BoardPostContent 데이터 추가");
	}
	
	@Transactional
	@Override
	public boolean delete(Long postSN) {
		
		 mapper.deleteReply(postSN);
		 log.info("해당 글의 댓글 삭제");
		if(mapper.deleteCont(postSN) == 1 && mapper.deletePost(postSN) == 1 ) {
			return true;
		}
			  
		return false;
		
	}
	@Transactional
	@Override
	public boolean update(BoardVO board) {
		log.info("업데이트 전");
		if(mapper.updatePost(board) == 1 && mapper.updateCont(board) == 1) {
			log.info("업데이트 성공");
			return true;
		}
		log.info("업데이트 실패");
		return false;
	}
	
	@Override
	public BoardVO get(Long postSN) {
		
		return mapper.read(postSN);
		
	}
	
	@Override
	public int getTotal(Criteria cri) {
		
		return mapper.getTotalCount(cri);
		
	}

}
