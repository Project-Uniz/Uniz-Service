package com.uniz.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	public String goRegister() {
		log.info("registerForm");
		return "/user/register";
	}
	@GetMapping("/read")
	public String userInfoRead() {
		return "/user/read";
	}
	@GetMapping("/modify")
	public String userInfoModify() {
		return "/user/modify";
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
	public String userInfoModify(UserDTO dto) {
		boolean SUCCESS = true;
		if(service.modifyUserInfo(dto)==SUCCESS) {
			return "/user/read";
		}else {
			return "/user/modify";
		}
		
	}


}
