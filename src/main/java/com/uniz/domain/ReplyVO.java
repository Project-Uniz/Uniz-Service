package com.uniz.domain;

import java.util.Date;

import lombok.Data;

@Data
public class ReplyVO {
	
	private Long replySN;
	private Long userSN;
	private Long postSN;
	private String replyContent;
	private String nick;
	private Date createdatetime;
	
}
