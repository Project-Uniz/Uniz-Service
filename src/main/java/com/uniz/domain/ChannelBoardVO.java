package com.uniz.domain;

import java.util.Date;

import lombok.Data;

@Data
public class ChannelBoardVO {
	
	private Long postSN;
	private Long channelSN;
	private Long userSN;
	private String postContent;
	private String title;
	private int viewCnt;
	private int likeCnt;
	private Date createDateTime;
	private Date updateDateTime;
	
}
