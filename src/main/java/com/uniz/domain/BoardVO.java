package com.uniz.domain;

import java.util.Date;


import lombok.Data;

@Data
public class BoardVO {
	
	private Long postSN;
	private Long boardSN;
	private Long replySN;
	private Long userSN;
	private String boardTitle;
	private String boardComment;
	private String title;
	private String nick;
	private String postContent;
	private Date createDateTime;
	private Date updateDateTime;
	private int replyCnt;
	
}
