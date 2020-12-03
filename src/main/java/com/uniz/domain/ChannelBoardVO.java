package com.uniz.domain;

import java.util.Date;

import lombok.Data;

@Data
public class ChannelBoardVO {
	
	private Long postSN;
	private Long channelSN;
	private Long userSN;
	private String postContent;
	private String channelTitle;
	private String title;
	private String nick;
	private int viewCnt;
	private int likeCnt;
	private Date createDateTime;
	private Date updateDateTime;
	private int replyCnt;
	
}
