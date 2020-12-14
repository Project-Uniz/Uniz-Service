package com.uniz.service;

import javax.servlet.http.HttpSession;

import com.uniz.domain.VideoDataVO;

public interface VideoService {
	public VideoDataVO getVideo(long videoSn, Long userSN);
}
