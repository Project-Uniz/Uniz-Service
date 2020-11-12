package com.uniz.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.uniz.domain.VideoDataVO;

public interface VideoMapper {

	public List<VideoDataVO> getVideoList(@Param("unizSN") Long unizSN);
}
