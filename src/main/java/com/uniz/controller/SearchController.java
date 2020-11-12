package com.uniz.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/search/*")
@AllArgsConstructor
public class SearchController {

	//private UnizService unizService;
	//private UserService userService;
	
	@GetMapping("/list")
	public void list(Model model, String keyword, Long userSN) {
		
		log.info("search/list.....");
		
		// 1. 내 검색 옵션 리스트를 가져온다 
		// - List<Uniz> unizOptionList = userService.getSearchOptions(userSN);
		
		// 2. 옵션별로 키워드로 검색한다.
		// - HashMap<키워드, 리스트> mapResult  
		// - key option in unizOptionList
		// - List<VideoData> resultVideos = unizService.getSearchedList(keyword, option);
		// - mapResult.put(option, resultVideos);

		// 3. 결과 반환
		// model.addAttribute("searchResult", mapResult);
	}
}
