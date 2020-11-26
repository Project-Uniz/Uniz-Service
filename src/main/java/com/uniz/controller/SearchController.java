package com.uniz.controller;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.uniz.domain.MenuType;
import com.uniz.domain.SearchResult;
import com.uniz.domain.UnizVO;
import com.uniz.domain.VideoDataListResult;
import com.uniz.service.SearchService;
import com.uniz.service.UnizService;

import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/search/*")
@AllArgsConstructor
public class SearchController {

	@Setter(onMethod_ = @Autowired)
	private UnizService unizService;

	@Setter(onMethod_ = @Autowired)
	private SearchService searchService;


	@GetMapping(value="/index")
	public void index(Locale locale, Model model) {

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate );

		return;
	}

	@GetMapping(value="/list",
			produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<SearchResult> list(@RequestParam("keyword") List<String> keyword, Long userSN) {

		log.info("search/list.....");

		// 1. 내 검색 옵션 리스트를 기반으로 서치유니즈 리스트를 가져온다 
		List<UnizVO> searchUnizList = searchService.getSearchUnizList(userSN);

		// 2. 서치 유니즈 리스트를 통해 비디오 데이터 검색
		List<VideoDataListResult> resultVideos = searchService.getSearchResult(
											keyword.stream()
												.map(String::toUpperCase)
												.collect(Collectors.toList()),
											searchUnizList
										);

		SearchResult searchResult = new SearchResult(keyword, resultVideos);
		// 3. 결과 반환
		return new ResponseEntity<>(searchResult, HttpStatus.OK);
		// model.addAttribute("searchResult", mapResult);
	}

	@GetMapping(value = "/getUnitag",
			produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List<UnizVO>> getUnitagList(String keyword, Long userSN) {

		log.info("getUnitagList for search : " + keyword);

		List<UnizVO> list = null;

		if( userSN == null || userSN == 0) {
			// 비로그인 상태
			list = unizService.getPresetList(MenuType.SEARCH.getMenu());
		} else {
			// 로그인 상태 TODO 바꿔야함 
			list = unizService.getPresetList(MenuType.SEARCH.getMenu());
		}

//		System.out.println("list : " + list);

		return new ResponseEntity<>(list, HttpStatus.OK);
	}


	@GetMapping(value = "/getOptions",
			produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Map<Integer, String>> getOptions(Long userSN) {

		log.info("search getOptionList : " + userSN);

		return new ResponseEntity<>(searchService.getOptionNameMap(userSN), HttpStatus.OK);
	}


	@PostMapping(value = "/setOptions",
			produces = {
					MediaType.APPLICATION_JSON_UTF8_VALUE
				})
	public ResponseEntity<Map<Integer, String>> setOptions( @RequestParam("options") ArrayList<Integer> options, @RequestParam("userSN") Long userSN) {
		log.info("options: " + options);
		Map<Integer, String> map = new HashMap<>();
		if (userSN != null) {
			map = searchService.setOptions(userSN, options);
			log.info("setOptions map............. : " + map);
		}

		return map.size() > 0
				? new ResponseEntity<>(map, HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
}