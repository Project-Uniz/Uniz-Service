package com.uniz.service;

import java.util.List;

import com.uniz.domain.ChannelBoardVO;
import com.uniz.domain.ChannelPageDTO;
import com.uniz.domain.ChannelVO;
import com.uniz.domain.Criteria;

public interface ChannelService {
	
	public List<ChannelBoardVO> getChannelList();
	
	public List<ChannelBoardVO> getPostList(Long channelSN);
	
	public List<ChannelBoardVO> getAllPost(Criteria cri);  // cri 추가
	
	public ChannelPageDTO getListPage(Criteria cri);  // 추가
	
	public ChannelBoardVO getPost(Long postSN);
	
	public void createChannel(ChannelVO vo);
	
	public void register(ChannelBoardVO vo);
	
	public boolean delete(Long postSN);
	
	public boolean update(ChannelBoardVO vo);
	
}
