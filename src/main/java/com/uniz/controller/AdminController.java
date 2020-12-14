package com.uniz.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.uniz.domain.ApplyVO;
import com.uniz.domain.EmailVO;
import com.uniz.service.AdminService;
import com.uniz.service.ApplyCreatorService;
import com.uniz.utils.EmailSender;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@RequestMapping("/admin/*")
@Controller
@Log4j
@AllArgsConstructor
public class AdminController {
	
	private AdminService adminService;
	private ApplyCreatorService service;
	private EmailSender emailSender;
	
	@GetMapping("/list")
	public void getList() {
		
	}
	
	@GetMapping("/detail/{userSN}")
	public String applyDetail(@PathVariable("userSN")Long userSN, Model model) {
		
		log.info(" userSN : " + userSN);
		
		ApplyVO vo = service.getApply(userSN);
		
		model.addAttribute("apply", vo);
		
		return "/admin/detail";
		
	}
	
	@PostMapping("/accept")
	public String acceptApply(ApplyVO vo, RedirectAttributes rttr) throws Exception{
		
		log.info("userSN : " + vo.getUserSN());
		
		boolean test = adminService.acceptApply(vo.getUserSN());
		
		String userEmail = "";
		
		if(test) {
			
			EmailVO email = new EmailVO();
			
			//log.info("email : " + service.getApply(vo.getUserSN()).getEmail());
	        
			userEmail = service.getApply(vo.getUserSN()).getEmail();
	        
	        email.setReceiver(userEmail);
	        email.setContent("<strong>저희 Uniz를 이용 해주셔서 감사합니다.</strong>" + "<br><br><br>" + 
	        "크리에이터 등록이 되셨으므로 채널 게시판을 만드실 수 있으십니다.");
	        email.setSubject("Uniz 크리에이터 신청 관련 이메일입니다");
	        
	        emailSender.SendEmail(email);
	        
		}
		
		return "/admin/list";
		
	}
	
	@PostMapping("/refuse")
	public String refuseApply(ApplyVO vo, RedirectAttributes rttr) throws Exception{
		
		log.info("userSN : " + vo.getUserSN());
		
		String userEmail = "";
		
		EmailVO email = new EmailVO();
		
		//log.info("email : " + service.getApply(vo.getUserSN()).getEmail());
        
		userEmail = service.getApply(vo.getUserSN()).getEmail();
        
        email.setReceiver(userEmail);
        email.setContent("<strong>저희 Uniz를 이용 해주셔서 감사합니다.</strong>" + "<br><br><br>" + 
        		"신청하신 내용이 요건에 충족하지 못하여 크리에이터 등록이 거부 되었습니다." + "<br><br><br>" 
        		+"크리에이터 신청 내용을 수정 해서 다시 접수 해주시길 바랍니다.");
        email.setSubject("Uniz 크리에이터 신청 관련 이메일입니다");
        
        emailSender.SendEmail(email);
        
        return "/admin/list";
		
	}
}
