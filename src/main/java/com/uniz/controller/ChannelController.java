package com.uniz.controller;

import java.util.List;

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

import com.uniz.domain.ChannelBoardVO;
import com.uniz.service.ChannelService;

import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RequestMapping("/channel/*")
@RestController
@Log4j
@AllArgsConstructor
public class ChannelController {
	
	@Setter(onMethod_ =@Autowired)
	private ChannelService service;
	
	
	// 채널  목록 보여줌
	@GetMapping(value= "/list" ,
	produces = { MediaType.APPLICATION_XML_VALUE,
				 MediaType.APPLICATION_JSON_UTF8_VALUE})
	public ResponseEntity<List<ChannelBoardVO>> getChannelList(){
		log.info("get Channel List........");
		return new ResponseEntity<>(service.getChannelList() , HttpStatus.OK);
	}
	
	// 채널 별 게시글 목록 보여줌
	@GetMapping(value ="/list/{channelSN}",
			produces = { MediaType.APPLICATION_XML_VALUE,
					 MediaType.APPLICATION_JSON_UTF8_VALUE})
	public ResponseEntity<List<ChannelBoardVO>>getPostList(@PathVariable("channelSN") Long channelSN ){
		log.info("get Post List.....");
		return new ResponseEntity<>(service.getPostList(channelSN), HttpStatus.OK);
	}
	
	// 채널 게시글 작성
	@PostMapping(value= "/new",
				consumes = "application/json",
				produces = { MediaType.TEXT_PLAIN_VALUE })
	public ResponseEntity<String> create(@RequestBody ChannelBoardVO vo) {
		
		log.info("BoardVO : " + vo);
		
		int insertCount = service.register(vo);
		
		return insertCount == 1 ?
								new ResponseEntity<>("success", HttpStatus.OK) :
								new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	// 게시글 조회
	@GetMapping(value ="/{postSN}" , produces = { MediaType.APPLICATION_XML_VALUE,
					 MediaType.APPLICATION_JSON_UTF8_VALUE} )
	public ResponseEntity<List<ChannelBoardVO>> get(@PathVariable("postSN") Long postSN){
		return new ResponseEntity<>(service.getPost(postSN) , HttpStatus.OK);
	}
	
	//게시글 삭제
	@DeleteMapping(value ="/{postSN}" ,
			produces = { MediaType.TEXT_PLAIN_VALUE })
	public ResponseEntity<String> remove(@PathVariable("postSN") Long postSN){
		
		log.info("게시물 삭제 : " + postSN);
		
		return service.delete(postSN) == 1 ? new ResponseEntity<>("success", HttpStatus.OK) 
										   : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	
	//게시글 수정
	@RequestMapping(method = { RequestMethod.PUT , RequestMethod.PATCH } ,
			value = "/{postSN}",
			consumes = "application/json",
			produces = { MediaType.TEXT_PLAIN_VALUE } )
	public ResponseEntity<String> modify(@RequestBody ChannelBoardVO vo , @PathVariable("postSN") Long postSN){
		
		vo.setPostSN(postSN);
		
		log.info("수정 내용 : " + vo);
		
		return service.update(vo) == 1 ? new ResponseEntity<>("success" , HttpStatus.OK)
									   : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	
}
