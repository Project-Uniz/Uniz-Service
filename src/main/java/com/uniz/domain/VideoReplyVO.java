package com.uniz.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class VideoReplyVO {
	
	private Long replySN;
	private Long userSN;
	private Long videoSN;
	private String replyContent;
	private String nick;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
	private Date createDateTime;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
	private Date updateDateTime;
	
	
	

}
