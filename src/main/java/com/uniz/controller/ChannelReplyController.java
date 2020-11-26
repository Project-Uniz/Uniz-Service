package com.uniz.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.uniz.domain.ChannelReplyVO;
import com.uniz.domain.Criteria;
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
	private ChannelReplyMapper mapper;
	
	//댓글 추가
	@PostMapping("/add")
	public String register( ChannelReplyVO vo , Long postSN, RedirectAttributes rttr) {
			
		service.register(vo);
		rttr.addFlashAttribute("result", vo.getPostSN());
		
		return "redirect:/channel/get/" + vo.getPostSN();
		
	}
	//댓글 삭제
	@PostMapping(value="/remove")
	public String remove(@RequestParam("replySN") Long replySN, @RequestParam("postSN") Long postSN , RedirectAttributes rttr) {
		
		final boolean SUCCESS = service.delete(replySN);
		
		if(SUCCESS){
			rttr.addFlashAttribute("result", "success");
		}
		
		return "redirect:/channel/get/" + postSN;
		
	}
	//댓글 수정
	@PostMapping(value="/modify")
	public String modify(@RequestParam("replySN") Long replySN, 
						 @RequestParam("postSN") Long postSN, ChannelReplyVO vo, RedirectAttributes rttr) {
		
		final boolean SUCCESS = service.modify(vo);
		
		if(SUCCESS) {
			rttr.addFlashAttribute("resutl", "success");
		}
		return "redirect:/channel/get/" + postSN;
		
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
