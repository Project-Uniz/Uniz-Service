package com.uniz.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.uniz.domain.UnizTypeEnum;
import com.uniz.domain.UnizVO;
import com.uniz.service.UnizPointService;
import com.uniz.service.UnizService;

import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RequestMapping("/uniz/")
@RestController
@Log4j
@AllArgsConstructor
public class UnizController {
	@Setter(onMethod_ = @Autowired)
	private UnizService service;

	@Setter(onMethod_ = @Autowired)
	private UnizPointService upsvc;

	@PostMapping(value = "/register",
			produces = {
					MediaType.APPLICATION_XML_VALUE,
					MediaType.APPLICATION_JSON_UTF8_VALUE
				})
	public ResponseEntity<UnizVO> create(String keyWord) {
		log.info("keyWord: " + keyWord);

		UnizVO uniz = null;

		keyWord = keyWord.trim();

		// 1. 기존 uniz 중에 있는지 검사
		//int[] rangeType = new int[] {
		//		UnizTypeVO.MAINGROUP.getTypeSN(),
		//		UnizTypeVO.USERMADE.getTypeSN(),
		//		UnizTypeVO.COMMADE.getTypeSN()
		//	};

		uniz = service.findByKeywordForUserUnizInsert(keyWord);

		// 2. 없으면 생성
		if (uniz == null || uniz.isEnable() == false) {

			// 2-1. 사용자 등록 유니즈로 등록
			UnizVO newUniz = UnizVO.builder()
					.unizKeyword(keyWord)
					.unizTypeSN(UnizTypeEnum.USERMADE.getTypeSN())
					.build();

			service.registerSelectKey(newUniz);
			uniz = service.get(newUniz.getUnizSN());
		}

		// 3. 유니즈 정보 반환
		System.out.println(uniz);

		return uniz != null
				? new ResponseEntity<>(uniz, HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@GetMapping(value = "/getPreset",
			produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List<UnizVO>> getList(Integer menu ) {

		log.info("get preset List menu: " + menu);

		List<UnizVO> list = service.getPresetList(menu);

		System.out.println("list : " + list);

		return new ResponseEntity<>(list, HttpStatus.OK);
	}


	@GetMapping(value = "/getUnizNames",
			produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Map<Long, String>> getNameList(@RequestParam("unizSN") Long[] unizSNList ) {

		log.info("get UnizNames List : " + unizSNList);

		Map<Long, String> map = new HashMap<>();

		for (Long unizSN : unizSNList) {
			UnizVO uniz = service.get(unizSN);
			if(uniz == null || uniz.isEnable() == false ) {
				continue;
			}
			map.put(unizSN, uniz.getUnizKeyword());
		}

		System.out.println("map : " + map);

		return new ResponseEntity<>(map, HttpStatus.OK);
	}	

	@GetMapping(value = "/getFavoriteList",
			produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List<UnizVO>> getFavoriteList(@RequestParam("userSN") Long userSN, @RequestParam("limit") int limit ) {

		log.info("getFavoriteList : " + userSN + "/" + limit);

		// TODO : 선호 유니즈 대상 기준 포인트값 어드민 설정 가능하도록 변경
		int favoritePoint = 100;
		List<UnizVO> list = service.getFavoriteList(userSN, favoritePoint, limit);

		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	@PostMapping(value = "/mylike",
			produces = {
					MediaType.APPLICATION_XML_VALUE,
					MediaType.APPLICATION_JSON_UTF8_VALUE
				})
	public ResponseEntity<List<Long>> myLike(@RequestParam("userSN") Long userSN, @RequestParam("unizSN") List<Long> unizSNList) {

		// TODO : 좋아요 유니즈 선택시 지정값 어드민에서 설정 가능하도록 변경
		int default_like_point = 100;
		
		List<Long> result = null;
		if (upsvc.registUnizPoint(userSN, unizSNList, default_like_point)) {
			result = unizSNList;
		}

		return result != null
				? new ResponseEntity<>(result, HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@PostMapping(value = "/mydislike",
			produces = {
					MediaType.APPLICATION_XML_VALUE,
					MediaType.APPLICATION_JSON_UTF8_VALUE
				})
	public ResponseEntity<List<Long>> myDislike(@RequestParam("userSN") Long userSN, @RequestParam("unizSN") List<Long> unizSNList) {

		// TODO : 싫어요 유니즈 선택시 지정값 어드민에서 설정 가능하도록 변경
		int default_dislike_point = 30;

		List<Long> result = null;
		if (upsvc.registUnizPoint(userSN, unizSNList, default_dislike_point)) {
			result = unizSNList;
		}

		return result != null
				? new ResponseEntity<>(result, HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@PostMapping(value = "/positiveFeed",
			produces = {
					MediaType.APPLICATION_XML_VALUE,
					MediaType.APPLICATION_JSON_UTF8_VALUE
				})
	public ResponseEntity<List<Long>> positiveFeed(@RequestParam("userSN") Long userSN, @RequestParam("unizSN") List<Long> unizSNList) {

		// TODO 1 : 긍정 피드 증가값 어드민에서 설정 가능하도록 변경
		// TODO 2 : 긍정 피드 증가값에 대한 계산식 정의 필요
		int default_like_point = 1;
		
		List<Long> result = null;
		if (upsvc.incMyUnizPoint(userSN, unizSNList, default_like_point)) {
			result = unizSNList;
		}

		return result != null
				? new ResponseEntity<>(result, HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@PostMapping(value = "/negativeFeed",
			produces = {
					MediaType.APPLICATION_XML_VALUE,
					MediaType.APPLICATION_JSON_UTF8_VALUE
				})
	public ResponseEntity<List<Long>> negativeFeed(@RequestParam("userSN") Long userSN, @RequestParam("unizSN") List<Long> unizSNList) {

		// TODO 1 : 부정 피드 증가값 어드민에서 설정 가능하도록 변경
		// TODO 2 : 부정 피드 증가값에 대한 계산식 정의 필요
		int default_dislike_point = 1;

		List<Long> result = null;
		if (upsvc.decMyUnizPoint(userSN, unizSNList, default_dislike_point)) {
			result = unizSNList;
		}

		return result != null
				? new ResponseEntity<>(result, HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@PostMapping(value = "/recoverFeed",
			produces = {
					MediaType.APPLICATION_XML_VALUE,
					MediaType.APPLICATION_JSON_UTF8_VALUE
				})
	public ResponseEntity<List<Long>> recoverFeed(@RequestParam("userSN") Long userSN, @RequestParam("unizSN") List<Long> unizSNList) {

		// TODO 1 : 부정 피드 복구값 어드민에서 설정 가능하도록 변경
		// TODO 2 : 부정 피드 복구값에 대한 계산식 정의 필요
		int default_recover_point = 1;

		List<Long> result = null;
		if (upsvc.incMyUnizPoint(userSN, unizSNList, default_recover_point)) {
			result = unizSNList;
		}

		return result != null
				? new ResponseEntity<>(result, HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

}