package com.uniz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniz.domain.UnizVO;
import com.uniz.domain.VideoDataVO;
import com.uniz.mapper.UnizHitMapper;
import com.uniz.mapper.UnizMapper;

import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
@AllArgsConstructor
public class UnizHitServiceImpl implements UnizHitService {

	@Setter(onMethod_ = @Autowired)
	private UnizHitMapper mapper;

	@Override
	public List<VideoDataVO> getHitList() {
		log.info("getPresetList......." );

		return mapper.getHitList();
	}

	@Override
	public VideoDataVO getVideo(long videoSn) {
		
		log.info("getVideoInfo" + videoSn);
		
		VideoDataVO videoVO = mapper.getVideo(videoSn);
		
		String changeURL = videoVO.getUrlPath();
		
		int idx = changeURL.indexOf("=");
		
		videoVO.setUrlPath(changeURL.substring(idx+1));
		
		System.out.println(videoVO.getUrlPath());
		
		return videoVO;
	}
	

}