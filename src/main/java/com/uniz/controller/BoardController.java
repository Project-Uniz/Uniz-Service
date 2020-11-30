package com.uniz.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.uniz.domain.BoardVO;
import com.uniz.domain.Criteria;
import com.uniz.domain.PageDTO;
import com.uniz.service.BoardService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/board/*")
@AllArgsConstructor
public class BoardController {
	
	private BoardService service;
	
	
	@GetMapping("/list")
	public void showList(Criteria cri ,Model model) {
		
		log.info("list: " + cri);
		model.addAttribute("list", service.getListWithPaging(cri));
		int total = service.getTotal(cri);
		model.addAttribute("pageMaker", new PageDTO(cri, total));

	}
	
	@GetMapping("/register")
	public void register() {
		
	}
	
	@PostMapping("/register")
	public String register(BoardVO board, RedirectAttributes rttr ) {
		//유효성 검사 서버에서 체크 할 수 있게
		service.register(board);
		rttr.addFlashAttribute("result", board.getPostSN());
		return "redirect:/board/list";
		
	}
	
	@GetMapping( "/modify")
	public void getModify(@RequestParam("postSN") Long postSN , @ModelAttribute("cri") Criteria cri
	, Model model) {
		
		log.info(" modify");
		model.addAttribute("board", service.get(postSN));
		
	}
	
	@GetMapping("/get")
	public String get(@RequestParam("postSN") Long postSN , @ModelAttribute("cri") Criteria cri
	, Model model) {
		
		BoardVO vo = service.get(postSN);
		
		if(vo != null) {

			model.addAttribute("board", vo);
			return "board/get";
		
		}else {
			
			model.addAttribute("list", service.getListWithPaging(cri));
			int total = service.getTotal(cri);
			model.addAttribute("pageMaker", new PageDTO(cri, total));
			return "board/list";

		}
	}
	
	@PostMapping("/modify")
	public String modify(BoardVO board, Criteria cri  ,RedirectAttributes rttr) {
		
		boolean updatePost = service.update(board);
		
		if(updatePost) {
			log.info("modify after");
			rttr.addFlashAttribute("result", "success");
		}
		
		return "redirect:/board/list" + cri.getListLink();
		
	}
	
	@PostMapping("/remove")
	public String remove(@RequestParam("postSN") Long postSN , Criteria cri , RedirectAttributes rttr) {
		
		boolean deletePost = service.delete(postSN);
		
		if(deletePost) {
			rttr.addFlashAttribute("result", "success");
			log.info("삭제 후 1");
		}
			log.info("삭제 후 2");
		return "redirect:/board/list"+ cri.getListLink();
		
	}
	
}
