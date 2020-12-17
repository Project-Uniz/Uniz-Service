package com.uniz.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.uniz.domain.MyUnizPoint;
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

		// 회원가입시 보여줄 프리셋리스트를 가져온다
		// 프리셋 리스트 : 회원가입시 입력할 유저가 관심있는 키워드
		List<UnizVO> userPresetList = unizService.getPresetList(userPreset);

		log.info("userPresetList : " + userPresetList);

		model.addAttribute("PresetList", userPresetList);

		return "/user/register";
	}

	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();

		return "home";
	}

	@GetMapping("/info")
	public String userInfoRead(HttpSession session, Model model) {

		// 세션없이 접근시 메인으로 리턴
		if (session.getAttribute("user") == null)
			return "/user/loginForm";

		// 1. 회원정보 가져오기 - 세션에 저장된 값

		// 2. 회원유니즈 가져오기
		// 세션에 저장된 값으로 가져온다.
		UserDTO user = (UserDTO) session.getAttribute("user");
		List<MyUnizPoint> userUnizPoint = userService.getUserUniz(user.getUserSN());

		log.info("userUnizPoint" + userUnizPoint);

		model.addAttribute("myUnizPoint", userUnizPoint);

		return "/user/userInfo";
	}

	// 회원가입 폼에서 회원가입 버튼 클릭
	@PostMapping("/register")
	public String register(UserDTO user, @RequestParam("unizSN") List<Long> unizSN) {
		log.info("user : " + user);
		log.info("unizSN : " + unizSN);

		int result = userService.userRegister(user, unizSN);

		log.info("result : " + result);
		return "home";
	}

	@GetMapping("/modify")
	public String modify(HttpSession session) {

		return session.getAttribute("user") != null ? "/user/userModify" : "home";
	}

	@PostMapping("/modify")
	public String modifyAction(UserDTO modifyUserDto, String c_password, HttpSession session, Model model) {

		// 1. 회원정보수정에서 입력한 값 가져오기

		UserDTO userDto = (UserDTO) session.getAttribute("user"); // 세션에 저장된 값

		log.info("userDto - 변경할 값이 들어있는 객체 " + modifyUserDto);
		log.info("현재 비밀번호  : " + c_password);
		log.info("session userDto : " + userDto);

		String resultStr = userService.modifyUser(userDto, modifyUserDto, c_password, session);

		model.addAttribute("MSG", resultStr);

		return resultStr.equals("SUCCESS") ? "home" : "/user/userModify";

	}

	@GetMapping("/delete")
	public String userDelete(HttpSession session) {
		final int STATECODE = 3;
		if (session.getAttribute("user") == null)
			return "home";

		UserDTO userDto = (UserDTO) session.getAttribute("user");

		// 삭제 실행
		userService.changeUserState(userDto.getUserSN(), STATECODE);

		session.invalidate();

		return "home";
	}

	// 아이디 중복체크 - 대윤
	@PostMapping("/userIdCheck")
	public @ResponseBody Map<String, Object> userIdDuplicationCheck(@RequestBody String userId) {

		log.info("userId" + userId);

		String resultStr = userService.userIdDuplicationCheck(userId);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", resultStr);

		log.info("resultStr : " + map);

		return map;
	}

	// 닉네임 중복체크 - 대윤
	@PostMapping("/userNickCheck")
	public @ResponseBody Map<String, Object> userNickDuplicationCheck(@RequestBody String nick) {

		log.info("userId" + nick);

		String resultStr = userService.userNickDuplicationCheck(nick);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("data", resultStr);

		log.info("resultStr : " + map);

		return map;
	}

	@PostMapping("/loginForm")
	public String login(UserDTO user, Model model, HttpSession session) {
		final int SUCCESS = 1;
		// 데이터 제대로 들어오는것 확인
		log.info("user :" + user);

		int loginResult = userService.userLogin(user, session);
		
		log.info("loginResult == " + loginResult);

		log.info("session Check : " + session.getAttribute("user"));

		model.addAttribute("result", loginResult);
		return loginResult == SUCCESS ? "home" : "/user/loginForm";

	}

	// 영상 시청기록 저장

	@PostMapping("/addHistory")
	public @ResponseBody Map<String, Object> addMyPlayLog(Long userSN, Long videoSN, int currentTime) {
		Map<String, Object> map = new HashMap<String, Object>();
		String resultStr = "FAIL";
		if(userSN != null) {
			resultStr = userService.addMyPlayLog(userSN, videoSN, currentTime);
		}
		

		map.put("result", resultStr);
		return map;
	}
}
