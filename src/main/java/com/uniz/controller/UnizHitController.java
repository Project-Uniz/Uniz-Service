package com.uniz.controller;


import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.uniz.domain.VideoDataVO;
import com.uniz.service.UnizHitService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/UnizHit/*")
@AllArgsConstructor
public class UnizHitController {
	//2차테스트 
	
	private UnizHitService service;
	//private UserService userService;
	
	@GetMapping(value = "/Hitlist",
				produces = {MediaType.APPLICATION_JSON_UTF8_VALUE}
						   )
	//video 리스트 가져오기.(JSON형식)
	public ResponseEntity<List<VideoDataVO>>getHitList() {
		log.info("JSON확인");
		return new ResponseEntity<List<VideoDataVO>>(service.getHitList(),HttpStatus.OK);
		
	}
	
	@GetMapping("/UnizHit")
	public String unizHitView(Model model) {
		
		return "UnizHit/unizHitMain";
	}
	
	@GetMapping("/UnizHit/{videoSn}")
	public String videoList(@PathVariable long videoSn, Model model) {
		log.info("videoSn : " + videoSn);
		
		VideoDataVO videoVO= service.getVideo(videoSn);
		
		log.info("videoVO : " + videoVO); 
		
		model.addAttribute("videoData", videoVO);
		
		return "videoDetail";
	}
		
	
		//0.로그인 여부는 당장 상관없음.(추후고려)

		//1.상단 태그를 가져와서 카테고리 별로 보여줘야한다.
		
		//2.키워드별로 세부 카테고리를 정한다. 
		//2-1.uniTag 14개 사용 하여 관련된 영상을 분류 ex)스포츠, 영화 , 드라마, 동물.. 등 
		//2-2.세부 유니즈 설정은 2차 
		
		//3.영상 정렬 기준은 Likecnt + viewcnt를 하여 높은순으로 정렬하여 보여준다.  
		
		
	
	
}
