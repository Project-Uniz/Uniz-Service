package com.uniz.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.uniz.domain.UnizVO;
import com.uniz.domain.UserDTO;
import com.uniz.service.UnizService;
import com.uniz.service.UserService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/user/*")
@AllArgsConstructor
public class UserController {

	private UserService userService;
	private UnizService unizService;
	@GetMapping("/loginForm")
	public String goLoginForm() {
		log.info("loginForm");
		return "/user/loginForm";
	}

	@GetMapping("/register")
	public String goRegister(Model model) {
		
		final int userPreset = 1;
		
		//회원가입시 보여줄 프리셋리스트를 가져온다
		//프리셋 리스트 : 회원가입시 입력할 유저가 관심있는 키워드
		List<UnizVO>userPresetList= unizService.getPresetList(userPreset);
		
		log.info("userPresetList : " + userPresetList);
		
		model.addAttribute("PresetList", userPresetList);
		
		return "/user/register";
	}

	@GetMapping("/deleteConfirm")
	public String updateState() {
		log.info("State Update.......");
		return "/user/deleteConfirm";
	}

	@GetMapping("/info")
	public String userInfoRead() {
		return "/user/info";
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

	@RequestMapping(value = "/nickCheck", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody String nickCheck(@RequestParam("userId") String userId) {

		String str = "";
		boolean SUCCESS = true;
		if (userService.getExistingNick(userId) == SUCCESS) { // 아이디 중복 아님.
			str = "YES";
		} else {
			str = "NO";
		}
		return str;
	}
	
	// 회원가입 폼에서 회원가입 버튼 클릭 - 대윤
	@PostMapping("/register")
	public String register(UserDTO userDto,@RequestParam("unizSN") List<Long> unizSN) {
		final int SUCCESS = 1;

		//회원가입 버튼을 클릭시 필드에 입력한 값이 넘어온다 - dto
		//서비스를 통해 회원가입을 진행한다
		
		userService.userRegister(userDto,unizSN);
		
		return "home";
	}
	@PostMapping("/userIdCheck")
	public @ResponseBody Map<String, Object> userIdDuplicationCheck(@RequestBody String userId){
		
		log.info("userId" + userId);
		
		String resultStr = userService.userIdDuplicationCheck(userId);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("data",resultStr);
		
		log.info("resultStr : " + map);
	
		return map;
	}
	
	@PostMapping("/userNickCheck")
	public @ResponseBody Map<String, Object> userNickDuplicationCheck(@RequestBody String nick){
		
		log.info("userId" + nick);
		
		String resultStr = userService.userNickDuplicationCheck(nick);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("data",resultStr);
		
		log.info("resultStr : " + map);
	
		return map;
	}
	
	@PostMapping("/loginForm")
	public String login(UserDTO dto, Model model, HttpServletRequest request, HttpServletResponse response) {
		final int SUCCESS = 1;
		if (userService.login(dto, model, request, response) == SUCCESS) {

			return "/user/read";
		} else {
			return "/user/loginForm";
		}
	}

	@PostMapping("/modify")
	public String userInfoModify(UserDTO dto, Model model) {

		model.addAttribute("UserDTO", userService.getUserDTO(dto.getUserId()));
		System.out.println("get UserDTO ......." + userService.getUserDTO(dto.getUserId()));

		boolean SUCCESS = true;
		if (userService.modifyUserInfo(dto, model) == SUCCESS) {
			return "/user/read";
		} else {
			return "/user/modify";
		}
	}

	@PostMapping("/userDeleteConfirm")
	public String updateState(UserDTO dto, Model model) {
		boolean SUCCESS = true;
		if (userService.updateState(dto, model) == SUCCESS) {
			return "home";
		} else {
			return "/user/userDeleteConfirm";
		}
	}

}
