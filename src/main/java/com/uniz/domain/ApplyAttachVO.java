package com.uniz.domain;

import lombok.Data;

@Data
public class ApplyAttachVO {
	
	private String uuid;
	private String uploadPath;
	private String fileName;
	private boolean fileType;
	private Long userSN;
	private Long applySN;
	
}
