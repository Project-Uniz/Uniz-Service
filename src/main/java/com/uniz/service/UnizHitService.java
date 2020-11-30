package com.uniz.service;

import java.util.List;

import com.uniz.domain.UnizVO;
import com.uniz.domain.VideoDataVO;

public interface UnizHitService {
	
	public List<VideoDataVO> getHitList();

	public VideoDataVO getVideo(long videoSn);	
		
}
