package com.uniz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uniz.domain.ChannelBoardVO;
import com.uniz.mapper.ChannelMapper;

import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
@AllArgsConstructor
public class ChannelServiceImpl implements ChannelService {
	
	@Setter(onMethod_ = @Autowired)
	private ChannelMapper mapper;
	
	
	public List<ChannelBoardVO> getChannelList(){
		log.info("채널  목록 출력");
		return mapper.getChannelList();
	}
	
	public List<ChannelBoardVO> getPostList(Long channelSN){
		log.info("해당 채널 게시글 목록 출력");
		return mapper.getPostList(channelSN);
	}
	
	public List<ChannelBoardVO> getPost(Long postSN){
		log.info("게시글 출력 ");
		return mapper.getPost(postSN);
	}
	
	@Transactional
	@Override
	public int register(ChannelBoardVO vo) {
		
		if (mapper.insertPost(vo) == 1 && mapper.insertCont(vo) == 1) {
			return 1;
		}
			return 0;
	}
	
	@Transactional
	@Override
	public int delete(Long postSN) {
		
		if(mapper.deletePost(postSN) == 1 && mapper.deleteCont(postSN) == 1) {
			log.info("삭제중");
			return 1;
		}
		
		return 0;
		
	}
	
	@Transactional
	@Override
	public int update(ChannelBoardVO vo) {
		
		if(mapper.updatePost(vo) == 1 && mapper.updateCont(vo) == 1) {
			return 1;
		}
			return 0;
	}

}
