package com.uniz.controller;


import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.uniz.domain.BoardVO;
import com.uniz.domain.ChannelBoardVO;
import com.uniz.domain.ChannelPageDTO;
import com.uniz.domain.Criteria;
import com.uniz.domain.PageDTO;
import com.uniz.service.BoardService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/category/*")
@AllArgsConstructor
public class BoardController {
	
	private BoardService service;
	
	// 카테고리 별 게시판 메인으로 이동
	@GetMapping("/main")
	public String getMain(Model model) {
		return "category/main";
	}
	
	// 게시판 목록 보여줌
	@GetMapping(value = "/boardlist" ,
			produces = { MediaType.APPLICATION_XML_VALUE,
					 MediaType.APPLICATION_JSON_UTF8_VALUE})
	public ResponseEntity<List<BoardVO>> getBoardList(){
		log.info("get Board List........");
		return new ResponseEntity<>(service.getBoardList() , HttpStatus.OK);
	} 
	
	// 게시판 별 게시글 목록 보여줌
	@GetMapping(value = "/boardlist/{boardSN}/{page}",
			produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
	public ResponseEntity<PageDTO>getPostList(@PathVariable("boardSN") Long boardSN, @PathVariable("page") int page ){
		
		Criteria cri = new Criteria(page, 10);
		
		return new ResponseEntity<>(service.getPostListPaging(cri, boardSN), HttpStatus.OK);
		
	}
	
	// 게시글  전체목록 보여줌
	@GetMapping(value = "/list/all/{page}",
			produces = { MediaType.APPLICATION_XML_VALUE,
					 MediaType.APPLICATION_JSON_UTF8_VALUE})
	public ResponseEntity<PageDTO> getAllPost(@PathVariable("page") int page) {
		
		Criteria cri = new Criteria(page, 10);
		
		return new ResponseEntity<>(service.getListPage(cri), HttpStatus.OK);

	}
	
	// 게시판으로 이동
	@GetMapping("/board/{boardSN}")
	public String getBoard(@PathVariable("boardSN") Long boardSN , BoardVO vo, Model model) {
		
		model.addAttribute("board", vo);
		
		return "category/board";
		
	}
	
	
	@GetMapping("/register/{boardSN}")
	public String register(@PathVariable("boardSN")Long boardSN, Model model) {
		
		return "category/register";
		
	}
	
	@PostMapping("/register")
	public String register(BoardVO vo, Long boardSN ,RedirectAttributes rttr ) {
		//유효성 검사 서버에서 체크 할 수 있게
		service.register(vo);
		rttr.addFlashAttribute("result", vo.getPostSN());
		
		return "redirect:/category/board/" + vo.getBoardSN();
		
	}
	
	@GetMapping( "/modify/{postSN}/{boardSN}")
	public String getModify(@PathVariable("postSN") Long postSN , @PathVariable("boardSN")Long boardSN
			,@ModelAttribute("cri") Criteria cri
	, Model model) {
		
		return "category/modify";
		
	}
	
	@GetMapping("/get/{postSN}")
	public String get(@PathVariable("postSN") Long postSN , @ModelAttribute("cri") Criteria cri
	, Model model) {
		
		BoardVO vo = service.get(postSN);
		
		if(vo != null) {

			model.addAttribute("board", vo);
			return "category/get";
		
		}else {
			
			
			return "category/main";

		}
	}
	
	// 게시글 내용 보여줌
	@GetMapping(value ="/{postSN}",
			produces = { MediaType.APPLICATION_XML_VALUE,
					 MediaType.APPLICATION_JSON_UTF8_VALUE})
	public ResponseEntity<BoardVO> getPost(@PathVariable("postSN") Long postSN ){
		
		return new ResponseEntity<>(service.get(postSN) , HttpStatus.OK);
		
	}
	
	@PostMapping("/modify")
	public String modify(@RequestParam("postSN") Long postSN,
						 @RequestParam("boardSN") Long boardSN,	
						 BoardVO vo,RedirectAttributes rttr) {
		
		boolean updatePost = service.update(vo);
		
		if(updatePost) {
			log.info("modify after");
			rttr.addFlashAttribute("result", "success");
		}
		
		return "redirect:/category/board/" + boardSN;
		
	}
	
	@PostMapping("/remove")
	public String remove(@RequestParam("postSN") Long postSN , 
						 @RequestParam("boardSN") Long boardSN , RedirectAttributes rttr) {
		
		boolean deletePost = service.delete(postSN);
		
		if(deletePost) {
			rttr.addFlashAttribute("result", "success");
			log.info("삭제 후 1");
		}
			log.info("삭제 후 2");
		return "redirect:/category/board/" + boardSN;
		
	}
	
}
