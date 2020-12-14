package com.uniz.domain;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class ApplyVO {
	
	private Long applySN;
	private Long userSN;
	private int userType;
	private String channelTitle;
	private String category;
	private String email;
	private Date createDateTime;
	private Date updateDateTime;
	
	private List<ApplyAttachVO> attachList;
	
}
