package com.uniz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniz.domain.Criteria;
import com.uniz.domain.ReplyPageDTO;
import com.uniz.domain.ReplyVO;
import com.uniz.mapper.ReplyMapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Service
@Log4j

public class ReplyServiceImpl implements ReplyService {

	@Setter(onMethod_ = @Autowired)
	private ReplyMapper mapper;
	
	@Override
	public int register(ReplyVO vo) {
		log.info("댓글 작성" + vo);
		return mapper.insert(vo);
	}

	@Override
	public ReplyVO get(Long replySN) {
		log.info("댓글 읽기" + replySN);
		log.info("매퍼 : ---------"+mapper);
		return mapper.read(replySN);
	}

	@Override
	public int modify(ReplyVO vo) {
		log.info("댓글 수정" + vo);
		return mapper.update(vo);
	}

	@Override
	public int remove(Long replySN) {
		log.info("댓글 삭제" + replySN);
		return mapper.deleteReply(replySN);
	}

	@Override
	public List<ReplyVO> getReplyListWithPaging(Criteria cri, Long postSN) {
		log.info("댓글 전체 읽기" + postSN);
		return mapper.getReplyListWithPaging(cri, postSN);
	}
	
	@Override
	public ReplyPageDTO getListPage(Criteria cri, Long postSN) {
		
		return new ReplyPageDTO(mapper.getCountByPostSN(postSN),
								mapper.getReplyListWithPaging(cri, postSN));
		
	}

}
