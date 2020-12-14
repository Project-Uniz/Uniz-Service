package com.uniz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uniz.domain.ChannelReplyPageDTO;
import com.uniz.domain.ChannelReplyVO;
import com.uniz.domain.Criteria;
import com.uniz.mapper.ChannelMapper;
import com.uniz.mapper.ChannelReplyMapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class ChannelReplyServiceImpl implements ChannelReplyService {
	
	@Setter(onMethod_ = @Autowired)
	private ChannelReplyMapper mapper;
	
	@Setter(onMethod_ = @Autowired)
	private ChannelMapper channelMapper;
	
	@Transactional
	@Override
	public int register(ChannelReplyVO vo) {
		
		channelMapper.updateReplyCnt(vo.getPostSN(), 1);
		return mapper.insert(vo);
	}

	@Override
	public ChannelReplyVO get(Long replySN) {
		// 해당 댓글 번호의 댓글 내용 불러옴
		return mapper.read(replySN);
	}

	@Override
	public int modify(ChannelReplyVO vo) {
		// 수정할 댓글 번호와 수정 댓글 내용을 객체로 받아와 댓글 수정
		return mapper.update(vo) ;
	}
	
	@Transactional
	@Override
	public int delete(Long replySN) {
		
		ChannelReplyVO vo = mapper.read(replySN);
		
		channelMapper.updateReplyCnt(vo.getPostSN(), -1);
		return mapper.deleteReply(replySN) ;
	}
	
	@Override
	public List<ChannelReplyVO> getList(Criteria cri, Long postSN){
		//postSN 해당하는 댓글 목록 불러옴
		
		return mapper.getList(cri, postSN);
	}
	
	@Override
	public ChannelReplyPageDTO getListPage(Criteria cri , Long postSN) {
		
		return new ChannelReplyPageDTO(mapper.getCount(postSN),
									   mapper.getList(cri, postSN));
		
	}

}
