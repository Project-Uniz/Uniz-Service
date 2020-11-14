package com.uniz.domain;

import java.util.Date;

import lombok.Data;

@Data
public class BoardVO {
	
	private Long postSN;
	private Long replySN;
	private String title;
	private String postContent;
	private Long userSN;
	private Date createDateTime;
	private Date updateDateTime;
	private String nick;
	
}
