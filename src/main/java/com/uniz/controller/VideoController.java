package com.uniz.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.uniz.domain.UserDTO;
import com.uniz.domain.VideoDataVO;
import com.uniz.service.VideoService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
@Controller
@AllArgsConstructor
public class VideoController {

	private VideoService service;
	
	//videoList에서 썸네일을 클릭하면 이동하는 해당동영상 재상페이지
	@GetMapping("/video/{videoSn}")
	public String videoList(@PathVariable long videoSn, Model model,HttpSession session) {
		log.info("videoSn : " + videoSn);
		
		UserDTO userDto= (UserDTO)session.getAttribute("user");
		Long userSN = userDto.getUserSN();
		
		VideoDataVO videoVO= service.getVideo(videoSn, userSN);
		
		model.addAttribute("videoData", videoVO);
		
		return "videoDetail";
	}
	
}