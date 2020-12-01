package com.uniz.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.uniz.domain.UserDTO;
import com.uniz.service.UserService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/user/*")
@AllArgsConstructor
public class UserController {
	
	private UserService service;
	
	@GetMapping("/")
	public String goHome() {
		return "home";
	}
	@GetMapping("/loginForm")
	public String goLoginForm() {
		log.info("loginForm");
		return "/user/loginForm";
	}
	@GetMapping("/register")
	public String goRegister(Model model) {
		log.info("registerForm...");
		
		return "/user/register";
	}
	@GetMapping("/deleteConfirm")
	public String updateState() {
		log.info("State Update.......");
		return "/user/deleteConfirm";
	}
	@GetMapping("/read")
	public String userInfoRead() {
		return "/user/read";
	}
	@GetMapping("/modify")
	public String userInfoModify() {
		return "/user/modify";
	}
	
	@GetMapping("/modifySuccess")
	public String modifySuccess() {
		return "/user/modifySuccess";
	}
	@GetMapping("/userDeleteConfirm")
	public String userDeleteConfirm() {
		return "/user/userDeleteConfirm";
	}
	
	@RequestMapping(value="/nickCheck", method = {RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody String nickCheck(@RequestParam("userId")String userId) {
	
		String str = "";
		boolean SUCCESS = true;
		if(service.getExistingNick(userId)==SUCCESS) { //아이디 중복 아님. 
			str = "YES"; 
		}else {
			str = "NO";
		}
		return str;
	}
	
	@PostMapping("/register")
	public String register(UserDTO dto, Model model) {
		final int SUCCESS = 1;
		if(service.register(dto, model)==SUCCESS) {
			
			return "/user/read"; //가입 성공하면 마이페이지로 가서 정보 확인. 
		}else {
			return "/user/register"; //실패시 다시 회원가입페이지로 간다. 
		}
	}
	
	
	@PostMapping("/loginForm")
	public String login(UserDTO dto, Model model, HttpServletRequest request, HttpServletResponse response) {
		final int SUCCESS = 1;
		if(service.login(dto, model, request, response)==SUCCESS) { 
			
			return "/user/read";
		}else {
			return "/user/loginForm";
		}
	}
	@PostMapping("/modify")
	public String userInfoModify(UserDTO dto, Model model) {
		
		model.addAttribute("UserDTO", service.getUserDTO(dto.getUserId()));
		System.out.println("get UserDTO ......."+ service.getUserDTO(dto.getUserId()));
		
		boolean SUCCESS = true;
		if(service.modifyUserInfo(dto, model)==SUCCESS) {
			return "/user/read";
		}else {
			return "/user/modify";
		}
	}
	@PostMapping("/userDeleteConfirm")
	public String updateState(UserDTO dto, Model model) {
		boolean SUCCESS = true;
		if(service.updateState(dto, model)==SUCCESS) {
			return "home";
		}else {
			return "/user/userDeleteConfirm";
		}
	}


}
