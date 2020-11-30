package com.uniz.controller;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.uniz.domain.ReplyPageDTO;
import com.uniz.domain.ReplyVO;
import com.uniz.service.ReplyService;

import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RequestMapping("/replies/")
@RestController
@Log4j
@AllArgsConstructor
public class ReplyController {
	
	@Setter(onMethod_ = @Autowired)
	private ReplyService service;
	
	//댓글 추가

	@PostMapping(value = "/new",
			consumes = "application/json", 
			produces = { MediaType.TEXT_PLAIN_VALUE })

	public ResponseEntity<String> create(@RequestBody ReplyVO vo){
		
		int insertCount = service.register(vo);
		return insertCount == 1 ? new ResponseEntity<>("success" , HttpStatus.OK)
					      		: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	
	//해당 글의 댓글들 보여줌
	@GetMapping(value = "/pages/{postSN}/{page}" ,
			produces = {
					MediaType.APPLICATION_XML_VALUE,
					MediaType.APPLICATION_JSON_UTF8_VALUE })
	public ResponseEntity<ReplyPageDTO> getList( @PathVariable("page") int page, @PathVariable("postSN") Long postSN ){
		
		log.info("test-------------");
		Criteria cri = new Criteria(page, 10);
		
		return new ResponseEntity<>(service.getListPage(cri, postSN), HttpStatus.OK);
		
	}
	
	//댓글 상세
	@GetMapping(value = "/{replySN}",
				produces = { MediaType.APPLICATION_XML_VALUE,
							 MediaType.APPLICATION_JSON_UTF8_VALUE })
	public ResponseEntity<ReplyVO> get(@PathVariable("replySN") Long replySN){
		
		return new ResponseEntity<>(service.get(replySN), HttpStatus.OK);
		
	}
	
	// 댓글 삭제
	@DeleteMapping(value = "/{replySN}", produces = { MediaType.TEXT_PLAIN_VALUE })
	public ResponseEntity<String> remove(@PathVariable("replySN") Long replySN){
		
		log.info("댓글 삭제 : " + replySN);
		return service.remove(replySN) == 1 ? new ResponseEntity<>("success" , HttpStatus.OK)
											: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	//댓글 수정
	@RequestMapping(method = { RequestMethod.PUT, RequestMethod.PATCH },
			value = "/{replySN}",
			consumes = "application/json",
			produces = { MediaType.TEXT_PLAIN_VALUE })
	public ResponseEntity<String> modify(@RequestBody ReplyVO vo, @PathVariable("replySN") Long replySN){
		
		vo.setReplySN(replySN);
		log.info("수정 될 댓글 번호 : " + replySN);
		log.info("수정될 내용: " + vo);
		
		return service.modify(vo) == 1 ? new ResponseEntity<>("success" , HttpStatus.OK)
									   : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	
}
