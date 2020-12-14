package com.uniz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.uniz.domain.EmailVO;
import com.uniz.utils.EmailSender;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
public class EmailController {
	
	@Autowired
	private EmailSender emailSender;
	
	@RequestMapping("/mail")
	public void sendEmailAction() throws Exception{
		
		 System.out.println("Email Controller");
	        
	        EmailVO email = new EmailVO();
	        
	        
	        email.setReceiver("dfdf124551@gmail.com");
	        email.setContent("테스트 내용");
	        email.setSubject("테스트 제목 ");
	        
	        emailSender.SendEmail(email);
	        
	    }
	    
	}