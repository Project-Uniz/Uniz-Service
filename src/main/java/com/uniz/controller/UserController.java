  package com.uniz.controller;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.uniz.domain.UserTemp;
import com.uniz.service.UserService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;



@Controller
@Log4j
@RequestMapping("/user/*")
@AllArgsConstructor
@SessionAttributes
public class UserController {

		private UserService service;
		
		HttpSession session;
		// 
		
		@GetMapping("/register")
		public String goRegister() {
			System.out.println("I am in register!!!! succeeded");
			
			return "/user/register";
		}
		
		@GetMapping("/loginForm")
		public String get3() {
			System.out.println("I'm in loginForm!!!! succeeded.");
			
			return "/user/loginForm";
		}

		@GetMapping("/logout")
		public String get4() {
			System.out.println("Logout! succeeded.");
			
			return "maincontent";
		}
		
		@PostMapping("/register")
		public String register(UserTemp vo, Model model) {

			System.out.println(vo.getUserId());
			System.out.println(vo.getPassword());

			boolean result = service.isNotNull(vo);
			// 아이디가 널이야? 확인하는 메서드.

			System.out.println(result);

			if (result == true) {
				// 아이디가 널이 아니야! 중복체크하고 정보 집어넣어!

				String result2 = service.register(vo);

				model.addAttribute("msg", result2);

				return "maincontent";
				// 메인으로 가! 성공했으면 success가 뜰것임.

			} else {

				model.addAttribute("msg", "null..enter the value");

				return "/user/register";
			}
		}
		
		@PostMapping("/loginForm")
		public String login(UserTemp vo, HttpServletRequest request, HttpServletResponse response, Model model) {

			// 여기서도 널 체크. 아이디와 패스워드가 널이야?
			boolean result = service.isNotNull(vo);
			
			

			if (result == true) {
				// 둘다 널이 아니라면 아이디랑 패스워드 맞는 지 확인하고 세션을 만들어줘!!
				String address = service.login(vo, request, response);
				
				return address;

			} else {
				// 애초에 널이라 뭣도 안되겠다.

				model.addAttribute("msg", "nulll..enter the value");
				
				return "/user/loginForm";

			}
		}
		@PostMapping("/logout")
		public String logout(UserTemp vo, HttpServletRequest request, HttpServletResponse response, Cookie cookie){
			
			session.invalidate();
		
			service.deleteCookie(vo, request, response);
			
		   System.out.println("로그아웃되었습니다.");
			
			return "maincontent";
		}
		

	}	
		

