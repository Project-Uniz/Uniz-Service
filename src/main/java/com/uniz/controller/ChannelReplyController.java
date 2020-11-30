package com.uniz.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.bind.annotation.ResponseBody;

import com.uniz.domain.ChannelReplyVO;
import com.uniz.domain.Criteria;

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

	@RequestMapping(value="/add")
	@ResponseBody
	public int register(@RequestParam Long postSN, @RequestParam Long userSN,
			 @RequestParam String replyContent	) {
		
		ChannelReplyVO vo = new ChannelReplyVO();
		vo.setUserSN(userSN);
		vo.setPostSN(postSN);
		vo.setReplyContent(replyContent);
		
		
		return service.register(vo);
	}
	
	//댓글 삭제
	@RequestMapping("/delete/{replySN}")
	@ResponseBody
	public int remove(@PathVariable("replySN") Long replySN){
		
		log.info("댓글 삭제 : " + replySN);
		return service.delete(replySN);

		
	}
	//댓글 수정
	@RequestMapping(method = { RequestMethod.PUT, RequestMethod.PATCH },

			value = "/update/{replySN}",

			consumes = "application/json",
			produces = { MediaType.TEXT_PLAIN_VALUE })
	public ResponseEntity<String> modify(@RequestBody ChannelReplyVO vo, @PathVariable("replySN") Long replySN){
		
		vo.setReplySN(replySN);
		log.info("수정 될 댓글 번호 : " + replySN);
		log.info("수정될 내용: " + vo);
		
		return service.modify(vo) == 1 ? new ResponseEntity<>("success" , HttpStatus.OK)

				   : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
		
	
	@RequestMapping("/page/{postSN}/{page}")
	@ResponseBody
	public List<ChannelReplyVO> getList(@PathVariable("page") int page, @PathVariable("postSN") Long postSN, Model model){
		
		
		Criteria cri = new Criteria(page, 10);

		return service.getList(cri, postSN);
		
	}
	

}
