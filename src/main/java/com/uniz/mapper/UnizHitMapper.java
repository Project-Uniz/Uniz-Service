package com.uniz.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.uniz.domain.UnizLayerListVO;
import com.uniz.domain.UnizVO;
import com.uniz.domain.VideoDataVO;

public interface UnizHitMapper {

	public VideoDataVO read(Long videoSN);
	
	public List<VideoDataVO> getHitList();

	public VideoDataVO getVideo(long videoSn);
	
	public List<VideoDataVO> keywordHitList(
				@Param("unizSNlist") List<UnizLayerListVO> UnizLayerList);
	
	public List<UnizLayerListVO> keywordUniz(
			@Param("unizSN") Long unizSN);
}
