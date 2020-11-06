package com.uniz.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class VideoDataVO {

	private Long	videoSN;
	private String	title;
	private String	authorID;
	private String	authorNick;
	private String	urlPath;
	private String	thumbUrl;
	
	private Long	likeCnt;
	private Long	followCnt;
	private int		utbCateSN;	
	private String	utbVideoID;	

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
	private Date createDateTime;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
	private Date updateDateTime;
}
