package com.uniz.service;

import java.util.List;

import com.uniz.domain.ChannelAttachVO;
import com.uniz.domain.ChannelBoardVO;
import com.uniz.domain.ChannelPageDTO;
import com.uniz.domain.ChannelVO;
import com.uniz.domain.Criteria;
import com.uniz.domain.UserData;

public interface ChannelService {
	
	public List<ChannelBoardVO> getChannelList();
	
	public List<ChannelBoardVO> getPostList( Criteria cri , Long channelSN); // 해당 채널 게시물 목록
	
	public List<ChannelBoardVO> getList(Long channelSN);
	
	public ChannelPageDTO getPostListPaging( Criteria cri, Long channelSN); // 해당 채널 게시물 페이징 처리
	
	public List<ChannelBoardVO> getAllPost(Criteria cri);  // cri 추가  전체 게시물 페이징 처리
	
	public ChannelPageDTO getListPage(Criteria cri);  // 추가
	
	public ChannelBoardVO getPost(Long postSN);
	
	public int checkChannel(Long channelSN);
	
	public List<UserData> checkUserType(Long userSN);
	
	public void createChannel(ChannelVO vo);
	
	public void register(ChannelBoardVO vo);
	
	public boolean delete(Long postSN);
	
	public boolean update(ChannelBoardVO vo);
	
	public List<ChannelAttachVO> getAttachList(Long postSN);
	
}
