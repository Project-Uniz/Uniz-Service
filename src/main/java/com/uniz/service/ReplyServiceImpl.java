package com.uniz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uniz.domain.Criteria;
import com.uniz.domain.ReplyPageDTO;
import com.uniz.domain.ReplyVO;
import com.uniz.mapper.BoardMapper;
import com.uniz.mapper.ReplyMapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Service
@Log4j

public class ReplyServiceImpl implements ReplyService {

	@Setter(onMethod_ = @Autowired)
	private ReplyMapper mapper;
	
	@Setter(onMethod_ = @Autowired)
	private BoardMapper boardMapper;
	
	@Transactional
	@Override
	public int register(ReplyVO vo) {
		log.info("댓글 작성 전");
		boardMapper.updateReplyCnt(vo.getPostSN(), 1);
		log.info("댓글 수 업데이트 후 ");
		return mapper.insert(vo);
	}

	@Override
	public ReplyVO get(Long replySN) {
		log.info("댓글 읽기= " + replySN);
	
		return mapper.read(replySN);
	}

	@Override
	public int modify(ReplyVO vo) {
		log.info("댓글 수정= " + vo);
		return mapper.update(vo);
	}
	
	@Transactional
	@Override
	public int remove(Long replySN) {
		log.info("댓글 삭제= " + replySN);
		
		ReplyVO vo = mapper.read(replySN);
		
		boardMapper.updateReplyCnt(vo.getPostSN(), -1);
		return mapper.deleteReply(replySN);
	}

	@Override
	public List<ReplyVO> getReplyListWithPaging(Criteria cri, Long postSN) {
		log.info("댓글 전체 읽기= " + postSN);
		return mapper.getReplyListWithPaging(cri, postSN);
	}
	
	@Override
	public ReplyPageDTO getListPage(Criteria cri, Long postSN) {
		
		return new ReplyPageDTO(mapper.getCountByPostSN(postSN),
								mapper.getReplyListWithPaging(cri, postSN));
		
	}

}
