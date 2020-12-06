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
	private String	authorNick;
	private String  searchKeyword;
	private String	urlPath;
	private String	thumbUrl;
	
	private Long	likeCnt;
	private Long    dislikeCnt;
	private Long	followCnt;
	private Long	viewCnt;
	private Long    duration;
	
	private String  utbCategory;
	private int		utbCateSN;
	private boolean isFamilyFriendly;
	private String  titleHashTags;
	private String  descHashTags;
	private String	utbVideoID;
	private String  authorID;
	private String  keywords;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
	private Date yUploadDateTime;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
	private Date createDateTime;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
	private Date updateDateTime;

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
		if (utbCateSN != other.utbCateSN)
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
		result = prime * result + utbCateSN;
		result = prime * result + ((utbVideoID == null) ? 0 : utbVideoID.hashCode());
		return result;
	}

}
