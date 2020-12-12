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

	private int videoSN; //비디오번호
	private String title; //제목
	private String authorNick; //게시자 닉네임
	private String description; // 본문
	private String urlPath; // 유튜브URL
	private String thumbUrl; //썸네일URL
	private int likeCnt; //좋아요수
	private int dislikeCnt; //싫어요 수
	private int followCnt; //팔로워수
	private int viewCnt; // 조회수
	private int duration; //영상시간
	private String utbCateGory; //유튜브 카테고리명
	private Long utbCateSN; //유튜브 번호
	private String isFamilyFriendly; //모름
	private String titleHashTags; //타이틀 해시태크
	private String descHashTags; //;
	private String utbVideoID; //영상아아디ㅣ
	private String authorId; //게시자 아이디
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
	private Date yUploadDateTime; //게시글작성일
	private String keywords;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
	private Date createDateTime; //생성일
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
	private Date updateDateTime; //변경일
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		VideoDataVO other = (VideoDataVO) obj;
		if (urlPath == null) {
			if (other.urlPath != null)
				return false;
		} else if (!urlPath.equals(other.urlPath))
			return false;
		if (utbCateSN == null) {
			if (other.utbCateSN != null)
				return false;
		} else if (!utbCateSN.equals(other.utbCateSN))
			return false;
		if (utbVideoID == null) {
			if (other.utbVideoID != null)
				return false;
		} else if (!utbVideoID.equals(other.utbVideoID))
			return false;
		return true;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((urlPath == null) ? 0 : urlPath.hashCode());
		result = prime * result + ((utbCateSN == null) ? 0 : utbCateSN.hashCode());
		result = prime * result + ((utbVideoID == null) ? 0 : utbVideoID.hashCode());
		return result;
	}




}
