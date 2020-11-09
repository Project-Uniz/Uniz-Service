package com.uniz.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.uniz.domain.MenuType;
import com.uniz.domain.UnizTypeEnum;
import com.uniz.domain.UnizVO;
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

	@GetMapping("/list")
	public void list(String keyword, Long userSN) {

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

		Map<Integer, String> map = new HashMap<>();

		Integer optionValue = searchService.getOptions(userSN);
		
		if (optionValue != null) {
			UnizTypeEnum[] opts = UnizTypeEnum.values();
			for( int i=1 ; i<opts.length ; i++) {
				if (optionValue == null || optionValue == 0) {
					break;
				} else if ((optionValue & 1) == 1 ) {
					map.put(opts[i].getTypeSN(), opts[i].getTypeNameKR());
				}
				optionValue >>>= 1;
			}
		}
		
		return new ResponseEntity<>(map, HttpStatus.OK);
	}


	@PostMapping(value = "/setOptions",
			produces = {
					MediaType.APPLICATION_JSON_UTF8_VALUE
				})
	public ResponseEntity<Map<Integer, String>> create(Integer[] options, Long userSN) {
		log.info("options: " + options);

		Map<Integer, String> map = new HashMap<>();

		int optionValue = 0;
		for (Integer optNum : options) {
			UnizTypeEnum opt = UnizTypeEnum.valueOf(optNum);
			if (opt != null) {
				map.put(opt.getTypeSN(), opt.getTypeNameKR());
				optionValue += ( 1 << (opt.getTypeSN()-1) );
			}
		}

		if(map.size() > 0) {
			if ( searchService.getOptions(userSN) == null ) {
				searchService.makeOptions(userSN, optionValue);
			} else {
				searchService.setOptions(userSN, optionValue);
			}
		}

		System.out.println(map);

		return map.size() > 0
				? new ResponseEntity<>(map, HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
