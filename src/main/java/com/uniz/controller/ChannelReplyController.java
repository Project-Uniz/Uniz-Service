package com.uniz.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.uniz.domain.ChannelReplyVO;
import com.uniz.domain.Criteria;
import com.uniz.domain.ReplyVO;
import com.uniz.mapper.ChannelReplyMapper;
import com.uniz.service.ChannelReplyService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@RequestMapping("/chreplies/*")
@Controller
@Log4j
@AllArgsConstructor
public class ChannelReplyController {
		
	private ChannelReplyService service;
	
	
	//댓글 추가
	@PostMapping(value="/add" ,
			consumes = "application/json",
			produces = { MediaType.TEXT_PLAIN_VALUE })
	public ResponseEntity<String> register(@RequestBody  ChannelReplyVO vo ) {
			
		int registerCount = service.register(vo);
		
		
		return registerCount == 1 ? new ResponseEntity<>("success" , HttpStatus.OK)
								  : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	//댓글 삭제
	@DeleteMapping(value = "/{replySN}", produces = { MediaType.TEXT_PLAIN_VALUE })
	public ResponseEntity<String> remove(@PathVariable("replySN") Long replySN){
		
		log.info("댓글 삭제 : " + replySN);
		return service.delete(replySN) == 1 ? new ResponseEntity<>("success" , HttpStatus.OK)
											: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	//댓글 수정
	@RequestMapping(method = { RequestMethod.PUT, RequestMethod.PATCH },
			value = "/{replySN}",
			consumes = "application/json",
			produces = { MediaType.TEXT_PLAIN_VALUE })
	public ResponseEntity<String> modify(@RequestBody ChannelReplyVO vo, @PathVariable("replySN") Long replySN){
		
		vo.setReplySN(replySN);
		log.info("수정 될 댓글 번호 : " + replySN);
		log.info("수정될 내용: " + vo);
		
		return service.modify(vo) == 1 ? new ResponseEntity<>("success" , HttpStatus.OK)
									   : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	
	//댓글 상세
	@GetMapping(value= "/{replySN}",
			produces = {
					MediaType.APPLICATION_XML_VALUE,
					MediaType.APPLICATION_JSON_UTF8_VALUE })
	public ResponseEntity<ChannelReplyVO> get(@PathVariable("replySN") Long replySN){
		
		return new ResponseEntity<>(service.get(replySN), HttpStatus.OK);
		
	}
	
	@GetMapping(value= "/page/{postSN}/{page}",
			produces = {
					MediaType.APPLICATION_XML_VALUE,
					MediaType.APPLICATION_JSON_UTF8_VALUE })
	public ResponseEntity<List<ChannelReplyVO>> getList(@PathVariable("page") int page,
														@PathVariable("postSN") Long postSN){
		
		Criteria cri = new Criteria(page , 10);
		return new ResponseEntity<>(service.getList(cri, postSN) , HttpStatus.OK);
		
	}

}
