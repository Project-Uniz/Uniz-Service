package com.uniz.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.uniz.domain.Criteria;
import com.uniz.domain.VideoReplyVO;
import com.uniz.service.VideoReplyService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@RequestMapping("/unizReply/")
@RestController
@Log4j
@AllArgsConstructor
public class VideoReplyController {

	private VideoReplyService service;
	
	@PostMapping(value = "/new",
			consumes = "application/json",
			produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> create(@RequestBody VideoReplyVO vo){
		
		log.info("ReplyVO :"+vo);
		
		int insertCount = service.register(vo);
		
		log.info("Reply INSERT COUNT: " + insertCount);
		
		return insertCount == 1
				? new ResponseEntity<>("success", HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
				//삼항 연산자 처리 
	}
	
	@GetMapping(value = "/pages/{videoSN}/{page}",
				produces = {
						MediaType.APPLICATION_XML_VALUE,
						MediaType.APPLICATION_JSON_UTF8_VALUE
				})
	public ResponseEntity<List<VideoReplyVO>> getList(
				@PathVariable("page") int page,
				@PathVariable("videoSN") Long videoSN){
		
			log.info("getList========================");
			Criteria cri = new Criteria(page, 10);
			log.info(cri);
			
			return new ResponseEntity<>(service.getList(cri, videoSN),HttpStatus.OK);
	}
	
	@GetMapping(value = "/{replySN}",
			produces = {
					MediaType.APPLICATION_XML_VALUE,
					MediaType.APPLICATION_JSON_UTF8_VALUE
			})
	public ResponseEntity<VideoReplyVO> get(@PathVariable("replySN") Long replySN){
		
		log.info("get: " + replySN);
		
		return new ResponseEntity<>(service.get(replySN), HttpStatus.OK);	
	}
	
	@DeleteMapping(value= "/{replySN}", produces = {MediaType.TEXT_PLAIN_VALUE })
	public ResponseEntity<String> remove(@PathVariable("replySN") Long replySN){
		
		log.info("remove : " + replySN);
		
		return service.remove(replySN) == 1
				? new ResponseEntity<>("SUCCESS", HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	
	@RequestMapping(method = {RequestMethod.PUT, RequestMethod.PATCH},
			value = "/{replySN}",
			consumes = "application/json",
			produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> modify(
							@RequestBody VideoReplyVO vo,
							@PathVariable("replySN") Long replySN){
		
		vo.setReplySN(replySN);
		
		log.info("replySN : " + replySN);
		
		log.info("modify : " + vo);
		
		return service.modify(vo) == 1
				? new ResponseEntity<>("SUCCESS", HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
			
			
			
			
	
}
