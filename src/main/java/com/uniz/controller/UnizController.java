package com.uniz.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uniz.domain.UnizVO;
import com.uniz.service.UnizService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@RequestMapping("/uniz/")
@RestController
@Log4j
@AllArgsConstructor
public class UnizController {
	private UnizService service;
	
	@PostMapping(value = "/register",
			produces = {
					MediaType.APPLICATION_XML_VALUE,
					MediaType.APPLICATION_JSON_UTF8_VALUE
				})
	public ResponseEntity<UnizVO> create(String keyWord) {
		log.info("keyWord: " + keyWord);
		
		boolean result = false;
		
		UnizVO uniz = null;
		
		/*
		// 1. 기존 uniz 중에 있는지 검사
		UnizVO uniz = service.findByKeyword(keyWord); // -> 어느 유니즈를 명확히 찾을건지 정해야함
		
		// 2. 없으면 생성
		if (uniz == null) {			
			result = service.registerSelectKey(
						UnizVO.builder()
							.unizKeyword(keyWord)
							.unizTypeSN(8)
							.build()
					);
			// log.info("Uniz Insert Count: " + insertCnt);
			uniz = service.findByKeyword(keyWord);
		}
		// 3. 유니즈 정보 반환

		 */
				
		return result == true
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
}