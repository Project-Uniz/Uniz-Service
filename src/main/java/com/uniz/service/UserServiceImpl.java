package com.uniz.service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.uniz.domain.UserDTO;
import com.uniz.mapper.UserMapper;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;



@Log4j
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{

	private UserMapper mapper;
	
	@Override
	public boolean insertUserData(UserDTO dto) {  //데이터 insert  true.
		log.info("insert into userData................."+ dto);
        return mapper.insertUserData(dto)==1;		
	}
	@Override
	public boolean getExistingUserId( String userId) { //아이디와 닉네임 중복체크 true.
		log.info("findExistingUserId.................");
		return mapper.findExistingUserId(userId)==0;
	}
	@Override
	public boolean getExistingNick(String nick) {
		log.info("findExistingNick.......!!!!!");
		return mapper.findExistingNick(nick)==0;
	}
	@Override
	public boolean modifyUserInfo(UserDTO dto) {
		
		
		log.info("modify........."+ dto);
		return mapper.updateUserInfo(dto)==1;
	}
	@Override
	public boolean validateForm(UserDTO dto) { //유효성 검사. 
		if(dto.getNick()=="" ||dto.getUserId()=="" || dto.getPassword()=="") {
			return false;
		}
		log.info("is not null............");
		return true;
	}
	@Override
	public int register(UserDTO dto, Model model) {
		boolean SUCCESS = true;
		
		if(validateForm(dto)!=SUCCESS) {
			model.addAttribute("msg", "유효하지 않은 정보입니다. ");
			return 0;
		}
		if(getExistingNick(dto.getNick())!=SUCCESS) {
			model.addAttribute("msg", "중복된 닉네임입니다.");
			return 0;
		}
		if(getExistingUserId( dto.getUserId())!=SUCCESS) {
			model.addAttribute("msg", "중복된 아이디입니다. ");
			return 0;
		}
		if(insertUserData(dto)!=SUCCESS) {
			model.addAttribute("msg", "회원정보 저장에 실패했습니다. ");
			return 0;
		}
		return 1;
	}
	
	@Override
	public boolean isIdPwdValid(UserDTO dto) {
		return mapper.isIdPwdValid(dto.getUserId(), dto.getPassword())==1;
	}
	
	
	
	@Override
	public int login(UserDTO dto, Model model, HttpServletRequest request, HttpServletResponse response) {

		boolean SUCCESS = true;
		
		if(validateForm(dto)!=SUCCESS) {
			model.addAttribute("msg", "유효하지 않은 정보입니다. ");
			return 0;
		}
//		if(stateCheck(dto.getUserId())!=SUCCESS) { //상태 체크 . 탈퇴했나 . 
//			model.addAttribute("msg", "이미 탈퇴한 계정이라서 로그인이 불가능합니다. ");
//			return 0;
//		}
		if(isIdPwdValid(dto)==SUCCESS) {
			//세션을 생성한다. 
			validateSession(request, dto.getUserId());
		}else {	
			model.addAttribute("msg", "아이디 또는 패스워드가 일치하지 않습니다. ");
			//쿠키를 삭제한다. 
			deleteCookie(request, response);
			return 0;
	}
//		if(isIdRememberChecked(request)==SUCCESS) {  //체크박스에 체크되어야만 쿠키를 생성. 
//			//1. 쿠키 생성.
//			makeCookie(response, dto.getUserId());
//			
//		}
		return 1;
	}

	@Override
	public boolean stateCheck(String userId) {
		log.info("getUserState..............");
		return mapper.selectState(userId)==1; //1이 아니라면 탈퇴한 계정이다. 
	}
	
	@Override
	public void validateSession(HttpServletRequest request, String userId) {
		 HttpSession session = request.getSession();
		 session.setAttribute("userId", userId );
		 
		 System.out.println("Session................."+userId);
	}
	
	@Override
	public void deleteCookie(HttpServletRequest request, HttpServletResponse response) {
		Cookie[] cookies = request.getCookies(); // cookies가 null 수 있음에 주의
		
		if(cookies!=null) {//쿠키가 널이 아니라면
		for(int i=0;i<cookies.length;i++) {
		if(cookies[i].getName().equals("id")) {

		Cookie cookie = new Cookie("userId", ""); // 2. 쿠키를 생성
		cookie.setMaxAge(0); // 3. 쿠키의 유효시간을 0으로 변경(쿠키삭제)
		response.addCookie(cookie); // 4. 쿠키를 응답에 포함시킨다. 
				} // end if
			} // end for.
		} // end null if
	}
	@Override
	public void makeCookie(HttpServletResponse response, String userId) {
		Cookie cookie = new Cookie("userId", userId);
		response.addCookie(cookie);
	}
	@Override
	public boolean isIdRememberChecked(HttpServletRequest request) {
		
		String chkValue = request.getParameter("chk");
		
		if(chkValue == "" || chkValue == null) {
			return false;
		}
		return true;
	}
	
	

	
	

}
