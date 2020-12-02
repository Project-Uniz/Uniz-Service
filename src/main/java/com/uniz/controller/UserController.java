package com.uniz.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
	@GetMapping("/logout")
	public String logout(HttpSession session){
		session.invalidate();
		
		return "home";
	}

	@GetMapping("/info")
	public String userInfoRead() {
		
		//1. 회원정보 가져오기
		
		//2. 회원유니즈 가져오기 
		
		//둘다 세션에 넣어버렸음 ㅎㅎ

		return "/user/userInfo";
	}

	// 회원가입 폼에서 회원가입 버튼 클릭
	@PostMapping("/register")
	public String register(UserDTO user,@RequestParam("unizSN") List<Long> unizSN) {
		final int SUCCESS = 1;

		//회원가입 버튼을 클릭시 필드에 입력한 값이 넘어온다 - dto
		//서비스를 통해 회원가입을 진행한다
		
		userService.userRegister(user,unizSN);
		
		return "home";
	}
	//아이디 중복체크 - 대윤 
	@PostMapping("/userIdCheck")
	public @ResponseBody Map<String, Object> userIdDuplicationCheck(@RequestBody String userId){
		
		log.info("userId" + userId);
		
		String resultStr = userService.userIdDuplicationCheck(userId);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("data",resultStr);
		
		log.info("resultStr : " + map);
	
		return map;
	}
	
	//닉네임 중복체크 - 대윤
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
	public String login(UserDTO user, Model model,HttpSession session) {
		final int SUCCESS = 1;
		//데이터 제대로 들어오는것 확인
		log.info("user :"+ user);
		
		int loginResult = userService.userLogin(user,session);
		
		log.info("session Check : " + session.getAttribute("user"));
		
		return loginResult == SUCCESS ? "home" : "/user/loginForm";  
		
	}

}
