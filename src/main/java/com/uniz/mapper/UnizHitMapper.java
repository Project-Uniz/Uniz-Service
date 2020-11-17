package com.uniz.mapper;

import java.util.List;

import com.uniz.domain.UnizVO;
import com.uniz.domain.VideoDataVO;

public interface UnizHitMapper {

	public VideoDataVO read(Long videoSN);
	
	public List<VideoDataVO> getHitList();

	public VideoDataVO getVideo(long videoSn);
}
