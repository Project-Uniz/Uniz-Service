package com.uniz.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.uniz.domain.BoardAttachVO;
import com.uniz.domain.ChannelAttachVO;
import com.uniz.domain.ChannelBoardVO;
import com.uniz.domain.ChannelPageDTO;
import com.uniz.domain.ChannelVO;
import com.uniz.domain.Criteria;
import com.uniz.mapper.ChannelMapper;
import com.uniz.service.ChannelService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@RequestMapping("/channel/*")
@Controller
@Log4j
@AllArgsConstructor
public class ChannelController {
	
	private ChannelService service;
	private ChannelMapper mapper;
	
	// main 페이지로 이동
	@GetMapping("/ch")
	public String getMain(Model model) {
		
		return "channel/main";
	}
	
	//채널 생성 페이지로 이동
	@GetMapping("/chcreate")
	public String getChCreate(Model model) {
		return "channel/chcreate";
	}
	
	
	// postSN 을 가진채로 게시글 읽는 페이지로 이동
	@GetMapping("/get/{postSN}")
	public String get(@PathVariable("postSN") Long postSN, Model model ) throws Exception {
	
			if(mapper.checkPost(postSN) == 1){
				
				ChannelBoardVO vo = service.getPost(postSN);
				log.info("vo= " +vo);
				log.info("userSN= " + vo.getUserSN());
				model.addAttribute("board", vo);
				
				return "channel/get";
			
			}else {
			
				return "channel/main";
				
			}
		
	}
	
	// 채널 게시판으로 이동
	@GetMapping("/board/{channelSN}")
	public String getBoard(@PathVariable("channelSN")Long channelSN, ChannelBoardVO vo ,Model model) {
		
		
		if(mapper.checkChannel(channelSN) == 1){
		
			model.addAttribute("channel", vo);
		
			return "channel/board";

		}else {
		
			return "channel/main";
		
		}
	}
	
	// channelSN 을 가지고 게시글 쓰는 페이지로 이동
	@GetMapping("/register/{channelSN}")
	public String register(@PathVariable("channelSN") Long channelSN ,Model model) {
		log.info("channelSN= " +channelSN);
		
		return "channel/register";
	}
	
	// postSN 과 channelSN을 가지고 게시글 수정 페이지로 이동
	@GetMapping("/modify/{postSN}/{channelSN}")
	public String modify(@PathVariable("postSN") Long postSN,@PathVariable("channelSN") Long channelSN , Model model) {
		
		return "channel/modify";
	}
	
	
	// 게시글 작성
	@PostMapping("/register")
	public String register( ChannelBoardVO vo , RedirectAttributes rttr) {
		
		if(vo.getAttachList() != null) {
			
			vo.getAttachList().forEach(attach -> log.info(attach));
			
		}
		
		service.register(vo);
		
		rttr.addFlashAttribute("result", vo.getPostSN());
		log.info("vo= " +vo);
		
		return "redirect:/channel/board/" + vo.getChannelSN();
	}
	
	@PostMapping("/chcreate")
	public String createChannel (ChannelVO vo , RedirectAttributes rttr) {
		service.createChannel(vo);
		rttr.addFlashAttribute("reult", vo.getChannelTitle());
		
		return "redirect:/channel/ch/";
	}
	
	// 채널  목록 보여줌
	@GetMapping(value= "/list" ,
	produces = { MediaType.APPLICATION_XML_VALUE,
				 MediaType.APPLICATION_JSON_UTF8_VALUE})
	public ResponseEntity<List<ChannelBoardVO>> getChannelList(){
		log.info("get Channel List........");
		return new ResponseEntity<>(service.getChannelList() , HttpStatus.OK);
	}
	
	// 채널 별 게시글 목록 보여줌
	@GetMapping(value ="/list/{channelSN}/{page}",
			produces = {
					 MediaType.APPLICATION_JSON_UTF8_VALUE})
	public ResponseEntity<ChannelPageDTO>getPostList (@PathVariable("channelSN") Long channelSN, @PathVariable("page") int page ){
		
		Criteria cri = new Criteria(page, 10);
		
		return new ResponseEntity<>(service.getPostListPaging( cri , channelSN), HttpStatus.OK);
		
	}
	
	// 채널 게시물 전체 목록 보여줌
	@GetMapping(value ="/list/all/{page}",
			produces = { MediaType.APPLICATION_XML_VALUE,
					 MediaType.APPLICATION_JSON_UTF8_VALUE})
	public ResponseEntity<ChannelPageDTO>getAllPost(@PathVariable("page") int page){
		
		Criteria cri = new Criteria(page, 10);
		
		return new ResponseEntity<>(service.getListPage(cri), HttpStatus.OK);
	}
	
	// 게시글  보여줌
	@GetMapping(value = "/{postSN}", 
			produces = { MediaType.APPLICATION_XML_VALUE,
					 MediaType.APPLICATION_JSON_UTF8_VALUE})
	public ResponseEntity<ChannelBoardVO> getPost(@PathVariable("postSN") Long postSN ){
		
		return new ResponseEntity<>(service.getPost(postSN) , HttpStatus.OK);
		
	}
	
	
	// 게시글 조회
//	@GetMapping("/getPost/{postSN}")
//	public void getPost(@RequestParam("postSN") Long postSN , Model model) {
//		model.addAttribute("board", service.getPost(postSN));
//		log.info("얍얍얍 = " + model);
//	}
	
	//게시글 삭제
	@PostMapping(value ="/remove")		
	public String remove(@RequestParam("postSN") Long postSN,@RequestParam("channelSN") Long channelSN ,RedirectAttributes rttr){
		
		List<ChannelAttachVO> attachList = service.getAttachList(postSN);
		
		log.info("게시물 삭제 : " + postSN);
		
		boolean deletecheck = service.delete(postSN);
		
		if(deletecheck) {
			
			deleteFiles(attachList);
			rttr.addFlashAttribute("result", "success");
		}
		
		return "redirect:/channel/board/" + channelSN;
	}
	
	// 게시글 수정 하는 기능
	@PostMapping("/modify")
	public String modify(@RequestParam("postSN") Long postSN ,@RequestParam("channelSN") Long channelSN ,ChannelBoardVO vo, RedirectAttributes rttr) {
		log.info("modify before");
		
		if(service.update(vo)) {
			log.info("modify after");
			rttr.addFlashAttribute("result", "success");
		}
		
		return "redirect:/channel/board/" + channelSN;
		
	}
	
	@GetMapping(value ="/chgetAttachList",
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ResponseEntity<List<ChannelAttachVO>> getAttachList(Long postSN){
		
		log.info("getAttachList====== : " + postSN);
		
		return new ResponseEntity<>(service.getAttachList(postSN) , HttpStatus.OK);
		
	}
	
	
private void deleteFiles(List<ChannelAttachVO> attachList) {
		
		if(attachList == null || attachList.size() == 0) {
			return;
		}
		attachList.forEach(attach -> {
			
		  try {
			  
		  
			Path file = Paths.get("C:\\ch\\file\\" + attach.getUploadPath()+ "\\"+
					attach.getUuid()+"_" + attach.getFileName());
			
			Files.deleteIfExists(file);
			
			if(Files.probeContentType(file).startsWith("image")) {
				
				Path thumNail = Paths.get("C:\\ch\\file\\" + attach.getUploadPath() + 
						"\\s_" + attach.getUuid()+"_"+attach.getFileName());
				
				Files.delete(thumNail);
				
			}
			
		  }catch(Exception e) {
			  log.error("delete File error " + e.getMessage());
		  }
		});
		
	}
	
	
	
}
