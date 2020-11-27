package com.uniz.domain;

import java.util.Date;

import lombok.Data;

@Data
public class ChannelReplyVO {
	
	private Long replySN;
	private Long userSN;
	private Long postSN;
	private String nick;
	private String replyContent;
	private Date createDateTime;
	private Date updateDateTime;
}
