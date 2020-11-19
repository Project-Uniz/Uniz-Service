package com.uniz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uniz.domain.ChannelBoardVO;
import com.uniz.domain.ChannelVO;
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
	
	public List<ChannelBoardVO> getAllPost(){
		return mapper.getAllPost();
	}
	
	public List<ChannelBoardVO> getPost(Long postSN){
		log.info("게시글 출력 ");
		
		return mapper.getPost(postSN);
	}
	
	public void createChannel(ChannelVO vo) {
		mapper.createChannel(vo);
	}
	
	@Transactional
	@Override
	public void register(ChannelBoardVO vo) {
		
		mapper.insertPost(vo);
		mapper.insertCont(vo);
	}
	
	@Transactional
	@Override
	public boolean delete(Long postSN) {
		
		if(mapper.deletePost(postSN) == 1 && mapper.deleteCont(postSN) == 1) {
			log.info("삭제중");
			return true;
		}
		
		return false;
		
	}
	
	@Transactional
	@Override
	public boolean update(ChannelBoardVO vo) {
		
		if(mapper.updatePost(vo) == 1 && mapper.updateCont(vo) == 1) {
			return true;
		}
			return false;
	}

}
