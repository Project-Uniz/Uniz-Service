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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.uniz.domain.ApplyAttachVO;
import com.uniz.domain.ApplyVO;
import com.uniz.domain.BoardAttachVO;
import com.uniz.service.ApplyCreatorService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@RequestMapping("/creator/*")
@Controller
@Log4j
@AllArgsConstructor
public class ApplyCreatorController {
	
	private ApplyCreatorService service;
	
	//추후 로그인 하면 세션 값 담아서 등록 페이지로 이동하게 수정 해야함
	@GetMapping("/apply")
	public void getRegi() {
		
	}
	
	@PostMapping("/apply")
	public String apply(ApplyVO vo, RedirectAttributes rttr) {
		
		if(vo.getAttachList() != null) {
			
			vo.getAttachList().forEach(attach -> log.info(" attach : "+attach));
			
		}
		
		final int CHECKAPPLY = service.checkApply(vo.getUserSN());
		
		if(CHECKAPPLY != 1) {
			
			
			service.apply(vo);
			
			return "redirect:/creator/get?userSN=" + vo.getUserSN();
			
		}
			//신청한게 있으면 신청상세 페이지로 이동
			return "redirect:/channel/ch";
		
	}
	
	@GetMapping({"/get" , "/modify"})
	public void getApply(@RequestParam("userSN") Long userSN, Model model) {
		
		model.addAttribute("apply", service.getApply(userSN));
		
	}
	
	@PostMapping("/modify")
	public String modifyApply(ApplyVO vo , RedirectAttributes rttr) {
		
		final boolean MODIFYAPPLY = service.modifyApply(vo);
		
		if(MODIFYAPPLY) {
			
			rttr.addFlashAttribute("result", "success");
			
		}
		
		return "redirect:/creator/get?userSN=" + vo.getUserSN();
		
	}
	
	@PostMapping("/remove")
	public String cancleApply(@RequestParam("userSN")Long userSN, RedirectAttributes rttr) {
		
		List<ApplyAttachVO> attachList = service.getAttachList(userSN);
		
		final boolean CANCLEAPPLY = service.cancleApply(userSN);
		
		if(CANCLEAPPLY) {
			
			deleteFiles(attachList);
			
			rttr.addFlashAttribute("result", "success");
			
		}
		
		return "redirect:/channel/ch";
		
	}
	
	@GetMapping(value ="/getAttachList",
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ResponseEntity<List<ApplyAttachVO>> getAttachList(Long applySN){
		
		log.info("getAttachList====== : " + applySN);
		
		return new ResponseEntity<>(service.getAttachList(applySN) , HttpStatus.OK);
		
	}
	
	@GetMapping(value = "/getAllApply" ,
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ResponseEntity<List<ApplyVO>> getAllApply(){
		
		return new ResponseEntity<>(service.getAllApply(), HttpStatus.OK);
		
	}
	
	
	private void deleteFiles(List<ApplyAttachVO> attachList) {
			
			if(attachList == null || attachList.size() == 0) {
				return;
			}
			attachList.forEach(attach -> {
				
			  try {
				  
			  
				Path file = Paths.get("C:\\work\\Uniz-Service\\src\\main\\webapp\\resources\\imgUpload\\apply\\" + attach.getUploadPath()+ "\\"+
						attach.getUuid()+"_" + attach.getFileName());
				
				Files.deleteIfExists(file);
				
				if(Files.probeContentType(file).startsWith("image")) {
				
					Path thumNail = Paths.get("C:\\work\\Uniz-Service\\src\\main\\webapp\\resources\\imgUpload\\apply\\" + attach.getUploadPath() + 
							"\\s_" + attach.getUuid()+"_"+attach.getFileName());
					
					Files.delete(thumNail);
					
				}
				
			  }catch(Exception e) {
				  log.error("delete File error " + e.getMessage());
			  }
			});
			
		}
	
}
