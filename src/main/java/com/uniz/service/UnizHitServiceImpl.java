package com.uniz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniz.domain.MyUnizPoint;
import com.uniz.domain.UnizLayerListVO;
import com.uniz.domain.UnizVO;
import com.uniz.domain.VideoDataVO;
import com.uniz.mapper.UnizHitMapper;
import com.uniz.mapper.UnizMapper;
import com.uniz.mapper.UnizPointMapper;
import com.uniz.mapper.UserMapper;
import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
@AllArgsConstructor
public class UnizHitServiceImpl implements UnizHitService {

	@Setter(onMethod_ = @Autowired)
	private UnizHitMapper mapper;
	
	@Setter(onMethod_ = @Autowired)
	private UnizMapper unizMapper;
	
	@Setter(onMethod_ = @Autowired)
	private UserMapper userMapper;
	
	@Setter(onMethod_ = @Autowired)
	private UnizPointMapper unizPointMapper;
	
	@Setter(onMethod_ = @Autowired)
	private UnizPointService unizPointService;
	
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
		Long utbCateSN = videoVO.getUtbCateSN();
		
		Long parentUniz = unizMapper.findParentUniz(utbCateSN);
		
		videoVO.setUtbCateSN(parentUniz);
		
		return videoVO;
	}

	@Override
	public List<VideoDataVO> keywordHitList(Long unizSN) {
		
		List<UnizLayerListVO> unizSN2 = mapper.keywordUniz(unizSN);
		
		log.info("get keyword List" + unizSN);
	
		return mapper.keywordHitList(unizSN2);
		
		
	}

}