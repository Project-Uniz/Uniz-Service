package com.uniz.service;

import java.util.List;

import com.uniz.domain.ChannelBoardVO;

public interface ChannelService {
	
	public List<ChannelBoardVO> getChannelList();
	
	public List<ChannelBoardVO> getPostList(Long channelSN);
	
	public List<ChannelBoardVO> getPost(Long postSN);
	
	public int register(ChannelBoardVO vo);
	
	public int delete(Long postSN);
	
	public int update(ChannelBoardVO vo);
	
}
